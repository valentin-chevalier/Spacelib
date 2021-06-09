/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.exposition;

import fr.miage.m1.entities.Station;
import fr.miage.m1.metier.GestionStationLocal;
import fr.miage.m1.metier.GestionSystemeLocal;
import fr.miage.m1.utilities.PasDeQuaiDispoException;
import fr.miage.m1.utilities.PasDeReservationPourStationException;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Flo
 */
@Stateless
public class ExpoConducteur implements ExpoConducteurLocal {

    @EJB
    private GestionStationLocal gestionStation;

    @EJB
    private GestionSystemeLocal gestionSysteme;

    @Override
    public void calculerDispoQuai(Long idStation) throws PasDeQuaiDispoException, PasDeReservationPourStationException{
        this.gestionSysteme.calculerDispoQuai(idStation);
    }

    @Override
    public void transfererNavettesDeStations(Station station) {
        this.gestionSysteme.transfererNavettesDeStations(station);
    }
    
    @Override
    public Station getStation (Long idStation){
        return this.gestionStation.getStation(idStation);
    }

}
