/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.ChargeNavette;
import fr.miage.m1.entities.ChargeQuai;
import fr.miage.m1.entities.Conducteur;
import fr.miage.m1.entities.Station;
import fr.miage.m1.entities.Trajet;
import fr.miage.m1.spacelibshared.utilities.PasDeNavetteAQuaiException;
import fr.miage.m1.spacelibshared.utilities.PasDeQuaiDispoException;
import fr.miage.m1.spacelibshared.utilities.RevisionNavetteException;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Valentin
 */
@Local
public interface GestionSystemeLocal {
    
    public HashMap<Station, ChargeQuai> stationsQuaisALiberer();
    
    public HashMap<Station, ChargeNavette> stationsNavettesATransferer();

    public List<Trajet> creerListeVoyages(Conducteur conducteur) throws RevisionNavetteException, PasDeQuaiDispoException, PasDeNavetteAQuaiException;
}
