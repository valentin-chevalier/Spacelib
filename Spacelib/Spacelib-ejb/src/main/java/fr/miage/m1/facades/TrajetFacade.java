/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.facades;

import fr.miage.m1.entities.EtatTrajet;
import fr.miage.m1.entities.Quai;
import fr.miage.m1.entities.Station;
import fr.miage.m1.entities.Trajet;
import fr.miage.m1.entities.Usager;
import fr.miage.m1.entities.Utilisateur;
import fr.miage.m1.spacelibshared.utilities.TrajetInexistantException;
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
public class TrajetFacade extends AbstractFacade<Trajet> implements TrajetFacadeLocal {

    @EJB
    private UsagerFacadeLocal usagerFacade;

    @PersistenceContext(unitName = "spaceLibPersistanceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrajetFacade() {
        super(Trajet.class);
    }

    @Override
    public Trajet creerTrajet(int nbPassagers, EtatTrajet etatTrajet, Station stationDepart, Station stationArrivee, Quai quaiDepart, Quai quaiArrivee, Utilisateur utilisateur) {
        Trajet trajet = new Trajet();
        trajet.setEtatTrajet(etatTrajet);
        trajet.setNbPassagers(nbPassagers);
        trajet.setQuaiArrivee(quaiArrivee);
        trajet.setQuaiDepart(quaiDepart);
        trajet.setStationArrivee(stationArrivee);
        trajet.setStationDepart(stationDepart);
        trajet.setUtilisateur(utilisateur);
        this.create(trajet);
        return trajet;
    }

    @Override
    public Trajet getTrajet(Long idTrajet) {
        return this.find(idTrajet);
    }
    
    @Override
    public Trajet recupererTrajet(Long idUtilisateur) throws TrajetInexistantException{
        Usager usager = this.usagerFacade.find(idUtilisateur);
        Query q = this.em.createNamedQuery("Trajet.getTrajet");
        q.setParameter("vid", usager);
        List<Trajet> listeTrajets = (List<Trajet>)q.getResultList();
        if (listeTrajets.isEmpty())
            throw new TrajetInexistantException();
        //récupérer le dernier résultat (dernier trajet inséré)
        if (idUtilisateur.equals(listeTrajets.get(listeTrajets.size()-1).getUtilisateur().getId()))
            return listeTrajets.get(listeTrajets.size()-1);
        throw new TrajetInexistantException();
    }
}
