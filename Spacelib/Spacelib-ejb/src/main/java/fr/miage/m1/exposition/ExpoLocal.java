/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.exposition;

import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Quai;
import fr.miage.m1.entities.Utilisateur;
import javax.ejb.Local;

/**
 *
 * @author Flo
 */
@Local
public interface ExpoLocal {
    
    public Navette creerNavette(boolean estEnRevision, int nbVoyages, int capacite, Quai quai);
    
    public Navette getNavette(long idNavette);
    
    public Utilisateur creerUtilisateur(String prenom, String nom, String mail, String mdp);
    
    public Utilisateur getUtilisateur(Long idUtilisateur);
    
}
