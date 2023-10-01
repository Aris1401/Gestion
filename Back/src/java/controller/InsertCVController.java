/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CV;

/**
 *
 * @author BEST
 */
public class InsertCVController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        CV cv =new CV();
        String nom=request.getParameter("nom");
        String prenom=request.getParameter("prenom");
        String adresse=request.getParameter("adresse");
        String email=request.getParameter("email");
        String contact=request.getParameter("contact");
        String description=request.getParameter("description");
        String dateNaissance=request.getParameter("dateNaissance");
        String diplomeFichier=request.getParameter("diplomeFichier");
        String preuveTravailFichier=request.getParameter("preuveTravilFichier");
        String besoin=request.getParameter("besoin");
                        
        cv. InsertCV( nom, prenom, adresse, email, contact,description, dateNaissance, diplomeFichier, preuveTravailFichier, besoin);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
