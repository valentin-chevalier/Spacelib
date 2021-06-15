/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.facades;

import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Quai;
import fr.miage.m1.entities.Reservation;
import fr.miage.m1.entities.Station;
import fr.miage.m1.entities.Usager;
import fr.miage.m1.spacelibshared.utilities.AucuneReservationException;
import fr.miage.m1.spacelibshared.utilities.ReservationInexistanteException;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Valentin
 */
@Local
public interface ReservationFacadeLocal {

    void create(Reservation reservation);

    void edit(Reservation reservation);

    void remove(Reservation reservation);

    Reservation find(Object id);

    List<Reservation> findAll();

    List<Reservation> findRange(int[] range);

    int count();
    
    public Reservation creerReservation(int nbPassagers, Date dateDepart, Date dateArrivee, Navette navette, Usager usager, Station stationDepart, Station stationArrivee, Quai quaiDepart, Quai quaiArrivee);
    
    public Reservation getReservation (Long idReservation);
    
    public Reservation controlerReservation(Long idUtilisateur) throws ReservationInexistanteException, AucuneReservationException;
    
    public boolean reservationExiste(Long idUtilisateur);
    
    public List<Reservation> getReservationByStationDepart (Long idStation);
    
    public List<Reservation> getReservationByStationArrivee (Long idStation);
}
