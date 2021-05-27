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
import javax.persistence.OneToOne;

/**
 *
 * @author Valentin
 */
@Entity
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long nbPassagers;
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

    public Reservation(Long id, Long nbPassagers, Date dateDepart, Navette navette, Usager usager, Station stationDepart, Station stationArrivee, Quai quaiDepart, Quai quaiArrivee) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.miage.m1.entities.Reservation[ id=" + id + " ]";
    }

    public Long getNbPassagers() {
        return nbPassagers;
    }

    public void setNbPassagers(Long nbPassagers) {
        this.nbPassagers = nbPassagers;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

}
