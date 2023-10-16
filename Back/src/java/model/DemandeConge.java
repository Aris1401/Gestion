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
public class DemandeConge {
    private int id;
    private int motif;
    private Timestamp datedebut;
    private Timestamp datefin;
    private String description;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMotif() {
        return motif;
    }

    public void setMotif(int motif) {
        this.motif = motif;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DemandeConge() {
        
    }

    public DemandeConge(int id, int motif, Timestamp datedebut, Timestamp datefin, String description, int status) {
        this.setId(id);
        this.setMotif(motif);
        this.setDatedebut(datedebut);
        this.setDatefin(datefin);
        this.setDescription(description);
        this.setStatus(status);
    }
        
}
