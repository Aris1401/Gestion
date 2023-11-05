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
import model.Compte;
import utility.ResponsePrinter;

/**
 *
 * @author aris
 */
@WebServlet(name = "DejaReponduAuTest", urlPatterns = {"/DejaReponduAuTest"})
public class DejaReponduAuTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponsePrinter.setCORS(response);
        if (request.getSession().getAttribute("user") != null) {
            Compte user = (Compte) request.getSession().getAttribute("user");
            int besoin = Integer.parseInt(request.getParameter("besoin"));
            
            try {
                ResponsePrinter.PrintToJSON(response, model.CV.reponduAuTest(user.getPersonneInformation().getId(), besoin));
            } catch (Exception ex) {
                Logger.getLogger(DejaPostuler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ResponsePrinter.PrintToJSON(response, null);
            
        }
    }
}
