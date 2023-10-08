/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dbAccess.ConnectTo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author BEST
 */
public class SousCritere
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
    
    public static void insertSousCritere(int id,String nom,int critere) throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "insert into sousCritere(critere,nom)values(?,?)";
        try {
            connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, critere);
            preparedStatement.setString(2,nom);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
}
