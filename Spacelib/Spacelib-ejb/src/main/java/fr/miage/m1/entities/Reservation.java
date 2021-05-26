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
    
    private Long idNavette;
    private Long idUsager;
    private Long nbPassagers;
    private Date dateDepart;
    private Long idStationDepart;
    private Long idStationArrivee;
    private Long idQuaiDepart;
    private Long idQuaiArrivee;
    
    public Reservation(){
        
    }

    public Reservation(Long id, Long idNavette, Long idUsager, Long nbPassagers, Date dateDepart, Long idStationDepart, Long idStationArrivee, Long idQuaiDepart, Long idQuaiArrivee) {
        this.id = id;
        this.idNavette = idNavette;
        this.idUsager = idUsager;
        this.nbPassagers = nbPassagers;
        this.dateDepart = dateDepart;
        this.idStationDepart = idStationDepart;
        this.idStationArrivee = idStationArrivee;
        this.idQuaiDepart = idQuaiDepart;
        this.idQuaiArrivee = idQuaiArrivee;
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

    public Long getIdNavette() {
        return idNavette;
    }

    public void setIdNavette(Long idNavette) {
        this.idNavette = idNavette;
    }

    public Long getIdUsager() {
        return idUsager;
    }

    public void setIdUsager(Long idUsager) {
        this.idUsager = idUsager;
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

    public Long getIdStationDepart() {
        return idStationDepart;
    }

    public void setIdStationDepart(Long idStationDepart) {
        this.idStationDepart = idStationDepart;
    }

    public Long getIdStationArrivee() {
        return idStationArrivee;
    }

    public void setIdStationArrivee(Long idStationArrivee) {
        this.idStationArrivee = idStationArrivee;
    }

    public Long getIdQuaiDepart() {
        return idQuaiDepart;
    }

    public void setIdQuaiDepart(Long idQuaiDepart) {
        this.idQuaiDepart = idQuaiDepart;
    }

    public Long getIdQuaiArrivee() {
        return idQuaiArrivee;
    }

    public void setIdQuaiArrivee(Long idQuaiArrivee) {
        this.idQuaiArrivee = idQuaiArrivee;
    }
    
}
