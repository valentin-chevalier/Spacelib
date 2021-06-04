/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.facades;

import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Operation;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Valentin
 */
@Stateless
public class OperationFacade extends AbstractFacade<Operation> implements OperationFacadeLocal {

    @PersistenceContext(unitName = "spaceLibPersistanceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OperationFacade() {
        super(Operation.class);
    }

    @Override
    public Operation creerOperation(Date dateOperation, Navette navette) {
        Operation ope = new Operation();
        ope.setDateCreationOperation(new Date());
        ope.setDateOperation(dateOperation);
        ope.setNavette(navette);
        this.create(ope);
        return ope;
    }

    @Override
    public Operation getOperation(Long idOperation) {
        return this.find(idOperation);
    }
    
    @Override
    public Operation creerOperationMaintenance(Date dateOperation, Navette navette, Operation.EtatRevision etatRevision){
        Operation ope = new Operation();
        ope.setDateCreationOperation(new Date());
        ope.setDateOperation(dateOperation);
        ope.setNavette(navette);
        ope.setEtatRevision(etatRevision);
        this.create(ope);
        return ope;
    }
    
}
