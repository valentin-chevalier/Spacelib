/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.ws;

import fr.miage.m1.exposition.ExpoMecanicienLocal;
import fr.miage.m1.spacelibshared.utilities.AucuneReparationException;
import fr.miage.m1.spacelibshared.utilities.MailInexistantException;
import fr.miage.m1.spacelibshared.utilities.MauvaisMecanicienException;
import fr.miage.m1.spacelibshared.utilities.NavetteInexistanteException;
import fr.miage.m1.spacelibshared.utilities.NavettePasRevisableException;
import fr.miage.m1.spacelibshared.utilities.PasDeNavetteAReviserException;
import fr.miage.m1.spacelibshared.utilities.QuaiInexistantException;
import fr.miage.m1.spacelibshared.utilities.StationInexistanteException;
import fr.miage.m1.spacelibshared.utilities.UsagerInexistantException;
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

    @WebMethod(operationName = "getStation")
    public String getStation(@WebParam(name = "idStation") Long idStation) throws StationInexistanteException {
        return ejbRef.getStation(idStation).toString();
    }

    @WebMethod(operationName = "commencerReparation")
    public String commencerReparation(@WebParam(name = "idMecanicien") Long idMecanicien, @WebParam(name = "idNavette") Long idNavette) throws NavetteInexistanteException, QuaiInexistantException {
        return ejbRef.commencerReparation(idMecanicien, idNavette).toString();
    }

    @WebMethod(operationName = "getQuai")
    public String getQuai(@WebParam(name = "idQuai") Long idQuai) {
        return ejbRef.getQuai(idQuai).toString();
    }

    @WebMethod(operationName = "getNavettesAReviser")
    public String getNavettesAReviser(@WebParam(name = "idStation") Long idStation) throws StationInexistanteException, PasDeNavetteAReviserException {
        return ejbRef.getNavettesAReviser(ejbRef.getStation(idStation)).toString();
    }

    @WebMethod(operationName = "choisirNavetteAReviser")
    public String choisirNavetteAReviser(@WebParam(name = "idMecanicien") Long idMecanicien, @WebParam(name = "idNavette") Long idNavette) throws NavettePasRevisableException, UsagerInexistantException, PasDeNavetteAReviserException, NavetteInexistanteException {
        return ejbRef.choisirNavetteAReviser(idMecanicien, idNavette).toString();
    }

    @WebMethod(operationName = "cloturerReparation")
    public String cloturerReparation(@WebParam(name = "idMecanicien") Long idMecanicien, @WebParam(name = "idNavette") Long idNavette) throws AucuneReparationException, NavetteInexistanteException, UsagerInexistantException, AucuneReparationException, MauvaisMecanicienException {
        return ejbRef.cloturerReparation(idMecanicien, idNavette).toString();
    }
    
}
