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
public class CapaciteNavetteException extends Exception{
    
    //private String messageErreur = "La navette insérée n'a pas une capacité autorisée.";
    
    public CapaciteNavetteException(){
        super();
    }
    
    public CapaciteNavetteException(String messageErreur){
        super(messageErreur);
    }
}
