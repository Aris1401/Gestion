/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import generalisationIante.BDD;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author BEST
 */
public class CV extends BDD
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
 
//////////////////////////////////////////////////////////////////////////////////
public void InsertCV(String id,String nom,String prenom,String adresse,String email,String contact,
        String description,String dateNaissance,String diplomeFichier,String preuveTravailFichier,String besoin)
{
        CV cv=new CV();
        int idC =Integer.parseInt(id);
	String nomC =nom  ;
	String prenomC =prenom;
	String adresseC =adresse ;
	String emailC =email;
	int contactC =Integer.parseInt(contact);
	String descriptionC =description ;
	Date dateNaissanceC =Date.valueOf(dateNaissance);
	String diplomeFichierC= diplomeFichier;
	String preuveTravailFichierC =preuveTravailFichier;
	int besoinC =Integer.parseInt(besoin) ;
        
        cv.save();
}       

        
}
