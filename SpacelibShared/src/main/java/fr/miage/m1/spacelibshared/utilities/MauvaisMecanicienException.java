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
public class MauvaisMecanicienException extends Exception{
    
    public MauvaisMecanicienException(){
        super("Le mécanicien fourni n'est pas responsable de cette réparation.");
    }
    
}
