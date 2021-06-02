/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.ws;

import fr.miage.m1.exposition.ExpoBorneLocal;
import fr.miage.m1.utilities.MailUsagerDejaExistantException;
import fr.miage.m1.utilities.UsagerInexistantException;
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
    private ExpoBorneLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "creerUsager")
    public String creerUsager(@WebParam(name = "prenom") String prenom, @WebParam(name = "nom") String nom, @WebParam(name = "mail") String mail, @WebParam(name = "mdp") String mdp) throws MailUsagerDejaExistantException {
        return ejbRef.creerUsager(prenom, nom, mail, mdp).toString();
    }

    @WebMethod(operationName = "verifierUsagerDansBd")
    public String verifierUsagerDansBd(@WebParam(name = "mail") String mail, @WebParam(name = "mdp") String mdp) throws UsagerInexistantException {
        return ejbRef.verifierUsagerDansBd(mail, mdp).toString();
    }

    
}
