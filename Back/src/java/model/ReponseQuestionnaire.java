/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Henintsoa & Hery
 */
public class ReponseQuestionnaire {
    private int id;
    private int questionnaire;
    private int reponse;
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

    public int getReponse() {
        return reponse;
    }

    public void setReponse(int reponse) {
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

    public ReponseQuestionnaire(int id, int questionnaire, int reponse, int cv) {
        this.setId(id);
        this.setQuestionnaire(questionnaire);
        this.setReponse(reponse);
        this.setCv(cv);
    }
    
    
    
    
}
