/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.exposition;

import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Quai;
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
    private GestionNavetteLocal gestionNavette;
    
    @Override
    public void creerNavette(boolean estEnRevision, int nbVoyages, int capacite, Quai quai) {
        this.gestionNavette.creerNavette(estEnRevision, nbVoyages, capacite, quai);
    }
    
    @Override
    public Navette getNavette(long idNavette) {
        return this.gestionNavette.getNavette(idNavette);
    }

}
