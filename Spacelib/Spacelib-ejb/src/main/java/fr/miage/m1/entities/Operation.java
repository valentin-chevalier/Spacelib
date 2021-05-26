/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Valentin
 */
@Entity
public class Operation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date dateOperation;
    private Long idNavette;

    @ManyToOne
    public Navette navette;

    public Operation() {
    }

    public Operation(Long id, Date dateOperation, Long idNavette, Navette navette) {
        this.id = id;
        this.dateOperation = dateOperation;
        this.idNavette = idNavette;
        this.navette = navette;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operation)) {
            return false;
        }
        Operation other = (Operation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.miage.m1.entities.Operation[ id=" + id + " ]";
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Long getIdNavette() {
        return idNavette;
    }

    public void setIdNavette(Long idNavette) {
        this.idNavette = idNavette;
    }

    public Navette getNavette() {
        return navette;
    }

    public void setNavette(Navette navette) {
        this.navette = navette;
    }
    
    
}
