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
import utility.ResponsePrinter;

/**
 *
 * @author aris
 */
@WebServlet(name = "PosteService", urlPatterns = {"/PosteService"})
public class PosteService extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (!req.getParameter("service").equals("undefined")){
                int service = Integer.parseInt(req.getParameter("service"));
                ResponsePrinter.PrintToJSON(resp, model.PosteService.getPosteServices(service));
            } else ResponsePrinter.PrintToJSON(resp, model.PosteService.getPosteServices());
        } catch (Exception ex) {
            Logger.getLogger(PosteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
