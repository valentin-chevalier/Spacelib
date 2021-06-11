/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.Station;
import fr.miage.m1.utilities.PasDeQuaiDispoException;
import fr.miage.m1.utilities.PasDeReservationPourStationException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Valentin
 */
@Local
public interface GestionSystemeLocal {
        
    public void transfererNavettesDeStations(Station station);
    
    public void calculerDispoQuai(Long idStation) throws PasDeQuaiDispoException, PasDeReservationPourStationException;

    public List<Station> stationsQuaisALiberer() throws PasDeQuaiDispoException, PasDeReservationPourStationException;
}
