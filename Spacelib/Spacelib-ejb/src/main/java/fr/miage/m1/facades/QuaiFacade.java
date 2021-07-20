/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.facades;

import fr.miage.m1.entities.Quai;
import fr.miage.m1.entities.Station;
import fr.miage.m1.spacelibshared.utilities.PasDeQuaiDispoException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Valentin
 */
@Stateless
public class QuaiFacade extends AbstractFacade<Quai> implements QuaiFacadeLocal {

    @EJB
    private StationFacadeLocal stationFacade;

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
    
    @Override
    public List<Quai> getAllQuais(Long idStation)  throws PasDeQuaiDispoException {
        Station station = this.stationFacade.getStation(idStation);
        Query q = this.em.createNamedQuery("Quai.getAllQuais");
        q.setParameter("vstation", station);
        if (q.getResultList().isEmpty())
            throw new PasDeQuaiDispoException();
        return q.getResultList();
}
    
    @Override
    public List<Quai> getQuaisDispo(Long idStation) throws PasDeQuaiDispoException{
        Station station = this.stationFacade.getStation(idStation);
        Query q = this.em.createNamedQuery("Quai.getQuaisDispo");
        q.setParameter("vstation", station);
        if (q.getResultList().isEmpty())
            throw new PasDeQuaiDispoException();
        return q.getResultList();
    }
}
