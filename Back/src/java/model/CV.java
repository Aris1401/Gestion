/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author BEST
 */
public class CV
{
    	int id;
	String nom ;
	String prenom ;
	String adresse ;
	String email ;
	int contact ;
	String description ;
	Date dateNaissance ;
	String diplomeFichier ;
	String preuveTravailFichier ;
	int besoin ;

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getDiplomeFichier() {
        return diplomeFichier;
    }

    public void setDiplomeFichier(String diplomeFichier) {
        this.diplomeFichier = diplomeFichier;
    }

    public String getPreuveTravailFichier() {
        return preuveTravailFichier;
    }

    public void setPreuveTravailFichier(String preuveTravailFichier) {
        this.preuveTravailFichier = preuveTravailFichier;
    }

    public int getBesoin() {
        return besoin;
    }

    public void setBesoin(int besoin) {
        this.besoin = besoin;
    }
        
        
}
