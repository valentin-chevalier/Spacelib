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
public class Trajet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date dateDepart;
    private Date dateArrivee;

    private Long idStationDepart;
    private Long idStationArrivee;
    private Long idQuaiDepart;
    private Long idQuaiArrivee;
    private int nbPassagers;
    private Long idUsager;
    private EtatTrajet etatTrajet;

    @OneToOne
    private Station stationDepart;
    @OneToOne
    private Station stationArrivee;
    @OneToOne
    private Quai quaiDepart;
    @OneToOne
    private Quai quaiArrivee;
    
    public Trajet() {
    }

    public Trajet(Long id, Date dateDepart, Date dateArrivee, Long idStationDepart, Long idStationArrivee, Long idQuaiDepart, Long idQuaiArrivee, int nbPassagers, Long idUsager, EtatTrajet etatTrajet) {
        this.id = id;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
        this.idStationDepart = idStationDepart;
        this.idStationArrivee = idStationArrivee;
        this.idQuaiDepart = idQuaiDepart;
        this.idQuaiArrivee = idQuaiArrivee;
        this.nbPassagers = nbPassagers;
        this.idUsager = idUsager;
        this.etatTrajet = etatTrajet;
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

    
    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Date getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(Date dateArrivee) {
        this.dateArrivee = dateArrivee;
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

    public int getNbPassagers() {
        return nbPassagers;
    }

    public void setNbPassagers(int nbPassagers) {
        this.nbPassagers = nbPassagers;
    }

    public Long getIdUsager() {
        return idUsager;
    }

    public void setIdUsager(Long idUsager) {
        this.idUsager = idUsager;
    }

    public EtatTrajet getEtatTrajet() {
        return etatTrajet;
    }

    public void setEtatTrajet(EtatTrajet etatTrajet) {
        this.etatTrajet = etatTrajet;
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
        if (!(object instanceof Trajet)) {
            return false;
        }
        Trajet other = (Trajet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.miage.m1.entities.Trajet[ id=" + id + " ]";
    }
    
}
