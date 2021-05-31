/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.facades;

import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Operation;
import fr.miage.m1.entities.Quai;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Valentin
 */
@Stateless
public class NavetteFacade extends AbstractFacade<Navette> implements NavetteFacadeLocal {

    @PersistenceContext(unitName = "spaceLibPersistanceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NavetteFacade() {
        super(Navette.class);
    }
    
    @Override
    public Navette creerNavette(boolean estEnRevision, boolean estDispo, int nbVoyages, int capacite, Quai quai){
       Navette navette = new Navette();
       navette.setListeOperations(new ArrayList<Operation>());
       navette.setEstEnRevision(estEnRevision);
       navette.setEstDispo(estDispo);
       navette.setNbVoyages(nbVoyages);
       navette.setCapacite(capacite);
       navette.setQuai(quai);
       this.create(navette);
       return navette;
    }
    
    @Override
    public Navette getNavette(Long idNavette) {
        return this.find(idNavette);
    }
}
