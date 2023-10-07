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
public class TestQuestionnaire {
    private int id;
    private int besoin;
    private String question;
    private boolean estchoixmultiple;
    private double note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBesoin() {
        return besoin;
    }

    public void setBesoin(int besoin) {
        this.besoin = besoin;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isEstchoixmultiple() {
        return estchoixmultiple;
    }

    public void setEstchoixmultiple(boolean estchoixmultiple) {
        this.estchoixmultiple = estchoixmultiple;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public TestQuestionnaire() {
    }

    public TestQuestionnaire(int id, int besoin, String question, boolean estchoixmultiple, double note) {
        this.setId(id);
        this.setBesoin(besoin);
        this.setQuestion(question);
        this.setEstchoixmultiple(estchoixmultiple);
        this.setNote(note);
    }
    
    public static void ajoutQuestionnaire(int besoin,String question,boolean estchoixmultiple,double note)throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "insert into testQuestionnaire(besoin,question,estchoixmultiple,note)values(?,?,?,?)";
        try {
            connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,besoin);
            preparedStatement.setString(2,question);
            preparedStatement.setBoolean(3,estchoixmultiple);
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
