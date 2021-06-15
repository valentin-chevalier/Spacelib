/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.facades;

import fr.miage.m1.entities.Mecanicien;
import fr.miage.m1.entities.Reparation;
import fr.miage.m1.spacelibshared.utilities.MailInexistantException;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Valentin
 */
@Stateless
public class MecanicienFacade extends AbstractFacade<Mecanicien> implements MecanicienFacadeLocal {

    @PersistenceContext(unitName = "spaceLibPersistanceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MecanicienFacade() {
        super(Mecanicien.class);
    }

    @Override
    public Mecanicien creerMecanicien(String prenom, String nom, String mail, String mdp) {
        Mecanicien mecanicien = new Mecanicien();
        mecanicien.setMail(mail);
        mecanicien.setMdp(mdp);
        mecanicien.setNom(nom);
        mecanicien.setPrenom(prenom);
        mecanicien.setListeReparations(new ArrayList<Reparation>());
        this.create(mecanicien);
        return mecanicien;
    }

    @Override
    public Mecanicien getMecanicien(Long idMecanicien) {
        return this.find(idMecanicien);
    }
    
    @Override
    public Mecanicien verifierMecanicienDansBd(String mail, String mdp) throws MailInexistantException{
        Query q = this.em.createNamedQuery("Mecanicien.verifierDansBd");
        q.setParameter("vmail", mail);
        q.setParameter("vmdp", mdp);
        //si pas de résultat dans la bd
        if(q.getResultList().isEmpty())
            //lever exception pour usager inexistant
            throw new MailInexistantException();
        return (Mecanicien)q.getSingleResult();
    }
    
}
