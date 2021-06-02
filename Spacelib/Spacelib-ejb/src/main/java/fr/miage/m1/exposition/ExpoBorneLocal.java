/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.exposition;

import fr.miage.m1.entities.Station;
import fr.miage.m1.entities.Trajet;
import fr.miage.m1.entities.Usager;
import fr.miage.m1.utilities.MailUsagerDejaExistantException;
import fr.miage.m1.utilities.UsagerInexistantException;
import javax.ejb.Local;

/**
 *
 * @author Flo
 */
@Local
public interface ExpoBorneLocal {
    
    public Usager creerUsager(String prenom, String nom, String mail, String mdp) throws MailUsagerDejaExistantException;
    
    public Usager getUsager(Long idUsager);
    
    public Trajet getTrajet(Long idTrajet);
    
    public Station getStation(Long idStation);
    
    public Usager verifierUsagerDansBd(String mail, String mdp) throws UsagerInexistantException;

}
