/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import aris.bdd.generic.GenericDAO;
import dbAccess.ConnectTo;
import generalisationIante.BDD;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Henintsoa & Hery
 */
public class ReponseCV extends BDD{
    private int id;
    private int cv;
    private int critere;
    private int souscritere;

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

    public int getSouscritere() {
        return souscritere;
    }

    public void setSouscritere(int souscritere) {
        this.souscritere = souscritere;
    }

    public ReponseCV() {
    }

    public ReponseCV(int id, int cv, int critere, int souscritere) {
        this.setId(id);
        this.setCv(cv);
        this.setCritere(critere);
        this.setSouscritere(souscritere);
    }
    
    public static void insertReponseCV(int critere, int sousCritere, int cv) {
        ReponseCV reponse = new ReponseCV();
        reponse.setCritere(critere);
        reponse.setSouscritere(sousCritere);
        reponse.setCv(cv);
        reponse.dontSave("id");
        reponse.save();
    }
    
    public static ArrayList<SousCritere> reponsesCV(int cv, int critere) throws SQLException, Exception {
        Connection c = ConnectTo.postgreS();
        
        // Obtenir les reponses cv
        GenericDAO reponsesDAO = new GenericDAO();
        reponsesDAO.setCurrentClass(ReponseCV.class);
        reponsesDAO.addToSelection("cv", cv, "and");
        reponsesDAO.addToSelection("critere", critere, "");
        
        ArrayList<ReponseCV> reponses = reponsesDAO.getFromDatabase(c);
        
        // Sous criteres
        ArrayList<SousCritere> sousCriteres = new ArrayList<>();
        
        for (ReponseCV reponse : reponses) {
            GenericDAO scDAO = new GenericDAO();
            scDAO.setCurrentClass(SousCritere.class);
            scDAO.addToSelection("id", reponse.getSouscritere(), "");
            
            ArrayList<SousCritere> res = scDAO.getFromDatabase(c);
            if (!res.isEmpty()) sousCriteres.add(res.get(0));
        }
        
        c.close();
        
        return sousCriteres;
    }
}
