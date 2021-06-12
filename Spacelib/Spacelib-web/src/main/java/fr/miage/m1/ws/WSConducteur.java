/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.ws;

import fr.miage.m1.exposition.ExpoConducteurLocal;
import fr.miage.m1.utilities.PasDeNavetteAQuaiException;
import fr.miage.m1.utilities.PasDeQuaiDispoException;
import fr.miage.m1.utilities.RevisionNavetteException;
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
    
    @WebMethod(operationName = "stationsQuaisALiberer")
    public String stationsQuaisALiberer(){
        return ejbRef.stationsQuaisALiberer().toString();
    }
    
    @WebMethod(operationName = "stationsNavettesATransferer")
    public String stationsNavettesATransferer(){
        return ejbRef.stationsNavettesATransferer().toString();
    }
    
    @WebMethod(operationName = "creerListeVoyages")
    public String creerListeVoyages(@WebParam(name = "idConducteur") Long idConducteur) throws RevisionNavetteException, PasDeQuaiDispoException, PasDeNavetteAQuaiException {
        return ejbRef.creerListeVoyages(ejbRef.getConducteur(idConducteur)).toString();
    }
}
