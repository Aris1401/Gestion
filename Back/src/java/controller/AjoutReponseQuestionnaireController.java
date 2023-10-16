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
import model.ChoixReponse;
import model.Compte;
import model.ReponseQuestionnaire;
import utility.ResponsePrinter;

/**
 *
 * @author ITU
 */
@WebServlet(name = "AjoutReponseQuestionnaireController", urlPatterns = {"/AjoutReponseQuestionnaireController"})
public class AjoutReponseQuestionnaireController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        ResponsePrinter.setCORS(response);
        try (PrintWriter out = response.getWriter()) {
            if(request.getParameter("questionnaire")!=null&&request.getParameter("reponse")!=null){
                int questionnaire = Integer.parseInt(request.getParameter("questionnaire"));
                int reponse = Integer.parseInt(request.getParameter("reponse"));
                int besoin = Integer.parseInt(request.getParameter("besoin"));
                if (request.getSession().getAttribute("user") != null) {
                    Compte user = (Compte) request.getSession().getAttribute("user");
                    CV cv = CV.dejaPostulter(user.getPersonneInformation().getId(), besoin);
                    ReponseQuestionnaire.ajoutReponseQuestionnaire(questionnaire, reponse, cv.getId());
                }
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
