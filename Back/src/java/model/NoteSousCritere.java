/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dbAccess.ConnectTo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author BEST
 */
public class NoteSousCritere 
{
    int id;
    int sousCritere;
    double note;
    int critereBesoin;

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

    public int getCritereBesoin() {
        return critereBesoin;
    }

    public void setCritereBesoin(int critereBesoin) {
        this.critereBesoin = critereBesoin;
    }

    public NoteSousCritere() {
        
    }

    public NoteSousCritere(int id, int sousCritere, double note, int critereBesoin) {
        this.id = id;
        this.sousCritere = sousCritere;
        this.note = note;
        this.critereBesoin = critereBesoin;
    }
    
    public static void addNoteSousCritere(int sousCritere,double note,int critereBesoin) throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "insert into noteSousCritere(sousCritere,note,critereBesoin)values(?,?,?)";
        try {
            connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,sousCritere);
            preparedStatement.setDouble(2,note);
            preparedStatement.setInt(3,critereBesoin);
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
