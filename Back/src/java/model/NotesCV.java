/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import generalisationIante.BDD;
import java.util.ArrayList;

/**
 *
 * @author BEST
 */
public class NotesCV extends BDD
{
    int id;
    int cv;
    int critere;
    int besoin;
    int coefficient;
    int sousCritere;
    double note;
    int critereBesoin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCv() {
        return cv;
    }

    public void setCv(int cv) {
        this.cv = cv;
    }

    public int getCritere() {
        return critere;
    }

    public void setCritere(int critere) {
        this.critere = critere;
    }

    public int getBesoin() {
        return besoin;
    }

    public void setBesoin(int besoin) {
        this.besoin = besoin;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public int getSousCritere() {
        return sousCritere;
    }

    public void setSousCritere(int sousCritere) {
        this.sousCritere = sousCritere;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public int getCritereBesoin() {
        return critereBesoin;
    }

    public void setCritereBesoin(int critereBesoin) {
        this.critereBesoin = critereBesoin;
    }
    
public ArrayList<NotesCV> getNoteCV()
{
    NotesCV notesCV= new NotesCV();
    ArrayList<String[]>allNoteCVBDD=notesCV.select();
    ArrayList<NotesCV> notesCVs=new ArrayList<>();
    for(int i=0;i<allNoteCVBDD.size();i++)
    {
      NotesCV n=new NotesCV();
        n.setId(Integer.parseInt(allNoteCVBDD.get(id)[0]));
        n.setCv(Integer.parseInt(allNoteCVBDD.get(id)[1]));
        n.setCritere(Integer.parseInt(allNoteCVBDD.get(id)[2]));
        n.setBesoin(Integer.parseInt(allNoteCVBDD.get(id)[3]));
        n.setCoefficient(Integer.parseInt(allNoteCVBDD.get(id)[4]));
        n.setSousCritere(Integer.parseInt(allNoteCVBDD.get(id)[5]));
        n.setNote(Double.parseDouble(allNoteCVBDD.get(id)[6]));
        n.setCritereBesoin(Integer.parseInt(allNoteCVBDD.get(id)[7]));
      notesCVs.add(n);
    }
    return notesCVs;
} 

public ArrayList<NotesCV> getNoteClient(int idC)
{
    String condition="idC="+idC;
    NotesCV notesCV= new NotesCV();
    ArrayList<String[]>allNoteCVBDD=notesCV.select(condition);
    ArrayList<NotesCV> notesCVs=new ArrayList<>();
    for(int i=0;i<allNoteCVBDD.size();i++)
    {
      NotesCV n=new NotesCV();
        n.setId(Integer.parseInt(allNoteCVBDD.get(id)[0]));
        n.setCv(Integer.parseInt(allNoteCVBDD.get(id)[1]));
        n.setCritere(Integer.parseInt(allNoteCVBDD.get(id)[2]));
        n.setBesoin(Integer.parseInt(allNoteCVBDD.get(id)[3]));
        n.setCoefficient(Integer.parseInt(allNoteCVBDD.get(id)[4]));
        n.setSousCritere(Integer.parseInt(allNoteCVBDD.get(id)[5]));
        n.setNote(Double.parseDouble(allNoteCVBDD.get(id)[6]));
        n.setCritereBesoin(Integer.parseInt(allNoteCVBDD.get(id)[7]));
      notesCVs.add(n);
    }
    return notesCVs;
} 

//=>Fonction calcul de note d'un cv -> Liaison table reponseCv aux tables criterebesoin et notesouscritere
//view:Create view CritereBesoin as ("select * from reponseCV JOIN cv ON cv.id= reponseCV.cv
//JOIN noteSOUCRITERE ON NoteSoucritere.besoin=cv.besoin JOIN critereBesoin ON CritereBesoin.besoin =NoteSousCritere.besoin 
//WHERE NoteSousCritere.sousCritere=reponseCV.SousCritere AND critereBesoin.Critere=ReponseCV.Critere);
}
