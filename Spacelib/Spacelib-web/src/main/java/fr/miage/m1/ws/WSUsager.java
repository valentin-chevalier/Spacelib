/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.ws;

import fr.miage.m1.entities.Station;
import fr.miage.m1.exposition.ExpoUsagerLocal;
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
import java.text.ParseException;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Flo
 */
@WebService(serviceName = "WSUsager")
public class WSUsager {

    @EJB
    private ExpoUsagerLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "getTrajetsPossibles")
    public String getTrajetsPossibles(@WebParam(name = "idUsager") Long idUsager, @WebParam(name = "idStationDepart") Long idStationDepart, @WebParam(name = "idStationArrivee") Long idStationArrivee, @WebParam(name = "nbPassagers") int nbPassagers, @WebParam(name = "dateMin") String dateMin, @WebParam(name = "dateDepart") String dateDepart) throws ParseException, PasDeNavetteAQuaiException, RevisionNavetteException, TrajetInexistantException, CapaciteNavetteInsuffisanteException, PasDeQuaiDispoException, StationInexistanteException, UsagerInexistantException, NbPassagersNonAutoriseException, ReservationInexistanteException, ReservationDejaExistanteException, AucuneReservationException, RevisionNavetteException, PasDeQuaiDispoException, PasDeNavetteAQuaiException{
        Station stationDepart = ejbRef.getStation(idStationDepart);
        Station stationArrivee = ejbRef.getStation(idStationArrivee);
        return ejbRef.getTrajetsPossibles(idUsager, stationDepart, stationArrivee, nbPassagers, dateMin, dateDepart).toString();
    }

    @WebMethod(operationName = "getStation")
    public String getStation(@WebParam(name = "idStation") Long idStation) {
        return ejbRef.getStation(idStation).toString();
    }
    
}
