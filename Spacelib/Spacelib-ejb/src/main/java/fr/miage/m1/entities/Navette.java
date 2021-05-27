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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Valentin
 */
@Entity
public class Navette implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean estEnRevision;
    private int nbVoyages;
    private int capacite;
    private int idQUai;
    @OneToMany(mappedBy = "navette")
    private ArrayList<Operation> listeOperations;
    
    public Navette() {
    }

    public Navette(Long id, boolean estEnRevision, int nbVoyages, int capacite, ArrayList<Operation> listeOperations, int idQUai) {
        this.id = id;
        this.estEnRevision = estEnRevision;
        this.nbVoyages = nbVoyages;
        this.capacite = capacite;
        this.listeOperations = listeOperations;
        this.idQUai = idQUai;
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
        if (!(object instanceof Navette)) {
            return false;
        }
        Navette other = (Navette) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.miage.m1.entities.Navette[ id=" + id + " ]";
    }

    public boolean isEstEnRevision() {
        return estEnRevision;
    }

    public void setEstEnRevision(boolean estEnRevision) {
        this.estEnRevision = estEnRevision;
    }

    public int getNbVoyages() {
        return nbVoyages;
    }

    public void setNbVoyages(int nbVoyages) {
        this.nbVoyages = nbVoyages;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public ArrayList<Operation> getListeOperations() {
        return listeOperations;
    }

    public void setListeOperations(ArrayList<Operation> listeOperations) {
        this.listeOperations = listeOperations;
    }

    public int getIdQUai() {
        return idQUai;
    }

    public void setIdQUai(int idQUai) {
        this.idQUai = idQUai;
    }
    
}
