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
import model.ChoixReponse;
import utility.ResponsePrinter;

/**
 *
 * @author ITU
 */
@WebServlet(name = "AjoutChoixReponseController", urlPatterns = {"/AjoutChoixReponseController"})
public class AjoutChoixReponseController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        ResponsePrinter.setCORS(response);
        try {
//            if(request.getParameter("questionnaire")!=null&&request.getParameter("reponse")!=null&&request.getParameter("note")!=null){
                int questionnaire = Integer.parseInt(request.getParameter("questionnaire"));
                String reponse = request.getParameter("reponse");
                double note = Double.parseDouble(request.getParameter("note"));
                ChoixReponse.ajoutChoixReponse(questionnaire, reponse, note);
            
        } catch (Exception e) {
            e.printStackTrace();
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
