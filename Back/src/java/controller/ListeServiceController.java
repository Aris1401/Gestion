package controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import static javax.management.Query.value;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Service;

/**
 *
 * @author BEST
 */
public class ListeServiceController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
         Service service = new Service();
            ArrayList<Service> allService = service.allService();
            Gson gson = new Gson();
            String json = gson.toJson(allService);
            response.setHeader("Access-Control-Allow-Origin", "*"); 
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            
            response.getWriter().println(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        

    }


}
