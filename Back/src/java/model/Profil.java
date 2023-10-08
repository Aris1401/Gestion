/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import aris.bdd.generic.GenericDAO;
import dbAccess.ConnectTo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    
    public static ArrayList<Profil> getAllProfiles()throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Profil> allProfils = new ArrayList<>();
        String query = "SELECT * FROM profil";
        try {
            connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Profil profil = new Profil();
                profil.setId(resultSet.getInt(1));
                profil.setNom(resultSet.getString(2));
                profil.setRang(resultSet.getInt(3));
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
        return allProfils;
    }
    
    public static Profil getProfil(int id) throws Exception {
        Connection c = ConnectTo.postgreS();
        
        GenericDAO profilDAO = new GenericDAO();
        profilDAO.setCurrentClass(Profil.class);
        profilDAO.addToSelection("id", id, "");
        return (Profil) profilDAO.getFromDatabase(c).get(0);
    }
}
