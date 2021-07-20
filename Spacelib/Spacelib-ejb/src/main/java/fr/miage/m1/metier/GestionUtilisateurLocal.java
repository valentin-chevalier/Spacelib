/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.Utilisateur;
import javax.ejb.Local;

/**
 *
 * @author Elias
 */
@Local
public interface GestionUtilisateurLocal {
    
    public Utilisateur creerUtilisateur(String prenom, String nom, String mail, String mdp);
    public Utilisateur getUtilisateur(Long idUtilisateur);
    
}
