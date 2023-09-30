/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

<<<<<<< Updated upstream
=======
import dbAccess.ConnectTo;
import generalisationIante.BDD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

>>>>>>> Stashed changes
/**
 *
 * @author BEST
 */
<<<<<<< Updated upstream
public class CritereBesoin 
=======
public class CritereBesoin extends BDD
>>>>>>> Stashed changes
{
    	int id;
	int besoin;
	int critere;
	int coefficient;
        ///////nanao JOIN de napina Variable 
        int sousCritere;
        double note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBesoin() {
        return besoin;
    }

    public void setBesoin(int besoin) {
        this.besoin = besoin;
    }

    public int getCritere() {
        return critere;
    }

    public void setCritere(int critere) {
        this.critere = critere;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }
<<<<<<< Updated upstream
        
        
=======

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

    public CritereBesoin() {
    }

    public CritereBesoin(int id, int besoin, int critere, int coefficient) {
        this.setId(id);
        this.setBesoin(besoin);
        this.setCritere(critere);
        this.setCoefficient(coefficient);
    }
    
    public static void insererCritereBesoin(int besoin, int critere, int coefficient) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectTo.postgreS();
            String query = "INSERT INTO critere_besoin (besoin, critere, coefficient) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, besoin);
            preparedStatement.setInt(2, critere);
            preparedStatement.setInt(3, coefficient);
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
    
    public static ArrayList<CritereBesoin> getByIdBesoin(int besoinId) throws Exception {
        ArrayList<CritereBesoin> critereBesoins = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectTo.postgreS();
            String query = "SELECT * FROM critere_besoin WHERE besoin = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, besoinId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int critere = resultSet.getInt("critere");
                int coefficient = resultSet.getInt("coefficient");
                CritereBesoin critereBesoin = new CritereBesoin(id, besoinId, critere, coefficient);
                critereBesoins.add(critereBesoin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

        return critereBesoins;
    }
 //////////////////////////////////////////////////////////////////////////////////maka coeff anle client 
public double  getValeurCritere(int idCritere,int idSousCritere,boolean check)
{
    
    if(!check)
    {
        return 0;
    }
    double notee = 0;
   CritereBesoin critereBesoin =new CritereBesoin();
        String condition="JOIN NoteSousCritere ON CritereBesoin.idCritere=SousCritere.CritereBesoin WHERE CritereBesoin.critere = "
                + idCritere + "AND NoteSousCritere.sousCritere = " +idSousCritere +"";
       
        ArrayList<String[]> critereBesoinBDD =critereBesoin.select(condition);
        ArrayList<CritereBesoin> critereBesoins =new ArrayList<>();
        
        for(int i=0;i<critereBesoinBDD.size();i++)
        {
            CritereBesoin c =new CritereBesoin();
            c.id= Integer.parseInt(critereBesoinBDD.get(id)[0]);
            c.besoin=Integer.parseInt(critereBesoinBDD.get(id)[1]);
            c.critere=Integer.parseInt(critereBesoinBDD.get(id)[2]);
            c.coefficient=Integer.parseInt(critereBesoinBDD.get(id)[3]);
            c.sousCritere=Integer.parseInt(critereBesoinBDD.get(id)[4]);
            c.note=Double.parseDouble(critereBesoinBDD.get(id)[5]);
            notee=c.coefficient*c.note;
            critereBesoins.add(c);
        }
        
        
        return notee;
}
/////////////////////////////////////////////////////////////////////////////////  

>>>>>>> Stashed changes
}
