/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dbAccess.ConnectTo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Henintsoa & Hery
 */
public class ChoixReponse {
    private int id;
    private int questionnaire;
    private String reponse;
    private double note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(int questionnaire) {
        this.questionnaire = questionnaire;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public ChoixReponse() {
    }

    public ChoixReponse(int id, int questionnaire, String reponse, double note) {
        this.setId(id);
        this.setQuestionnaire(questionnaire);
        this.setReponse(reponse);
        this.setNote(note);
    }
    
    public static void ajoutChoixReponse(int questionnaire, String reponse, double note)throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "insert into choixreponse(questionnaire,reponse,note)values(?,?,?)";
        try {
            connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,questionnaire);
            preparedStatement.setString(2,reponse);
            preparedStatement.setDouble(4,note);
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
