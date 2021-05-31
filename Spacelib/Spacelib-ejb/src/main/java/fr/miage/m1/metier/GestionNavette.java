/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Quai;
import fr.miage.m1.facades.NavetteFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Flo
 */
@Stateless
public class GestionNavette implements GestionNavetteLocal {

    @EJB
    private NavetteFacadeLocal navetteFacade;
    
    @Override
    public Navette creerNavette(boolean estEnRevision, int nbVoyages, int capacite, Quai quai){
        return this.navetteFacade.creerNavette(estEnRevision, nbVoyages, capacite, quai);
    }

    @Override
    public Navette getNavette(Long idNavette) {
        return this.navetteFacade.find(idNavette);
    }
}
