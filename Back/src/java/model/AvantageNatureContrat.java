/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Henintsoa & Hery
 */
public class AvantageNatureContrat {
    private int id;
    private int contrat;
    private int avantagesignature;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContrat() {
        return contrat;
    }

    public void setContrat(int contrat) {
        this.contrat = contrat;
    }

    public int getAvantagesignature() {
        return avantagesignature;
    }

    public void setAvantagesignature(int avantagesignature) {
        this.avantagesignature = avantagesignature;
    }
    
    public AvantageNatureContrat(){
        
    }
    
    public AvantageNatureContrat(int id,int contrat,int avantagesignature){
        this.setId(id);
        this.setContrat(contrat);
        this.setAvantagesignature(avantagesignature);
    }
}
