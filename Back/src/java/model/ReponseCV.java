/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Henintsoa & Hery
 */
public class ReponseCV {
    private int id;
    private int cv;
    private int critere;
    private int souscritere;

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

    public int getCritere() {
        return critere;
    }

    public void setCritere(int critere) {
        this.critere = critere;
    }

    public int getSouscritere() {
        return souscritere;
    }

    public void setSouscritere(int souscritere) {
        this.souscritere = souscritere;
    }

    public ReponseCV() {
    }

    public ReponseCV(int id, int cv, int critere, int souscritere) {
        this.setId(id);
        this.setCv(cv);
        this.setCritere(critere);
        this.setSouscritere(souscritere);
    }
    
    
}
