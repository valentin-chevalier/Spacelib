/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.exposition;

import fr.miage.m1.entities.Reservation;
import fr.miage.m1.entities.Station;
import fr.miage.m1.entities.Trajet;
import fr.miage.m1.entities.Usager;
import fr.miage.m1.utilities.AucuneReservationException;
import fr.miage.m1.utilities.CapaciteNavetteInsuffisanteException;
import fr.miage.m1.utilities.MailUsagerDejaExistantException;
import fr.miage.m1.utilities.MailInexistantException;
import fr.miage.m1.utilities.NbPassagersNonAutoriseException;
import fr.miage.m1.utilities.PasDeQuaiDispoException;
import fr.miage.m1.utilities.ReservationDejaExistanteException;
import fr.miage.m1.utilities.ReservationInexistanteException;
import fr.miage.m1.utilities.RevisionNavetteException;
import fr.miage.m1.utilities.StationInexistanteException;
import fr.miage.m1.utilities.TrajetDejaAcheveException;
import fr.miage.m1.utilities.TrajetInexistantException;
import fr.miage.m1.utilities.UsagerInexistantException;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author Flo
 */
@Local
public interface ExpoBorneLocal {
    
    public Usager creerUsager(String prenom, String nom, String mail, String mdp) throws MailUsagerDejaExistantException;
    
    public Usager getUsager(Long idUsager);
    
    public Trajet getTrajet(Long idTrajet);
    
    public Station getStation(Long idStation);
   
    public Usager connecterUsager(String mail, String mdp) throws MailInexistantException;

    public Reservation effectuerReservation (Date dateDepart, Usager usager, Station stationDepart, Station stationArrivee, int nbPassagers) throws TrajetInexistantException, CapaciteNavetteInsuffisanteException, PasDeQuaiDispoException, StationInexistanteException, UsagerInexistantException, NbPassagersNonAutoriseException, ReservationInexistanteException, ReservationDejaExistanteException, AucuneReservationException;

    public Trajet finaliserTrajet(Usager usager) throws TrajetDejaAcheveException, TrajetInexistantException, UsagerInexistantException, RevisionNavetteException, ReservationInexistanteException, AucuneReservationException;
}
