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
import fr.miage.m1.facades.NavetteFacadeLocal;
import fr.miage.m1.facades.OperationFacadeLocal;
import fr.miage.m1.facades.ReparationFacadeLocal;
import fr.miage.m1.facades.StationFacadeLocal;
import fr.miage.m1.spacelibshared.utilities.AucuneReparationException;
import fr.miage.m1.spacelibshared.utilities.MailInexistantException;
import fr.miage.m1.spacelibshared.utilities.MauvaisMecanicienException;
import fr.miage.m1.spacelibshared.utilities.NavetteInexistanteException;
import fr.miage.m1.spacelibshared.utilities.NavettePasRevisableException;
import fr.miage.m1.spacelibshared.utilities.PasDeNavetteAReviserException;
import fr.miage.m1.spacelibshared.utilities.QuaiInexistantException;
import fr.miage.m1.spacelibshared.utilities.StationInexistanteException;
import fr.miage.m1.spacelibshared.utilities.UsagerInexistantException;
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
    private OperationFacadeLocal operationFacade;

    @EJB
    private NavetteFacadeLocal navetteFacade;

    @EJB
    private ReparationFacadeLocal reparationFacade;

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
    public Mecanicien verifierMecanicienDansBd(String mail, String mdp) throws MailInexistantException {
        return this.mecanicienFacade.verifierMecanicienDansBd(mail, mdp);
    }

    @Override
    public Station getStation(Long idStation) throws StationInexistanteException {
        if (idStation == null || this.stationFacade.getStation(idStation) == null) {
            throw new StationInexistanteException();
        }
        return this.stationFacade.getStation(idStation);
    }

    @Override
    public Quai commencerReparation(Long idMecanicien, Long idNavette) throws NavetteInexistanteException, QuaiInexistantException {
        if (idNavette == null || this.gestionNavette.getNavette(idNavette) == null) {
            throw new NavetteInexistanteException();
        }
        if (this.gestionNavette.getNavette(idNavette).getQuai() == null) {
            throw new QuaiInexistantException();
        }
        //r??cup??rer le quai
        Quai quai = this.gestionNavette.getNavette(idNavette).getQuai();
        quai.setEstLibre(false);
        //maj la navette 
        Navette navette = this.gestionNavette.getNavette(idNavette);
        navette.setEstDispo(false);
        navette.setEstEnRevision(true);
        //enregistrer operation de maintenance
        Operation operation = this.gestionOperation.creerOperation(new Date(), navette);
        Reparation reparation = this.gestionReparation.creerReparation(new Date());
        reparation.setMecanicien(getMecanicien(idMecanicien));
        operation.setEtatRevision(Operation.EtatRevision.DEBUT_REVISION);
        return quai;
    }

    @Override
    public Quai getQuai(Long idQuai) {
        return this.gestionQuai.getQuai(idQuai);
    }

    @Override
    public List<Navette> getNavettesAReviser(Station station) throws PasDeNavetteAReviserException {
        List<Navette> listeNavettesAReviser = new ArrayList<Navette>();
        for (Navette navette : station.getListeNavettes()) {
            System.out.println("Navette " + navette.getId());
            System.out.println("Nb voyages " +navette.getNbVoyages());
            System.out.println("Dispo " +navette.isEstDispo());
            System.out.println("EstEnRevision " +navette.isEstEnRevision());

            if (navette.getNbVoyages() >= 3 && !navette.isEstEnRevision()) {
                listeNavettesAReviser.add(navette);
            }
        }
        if (listeNavettesAReviser.isEmpty()) {
            throw new PasDeNavetteAReviserException();
        }
        return listeNavettesAReviser;
    }

    @Override
    public Quai choisirNavetteAReviser(Long idMecanicien, Long idNavette) throws NavettePasRevisableException, UsagerInexistantException, PasDeNavetteAReviserException, NavetteInexistanteException {
        //gestion des erreurs
        if (idMecanicien == null || getMecanicien(idMecanicien) == null) {
            throw new UsagerInexistantException();
        }
        if (idNavette == null || this.gestionNavette.getNavette(idNavette) == null) {
            throw new NavetteInexistanteException();
        }
        if (this.gestionQuai.getQuai(this.gestionNavette.getNavette(idNavette).getQuai().getId()) == null) {
            throw new NavetteInexistanteException();
        }
        //cr??er op??ration de maintenance
        this.gestionOperation.creerOperationMaintenance(this.gestionNavette.getNavette(idNavette), Operation.EtatRevision.DEBUT_REVISION);
        //cr??er r??paration
        Reparation reparation = this.gestionReparation.creerReparation(new Date());
        reparation.setMecanicien(getMecanicien(idMecanicien));
        this.reparationFacade.edit(reparation);
        //set EstEnR??vision de la navette ?? true
        this.gestionNavette.getNavette(idNavette).setEstDispo(false);
        this.gestionNavette.getNavette(idNavette).setEstEnRevision(true);
        this.navetteFacade.edit(this.gestionNavette.getNavette(idNavette));
        //retourner le quai o?? est la navette
        return this.gestionNavette.getNavette(idNavette).getQuai();
    }

    @Override
    public Operation cloturerReparation(Long idMecanicien, Long idNavette) throws AucuneReparationException, NavetteInexistanteException, UsagerInexistantException, AucuneReparationException, MauvaisMecanicienException {
        if (idMecanicien == null || getMecanicien(idMecanicien) == null) {
            throw new UsagerInexistantException();
        }
        //r??cup??rer la navette
        Navette navette = this.gestionNavette.getNavette(idNavette);
        if (navette == null) {
            throw new NavetteInexistanteException();
        }
        for (Reparation rep : getMecanicien(idMecanicien).getListeReparations()) {
            if (!idMecanicien.equals(rep.getMecanicien().getId())) {
                throw new MauvaisMecanicienException();
            }
            if (this.gestionOperation.getOperation(rep.getId()) == null
                    || !idNavette.equals(this.gestionOperation.getOperation(rep.getId()).getNavette())) {
                throw new AucuneReparationException();
            }
        }
        Operation ope = this.gestionOperation.creerOperationMaintenance(navette, Operation.EtatRevision.FIN_REVISION);
        //navette redevient disponible ?? l'emprunt
        navette.setNbVoyages(0);
        navette.setEstEnRevision(false);
        navette.setEstDispo(true);
        this.navetteFacade.edit(navette);
        return ope;
    }
}
