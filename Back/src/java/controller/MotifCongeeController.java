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
import model.Motif;
import utility.ResponsePrinter;

/**
 *
 * @author aris
 */
@WebServlet(name = "MotifCongeeController", urlPatterns = {"/MotifCongee"})
public class MotifCongeeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ResponsePrinter.PrintToJSON(resp, Motif.motifs());
        } catch (Exception ex) {
            Logger.getLogger(MotifCongeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
