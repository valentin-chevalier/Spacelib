/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.Reparation;
import fr.miage.m1.facades.ReparationFacadeLocal;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Flo
 */
@Stateless
public class GestionReparation implements GestionReparationLocal {

    @EJB
    private ReparationFacadeLocal reparationFacade;

    @Override
    public Reparation creerReparation(Date dateCreationOperation) {
        return this.reparationFacade.creerReparation(dateCreationOperation);
    }

    @Override
    public Reparation getReparation(Long idReparation) {
        return this.reparationFacade.getReparation(idReparation);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
