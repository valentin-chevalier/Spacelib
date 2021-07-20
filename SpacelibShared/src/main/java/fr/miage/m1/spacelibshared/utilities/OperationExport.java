/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.spacelibshared.utilities;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Flo
 */
public class OperationExport implements Serializable{
    
    private Long id;
    private Date dateOperation;
    private EtatRevisionExport etatRevision;
    private Date dateCreationOperation;
    public NavetteExport navette;

    public OperationExport() {
    }

    public OperationExport(Long id, Date dateOperation, EtatRevisionExport etatRevision, Date dateCreationOperation, NavetteExport navette) {
        this.id = id;
        this.dateOperation = dateOperation;
        this.etatRevision = etatRevision;
        this.dateCreationOperation = dateCreationOperation;
        this.navette = navette;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public EtatRevisionExport getEtatRevision() {
        return etatRevision;
    }

    public void setEtatRevision(EtatRevisionExport etatRevision) {
        this.etatRevision = etatRevision;
    }

    public Date getDateCreationOperation() {
        return dateCreationOperation;
    }

    public void setDateCreationOperation(Date dateCreationOperation) {
        this.dateCreationOperation = dateCreationOperation;
    }

    public NavetteExport getNavette() {
        return navette;
    }

    public void setNavette(NavetteExport navette) {
        this.navette = navette;
    }


}
