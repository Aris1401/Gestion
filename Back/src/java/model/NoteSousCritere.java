/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author BEST
 */
public class NoteSousCritere 
{
    int id;
    int sousCritere;
    double note;
    int besoin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSousCritere() {
        return sousCritere;
    }

    public void setSousCritere(int sousCritere) {
        this.sousCritere = sousCritere;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public int getBesoin() {
        return besoin;
    }

    public void setBesoin(int besoin) {
        this.besoin = besoin;
    }

    public NoteSousCritere() {
        
    }

    public NoteSousCritere(int id, int sousCritere, double note, int besoin) {
        this.id = id;
        this.sousCritere = sousCritere;
        this.note = note;
        this.besoin = besoin;
    }
    
    public static NoteSousCritere getNoteSousCritere(int sousCritere, int besoin) throws Exception {
        Connection c = ConnectTo.postgreS();
        
        GenericDAO<NoteSousCritere> noteGeneric = new GenericDAO<>();
        noteGeneric.setCurrentClass(NoteSousCritere.class);
        noteGeneric.addToSelection("sousCritere", sousCritere, "and");
        noteGeneric.addToSelection("besoin", besoin, "");
        
        ArrayList<NoteSousCritere> notes = noteGeneric.getFromDatabase(c);
        if (notes.size() <= 0) return null;
        
        return notes.get(0);
    }
    
    public static void updateNoteSousCritere(int sousCritere,int besoin,int note)throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectTo.postgreS();
            String query = "update notesouscritere set note = ? where besoin=? and souscritere =?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, note);
            preparedStatement.setInt(2, besoin);
            preparedStatement.setInt(3, sousCritere);
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
    
    public static boolean checkIfExist(int sousCritere, int besoin) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean exist = false;

        try {
            connection = ConnectTo.postgreS();
            String query = "SELECT * FROM notesouscritere WHERE souscritere = ? AND besoin = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, sousCritere);
            preparedStatement.setInt(2, besoin);
            resultSet = preparedStatement.executeQuery();

            exist = resultSet.next();
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

        return exist;
    }
    
    public static void addNoteSousCritere(int sousCritere,double note,int besoin) throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "insert into noteSousCritere(sousCritere,note,besoin)values(?,?,?)";
        try {
            connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,sousCritere);
            preparedStatement.setDouble(2,note);
            preparedStatement.setInt(3,besoin);
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
    
    public static void insertOrUpdate(int sousCritere,int besoin,int note) throws Exception{
        boolean exist = NoteSousCritere.checkIfExist(sousCritere, besoin);
        if(exist){
            NoteSousCritere.updateNoteSousCritere(sousCritere, besoin, note);
        }else{
            NoteSousCritere.addNoteSousCritere(sousCritere, note, besoin);
        }
    }
}
