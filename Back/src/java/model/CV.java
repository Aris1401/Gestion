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
                cv.setDiplomeFichier(resultSet.getString(9));
                cv.setPreuveTravailFichier(resultSet.getString(10));
                cv.setBesoin(resultSet.getInt(11));
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
    public void InsertCV(String nom,String prenom,String adresse,String email,String contact,
        String description,String dateNaissance,String diplomeFichier,String preuveTravailFichier,String besoin)
    {
        CV cv=new CV();
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
        cv.dontSave("id");
        cv.save();
    }       

        
}
