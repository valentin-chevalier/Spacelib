/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.facades;

import fr.miage.m1.entities.Duree;
import fr.miage.m1.entities.Station;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Valentin
 */
@Stateless
public class DureeFacade extends AbstractFacade<Duree> implements DureeFacadeLocal {

    @PersistenceContext(unitName = "spaceLibPersistanceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DureeFacade() {
        super(Duree.class);
    }

    @Override
    public Duree creerDuree(int duree, Station station1, Station station2) {
        Duree objDuree = new Duree();
        objDuree.setDuree(duree);
        objDuree.setStation1(station1);
        objDuree.setStation2(station2);
        this.create(objDuree);
        return objDuree;
    }

    @Override
    public Duree getDuree(Long idDuree) {
        return this.find(idDuree);
    }
    
    @Override
    public Long calculerDuree(Station station1, Station station2){
        Query q = this.em.createNamedQuery("Duree.recupererDuree");
        q.setParameter("vstation1", station1);
        q.setParameter("vstation2", station2);
        return (Long)q.getSingleResult();
    }
    
}
