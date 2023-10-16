/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Henintsoa & Hery
 */
public class ChoixReponseFicheEvaluation {
    private int id;
    private String reponse;
    private String question;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ChoixReponseFicheEvaluation() {
    
    }

    public ChoixReponseFicheEvaluation(int id, String reponse, String question) {
        this.setId(id);
        this.setReponse(reponse);
        this.setQuestion(question);
    }
    
    
}
