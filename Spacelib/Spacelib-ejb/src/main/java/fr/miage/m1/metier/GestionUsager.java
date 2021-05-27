/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.Usager;
import fr.miage.m1.facades.UsagerFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Flo
 */
@Stateless
public class GestionUsager implements GestionUsagerLocal {

    @EJB
    private UsagerFacadeLocal usagerFacade;

    @Override
    public Usager creerUsager(String prenom, String nom, String mail, String mdp) {
        return this.usagerFacade.creerUsager(prenom, nom, mail, mdp);
    }

    @Override
    public Usager getUsager(Long idUsager) {
        return this.usagerFacade.getUsager(idUsager);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
