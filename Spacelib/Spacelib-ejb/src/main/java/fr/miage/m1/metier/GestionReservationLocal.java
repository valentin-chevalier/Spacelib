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
import fr.miage.m1.utilities.AucuneReservationException;
import fr.miage.m1.utilities.CapaciteNavetteInsuffisanteException;
import fr.miage.m1.utilities.NbPassagersNonAutoriseException;
import fr.miage.m1.utilities.PasDeQuaiDispoException;
import fr.miage.m1.utilities.ReservationDejaExistanteException;
import fr.miage.m1.utilities.ReservationInexistanteException;
import fr.miage.m1.utilities.StationInexistanteException;
import fr.miage.m1.utilities.TrajetInexistantException;
import fr.miage.m1.utilities.UsagerInexistantException;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author Flo
 */
@Local
public interface GestionReservationLocal {
    
    public Reservation creerReservation (int nbPassagers, Date dateDepart, Navette navette, Usager usager, Station stationDepart, Station stationArrivee, Quai quaiDepart, Quai quaiArrivee);
    
    public Reservation getReservation (Long idReservation);
    
    public Reservation effectuerReservation (Date dateDepart, Usager usager, Station stationDepart, Station stationArrivee, int nbPassagers) throws TrajetInexistantException, CapaciteNavetteInsuffisanteException, PasDeQuaiDispoException, StationInexistanteException, UsagerInexistantException, NbPassagersNonAutoriseException, ReservationInexistanteException, ReservationDejaExistanteException, AucuneReservationException;

    public Reservation controlerReservation(Long idUtilisateur) throws ReservationInexistanteException, AucuneReservationException;

    public boolean reservationExiste(Long idUtilisateur);
}
