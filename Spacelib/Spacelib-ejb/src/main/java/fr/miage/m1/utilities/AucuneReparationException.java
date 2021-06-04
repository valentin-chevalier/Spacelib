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
public class AucuneReparationException extends Exception{
    
    public AucuneReparationException(){
        super("Il n'y a pas de réparation enregistrée en bd.");
    }
    
}
