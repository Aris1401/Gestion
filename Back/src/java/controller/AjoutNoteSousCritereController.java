/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Besoin;
import model.NoteSousCritere;

/**
 *
 * @author Henintsoa & Hery
 */
@WebServlet(name = "AjoutNoteSousCritereController", urlPatterns = {"/AjoutNoteSousCritereController"})
public class AjoutNoteSousCritereController extends HttpServlet {
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if(request.getParameter("sousCritere")!=null&&request.getParameter("note")!=null&&request.getParameter("critereBesoin")!=null){
                int sousCritere = Integer.parseInt(request.getParameter("sousCritere"));
                double note = Double.parseDouble(request.getParameter("note"));
                int tauxJourHomme = Integer.parseInt(request.getParameter("critereBesoin"));
                NoteSousCritere.addNoteSousCritere(sousCritere, note, sousCritere);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("pageSuivante.jsp");
                requestDispatcher.forward(request, response);
            }else{
                throw new Exception("Something is null");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AjoutBesoinController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AjoutBesoinController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
