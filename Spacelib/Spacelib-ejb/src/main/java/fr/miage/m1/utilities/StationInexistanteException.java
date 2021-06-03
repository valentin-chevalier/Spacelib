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
public class StationInexistanteException extends Exception{
    
    public StationInexistanteException(){
        super("La station indiquée n'existe pas.");
    }
    
    public StationInexistanteException(String messageErreur){
        super("["+ messageErreur + "] La station indiquée n'existe pas.");
    }
}