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
    int service;
    int besoin;

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

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public int getBesoin() {
        return besoin;
    }

    public void setBesoin(int besoin) {
        this.besoin = besoin;
    }

    public Annonce() {
        
    }

    public Annonce(String titre, String description, int nbrPersonne, Date dateAnnonce, int besoin, int service) {
        this.setTitre(titre);
        this.setDescription(description);
        this.setNbrPersonne(nbrPersonne);
        this.setDateAnnonce(dateAnnonce);
        this.setBesoin(besoin);
        this.setService(service);
    }
    
    public static ArrayList<Annonce> getAnnonceDispo()throws Exception{
        ArrayList<Besoin> allBesoins = Besoin.getBesoinValide();
        ArrayList<Annonce> allAnnonces = new ArrayList<>();
        for(int i = 0; i < allBesoins.size(); i++){
            Annonce annonce = new Annonce();
            annonce.setTitre(allBesoins.get(i).getTitre());
            annonce.setDescription(allBesoins.get(i).getDescription());
            annonce.setNbrPersonne(Besoin.getNombrePourBesoin(allBesoins.get(i).getId()));
            annonce.setDateAnnonce(allBesoins.get(i).getDateBesoin());
            annonce.setBesoin(allBesoins.get(i).getId());
            annonce.setService(allBesoins.get(i).getService());
            allAnnonces.add(annonce);
        }
        return allAnnonces;
    }    
}
