/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dbAccess.ConnectTo;
import generalisationIante.BDD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private int contact ;
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

    public CV(int id, String nom, String prenom, String adresse, String email, int contact, String description, Date dateNaissance, String preuvediplome, String preuvetravail, int besoin, int personne, int status, Date dateecriture) {
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

    
    
    public static ArrayList<CV> getAllCVForBesoin(int besoin) throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<CV> allCV = new ArrayList<>();
        String query = "SELECT * FROM CV where besoin = ?";
        try {
            connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,besoin);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CV cv = new CV();
                cv.setId(resultSet.getInt(1));
                cv.setNom(resultSet.getString(2));
                cv.setPrenom(resultSet.getString(3));
                cv.setAdresse(resultSet.getString(4));
                cv.setEmail(resultSet.getString(5));
                cv.setContact(resultSet.getInt(6));
                cv.setDescription(resultSet.getString(7));
                cv.setDateNaissance(resultSet.getDate(8));
                cv.setPreuvediplome(resultSet.getString(9));
                cv.setPreuvetravail(resultSet.getString(10));
                cv.setBesoin(resultSet.getInt(11));
                cv.setPersonne(resultSet.getInt(12));
                cv.setStatus(resultSet.getInt(13));
                cv.setDateecriture(resultSet.getDate(14));
                allCV.add(cv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return allCV;
    }
 
//////////////////////////////////////////////////////////////////////////////////
    public void InsertCV(String nom,String prenom,String adresse,String email,int contact,String description,Date dateNaissance,String preuvediplome,String preuvetravail,int besoin,int personne,int status,Date dateecriture)
    {
        CV cv = new CV();
        cv.setId(id);
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
        cv.setStatus(status);
        cv.setDateecriture(dateecriture);
        cv.dontSave("id");
        cv.save();
    }       
////////////////////////////////////////////////////////////////////////////////

        
}
