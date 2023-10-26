/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import aris.bdd.generic.GenericDAO;
import java.sql.Timestamp;

/**
 *
 * @author Henintsoa & Hery
 */
public class Entretient {
    private int id;
    private int cv;
    private Timestamp dateEntretient;
    private double dureeentretient;

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
        return dureeentretient;
    }

    public void setDureeentretient(double dureeentretient) {
        this.dureeentretient = dureeentretient;
    }

    public Entretient() {
        
    }   

    public Entretient(int id, int cv, Timestamp dateEntretient, double dureeentretient) {
        this.setId(id);
        this.setCv(cv);
        this.setDateEntretient(dateEntretient);
        this.setDureeentretient(dureeentretient);
    }
    
    public static void insertEntretient(Timestamp dateEntretient, double dureeEntretient) {
        GenericDAO entretienDAO = new GenericDAO();
        entretienDAO.setCurrentClass(Entretient.class);
        
    }
}
