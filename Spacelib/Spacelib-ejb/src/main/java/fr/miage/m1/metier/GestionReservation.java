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
import fr.miage.m1.entities.Usager;
import fr.miage.m1.facades.OperationFacadeLocal;
import fr.miage.m1.facades.QuaiFacadeLocal;
import fr.miage.m1.facades.ReservationFacadeLocal;
import fr.miage.m1.facades.StationFacadeLocal;
import fr.miage.m1.facades.TrajetFacadeLocal;
import fr.miage.m1.facades.UsagerFacadeLocal;
import fr.miage.m1.utilities.CapaciteNavetteInsuffisanteException;
import fr.miage.m1.utilities.NbPassagersNonAutoriseException;
import fr.miage.m1.utilities.PasDeQuaiDispoException;
import fr.miage.m1.utilities.StationInexistanteException;
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
    private StationFacadeLocal stationFacade;

    @EJB
    private UsagerFacadeLocal usagerFacade;

    @EJB
    private GestionUsagerLocal gestionUsager;

    @EJB
    private GestionStationLocal gestionStation;

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
    public Reservation effectuerReservation (Date dateDepart, Usager usager, Station stationDepart, Station stationArrivee, int nbPassagers) throws CapaciteNavetteInsuffisanteException, PasDeQuaiDispoException, StationInexistanteException, UsagerInexistantException, NbPassagersNonAutoriseException{
        //controle des params fourni
        if (nbPassagers <= 0)
            throw new NbPassagersNonAutoriseException();
        if (usager == null || this.usagerFacade.find(usager.getId()) == null)
            throw new UsagerInexistantException();
        if (stationDepart == null || this.stationFacade.find(stationDepart.getId())==null)
            throw new StationInexistanteException("DEPART");
        if (stationArrivee == null || this.stationFacade.find(stationArrivee.getId())==null)
            throw new StationInexistanteException("ARRIVEE");
        Reservation res = null;
        //vérifier qu'un quai soit dispo
        int n = 0;
        if (!this.quaiFacade.getQuaisDispo(stationDepart.getId()).isEmpty() 
            && !this.quaiFacade.getQuaisDispo(stationArrivee.getId()).isEmpty()){
            //récupérer le premier résultat de la liste
            Quai quaiDepart = this.quaiFacade.getQuaisDispo(stationDepart.getId()).get(n);
            Quai quaiArrivee = this.quaiFacade.getQuaisDispo(stationArrivee.getId()).get(n);
            for (Navette navette : stationDepart.getListeNavettes()){
                //vérifier la capacité de la navette
                if (navette.getCapacite() == nbPassagers || navette.getCapacite() > nbPassagers){
                    res = this.reservationFacade.creerReservation(nbPassagers, dateDepart, navette, usager, stationDepart, stationArrivee, quaiDepart, quaiArrivee);
                    this.trajetFacade.creerTrajet(nbPassagers, EtatTrajet.VOYAGE_INITIE, stationDepart, stationArrivee, quaiDepart, quaiArrivee, usager);
                    this.operationFacade.creerOperation(new Date(), navette);
                    break;
                } else {
                    //lever une erreur si pas de navettes avec capacité suffisante
                    throw new CapaciteNavetteInsuffisanteException();
                }
            }
            n++;
        } else {
            if (this.quaiFacade.getQuaisDispo(stationDepart.getId()).isEmpty())
                throw new PasDeQuaiDispoException("DEPART");
            if (this.quaiFacade.getQuaisDispo(stationArrivee.getId()).isEmpty())
                throw new PasDeQuaiDispoException("ARRIVEE");
        }
        return res;
    }

}
