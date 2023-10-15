/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Henintsoa & Hery
 */
public class ResponsabilitePoste {
   private int id;
   private int personne;
   private int ficheposte;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonne() {
        return personne;
    }

    public void setPersonne(int personne) {
        this.personne = personne;
    }

    public int getFicheposte() {
        return ficheposte;
    }

    public void setFicheposte(int ficheposte) {
        this.ficheposte = ficheposte;
    }
    
    public ResponsabilitePoste(){
        
    }
    
    public ResponsabilitePoste(int personne,int ficheposte){
        this.setPersonne(personne);
        this.setFicheposte(ficheposte);
    }
}
