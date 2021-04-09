/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.facades;

import fr.miage.m1.entities.Mecanicien;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Valentin
 */
@Local
public interface MecanicienFacadeLocal {

    void create(Mecanicien mecanicien);

    void edit(Mecanicien mecanicien);

    void remove(Mecanicien mecanicien);

    Mecanicien find(Object id);

    List<Mecanicien> findAll();

    List<Mecanicien> findRange(int[] range);

    int count();
    
}
