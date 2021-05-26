/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Valentin
 */
@Entity
public class Station implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nom;
    private String coordonnees;
    private ArrayList<Quai> listeQuais;
    private ArrayList<Navette> listeNavettes;

    public Station() {
    }

    public Station(Long id, String nom, String coordonnees, ArrayList<Quai> listeQuais, ArrayList<Navette> listeNavettes) {
        this.id = id;
        this.nom = nom;
        this.coordonnees = coordonnees;
        this.listeQuais = listeQuais;
        this.listeNavettes = listeNavettes;
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
        if (!(object instanceof Station)) {
            return false;
        }
        Station other = (Station) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.miage.m1.entities.Station[ id=" + id + " ]";
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(String coordonnees) {
        this.coordonnees = coordonnees;
    }

    public ArrayList<Quai> getListeQuais() {
        return listeQuais;
    }

    public void setListeQuais(ArrayList<Quai> listeQuais) {
        this.listeQuais = listeQuais;
    }

    public ArrayList<Navette> getListeNavettes() {
        return listeNavettes;
    }

    public void setListeNavettes(ArrayList<Navette> listeNavettes) {
        this.listeNavettes = listeNavettes;
    }
    
}
