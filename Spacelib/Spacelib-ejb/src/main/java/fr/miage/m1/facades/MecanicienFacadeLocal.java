/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.facades;

import fr.miage.m1.entities.Mecanicien;
import fr.miage.m1.spacelibshared.utilities.MailInexistantException;
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
    
    public Mecanicien creerMecanicien(String prenom, String nom, String mail, String mdp);

    public Mecanicien getMecanicien(Long idMecanicien);
    
    public Mecanicien verifierMecanicienDansBd(String mail, String mdp) throws MailInexistantException;
}
