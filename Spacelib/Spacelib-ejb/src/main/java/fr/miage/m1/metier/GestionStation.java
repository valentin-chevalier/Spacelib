/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Quai;
import fr.miage.m1.entities.Reservation;
import fr.miage.m1.entities.Station;
import fr.miage.m1.facades.NavetteFacadeLocal;
import fr.miage.m1.facades.QuaiFacadeLocal;
import fr.miage.m1.facades.ReservationFacadeLocal;
import fr.miage.m1.facades.StationFacadeLocal;
import fr.miage.m1.utilities.CapaciteNavetteNonAutoriseeException;
import fr.miage.m1.utilities.StationInexistanteException;
import java.util.Date;
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
    public void transfererNavettesDeStations(Station station) {
        //récupérer toutes les réservations
        List<Reservation> listeReservations = this.reservationFacade.findAll();
        //pour chaque réservation
        for (Reservation res : listeReservations){
            int totalQuaisStationDepart = 0;
            int nbQuaisDispoStationDepart = 0;
            //si la date de réservation est dans - de 10j
            if((res.getStationDepart() == station || res.getStationArrivee() == station) && (new Date().compareTo(res.getDateDepart()) == -10)){
                //récupérer le nombre de quais dispo parmi toutes les quais
                for (Quai q : station.getListeQuais()){
                    totalQuaisStationDepart++;
                    if (q.isEstLibre())
                        nbQuaisDispoStationDepart++;
                }
                //calculer si < 10%
                if ((nbQuaisDispoStationDepart / totalQuaisStationDepart) <0.1){
                    //tant que ça n'est pas > 20%
                    while ((nbQuaisDispoStationDepart / totalQuaisStationDepart) >= 0.2){
                        //rechercher les autres stations
                        List<Station> listeStations = this.stationFacade.findAll();
                        //pour chaque station
                        for (Station nouvelleStation : listeStations){
                            int totalQuais = 0;
                            int nbQuaisDispo = 0;
                            //récupérer les quais
                            for (Quai quai : nouvelleStation.getListeQuais()){
                                totalQuais++;
                                if (quai.isEstLibre()){
                                    nbQuaisDispo++;
                                }
                            }
                            //si le ratio quais dispo / total quais > 20% dans la nouvelle station
                            if ((nbQuaisDispo / totalQuais) > 0.2){
                                //tant que le rapport < 0.1
                                while(!((nbQuaisDispo / totalQuais) < 0.1)){
                                    //pour chaque quai
                                    for (Quai quai : nouvelleStation.getListeQuais()){
                                        //on change le quai de notre navette
                                        for (Navette navette : station.getListeNavettes()){
                                            navette.setQuai(quai);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
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
}
