/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.facades;

import fr.miage.m1.entities.Utilisateur;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Valentin
 */
@Stateless
public class UtilisateurFacade extends AbstractFacade<Utilisateur> implements UtilisateurFacadeLocal {

    @PersistenceContext(unitName = "spaceLibPersistanceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilisateurFacade() {
        super(Utilisateur.class);
    }

    @Override
    public Utilisateur creerUtilisateur(String prenom, String nom, String mail, String mdp) {
        Utilisateur user = new Utilisateur();
        user.setMail(mail);
        user.setMdp(mdp);
        user.setNom(nom);
        user.setPrenom(prenom);
        this.create(user);
        return user;
    }

    @Override
    public Utilisateur getUtilisateur(Long idUtilisateur) {
        return this.find(idUtilisateur);
    }
    
}
