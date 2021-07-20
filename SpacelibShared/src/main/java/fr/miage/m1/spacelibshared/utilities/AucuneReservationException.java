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
public class AucuneReservationException extends Exception{
    
    public AucuneReservationException(){
        super("Il n'y a pas de réservation enregistrée en bd.");
    }
    
}
