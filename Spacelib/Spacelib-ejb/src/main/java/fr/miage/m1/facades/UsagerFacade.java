/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.facades;

import fr.miage.m1.entities.Administrateur;
import fr.miage.m1.entities.Usager;
import fr.miage.m1.entities.Utilisateur;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Valentin
 */
@Stateless
public class UsagerFacade extends AbstractFacade<Usager> implements UsagerFacadeLocal {

    @PersistenceContext(unitName = "spaceLibPersistanceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsagerFacade() {
        super(Usager.class);
    }

    @Override
    public Usager creerUsager(String prenom, String nom, String mail, String mdp) {
        Usager user = new Usager();
        user.setMail(mail);
        user.setMdp(mdp);
        user.setNom(nom);
        user.setPrenom(prenom);
        this.create(user);
        return user;
    }

    @Override
    public Usager getUsager(Long idUsager) {
        return this.find(idUsager);
    }
    
}
