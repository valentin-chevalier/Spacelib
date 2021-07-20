/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.Utilisateur;
import fr.miage.m1.facades.UtilisateurFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Elias
 */
@Stateless
public class GestionUtilisateur implements GestionUtilisateurLocal {

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;

    @Override
    public Utilisateur creerUtilisateur(String prenom, String nom, String mail, String mdp) {
        return this.utilisateurFacade.creerUtilisateur(prenom, nom, mail, mdp);
    }

    @Override
    public Utilisateur getUtilisateur(Long idUtilisateur) {
        return this.utilisateurFacade.getUtilisateur(idUtilisateur);
    }

    
}
