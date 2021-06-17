/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.EtatTrajet;
import fr.miage.m1.entities.Navette;
import fr.miage.m1.entities.Quai;
import fr.miage.m1.entities.Reservation;
import fr.miage.m1.entities.Station;
import fr.miage.m1.entities.Trajet;
import fr.miage.m1.entities.Usager;
import fr.miage.m1.facades.NavetteFacadeLocal;
import fr.miage.m1.facades.OperationFacadeLocal;
import fr.miage.m1.facades.QuaiFacadeLocal;
import fr.miage.m1.facades.ReservationFacadeLocal;
import fr.miage.m1.facades.StationFacadeLocal;
import fr.miage.m1.facades.TrajetFacadeLocal;
import fr.miage.m1.facades.UsagerFacadeLocal;
import fr.miage.m1.spacelibshared.utilities.AucuneReservationException;
import fr.miage.m1.spacelibshared.utilities.CapaciteNavetteInsuffisanteException;
import fr.miage.m1.spacelibshared.utilities.DelaiAnnulationResDepasseException;
import fr.miage.m1.spacelibshared.utilities.NbPassagersNonAutoriseException;
import fr.miage.m1.spacelibshared.utilities.PasDeNavetteAQuaiException;
import fr.miage.m1.spacelibshared.utilities.PasDeQuaiDispoException;
import fr.miage.m1.spacelibshared.utilities.ReservationDejaExistanteException;
import fr.miage.m1.spacelibshared.utilities.ReservationInexistanteException;
import fr.miage.m1.spacelibshared.utilities.RevisionNavetteException;
import fr.miage.m1.spacelibshared.utilities.StationInexistanteException;
import fr.miage.m1.spacelibshared.utilities.TrajetDejaAcheveException;
import fr.miage.m1.spacelibshared.utilities.TrajetInexistantException;
import fr.miage.m1.spacelibshared.utilities.UsagerInexistantException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Flo
 */
@Stateless
public class GestionReservation implements GestionReservationLocal {

    @EJB
    private GestionDureeLocal gestionDuree;

    @EJB
    private NavetteFacadeLocal navetteFacade;

    @EJB
    private GestionTrajetLocal gestionTrajet;

    @EJB
    private StationFacadeLocal stationFacade;

    @EJB
    private UsagerFacadeLocal usagerFacade;

    @EJB
    private OperationFacadeLocal operationFacade;

    @EJB
    private TrajetFacadeLocal trajetFacade;

    @EJB
    private QuaiFacadeLocal quaiFacade;

    @EJB
    private ReservationFacadeLocal reservationFacade;

    @Override
    public Reservation creerReservation(int nbPassagers, Date dateDepart, Date dateArrivee, Navette navette, Usager usager, Station stationDepart, Station stationArrivee, Quai quaiDepart, Quai quaiArrivee) {
        return this.reservationFacade.creerReservation(nbPassagers, dateDepart, dateArrivee, navette, usager, stationDepart, stationArrivee, quaiDepart, quaiArrivee);
    }

    @Override
    public Reservation getReservation(Long idReservation) {
        return this.reservationFacade.getReservation(idReservation);
    }

