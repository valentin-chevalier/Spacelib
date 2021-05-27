/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.facades;

import fr.miage.m1.entities.Administrateur;
import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Quai;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Valentin
 */
@Local
public interface AdministrateurFacadeLocal {

    void create(Administrateur administrateur);

    void edit(Administrateur administrateur);

    void remove(Administrateur administrateur);

    Administrateur find(Object id);

    List<Administrateur> findAll();

    List<Administrateur> findRange(int[] range);

    int count();
    
    public Administrateur creerAdmnistrateur(String prenom, String nom, String mail, String mdp);

    public Administrateur getAdmnistrateur(Long idAdmin);

    
}
