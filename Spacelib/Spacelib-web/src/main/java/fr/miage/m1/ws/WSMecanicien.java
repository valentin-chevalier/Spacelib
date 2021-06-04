/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.ws;

import fr.miage.m1.exposition.ExpoMecanicienLocal;
import fr.miage.m1.utilities.MailInexistantException;
import fr.miage.m1.utilities.PasDeNavetteAReviserException;
import fr.miage.m1.utilities.StationInexistanteException;
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
    private ExpoMecanicienLocal ejbRef;

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
    
    @WebMethod(operationName = "commencerReparation")
    public String commencerReparation(@WebParam(name = "idMecanicien") Long idMecanicien, @WebParam(name = "idQuai") Long idQuai, @WebParam(name = "idNavette") Long idNavette){
        return this.ejbRef.commencerReparation(idMecanicien, idQuai, idNavette).toString();
    }
    
    @WebMethod(operationName = "getQuai")
    public String commencerReparation(@WebParam(name = "idQuai") Long idQuai){
        return this.ejbRef.getQuai(idQuai).toString();
    }
    
    @WebMethod(operationName = "getNavettesAReviser")
    public String getNavettesAReviser(@WebParam(name = "idStation") Long idStation) throws PasDeNavetteAReviserException, StationInexistanteException{
        if (idStation == null || this.ejbRef.getStation(idStation) == null){
            throw new StationInexistanteException();
        }
        return this.ejbRef.getNavettesAReviser(ejbRef.getStation(idStation)).toString();
    }
}
