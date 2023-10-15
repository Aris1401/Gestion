/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Henintsoa & Hery
 */
public class FichePoste {
    private int id;
    private int cv;
    private int contrat;

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

    public int getContrat() {
        return contrat;
    }

    public void setContrat(int contrat) {
        this.contrat = contrat;
    }

    public FichePoste() {
    
    }

    public FichePoste(int id, int cv, int contrat) {
        this.setId(id);
        this.setCv(cv);
        this.setContrat(contrat);
    }    
}
