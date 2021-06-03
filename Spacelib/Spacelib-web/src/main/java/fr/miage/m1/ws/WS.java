/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.ws;

import fr.miage.m1.entities.Administrateur;
import fr.miage.m1.entities.Conducteur;
import fr.miage.m1.entities.Duree;
import fr.miage.m1.entities.EtatTrajet;
import fr.miage.m1.entities.Mecanicien;
import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Operation;
import fr.miage.m1.entities.Quai;
import fr.miage.m1.entities.Reparation;
import fr.miage.m1.entities.Reservation;
import fr.miage.m1.entities.Station;
import fr.miage.m1.entities.Trajet;
import fr.miage.m1.entities.Usager;
import fr.miage.m1.entities.Utilisateur;
import fr.miage.m1.exposition.ExpoLocal;
import fr.miage.m1.utilities.CapaciteNavetteNonAutoriseeException;
import fr.miage.m1.utilities.MailUsagerDejaExistantException;
import fr.miage.m1.utilities.NavetteSansQuaiException;
import java.util.Date;
import javax.ejb.EJB;
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
    public String creerNavette(@WebParam(name = "estEnRevision") boolean estEnRevision, @WebParam(name = "estDispo") boolean estDispo, @WebParam(name = "capacite") int capacite, @WebParam(name = "idQuai") Long idQuai) throws NavetteSansQuaiException, CapaciteNavetteNonAutoriseeException{
        if (ejbRef.getQuai(idQuai) == null)
            throw new NavetteSansQuaiException();
        Navette navette = ejbRef.creerNavette(estEnRevision, estDispo, 0, capacite, ejbRef.getQuai(idQuai));
        return navette.toString(); 
    }

    @WebMethod(operationName = "getNavette")
    public String getNavette(@WebParam(name = "idNavette") long idNavette) {
         Navette navette = ejbRef.getNavette(idNavette);
         return navette.toString();
    }
    
    @WebMethod(operationName = "creerUtilisateur")
    public String creerUtilisateur(@WebParam(name = "prenom") String prenom, @WebParam(name = "nom") String nom, @WebParam(name = "mail") String mail, @WebParam(name = "mdp") String mdp) {
        Utilisateur user = ejbRef.creerUtilisateur(prenom, nom, mail, mdp);
        return user.toString();
    }

    @WebMethod(operationName = "getUtilisateur")
    public String getUtilisateur(@WebParam(name = "idUtilisateur") Long idUtilisateur) {
        Utilisateur user = ejbRef.getUtilisateur(idUtilisateur);
        return user.toString();
    }
    
    
    @WebMethod(operationName = "creerAdmnistrateur")
    public String creerAdmnistrateur(@WebParam(name = "prenom") String prenom, @WebParam(name = "nom") String nom, @WebParam(name = "mail") String mail, @WebParam(name = "mdp") String mdp) {
        Administrateur admin = ejbRef.creerAdmnistrateur(prenom, nom, mail, mdp);
        return admin.toString();
    }

    @WebMethod(operationName = "getAdmnistrateur")
    public String getAdmnistrateur(@WebParam(name = "idAdmin") Long idAdmin) {
        Administrateur admin = ejbRef.getAdmnistrateur(idAdmin);
        return admin.toString();
    }

    @WebMethod(operationName = "creerConducteur")
    public String creerConducteur(@WebParam(name = "prenom") String prenom, @WebParam(name = "nom") String nom, @WebParam(name = "mail") String mail, @WebParam(name = "mdp") String mdp) {
        Conducteur conducteur = ejbRef.creerConducteur(prenom, nom, mail, mdp);
        return conducteur.toString();
    }

    @WebMethod(operationName = "getConducteur")
    public String getConducteur(@WebParam(name = "idConducteur") Long idConducteur) {
        Conducteur conducteur = ejbRef.getConducteur(idConducteur);
        return conducteur.toString();
    }

    @WebMethod(operationName = "creerDuree")
    public String creerDuree(@WebParam(name = "duree") int duree, @WebParam(name = "idStation1") Long idStation1, @WebParam(name = "idStation2") Long idStation2) {
        Duree objDuree = ejbRef.creerDuree(duree, ejbRef.getStation(idStation1), ejbRef.getStation(idStation2));
        return objDuree.toString();
    }

    @WebMethod(operationName = "getDuree")
    public String getDuree(@WebParam(name = "idDuree") Long idDuree) {
        Duree objDuree = ejbRef.getDuree(idDuree);
        return objDuree.toString();
    }

    @WebMethod(operationName = "creerMecanicien")
    public String creerMecanicien(@WebParam(name = "prenom") String prenom, @WebParam(name = "nom") String nom, @WebParam(name = "mail") String mail, @WebParam(name = "mdp") String mdp) {
        Mecanicien mecanicien = ejbRef.creerMecanicien(prenom, nom, mail, mdp);
        return mecanicien.toString();
    }

    @WebMethod(operationName = "getMecanicien")
    public String getMecanicien(@WebParam(name = "idMecanicien") Long idMecanicien) {
        Mecanicien mecanicien = ejbRef.getMecanicien(idMecanicien);
        return mecanicien.toString();
    }

    @WebMethod(operationName = "creerOperation")
    public String creerOperation(@WebParam(name = "dateOperation") Date dateOperation, @WebParam(name = "idNavette") Long idNavette) {
        Navette navette = ejbRef.getNavette(idNavette);
        Operation operation = ejbRef.creerOperation(dateOperation, navette);
        return operation.toString();
    }

    @WebMethod(operationName = "getOperation")
    public String getOperation(@WebParam(name = "idOperation") Long idOperation) {
        Operation operation = ejbRef.getOperation(idOperation);
        return operation.toString();
    }

    @WebMethod(operationName = "creerQuai")
    public String creerQuai(@WebParam(name = "noQuai") int noQuai, @WebParam(name = "estLibre") boolean estLibre, @WebParam(name = "idStation") Long idStation) {
        Station station = ejbRef.getStation(idStation);
        Quai quai = ejbRef.creerQuai(noQuai, estLibre, station);
        return quai.toString();
    }

    @WebMethod(operationName = "getQuai")
    public String getQuai(@WebParam(name = "idQuai") Long idQuai) {
        Quai quai = ejbRef.getQuai(idQuai);
        return quai.toString();
    }

    @WebMethod(operationName = "creerReparation")
    public String creerReparation(@WebParam(name = "dateCreationOperation") Date dateCreationOperation) {
        Reparation reparation = ejbRef.creerReparation(dateCreationOperation);
        return reparation.toString();
    }

    @WebMethod(operationName = "getReparation")
    public String getReparation(@WebParam(name = "idReparation") Long idReparation) {
        Reparation reparation = ejbRef.getReparation(idReparation);
        return reparation.toString();
    }

    @WebMethod(operationName = "creerReservation")
    public String creerReservation(@WebParam(name = "nbPassagers") int nbPassagers, @WebParam(name = "dateDepart") Date dateDepart, @WebParam(name = "idNavette") Long idNavette, @WebParam(name = "idUsager") Long idUsager, @WebParam(name = "idStationDepart") Long idStationDepart, @WebParam(name = "idStationArrivee") Long idStationArrivee, @WebParam(name = "idQuaiDepart") Long idQuaiDepart, @WebParam(name = "idQuaiArrivee") Long idQuaiArrivee) {
        Navette navette = ejbRef.getNavette(idNavette);
        Usager usager = ejbRef.getUsager(idUsager);
        Station stationDepart = ejbRef.getStation(idStationDepart);
        Station stationArrivee = ejbRef.getStation(idStationArrivee);
        Quai quaiDepart = ejbRef.getQuai(idQuaiDepart);
        Quai quaiArrivee = ejbRef.getQuai(idQuaiArrivee);
        Reservation reservation = ejbRef.creerReservation(nbPassagers, dateDepart, navette, usager, stationDepart, stationArrivee, quaiDepart, quaiArrivee);
        return reservation.toString();
    }

    @WebMethod(operationName = "getReservation")
    public String getReservation(@WebParam(name = "idReservation") Long idReservation) {
        Reservation reservation = ejbRef.getReservation(idReservation);
        return reservation.toString();
    }

    @WebMethod(operationName = "creerStation")
    public String creerStation(@WebParam(name = "nom") String nom, @WebParam(name = "coordonnees") String coordonnees) {
        Station station = ejbRef.creerStation(nom, coordonnees);
        return station.toString();
    }

    @WebMethod(operationName = "getStation")
    public String getStation(@WebParam(name = "idStation") Long idStation) {
        Station station = ejbRef.getStation(idStation);
        return station.toString();
    }

    @WebMethod(operationName = "creerTrajet")
    public String creerTrajet(@WebParam(name = "nbPassagers") int nbPassagers, @WebParam(name = "etatTrajet") EtatTrajet etatTrajet, @WebParam(name = "idStationDepart") Long idStationDepart, @WebParam(name = "idStationArrivee") Long idStationArrivee, @WebParam(name = "idQuaiDepart") Long idQuaiDepart, @WebParam(name = "idQuaiArrivee") Long idQuaiArrivee, @WebParam(name = "idUtilisateur") Long idUtilisateur) {
        Station stationDepart = ejbRef.getStation(idStationDepart);
        Station stationArrivee = ejbRef.getStation(idStationArrivee);
        Quai quaiDepart = ejbRef.getQuai(idQuaiDepart);
        Quai quaiArrivee = ejbRef.getQuai(idQuaiArrivee);
        Utilisateur user = ejbRef.getUtilisateur(idUtilisateur);
        Trajet trajet = ejbRef.creerTrajet(nbPassagers, etatTrajet, stationDepart, stationArrivee, quaiDepart, quaiArrivee, user);
        return trajet.toString();
    }

    @WebMethod(operationName = "getTrajet")
    public String getTrajet(@WebParam(name = "idTrajet") Long idTrajet) {
        Trajet trajet = ejbRef.getTrajet(idTrajet);
        return trajet.toString();
    }

    @WebMethod(operationName = "creerUsager")
    public String creerUsager(@WebParam(name = "prenom") String prenom, @WebParam(name = "nom") String nom, @WebParam(name = "mail") String mail, @WebParam(name = "mdp") String mdp) throws MailUsagerDejaExistantException {
        Usager usager = ejbRef.creerUsager(prenom, nom, mail, mdp);
        return usager.toString();
    }

    @WebMethod(operationName = "getUsager")
    public String getUsager(@WebParam(name = "idUsager") Long idUsager) {
        Usager usager = ejbRef.getUsager(idUsager);
        return usager.toString();
    }

}
