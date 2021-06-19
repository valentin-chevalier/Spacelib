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
import java.util.logging.Level;
import java.util.logging.Logger;
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
        StationExport stationDepartExport = creerStationExport(trajet.getStationDepart());
        StationExport stationArriveeExport = creerStationExport(trajet.getStationArrivee());
        QuaiExport quaiDepartExport = creerQuaiExport(trajet.getQuaiDepart(), stationDepartExport);
        QuaiExport quaiArriveeExport = creerQuaiExport(trajet.getQuaiArrivee(), stationArriveeExport);
        UtilisateurExport userExport = creerUtilisateurExport(trajet.getUtilisateur());
        return creerTrajetExport(trajet, stationDepartExport, stationArriveeExport, quaiDepartExport, quaiArriveeExport, userExport);
    }

    @Override
    public ReservationExport effectuerReservation(String dateDepart, UsagerExport usagerExport, StationExport stationDepartExport, StationExport stationArriveeExport, int nbPassagers) throws ParseException, PasDeNavetteAQuaiException, RevisionNavetteException, TrajetInexistantException, CapaciteNavetteInsuffisanteException, PasDeQuaiDispoException, StationInexistanteException, UsagerInexistantException, NbPassagersNonAutoriseException, ReservationInexistanteException, ReservationDejaExistanteException, AucuneReservationException {
        try {
            Usager usager = this.gestionUsager.creerUsager(usagerExport.getPrenom(), usagerExport.getNom(), usagerExport.getMail(), usagerExport.getMdp());
            Station stationDepart = this.gestionStation.getStation(stationDepartExport.getId());
            Station stationArrivee = this.gestionStation.getStation(stationArriveeExport.getId());
            Reservation res = this.gestionReservation.effectuerReservation(dateDepart, usager, stationDepart, stationArrivee, nbPassagers);
            //transformer en export pour appeler méthodes rmi
            StationExport stationExportDepart = creerStationExport(stationDepart);
            QuaiExport quaiExportDepart = creerQuaiExport(res.getQuaiDepart(), stationExportDepart);
            StationExport stationExportArrivee = creerStationExport(stationArrivee);
            QuaiExport quaiExportArrivee = creerQuaiExport(res.getQuaiDepart(), stationExportArrivee);
            NavetteExport navetteExport = creerNavetteExport(res.getNavette(), creerQuaiExport(res.getQuaiDepart(), stationExportDepart));
            return creerReservationExport(navetteExport, res, stationExportDepart, stationExportArrivee, quaiExportDepart, quaiExportArrivee, usagerExport);
        } catch (MailUsagerDejaExistantException ex) {
            Logger.getLogger(ExpoBorneLrd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public TrajetExport finaliserTrajet(UsagerExport usagerExport) throws TrajetDejaAcheveException, TrajetInexistantException, UsagerInexistantException, RevisionNavetteException, ReservationInexistanteException, AucuneReservationException {
        try {
            Usager usager = this.gestionUsager.creerUsager(usagerExport.getPrenom(), usagerExport.getNom(), usagerExport.getMail(), usagerExport.getMdp());
            Trajet trajet = this.gestionTrajet.finaliserTrajet(usager);
            StationExport stationExportDepart =
                    creerStationExport(trajet.getStationDepart());
            StationExport stationExportArrivee =
                    creerStationExport(trajet.getStationArrivee());
            QuaiExport quaiExportDepart = creerQuaiExport(trajet.getQuaiDepart(), stationExportDepart);
            QuaiExport quaiExportArrivee = creerQuaiExport(trajet.getQuaiArrivee(), stationExportArrivee);
            return creerTrajetExport(trajet, stationExportDepart, stationExportArrivee, quaiExportDepart, quaiExportArrivee, usagerExport);
        } catch (MailUsagerDejaExistantException ex) {
            Logger.getLogger(ExpoBorneLrd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public QuaiExport demanderReservation(String dateDepart, UsagerExport usagerExport, StationExport stationDepartExport, StationExport stationArriveeExport, int nbPassagers) throws ParseException, PasDeNavetteAQuaiException, RevisionNavetteException, TrajetInexistantException, CapaciteNavetteInsuffisanteException, PasDeQuaiDispoException, StationInexistanteException, UsagerInexistantException, NbPassagersNonAutoriseException, ReservationInexistanteException, ReservationDejaExistanteException, AucuneReservationException {
        try {
            Usager usager = this.gestionUsager.creerUsager(usagerExport.getPrenom(), usagerExport.getNom(), usagerExport.getMail(), usagerExport.getMdp());
            Station stationDepart = this.gestionStation.getStation(stationDepartExport.getId());
            Station stationArrivee = this.gestionStation.getStation(stationArriveeExport.getId());
            Quai quai = this.gestionReservation.demanderReservation(dateDepart, usager, stationDepart, stationArrivee, nbPassagers);
            return creerQuaiExport(quai, stationDepartExport);
        } catch (MailUsagerDejaExistantException ex) {
            Logger.getLogger(ExpoBorneLrd.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return null;
    }

            
    // METHODES EXPORT RMI 
    public List<StationExport> creerListeStationsExport(List<Station> listeStations) {
        List<StationExport> listeStationsExport = new ArrayList<StationExport>();
        for (Station station : listeStations) {
            StationExport stationExport = creerStationExport(station);
            listeStationsExport.add(stationExport);
        }
        return listeStationsExport;
    }

    public ArrayList<NavetteExport> creerListeNavettesExport(List<Navette> listeNavettes, List<QuaiExport> listeQuaisExport) {
        ArrayList<NavetteExport> listeNavettesExport = new ArrayList<NavetteExport>();
        int compteur = 0;
        for (Navette navette : listeNavettes) {
            NavetteExport navetteExport = creerNavetteExport(navette, listeQuaisExport.get(compteur));
            compteur++;
            listeNavettesExport.add(navetteExport);
        }
        return listeNavettesExport;
    }

    public NavetteExport creerNavetteExport(Navette navette, QuaiExport quaiExport) {
        return new NavetteExport(navette.getId(), navette.isEstDispo(), navette.isEstEnRevision(), navette.getNbVoyages(), navette.getCapacite(), quaiExport);
    }

    public ArrayList<OperationExport> creerListeOperationsExport(List<Operation> listeOperations, NavetteExport navetteExport) {
        ArrayList<OperationExport> listeOperationsExport = new ArrayList<OperationExport>();
        for (Operation operation : listeOperations) {
            OperationExport operationExport = creerOperationExport(operation, navetteExport);
            listeOperationsExport.add(operationExport);
        }
        return listeOperationsExport;
    }

    public OperationExport creerOperationExport(Operation operation, NavetteExport navetteExport) {
        return new OperationExport(operation.getId(), operation.getDateOperation(), creerEtatRevisionExport(operation.getEtatRevision()), operation.getDateCreationOperation(), navetteExport);
    }

    public EtatRevisionExport creerEtatRevisionExport(Operation.EtatRevision etatRevision) {
        return EtatRevisionExport.valueOf(etatRevision.toString());
    }

    public QuaiExport creerQuaiExport(Quai quai, StationExport stationExport) {
        return new QuaiExport(quai.getId(), quai.getNoQuai(), quai.isEstLibre(), stationExport);
    }

    public EtatTrajetExport creerEtatTrajetExport(EtatTrajet etatTrajet) {
        return EtatTrajetExport.valueOf(etatTrajet.toString());
    }

    public TrajetExport creerTrajetExport(Trajet trajet, StationExport stationExportDepart, StationExport stationExportArrivee, QuaiExport quaiExportDepart, QuaiExport quaiExportArrivee, UtilisateurExport utilisateurExport) {
        return new TrajetExport(trajet.getId(), trajet.getNbPassagers(), creerEtatTrajetExport(trajet.getEtatTrajet()), trajet.getDateDepart(), trajet.getDateArrivee(), stationExportDepart, stationExportArrivee, quaiExportDepart, quaiExportArrivee, utilisateurExport);
    }

    public UtilisateurExport creerUtilisateurExport(Utilisateur usager) {
        return new UtilisateurExport(usager.getId(), usager.getPrenom(), usager.getNom(), usager.getMail(), usager.getMdp());
    }

    public UsagerExport creerUsagerExport(Usager usager) {
        return new UsagerExport(usager.getId(), usager.getPrenom(), usager.getNom(), usager.getMail(), usager.getMdp());
    }

    public StationExport creerStationExport(Station station) {
        StationExport stationExport = new StationExport(station.getId(), station.getNom(), station.getCoordonnees());
        ArrayList<QuaiExport> listeQuaiExport = creerListeQuaisExport(station.getListeQuais(), stationExport);
        stationExport.setListeQuais(listeQuaiExport);
        ArrayList<NavetteExport> listeNavetteExport = creerListeNavettesExport(station.getListeNavettes(), listeQuaiExport);
        stationExport.setListeNavettes(listeNavetteExport);
        return stationExport;
    }

    public ArrayList<QuaiExport> creerListeQuaisExport(List<Quai> listeQuais, StationExport stationExport) {
        ArrayList<QuaiExport> listeQuaiExport = new ArrayList<QuaiExport>();
        for (Quai quai : listeQuais) {
            QuaiExport quaiExport = creerQuaiExport(quai, stationExport);
            listeQuaiExport.add(quaiExport);
        }
        return listeQuaiExport;
    }

    public ReservationExport creerReservationExport(NavetteExport navetteExport, Reservation reservation, StationExport stationExportDepart, StationExport stationExportArrivee, QuaiExport quaiExportDepart, QuaiExport quaiExportArrivee, UsagerExport usagerExport) {
        return new ReservationExport(reservation.getId(), reservation.getNbPassagers(), reservation.getDateDepart(), reservation.getDateArrivee(), navetteExport, usagerExport, stationExportDepart, stationExportArrivee, quaiExportDepart, quaiExportArrivee);
    }
    

}
