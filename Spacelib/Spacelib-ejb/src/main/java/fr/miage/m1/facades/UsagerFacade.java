/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.facades;

import fr.miage.m1.entities.Usager;
import fr.miage.m1.spacelibshared.utilities.MailUsagerDejaExistantException;
import fr.miage.m1.spacelibshared.utilities.MailInexistantException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Valentin
 */
@Stateless
public class UsagerFacade extends AbstractFacade<Usager> implements UsagerFacadeLocal {

    @PersistenceContext(unitName = "spaceLibPersistanceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsagerFacade() {
        super(Usager.class);
    }

    @Override
    public Usager creerUsager(String prenom, String nom, String mail, String mdp) throws MailUsagerDejaExistantException {
        //récupérer le mail donné en param
        Query q = this.em.createNamedQuery("Usager.getMail");
        q.setParameter("vmail", mail);
        //si mail déjà existant dans la bd
        if (!q.getResultList().isEmpty())
            //lever une exception pour usager déjà existant
            throw new MailUsagerDejaExistantException();
        Usager user = new Usager();
        user.setMail(mail);
        user.setMdp(mdp);
        user.setNom(nom);
        user.setPrenom(prenom);
        this.create(user);
        return user;
    }

    @Override
    public Usager getUsager(Long idUsager) {
        return this.find(idUsager);
    }
    
    @Override
    public Usager verifierUsagerDansBd(String mail, String mdp) throws MailInexistantException{
        Query q = this.em.createNamedQuery("Usager.verifierDansBd");
        q.setParameter("vmail", mail);
        q.setParameter("vmdp", mdp);
        //si pas de résultat dans la bd
        if(q.getResultList().isEmpty())
            //lever exception pour usager inexistant
            throw new MailInexistantException();
        return (Usager)q.getSingleResult();
    }

    @Override
    public boolean verifierUsagerExiste(Long idUsager) throws MailInexistantException {
        Usager usager = this.find(idUsager);
        if (usager == null)
            throw new MailInexistantException();
        return true;
    }
}
