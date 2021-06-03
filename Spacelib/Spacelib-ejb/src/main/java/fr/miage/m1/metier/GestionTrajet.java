/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.EtatTrajet;
import fr.miage.m1.entities.Quai;
import fr.miage.m1.entities.Reservation;
import fr.miage.m1.entities.Station;
import fr.miage.m1.entities.Trajet;
import fr.miage.m1.entities.Usager;
import fr.miage.m1.entities.Utilisateur;
import fr.miage.m1.facades.TrajetFacadeLocal;
import fr.miage.m1.utilities.AucuneReservationException;
import fr.miage.m1.utilities.ReservationInexistanteException;
import fr.miage.m1.utilities.RevisionNavetteException;
import fr.miage.m1.utilities.TrajetInexistantException;
import fr.miage.m1.utilities.UsagerInexistantException;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Flo
 */
@Stateless
public class GestionTrajet implements GestionTrajetLocal {

    @EJB
    private GestionNavetteLocal gestionNavette;

    @EJB
    private GestionOperationLocal gestionOperation;

    @EJB
    private GestionReservationLocal gestionReservation;

    @EJB
    private TrajetFacadeLocal trajetFacade;

    @Override
    public Trajet creerTrajet(int nbPassagers, EtatTrajet etatTrajet, Station stationDepart, Station stationArrivee, Quai quaiDepart, Quai quaiArrivee, Utilisateur utilisateur) {
        return this.trajetFacade.creerTrajet(nbPassagers, etatTrajet, stationDepart, stationArrivee, quaiDepart, quaiArrivee, utilisateur);
    }

    @Override
    public Trajet getTrajet(Long idTrajet) {
        return this.trajetFacade.getTrajet(idTrajet);
    }

    @Override
    public Trajet finaliserTrajet(Usager usager) throws TrajetInexistantException, UsagerInexistantException, RevisionNavetteException, ReservationInexistanteException, AucuneReservationException{
        if (usager == null)
            throw new UsagerInexistantException();
        Trajet trajet = this.trajetFacade.recupererTrajet(usager.getId());
        trajet.setEtatTrajet(EtatTrajet.VOYAGE_ACHEVE);
        trajet.setDateArrivee(new Date());
        Reservation res = this.gestionReservation.controlerReservation(usager.getId());
        this.gestionOperation.creerOperation(new Date(), res.getNavette());
        this.gestionNavette.incrementerNbVoyages(res.getNavette());
        return trajet;
    }
}
