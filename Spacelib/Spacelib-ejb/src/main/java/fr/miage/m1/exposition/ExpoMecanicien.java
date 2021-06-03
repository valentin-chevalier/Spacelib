/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.exposition;

import fr.miage.m1.entities.Mecanicien;
import fr.miage.m1.metier.GestionMecanicienLocal;
import fr.miage.m1.utilities.MailInexistantException;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Flo
 */
@Stateless
public class ExpoMecanicien implements ExpoMecanicienLocal {

    @EJB
    private GestionMecanicienLocal gestionMecanicien;

    @Override
    public Mecanicien creerMecanicien(String prenom, String nom, String mail, String mdp) {
        return this.gestionMecanicien.creerMecanicien(prenom, nom, mail, mdp);
    }

    @Override
    public Mecanicien getMecanicien(Long idMecanicien) {
        return this.gestionMecanicien.getMecanicien(idMecanicien);
    }

    @Override
    public Mecanicien verifierMecanicienDansBd(String mail, String mdp) throws MailInexistantException {
        return this.gestionMecanicien.verifierMecanicienDansBd(mail, mdp);
    }

    
}