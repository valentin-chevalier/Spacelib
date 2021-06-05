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
import fr.miage.m1.metier.GestionMecanicienLocal;
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
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Flo
 */
@Stateless
public class ExpoMecanicien implements ExpoMecanicienLocal {

    @EJB
    private GestionMecanicienLocal gestionMecanicien;

    @Override
    public Mecanicien creerMecanicien(String prenom, String nom, String mail, String mdp) {
        return this.gestionMecanicien.creerMecanicien(prenom, nom, mail, mdp);
    }

    @Override
    public Mecanicien getMecanicien(Long idMecanicien) {
        return this.gestionMecanicien.getMecanicien(idMecanicien);
    }

    @Override
    public Mecanicien verifierMecanicienDansBd(String mail, String mdp) throws MailInexistantException {
        return this.gestionMecanicien.verifierMecanicienDansBd(mail, mdp);
    }
    
    @Override
    public Station getStation(Long idStation) throws StationInexistanteException{
        return this.gestionMecanicien.getStation(idStation);
    }
    
    @Override
    public Quai commencerReparation(Long idMecanicien, Long idNavette) throws NavetteInexistanteException, QuaiInexistantException{
        return this.gestionMecanicien.commencerReparation(idMecanicien, idNavette);
    }

    @Override
    public Quai getQuai(Long idQuai) {
        return this.gestionMecanicien.getQuai(idQuai);
    }
    
    @Override
    public List<Navette> getNavettesAReviser(Station station) throws PasDeNavetteAReviserException{
        return this.gestionMecanicien.getNavettesAReviser(station);
    }
    
    @Override
    public Quai choisirNavetteAReviser(Long idMecanicien, Long idNavette) throws NavettePasRevisableException, UsagerInexistantException, PasDeNavetteAReviserException, NavetteInexistanteException{
        return this.gestionMecanicien.choisirNavetteAReviser(idMecanicien, idNavette);
    }

    @Override
    public Operation cloturerReservation(Long idMecanicien, Long idNavette) throws AucuneReparationException, NavetteInexistanteException, UsagerInexistantException, AucuneReparationException, MauvaisMecanicienException{
        return this.gestionMecanicien.cloturerReservation(idMecanicien, idNavette);
    }

}
