/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.exposition;

import fr.miage.m1.entities.ChargeNavette;
import fr.miage.m1.entities.ChargeQuai;
import fr.miage.m1.entities.Conducteur;
import fr.miage.m1.entities.Station;
import fr.miage.m1.entities.Trajet;
import fr.miage.m1.metier.GestionConducteurLocal;
import fr.miage.m1.metier.GestionStationLocal;
import fr.miage.m1.metier.GestionSystemeLocal;
import fr.miage.m1.utilities.PasDeNavetteAQuaiException;
import fr.miage.m1.utilities.PasDeQuaiDispoException;
import fr.miage.m1.utilities.RevisionNavetteException;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Flo
 */
@Stateless
public class ExpoConducteur implements ExpoConducteurLocal {

    @EJB
    private GestionConducteurLocal gestionConducteur;

    @EJB
    private GestionStationLocal gestionStation;

    @EJB
    private GestionSystemeLocal gestionSysteme;
    
    @Override
    public Station getStation (Long idStation){
        return this.gestionStation.getStation(idStation);
    }

    @Override
    public HashMap<Station, ChargeQuai> stationsQuaisALiberer() {
        return this.gestionSysteme.stationsQuaisALiberer();
    }

    @Override
    public HashMap<Station, ChargeNavette> stationsNavettesATransferer() {
        return this.gestionSysteme.stationsNavettesATransferer();
    }

    @Override
    public List<Trajet> creerListeVoyages(Conducteur conducteur) throws RevisionNavetteException, PasDeQuaiDispoException, PasDeNavetteAQuaiException {
        return this.gestionSysteme.creerListeVoyages(conducteur);
    }

    @Override
    public Conducteur getConducteur(Long idConducteur) {
        return this.gestionConducteur.getConducteur(idConducteur);
    }
    
    

}
