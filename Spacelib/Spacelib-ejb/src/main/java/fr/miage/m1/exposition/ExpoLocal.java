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
import fr.miage.m1.utilities.CapaciteNavetteNonAutoriseeException;
import fr.miage.m1.utilities.MailUsagerDejaExistantException;
import fr.miage.m1.utilities.NavetteSansQuaiException;
import fr.miage.m1.utilities.PasDeNavetteAQuaiException;
import fr.miage.m1.utilities.PasDeQuaiDispoException;
import fr.miage.m1.utilities.RevisionNavetteException;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author Flo
 */
@Local
public interface ExpoLocal {
    
    public Administrateur creerAdmnistrateur(String prenom, String nom, String mail, String mdp);

    public Administrateur getAdmnistrateur(Long idAdmin);
    
    public Conducteur creerConducteur(String prenom, String nom, String mail, String mdp);
    
    public Conducteur getConducteur(Long idConducteur);
    
    public Duree creerDuree(int duree, Station station1, Station station2);
    
    public Duree getDuree(Long idDuree);
    
    public Mecanicien creerMecanicien(String prenom, String nom, String mail, String mdp);

    public Mecanicien getMecanicien(Long idMecanicien);
    
    public Navette creerNavette(boolean estEnRevision, boolean estDispo, int nbVoyages, int capacite, Quai quai) throws NavetteSansQuaiException, CapaciteNavetteNonAutoriseeException;
    
    public Navette getNavette(Long idNavette);
    
    public Operation creerOperation(Date dateOperation, Navette navette);
    
    public Operation getOperation (Long idOperation);
    
    public Quai creerQuai(int noQuai, boolean estLibre, Station station);
    
    public Quai getQuai (Long idQuai);
    
    public Reparation creerReparation (Date dateCreationOperation);
    
    public Reparation getReparation(Long idReparation);
    
    public Reservation creerReservation (int nbPassagers, Date dateDepart, Date dateArrivee, Navette navette, Usager usager, Station stationDepart, Station stationArrivee, Quai quaiDepart, Quai quaiArrivee);
    
    public Reservation getReservation (Long idReservation);
    
    public Station creerStation(String nom, String coordonnees);
    
    public Station getStation(Long idStation);
    
    public Trajet creerTrajet(int nbPassagers, EtatTrajet etatTrajet, Station stationDepart, Station stationArrivee, Quai quaiDepart, Quai quaiArrivee, Utilisateur utilisateur) throws RevisionNavetteException, PasDeQuaiDispoException, PasDeNavetteAQuaiException;
    
    public Trajet getTrajet(Long idTrajet);
    
    public Usager creerUsager(String prenom, String nom, String mail, String mdp) throws MailUsagerDejaExistantException;

    public Usager getUsager(Long idUsager);
    
    public Utilisateur creerUtilisateur(String prenom, String nom, String mail, String mdp);
    
    public Utilisateur getUtilisateur(Long idUtilisateur);
    
    public void setEstDispo(Navette navette, boolean bool);
}
