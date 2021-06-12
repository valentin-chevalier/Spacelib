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
public class ChargeQuai {

    private int nbQuaisLibres;
    private int nbQuaisTotal;
    
    public ChargeQuai(int nbQuaisLibres, int nbQuaisTotal){
        this.nbQuaisLibres = nbQuaisLibres;
        this.nbQuaisTotal = nbQuaisTotal;
    }

    public ChargeQuai() {
    }

    public void incrementerNbQuaisLibres(){
        nbQuaisLibres++;
    }
    
    public void incrementerNbQuaisTotal(){
        nbQuaisTotal++;
    }
    
    public void decrementerNbQuaisLibres(){
        nbQuaisLibres--;
    }
    
    public void decrementerNbQuaisTotal(){
        nbQuaisTotal--;
    }
    
    public float calculerRatio(){
        System.out.println("NB QUAIS LIBRES : " + nbQuaisLibres);
        System.out.println("NB QUAIS TOTAL : " + nbQuaisTotal);
        return (float)nbQuaisLibres/(float)nbQuaisTotal;
    }
    
    
}
