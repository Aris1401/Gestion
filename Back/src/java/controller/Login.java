package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Personne;

/**
 *
 * @author BEST
 */
@MultipartConfig
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
       String mdp=request.getParameter("mdp");
       String email=request.getParameter("email");
       Personne personne =new Personne();
       int valeur=personne.Login(email, mdp);
       if(valeur==1)
       {
           //makan amn amn admin
       }
       else
       {
           //makan amn olona tsotra
       }
       
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
