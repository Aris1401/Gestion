/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import generalisationIante.BDD;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author BEST
 */
public class NotesTest extends BDD
{
    int id;
    int reponseQuestionnaire;
    int questionnaire;
    int reponse;
    int cv;
     String nom ;
     String prenom ;
     String adresse ;
     String email ;
     int contact ;
     String description ;
     Date dateNaissance ;
     String preuvediplome;
     String preuvetravail;
     int besoin;
     int personne;
     int status ;
     Date dateecriture;
     int question;
     boolean estchoixmultiple;
     double noteTest;
     String reponseChoix;
     double noteChoisReponse;
    
    
//=>Fonction calcul note de test -> Liaison de la table reponseauestionnaire a choix reponse     

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReponseQuestionnaire() {
        return reponseQuestionnaire;
    }

    public void setReponseQuestionnaire(int reponseQuestionnaire) {
        this.reponseQuestionnaire = reponseQuestionnaire;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getPreuvediplome() {
        return preuvediplome;
    }

    public void setPreuvediplome(String preuvediplome) {
        this.preuvediplome = preuvediplome;
    }

    public String getPreuvetravail() {
        return preuvetravail;
    }

    public void setPreuvetravail(String preuvetravail) {
        this.preuvetravail = preuvetravail;
    }

    public int getBesoin() {
        return besoin;
    }

    public void setBesoin(int besoin) {
        this.besoin = besoin;
    }

    public int getPersonne() {
        return personne;
    }

    public void setPersonne(int personne) {
        this.personne = personne;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDateecriture() {
        return dateecriture;
    }

    public void setDateecriture(Date dateecriture) {
        this.dateecriture = dateecriture;
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

    public double getNoteTest() {
        return noteTest;
    }

    public void setNoteTest(double noteTest) {
        this.noteTest = noteTest;
    }

    public String getReponseChoix() {
        return reponseChoix;
    }

    public void setReponseChoix(String reponseChoix) {
        this.reponseChoix = reponseChoix;
    }

    public double getNoteChoisReponse() {
        return noteChoisReponse;
    }

    public void setNoteChoisReponse(double noteChoisReponse) {
        this.noteChoisReponse = noteChoisReponse;
    }
    
//////////////////////////////////////////////////////////////
public ArrayList<NotesTest> getNoteTets()
{
    NotesTest noteTest =new NotesTest();
    ArrayList<String[]>noteTestBDD =noteTest.select();
    ArrayList<NotesTest> allNote= new ArrayList<NotesTest>();
    for(int i=0;i<noteTestBDD.size();i++)
    {
        NotesTest n=new NotesTest();
    n.setId(Integer.parseInt(noteTestBDD.get(id)[0]));
    n.setReponseQuestionnaire(Integer.parseInt(noteTestBDD.get(id)[0]));
    n.setQuestionnaire(Integer.parseInt(noteTestBDD.get(id)[0]));
    n.setReponse(Integer.parseInt(noteTestBDD.get(id)[0]));
    n.setCv(Integer.parseInt(noteTestBDD.get(id)[0]));
    n.setNom(noteTestBDD.get(id)[0]);
    n.setPrenom(noteTestBDD.get(id)[0]);
    n.setAdresse(noteTestBDD.get(id)[0]);
    n.setEmail(noteTestBDD.get(id)[0]);
    n.setContact(Integer.parseInt(noteTestBDD.get(id)[0]));
    n.setDescription(noteTestBDD.get(id)[0]);
    n.setDateNaissance(Date.valueOf(noteTestBDD.get(id)[0]));
    n.setPreuvediplome(noteTestBDD.get(id)[0]);
    n.setPreuvetravail(noteTestBDD.get(id)[0]);
    n.setBesoin(Integer.parseInt(noteTestBDD.get(id)[0]));
    n.setPersonne(Integer.parseInt(noteTestBDD.get(id)[0]));
    n.setStatus (Integer.parseInt(noteTestBDD.get(id)[0]));
    n.setDateecriture(Date.valueOf(noteTestBDD.get(id)[0]));
    n.setQuestion(Integer.parseInt(noteTestBDD.get(id)[0]));
//    n.setEstchoixmultiple(noteTestBDD.get(id)[0]);
    n.setNoteTest(Integer.parseInt(noteTestBDD.get(id)[0]));
    n.setReponseChoix(noteTestBDD.get(id)[0]);
    n.setNoteChoisReponse(Integer.parseInt(noteTestBDD.get(id)[0]));
    allNote.add(n);
    }
   
    return allNote;
}
}
