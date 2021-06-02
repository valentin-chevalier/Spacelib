/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.Station;
import fr.miage.m1.utilities.CapaciteNavetteException;
import javax.ejb.Local;

/**
 *
 * @author Elias
 */
@Local
public interface GestionStationLocal {
    
    public Station creerStation(String nom, String coordonnees);
    
    public Station creerStation(String nom, String coordonnees, int nbQuais, int capaciteNavettes) throws CapaciteNavetteException;
    
    public Station getStation(Long idStation);
    
    public void transfererNavettesDeStations(Station station);
}
