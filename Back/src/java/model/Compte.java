/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author aris
 */
public class Compte {
    Personne personne = null;
    int rang = -1;
    int profil = -1;
    String nom = "";
    String error = null;
    int valide = 0;

    public Compte() {}
    
    public Compte(Personne personne, int rang, String nom) {
        this.personne = personne;
        this.rang = rang;
        this.nom = nom;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }   

    public int getValide() {
        return valide;
    }

    public void setValide(int valide) {
        this.valide = valide;
    }

    public int getProfil() {
        return profil;
    }

    public void setProfil(int profil) {
        this.profil = profil;
    }  
    
    public Personne getPersonneInformation() {
        return personne;
    }
}
