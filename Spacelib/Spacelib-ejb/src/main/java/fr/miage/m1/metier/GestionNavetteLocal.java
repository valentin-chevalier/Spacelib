/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Quai;
import fr.miage.m1.entities.Station;
import fr.miage.m1.utilities.CapaciteNavetteException;
import fr.miage.m1.utilities.NavetteSansQuaiException;
import fr.miage.m1.utilities.RevisionNavetteException;
import javax.ejb.Local;

/**
 *
 * @author Flo
 */
@Local
public interface GestionNavetteLocal {
 
    public Navette creerNavette(boolean estEnRevision, boolean estDispo, int nbVoyages, int capacite, Quai quai) throws NavetteSansQuaiException, CapaciteNavetteException;
    
    public Navette getNavette(Long idNavette);
    
    public boolean verifierCapaciteAutorisee(int capacite) throws CapaciteNavetteException;
    
    public void incrementerNbVoyages(Navette navette) throws RevisionNavetteException;
    
    public void calculerDisponibiliteNavettes(Station station);
}
