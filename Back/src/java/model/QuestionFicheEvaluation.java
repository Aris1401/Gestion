/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Henintsoa & Hery
 */
public class QuestionFicheEvaluation {
    private int id;
    private String question;
    private int ficheEvaluation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getFicheEvaluation() {
        return ficheEvaluation;
    }

    public void setFicheEvaluation(int ficheEvaluation) {
        this.ficheEvaluation = ficheEvaluation;
    }

    public QuestionFicheEvaluation() {
        
    }

    public QuestionFicheEvaluation(int id, String question, int ficheEvaluation) {
        this.setId(id);
        this.setQuestion(question);
        this.setFicheEvaluation(ficheEvaluation);
    }
}
