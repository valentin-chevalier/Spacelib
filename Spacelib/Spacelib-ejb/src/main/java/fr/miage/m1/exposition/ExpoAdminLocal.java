/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.exposition;

import fr.miage.m1.entities.Station;
import fr.miage.m1.utilities.CapaciteNavetteNonAutoriseeException;
import javax.ejb.Local;

/**
 *
 * @author Valentin
 */
@Local
public interface ExpoAdminLocal {
    
    public Station creerStation(String nom, String coordonnees, int nbQuais, int capaciteNavettes) throws CapaciteNavetteNonAutoriseeException;
    
}
