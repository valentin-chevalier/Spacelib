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
public class NavetteSansQuaiException extends Exception{
    
    public NavetteSansQuaiException(){
        super("Le quai associé à la création de la navette n'existe pas.");
    }
}
