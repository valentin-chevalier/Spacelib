/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.entities;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Flo
 */
@Stateless
@LocalBean
public class ChargeNavette {

    private int nbNavettesLibres;
    private int nbNavettesTotal;
    
    public ChargeNavette(int nbNavettesLibres, int nbNavettesTotal){
        this.nbNavettesLibres = nbNavettesLibres;
        this.nbNavettesTotal = nbNavettesTotal;
    }
    
    public ChargeNavette(){
    }
    
    
    public void incrementerNbNavettesLibres(){
        nbNavettesLibres++;
    }
    
    public void incrementerNbNavettesTotal(){
        nbNavettesTotal++;
    }
    
    public void decrementerNbNavettesLibres(){
        nbNavettesLibres--;
    }
    
    public void decrementerNbNavettesTotal(){
        nbNavettesTotal--;
    }
    
    public float calculerRatio(){
        return (float)nbNavettesLibres/(float)nbNavettesTotal;
    }
}
