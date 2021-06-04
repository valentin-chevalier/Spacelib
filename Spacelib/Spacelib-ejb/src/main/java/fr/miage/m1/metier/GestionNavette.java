/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Quai;
import fr.miage.m1.facades.NavetteFacadeLocal;
import fr.miage.m1.utilities.CapaciteNavetteNonAutoriseeException;
import fr.miage.m1.utilities.NavetteInexistanteException;
import fr.miage.m1.utilities.NavetteSansQuaiException;
import java.util.List;
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
    public Navette creerNavette(boolean estEnRevision, boolean estDispo, int nbVoyages, int capacite, Quai quai) throws NavetteSansQuaiException, CapaciteNavetteNonAutoriseeException{
        verifierCapaciteAutorisee(capacite);
        return this.navetteFacade.creerNavette(estEnRevision, estDispo, nbVoyages, capacite, quai);
    }
      
    @Override
    public boolean verifierCapaciteAutorisee(int capacite) throws CapaciteNavetteNonAutoriseeException {
        if (capacite != 2 && capacite != 5 && capacite != 10 && capacite != 15) {
            throw new CapaciteNavetteNonAutoriseeException();
        } 
        return true;
    }
        
    @Override
    public Navette getNavette(Long idNavette) {
        return this.navetteFacade.find(idNavette);
    }
    
    public List<Navette> getAllNavettes() throws NavetteInexistanteException{
        if (navetteFacade.findAll().isEmpty()){
            throw new NavetteInexistanteException();
        }
        return this.navetteFacade.findAll();
    }
}

