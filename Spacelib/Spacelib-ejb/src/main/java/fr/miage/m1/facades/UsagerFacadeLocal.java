/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.facades;

import fr.miage.m1.entities.Usager;
import fr.miage.m1.utilities.MailUsagerDejaExistantException;
import fr.miage.m1.utilities.MailInexistantException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Valentin
 */
@Local
public interface UsagerFacadeLocal {

    void create(Usager usager);

    void edit(Usager usager);

    void remove(Usager usager);

    Usager find(Object id);

    List<Usager> findAll();

    List<Usager> findRange(int[] range);

    int count();
    
    public Usager creerUsager(String prenom, String nom, String mail, String mdp) throws MailUsagerDejaExistantException;

    public Usager getUsager(Long idUsager);
 
    public Usager verifierUsagerDansBd(String mail, String mdp) throws MailInexistantException;
    
    public boolean verifierUsagerExiste(Long idUsager) throws MailInexistantException;
}
