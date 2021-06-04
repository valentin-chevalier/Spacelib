/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.facades;

import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Operation;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Valentin
 */
@Local
public interface OperationFacadeLocal {

    void create(Operation operation);

    void edit(Operation operation);

    void remove(Operation operation);

    Operation find(Object id);

    List<Operation> findAll();

    List<Operation> findRange(int[] range);

    int count();
    
    public Operation creerOperation(Date dateOperation, Navette navette);
    
    public Operation getOperation (Long idOperation);
    
    public Operation creerOperationMaintenance(Date dateOperation, Navette navette, Operation.EtatRevision etatRevision);
    
}
