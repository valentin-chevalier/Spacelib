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
public class UtilisateurExport implements Serializable{
    
    private Long id;
    private String  prenom;
    private String nom;
    private String mail;
    private String mdp; 

    public UtilisateurExport() {
    }

    public UtilisateurExport(Long id, String prenom, String nom, String mail, String mdp) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.mail = mail;
        this.mdp = mdp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    
    

}
