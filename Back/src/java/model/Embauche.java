/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import aris.bdd.generic.GenericDAO;
import dataModels.CVData;
import dataModels.ProfilData;
import dbAccess.ConnectTo;
import generalisationIante.BDD;
import java.sql.Connection;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Henintsoa & Hery
 */
public class Embauche extends BDD{
    private int id;
    private Timestamp dateEmbauche;
    private int cv;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(Timestamp dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public int getCv() {
        return cv;
    }

    public void setCv(int cv) {
        this.cv = cv;
    }
    
    public Embauche(){
        
    }
    
    public Embauche(int id, Timestamp dateEmbauche, int cv){
       this.setId(id);
       this.setDateEmbauche(dateEmbauche);
       this.setCv(cv);
    }
    
    public static Embauche getEmbaucheCV(int cv) throws Exception {
        Connection c = ConnectTo.postgreS();
        
        GenericDAO embauche = new GenericDAO();
        embauche.setCurrentClass(Embauche.class);
        embauche.addToSelection("cv", cv, "");
        
        ArrayList<Embauche> embauches = embauche.getFromDatabase(c);
        
        c.close();
        
        return embauches.isEmpty() ? null : embauches.get(0);
        
    }
    
    public static void embaucherCV(int cv) throws Exception {
            Connection c = ConnectTo.postgreS();
            
            // Mettre a jour le cv de la personne
            CV cvInstance = CV.getCVById(cv);
            
            // Check si la personne est deja en cours
            CV enCours = Personne.estPersonnel(cvInstance.getPersonne());
            if (enCours != null) throw new Exception("Personne deja embaucher");
            
            if (cvInstance.getStatus() == CVData.EMBAUCHER) throw new Exception("Personne deja embaucher");
            else {
                // 
                GenericDAO cvUpdater = new GenericDAO();
                cvUpdater.setCurrentClass(CV.class);
                cvUpdater.addToSelection("id", cv, "");
                cvUpdater.addToSetUpdate("status", CVData.EMBAUCHER);
                cvUpdater.updateInDatabase(c);
                
                // Update personne
                GenericDAO profilUpdater = new GenericDAO();
                profilUpdater.setCurrentClass(Personne.class);
                profilUpdater.addToSelection("id", cvInstance.getPersonne(), "");
                profilUpdater.addToSetUpdate("profil", ProfilData.PERSONNEL_PROFIL);
                profilUpdater.updateInDatabase(c);
            }
            
            Embauche embauche = new Embauche();
            embauche.setCv(cv);
            embauche.setDateEmbauche(new Timestamp(System.currentTimeMillis()));
            embauche.dontSave("id");

            embauche.save();
            
            c.close();
        
    }
    
    public String calculerAnciennete() {
        LocalDateTime dateEmbaucheLocal = dateEmbauche.toLocalDateTime();
        LocalDateTime maintenant = LocalDateTime.now();

        Period differenceInPeriod = Period.between(dateEmbaucheLocal.toLocalDate(), maintenant.toLocalDate());
        Duration differenceInDuration = Duration.between(dateEmbaucheLocal, maintenant);

        long years = differenceInPeriod.getYears();
        long months = differenceInPeriod.getMonths();
        long days = differenceInPeriod.getDays();
        long hours = differenceInDuration.toHours() - (days * 24);

        return years + " ans, " + months + " mois, " + days + " jours, et " + hours + " heures.";
    }
}
