/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.spaceborne;

import fr.miage.m1.spacelibshared.interfremote.ExpoBorneLrdRemote;
import fr.miage.m1.spacelibshared.utilities.MailUsagerDejaExistantException;
import fr.miage.m1.spacelibshared.utilities.UsagerExport;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Flo
 */
public class Main {
    
    public static void main(String[] args){
        
        try {
            /*
            //accès à annuaire jndi glassfish
            Properties jNDIProperties = new Properties();
            jNDIProperties.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
            jNDIProperties.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
            jNDIProperties.setProperty("org.omg.CORBA.ORBInitialPort", "1527");
       */
            Context ctx = new InitialContext();
            //objet à contacter
            ExpoBorneLrdRemote borne = (ExpoBorneLrdRemote) ctx.lookup("java:global/Spacelib-ear/Spacelib-ejb-1.0-SNAPSHOT/ExpoBorneLrd!fr.miage.m1.spacelibshared.interfremote.ExpoBorneLrdRemote");
            
            UsagerExport usager = borne.creerUsager("Flo", "Test", "flo@flo.com", "flo");
            System.out.println("USAGER : " + usager.getPrenom() + " " + usager.getNom());
        } catch (NamingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MailUsagerDejaExistantException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
