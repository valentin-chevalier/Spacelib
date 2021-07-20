/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.spacelibshared.utilities;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Flo
 */
public class TrajetExport implements Serializable{
        
    private Long id;
    private int nbPassagers;
    private EtatTrajetExport etatTrajet;
    private Date dateDepart;
    private Date dateArrivee;
    public StationExport stationDepart;
    public StationExport stationArrivee;
    public QuaiExport quaiDepart;
    public QuaiExport quaiArrivee;
    public UtilisateurExport utilisateur;

    public TrajetExport() {
    }

    public TrajetExport(Long id, int nbPassagers, EtatTrajetExport etatTrajet, Date dateDepart, Date dateArrivee, StationExport stationDepart, StationExport stationArrivee, QuaiExport quaiDepart, QuaiExport quaiArrivee, UtilisateurExport utilisateur) {
        this.id = id;
        this.nbPassagers = nbPassagers;
        this.etatTrajet = etatTrajet;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
        this.stationDepart = stationDepart;
        this.stationArrivee = stationArrivee;
        this.quaiDepart = quaiDepart;
        this.quaiArrivee = quaiArrivee;
        this.utilisateur = utilisateur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNbPassagers() {
        return nbPassagers;
    }

    public void setNbPassagers(int nbPassagers) {
        this.nbPassagers = nbPassagers;
    }

    public EtatTrajetExport getEtatTrajet() {
        return etatTrajet;
    }

    public void setEtatTrajet(EtatTrajetExport etatTrajet) {
        this.etatTrajet = etatTrajet;
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

    public StationExport getStationDepart() {
        return stationDepart;
    }

    public void setStationDepart(StationExport stationDepart) {
        this.stationDepart = stationDepart;
    }

    public StationExport getStationArrivee() {
        return stationArrivee;
    }

    public void setStationArrivee(StationExport stationArrivee) {
        this.stationArrivee = stationArrivee;
    }

    public QuaiExport getQuaiDepart() {
        return quaiDepart;
    }

    public void setQuaiDepart(QuaiExport quaiDepart) {
        this.quaiDepart = quaiDepart;
    }

    public QuaiExport getQuaiArrivee() {
        return quaiArrivee;
    }

    public void setQuaiArrivee(QuaiExport quaiArrivee) {
        this.quaiArrivee = quaiArrivee;
    }

    public UtilisateurExport getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(UtilisateurExport utilisateur) {
        this.utilisateur = utilisateur;
    }
    
    
}
