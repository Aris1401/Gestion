/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CV;
import model.Compte;
import model.ReponseCV;
import model.ReponseQuestionnaire;
import utility.ResponsePrinter;

/**
 *
 * @author aris
 */
@WebServlet(name = "AjoutReponseCV", urlPatterns = {"/AjoutReponseCV"})
public class AjoutReponseCV extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       ResponsePrinter.setCORS(response);
        try (PrintWriter out = response.getWriter()) {
            if(request.getParameter("critere")!=null&&request.getParameter("sousCritere")!=null){
                int critere = Integer.parseInt(request.getParameter("critere"));
                int sousCritere = Integer.parseInt(request.getParameter("sousCritere"));
                int besoin = Integer.parseInt(request.getParameter("besoin"));
                if (request.getSession().getAttribute("user") != null) {
                    Compte user = (Compte) request.getSession().getAttribute("user");
                    CV cv = CV.dejaPostulter(user.getPersonneInformation().getId(), besoin);
                    ReponseCV.insertReponseCV(critere, sousCritere, cv.getId());
                }
            }else{
                throw new Exception("Something is null");
            }
        } catch (Exception ex) {
            ResponsePrinter.PrintToJSON(response, ex.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
