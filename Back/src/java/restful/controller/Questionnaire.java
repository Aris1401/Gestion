/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package restful.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
 * @author aris
 */
@WebServlet(name = "Questionnaire", urlPatterns = {"/Questionnaire"})
public class Questionnaire extends HttpServlet {
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponsePrinter.setCORS(resp);
        
        int questionnaire = Integer.parseInt(req.getParameter("question"));
        try {
            TestQuestionnaire.deleteQuestionnaire(questionnaire);
        } catch (Exception ex) {
            Logger.getLogger(Questionnaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
