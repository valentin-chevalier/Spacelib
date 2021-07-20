/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Valentin
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Mecanicien.verifierDansBd", query="SELECT m FROM Mecanicien m WHERE m.mail = :vmail AND m.mdp = :vmdp"),
    @NamedQuery(name="Mecanicien.getMail", query="SELECT m FROM Mecanicien m WHERE m.mail = :vmail")
})
public class Mecanicien extends Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "mecanicien")
    private ArrayList<Reparation> listeReparations;

    public Mecanicien() {
    }
    
    public Mecanicien(Long id, String prenom, String nom, String mail, String mdp){
        super(id, prenom, nom, mail, mdp);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArrayList<Reparation> getListeReparations() {
        return listeReparations;
    }

    public void setListeReparations(ArrayList<Reparation> listeReparations) {
        this.listeReparations = listeReparations;
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
        if (!(object instanceof Mecanicien)) {
            return false;
        }
        Mecanicien other = (Mecanicien) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.miage.m1.entities.Mecanicien[ id=" + id + " ]";
    }
    
}
