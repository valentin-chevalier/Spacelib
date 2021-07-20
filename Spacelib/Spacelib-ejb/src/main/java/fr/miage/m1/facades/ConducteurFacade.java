/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.facades;

import fr.miage.m1.entities.Conducteur;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Valentin
 */
@Stateless
public class ConducteurFacade extends AbstractFacade<Conducteur> implements ConducteurFacadeLocal {

    @PersistenceContext(unitName = "spaceLibPersistanceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConducteurFacade() {
        super(Conducteur.class);
    }

    @Override
    public Conducteur creerConducteur(String prenom, String nom, String mail, String mdp) {
        Conducteur conducteur = new Conducteur();
        conducteur.setMail(mail);
        conducteur.setNom(nom);
        conducteur.setPrenom(prenom);
        conducteur.setMdp(mdp);
        this.create(conducteur);
        return conducteur;
    }

    @Override
    public Conducteur getConducteur(Long idConducteur) {
        return this.find(idConducteur);
    }
    
}
