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
public class StationExport implements Serializable{
    
    private Long id;
    private String nom;
    private String coordonnees;
    private ArrayList<QuaiExport> listeQuais;
    private ArrayList<NavetteExport> listeNavettes;
    private TrajetExport trajet1;
    private TrajetExport trajet2;

    public StationExport() {
    }

    public StationExport(Long id, String nom, String coordonnees, ArrayList<QuaiExport> listeQuais, ArrayList<NavetteExport> listeNavettes, TrajetExport trajet1, TrajetExport trajet2) {
        this.id = id;
        this.nom = nom;
        this.coordonnees = coordonnees;
        this.listeQuais = listeQuais;
        this.listeNavettes = listeNavettes;
        this.trajet1 = trajet1;
        this.trajet2 = trajet2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ArrayList<QuaiExport> getListeQuais() {
        return listeQuais;
    }

    public void setListeQuais(ArrayList<QuaiExport> listeQuais) {
        this.listeQuais = listeQuais;
    }

    public ArrayList<NavetteExport> getListeNavettes() {
        return listeNavettes;
    }

    public void setListeNavettes(ArrayList<NavetteExport> listeNavettes) {
        this.listeNavettes = listeNavettes;
    }

    public TrajetExport getTrajet1() {
        return trajet1;
    }

    public void setTrajet1(TrajetExport trajet1) {
        this.trajet1 = trajet1;
    }

    public TrajetExport getTrajet2() {
        return trajet2;
    }

    public void setTrajet2(TrajetExport trajet2) {
        this.trajet2 = trajet2;
    }


}
