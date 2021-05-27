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
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Valentin
 */
@Stateless
public class ReservationFacade extends AbstractFacade<Reservation> implements ReservationFacadeLocal {

    @PersistenceContext(unitName = "spaceLibPersistanceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservationFacade() {
        super(Reservation.class);
    }

    @Override
    public Reservation creerReservation(Long nbPassagers, Date dateDepart, Navette navette, Usager usager, Station stationDepart, Station stationArrivee, Quai quaiDepart, Quai quaiArrivee) {
        Reservation reservation = new Reservation();
        reservation.setDateDepart(dateDepart);
        reservation.setNavette(navette);
        reservation.setNbPassagers(nbPassagers);
        reservation.setQuaiArrivee(quaiArrivee);
        reservation.setQuaiDepart(quaiDepart);
        reservation.setStationArrivee(stationArrivee);
        reservation.setStationDepart(stationDepart);
        reservation.setUsager(usager);
        this.create(reservation);
        return reservation;
    }

    @Override
    public Reservation getReservation(Long idReservation) {
        return this.find(idReservation);
    }
    
}
