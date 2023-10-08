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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CV;
import model.PosteService;
import utility.ResponsePrinter;

/**
 *
 * @author ITU
 */
@WebServlet(name = "ListePosteService", urlPatterns = {"/ListePosteService"})
public class ListePosteService extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int service = Integer.parseInt(request.getParameter("service"));
            ArrayList<PosteService> allPosteServices = PosteService.getPosteServiceByService(service);
            ResponsePrinter.PrintToJSON(response, allPosteServices);
//            Gson gson = new Gson();
//            String json = gson.toJson(allPosteServices);
//            response.setHeader("Access-Contro
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
            
//            response.getWriter().println(json);
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