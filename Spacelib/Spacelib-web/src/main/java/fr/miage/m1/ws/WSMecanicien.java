/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.ws;

import fr.miage.m1.exposition.ExpoMecanicienLocal;
import fr.miage.m1.utilities.MailInexistantException;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Flo
 */
@WebService(serviceName = "WSMecanicien")
public class WSMecanicien {

    @EJB
    private ExpoMecanicienLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "creerMecanicien")
    public String creerMecanicien(@WebParam(name = "prenom") String prenom, @WebParam(name = "nom") String nom, @WebParam(name = "mail") String mail, @WebParam(name = "mdp") String mdp) {
        return ejbRef.creerMecanicien(prenom, nom, mail, mdp).toString();
    }

    @WebMethod(operationName = "getMecanicien")
    public String getMecanicien(@WebParam(name = "idMecanicien") Long idMecanicien) {
        return ejbRef.getMecanicien(idMecanicien).toString();
    }

    @WebMethod(operationName = "verifierMecanicienDansBd")
    public String verifierMecanicienDansBd(@WebParam(name = "mail") String mail, @WebParam(name = "mdp") String mdp) throws MailInexistantException {
        return ejbRef.verifierMecanicienDansBd(mail, mdp).toString();
    }
    
}
