/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dbAccess.ConnectTo;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Henintsoa & Hery
 */
public class Contrat {
    private int id;
    private int typecontrat;
    private double salairebrut;
    private Date debutcontrat;
    private Date datefincontrait;
    private Date datedebutessai;
    private Date datefinessai;
    private int embauche;
    private int status;
    private String matricule;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypecontrat() {
        return typecontrat;
    }

    public void setTypecontrat(int typecontrat) {
        this.typecontrat = typecontrat;
    }

    public double getSalairebrut() {
        return salairebrut;
    }

    public void setSalairebrut(double salairebrut) {
        this.salairebrut = salairebrut;
    }

    public Date getDebutcontrat() {
        return debutcontrat;
    }

    public void setDebutcontrat(Date debutcontrat) {
        this.debutcontrat = debutcontrat;
    }

    public Date getDatefincontrait() {
        return datefincontrait;
    }

    public void setDatefincontrait(Date datefincontrait) {
        this.datefincontrait = datefincontrait;
    }

    public Date getDatedebutessai() {
        return datedebutessai;
    }

    public void setDatedebutessai(Date datedebutessai) {
        this.datedebutessai = datedebutessai;
    }

    public Date getDatefinessai() {
        return datefinessai;
    }

    public void setDatefinessai(Date datefinessai) {
        this.datefinessai = datefinessai;
    }

    public int getEmbauche() {
        return embauche;
    }

    public void setEmbauche(int embauche) {
        this.embauche = embauche;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
    
    public Contrat(){
        
    }

    public Contrat(int id, int typecontrat, double salairebrut, Date debutcontrat, Date datefincontrait, Date datedebutessai, Date datefinessai, int embauche, int status, String matricule) {
        this.setId(id);
        this.setTypecontrat(typecontrat);
        this.setSalairebrut(salairebrut);
        this.setDebutcontrat(debutcontrat);
        this.setDatefincontrait(datefincontrait);
        this.setDatedebutessai(datedebutessai);
        this.setDatefinessai(datefinessai);
        this.setEmbauche(embauche);
        this.setStatus(status);
        this.setMatricule(matricule);
    }
    
    
    public static void insertContratEssai(int typecontrat, double salairebrut,Date datedebutessai, Date datefinessai, int embauche, int status, String matricule)throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "insert into contrat(typecontrat,salairebrut,debutcontrat,datefincontrat,datedebutessai,datefinessai,embauche,status,matricule)values(?,?,?,?,?,?,?,?,?,?)";
        try {
            connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nom);
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
    
    public static void updateStatusContrat(int id,int status)throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "update contrat set status=? where id = ?";
        try {
            connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, status);
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
    
    
}