    @Override
    public Reservation effectuerReservation(String dateDepart, Usager usager, Station stationDepart, Station stationArrivee, int nbPassagers) throws ParseException, PasDeNavetteAQuaiException, RevisionNavetteException, TrajetInexistantException, CapaciteNavetteInsuffisanteException, PasDeQuaiDispoException, StationInexistanteException, UsagerInexistantException, NbPassagersNonAutoriseException, ReservationInexistanteException, ReservationDejaExistanteException, AucuneReservationException {
        //controle des params fourni
        if (nbPassagers <= 0 || nbPassagers > 15) {
            throw new NbPassagersNonAutoriseException();
        }
        if (usager == null || this.usagerFacade.find(usager.getId()) == null) {
            throw new UsagerInexistantException();
        }
        if (stationDepart == null || this.stationFacade.find(stationDepart.getId()) == null) {
            throw new StationInexistanteException("DEPART");
        }
        if (stationArrivee == null || this.stationFacade.find(stationArrivee.getId()) == null) {
            throw new StationInexistanteException("ARRIVEE");
        }
        if (this.reservationExiste(usager.getId()) && EtatTrajet.VOYAGE_INITIE.equals(this.gestionTrajet.recupererTrajet(usager.getId()).getEtatTrajet())) {
            throw new ReservationDejaExistanteException();
        }
        if (stationDepart.getListeNavettes().isEmpty()) {
            throw new PasDeNavetteAQuaiException();
        }
        Reservation res = null;
        int n = 0;
        //vérifier qu'un quai soit dispo
        if (!this.quaiFacade.getQuaisDispo(stationDepart.getId()).isEmpty()
                && !this.quaiFacade.getQuaisDispo(stationArrivee.getId()).isEmpty()) {
            System.out.println("étape quais");
            //récupérer le premier résultat de la liste
            Quai quaiDepart = this.quaiFacade.getQuaisDispo(stationDepart.getId()).get(n);
            Quai quaiArrivee = this.quaiFacade.getQuaisDispo(stationArrivee.getId()).get(n);
            //pour chaque navette de la station
            for (Navette navette : stationDepart.getListeNavettes()) {
                System.out.println("étape navette");
                //vérifier la capacité de la navette
                if (navette.isEstDispo() && !navette.isEstEnRevision() && navette.getCapacite() >= nbPassagers && navette.getNbVoyages() < 3) {
                    System.out.println("étape navette dispo");
                    navette.incrementerNbVoyages();
                    navette.setEstDispo(false);
                    this.navetteFacade.edit(navette);
                    SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
                    Date date1 = formatter1.parse(dateDepart);
                    LocalDate date = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate dateArrivee = date.plusDays(this.gestionDuree.calculerDuree(stationDepart, stationArrivee));
                    Date date2 = Date.from(dateArrivee.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    System.out.println("DATE DEPART " + date1);
                    System.out.println("DATE ARRIVEE " + date2);
                    res = this.reservationFacade.creerReservation(nbPassagers, date1, date2, navette, usager, stationDepart, stationArrivee, quaiDepart, quaiArrivee);
                    Trajet t = this.trajetFacade.creerTrajet(nbPassagers, EtatTrajet.VOYAGE_INITIE, stationDepart, stationArrivee, quaiDepart, quaiArrivee, usager);
                    t.setDateDepart(date1);
                    this.operationFacade.creerOperation(new Date(), navette);
                    return res;
                }
            }
            n++;
        } else {
            //affichage des erreurs
            if (this.quaiFacade.getQuaisDispo(stationDepart.getId()).isEmpty()) {
                throw new PasDeQuaiDispoException("DEPART");
            }
            if (this.quaiFacade.getQuaisDispo(stationArrivee.getId()).isEmpty()) {
                throw new PasDeQuaiDispoException("ARRIVEE");
            }
        }
        if (res == null) {
            throw new PasDeNavetteAQuaiException();
        }
        return res;
    }

    @Override
    public Reservation controlerReservation(Long idUtilisateur) throws ReservationInexistanteException, AucuneReservationException {
        return this.reservationFacade.controlerReservation(idUtilisateur);
    }

    @Override
    public boolean reservationExiste(Long idUtilisateur) {
        return this.reservationFacade.reservationExiste(idUtilisateur);
    }

    @Override
    public boolean annulerReservation(Usager usager, Long idReservation) throws ParseException, TrajetDejaAcheveException, TrajetInexistantException, DelaiAnnulationResDepasseException, UsagerInexistantException, AucuneReservationException {
        if (usager == null || this.usagerFacade.find(usager.getId()) == null) {
            throw new UsagerInexistantException();
        }
        //si réservation n'existe pas
        //ou si réservation ne correspond pas à cet usager
        if (idReservation == null || getReservation(idReservation) == null || !this.reservationExiste(usager.getId())) {
            throw new AucuneReservationException();
        }
        int nbJours = new Date().compareTo(getReservation(idReservation).getDateDepart());
        if (nbJours > 0) {
            throw new DelaiAnnulationResDepasseException();
        }
        Reservation res = getReservation(idReservation);
        if (EtatTrajet.VOYAGE_INITIE.equals(this.gestionTrajet.recupererTrajet(usager.getId()).getEtatTrajet())) {
            //décrémenter le nb de voyages de la navette
            res.getNavette().setNbVoyages(res.getNavette().getNbVoyages() - 1);
            //supprimer la réservation
            this.reservationFacade.remove(res);
            //supprimer le trajet
            Trajet trajet = this.gestionTrajet.recupererTrajet(usager.getId());
            this.trajetFacade.remove(trajet);
            return true;
        } else if (EtatTrajet.VOYAGE_ACHEVE.equals(this.gestionTrajet.recupererTrajet(usager.getId()).getEtatTrajet())) {
            throw new TrajetDejaAcheveException();
        }
        return false;
    }

    @Override
    public boolean cloturerReservation(Long idUtilisateur, Long idReservation) throws TrajetDejaAcheveException, TrajetInexistantException, UsagerInexistantException, AucuneReservationException {
        if (idUtilisateur == null || this.usagerFacade.find(idUtilisateur) == null) {
            throw new UsagerInexistantException();
        }
        //si réservation n'existe pas
        //ou si réservation ne correspond pas à cet usager
        if (idReservation == null || getReservation(idReservation) == null || !this.reservationExiste(idUtilisateur)) {
            throw new AucuneReservationException();
        }
        if (EtatTrajet.VOYAGE_INITIE.equals(this.gestionTrajet.recupererTrajet(idUtilisateur).getEtatTrajet())) {
            this.gestionTrajet.recupererTrajet(idUtilisateur).setEtatTrajet(EtatTrajet.VOYAGE_ACHEVE);
            return true;
        } else if (EtatTrajet.VOYAGE_ACHEVE.equals(this.gestionTrajet.recupererTrajet(idUtilisateur).getEtatTrajet())) {
            throw new TrajetDejaAcheveException();
        }
        return false;
    }

    @Override
    public void supprimerReservationsNonCloturees() {
        //pour chaque trajet
        for (Trajet trajet : this.gestionTrajet.getAllTrajets()) {
            //si trajet initié mais date dépassée
            if (EtatTrajet.VOYAGE_INITIE.equals(trajet.getEtatTrajet()) && new Date().compareTo(trajet.getDateDepart()) > 0) {
                //parcourir les réservations
                for (Reservation res : this.reservationFacade.findAll()) {
                    //si l'usager inscrit sur la navette et dans la résa est le même
                    if (res.getUsager().getId().equals(trajet.getUtilisateur().getId())) {
                        trajet.getQuaiDepart().setEstLibre(false);
                        trajet.getQuaiArrivee().setEstLibre(true);
                        //supprimer
                        this.reservationFacade.remove(res);
                        this.trajetFacade.remove(trajet);
                    }
                }
            }
        }
    }

    @Override
    public List<Reservation> getAllReservations() {
        return this.reservationFacade.findAll();
    }

}
