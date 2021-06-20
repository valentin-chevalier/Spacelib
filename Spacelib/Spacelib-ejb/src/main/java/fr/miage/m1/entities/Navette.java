/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.entities;

import fr.miage.m1.spacelibshared.utilities.RevisionNavetteException;
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
    private boolean estDispo;
    private boolean estEnRevision;
    private int nbVoyages;
    private int capacite;
    
    @OneToOne
    public Quai quai;
    
    @OneToMany(mappedBy = "navette")
    private ArrayList<Operation> listeOperations;
    
    public Navette() {
    }

    public Navette(Long id, boolean estEnRevision, boolean estDispo, int nbVoyages, int capacite, Quai quai) {
        this.id = id;
        this.estEnRevision = estEnRevision;
        this.estDispo = estDispo;
        this.nbVoyages = nbVoyages; 
        this.capacite = capacite;
        this.quai = quai;
        this.listeOperations = listeOperations;
    }

    public boolean isEstDispo() {
        return estDispo;
    }

    public void setEstDispo(boolean estDispo) {
        this.estDispo = estDispo;
    }

    public Quai getQuai() {
        return quai;
    }

    public void setQuai(Quai quai) {
        this.quai = quai;
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
    
    public void incrementerNbVoyages() throws RevisionNavetteException{
        if (this.nbVoyages >= 3){
            throw new RevisionNavetteException();
        }
        //System.out.println("Avant" + this.nbVoyages);
        this.nbVoyages++;
        //System.out.println("Apres" + this.nbVoyages);
        if (this.nbVoyages >= 3){
            //La navette passe en attente de révision
            this.estDispo = false;
            this.estEnRevision = false;
        }
    }
}