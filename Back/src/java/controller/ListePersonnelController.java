/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Besoin;
import model.CV;
import model.Embauche;
import model.Personne;
import model.PosteService;
import model.Service;
import org.apache.tomcat.util.json.JSONParser;
import utility.ResponsePrinter;

/**
 *
 * @author aris
 */
@WebServlet(name = "ListePersonnelController", urlPatterns = {"/ListePersonnel"})
public class ListePersonnelController extends HttpServlet {

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
        try {
            ArrayList<CV> personnels = Personne.listePersonnel();
            
            Gson gson = new Gson();
            
            // Array of json
            ArrayList<JsonObject> personnelsJSON = new ArrayList<>();
            for (CV pers : personnels) {
                JsonObject personnelJSON =  gson.toJsonTree(pers).getAsJsonObject();
                
                // Calcul age
                
                personnelJSON.addProperty("age", pers.calculerAge());
                
                // Ajout de date embauche
                Embauche personneEmbauche = Embauche.getEmbaucheCV(pers.getId());
                personnelJSON.addProperty("dateEmbauche", personneEmbauche.getDateEmbauche().toString());
                personnelJSON.addProperty("anciennete", personneEmbauche.calculerAnciennete());
                
                // Obtenir le besoin
                Besoin besoin = Besoin.getBesoinById(pers.getBesoin());
                personnelJSON.addProperty("poste", PosteService.getPosteServiceById(besoin.getPosteService()).getTitreposte());
                
                // Service
                Service service = Service.getServiceById(besoin.getService());
                personnelJSON.addProperty("service", service.getNom());
                
                personnelsJSON.add(personnelJSON);
            }
            
            ResponsePrinter.PrintToJSON(response, personnelsJSON);
        } catch (Exception ex) {
            Logger.getLogger(ListePersonnelController.class.getName()).log(Level.SEVERE, null, ex);
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
