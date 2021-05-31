/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.facades;

import fr.miage.m1.entities.Mecanicien;
import fr.miage.m1.entities.Reparation;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Valentin
 */
@Local
public interface ReparationFacadeLocal {

    void create(Reparation reparation);

    void edit(Reparation reparation);

    void remove(Reparation reparation);

    Reparation find(Object id);

    List<Reparation> findAll();

    List<Reparation> findRange(int[] range);

    int count();
    
    public Reparation creerReparation (Date dateCreationOperation);
    
    public Reparation getReparation(Long idReparation);
    
}
