/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.ws;

import fr.miage.m1.exposition.ExpoBorneLocal;
import fr.miage.m1.spacelibshared.utilities.AucuneReservationException;
import fr.miage.m1.spacelibshared.utilities.CapaciteNavetteInsuffisanteException;
import fr.miage.m1.spacelibshared.utilities.MailUsagerDejaExistantException;
import fr.miage.m1.spacelibshared.utilities.NbPassagersNonAutoriseException;
import fr.miage.m1.spacelibshared.utilities.PasDeQuaiDispoException;
import fr.miage.m1.spacelibshared.utilities.StationInexistanteException;
import fr.miage.m1.spacelibshared.utilities.MailInexistantException;
import fr.miage.m1.spacelibshared.utilities.PasDeNavetteAQuaiException;
import fr.miage.m1.spacelibshared.utilities.ReservationDejaExistanteException;
import fr.miage.m1.spacelibshared.utilities.ReservationInexistanteException;
import fr.miage.m1.spacelibshared.utilities.RevisionNavetteException;
import fr.miage.m1.spacelibshared.utilities.TrajetDejaAcheveException;
import fr.miage.m1.spacelibshared.utilities.TrajetInexistantException;
import fr.miage.m1.spacelibshared.utilities.UsagerInexistantException;
import java.text.ParseException;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Flo
 */
@WebService(serviceName = "WSBorne")
public class WSBorne {

    @EJB
    private ExpoBorneLocal ejbRef;

    @WebMethod(operationName = "creerUsager")
    public String creerUsager(@WebParam(name = "prenom") String prenom, @WebParam(name = "nom") String nom, @WebParam(name = "mail") String mail, @WebParam(name = "mdp") String mdp) throws MailUsagerDejaExistantException {
        return ejbRef.creerUsager(prenom, nom, mail, mdp).toString();
    }

    @WebMethod(operationName = "connecterUsager")
    public String connecterUsager(@WebParam(name = "mail") String mail, @WebParam(name = "mdp") String mdp) throws MailInexistantException {
        return ejbRef.connecterUsager(mail, mdp).toString();
    }

    @WebMethod(operationName = "getUsager")
    public String getUsager(@WebParam(name = "idUsager") Long idUsager) {
        return ejbRef.getUsager(idUsager).toString();
    }

    @WebMethod(operationName = "getTrajet")
    public String getTrajet(@WebParam(name = "idTrajet") Long idTrajet) {
        return ejbRef.getTrajet(idTrajet).toString();
    }

    @WebMethod(operationName = "getStation")
    public String getStation(@WebParam(name = "idStation") Long idStation) {
        return ejbRef.getStation(idStation).toString();
    }

    @WebMethod(operationName = "effectuerReservation")
    public String effectuerReservation(@WebParam(name = "dateDepart") String dateDepart, @WebParam(name = "idUsager") Long idUsager, @WebParam(name = "idStationDepart") Long idStationDepart, @WebParam(name = "idStationArrivee") Long idStationArrivee, @WebParam(name = "nbPassagers") int nbPassagers) throws ParseException, PasDeNavetteAQuaiException, RevisionNavetteException, TrajetInexistantException, CapaciteNavetteInsuffisanteException, PasDeQuaiDispoException, StationInexistanteException, UsagerInexistantException, NbPassagersNonAutoriseException, ReservationInexistanteException, ReservationDejaExistanteException, AucuneReservationException{
        return ejbRef.effectuerReservation(dateDepart, ejbRef.getUsager(idUsager), ejbRef.getStation(idStationDepart), ejbRef.getStation(idStationArrivee), nbPassagers).toString();
    }
    
    @WebMethod(operationName = "finaliserTrajet")
    public String finaliserTrajet(@WebParam(name = "idUsager") Long idUsager) throws TrajetDejaAcheveException, TrajetInexistantException, UsagerInexistantException, RevisionNavetteException, ReservationInexistanteException, AucuneReservationException {
        if (idUsager == null || this.ejbRef.getUsager(idUsager) == null)
            throw new UsagerInexistantException();
        return ejbRef.finaliserTrajet(this.ejbRef.getUsager(idUsager)).toString();
    }
}
