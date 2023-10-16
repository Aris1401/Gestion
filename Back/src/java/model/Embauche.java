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
public class Embauche {
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
}
