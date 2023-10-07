/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aris
 */
public class ResponsePrinter {
    public static void PrintToJSON(HttpServletResponse res, Object obj) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        res.setHeader("Access-Control-Allow-Origin", "*"); 
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        res.getWriter().println(json);
    }
}
