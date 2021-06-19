/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.spaceborne;

import fr.miage.m1.spacelibshared.interfremote.ExpoBorneLrdRemote;
import fr.miage.m1.spacelibshared.utilities.AucuneReservationException;
import fr.miage.m1.spacelibshared.utilities.CapaciteNavetteInsuffisanteException;
import fr.miage.m1.spacelibshared.utilities.MailUsagerDejaExistantException;
import fr.miage.m1.spacelibshared.utilities.NbPassagersNonAutoriseException;
import fr.miage.m1.spacelibshared.utilities.PasDeNavetteAQuaiException;
import fr.miage.m1.spacelibshared.utilities.PasDeQuaiDispoException;
import fr.miage.m1.spacelibshared.utilities.QuaiExport;
import fr.miage.m1.spacelibshared.utilities.ReservationDejaExistanteException;
import fr.miage.m1.spacelibshared.utilities.ReservationInexistanteException;
import fr.miage.m1.spacelibshared.utilities.RevisionNavetteException;
import fr.miage.m1.spacelibshared.utilities.StationExport;
import fr.miage.m1.spacelibshared.utilities.StationInexistanteException;
import fr.miage.m1.spacelibshared.utilities.TrajetDejaAcheveException;
import fr.miage.m1.spacelibshared.utilities.TrajetInexistantException;
import fr.miage.m1.spacelibshared.utilities.UsagerExport;
import fr.miage.m1.spacelibshared.utilities.UsagerInexistantException;
import java.text.ParseException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Flo
 */
public class Main {
    
    public static void main(String[] args) throws TrajetDejaAcheveException, NamingException, MailUsagerDejaExistantException,ParseException, PasDeNavetteAQuaiException, RevisionNavetteException, TrajetInexistantException, CapaciteNavetteInsuffisanteException, PasDeQuaiDispoException, StationInexistanteException, UsagerInexistantException, NbPassagersNonAutoriseException, ReservationInexistanteException, ReservationDejaExistanteException, AucuneReservationException, MailUsagerDejaExistantException{
        /*
            //accès à annuaire jndi glassfish
            Properties jNDIProperties = new Propertiesrties();
            jNDIProperties.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
            jNDIProperties.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
            jNDIProperties.setProperty("org.omg.CORBA.ORBInitialPort", "1527");
       */
            Context ctx = new InitialContext();
            //objet à contacter
            ExpoBorneLrdRemote borne = (ExpoBorneLrdRemote) ctx.lookup("java:global/Spacelib-ear/Spacelib-ejb-1.0-SNAPSHOT/ExpoBorneLrd!fr.miage.m1.spacelibshared.interfremote.ExpoBorneLrdRemote");
            
            //UsagerExport usager = borne.creerUsager("flo", "test", "flo@flo.com", "flo");
            UsagerExport usager = borne.getUsager(34L);
            System.out.println("USAGER " + usager.getPrenom() + " " + usager.getNom());
            StationExport stationDepart = borne.getStation(1L);
            System.out.println("STATION DEPART " + stationDepart.getNom());

            StationExport stationArrivee = borne.getStation(17L);
            System.out.println("STATION ARRIVEE " + stationArrivee.getNom());

            QuaiExport quai = borne.demanderReservation("17/06/2021", usager, stationDepart, stationArrivee, 30);
            //borne.finaliserTrajet(usager);
            System.out.println("RDV au quai " + quai.getNoQuai());
    }
    
}
