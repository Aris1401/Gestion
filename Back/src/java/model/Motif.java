/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import aris.bdd.generic.GenericDAO;
import dbAccess.ConnectTo;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author aris
 */
public class Motif {
    int id;
    String nom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public static ArrayList<Motif> motifs() throws Exception {
        Connection c = ConnectTo.postgreS();
        
        GenericDAO motifDAO = new GenericDAO();
        motifDAO.setCurrentClass(Motif.class);
        
        ArrayList<Motif> motifsCongee = motifDAO.getFromDatabase(c);
        
        c.close();
        
        return motifsCongee;
    }
    
    public static Motif motifsById(int id) throws Exception {
        Connection c = ConnectTo.postgreS();
        
        GenericDAO motifDAO = new GenericDAO();
        motifDAO.setCurrentClass(Motif.class);
        motifDAO.addToSelection("id", id, "");
        
        ArrayList<Motif> motifsCongee = motifDAO.getFromDatabase(c);
        
        c.close();
        
        return motifsCongee.isEmpty() ? null : motifsCongee.get(0);
    }
}
