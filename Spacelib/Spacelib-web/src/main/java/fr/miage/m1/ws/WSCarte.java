/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.ws;

import fr.miage.m1.exposition.ExpoCarteLocal;
import javax.ejb.EJB;
import javax.jws.WebMethod;
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
    
}
