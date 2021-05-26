/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.entities;

import java.io.Serializable;
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
public class Duree implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long idStation1;
    private Long idStation2;
    private int duree;

    @OneToOne
    public Station station1;
    @OneToOne
    public Station station2;
    
    public Duree() {
    }

    public Duree(Long id, Long idStation1, Long idStation2, int duree) {
        this.id = id;
        this.idStation1 = idStation1;
        this.idStation2 = idStation2;
        this.duree = duree;
    }

    public Long getIdStation1() {
        return idStation1;
    }

    public void setIdStation1(Long idStation1) {
        this.idStation1 = idStation1;
    }

    public Long getIdStation2() {
        return idStation2;
    }

    public void setIdStation2(Long idStation2) {
        this.idStation2 = idStation2;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Station getStation1() {
        return station1;
    }

    public void setStation1(Station station1) {
        this.station1 = station1;
    }

    public Station getStation2() {
        return station2;
    }

    public void setStation2(Station station2) {
        this.station2 = station2;
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
        if (!(object instanceof Duree)) {
            return false;
        }
        Duree other = (Duree) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.miage.m1.entities.Duree[ id=" + id + " ]";
    }
    
}
