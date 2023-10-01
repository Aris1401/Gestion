/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import generalisationIante.BDD;

/**
 *
 * @author BEST
 */
public class NoteCoefficient extends BDD
{ 
    int id;
    int CritereBesoin;
    int besoin;
    int critere;
    int coefficient;
    int sousCritere;
    double note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCritereBesoin() {
        return CritereBesoin;
    }

    public void setCritereBesoin(int CritereBesoin) {
        this.CritereBesoin = CritereBesoin;
    }

    public int getBesoin() {
        return besoin;
    }

    public void setBesoin(int besoin) {
        this.besoin = besoin;
    }

    public int getCritere() {
        return critere;
    }

    public void setCritere(int critere) {
        this.critere = critere;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public int getSousCritere() {
        return sousCritere;
    }

    public void setSousCritere(int sousCritere) {
        this.sousCritere = sousCritere;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }
  
    
}
