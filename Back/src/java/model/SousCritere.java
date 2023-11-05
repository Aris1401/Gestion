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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author BEST
 */
public class SousCritere extends BDD
{
    	int id;
        int critere;
	String nom ;	

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

    public int getCritere() {
        return critere;
    }

    public void setCritere(int critere) {
        this.critere = critere;
    }
    
    public SousCritere(){
        
    }

    public SousCritere(int id, String nom, int critere) {
        this.setId(id);
        this.setNom(nom);
        this.setCritere(critere);
    }
    
    public static ArrayList<SousCritere> getAllSousCriteresByCritere(int critere) throws Exception{
        ArrayList<SousCritere> sousCriteres = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM sousCritere where critere = ?";
        try {
            connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,critere);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SousCritere sousCritere = new SousCritere();
                sousCritere.setId(resultSet.getInt(1));

                sousCritere.setNom(resultSet.getString(3));
                sousCritere.setCritere(resultSet.getInt(2));
                sousCriteres.add(sousCritere);
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
        return sousCriteres;
    }
    
    public static void insertSousCritere(String nom, int critere) {
        SousCritere sousCritere = new SousCritere();
        sousCritere.setNom(nom);
        sousCritere.setCritere(critere);
        
        sousCritere.dontSave("id");
        sousCritere.save();
    }
    
    public static void modifierSousCritere(int id, String nom) throws Exception {
        Connection c = ConnectTo.postgreS();
        
        GenericDAO sousCritere = new GenericDAO();
        sousCritere.setCurrentClass(SousCritere.class);
        sousCritere.addToSetUpdate("nom", nom);
        sousCritere.addToSelection("id", id, "");
        sousCritere.updateInDatabase(c);
        
        c.close();
    }
    
    public static void deleteSousCritere(int id) throws Exception {
        Connection c = ConnectTo.postgreS();
        
        GenericDAO sousCritere = new GenericDAO();
        sousCritere.setCurrentClass(SousCritere.class);
        sousCritere.addToSelection("id", id, "");
        sousCritere.deleteFromDatabase(c);
        
        c.close();
    }
}
