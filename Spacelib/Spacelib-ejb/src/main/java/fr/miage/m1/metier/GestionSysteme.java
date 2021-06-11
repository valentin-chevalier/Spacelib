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
import fr.miage.m1.facades.ReservationFacadeLocal;
import fr.miage.m1.utilities.PasDeQuaiDispoException;
import fr.miage.m1.utilities.PasDeReservationPourStationException;
import java.util.ArrayList;
import java.util.Date;
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

    public void calculerDispoQuai(Long idStation) throws PasDeQuaiDispoException, PasDeReservationPourStationException {
        int capaciteAPrevoir = 0;
        Station station = this.gestionStation.getStation(idStation);
        //récupérer la liste des navettes de la station
        List<Navette> listeNavettesByStation = station.getListeNavettes();
        //récupérer le nb de quais de la station
        int nbQuais = station.getListeQuais().size();
        List<Reservation> listeRes = this.reservationFacade.getReservationByStationDepart(idStation);
        //pour chaque réservation
        for (Reservation res : listeRes){
            //dans 10j
            System.out.println("DATE " + res.getDateDepart());
            if (new Date().compareTo(res.getDateDepart()) > -10){
                //calculer nb de quais occupés
                capaciteAPrevoir++;
            }
        }
        int total = 0;
        int occupe = 0;
        //si ratio quais occupés / totalité des quais de la station < 10%
        if (capaciteAPrevoir/nbQuais < 0.1){
            //tant que c'est < 20%
            while (capaciteAPrevoir/nbQuais < 0.2){
                //pour chaque navette
                for (Navette navette : listeNavettesByStation){
                    System.out.println("Avant modif : " + navette.getQuai());
                    //parcourir les stations
                    for (Station s : this.gestionStation.getAllStations()){
                        List<Quai> listeQuais = s.getListeQuais();
                        //récupérer les quais
                        for (Quai q : listeQuais){
                            System.out.println("QUAI : " + q);
                            System.out.println("STATION CONCERNEE : " + q.getStation());
                            //calculer le ratio quais occupés / quais totaux
                            for (Reservation res : listeRes){
                                if (res.getQuaiDepart() == q && new Date().compareTo(res.getDateDepart()) > -10)
                                    occupe++;
                                else 
                                    total++;
                            }
                            System.out.println("Ratio de la station : " + occupe/total);

                            //si ratio < 10%
                            gestioneffectif:
                            if (occupe/total < 0.1)
                                //aucune action
                                break gestioneffectif;
                            else {
                                // tant que ratio < 10%
                                while (occupe/total > 0.1){
                                    //maj le transfert
                                    navette.getQuai().setEstLibre(true);
                                    navette.setQuai(q);
                                    q.setEstLibre(false);
                                    System.out.println("Apres modif : " + navette.getQuai());

                                    s.getListeNavettes().add(navette);
                                }
                            }
                        }
                    }
                }
            }
        }
    }    
    
    public List<Station> stationsQuaisALiberer() throws PasDeQuaiDispoException, PasDeReservationPourStationException {
        float occupe = 0;
        float total = 0;
        List<Station> listeStationsSurchargees = new ArrayList<Station>();
        List<Reservation> reservationsArriveeStation;
        List<Reservation> reservationsDepartStation;
        for (Station station : this.gestionStation.getAllStations()){
            reservationsArriveeStation = this.reservationFacade.getReservationByStationArrivee(station.getId());
            reservationsDepartStation = this.reservationFacade.getReservationByStationDepart(station.getId());
            for (Reservation res : reservationsArriveeStation){
                if (getDifferenceDays(new Date(), res.getDateArrivee()) <= 10 && getDifferenceDays(new Date(), res.getDateArrivee())>0){
                    occupe++;
                    System.out.println("test1 " + getDifferenceDays(new Date(), res.getDateArrivee()));
                }
            }
            for (Reservation res : reservationsDepartStation){
                if (getDifferenceDays(new Date(), res.getDateDepart()) <= 10 && getDifferenceDays(new Date(), res.getDateArrivee())>0){
                    occupe--;
                    System.out.println("test2 " + getDifferenceDays(new Date(), res.getDateArrivee()));
                }
            }
            for (Quai quai : station.getListeQuais()){
                if (!quai.isEstLibre())
                    occupe++;
            }
            total = station.getListeQuais().size();
            System.out.println("Nb quais occupés : " + occupe);
            System.out.println("Nb quais total : " + total);
            System.out.println("Total de STATION : " + station.getNom());
            System.out.println("Ratio occupation/total : " + occupe/total);
            if (occupe/total > 0.9){
                System.out.println("STATION SURCHARGE");
                listeStationsSurchargees.add(station);
            }
            occupe=0;
            total=0;
        }
        return listeStationsSurchargees;
    }
    
    public static int getDifferenceDays(Date d1, Date d2){
        long diff = d2.getTime() - d1.getTime();
        return (int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
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
                        List<Station> listeStations = this.gestionStation.getAllStations();
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
}
