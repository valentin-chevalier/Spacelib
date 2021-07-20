/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.spacelibshared.utilities;

/**
 *
 * @author Flo
 */
public class PasDeReservationPourStationException extends Exception{
    
    public PasDeReservationPourStationException(){
        super("Il n'y a pas de réservation pour cette station.");
    }
    
}
