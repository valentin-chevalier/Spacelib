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
public class MailUsagerDejaExistantException extends Exception{
    
    public MailUsagerDejaExistantException(){
        super("Le mail fourni est déjà utilisé par un autre usager.");
    }
}
