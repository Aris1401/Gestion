/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import aris.bdd.generic.GenericDAO;
import dbAccess.ConnectTo;
import generalisationIante.BDD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author BEST
 */
public class CV extends BDD
{
    private int id;
    private String nom ;
    private String prenom ;
    private String adresse ;
    private String email ;
    private String contact ;
    private String description ;
    private Date dateNaissance ;
    private String preuvediplome;
    private String preuvetravail;
    private int besoin;
    private int personne;
    private int status ;
    private Date dateecriture;

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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
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

    public String getPreuvediplome() {
        return preuvediplome;
    }

    public void setPreuvediplome(String preuvediplome) {
        this.preuvediplome = preuvediplome;
    }

    public String getPreuvetravail() {
        return preuvetravail;
    }

    public void setPreuvetravail(String preuvetravail) {
        this.preuvetravail = preuvetravail;
    }

    public int getBesoin() {
        return besoin;
    }

    public void setBesoin(int besoin) {
        this.besoin = besoin;
    }

    public int getPersonne() {
        return personne;
    }

    public void setPersonne(int personne) {
        this.personne = personne;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDateecriture() {
        return dateecriture;
    }

    public void setDateecriture(Date dateecriture) {
        this.dateecriture = dateecriture;
    }

    public CV() {
    }

    public CV(int id, String nom, String prenom, String adresse, String email, String contact, String description, Date dateNaissance, String preuvediplome, String preuvetravail, int besoin, int personne, int status, Date dateecriture) {
        this.setId(id);
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setAdresse(adresse);
        this.setEmail(email);
        this.setContact(contact);
        this.setDescription(description);
        this.setDateNaissance(dateNaissance);
        this.setPreuvediplome(preuvediplome);
        this.setPreuvetravail(preuvetravail);
        this.setBesoin(besoin);
        this.setPersonne(personne);
        this.setStatus(status);
        this.setDateecriture(dateecriture);
    }

    public static ArrayList<CV> getCVFromBesoin(int besoin) throws Exception {
        Connection c = ConnectTo.postgreS();
        
        GenericDAO cvs = new GenericDAO();
        cvs.setCurrentClass(CV.class);
        cvs.addToSelection("besoin", besoin, "");
        return cvs.getFromDatabase(c);
    }
 
    public static CV dejaPostulter(int personne, int besoin) throws Exception {
        Connection c = ConnectTo.postgreS();
        
        GenericDAO cvDAO = new GenericDAO();
        cvDAO.setCurrentClass(CV.class);
        cvDAO.addToSelection("personne", personne, "and");
        cvDAO.addToSelection("besoin", besoin,"");
        
        ArrayList<CV> postulation = cvDAO.getFromDatabase(c);
        if (!postulation.isEmpty()) return postulation.get(0);
        
        c.close();
        
        return null;
    }
    
    public static CV reponduAuTest(int personne, int besoin) throws Exception {
        CV cv = CV.dejaPostulter(personne, besoin);
        if (cv == null) return null;
        
        // Si a deja postuler
        // Obtenir les reponses aux tests
        Connection c = ConnectTo.postgreS();
        GenericDAO reponses = new GenericDAO();
        reponses.setCurrentClass(ReponseQuestionnaire.class);
        reponses.addToSelection("cv", cv.getId(), "");
        
        ArrayList<ReponseQuestionnaire> reponsesArray = reponses.getFromDatabase(c);
        if (reponsesArray.isEmpty()) return null;
        
        return cv;
    }
    
    public static CV getCVById(int cv) throws Exception {
        Connection c = ConnectTo.postgreS();
        
        GenericDAO cvs = new GenericDAO();
        cvs.setCurrentClass(CV.class);
        cvs.addToSelection("id", cv, "");
        return cvs.getFromDatabase(c).isEmpty() ? null : (CV) cvs.getFromDatabase(c).get(0);
    }
    
//////////////////////////////////////////////////////////////////////////////////
    public void InsertCV(String nom,String prenom,String adresse,String email,String contact,String description,Date dateNaissance,String preuvediplome,String preuvetravail,int besoin,int personne)
    {
        CV cv = new CV();
        
        cv.setNom(nom);
        cv.setPrenom(prenom);
        cv.setAdresse(adresse);
        cv.setEmail(email);
        cv.setContact(contact);
        cv.setDescription(description);
        cv.setDateNaissance(dateNaissance);
        cv.setPreuvediplome(preuvediplome);
        cv.setPreuvetravail(preuvetravail);
        cv.setBesoin(besoin);
        cv.setPersonne(personne);
        cv.setStatus(1);
        cv.setDateecriture(new Date(System.currentTimeMillis()));
        cv.dontSave("id");
        cv.save();
    }       
////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return "YourClassName{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                ", description='" + description + '\'' +
                ", dateNaissance=" + (dateNaissance != null ? dateFormat.format(dateNaissance) : null) +
                ", preuvediplome='" + preuvediplome + '\'' +
                ", preuvetravail='" + preuvetravail + '\'' +
                ", besoin=" + besoin +
                ", personne=" + personne +
                ", status=" + status +
                ", dateecriture=" + (dateecriture != null ? dateFormat.format(dateecriture) : null) +
                '}';
    }

}
