/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.ws;

import fr.miage.m1.exposition.ExpoConducteurLocal;
import fr.miage.m1.utilities.PasDeQuaiDispoException;
import fr.miage.m1.utilities.PasDeReservationPourStationException;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Flo
 */
@WebService(serviceName = "WSConducteur")
public class WSConducteur {

    @EJB
    private ExpoConducteurLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "calculerDispoQuai")
    public void calculerDispoQuai(@WebParam(name = "idStation") Long idStation) throws PasDeQuaiDispoException, PasDeReservationPourStationException {
        ejbRef.calculerDispoQuai(idStation);
    }
    
    @WebMethod(operationName = "transfererNavettesDeStations")
    public void transfererNavettesDeStations(@WebParam(name = "idStation") Long idStation) {
        ejbRef.transfererNavettesDeStations(ejbRef.getStation(idStation));
    }
    
    @WebMethod(operationName = "stationsQuaisALiberer")
    public String stationsQuaisALiberer() throws PasDeQuaiDispoException, PasDeReservationPourStationException{
        return ejbRef.stationsQuaisALiberer().toString();
    }

}
