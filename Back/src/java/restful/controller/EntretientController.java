/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package restful.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Entretient;
import utility.ResponsePrinter;

/**
 *
 * @author aris
 */
@MultipartConfig
@WebServlet(name = "EntretientController", urlPatterns = {"/Entretient"})
public class EntretientController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponsePrinter.setCORS(resp);
        
        int cv = Integer.parseInt(req.getParameter("cv"));
        Timestamp dateEntretient = Timestamp.valueOf(LocalDateTime.parse(req.getParameter("dateEntretient")));
        double dureeEntretient = Double.parseDouble(req.getParameter("dureeEntretient"));
        
        Entretient.insertEntretient(dateEntretient, dureeEntretient, cv);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("cv") != null) {
            try {
                // Si on donne un cv avec le url
                int cv = Integer.parseInt(req.getParameter("cv"));
                
                Entretient entretientCV = Entretient.obtenirEntretientPourCv(cv);
                
                ResponsePrinter.PrintToJSON(resp, entretientCV);
            } catch (Exception ex) {
                Logger.getLogger(EntretientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
}
