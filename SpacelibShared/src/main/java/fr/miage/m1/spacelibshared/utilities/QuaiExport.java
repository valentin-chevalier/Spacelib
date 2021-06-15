/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.spacelibshared.utilities;

import java.io.Serializable;

/**
 *
 * @author Flo
 */
public class QuaiExport implements Serializable{
    
    private Long id;
    private int noQuai;
    private boolean estLibre;
    public StationExport station;

    public QuaiExport() {
    }

    public QuaiExport(Long id, int noQuai, boolean estLibre, StationExport station) {
        this.id = id;
        this.noQuai = noQuai;
        this.estLibre = estLibre;
        this.station = station;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNoQuai() {
        return noQuai;
    }

    public void setNoQuai(int noQuai) {
        this.noQuai = noQuai;
    }

    public boolean isEstLibre() {
        return estLibre;
    }

    public void setEstLibre(boolean estLibre) {
        this.estLibre = estLibre;
    }

    public StationExport getStation() {
        return station;
    }

    public void setStation(StationExport station) {
        this.station = station;
    }
    
    
}
