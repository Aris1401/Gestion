/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import aris.bdd.generic.GenericDAO;
import dbAccess.ConnectTo;
import generalisationIante.BDD;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Henintsoa & Hery
 */
public class Entretient extends BDD{
    private int id;
    private int cv;
    private Timestamp dateEntretient;
    private double dureeEntretient;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCv() {
        return cv;
    }

    public void setCv(int cv) {
        this.cv = cv;
    }

    public Timestamp getDateEntretient() {
        return dateEntretient;
    }

    public void setDateEntretient(Timestamp dateEntretient) {
        this.dateEntretient = dateEntretient;
    }

    public double getDureeentretient() {
        return dureeEntretient;
    }

    public void setDureeEntretient(double dureeentretient) {
        this.dureeEntretient = dureeentretient;
    }

    public Entretient() {
        
    }   

    public Entretient(int id, int cv, Timestamp dateEntretient, double dureeentretient) {
        this.setId(id);
        this.setCv(cv);
        this.setDateEntretient(dateEntretient);
        this.setDureeEntretient(dureeentretient);
    }
    
    public static void insertEntretient(Timestamp dateEntretient, double dureeEntretient, int cv) {
        Entretient entretient = new Entretient();
        entretient.setCv(cv);
        entretient.setDateEntretient(dateEntretient);
        entretient.setDureeEntretient(dureeEntretient);
        entretient.dontSave("id");
        entretient.save();
    }
    
    public static Entretient obtenirEntretientPourCv(int cv) throws Exception {
        Connection c = ConnectTo.postgreS();
        
        GenericDAO entretientDAO = new GenericDAO();
        entretientDAO.setCurrentClass(Entretient.class);
        entretientDAO.addToSelection("cv", cv, "");
        
        ArrayList<Entretient> entretients = entretientDAO.getFromDatabase(c);
        
        c.close();
        
        return entretients.isEmpty() ? null : entretients.get(0);
    }
}
