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
import fr.miage.m1.utilities.AucuneReservationException;
import fr.miage.m1.utilities.ReservationInexistanteException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Valentin
 */
@Stateless
public class ReservationFacade extends AbstractFacade<Reservation> implements ReservationFacadeLocal {

    @EJB
    private StationFacadeLocal stationFacade;

    @EJB
    private TrajetFacadeLocal trajetFacade;

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
    public Reservation creerReservation(int nbPassagers, Date dateDepart, Date dateArrivee, Navette navette, Usager usager, Station stationDepart, Station stationArrivee, Quai quaiDepart, Quai quaiArrivee) {
        Reservation reservation = new Reservation();
        reservation.setDateDepart(dateDepart);
        reservation.setDateArrivee(dateArrivee);
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
    
    @Override
    public Reservation controlerReservation(Long idUtilisateur) throws ReservationInexistanteException, AucuneReservationException{
        /*
        Query q = this.em.createNamedQuery("Reservation.controlerUtilisateur");
        q.setParameter("vid", idUtilisateur);
        if (q.getResultList().isEmpty())
            throw new ReservationInexistanteException();
        return (Reservation) q.getSingleResult();
        */
        for (Reservation res : this.findAll()){
            if (idUtilisateur.equals(res.getUsager().getId())){
                return res;
            } else {
                throw new ReservationInexistanteException();
            }
        } 
        throw new AucuneReservationException();
    }
    
    @Override
    public boolean reservationExiste(Long idUtilisateur){
        for (Reservation res : this.findAll()){
            if (idUtilisateur.equals(res.getUsager().getId())){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public List<Reservation> getReservationByStationDepart (Long idStation){
        Station station = this.stationFacade.find(idStation);
        Query q = this.em.createNamedQuery("Reservation.getReservationByStation");
        q.setParameter("vstationDepart", station);
        return q.getResultList();
    }
    
    @Override
    public List<Reservation> getReservationByStationArrivee (Long idStation){
        Station station = this.stationFacade.find(idStation);
        Query q = this.em.createNamedQuery("Reservation.getReservationsByStationArrivee");
        q.setParameter("vstationArrivee", station);
        return q.getResultList();
    }
}
