/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.exposition;

import fr.miage.m1.entities.Usager;
import fr.miage.m1.metier.GestionReservationLocal;
import fr.miage.m1.metier.GestionStationLocal;
import fr.miage.m1.metier.GestionTrajetLocal;
import fr.miage.m1.metier.GestionUsagerLocal;
import fr.miage.m1.spacelibshared.interfremote.ExpoBorneLrdRemote;
import fr.miage.m1.spacelibshared.utilities.MailInexistantException;
import fr.miage.m1.spacelibshared.utilities.MailUsagerDejaExistantException;
import fr.miage.m1.spacelibshared.utilities.UsagerExport;
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
    public UsagerExport connecterUsager(String mail, String mdp) throws MailInexistantException{
        Usager usager = this.gestionUsager.verifierUsagerDansBd(mail, mdp);
        return new UsagerExport(usager.getId(), usager.getPrenom(), usager.getNom(), usager.getNom(), usager.getMdp());
    }

    @Override
    public UsagerExport getUsager(Long idUsager) {
        Usager usager = this.gestionUsager.getUsager(idUsager);
        return new UsagerExport(usager.getId(), usager.getPrenom(), usager.getNom(), usager.getNom(), usager.getMdp());
    }
 /*   
    @Override
    public StationExport getStation(Long idStation) {
        Station station = this.gestionStation.getStation(idStation);
        ArrayList<QuaiExport> quaisExport = new ArrayList<>();
        return new StationExport(station.getId(), station.getNom(), station.getCoordonnees(), station.getListeQuais(), station.getListeNavettes(), station.getTrajet1(), station.getTrajet2());
    }

    @Override
    public TrajetExport getTrajet(Long idTrajet) {
        Trajet trajet = this.gestionTrajet.getTrajet(idTrajet);
        return new TrajetExport(trajet.getId(), trajet.getNbPassagers(), trajet.getEtatTrajet(), trajet.getDateDepart(), trajet.getDateArrivee(), trajet.getStationDepart(), trajet.getStationArrivee(), trajet.getQuaiDepart(), trajet.getQuaiArrivee(), trajet.getUtilisateur());
    }

    @Override
    public ReservationExport effectuerReservation (String dateDepart, Usager usager, Station stationDepart, Station stationArrivee, int nbPassagers) throws ParseException, PasDeNavetteAQuaiException, RevisionNavetteException, TrajetInexistantException, CapaciteNavetteInsuffisanteException, PasDeQuaiDispoException, StationInexistanteException, UsagerInexistantException, NbPassagersNonAutoriseException, ReservationInexistanteException, ReservationDejaExistanteException, AucuneReservationException{
        Reservation res = this.gestionReservation.effectuerReservation(dateDepart, usager, stationDepart, stationArrivee, nbPassagers);
    }

    @Override
    public TrajetExport finaliserTrajet(UsagerExport usagerExport) throws TrajetDejaAcheveException, TrajetInexistantException, UsagerInexistantException, RevisionNavetteException, ReservationInexistanteException, AucuneReservationException {
        Usager usager = new Usager(usagerExport.getId(), usagerExport.getPrenom(), usagerExport.getNom(), usagerExport.getMail(), usagerExport.getMdp());
        Trajet trajet = this.gestionTrajet.finaliserTrajet(usager);
        EtatTrajetExport etatTrajetExport = trajet.getEtatTrajet();
        return new TrajetExport(trajet.getId(), trajet.getNbPassagers(), trajet.getEtatTrajet(), trajet.getDateDepart(), trajet.getDateArrivee(), trajet.getStationDepart(), trajet.getStationArrivee(), trajet.getQuaiDepart(), trajet.getQuaiArrivee(), trajet.getUtilisateur());
    }*/
}
