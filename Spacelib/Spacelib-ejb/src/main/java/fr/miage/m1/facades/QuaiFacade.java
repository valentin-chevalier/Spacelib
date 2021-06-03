/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.facades;

import fr.miage.m1.entities.Quai;
import fr.miage.m1.entities.Station;
import fr.miage.m1.utilities.PasDeQuaiDispoException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        /*
        Query q = this.em.createNamedQuery("Quai.getAllQuais");
        q.setParameter("vid", idStation);
        return q.getResultList();
        */
        List<Quai> listeQuais = new ArrayList<Quai>();
        for (Quai quai : this.stationFacade.getStation(idStation).getListeQuais()){
            listeQuais.add(quai);
        }
        return listeQuais;
}
    
    @Override
    public List<Quai> getQuaisDispo(Long idStation) throws PasDeQuaiDispoException{
       /*
       Query q = this.em.createNamedQuery("Quai.getQuaisDispo");
       q.setParameter("vid", idStation);
       return q.getResultList();
       */
        List<Quai> listeQuais = new ArrayList<Quai>();
        for (Quai quai : this.stationFacade.getStation(idStation).getListeQuais()){
            if (quai.isEstLibre())
                listeQuais.add(quai);
        }
        return listeQuais;
    }
}
