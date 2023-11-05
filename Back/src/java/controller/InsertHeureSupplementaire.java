/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.sql.Timestamp;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HeureSupplementaire;

/**
 *
 * @author Henintsoa & Hery
 */
@WebServlet(name = "InsertHeureSupplementaire", urlPatterns = {"/InsertHeureSupplementaire"})
public class InsertHeureSupplementaire extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int personne = Integer.parseInt(request.getParameter("personne"));
        Timestamp dateDebut = Timestamp.valueOf(request.getParameter("dateDebut"));
        Timestamp dateFin = Timestamp.valueOf(request.getParameter("dateFin"));
        try{
            HeureSupplementaire.insertHeureSupplementaire(personne, dateDebut, dateFin);
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
