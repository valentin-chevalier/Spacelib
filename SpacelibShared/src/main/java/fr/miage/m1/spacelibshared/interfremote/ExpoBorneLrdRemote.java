/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.spacelibshared.interfremote;

import fr.miage.m1.spacelibshared.utilities.MailInexistantException;
import fr.miage.m1.spacelibshared.utilities.MailUsagerDejaExistantException;
import fr.miage.m1.spacelibshared.utilities.UsagerExport;
import javax.ejb.Remote;

/**
 *
 * @author Flo
 */
@Remote
public interface ExpoBorneLrdRemote {
    
    public UsagerExport creerUsager(String prenom, String nom, String mail, String mdp) throws MailUsagerDejaExistantException;
    
    public UsagerExport getUsager(Long idUsager);
    
    //public TrajetExport getTrajet(Long idTrajet);
    
    //public StationExport getStation(Long idStation);
   
    public UsagerExport connecterUsager(String mail, String mdp) throws MailInexistantException;
   /*

    public ReservationExport effectuerReservation (String dateDepart, UsagerExport usager, StationExport stationDepart, StationExport stationArrivee, int nbPassagers) throws ParseException, PasDeNavetteAQuaiException, RevisionNavetteException, TrajetInexistantException, CapaciteNavetteInsuffisanteException, PasDeQuaiDispoException, StationInexistanteException, UsagerInexistantException, NbPassagersNonAutoriseException, ReservationInexistanteException, ReservationDejaExistanteException, AucuneReservationException;

    public TrajetExport finaliserTrajet(UsagerExport usager) throws TrajetDejaAcheveException, TrajetInexistantException, UsagerInexistantException, RevisionNavetteException, ReservationInexistanteException, AucuneReservationException;
*/
}
