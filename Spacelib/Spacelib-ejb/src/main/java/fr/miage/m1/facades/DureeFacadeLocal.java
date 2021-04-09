/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.facades;

import fr.miage.m1.entities.Duree;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Valentin
 */
@Local
public interface DureeFacadeLocal {

    void create(Duree duree);

    void edit(Duree duree);

    void remove(Duree duree);

    Duree find(Object id);

    List<Duree> findAll();

    List<Duree> findRange(int[] range);

    int count();
    
}
