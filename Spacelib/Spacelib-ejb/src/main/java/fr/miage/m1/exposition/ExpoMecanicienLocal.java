/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.exposition;

import fr.miage.m1.entities.Mecanicien;
import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Operation;
import fr.miage.m1.entities.Quai;
import fr.miage.m1.entities.Station;
import fr.miage.m1.utilities.AucuneReparationException;
import fr.miage.m1.utilities.MailInexistantException;
import fr.miage.m1.utilities.MauvaisMecanicienException;
import fr.miage.m1.utilities.NavetteInexistanteException;
import fr.miage.m1.utilities.NavettePasRevisableException;
import fr.miage.m1.utilities.PasDeNavetteAReviserException;
import fr.miage.m1.utilities.QuaiInexistantException;
import fr.miage.m1.utilities.StationInexistanteException;
import fr.miage.m1.utilities.UsagerInexistantException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Flo
 */
@Local
public interface ExpoMecanicienLocal {
    
    public Mecanicien creerMecanicien(String prenom, String nom, String mail, String mdp);

    public Mecanicien getMecanicien(Long idMecanicien);
    
    public Mecanicien verifierMecanicienDansBd (String mail, String mdp) throws MailInexistantException;

    public Station getStation(Long idStation) throws StationInexistanteException;

    public Quai commencerReparation(Long idMecanicien, Long idNavette) throws NavetteInexistanteException, QuaiInexistantException;

    public Quai getQuai(Long idQuai);

    public List<Navette> getNavettesAReviser(Station station) throws PasDeNavetteAReviserException;
    
    public Quai choisirNavetteAReviser(Long idMecanicien, Long idNavette) throws NavettePasRevisableException, UsagerInexistantException, PasDeNavetteAReviserException, NavetteInexistanteException;

    public Operation cloturerReservation(Long idMecanicien, Long idNavette) throws AucuneReparationException, NavetteInexistanteException, UsagerInexistantException, AucuneReparationException, MauvaisMecanicienException;
}
