/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.Mecanicien;
import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Operation;
import fr.miage.m1.entities.Quai;
import fr.miage.m1.entities.Reparation;
import fr.miage.m1.entities.Station;
import fr.miage.m1.facades.MecanicienFacadeLocal;
import fr.miage.m1.facades.StationFacadeLocal;
import fr.miage.m1.utilities.MailInexistantException;
import fr.miage.m1.utilities.PasDeNavetteAReviserException;
import fr.miage.m1.utilities.StationInexistanteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Flo
 */
@Stateless
public class GestionMecanicien implements GestionMecanicienLocal {

    @EJB
    private GestionReparationLocal gestionReparation;

    @EJB
    private GestionNavetteLocal gestionNavette;

    @EJB
    private GestionQuaiLocal gestionQuai;

    @EJB
    private GestionOperationLocal gestionOperation;

    @EJB
    private StationFacadeLocal stationFacade;

    @EJB
    private MecanicienFacadeLocal mecanicienFacade;

    @Override
    public Mecanicien creerMecanicien(String prenom, String nom, String mail, String mdp) {
        return this.mecanicienFacade.creerMecanicien(prenom, nom, mail, mdp);
    }

    @Override
    public Mecanicien getMecanicien(Long idMecanicien) {
        return this.mecanicienFacade.getMecanicien(idMecanicien);
    }
    
    @Override
    public Mecanicien verifierMecanicienDansBd (String mail, String mdp) throws MailInexistantException{
        return this.mecanicienFacade.verifierMecanicienDansBd(mail, mdp);
    }
    
    @Override
    public Station getStation(Long idStation) throws StationInexistanteException{
        if (idStation == null || this.stationFacade.getStation(idStation) == null)
            throw new StationInexistanteException();
        return this.stationFacade.getStation(idStation);
    }
    
    @Override
    public Reparation commencerReparation(Long idMecanicien, Long idQuai, Long idNavette){
        Quai quai = this.gestionQuai.getQuai(idQuai);
        quai.setEstLibre(false);
        Navette navette = this.gestionNavette.getNavette(idNavette);
        navette.setEstDispo(false);
        navette.setEstEnRevision(true);
        Operation operation = this.gestionOperation.creerOperation(new Date(), navette);
        Reparation reparation = this.gestionReparation.creerReparation(new Date());
        reparation.setMecanicien(getMecanicien(idMecanicien));
        operation.setEtatRevision(Operation.EtatRevision.DEBUT_REVISION);
        return reparation;
    }

    @Override
    public Quai getQuai(Long idQuai) {
        return this.gestionQuai.getQuai(idQuai);
    }

    @Override
    public List<Navette> getNavettesAReviser(Station station) throws PasDeNavetteAReviserException{
        List<Navette> listeNavettesAReviser = new ArrayList<>();
        for (Navette navette : station.getListeNavettes()){
            if(navette.getNbVoyages() >= 3){
                navette.setEstDispo(false);
                navette.setEstEnRevision(false);
                listeNavettesAReviser.add(navette);
            }
        }
        if (listeNavettesAReviser.isEmpty()){
            throw new PasDeNavetteAReviserException();
        }
        return listeNavettesAReviser;
    }
}
