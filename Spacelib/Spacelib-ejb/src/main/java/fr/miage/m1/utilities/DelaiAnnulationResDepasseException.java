/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.utilities;

/**
 *
 * @author Flo
 */
public class DelaiAnnulationResDepasseException extends Exception{
    
    public DelaiAnnulationResDepasseException(){
        super("Il est trop tard pour annuler votre réservation.");
    }
    
}
