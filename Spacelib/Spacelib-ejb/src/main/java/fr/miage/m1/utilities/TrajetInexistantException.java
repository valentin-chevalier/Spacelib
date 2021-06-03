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
public class TrajetInexistantException extends Exception{
    
    public TrajetInexistantException(){
        super("Le trajet fourni ne correspond à aucun trajet répertorié en bd.");
    }
}
