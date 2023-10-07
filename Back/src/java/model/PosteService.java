/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import aris.bdd.generic.GenericDAO;
import dbAccess.ConnectTo;
import generalisationIante.BDD;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Henintsoa & Hery
 */
public class PosteService extends BDD{
    private int id;
    private int service;
    private String titreposte;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public String getTitreposte() {
        return titreposte;
    }

    public void setTitreposte(String titreposte) {
        this.titreposte = titreposte;
    }

    public PosteService() {
    }

    public PosteService(int id, int service, String titreposte) {
        this.setId(id);
        this.setService(service);
        this.setTitreposte(titreposte);
    }
    
    public static ArrayList<PosteService> getPosteServices() throws Exception {
        ArrayList<PosteService> postes = new ArrayList<>();
        
        // Obtention des postes venant de la base de donnees
        ArrayList<String[]> postesString = new PosteService().select();
        for (String[] posteString : postesString) {
            PosteService ps = new PosteService(Integer.parseInt(posteString[0]), Integer.parseInt(posteString[1]), posteString[2]);
            postes.add(ps);
        }
        
        return postes;
    }
    
    public static ArrayList<PosteService> getPosteServices(int service) {
        ArrayList<PosteService> postes = new ArrayList<>();
        
        // Obtention des postes venant de la base de donnees
        ArrayList<String[]> postesString = new PosteService().select(" where service = " + service);
        for (String[] posteString : postesString) {
            PosteService ps = new PosteService(Integer.parseInt(posteString[0]), Integer.parseInt(posteString[1]), posteString[2]);
            postes.add(ps);
        }
        
        return postes;
    }
}
