/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Valentin
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Reservation.controlerUtilisateur", 
            query="SELECT r FROM Reservation r WHERE r.usager = :id")
})
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private int nbPassagers;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateDepart;
    
    @OneToOne
    public Navette navette;
    @OneToOne
    public Usager usager;
    @OneToOne
    public Station stationDepart;
    @OneToOne
    public Station stationArrivee;
    @OneToOne
    public Quai quaiDepart;
    @OneToOne
    public Quai quaiArrivee;
    
    public Reservation(){
        
    }

    public Reservation(int id, int nbPassagers, Date dateDepart, Navette navette, Usager usager, Station stationDepart, Station stationArrivee, Quai quaiDepart, Quai quaiArrivee) {
        this.id = id;
        this.nbPassagers = nbPassagers;
        this.dateDepart = dateDepart;
        this.navette = navette;
        this.usager = usager;
        this.stationDepart = stationDepart;
        this.stationArrivee = stationArrivee;
        this.quaiDepart = quaiDepart;
        this.quaiArrivee = quaiArrivee;
    }
    
    public Navette getNavette() {
        return navette;
    }

    public void setNavette(Navette navette) {
        this.navette = navette;
    }

    public Usager getUsager() {
        return usager;
    }

    public void setUsager(Usager usager) {
        this.usager = usager;
    }

    public Station getStationDepart() {
        return stationDepart;
    }

    public void setStationDepart(Station stationDepart) {
        this.stationDepart = stationDepart;
    }

    public Station getStationArrivee() {
        return stationArrivee;
    }

    public void setStationArrivee(Station stationArrivee) {
        this.stationArrivee = stationArrivee;
    }

    public Quai getQuaiDepart() {
        return quaiDepart;
    }

    public void setQuaiDepart(Quai quaiDepart) {
        this.quaiDepart = quaiDepart;
    }

    public Quai getQuaiArrivee() {
        return quaiArrivee;
    }

    public void setQuaiArrivee(Quai quaiArrivee) {
        this.quaiArrivee = quaiArrivee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "fr.miage.m1.entities.Reservation[ id=" + id + " ]";
    }

    public int getNbPassagers() {
        return nbPassagers;
    }

    public void setNbPassagers(int nbPassagers) {
        this.nbPassagers = nbPassagers;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

}
