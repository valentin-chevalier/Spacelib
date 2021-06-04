/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.EtatTrajet;
import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Quai;
import fr.miage.m1.entities.Reservation;
import fr.miage.m1.entities.Station;
import fr.miage.m1.entities.Trajet;
import fr.miage.m1.entities.Usager;
import fr.miage.m1.facades.NavetteFacadeLocal;
import fr.miage.m1.facades.OperationFacadeLocal;
import fr.miage.m1.facades.QuaiFacadeLocal;
import fr.miage.m1.facades.ReservationFacadeLocal;
import fr.miage.m1.facades.StationFacadeLocal;
import fr.miage.m1.facades.TrajetFacadeLocal;
import fr.miage.m1.facades.UsagerFacadeLocal;
import fr.miage.m1.utilities.AucuneReservationException;
import fr.miage.m1.utilities.CapaciteNavetteInsuffisanteException;
import fr.miage.m1.utilities.NbPassagersNonAutoriseException;
import fr.miage.m1.utilities.PasDeNavetteAQuaiException;
import fr.miage.m1.utilities.PasDeQuaiDispoException;
import fr.miage.m1.utilities.ReservationDejaExistanteException;
import fr.miage.m1.utilities.ReservationInexistanteException;
import fr.miage.m1.utilities.RevisionNavetteException;
import fr.miage.m1.utilities.StationInexistanteException;
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
public class GestionReservation implements GestionReservationLocal {

    @EJB
    private NavetteFacadeLocal navetteFacade;

    @EJB
    private GestionTrajetLocal gestionTrajet;

    @EJB
    private StationFacadeLocal stationFacade;

    @EJB
    private UsagerFacadeLocal usagerFacade;

    @EJB
    private OperationFacadeLocal operationFacade;

    @EJB
    private TrajetFacadeLocal trajetFacade;

    @EJB
    private QuaiFacadeLocal quaiFacade;

    @EJB
    private ReservationFacadeLocal reservationFacade;

    @Override
    public Reservation creerReservation(int nbPassagers, Date dateDepart, Navette navette, Usager usager, Station stationDepart, Station stationArrivee, Quai quaiDepart, Quai quaiArrivee) {
        return this.reservationFacade.creerReservation(nbPassagers, dateDepart, navette, usager, stationDepart, stationArrivee, quaiDepart, quaiArrivee);
    }

    @Override
    public Reservation getReservation(Long idReservation) {
        return this.reservationFacade.getReservation(idReservation);
    }

    @Override
    public Reservation effectuerReservation (Date dateDepart, Usager usager, Station stationDepart, Station stationArrivee, int nbPassagers) throws PasDeNavetteAQuaiException, RevisionNavetteException, TrajetInexistantException, CapaciteNavetteInsuffisanteException, PasDeQuaiDispoException, StationInexistanteException, UsagerInexistantException, NbPassagersNonAutoriseException, ReservationInexistanteException, ReservationDejaExistanteException, AucuneReservationException{
        //controle des params fourni
        if (nbPassagers <= 0 || nbPassagers > 15)
            throw new NbPassagersNonAutoriseException();
        if (usager == null || this.usagerFacade.find(usager.getId()) == null)
            throw new UsagerInexistantException();
        if (stationDepart == null || this.stationFacade.find(stationDepart.getId())==null)
            throw new StationInexistanteException("DEPART");
        if (stationArrivee == null || this.stationFacade.find(stationArrivee.getId())==null)
            throw new StationInexistanteException("ARRIVEE");
        if (this.reservationExiste(usager.getId()) && EtatTrajet.VOYAGE_INITIE.equals(this.gestionTrajet.recupererTrajet(usager.getId()).getEtatTrajet()))
            throw new ReservationDejaExistanteException();
        if (stationDepart.getListeNavettes().isEmpty())
            throw new PasDeNavetteAQuaiException();
        Reservation res = null;
        int n = 0;
        //vérifier qu'un quai soit dispo
        if (!this.quaiFacade.getQuaisDispo(stationDepart.getId()).isEmpty() 
            && !this.quaiFacade.getQuaisDispo(stationArrivee.getId()).isEmpty()){
            //récupérer le premier résultat de la liste
            Quai quaiDepart = this.quaiFacade.getQuaisDispo(stationDepart.getId()).get(n);
            Quai quaiArrivee = this.quaiFacade.getQuaisDispo(stationArrivee.getId()).get(n);
            //pour chaque navette de la station
            for (Navette navette : stationDepart.getListeNavettes()){
                System.out.println("[navette] : " + navette);
                System.out.println("[nb voyages] : " + navette.getNbVoyages());
                //vérifier la capacité de la navette
                if (navette.isEstDispo() && !navette.isEstEnRevision() && navette.getCapacite() >= nbPassagers && navette.getNbVoyages() < 3){
                    System.out.println("HERE !!!!!!!!!! id " + navette.getId());
                    navette.incrementerNbVoyages();
                    this.navetteFacade.edit(navette);
                    System.out.println("HERE !!!!!!!!!! nbVoyages " + navette.getNbVoyages());
                    res = this.reservationFacade.creerReservation(nbPassagers, dateDepart, navette, usager, stationDepart, stationArrivee, quaiDepart, quaiArrivee);
                    Trajet t = this.trajetFacade.creerTrajet(nbPassagers, EtatTrajet.VOYAGE_INITIE, stationDepart, stationArrivee, quaiDepart, quaiArrivee, usager);
                    t.setDateDepart(new Date());
                    this.operationFacade.creerOperation(new Date(), navette);
                    return res;
                }
            }
            n++;
        } else {
            //affichage des erreurs
            if (this.quaiFacade.getQuaisDispo(stationDepart.getId()).isEmpty())
                throw new PasDeQuaiDispoException("DEPART");
            if (this.quaiFacade.getQuaisDispo(stationArrivee.getId()).isEmpty())
                throw new PasDeQuaiDispoException("ARRIVEE");
        }
        if (res == null){
            throw new PasDeNavetteAQuaiException();
        }
        return res;
    }

    @Override
    public Reservation controlerReservation(Long idUtilisateur) throws ReservationInexistanteException, AucuneReservationException{
        return this.reservationFacade.controlerReservation(idUtilisateur);
    }
    
    @Override
    public boolean reservationExiste(Long idUtilisateur){
        return this.reservationFacade.reservationExiste(idUtilisateur);
    }

}
