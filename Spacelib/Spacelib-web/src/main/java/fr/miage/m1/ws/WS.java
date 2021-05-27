/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.ws;

import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Quai;
import fr.miage.m1.exposition.ExpoLocal;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Flo
 */
@WebService(serviceName = "WS")
public class WS {

    @EJB
    private ExpoLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "creerNavette")
    @Oneway
    public void creerNavette(@WebParam(name = "estEnRevision") boolean estEnRevision, @WebParam(name = "nbVoyages") int nbVoyages) {
        //ejbRef.creerNavette(estEnRevision, nbVoyages, capacite, null);
    }

    @WebMethod(operationName = "getNavette")
    public String getNavette(@WebParam(name = "idNavette") long idNavette) {
         //ejbRef.getNavette(idNavette);
         return "";
    }
    
}
