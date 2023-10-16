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
public class ReponseQuestionnaire {
    private int id;
    private int questionnaire;
    private String reponse;
    private int cv;

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

    public int getCv() {
        return cv;
    }

    public void setCv(int cv) {
        this.cv = cv;
    }

    public ReponseQuestionnaire() {
    }

    public ReponseQuestionnaire(int id, int questionnaire, String reponse, int cv) {
        this.setId(id);
        this.setQuestionnaire(questionnaire);
        this.setReponse(reponse);
        this.setCv(cv);
    }
    
    public static void ajoutReponseQuestionnaire(int questionnaire, int reponse, int cv)throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "insert into reponsequestionnaire(questionnaire,reponse,cv)values(?,?,?)";
        try {
            connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,questionnaire);
            preparedStatement.setInt(2,reponse);
            preparedStatement.setInt(3,cv);
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
