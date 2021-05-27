/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.ws;

import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Quai;
import fr.miage.m1.entities.Utilisateur;
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
    //@Oneway
    public String creerNavette(@WebParam(name = "estEnRevision") boolean estEnRevision, @WebParam(name = "nbVoyages") int nbVoyages, @WebParam(name = "capacite") int capacite) {
        Navette nvt = ejbRef.creerNavette(estEnRevision, nbVoyages, capacite, null);
        return "Ceci est mon id : " + nvt.getId();
    }

    @WebMethod(operationName = "getNavette")
    public String getNavette(@WebParam(name = "idNavette") long idNavette) {
         ejbRef.getNavette(idNavette);
         return "";
    }
    
    @WebMethod(operationName = "creerUtilisateur")
    public String creerUtilisateur(@WebParam(name = "prenom") String prenom, @WebParam(name = "nom") String nom, @WebParam(name = "mail") String mail, @WebParam(name = "mdp") String mdp) {
        Utilisateur user = ejbRef.creerUtilisateur(prenom, nom, mail, mdp);
        return "Ceci est mon id : " + user.getId();
    }

    @WebMethod(operationName = "getUtilisateur")
    public String getUtilisateur(@WebParam(name = "idUtilisateur") Long idUtilisateur) {
        Utilisateur user = ejbRef.getUtilisateur(idUtilisateur);
        return "Utilisateur + " + user.getPrenom();
    }
}
