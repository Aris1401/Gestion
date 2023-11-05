/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CV;
import model.CritereBesoin;
import utility.ResponsePrinter;

/**
 *
 * @author ITU
 */
@WebServlet(name = "ListeCVController", urlPatterns = {"/ListeCVController"})
public class ListeCVController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            int besoin = Integer.parseInt(request.getParameter("besoin"));
            ArrayList<CV> allCVForBesoins = CV.getCVFromBesoin(besoin);
            ResponsePrinter.PrintToJSON(response, allCVForBesoins);
//            Gson gson = new Gson();
//            String json = gson.toJson(allCVForBesoins);
//            response.setHeader(" 
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
            
//            response.getWriter().println(json);
        } catch (Exception e) {
            response.getWriter().print(e.getMessage());
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
