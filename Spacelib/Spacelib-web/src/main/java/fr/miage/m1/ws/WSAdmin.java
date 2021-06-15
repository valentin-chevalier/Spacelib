/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.ws;

import fr.miage.m1.exposition.ExpoAdminLocal;
import fr.miage.m1.spacelibshared.utilities.CapaciteNavetteNonAutoriseeException;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Valentin
 */
@WebService(serviceName = "WSAdmin")
public class WSAdmin {

    @EJB
    private ExpoAdminLocal ejbRef;

    @WebMethod(operationName = "creerStation")
    public String creerStation(@WebParam(name = "nom") String nom, @WebParam(name = "coordonnees") String coordonnees, @WebParam(name = "nbQuais") int nbQuais, @WebParam(name = "capaciteNavettes") int capaciteNavettes) throws CapaciteNavetteNonAutoriseeException {
        return ejbRef.creerStation(nom, coordonnees, nbQuais, capaciteNavettes).toString();
    }
    
    @WebMethod(operationName = "supprimerReservationsNonCloturees")
    public String supprimerReservationsNonCloturees() {
        ejbRef.supprimerReservationsNonCloturees();
        return "Les réservations non clôturées ont bien été supprimées de la bd.";
    }

}
