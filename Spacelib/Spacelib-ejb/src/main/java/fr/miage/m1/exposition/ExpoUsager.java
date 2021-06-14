/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.exposition;

import fr.miage.m1.entities.Station;
import fr.miage.m1.metier.GestionStationLocal;
import fr.miage.m1.metier.GestionTrajetLocal;
import fr.miage.m1.utilities.AucuneReservationException;
import fr.miage.m1.utilities.CapaciteNavetteInsuffisanteException;
import fr.miage.m1.utilities.NbPassagersNonAutoriseException;
import fr.miage.m1.utilities.PasDeNavetteAQuaiException;
import fr.miage.m1.utilities.PasDeQuaiDispoException;
import fr.miage.m1.utilities.ReservationDejaExistanteException;
import fr.miage.m1.utilities.ReservationInexistanteException;
import fr.miage.m1.utilities.RevisionNavetteException;
import fr.miage.m1.utilities.StationInexistanteException;
import fr.miage.m1.utilities.TrajetInexistantException;
import fr.miage.m1.utilities.UsagerInexistantException;
import java.text.ParseException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Flo
 */
@Stateless
public class ExpoUsager implements ExpoUsagerLocal {

    @EJB
    private GestionStationLocal gestionStation;

    @EJB
    private GestionTrajetLocal gestionTrajet;
    
    @Override
    public List<String> getTrajetsPossibles(Long idUsager, Station stationDepart, Station stationArrivee, int nbPassagers, String dateMin, String dateDepart) throws ParseException, PasDeNavetteAQuaiException, RevisionNavetteException, TrajetInexistantException, CapaciteNavetteInsuffisanteException, PasDeQuaiDispoException, StationInexistanteException, UsagerInexistantException, NbPassagersNonAutoriseException, ReservationInexistanteException, ReservationDejaExistanteException, AucuneReservationException, RevisionNavetteException, PasDeQuaiDispoException, PasDeNavetteAQuaiException{
        return this.gestionTrajet.getTrajetsPossibles(idUsager, stationDepart, stationArrivee, nbPassagers, dateMin, dateDepart);
    }
    
    @Override
    public Station getStation(Long idStation){
        return this.gestionStation.getStation(idStation);
    }

}
