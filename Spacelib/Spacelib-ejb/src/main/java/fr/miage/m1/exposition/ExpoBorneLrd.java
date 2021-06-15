/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.exposition;

import fr.miage.m1.entities.EtatTrajet;
import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Operation;
import fr.miage.m1.entities.Quai;
import fr.miage.m1.entities.Reservation;
import fr.miage.m1.entities.Usager;
import fr.miage.m1.entities.Station;
import fr.miage.m1.entities.Trajet;
import fr.miage.m1.entities.Utilisateur;
import fr.miage.m1.metier.GestionReservationLocal;
import fr.miage.m1.metier.GestionStationLocal;
import fr.miage.m1.metier.GestionTrajetLocal;
import fr.miage.m1.metier.GestionUsagerLocal;
import fr.miage.m1.spacelibshared.interfremote.ExpoBorneLrdRemote;
import fr.miage.m1.spacelibshared.utilities.AucuneReservationException;
import fr.miage.m1.spacelibshared.utilities.CapaciteNavetteInsuffisanteException;
import fr.miage.m1.spacelibshared.utilities.EtatRevisionExport;
import fr.miage.m1.spacelibshared.utilities.EtatTrajetExport;
import fr.miage.m1.spacelibshared.utilities.MailInexistantException;
import fr.miage.m1.spacelibshared.utilities.MailUsagerDejaExistantException;
import fr.miage.m1.spacelibshared.utilities.NavetteExport;
import fr.miage.m1.spacelibshared.utilities.NbPassagersNonAutoriseException;
import fr.miage.m1.spacelibshared.utilities.OperationExport;
import fr.miage.m1.spacelibshared.utilities.PasDeNavetteAQuaiException;
import fr.miage.m1.spacelibshared.utilities.PasDeQuaiDispoException;
import fr.miage.m1.spacelibshared.utilities.QuaiExport;
import fr.miage.m1.spacelibshared.utilities.ReservationDejaExistanteException;
import fr.miage.m1.spacelibshared.utilities.ReservationExport;
import fr.miage.m1.spacelibshared.utilities.ReservationInexistanteException;
import fr.miage.m1.spacelibshared.utilities.RevisionNavetteException;
import fr.miage.m1.spacelibshared.utilities.StationExport;
import fr.miage.m1.spacelibshared.utilities.StationInexistanteException;
import fr.miage.m1.spacelibshared.utilities.TrajetDejaAcheveException;
import fr.miage.m1.spacelibshared.utilities.TrajetExport;
import fr.miage.m1.spacelibshared.utilities.TrajetInexistantException;
import fr.miage.m1.spacelibshared.utilities.UsagerExport;
import fr.miage.m1.spacelibshared.utilities.UsagerInexistantException;
import fr.miage.m1.spacelibshared.utilities.UtilisateurExport;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Flo
 */
@Stateless
public class ExpoBorneLrd implements ExpoBorneLrdRemote {

    @EJB
    private GestionReservationLocal gestionReservation;

    @EJB
    private GestionTrajetLocal gestionTrajet;

    @EJB
    private GestionStationLocal gestionStation;

    @EJB
    private GestionUsagerLocal gestionUsager;

    @Override
    public UsagerExport creerUsager(String prenom, String nom, String mail, String mdp) throws MailUsagerDejaExistantException {
        Usager usager = this.gestionUsager.creerUsager(prenom, nom, mail, mdp);
        return new UsagerExport(usager.getId(), usager.getPrenom(), usager.getNom(), usager.getNom(), usager.getMdp());
    }

    @Override
    public UsagerExport connecterUsager(String mail, String mdp) throws MailInexistantException {
        Usager usager = this.gestionUsager.verifierUsagerDansBd(mail, mdp);
        return new UsagerExport(usager.getId(), usager.getPrenom(), usager.getNom(), usager.getNom(), usager.getMdp());
    }

    @Override
    public UsagerExport getUsager(Long idUsager) {
        Usager usager = this.gestionUsager.getUsager(idUsager);
        return new UsagerExport(usager.getId(), usager.getPrenom(), usager.getNom(), usager.getNom(), usager.getMdp());
    }

    @Override
    public StationExport getStation(Long idStation) {
        Station station = this.gestionStation.getStation(idStation);
        return creerStationExport(station);
    }

    @Override
    public TrajetExport getTrajet(Long idTrajet) {
        Trajet trajet = this.gestionTrajet.getTrajet(idTrajet);
        return creerTrajetExport(trajet);
    }

    @Override
    public ReservationExport effectuerReservation(String dateDepart, UsagerExport usagerExport, StationExport stationDepartExport, StationExport stationArriveeExport, int nbPassagers) throws ParseException, PasDeNavetteAQuaiException, RevisionNavetteException, TrajetInexistantException, CapaciteNavetteInsuffisanteException, PasDeQuaiDispoException, StationInexistanteException, UsagerInexistantException, NbPassagersNonAutoriseException, ReservationInexistanteException, ReservationDejaExistanteException, AucuneReservationException {
        Usager usager = new Usager(usagerExport.getId(), usagerExport.getPrenom(), usagerExport.getNom(), usagerExport.getMail(), usagerExport.getMdp());
        Station stationDepart = new Station(stationDepartExport.getId(), stationDepartExport.getNom(), stationDepartExport.getCoordonnees());
        Station stationArrivee = new Station(stationArriveeExport.getId(), stationArriveeExport.getNom(), stationArriveeExport.getCoordonnees());
        Reservation res = this.gestionReservation.effectuerReservation(dateDepart, usager, stationDepart, stationArrivee, nbPassagers);
        return creerReservationExport(res);
    }

