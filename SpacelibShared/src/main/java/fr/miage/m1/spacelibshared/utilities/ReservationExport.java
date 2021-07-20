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
public class ReservationExport implements Serializable{
    
    private Long id;
    private int nbPassagers;
    private Date dateDepart;
    private Date dateArrivee;
    public NavetteExport navette;
    public UsagerExport usager;
    public StationExport stationDepart;
    public StationExport stationArrivee;
    public QuaiExport quaiDepart;
    public QuaiExport quaiArrivee;

    public ReservationExport() {
    }

    public ReservationExport(Long id, int nbPassagers, Date dateDepart, Date dateArrivee, NavetteExport navette, UsagerExport usager, StationExport stationDepart, StationExport stationArrivee, QuaiExport quaiDepart, QuaiExport quaiArrivee) {
        this.id = id;
        this.nbPassagers = nbPassagers;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
        this.navette = navette;
        this.usager = usager;
        this.stationDepart = stationDepart;
        this.stationArrivee = stationArrivee;
        this.quaiDepart = quaiDepart;
        this.quaiArrivee = quaiArrivee;
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

    public NavetteExport getNavette() {
        return navette;
    }

    public void setNavette(NavetteExport navette) {
        this.navette = navette;
    }

    public UsagerExport getUsager() {
        return usager;
    }

    public void setUsager(UsagerExport usager) {
        this.usager = usager;
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
    
    
}
