/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import aris.bdd.generic.GenericDAO;
import dbAccess.ConnectTo;
import generalisationIante.BDD;
import dbAccess.ConnectTo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public static ArrayList<PosteService> getPosteServiceByService(int service) throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<PosteService> allPosteServices = new ArrayList<>();
        String query = "SELECT * FROM posteservice WHERE service = ?";

        try {
            connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,service);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                PosteService posteService = new PosteService();
                posteService.setId(resultSet.getInt(1));
                posteService.setService(resultSet.getInt(2));
                posteService.setTitreposte(resultSet.getString(3));
                allPosteServices.add(posteService);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return allPosteServices;
    }
    
    public static PosteService getPosteServiceById(int id) throws Exception {
        Connection c = ConnectTo.postgreS();
        
        GenericDAO posteServiceDAO = new GenericDAO();
        posteServiceDAO.setCurrentClass(PosteService.class);
        posteServiceDAO.addToSelection("id", id, "");
        
        ArrayList<PosteService> postes = posteServiceDAO.getFromDatabase(c);
        
        c.close();
        
        return postes.isEmpty() ? null : postes.get(0);
    }
}
