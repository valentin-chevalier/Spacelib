/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.Usager;
import javax.ejb.Local;

/**
 *
 * @author Flo
 */
@Local
public interface GestionUsagerLocal {
  
    public Usager creerUsager(String prenom, String nom, String mail, String mdp);

    public Usager getUsager(Long idUsager);
}
