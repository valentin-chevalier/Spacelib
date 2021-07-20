/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.spacelibshared.utilities;

import java.io.Serializable;

/**
 *
 * @author Flo
 */
public class UsagerExport extends UtilisateurExport implements Serializable{
    
    private Long id;

    public UsagerExport() {
    }
    
    public UsagerExport(Long id, String prenom, String nom, String mail, String mdp){
        super(id, prenom, nom, mail, mdp);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
