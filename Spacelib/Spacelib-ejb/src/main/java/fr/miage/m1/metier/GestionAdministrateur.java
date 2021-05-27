/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.Administrateur;
import fr.miage.m1.facades.AdministrateurFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Flo
 */
@Stateless
public class GestionAdministrateur implements GestionAdministrateurLocal {

    @EJB
    private AdministrateurFacadeLocal administrateurFacade;
    
    @Override
    public Administrateur creerAdmnistrateur(String prenom, String nom, String mail, String mdp) {
        return this.administrateurFacade.creerAdmnistrateur(prenom, nom, mail, mdp);
    }

    @Override
    public Administrateur getAdmnistrateur(Long idAdmin) {
        return this.administrateurFacade.getAdmnistrateur(idAdmin);
    }

    
}
