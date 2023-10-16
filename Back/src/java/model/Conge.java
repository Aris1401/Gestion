/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author Henintsoa & Hery
 */
public class Conge {
    private int id;
    private Timestamp datedebut;
    private Timestamp datefin;
    private int demande;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Timestamp datedebut) {
        this.datedebut = datedebut;
    }

    public Timestamp getDatefin() {
        return datefin;
    }

    public void setDatefin(Timestamp datefin) {
        this.datefin = datefin;
    }

    public int getDemande() {
        return demande;
    }

    public void setDemande(int demande) {
        this.demande = demande;
    }

    public Conge() {
        
    }

    public Conge(int id, Timestamp datedebut, Timestamp datefin, int demande) {
        this.setId(id);
        this.setDatedebut(datedebut);
        this.setDatefin(datefin);
        this.setDemande(demande);
    }
    
}
