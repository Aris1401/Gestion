/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Henintsoa & Hery
 */
public class TestQuestionnaire {
    private int id;
    private int besoin;
    private int question;
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

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
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

    public TestQuestionnaire(int id, int besoin, int question, boolean estchoixmultiple, double note) {
        this.setId(id);
        this.setBesoin(besoin);
        this.setQuestion(question);
        this.setEstchoixmultiple(estchoixmultiple);
        this.setNote(note);
    }
    
}
