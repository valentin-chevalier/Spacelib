/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.ws;

import fr.miage.m1.exposition.ExpoCarteLocal;
import fr.miage.m1.utilities.AucuneReservationException;
import fr.miage.m1.utilities.CapaciteNavetteInsuffisanteException;
import fr.miage.m1.utilities.DelaiAnnulationResDepasseException;
import fr.miage.m1.utilities.NbPassagersNonAutoriseException;
import fr.miage.m1.utilities.PasDeNavetteAQuaiException;
import fr.miage.m1.utilities.PasDeQuaiDispoException;
import fr.miage.m1.utilities.ReservationDejaExistanteException;
import fr.miage.m1.utilities.ReservationInexistanteException;
import fr.miage.m1.utilities.RevisionNavetteException;
import fr.miage.m1.utilities.StationInexistanteException;
import fr.miage.m1.utilities.TrajetDejaAcheveException;
import fr.miage.m1.utilities.TrajetInexistantException;
import fr.miage.m1.utilities.UsagerInexistantException;
import java.text.ParseException;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Flo
 */
@WebService(serviceName = "WSCarte")
public class WSCarte {

    @EJB
    private ExpoCarteLocal ejbRef;

    @WebMethod(operationName = "getAllStations")
    public String getAllStations() {
        return ejbRef.getAllStations().toString();
    }
    
    @WebMethod(operationName = "effectuerReservation")
    public String effectuerReservation(@WebParam(name = "idUsager") Long idUsager, @WebParam(name = "idStationDepart") Long idStationDepart, @WebParam(name = "dateDepart") String dateDepart, @WebParam(name = "idStationArrivee") Long idStationArrivee, @WebParam(name = "nbPassagers") int nbPassagers) throws ParseException, PasDeNavetteAQuaiException, RevisionNavetteException, TrajetInexistantException, CapaciteNavetteInsuffisanteException, PasDeQuaiDispoException, StationInexistanteException, UsagerInexistantException, NbPassagersNonAutoriseException, ReservationInexistanteException, ReservationDejaExistanteException, AucuneReservationException {
        return ejbRef.effectuerReservation(ejbRef.getUsager(idUsager), ejbRef.getStation(idStationDepart), dateDepart, ejbRef.getStation(idStationArrivee), nbPassagers).toString();
    }

    @WebMethod(operationName = "cloturerReservation")
    public String cloturerReservation(@WebParam(name = "idUsager") Long idUsager, @WebParam(name = "idReservation") Long idReservation) throws TrajetDejaAcheveException, TrajetInexistantException, UsagerInexistantException, AucuneReservationException {
        return ejbRef.cloturerReservation(idUsager, idReservation);
    }

    @WebMethod(operationName = "annulerReservation")
    public String annulerReservation(@WebParam(name = "idUsager") Long idUsager, @WebParam(name = "idReservation") Long idReservation) throws ParseException, TrajetDejaAcheveException, TrajetInexistantException, DelaiAnnulationResDepasseException, UsagerInexistantException, AucuneReservationException {
        return ejbRef.annulerReservation(ejbRef.getUsager(idUsager), idReservation);
    }
    
}
