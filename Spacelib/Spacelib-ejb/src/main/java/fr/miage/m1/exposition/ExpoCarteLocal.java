/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.exposition;

import fr.miage.m1.entities.Reservation;
import fr.miage.m1.entities.Station;
import fr.miage.m1.entities.Usager;
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
import javax.ejb.Local;

/**
 *
 * @author Flo
 */
@Local
public interface ExpoCarteLocal {
    
    public List<Station> getAllStations();
    
    public Reservation effectuerReservation(Usager usager, Station stationDepart, String dateDepart, Station stationArrivee, int nbPassagers) throws ParseException, PasDeNavetteAQuaiException, RevisionNavetteException, TrajetInexistantException, CapaciteNavetteInsuffisanteException, PasDeQuaiDispoException, StationInexistanteException, UsagerInexistantException, NbPassagersNonAutoriseException, ReservationInexistanteException, ReservationDejaExistanteException, AucuneReservationException;
        
    public String cloturerReservation(Long idUtilisateur, Long idReservation) throws TrajetDejaAcheveException, TrajetInexistantException, UsagerInexistantException, AucuneReservationException;
    
    public String annulerReservation(Usager usager, Long idReservation) throws ParseException, TrajetDejaAcheveException, TrajetInexistantException, DelaiAnnulationResDepasseException, UsagerInexistantException, AucuneReservationException;

    public Usager getUsager(Long idUsager);
    
    public Station getStation(Long idStation);
}
