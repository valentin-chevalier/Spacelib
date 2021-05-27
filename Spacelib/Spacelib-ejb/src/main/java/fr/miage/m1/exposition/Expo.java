/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.exposition;

import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Quai;
import fr.miage.m1.entities.Utilisateur;
import fr.miage.m1.facades.UtilisateurFacadeLocal;
import fr.miage.m1.metier.GestionNavetteLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Flo
 */
@Stateless
public class Expo implements ExpoLocal {

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;

    @EJB
    private GestionNavetteLocal gestionNavette;
    
    @Override
    public Navette creerNavette(boolean estEnRevision, int nbVoyages, int capacite, Quai quai) {
        Navette nvt = this.gestionNavette.creerNavette(estEnRevision, nbVoyages, capacite, quai);
        return nvt;
    }
    
    @Override
    public Navette getNavette(long idNavette) {
        return this.gestionNavette.getNavette(idNavette);
    }

    @Override
    public Utilisateur creerUtilisateur(String prenom, String nom, String mail, String mdp) {
        return this.utilisateurFacade.creerUtilisateur(prenom, nom, mail, mdp);
    }

    @Override
    public Utilisateur getUtilisateur(Long idUtilisateur) {
        return this.utilisateurFacade.getUtilisateur(idUtilisateur);
    }

}
