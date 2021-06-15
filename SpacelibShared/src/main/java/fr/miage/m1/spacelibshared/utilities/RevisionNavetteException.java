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
public class RevisionNavetteException extends Exception{
    
    public RevisionNavetteException(){
        super("Attention, la navette n'est plus disponible. Elle doit être révisée.");
    }
}
