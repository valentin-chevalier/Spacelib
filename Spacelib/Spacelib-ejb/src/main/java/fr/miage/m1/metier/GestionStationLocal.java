/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Quai;
import fr.miage.m1.entities.Station;
import fr.miage.m1.entities.Trajet;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author Elias
 */
@Local
public interface GestionStationLocal {
    
    public Station creerStation(String nom, String coordonnees, Trajet trajet1, Trajet trajet2);
    
    public Station getStation(Long idStation);
}
