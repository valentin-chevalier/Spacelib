/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.Duree;
import fr.miage.m1.entities.Station;
import javax.ejb.Local;

/**
 *
 * @author Flo
 */
@Local
public interface GestionDureeLocal {
    
    public Duree creerDuree(Long duree, Station station1, Station station2);
    
    public Duree getDuree(Long idDuree);
    
    public Long calculerDuree(Station station1, Station station2);
}
