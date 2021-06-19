/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.exposition;

import fr.miage.m1.entities.Quai;
import fr.miage.m1.entities.Reservation;
import fr.miage.m1.entities.Station;
import fr.miage.m1.entities.Trajet;
import fr.miage.m1.entities.Usager;
import fr.miage.m1.metier.GestionReservationLocal;
import fr.miage.m1.metier.GestionStationLocal;
import fr.miage.m1.metier.GestionTrajetLocal;
import fr.miage.m1.metier.GestionUsagerLocal;
import fr.miage.m1.spacelibshared.utilities.AucuneReservationException;
import fr.miage.m1.spacelibshared.utilities.CapaciteNavetteInsuffisanteException;
import fr.miage.m1.spacelibshared.utilities.MailUsagerDejaExistantException;
import fr.miage.m1.spacelibshared.utilities.NbPassagersNonAutoriseException;
import fr.miage.m1.spacelibshared.utilities.PasDeQuaiDispoException;
import fr.miage.m1.spacelibshared.utilities.StationInexistanteException;
import fr.miage.m1.spacelibshared.utilities.MailInexistantException;
import fr.miage.m1.spacelibshared.utilities.PasDeNavetteAQuaiException;
import fr.miage.m1.spacelibshared.utilities.ReservationDejaExistanteException;
import fr.miage.m1.spacelibshared.utilities.ReservationInexistanteException;
import fr.miage.m1.spacelibshared.utilities.RevisionNavetteException;
import fr.miage.m1.spacelibshared.utilities.TrajetDejaAcheveException;
import fr.miage.m1.spacelibshared.utilities.TrajetInexistantException;
import fr.miage.m1.spacelibshared.utilities.UsagerInexistantException;
import java.text.ParseException;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Flo
 */
@Stateless
public class ExpoBorne implements ExpoBorneLocal {

    @EJB
    private GestionReservationLocal gestionReservation;

    @EJB
    private GestionTrajetLocal gestionTrajet;

    @EJB
    private GestionStationLocal gestionStation;

    @EJB
    private GestionUsagerLocal gestionUsager;

    @Override
    public Usager creerUsager(String prenom, String nom, String mail, String mdp) throws MailUsagerDejaExistantException {
        return this.gestionUsager.creerUsager(prenom, nom, mail, mdp);
    }

    @Override
    public Usager connecterUsager(String mail, String mdp) throws MailInexistantException{
        return this.gestionUsager.verifierUsagerDansBd(mail, mdp);
    }

    @Override
    public Usager getUsager(Long idUsager) {
        return this.gestionUsager.getUsager(idUsager);
    }

    @Override
    public Station getStation(Long idStation) {
        return this.gestionStation.getStation(idStation);
    }

    @Override
    public Trajet getTrajet(Long idTrajet) {
        return this.gestionTrajet.getTrajet(idTrajet);
    }
    
    @Override
    public Reservation effectuerReservation (String dateDepart, Usager usager, Station stationDepart, Station stationArrivee, int nbPassagers) throws ParseException, PasDeNavetteAQuaiException, RevisionNavetteException, TrajetInexistantException, CapaciteNavetteInsuffisanteException, PasDeQuaiDispoException, StationInexistanteException, UsagerInexistantException, NbPassagersNonAutoriseException, ReservationInexistanteException, ReservationDejaExistanteException, AucuneReservationException{
        return this.gestionReservation.effectuerReservation(dateDepart, usager, stationDepart, stationArrivee, nbPassagers);
    }

    @Override
    public Trajet finaliserTrajet(Usager usager) throws TrajetDejaAcheveException, TrajetInexistantException, UsagerInexistantException, RevisionNavetteException, ReservationInexistanteException, AucuneReservationException {
        return this.gestionTrajet.finaliserTrajet(usager);
    }
    
    @Override
    public Quai demanderReservation(String dateDepart, Usager usager, Station stationDepart, Station stationArrivee, int nbPassagers) throws ParseException, PasDeNavetteAQuaiException, RevisionNavetteException, TrajetInexistantException, CapaciteNavetteInsuffisanteException, PasDeQuaiDispoException, StationInexistanteException, UsagerInexistantException, NbPassagersNonAutoriseException, ReservationInexistanteException, ReservationDejaExistanteException, AucuneReservationException{
        return this.gestionReservation.demanderReservation(dateDepart, usager, stationDepart, stationArrivee, nbPassagers);
    }

}
