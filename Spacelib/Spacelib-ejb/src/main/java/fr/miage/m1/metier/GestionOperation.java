/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Operation;
import fr.miage.m1.facades.OperationFacadeLocal;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Flo
 */
@Stateless
public class GestionOperation implements GestionOperationLocal {

    @EJB
    private OperationFacadeLocal operationFacade;

    @Override
    public Operation creerOperation(Date dateOperation, Navette navette) {
        return this.operationFacade.creerOperation(dateOperation, navette);
    }

    @Override
    public Operation getOperation(Long idOperation) {
        return this.operationFacade.getOperation(idOperation);
    }
    
    @Override
    public Operation creerOperationMaintenance(Navette navette, Operation.EtatRevision etatRevision){
        return this.operationFacade.creerOperationMaintenance(new Date(), navette, etatRevision);
    }

}
