/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.facades;

import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Quai;
import fr.miage.m1.entities.Station;
import fr.miage.m1.entities.Trajet;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Valentin
 */
@Stateless
public class StationFacade extends AbstractFacade<Station> implements StationFacadeLocal {

    @PersistenceContext(unitName = "spaceLibPersistanceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StationFacade() {
        super(Station.class);
    }

    @Override
    public Station creerStation(String nom, String coordonnees) {
        Station station = new Station();
        station.setCoordonnees(coordonnees);
        station.setListeNavettes(new ArrayList<Navette>());
        station.setListeQuais(new ArrayList<Quai>());
        station.setNom(nom);
        this.create(station);
        return station;
    }

    @Override
    public Station getStation(Long idStation) {
        return this.find(idStation);
    }
    
}
