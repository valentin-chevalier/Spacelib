/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.ChargeNavette;
import fr.miage.m1.entities.ChargeQuai;
import fr.miage.m1.entities.Conducteur;
import fr.miage.m1.entities.EtatTrajet;
import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Quai;
import fr.miage.m1.entities.Reservation;
import fr.miage.m1.entities.Station;
import fr.miage.m1.entities.Trajet;
import fr.miage.m1.facades.ReservationFacadeLocal;
import fr.miage.m1.spacelibshared.utilities.PasDeNavetteAQuaiException;
import fr.miage.m1.spacelibshared.utilities.PasDeQuaiDispoException;
import fr.miage.m1.spacelibshared.utilities.RevisionNavetteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Valentin
 */
@Stateless
public class GestionSysteme implements GestionSystemeLocal {

    @EJB
    private GestionReservationLocal gestionReservation;

    @EJB
    private ReservationFacadeLocal reservationFacade;

    @EJB
    private GestionStationLocal gestionStation;

    @EJB
    private GestionTrajetLocal gestionTrajet;

    @Override
    public HashMap<Station, ChargeQuai> stationsQuaisALiberer() {
        int nbQuaisOccupes = 0;
        int nbQuaisTotal = 0;
        HashMap<Station, ChargeQuai> listeStationsSurchargees = new HashMap<Station, ChargeQuai>();
        List<Reservation> reservationsArriveeStation;
        List<Reservation> reservationsDepartStation;
        for (Station station : this.gestionStation.getAllStations()) {
            //System.out.println("NOM DE LA STATION " + station.getNom());
            reservationsArriveeStation = this.reservationFacade.getReservationByStationArrivee(station.getId());
            reservationsDepartStation = this.reservationFacade.getReservationByStationDepart(station.getId());
            for (Reservation res : reservationsArriveeStation) {
                if (getDifferenceDays(new Date(), res.getDateArrivee()) <= 10 && getDifferenceDays(new Date(), res.getDateArrivee()) > 0) {
                    nbQuaisOccupes++;
                }
            }
            for (Reservation res : reservationsDepartStation) {
                if (getDifferenceDays(new Date(), res.getDateDepart()) <= 10 && getDifferenceDays(new Date(), res.getDateArrivee()) > 0) {
                    nbQuaisOccupes--;
                }
            }
            for (Quai quai : station.getListeQuais()) {
                if (!quai.isEstLibre()) {
                    nbQuaisOccupes++;
                }
            }
            nbQuaisTotal = station.getListeQuais().size();
            //System.out.println("NB QUAIS DISPO " + nbQuaisOccupes);
            //System.out.println("NB QUAIS TOTAL " + nbQuaisTotal);
            //System.out.println("RATIO DISPO/TOTAL " + (float) nbQuaisOccupes / (float) nbQuaisTotal);
            if ((float) nbQuaisOccupes / (float) nbQuaisTotal > 0.9) {
                //System.out.println("SURCHARGE");
                listeStationsSurchargees.put(station, new ChargeQuai(nbQuaisTotal-nbQuaisOccupes, nbQuaisTotal));
            }
            nbQuaisOccupes = 0;
            nbQuaisTotal = 0;
        }
        return listeStationsSurchargees;
    }

