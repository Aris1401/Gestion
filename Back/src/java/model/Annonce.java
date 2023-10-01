/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Henintsoa & Hery
 */
public class Annonce {
    private String titre;
    private String description;
    private int nbrPersonne;
    private Date dateAnnonce;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbrPersonne() {
        return nbrPersonne;
    }

    public void setNbrPersonne(int nbrPersonne) {
        this.nbrPersonne = nbrPersonne;
    }

    public Date getDateAnnonce() {
        return dateAnnonce;
    }

    public void setDateAnnonce(Date dateAnnonce) {
        this.dateAnnonce = dateAnnonce;
    }

    public Annonce() {
        
    }

    public Annonce(String titre, String description, int nbrPersonne, Date dateAnnonce) {
        this.setTitre(titre);
        this.setDescription(description);
        this.setNbrPersonne(nbrPersonne);
        this.setDateAnnonce(dateAnnonce);
    }
    
    public static ArrayList<Annonce> getAnnonceDispo()throws Exception{
        ArrayList<Besoin> allBesoins = new ArrayList<>();
        ArrayList<Annonce> allAnnonces = new ArrayList<>();
        allBesoins = Besoin.getBesoinValide();
        for(int i = 0; i < allBesoins.size(); i++){
            Annonce annonce = new Annonce();
            annonce.setTitre(allBesoins.get(i).getTitre());
            annonce.setDescription(allBesoins.get(i).getDescription());
            annonce.setNbrPersonne(Besoin.getNombrePourBesoin(allBesoins.get(i).getId()));
            annonce.setDateAnnonce(allBesoins.get(i).getDateBesoin());
        }
        return allAnnonces;
    }    
}
