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
public class TrajetDejaAcheveException extends Exception{
    
    public TrajetDejaAcheveException(){
        super("Le voyage indiqué est déjà achevé.");
    }
}
