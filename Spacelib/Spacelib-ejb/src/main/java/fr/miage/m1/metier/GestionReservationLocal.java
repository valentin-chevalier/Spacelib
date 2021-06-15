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
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Flo
 */
@Local
public interface GestionReservationLocal {
    
    public Reservation creerReservation (int nbPassagers, Date dateDepart, Date dateArrivee, Navette navette, Usager usager, Station stationDepart, Station stationArrivee, Quai quaiDepart, Quai quaiArrivee);
    
    public Reservation getReservation (Long idReservation);
    
    public Reservation effectuerReservation (String dateDepart, Usager usager, Station stationDepart, Station stationArrivee, int nbPassagers) throws ParseException, PasDeNavetteAQuaiException, RevisionNavetteException, TrajetInexistantException, CapaciteNavetteInsuffisanteException, PasDeQuaiDispoException, StationInexistanteException, UsagerInexistantException, NbPassagersNonAutoriseException, ReservationInexistanteException, ReservationDejaExistanteException, AucuneReservationException;

    public Reservation controlerReservation(Long idUtilisateur) throws ReservationInexistanteException, AucuneReservationException;

    public boolean reservationExiste(Long idUtilisateur);
    
    public boolean cloturerReservation(Long idUtilisateur, Long idReservation) throws TrajetDejaAcheveException, TrajetInexistantException, UsagerInexistantException, AucuneReservationException;
    
    public boolean annulerReservation(Usager usager, Long idReservation) throws ParseException, TrajetDejaAcheveException, TrajetInexistantException, DelaiAnnulationResDepasseException, UsagerInexistantException, AucuneReservationException;
    
    public void supprimerReservationsNonCloturees();
    
    public List<Reservation> getAllReservations();
}
