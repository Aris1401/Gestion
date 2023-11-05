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
}
