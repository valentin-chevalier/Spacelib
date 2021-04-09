/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.facades;

import fr.miage.m1.entities.Trajet;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Valentin
 */
@Local
public interface TrajetFacadeLocal {

    void create(Trajet trajet);

    void edit(Trajet trajet);

    void remove(Trajet trajet);

    Trajet find(Object id);

    List<Trajet> findAll();

    List<Trajet> findRange(int[] range);

    int count();
    
}
