/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.EtatTrajet;
import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Quai;
import fr.miage.m1.entities.Reservation;
import fr.miage.m1.entities.Station;
import fr.miage.m1.entities.Trajet;
import fr.miage.m1.entities.Usager;
import fr.miage.m1.entities.Utilisateur;
import fr.miage.m1.facades.NavetteFacadeLocal;
import fr.miage.m1.facades.ReservationFacadeLocal;
import fr.miage.m1.facades.TrajetFacadeLocal;
import fr.miage.m1.spacelibshared.utilities.AucuneReservationException;
import fr.miage.m1.spacelibshared.utilities.CapaciteNavetteInsuffisanteException;
import fr.miage.m1.spacelibshared.utilities.NbPassagersNonAutoriseException;
import fr.miage.m1.spacelibshared.utilities.PasDeNavetteAQuaiException;
import fr.miage.m1.spacelibshared.utilities.PasDeQuaiDispoException;
import fr.miage.m1.spacelibshared.utilities.ReservationDejaExistanteException;
import fr.miage.m1.spacelibshared.utilities.ReservationInexistanteException;
import fr.miage.m1.spacelibshared.utilities.RevisionNavetteException;
import fr.miage.m1.spacelibshared.utilities.StationInexistanteException;
import fr.miage.m1.spacelibshared.utilities.TrajetDejaAcheveException;
import fr.miage.m1.spacelibshared.utilities.TrajetInexistantException;
import fr.miage.m1.spacelibshared.utilities.UsagerInexistantException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Flo
 */
@Stateless
public class GestionTrajet implements GestionTrajetLocal {

    @EJB
    private ReservationFacadeLocal reservationFacade;

    @EJB
    private NavetteFacadeLocal navetteFacade;

    @EJB
    private GestionUsagerLocal gestionUsager;

    @EJB
    private GestionStationLocal gestionStation;

    @EJB
    private GestionOperationLocal gestionOperation;

    @EJB
    private GestionReservationLocal gestionReservation;

    @EJB
    private TrajetFacadeLocal trajetFacade;

    @Override
    public Trajet creerTrajet(int nbPassagers, EtatTrajet etatTrajet, Station stationDepart, Station stationArrivee, Quai quaiDepart, Quai quaiArrivee, Utilisateur utilisateur) throws RevisionNavetteException, PasDeQuaiDispoException, PasDeNavetteAQuaiException {
        //verifier que la station de départ peut accueillir la navette
        List<Quai> listeQuaisDepart = verifierQuaiDispo(stationDepart);
        if (listeQuaisDepart.isEmpty()) {
            throw new PasDeQuaiDispoException("DEPART");
        }
        //vérifier pour la station d'arrivée 
        List<Quai> listeQuaisArrivee = verifierQuaiDispo(stationArrivee);
        if (listeQuaisArrivee.isEmpty()) {
            throw new PasDeQuaiDispoException("ARRIVEE");
        }
        Trajet trajet = null;
        //pour chaque navette de la stationDepart
        for (Navette navette : stationDepart.getListeNavettes()) {
            //chercher une navette
            if (navette.isEstDispo() && !navette.isEstEnRevision() && navette.getCapacite() >= nbPassagers && navette.getNbVoyages() < 3) {
                //si trouvé, créer un trajet
                navette.incrementerNbVoyages();
                trajet = trajetFacade.creerTrajet(nbPassagers, etatTrajet, stationDepart, stationArrivee, quaiDepart, quaiArrivee, utilisateur);
                break;
            }
        }
        if (trajet == null) {
            throw new PasDeNavetteAQuaiException();
        }
        return trajet;
    }

    @Override
    public Trajet getTrajet(Long idTrajet) {
        return this.trajetFacade.getTrajet(idTrajet);
    }

