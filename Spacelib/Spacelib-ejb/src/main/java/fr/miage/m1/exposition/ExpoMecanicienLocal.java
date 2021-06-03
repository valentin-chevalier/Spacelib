/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.exposition;

import fr.miage.m1.entities.Mecanicien;
import fr.miage.m1.utilities.MailInexistantException;
import javax.ejb.Local;

/**
 *
 * @author Flo
 */
@Local
public interface ExpoMecanicienLocal {
    
    public Mecanicien creerMecanicien(String prenom, String nom, String mail, String mdp);

    public Mecanicien getMecanicien(Long idMecanicien);
    
    public Mecanicien verifierMecanicienDansBd (String mail, String mdp) throws MailInexistantException;
}