    @Override
    public HashMap<Station, ChargeNavette> stationsNavettesATransferer() {
        int nbNavettesDispo = 0;
        int nbQuais = 0;
        HashMap<Station, ChargeNavette> listeStationsLibres = new HashMap<Station, ChargeNavette>();
        List<Reservation> reservationsArriveeStation;
        for (Station station : this.gestionStation.getAllStations()) {
            //System.out.println("NOM DE LA STATION " + station.getNom());
            reservationsArriveeStation = this.reservationFacade.getReservationByStationArrivee(station.getId());
            for (Reservation res : reservationsArriveeStation) {
                if (getDifferenceDays(new Date(), res.getDateArrivee()) <= 10 && getDifferenceDays(new Date(), res.getDateArrivee()) > 0) {
                    nbNavettesDispo++;
                }
            }
            for (Quai quai : station.getListeQuais()) {
                //recuperation navette du quai
                for (Navette navette : station.getListeNavettes()) {
                    if (navette.getQuai().equals(quai) && navette.isEstDispo()) {
                        nbNavettesDispo++;
                    }
                }
            }
            nbQuais = station.getListeQuais().size();
            //System.out.println("NB NAVETTES DISPO " + nbNavettesDispo);
            //System.out.println("NB QUAIS TOTAL " + nbQuais);
            //System.out.println("RATIO DISPO/TOTAL " + (float) nbNavettesDispo / (float) nbQuais);
            if (nbNavettesDispo / nbQuais < 0.1) {
                //System.out.println("RAMENER DES NAVETTES");
                listeStationsLibres.put(station, new ChargeNavette(nbNavettesDispo, nbQuais));
            }
            nbNavettesDispo = 0;
            nbQuais = 0;
        }

        return listeStationsLibres;
    }

    public static int getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public List<Trajet> creerListeVoyages(Conducteur conducteur) throws RevisionNavetteException, PasDeQuaiDispoException, PasDeNavetteAQuaiException {
        List<Trajet> listeTrajets = new ArrayList<Trajet>();
        HashMap<Station, ChargeQuai> fonctionQuai = stationsQuaisALiberer();
        HashMap<Station, ChargeNavette> fonctionNavette = stationsNavettesATransferer();
        System.out.println(fonctionQuai);
        System.out.println(fonctionNavette);
        Station stationDepart = new Station();
        Station stationArrivee = new Station();
        Navette navette = new Navette();
        Quai quai = new Quai();
        Trajet trajet = new Trajet();
        float ratioParcours = 1;
        float ratioMin = 1;
        listerTrajets :
        while (!stationsQuaisALiberer().isEmpty() || !stationsNavettesATransferer().isEmpty()) {
            ratioParcours = 1;
            ratioMin = 1;
            for (Station station : fonctionQuai.keySet()) {
                ratioParcours = fonctionQuai.get(station).calculerRatio();
                System.out.println("RATIO PARCOURS : " + ratioParcours);
                if (ratioParcours > 0.2) {
                    fonctionQuai.remove(station);
                    if (fonctionQuai.isEmpty()){
                        break listerTrajets;
                    }
                } else if (ratioParcours < ratioMin) {
                    ratioMin = ratioParcours;
                    stationDepart = station;
                }
            }
            System.out.println("ICI : " + stationDepart.toString());
            rechercheNavetteLibre:
            for (Navette navetteParcours : stationDepart.getListeNavettes()) {
                if (navetteParcours.isEstDispo()) {
                    navette = navetteParcours;
                    break rechercheNavetteLibre;
                }
            }
            ratioParcours = 1;
            ratioMin = 1;
            for (Station station : fonctionNavette.keySet()) {
                ratioParcours = fonctionNavette.get(station).calculerRatio();
                if (ratioParcours > 0.2) {
                    fonctionNavette.remove(station);
                    if (fonctionNavette.isEmpty()){
                        break listerTrajets;
                    }
                } else if (ratioParcours < ratioMin) {
                    ratioMin = ratioParcours;
                    stationArrivee = station;
                }
            }
            rechercheQuaiArriveeLibre : 
            for (Quai quaiParcours : stationArrivee.getListeQuais()){
                if (quaiParcours.isEstLibre()){
                    quai = quaiParcours;
                    break rechercheQuaiArriveeLibre;
                }
            }
            trajet = this.gestionTrajet.creerTrajet(1, EtatTrajet.VOYAGE_PLANIFIE, stationDepart, stationArrivee, navette.getQuai(), quai, conducteur);
            listeTrajets.add(trajet);
            fonctionNavette.get(stationArrivee).incrementerNbNavettesLibres();
            fonctionQuai.get(stationDepart).incrementerNbQuaisLibres();
        }
        return listeTrajets;
    }

}
