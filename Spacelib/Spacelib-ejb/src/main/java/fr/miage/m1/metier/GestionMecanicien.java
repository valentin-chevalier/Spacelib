/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.metier;

import fr.miage.m1.entities.Mecanicien;
import fr.miage.m1.entities.Quai;
import fr.miage.m1.entities.Station;
import fr.miage.m1.facades.MecanicienFacadeLocal;
import fr.miage.m1.facades.StationFacadeLocal;
import fr.miage.m1.utilities.MailInexistantException;
import fr.miage.m1.utilities.StationInexistanteException;
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
    public List<Quai> getAllQuais(Station station) throws StationInexistanteException{
        return station.getListeQuais();
    }
    
    @Override
    public Station getStation(Long idStation) throws StationInexistanteException{
        if (idStation == null || this.stationFacade.getStation(idStation) == null)
            throw new StationInexistanteException();
        return this.stationFacade.getStation(idStation);
    }
}
