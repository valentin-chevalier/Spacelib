/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.exposition;

import fr.miage.m1.entities.Station;
import fr.miage.m1.metier.GestionStationLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Flo
 */
@Stateless
public class ExpoCarte implements ExpoCarteLocal {

    @EJB
    private GestionStationLocal gestionStation;

    @Override
    public List<Station> getAllStations(){
        return this.gestionStation.getAllStations();
    }
}
