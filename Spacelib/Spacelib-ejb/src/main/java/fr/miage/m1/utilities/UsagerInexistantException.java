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
public class UsagerInexistantException extends Exception{
    
    public UsagerInexistantException(){
        super("L'usager fourni n'existe pas dans la bd.");
    }
}