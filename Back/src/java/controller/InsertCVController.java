/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.CV;
import model.Compte;
import utility.ResponsePrinter;
import utility.Utils;

/**
 *
 * @author BEST
 */
@MultipartConfig
public class InsertCVController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        ResponsePrinter.setCORS(response);
        
        // Preuve de diplome
        String preuveDiplome = "";
        String preuveDeTravail = "";
        
        Utils fileUploader = new Utils();
        // Upload diplome
        for (Part p : request.getParts()) {
            try {
                String fileName = fileUploader.uploadFile(p);
                
                if (p.getName().contains("preuveDiplome")) {
                    preuveDiplome += fileName;
                } else if (p.getName().contains("preuveDeTravail")) {
                    preuveDeTravail += fileName;
                }
            } catch (Exception ex) {
                
            }
        }
        
        CV cv =new CV();
        String nom=request.getParameter("nom");
        String prenom=request.getParameter("prenom");
        String adresse=request.getParameter("addresse");
        String email=request.getParameter("email");
        String contact=request.getParameter("contact");
        String description=request.getParameter("description");
        String dateNaissance=request.getParameter("dateNaissance");
        String diplomeFichier=preuveDiplome;
        String preuveTravailFichier=preuveDeTravail;
        String besoin=request.getParameter("besoin");
        
        if (request.getSession().getAttribute("user") != null) {
            ResponsePrinter.PrintToJSON(response, cv);
            
            Compte user = (Compte) request.getSession().getAttribute("user");

            cv.InsertCV( nom, prenom, adresse, email, contact,description, Date.valueOf(dateNaissance), diplomeFichier, preuveTravailFichier, Integer.parseInt(besoin), user.getPersonneInformation().getId());
        }
        
        
    }

}
