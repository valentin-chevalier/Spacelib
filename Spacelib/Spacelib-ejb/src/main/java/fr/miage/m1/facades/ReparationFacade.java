/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.facades;

import fr.miage.m1.entities.Mecanicien;
import fr.miage.m1.entities.Reparation;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Valentin
 */
@Stateless
public class ReparationFacade extends AbstractFacade<Reparation> implements ReparationFacadeLocal {

    @PersistenceContext(unitName = "spaceLibPersistanceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReparationFacade() {
        super(Reparation.class);
    }

    @Override
    public Reparation creerReparation(Date dateCreationOperation) {
        Reparation reparation = new Reparation();
        reparation.setDateCreationOperation(new Date());
        this.create(reparation);
        return reparation;
    }

    @Override
    public Reparation getReparation(Long idReparation) {
        return this.find(idReparation);
    }
    
}
