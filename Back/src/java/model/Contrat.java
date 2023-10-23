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
import utility.Utils;

/**
 *
 * @author Henintsoa & Hery
 */
public class Contrat {
    private int id;
    private int cv;
    private int typecontrat;
    private double salairebrut;
    private Date datedebutcontrat;
    private Date datefincontrat;
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

    public int getCv() {
        return cv;
    }

    public void setCv(int cv) {
        this.cv = cv;
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

    public Date getDatedebutcontrat() {
        return datedebutcontrat;
    }

    public void setDatedebutcontrat(Date datedebutcontrat) {
        this.datedebutcontrat = datedebutcontrat;
    }

    public Date getDatefincontrat() {
        return datefincontrat;
    }

    public void setDatefincontrat(Date datefincontrat) {
        this.datefincontrat = datefincontrat;
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

    public Contrat(int id, int cv, int typecontrat, double salairebrut, Date datedebutcontrat, Date datefincontrat, Date datedebutessai, Date datefinessai, int embauche, int status, String matricule) {
        this.setId(id);
        this.setCv(cv);
        this.setTypecontrat(typecontrat);
        this.setSalairebrut(salairebrut);
        this.setDatedebutcontrat(datedebutcontrat);
        this.setDatefincontrat(datefincontrat);
        this.setDatedebutessai(datedebutessai);
        this.setDatefinessai(datefinessai);
        this.setEmbauche(embauche);
        this.setStatus(status);
        this.setMatricule(matricule);
    }
    
    
    public static void insertContratEssai(int cv, Date datedebutessai, Date datefinessai,int embauche)throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "insert into contrat(cv,typecontrat,salairebrut,datedebutcontrat,datefincontrat,datedebutessai,datefinessai,embauche,status,matricule)values(?,?,NULL,NULL,NULL,?,?,?,?,NULL)";
        try {
            connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1,cv);
            preparedStatement.setInt(2, typecontrat);
            preparedStatement.setDate(3, datedebutessai);
            preparedStatement.setDate(4, datefinessai);
            preparedStatement.setInt(5, embauche);
            preparedStatement.setInt(6, 0);

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
            preparedStatement.setInt(1, status);
            preparedStatement.setInt(2, id);
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
    
    public static void updateContratToIndetermine(int id,int typecontrat,double salairebrut)throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "update contrat set typecontrat=?,salairebrut=? where id = ?";
        try {
            connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, typecontrat);
            preparedStatement.setDouble(2, salairebrut);
            preparedStatement.setInt(3, typecontrat);
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
    
    public static void updateContratToDetermine(int id,int typecontrat,double salairebrut,Date datedebutcontrat,Date datefincontrat)throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "update contrat set typecontrat=?,salairebrut=?,datedebutcontrat=?,datefincontrat=? where id = ?";
        try {
            connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, typecontrat);
            preparedStatement.setDouble(2, salairebrut);
            preparedStatement.setDate(3, datedebutcontrat);
            preparedStatement.setDate(4, datefincontrat);
            preparedStatement.setInt(5, typecontrat);
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
    
    public static void acceptContratEssaieToIndetermine(int id,int typecontrat,double salairebrut)throws Exception{
        Contrat.acceptContratEssaieToIndetermine(id, typecontrat, salairebrut);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "update contrat set matricule=? where id = ?";
        try {
            connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, Utils.getMatricule());
            preparedStatement.setInt(2, id);
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
    
    public static void acceptContratEssaieToDetermine(int id,int typecontrat,double salairebrut,Date datedebutcontrat,Date datefincontrat)throws Exception{
        Contrat.acceptContratEssaieToDetermine(id, typecontrat, salairebrut, datedebutcontrat, datefincontrat);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "update contrat set matricule=? where id = ?";
        try {
            connection = ConnectTo.postgreS();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, Utils.getMatricule());
            preparedStatement.setInt(2, id);
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
    
    public static int getStatusContrat(int cv)throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try{
            connection = dbAccess.ConnectTo.postgreS();
            String sql = "SELECT status from contrat where cv = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, cv);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
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
            if (statement != null) {
                try {
                    statement.close();
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
        return 9999;
    }
}
