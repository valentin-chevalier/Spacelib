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
public class PasDeNavetteAQuaiException extends Exception{
    
    public PasDeNavetteAQuaiException(){
        super("Il n'y a pas de navettes disponibles dans la station fournie.");
    }
}
