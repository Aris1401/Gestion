package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Compte;
import model.Personne;
import utility.ResponsePrinter;

/**
 *
 * @author aris
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
@MultipartConfig
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        Compte compte = Personne.Login(email, password);
        if (compte.getValide() == 1) {
            ResponsePrinter.PrintToJSON(response, "Connected");
            
            HttpSession session = request.getSession();
            session.setAttribute("user", compte);
            
            Cookie sessionIdCookie = new Cookie("JSESSIONID", session.getId());
            sessionIdCookie.setPath("/");
            response.addCookie(sessionIdCookie);
            response.setHeader("Access-Control-Allow-Credentials", "true");
        } else {
            ResponsePrinter.PrintToJSON(response, compte.getError());
        }
        
    }
}