    @Override
    public Trajet finaliserTrajet(Usager usager) throws TrajetDejaAcheveException, TrajetInexistantException, UsagerInexistantException, RevisionNavetteException, ReservationInexistanteException, AucuneReservationException {
        if (usager == null) {
            throw new UsagerInexistantException();
        }
        Trajet trajet = this.trajetFacade.recupererTrajet(usager.getId());
        Reservation res = this.gestionReservation.controlerReservation(usager.getId());
        //verifier que le trajet n'ait pas déjà été achevé
        if (EtatTrajet.VOYAGE_INITIE.equals(trajetFacade.recupererTrajet(usager.getId()).getEtatTrajet())) {
            trajet.setEtatTrajet(EtatTrajet.VOYAGE_ACHEVE);
            res.getNavette().setEstDispo(true);
            System.out.println("STATION DEPART " + res.getNavette().getQuai().getStation());

            res.getNavette().setQuai(res.getQuaiArrivee());
            res.getNavette().getQuai().setStation(res.getQuaiArrivee().getStation());
            this.trajetFacade.edit(trajet);
            this.navetteFacade.edit(res.getNavette());
            this.reservationFacade.edit(res);
            System.out.println("STATION ARRIVEE " + res.getNavette().getQuai().getStation());

        } else if (EtatTrajet.VOYAGE_ACHEVE.equals(trajetFacade.recupererTrajet(usager.getId()).getEtatTrajet())) {
            throw new TrajetDejaAcheveException();
        }
        trajet.setDateArrivee(new Date());
        this.gestionOperation.creerOperation(new Date(), res.getNavette());
        return trajet;
    }

    @Override
    public Trajet recupererTrajet(Long idUser) throws TrajetInexistantException {
        return this.trajetFacade.recupererTrajet(idUser);
    }

    @Override
    public List<Quai> verifierQuaiDispo(Station station) {
        List<Quai> listeQuaisDispo = new ArrayList<>();
        for (Quai quai : station.getListeQuais()) {
            if (quai.isEstLibre()) {
                listeQuaisDispo.add(quai);
            }
        }
        return listeQuaisDispo;
    }

    @Override
    public List<Trajet> getAllTrajets() {
        return this.trajetFacade.findAll();
    }

    @Override
    public List<String> getTrajetsPossibles(Long idUsager, Station stationDepart, Station stationArrivee, int nbPassagers, String dateMin, String dateDepart) throws ParseException, PasDeNavetteAQuaiException, RevisionNavetteException, TrajetInexistantException, CapaciteNavetteInsuffisanteException, PasDeQuaiDispoException, StationInexistanteException, UsagerInexistantException, NbPassagersNonAutoriseException, ReservationInexistanteException, ReservationDejaExistanteException, AucuneReservationException, RevisionNavetteException, PasDeQuaiDispoException, PasDeNavetteAQuaiException {
        List<String> listeTrajetsPossibles = new ArrayList<String>();
        List<Navette> listeNavettesDispo = new ArrayList<Navette>();
        List<Quai> listeQuaisDispo = new ArrayList<Quai>();
        //récupérer toutes les navettes de la station 
        for (Navette navette : this.gestionStation.getStation(stationDepart.getId()).getListeNavettes()) {
            //voir si elles sont libres aux dates prévues && voir si elles ont la capacité
            if (navette.isEstDispo() && nbPassagers <= navette.getCapacite()) {
                listeNavettesDispo.add(navette);
                //System.out.println("+1 navette départ");
            }
        }
        for (Quai quai : this.gestionStation.getStation(stationArrivee.getId()).getListeQuais()) {
            //checker station arrivee && voir si au moins 1 quai libre
            if (quai.isEstLibre()) {
                listeQuaisDispo.add(quai);
                //System.out.println("+1 quai arrivée dans la station " + stationArrivee.getNom());
            }
        }
        int i = 0;
        for (Navette navette : listeNavettesDispo) {
            for (Quai quai : listeQuaisDispo) {
                //System.out.println("+1 trajet");
                this.gestionReservation.effectuerReservation(dateDepart, this.gestionUsager.getUsager(idUsager), stationDepart, stationArrivee, nbPassagers);
                String trajet = "Départ [quai de la navette] : " + navette.getQuai().getNoQuai() + " / [date] : " + dateMin + " | Arrivée [quai] " + quai.getNoQuai() + "\n";
                listeTrajetsPossibles.add(trajet);
                i++;
            }
        }
        return listeTrajetsPossibles;
    }

    public static int getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}
