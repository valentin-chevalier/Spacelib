/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.Usager;
import fr.miage.m1.facades.UsagerFacadeLocal;
import fr.miage.m1.utilities.MailUsagerDejaExistantException;
import fr.miage.m1.utilities.MailInexistantException;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Flo
 */
@Stateless
public class GestionUsager implements GestionUsagerLocal {

    @EJB
    private UsagerFacadeLocal usagerFacade;

    @Override
    public Usager creerUsager(String prenom, String nom, String mail, String mdp) throws MailUsagerDejaExistantException {
        return this.usagerFacade.creerUsager(prenom, nom, mail, mdp);
    }

    @Override
    public Usager getUsager(Long idUsager) {
        return this.usagerFacade.getUsager(idUsager);
    }

    @Override
    public Usager verifierUsagerDansBd(String mail, String mdp) throws MailInexistantException {
        return this.usagerFacade.verifierUsagerDansBd(mail, mdp);
    }

    @Override
    public boolean verifierUsagerExiste(Long idUsager) throws MailInexistantException {
        return this.usagerFacade.verifierUsagerExiste(idUsager);
    }
}
