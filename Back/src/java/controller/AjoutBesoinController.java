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

/**
 *
 * @author Henintsoa & Hery
 */
@WebServlet(name = "AjoutBesoinController", urlPatterns = {"/AjoutBesoinController"})
public class AjoutBesoinController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if(request.getParameter("service")!=null&&request.getParameter("description")!=null&&request.getParameter("titre")!=null&&request.getParameter("volumeTaches")!=null&&request.getParameter("tauxJourHomme")!=null&&request.getParameter("dateFin")!=null){
                int service = Integer.parseInt(request.getParameter("service"));
                String description = request.getParameter("description");
                String titre = request.getParameter("titre");
                double volumeTaches = Double.parseDouble(request.getParameter("volumeTaches"));
                double tauxJourHomme = Double.parseDouble(request.getParameter("tauxJourHomme"));
                Date dateFin = Date.valueOf(request.getParameter("dateFin"));
                Besoin.createBesoinToday(service, description, titre, volumeTaches, tauxJourHomme, dateFin);
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
