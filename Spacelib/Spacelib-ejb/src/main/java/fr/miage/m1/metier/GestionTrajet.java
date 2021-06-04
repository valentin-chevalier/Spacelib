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
import fr.miage.m1.entities.Utilisateur;
import fr.miage.m1.facades.TrajetFacadeLocal;
import fr.miage.m1.utilities.AucuneReservationException;
import fr.miage.m1.utilities.PasDeNavetteAQuaiException;
import fr.miage.m1.utilities.PasDeQuaiDispoException;
import fr.miage.m1.utilities.ReservationInexistanteException;
import fr.miage.m1.utilities.RevisionNavetteException;
import fr.miage.m1.utilities.TrajetDejaAcheveException;
import fr.miage.m1.utilities.TrajetInexistantException;
import fr.miage.m1.utilities.UsagerInexistantException;
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
public class GestionTrajet implements GestionTrajetLocal {

    @EJB
    private GestionNavetteLocal gestionNavette;

    @EJB
    private GestionOperationLocal gestionOperation;

    @EJB
    private GestionReservationLocal gestionReservation;

    @EJB
    private TrajetFacadeLocal trajetFacade;

    @Override
    public Trajet creerTrajet(int nbPassagers, EtatTrajet etatTrajet, Station stationDepart, Station stationArrivee, Quai quaiDepart, Quai quaiArrivee, Utilisateur utilisateur) throws RevisionNavetteException, PasDeQuaiDispoException, PasDeNavetteAQuaiException {
        //verifier que la station de départ peut accueillir la navette
        List<Quai> listeQuaisDepart = verifierQuaiDispo(stationDepart);
        if (listeQuaisDepart.isEmpty())
            throw new PasDeQuaiDispoException("DEPART");
        //vérifier pour la station d'arrivée 
        List<Quai> listeQuaisArrivee = verifierQuaiDispo(stationArrivee);
        if (listeQuaisArrivee.isEmpty())
            throw new PasDeQuaiDispoException("ARRIVEE");
        Trajet trajet = null;
        //pour chaque navette de la stationDepart
        for (Navette navette : stationDepart.getListeNavettes()){
            //chercher une navette
            if(navette.isEstDispo() && !navette.isEstEnRevision() && navette.getCapacite() >= nbPassagers){
                //si trouvé, créer un trajet
                this.gestionNavette.incrementerNbVoyages(navette);
                trajet = trajetFacade.creerTrajet(nbPassagers, etatTrajet, stationDepart, stationArrivee, quaiDepart, quaiArrivee, utilisateur);
                break;
            }
        }
        if (trajet == null){
            throw new PasDeNavetteAQuaiException();
        }
        return trajet;
    }

    @Override
    public Trajet getTrajet(Long idTrajet) {
        return this.trajetFacade.getTrajet(idTrajet);
    }

    @Override
    public Trajet finaliserTrajet(Usager usager) throws TrajetDejaAcheveException, TrajetInexistantException, UsagerInexistantException, RevisionNavetteException, ReservationInexistanteException, AucuneReservationException{
        if (usager == null)
            throw new UsagerInexistantException();
        Trajet trajet = this.trajetFacade.recupererTrajet(usager.getId());
        //verifier que le trajet n'ait pas déjà été achevé
        if (EtatTrajet.VOYAGE_INITIE.equals(trajetFacade.recupererTrajet(usager.getId()).getEtatTrajet())){
            trajet.setEtatTrajet(EtatTrajet.VOYAGE_ACHEVE);
        } else if (EtatTrajet.VOYAGE_ACHEVE.equals(trajetFacade.recupererTrajet(usager.getId()).getEtatTrajet())) {
            throw new TrajetDejaAcheveException();
        }
        trajet.setDateArrivee(new Date());
        Reservation res = this.gestionReservation.controlerReservation(usager.getId());
        this.gestionOperation.creerOperation(new Date(), res.getNavette());
        this.gestionNavette.incrementerNbVoyages(res.getNavette());
        return trajet;
    }
    
    @Override
    public Trajet recupererTrajet(Long idUser) throws TrajetInexistantException{
        return this.trajetFacade.recupererTrajet(idUser);
    }
    
    @Override
    public List<Quai> verifierQuaiDispo(Station station){
        List<Quai> listeQuaisDispo = new ArrayList<>();
        for (Quai quai : station.getListeQuais()){
            if (quai.isEstLibre()){
                listeQuaisDispo.add(quai);
            }
        }
        return listeQuaisDispo;
    }
}
