/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Operation;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author Flo
 */
@Local
public interface GestionOperationLocal {
    
    public Operation creerOperation(Date dateOperation, Navette navette);
    
    public Operation getOperation (Long idOperation);
    
    public Operation creerOperationMaintenance(Navette navette, Operation.EtatRevision etatRevision);

}
