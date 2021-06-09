/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Quai;
import fr.miage.m1.entities.Station;
import fr.miage.m1.facades.NavetteFacadeLocal;
import fr.miage.m1.facades.QuaiFacadeLocal;
import fr.miage.m1.facades.ReservationFacadeLocal;
import fr.miage.m1.facades.StationFacadeLocal;
import fr.miage.m1.utilities.CapaciteNavetteNonAutoriseeException;
import fr.miage.m1.utilities.StationInexistanteException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Elias
 */
@Stateless
public class GestionStation implements GestionStationLocal {

    @EJB
    private GestionNavetteLocal gestionNavette;

    @EJB
    private ReservationFacadeLocal reservationFacade;

    @EJB
    private QuaiFacadeLocal quaiFacade;
    
    @EJB
    private NavetteFacadeLocal navetteFacade;

    @EJB
    private StationFacadeLocal stationFacade;
    
    @Override
    public Station creerStation(String nom, String coordonnees) {
        return this.stationFacade.creerStation(nom, coordonnees);
    }

    @Override
    public Station getStation(Long idStation){
        return this.stationFacade.getStation(idStation);
    }

    @Override
    public Station creerStation(String nom, String coordonnees, int nbQuais, int capaciteNavettes) throws CapaciteNavetteNonAutoriseeException{
        this.gestionNavette.verifierCapaciteAutorisee(capaciteNavettes);
        Station station = this.stationFacade.creerStation(nom, coordonnees);
        int numQuai = 1;
        for (int i = 1; i <= nbQuais; i++){
            Quai quai = this.quaiFacade.creerQuai(numQuai, true, station);
            station.getListeQuais().add(quai);
            numQuai++;
            if (i <= nbQuais / 2){
                try {
                    Navette navette = this.navetteFacade.creerNavette(false, true, 0, capaciteNavettes, quai);
                    quai.setEstLibre(false);
                    station.getListeNavettes().add(navette);
                } catch (Exception e){
                    //gestion erreur
                }
            }
        }
        return station;
    }

    @Override
    public boolean verifierStationDansBd(Long idStation) throws StationInexistanteException {
        return this.stationFacade.verifierStationDansBd(idStation);
    }
    
    @Override
    public List<Station> getAllStations(){
        return this.stationFacade.getAllStations();
    }
}
