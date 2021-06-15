/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.spacelibshared.utilities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Flo
 */
public class NavetteExport implements Serializable{
    
    private Long id;
    private boolean estDispo;
    private boolean estEnRevision;
    private int nbVoyages;
    private int capacite;
    public QuaiExport quai;
    private ArrayList<OperationExport> listeOperations;

    public NavetteExport() {
    }

    public NavetteExport(Long id, boolean estDispo, boolean estEnRevision, int nbVoyages, int capacite, QuaiExport quai, ArrayList<OperationExport> listeOperations) {
        this.id = id;
        this.estDispo = estDispo;
        this.estEnRevision = estEnRevision;
        this.nbVoyages = nbVoyages;
        this.capacite = capacite;
        this.quai = quai;
        this.listeOperations = listeOperations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEstDispo() {
        return estDispo;
    }

    public void setEstDispo(boolean estDispo) {
        this.estDispo = estDispo;
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

    public QuaiExport getQuai() {
        return quai;
    }

    public void setQuai(QuaiExport quai) {
        this.quai = quai;
    }

    public ArrayList<OperationExport> getListeOperations() {
        return listeOperations;
    }

    public void setListeOperations(ArrayList<OperationExport> listeOperations) {
        this.listeOperations = listeOperations;
    }
    
}