    @Override
    public TrajetExport finaliserTrajet(UsagerExport usagerExport) throws TrajetDejaAcheveException, TrajetInexistantException, UsagerInexistantException, RevisionNavetteException, ReservationInexistanteException, AucuneReservationException {
        Usager usager = new Usager(usagerExport.getId(), usagerExport.getPrenom(), usagerExport.getNom(), usagerExport.getMail(), usagerExport.getMdp());
        Trajet trajet = this.gestionTrajet.finaliserTrajet(usager);
        return creerTrajetExport(trajet);
    }

    // METHODES EXPORT RMI 
        
    public List<StationExport> creerListeStationsExport(List<Station> listeStations) {
        List<StationExport> listeStationsExport = new List<StationExport>();
        for (Station station : listeStations) {
            StationExport stationExport = creerStationExport(station);
            listeStationsExport.add(stationExport);
        }
        return listeStationsExport;
    }

    public ArrayList<NavetteExport> creerListeNavettesExport(List<Navette> listeNavettes) {
        List<NavetteExport> listeNavettesExport = new ArrayList<NavetteExport>();
        for (Navette navette : listeNavettes) {
            NavetteExport navetteExport = creerNavetteExport(navette);
            listeNavettesExport.add(navetteExport);
        }
        return listeNavettesExport;
    }

    public NavetteExport creerNavetteExport(Navette navette) {
        return new NavetteExport(navette.getId(), navette.isEstDispo(), navette.isEstEnRevision(), navette.getNbVoyages(), navette.getCapacite(), creerQuaiExport(navette.getQuai()), creerListeOperationsExport(navette.getListeOperations()));
    }

    public ArrayList<OperationExport> creerListeOperationsExport(List<Operation> listeOperations) {
        List<OperationExport> listeOperationsExport = new ArrayList<OperationExport>();
        for (Operation operation : listeOperations) {
            OperationExport operationExport = creerOperationExport(operation);
            listeOperationsExport.add(operationExport);
        }
        return listeOperationsExport;
    }

    public OperationExport creerOperationExport(Operation operation) {
        return new OperationExport(operation.getId(), operation.getDateOperation(), creerEtatRevisionExport(operation.getEtatRevision()), operation.getDateCreationOperation(), creerNavetteExport(operation.getNavette()));
    }

    public EtatRevisionExport creerEtatRevisionExport(Operation.EtatRevision etatRevision) {
        return EtatRevisionExport.valueOf(etatRevision.toString());
    }

    public QuaiExport creerQuaiExport(Quai quai) {
        return new QuaiExport(quai.getId(), quai.getNoQuai(), quai.isEstLibre(), creerStationExport(quai.getStation()));
    }

    public EtatTrajetExport creerEtatTrajetExport(EtatTrajet etatTrajet) {
        return EtatTrajetExport.valueOf(etatTrajet.toString());
    }

    public TrajetExport creerTrajetExport(Trajet trajet) {
        return new TrajetExport(trajet.getId(), trajet.getNbPassagers(), creerEtatTrajetExport(trajet.getEtatTrajet()), trajet.getDateDepart(), trajet.getDateArrivee(), creerStationExport(trajet.getStationDepart()), creerStationExport(trajet.getStationArrivee()), creerQuaiExport(trajet.getQuaiDepart()), creerQuaiExport(trajet.getQuaiArrivee()), creerUtilisateurExport(trajet.getUtilisateur()));
    }

    public UtilisateurExport creerUtilisateurExport(Utilisateur usager) {
        return new UtilisateurExport(usager.getId(), usager.getPrenom(), usager.getNom(), usager.getMail(), usager.getMdp());
    }

    public UsagerExport creerUsagerExport(Usager usager) {
        return new UsagerExport(usager.getId(), usager.getPrenom(), usager.getNom(), usager.getMail(), usager.getMdp());
    }

    public StationExport creerStationExport(Station station) {
        return new StationExport(station.getId(), station.getNom(), station.getCoordonnees(), creerListeQuaisExport(station.getListeQuais()), creerListeNavettesExport(station.getListeNavettes()), creerTrajetExport(station.getTrajet1()), creerTrajetExport(station.getTrajet2()));
    }

    public ArrayList<QuaiExport> creerListeQuaisExport(List<Quai> listeQuais) {
        List<QuaiExport> listeQuaiExport = new ArrayList<QuaiExport>();
        for (Quai quai : listeQuais) {
            QuaiExport quaiExport = creerQuaiExport(quai);
            listeQuaiExport.add(quaiExport);
        }
        return listeQuaiExport;
    }
    
    public ReservationExport creerReservationExport(Reservation reservation){
        return new ReservationExport(reservation.getId(), reservation.getNbPassagers(), reservation.getDateDepart(), reservation.getDateArrivee(), creerNavetteExport(reservation.getNavette()), creerUsagerExport(reservation.getUsager()), creerStationExport(reservation.getStationDepart()), creerStationExport(reservation.getStationArrivee()), creerQuaiExport(reservation.getQuaiDepart()), creerQuaiExport(reservation.getQuaiArrivee()));
    }
}
