/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Quai;
import fr.miage.m1.entities.Station;
import fr.miage.m1.facades.NavetteFacadeLocal;
import fr.miage.m1.facades.QuaiFacadeLocal;
import fr.miage.m1.utilities.CapaciteNavetteException;
import fr.miage.m1.utilities.NavetteSansQuaiException;
import fr.miage.m1.utilities.RevisionNavetteException;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Flo
 */
@Stateless
public class GestionNavette implements GestionNavetteLocal {

    @EJB
    private QuaiFacadeLocal quaiFacade;

    @EJB
    private NavetteFacadeLocal navetteFacade;
    
    @Override
    public Navette creerNavette(boolean estEnRevision, boolean estDispo, int nbVoyages, int capacite, Quai quai) throws NavetteSansQuaiException, CapaciteNavetteException{
        if (this.quaiFacade.find(quai.getId()) == null)
            throw new NavetteSansQuaiException();
        verifierCapaciteAutorisee(capacite);
        return this.navetteFacade.creerNavette(estEnRevision, estDispo, nbVoyages, capacite, quai);
    }
      
    @Override
    public boolean verifierCapaciteAutorisee(int capacite) throws CapaciteNavetteException {
        if (capacite != 2 && capacite != 5 && capacite != 10 && capacite != 15) {
            throw new CapaciteNavetteException();
        } 
        return true;
    }
    
    @Override
    public void incrementerNbVoyages(Navette navette) throws RevisionNavetteException {
        if (navette.getNbVoyages() < 3){
            navette.setNbVoyages(navette.getNbVoyages() + 1);
        } else if (navette.getNbVoyages() >= 3){
            navette.setNbVoyages(0);
            navette.setEstDispo(false);
            navette.setEstEnRevision(false);
        }
    }
        
    @Override
    public Navette getNavette(Long idNavette) {
        return this.navetteFacade.find(idNavette);
    }

    @Override
    public void calculerDisponibiliteNavettes(Station station) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

