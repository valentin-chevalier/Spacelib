/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.exposition;

import fr.miage.m1.entities.Reservation;
import fr.miage.m1.entities.Station;
import fr.miage.m1.entities.Usager;
import fr.miage.m1.metier.GestionReservationLocal;
import fr.miage.m1.metier.GestionStationLocal;
import fr.miage.m1.metier.GestionUsagerLocal;
import fr.miage.m1.spacelibshared.utilities.AucuneReservationException;
import fr.miage.m1.spacelibshared.utilities.CapaciteNavetteInsuffisanteException;
import fr.miage.m1.spacelibshared.utilities.DelaiAnnulationResDepasseException;
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
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Flo
 */
@Stateless
public class ExpoCarte implements ExpoCarteLocal {

    @EJB
    private GestionUsagerLocal gestionUsager;

    @EJB
    private GestionReservationLocal gestionReservation;

    @EJB
    private GestionStationLocal gestionStation;

    @Override
    public List<Station> getAllStations(){
        return this.gestionStation.getAllStations();
    }

    @Override
    public Reservation effectuerReservation(Usager usager, Station stationDepart, String dateDepart, Station stationArrivee, int nbPassagers) throws ParseException, PasDeNavetteAQuaiException, RevisionNavetteException, TrajetInexistantException, CapaciteNavetteInsuffisanteException, PasDeQuaiDispoException, StationInexistanteException, UsagerInexistantException, NbPassagersNonAutoriseException, ReservationInexistanteException, ReservationDejaExistanteException, AucuneReservationException {
        return this.gestionReservation.effectuerReservation(dateDepart, usager, stationDepart, stationArrivee, nbPassagers);
    }

    @Override
    public String cloturerReservation(Long idUtilisateur, Long idReservation) throws TrajetDejaAcheveException, TrajetInexistantException, UsagerInexistantException, AucuneReservationException {
        if(this.gestionReservation.cloturerReservation(idUtilisateur, idReservation))
            return "Votre r??servation a ??t?? cl??tur??e.";
        return "Votre r??servation n'a pas pas pu ??tre cl??tur??e.";
    }

    @Override
    public String annulerReservation(Usager usager, Long idReservation) throws ParseException, TrajetDejaAcheveException, TrajetInexistantException, DelaiAnnulationResDepasseException, UsagerInexistantException, AucuneReservationException {
        if (this.gestionReservation.annulerReservation(usager, idReservation))
            return "Votre r??servation a ??t?? annul??e";
        return "Votre r??servation n'a pas pas pu ??tre annul??e.";
    }

    @Override
    public Usager getUsager(Long idUsager) {
        return this.gestionUsager.getUsager(idUsager);
    }

    @Override
    public Station getStation(Long idStation) {
        return this.gestionStation.getStation(idStation);
    }

}
