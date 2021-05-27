/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Quai;
import fr.miage.m1.entities.Station;
import fr.miage.m1.entities.Trajet;
import fr.miage.m1.facades.StationFacadeLocal;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Elias
 */
@Stateless
public class GestionStation implements GestionStationLocal {

    @EJB
    private StationFacadeLocal stationFacade;

    @Override
    public Station creerStation(String nom, String coordonnees, ArrayList<Quai> listeQuais, ArrayList<Navette> listeNavettes, Trajet trajet1, Trajet trajet2) {
        return this.stationFacade.creerStation(nom, coordonnees, listeQuais, listeNavettes, trajet1, trajet2);
    }

    @Override
    public Station getStation(Long idStation){
        return this.stationFacade.getStation(idStation);
    }

   
}
