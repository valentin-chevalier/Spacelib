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
public class PasDeQuaiDispoException extends Exception{
    
    public PasDeQuaiDispoException(){
        super("Il n'y a pas de quai disponible.");
    }
    
    public PasDeQuaiDispoException(String quai){
        super("[" + quai +"] Il n'y a pas de quai disponible.");
    }
}
