/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Henintsoa & Hery
 */
public class Profil {
    private int id;
    private String nom;
    private int rang;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public Profil() {
    }

    public Profil(int id, String nom, int rang) {
        this.setId(id);
        this.setNom(nom);
        this.setRang(rang);
    }
    
    
}
