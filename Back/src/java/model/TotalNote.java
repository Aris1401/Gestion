/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import aris.bdd.generic.GenericDAO;
import dbAccess.ConnectTo;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author aris
 */
public class TotalNote {
    int cv;
    double total;
    int besoin;

    public int getCv() {
        return cv;
    }

    public void setCv(int cv) {
        this.cv = cv;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getBesoin() {
        return besoin;
    }

    public void setBesoin(int besoin) {
        this.besoin = besoin;
    }
    
    
    
    public static ArrayList<TotalNote> obtenirToutNotes(int besoin) throws Exception {
        // Ouverture de connexion
        Connection c = ConnectTo.postgreS();
        
        GenericDAO notesDAO = new GenericDAO();
        notesDAO.setCurrentClass(TotalNote.class);
        notesDAO.addToSelection("besoin", besoin, "");
        ArrayList<TotalNote> notes = notesDAO.getFromDatabase(c);
        
        c.close();
        
        return notes;
    }
    
    public static TotalNote obtenirNoteCanditature(int cv) throws Exception {
        // Ouverture de connection
        Connection c = ConnectTo.postgreS();
        
        GenericDAO noteDAO = new GenericDAO();
        noteDAO.setCurrentClass(TotalNote.class);
        noteDAO.addToSelection("cv", cv, "");
        ArrayList<TotalNote> notes = noteDAO.getFromDatabase(c);
        
        return notes.isEmpty() ? null : notes.get(0);
    }
}
