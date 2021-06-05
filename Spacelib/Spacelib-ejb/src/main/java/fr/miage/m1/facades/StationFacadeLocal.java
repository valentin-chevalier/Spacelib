/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.facades;

import fr.miage.m1.entities.Station;
import fr.miage.m1.utilities.StationInexistanteException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Valentin
 */
@Local
public interface StationFacadeLocal {

    void create(Station station);

    void edit(Station station);

    void remove(Station station);

    Station find(Object id);

    List<Station> findAll();

    List<Station> findRange(int[] range);

    int count();
    
    public Station creerStation(String nom, String coordonnees);
    
    public Station getStation(Long idStation);
    
    public boolean verifierStationDansBd(Long idStation) throws StationInexistanteException;
    
    public List<Station> getAllStations();
}
