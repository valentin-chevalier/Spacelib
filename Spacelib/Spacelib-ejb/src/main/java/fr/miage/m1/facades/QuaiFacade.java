/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.facades;

import fr.miage.m1.entities.Quai;
import fr.miage.m1.entities.Station;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Valentin
 */
@Stateless
public class QuaiFacade extends AbstractFacade<Quai> implements QuaiFacadeLocal {

    @PersistenceContext(unitName = "spaceLibPersistanceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public QuaiFacade() {
        super(Quai.class);
    }

    @Override
    public Quai creerQuai(int noQuai, boolean estLibre, Station station) {
        Quai quai = new Quai();
        quai.setNoQuai(noQuai);
        quai.setEstLibre(estLibre);
        quai.setStation(station);
        this.create(quai);
        return quai;
    }

    @Override
    public Quai getQuai(Long idQuai) {
        return this.find(idQuai);
    }
    
}
