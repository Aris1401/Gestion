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
public class ReponseFicheEvaluation {
    private int id;
    private int reponse;
    private String texteReponse;
    private int ficheposte;
    private Timestamp datereponse;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReponse() {
        return reponse;
    }

    public void setReponse(int reponse) {
        this.reponse = reponse;
    }

    public String getTexteReponse() {
        return texteReponse;
    }

    public void setTexteReponse(String texteReponse) {
        this.texteReponse = texteReponse;
    }

    public int getFicheposte() {
        return ficheposte;
    }

    public void setFicheposte(int ficheposte) {
        this.ficheposte = ficheposte;
    }

    public Timestamp getDatereponse() {
        return datereponse;
    }

    public void setDatereponse(Timestamp datereponse) {
        this.datereponse = datereponse;
    }

    public ReponseFicheEvaluation() {
    
    }

    public ReponseFicheEvaluation(int id, int reponse, String texteReponse, int ficheposte, Timestamp datereponse) {
        this.setId(id);
        this.setReponse(reponse);
        this.setTexteReponse(texteReponse);
        this.setFicheposte(ficheposte);
        this.setDatereponse(datereponse);
    }    
}
