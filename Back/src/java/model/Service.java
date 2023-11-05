/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import aris.bdd.generic.GenericDAO;
import dbAccess.ConnectTo;
import generalisationIante.BDD;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author BEST
 */
public class Service extends BDD
{
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
 /////////////////////////////////////////////////
        public ArrayList<Service> allService()
   {
      Service service = new Service();
      ArrayList<String[]> serviceBDD =service.select();
      ArrayList<Service> services= new ArrayList<>();
      for(int i=0;i< serviceBDD.size();i++)
      {
          Service s = new Service();
          s.setId(Integer.parseInt(serviceBDD.get(i)[0]));
          s.setNom(serviceBDD.get(i)[1]);

        services.add(s);
      }
      return services;  
   } 
        
        public static Service getServiceById(int id) throws Exception {
            Connection c = ConnectTo.postgreS();
            
            GenericDAO serviceDAO = new GenericDAO();
            serviceDAO.setCurrentClass(Service.class);
            serviceDAO.addToSelection("id", id, "");
            
            ArrayList<Service> services = serviceDAO.getFromDatabase(c);
            
            c.close();
            
            return services.isEmpty() ? null : services.get(0);
        }
}
