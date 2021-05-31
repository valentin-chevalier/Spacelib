/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.Mecanicien;
import fr.miage.m1.entities.Reparation;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author Flo
 */
@Local
public interface GestionReparationLocal {
        
    public Reparation creerReparation (Date dateCreationOperation);
    
    public Reparation getReparation(Long idReparation);
}
