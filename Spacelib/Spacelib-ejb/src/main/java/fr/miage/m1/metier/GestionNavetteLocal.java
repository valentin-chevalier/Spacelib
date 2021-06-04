/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Quai;
import fr.miage.m1.utilities.CapaciteNavetteNonAutoriseeException;
import fr.miage.m1.utilities.NavetteInexistanteException;
import fr.miage.m1.utilities.NavetteSansQuaiException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Flo
 */
@Local
public interface GestionNavetteLocal {
 
    public Navette creerNavette(boolean estEnRevision, boolean estDispo, int nbVoyages, int capacite, Quai quai) throws NavetteSansQuaiException, CapaciteNavetteNonAutoriseeException;
    
    public Navette getNavette(Long idNavette);
    
    public boolean verifierCapaciteAutorisee(int capacite) throws CapaciteNavetteNonAutoriseeException;
    
    public List<Navette> getAllNavettes() throws NavetteInexistanteException;

}
