/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.Usager;
import fr.miage.m1.utilities.MailUsagerDejaExistantException;
import fr.miage.m1.utilities.MailInexistantException;
import javax.ejb.Local;

/**
 *
 * @author Flo
 */
@Local
public interface GestionUsagerLocal {
  
    public Usager creerUsager(String prenom, String nom, String mail, String mdp) throws MailUsagerDejaExistantException;

    public Usager getUsager(Long idUsager);
    
    public Usager verifierUsagerDansBd (String mail, String mdp) throws MailInexistantException;
    
    public boolean verifierUsagerExiste(Long idUsager) throws MailInexistantException;

}
