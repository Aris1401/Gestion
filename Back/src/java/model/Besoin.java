/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dbAccess.ConnectTo;
import generalisationIante.BDD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author BEST
 */
public class Besoin extends BDD
{
    	private int id;
	private int service ;
	private String  description;
	private String titre ;
        private int posteService;
	private double volumeTaches;
	private double tauxJourHomme ;
	private Date dateBesoin;
	private Date dateFin;
        private int status;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getPosteService() {
        return posteService;
    }

    public void setPosteService(int posteService) {
        this.posteService = posteService;
    }
    

    public double getVolumeTaches() {
        return volumeTaches;
    }

    public void setVolumeTaches(double volumeTaches) {
        this.volumeTaches = volumeTaches;
    }

    public double getTauxJourHomme() {
        return tauxJourHomme;
    }

    public void setTauxJourHomme(double tauxJourHomme) {
        this.tauxJourHomme = tauxJourHomme;
    }

    public Date getDateBesoin() {
        return dateBesoin;
    }

    public void setDateBesoin(Date dateBesoin) {
        this.dateBesoin = dateBesoin;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Besoin() {
    }

    public Besoin(int id, int service, String description, String titre, int posteService, double volumeTaches, double tauxJourHomme, Date dateBesoin, Date dateFin, int status) {
        this.setId(id);
        this.setService(service);
        this.setDescription(description);
        this.setTitre(titre);
        this.setPosteService(posteService);
        this.setVolumeTaches(volumeTaches);
        this.setTauxJourHomme(tauxJourHomme);
        this.setDateBesoin(dateBesoin);
        this.setDateFin(dateFin);
        this.setStatus(status);
    }
    
    
    
    
//////////////////////////////////////////////////////////////       
    public static ArrayList<Besoin> allBesoin()
   {
      Besoin besoin = new Besoin();
      ArrayList<String[]> besoinsBDD =besoin.select();
      ArrayList<Besoin> besoins= new ArrayList<>();
      for(int i=0;i< besoinsBDD.size();i++)
      {
          Besoin b = new Besoin();
          b.setId(Integer.parseInt(besoinsBDD.get(i)[0]));
          b.setService(Integer.parseInt(besoinsBDD.get(i)[1]));
          b.setDescription(String.valueOf(besoinsBDD.get(i)[2]));
          b.setTitre(String.valueOf(besoinsBDD.get(i)[3]));
          b.setPosteService(Integer.parseInt(besoinsBDD.get(i)[4]));
          b.setVolumeTaches(Double.parseDouble(besoinsBDD.get(i)[5]));
          b.setTauxJourHomme(Double.parseDouble(besoinsBDD.get(i)[6]));
          b.setDateBesoin(Date.valueOf(besoinsBDD.get(i)[7]));
          b.setDateFin(Date.valueOf(besoinsBDD.get(i)[8]));
          b.setStatus(Integer.parseInt(besoinsBDD.get(i)[9]));

        besoins.add(b);
      }
      return besoins;  
   } 
///////////////////////////////////////////////////////////////////   
    
    public static void createBesoin(int service, String description, String titre,int posteService,double volumeTaches, double tauxJourHomme, Date dateBesoin, Date dateFin) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectTo.postgreS();
            String query = "INSERT INTO besoin (service, description, titre, posteService, volumeTaches, tauxJourHomme, dateBesoin, dateFin,status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, service);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, titre);
            preparedStatement.setInt(4, posteService);
            preparedStatement.setDouble(5, volumeTaches);
            preparedStatement.setDouble(6, tauxJourHomme);
            preparedStatement.setDate(7, dateBesoin);
            preparedStatement.setDate(8, dateFin);
            preparedStatement.setInt(9, 10);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
    }
   
    public static void createBesoinToday(int service, String description, String titre, int posteService, double volumeTaches, double tauxJourHomme, Date dateFin) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectTo.postgreS();
            String query = "INSERT INTO besoin (service, description, titre, posteService, volumeTaches, tauxJourHomme, dateBesoin, dateFin,status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, service);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, titre);
            preparedStatement.setInt(4, posteService);
            preparedStatement.setDouble(5, volumeTaches);
            preparedStatement.setDouble(6, tauxJourHomme);
            Calendar calendar = Calendar.getInstance();
            Date dateBesoin = new Date(calendar.getTime().getTime());
            preparedStatement.setDate(7, dateBesoin);
            preparedStatement.setDate(8, dateFin);
            preparedStatement.setInt(9, 10);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
    }

    public static Besoin getBesoinById(int id) throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Besoin besoin = new Besoin();
        String query = "SELECT * FROM besoin WHERE id = ?";

        try {
            connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                besoin.setId(resultSet.getInt(1));
                besoin.setService(resultSet.getInt(2));
                besoin.setDescription(resultSet.getString(3));
                besoin.setTitre(resultSet.getString(4));
                besoin.setPosteService(resultSet.getInt(5));
                besoin.setVolumeTaches(resultSet.getDouble(6));
                besoin.setTauxJourHomme(resultSet.getDouble(7));
                besoin.setDateBesoin(resultSet.getDate(8));
                besoin.setDateFin(resultSet.getDate(9));
                besoin.setStatus(resultSet.getInt(10));
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
        return besoin;
    }
    
    public static ArrayList<Besoin> getBesoinValide(){
        ArrayList<Besoin> allBesoinsDispo = new ArrayList<>();
        allBesoinsDispo = Besoin.allBesoin();
        for(int i = allBesoinsDispo.size()-1; i >= 0; i--){
            if(allBesoinsDispo.get(i).getStatus() != 0)allBesoinsDispo.remove(i);
        }
        return allBesoinsDispo;
    }
    
    public static int getNombrePourBesoin(int id) throws Exception{
        Besoin besoin = Besoin.getBesoinById(id);
        int nombrePersonne;
        double reponse = besoin.getVolumeTaches()/besoin.getTauxJourHomme();
        nombrePersonne = (int)reponse;
        return nombrePersonne*3;
    }

}
