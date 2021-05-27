/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.Quai;
import fr.miage.m1.entities.Station;
import fr.miage.m1.facades.QuaiFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Flo
 */
@Stateless
public class GestionQuai implements GestionQuaiLocal {

    @EJB
    private QuaiFacadeLocal quaiFacade;

    @Override
    public Quai creerQuai(int noQuai, boolean estLibre, Station station) {
        return this.quaiFacade.creerQuai(noQuai, estLibre, station);
    }

    @Override
    public Quai getQuai(int idQuai) {
        return this.quaiFacade.getQuai(idQuai);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
