/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.facades;

import fr.miage.m1.entities.Administrateur;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Valentin
 */
@Stateless
public class AdministrateurFacade extends AbstractFacade<Administrateur> implements AdministrateurFacadeLocal {

    @PersistenceContext(unitName = "spaceLibPersistanceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministrateurFacade() {
        super(Administrateur.class);
    }

    @Override
    public Administrateur creerAdmnistrateur(String prenom, String nom, String mail, String mdp) {
        Administrateur admin = new Administrateur();
        admin.setMail(mail);
        admin.setMdp(mdp);
        admin.setNom(nom);
        admin.setPrenom(prenom);
        this.create(admin);
        return admin;
    }

    @Override
    public Administrateur getAdmnistrateur(Long idAdmin) {
        return this.find(idAdmin);
    }
    
}
