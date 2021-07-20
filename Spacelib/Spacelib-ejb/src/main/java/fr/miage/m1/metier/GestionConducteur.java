/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.Conducteur;
import fr.miage.m1.facades.ConducteurFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Flo
 */
@Stateless
public class GestionConducteur implements GestionConducteurLocal {

    @EJB
    private ConducteurFacadeLocal conducteurFacade;

    @Override
    public Conducteur creerConducteur(String prenom, String nom, String mail, String mdp) {
        return this.conducteurFacade.creerConducteur(prenom, nom, mail, mdp);
    }

    @Override
    public Conducteur getConducteur(Long idConducteur) {
        return this.conducteurFacade.getConducteur(idConducteur);
    }
}
