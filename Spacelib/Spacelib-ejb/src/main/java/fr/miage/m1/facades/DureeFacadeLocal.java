/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.facades;

import fr.miage.m1.entities.Duree;
import fr.miage.m1.entities.Station;
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
    
    public Duree creerDuree(int duree, Station station1, Station station2);
    
    public Duree getDuree(Long idDuree);
    
    public Long calculerDuree(Station station1, Station station2);
    
}
