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
    private int service;

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

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }
    
    public QuestionFicheEvaluation() {
        
    }

    public QuestionFicheEvaluation(int id, String question, int ficheEvaluation, int service) {
        this.setId(id);
        this.setQuestion(question);
        this.setFicheEvaluation(ficheEvaluation);
        this.setService(service);
    }
}
