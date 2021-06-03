/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.ws;

import fr.miage.m1.exposition.ExpoBorneLocal;
import fr.miage.m1.utilities.AucuneReservationException;
import fr.miage.m1.utilities.CapaciteNavetteInsuffisanteException;
import fr.miage.m1.utilities.MailUsagerDejaExistantException;
import fr.miage.m1.utilities.NbPassagersNonAutoriseException;
import fr.miage.m1.utilities.PasDeQuaiDispoException;
import fr.miage.m1.utilities.StationInexistanteException;
import fr.miage.m1.utilities.MailInexistantException;
import fr.miage.m1.utilities.ReservationDejaExistanteException;
import fr.miage.m1.utilities.ReservationInexistanteException;
import fr.miage.m1.utilities.RevisionNavetteException;
import fr.miage.m1.utilities.TrajetInexistantException;
import fr.miage.m1.utilities.UsagerInexistantException;
import java.util.Date;
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
    public String effectuerReservation(@WebParam(name = "dateDepart") Date dateDepart, @WebParam(name = "idUsager") Long idUsager, @WebParam(name = "idStationDepart") Long idStationDepart, @WebParam(name = "idStationArrivee") Long idStationArrivee, @WebParam(name = "nbPassagers") int nbPassagers) throws CapaciteNavetteInsuffisanteException, PasDeQuaiDispoException, StationInexistanteException, UsagerInexistantException, NbPassagersNonAutoriseException, ReservationInexistanteException, ReservationDejaExistanteException, AucuneReservationException{
        return ejbRef.effectuerReservation(dateDepart, ejbRef.getUsager(idUsager), ejbRef.getStation(idStationDepart), ejbRef.getStation(idStationArrivee), nbPassagers).toString();
    }
    
    @WebMethod(operationName = "finaliserTrajet")
    public String finaliserTrajet(@WebParam(name = "idUsager") Long idUsager) throws TrajetInexistantException, UsagerInexistantException, RevisionNavetteException, ReservationInexistanteException, AucuneReservationException {
        if (idUsager == null || this.ejbRef.getUsager(idUsager) == null)
            throw new UsagerInexistantException();
        return ejbRef.finaliserTrajet(this.ejbRef.getUsager(idUsager)).toString();
    }
}
