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
public class MailInexistantException extends Exception{
    
    public MailInexistantException(){
        super("Le couple mail/mdp ne correspond à aucun usager enregistré.");
    }
}
