/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.Duree;
import fr.miage.m1.entities.Station;
import fr.miage.m1.facades.DureeFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Flo
 */
@Stateless
public class GestionDuree implements GestionDureeLocal {

    @EJB
    private DureeFacadeLocal dureeFacade;

    @Override
    public Duree creerDuree(Long duree, Station station1, Station station2) {
        return this.dureeFacade.creerDuree(duree, station1, station2);
    }

    @Override
    public Duree getDuree(Long idDuree) {
        return this.dureeFacade.getDuree(idDuree);
    }
    
    @Override
    public Long calculerDuree(Station station1, Station station2){
        return this.dureeFacade.calculerDuree(station1, station2);
    }

}
