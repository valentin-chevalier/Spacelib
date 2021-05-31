/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.exposition;

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
import fr.miage.m1.facades.AdministrateurFacadeLocal;
import fr.miage.m1.facades.UtilisateurFacadeLocal;
import fr.miage.m1.metier.GestionAdministrateurLocal;
import fr.miage.m1.metier.GestionConducteurLocal;
import fr.miage.m1.metier.GestionDureeLocal;
import fr.miage.m1.metier.GestionMecanicienLocal;
import fr.miage.m1.metier.GestionNavetteLocal;
import fr.miage.m1.metier.GestionOperationLocal;
import fr.miage.m1.metier.GestionQuaiLocal;
import fr.miage.m1.metier.GestionReparationLocal;
import fr.miage.m1.metier.GestionReservationLocal;
import fr.miage.m1.metier.GestionStationLocal;
import fr.miage.m1.metier.GestionSystemeLocal;
import fr.miage.m1.metier.GestionTrajetLocal;
import fr.miage.m1.metier.GestionUsagerLocal;
import fr.miage.m1.metier.GestionUtilisateurLocal;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Flo
 */
@Stateless
public class Expo implements ExpoLocal {

    @EJB
    private GestionUsagerLocal gestionUsager;

    @EJB
    private GestionTrajetLocal gestionTrajet;

    @EJB
    private GestionSystemeLocal gestionSysteme;

    @EJB
    private GestionStationLocal gestionStation;

    @EJB
    private GestionReservationLocal gestionReservation;

    @EJB
    private GestionReparationLocal gestionReparation;

    @EJB
    private GestionQuaiLocal gestionQuai;

    @EJB
    private GestionOperationLocal gestionOperation;

    @EJB
    private GestionMecanicienLocal gestionMecanicien;

    @EJB
    private GestionDureeLocal gestionDuree;

    @EJB
    private GestionConducteurLocal gestionConducteur;

    @EJB
    private GestionAdministrateurLocal gestionAdministrateur;

    @EJB
    private GestionUtilisateurLocal gestionUtilisateur;

    @EJB
    private GestionNavetteLocal gestionNavette;
    
    @Override
    public Navette creerNavette(boolean estEnRevision, int nbVoyages, int capacite, Quai quai) {
        return this.gestionNavette.creerNavette(estEnRevision, nbVoyages, capacite, quai);
    }
    
    @Override
    public Navette getNavette(Long idNavette) {
        return this.gestionNavette.getNavette(idNavette);
    }

    @Override
    public Utilisateur creerUtilisateur(String prenom, String nom, String mail, String mdp) {
        return this.gestionUtilisateur.creerUtilisateur(prenom, nom, mail, mdp);
    }

    @Override
    public Utilisateur getUtilisateur(Long idUtilisateur) {
        return this.gestionUtilisateur.getUtilisateur(idUtilisateur);
    }

    @Override
    public Administrateur creerAdmnistrateur(String prenom, String nom, String mail, String mdp) {
        return this.gestionAdministrateur.creerAdmnistrateur(prenom, nom, mail, mdp);
    }

    @Override
    public Administrateur getAdmnistrateur(Long idAdmin) {
        return this.gestionAdministrateur.getAdmnistrateur(idAdmin);
    }

    @Override
    public Conducteur creerConducteur(String prenom, String nom, String mail, String mdp) {
        return this.gestionConducteur.creerConducteur(prenom, nom, mail, mdp);
    }

    @Override
    public Conducteur getConducteur(Long idConducteur) {
        return this.gestionConducteur.getConducteur(idConducteur);
    }

    @Override
    public Duree creerDuree(int duree, Station station1, Station station2) {
        return this.gestionDuree.creerDuree(duree, station1, station2);
    }

    @Override
    public Duree getDuree(Long idDuree) {
        return this.gestionDuree.getDuree(idDuree);
    }

    @Override
    public Mecanicien creerMecanicien(String prenom, String nom, String mail, String mdp) {
        return this.gestionMecanicien.creerMecanicien(prenom, nom, mail, mdp);
    }

    @Override
    public Mecanicien getMecanicien(Long idMecanicien) {
        return this.gestionMecanicien.getMecanicien(idMecanicien);
    }

    @Override
    public Operation creerOperation(Date dateOperation, Navette navette) {
        return this.gestionOperation.creerOperation(dateOperation, navette);
    }

    @Override
    public Operation getOperation(Long idOperation) {
        return this.gestionOperation.getOperation(idOperation);
    }

    @Override
    public Quai creerQuai(int noQuai, boolean estLibre, Station station) {
        return this.gestionQuai.creerQuai(noQuai, estLibre, station);
    }

    @Override
    public Quai getQuai(Long idQuai) {
        return this.gestionQuai.getQuai(idQuai);
    }

    @Override
    public Reparation creerReparation(Date dateCreationOperation) {
        return this.gestionReparation.creerReparation(dateCreationOperation);
    }

    @Override
    public Reparation getReparation(Long idReparation) {
        return this.gestionReparation.getReparation(idReparation);
    }

    @Override
    public Reservation creerReservation(Long nbPassagers, Date dateDepart, Navette navette, Usager usager, Station stationDepart, Station stationArrivee, Quai quaiDepart, Quai quaiArrivee) {
        return this.gestionReservation.creerReservation(nbPassagers, dateDepart, navette, usager, stationDepart, stationArrivee, quaiDepart, quaiArrivee);
    }

    @Override
    public Reservation getReservation(Long idReservation) {
        return this.gestionReservation.getReservation(idReservation);
    }

    @Override
    public Station creerStation(String nom, String coordonnees, Trajet trajet1, Trajet trajet2) {
        return this.gestionStation.creerStation(nom, coordonnees, trajet1, trajet2);
    }

    @Override
    public Station getStation(Long idStation) {
        return this.gestionStation.getStation(idStation);
    }

    @Override
    public Trajet creerTrajet(Long id, int nbPassagers, EtatTrajet etatTrajet, Station stationDepart, Station stationArrivee, Quai quaiDepart, Quai quaiArrivee, Utilisateur utilisateur) {
        return this.gestionTrajet.creerTrajet(id, nbPassagers, etatTrajet, stationDepart, stationArrivee, quaiDepart, quaiArrivee, utilisateur);
    }

    @Override
    public Trajet getTrajet(Long idTrajet) {
        return this.gestionTrajet.getTrajet(idTrajet);
    }

    @Override
    public Usager creerUsager(String prenom, String nom, String mail, String mdp) {
        return this.gestionUsager.creerUsager(prenom, nom, mail, mdp);
    }

    @Override
    public Usager getUsager(Long idUsager) {
        return this.gestionUsager.getUsager(idUsager);
    }

}
