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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utility.ResponsePrinter;

/**
 *
 * @author aris
 */
@MultipartConfig
@WebServlet(name = "SousCritere", urlPatterns = {"/SousCritere"})
public class SousCritere extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResponsePrinter.setCORS(response);
        
        String nom = request.getParameter("nom");
        int critere = Integer.parseInt(request.getParameter("critere"));
        model.SousCritere.insertSousCritere(nom, critere);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponsePrinter.setCORS(resp);
        
        int id = Integer.parseInt(req.getParameter("id"));
        String nom = req.getParameter("nom");
        
        try {
            model.SousCritere.modifierSousCritere(id, nom);
        } catch (Exception ex) {
            Logger.getLogger(SousCritere.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponsePrinter.setCORS(resp);
        
        int id = Integer.parseInt(req.getParameter("id"));
        
        try {
            model.SousCritere.deleteSousCritere(id);
        } catch (Exception ex) {
            resp.getWriter().print(ex.getMessage());
            Logger.getLogger(SousCritere.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
