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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TestQuestionnaire;
import utility.ResponsePrinter;

/**
 *
 * @author ITU
 */
@WebServlet(name = "AjoutQuestionnaireController", urlPatterns = {"/AjoutQuestionnaireController"})
public class AjoutQuestionnaireController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        ResponsePrinter.setCORS(response);
        try (PrintWriter out = response.getWriter()) {
            if(request.getParameter("besoin")!=null&&request.getParameter("question")!=null&&request.getParameter("estchoixmultiple")!=null){
                int besoin = Integer.parseInt(request.getParameter("besoin"));
                String question = request.getParameter("question");
                boolean estchoixmultiple = Boolean.valueOf(request.getParameter("estchoixmultiple"));
                double note = 0;
                TestQuestionnaire.ajoutQuestionnaire(besoin, question, estchoixmultiple, note);
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