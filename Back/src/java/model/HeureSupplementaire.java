/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dbAccess.ConnectTo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author Henintsoa & Hery
 */
public class HeureSupplementaire {
    private int id;
    private int personne;
    private Timestamp dateDebut;
    private Timestamp dateFin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonne() {
        return personne;
    }

    public void setPersonne(int personne) {
        this.personne = personne;
    }

    public Timestamp getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Timestamp dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Timestamp getDateFin() {
        return dateFin;
    }

    public void setDateFin(Timestamp dateFin) {
        this.dateFin = dateFin;
    }

    public HeureSupplementaire(int id, int personne, Timestamp dateDebut, Timestamp dateFin) {
        this.id = id;
        this.personne = personne;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public HeureSupplementaire() {
        
    }
    
    public static void insertHeureSupplementaire(int personne, Timestamp dateDebut, Timestamp dateFin)throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "insert into heuresupplementaire(personne,datedebut,datefin)values(?,?,?)";
        try {
            connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,personne);
            preparedStatement.setTimestamp(2,dateDebut);
            preparedStatement.setTimestamp(3,dateFin);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {//2860
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
