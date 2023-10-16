package utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.servlet.http.Part;

public class Utils{
	
    private String savePath = "E:/uploads/";

    public String getSavePath() {
            return savePath;
    }

    public void setSavePath(String savePath) {
            this.savePath = savePath;
    }

    public Utils() {

    }

    private String getFileName(Part filePart) {
            String contentDisp = filePart.getHeader("content-disposition");
            String[] tokens = contentDisp.split(";");
            for (String token : tokens) {
                    if (token.trim().startsWith("filename")) {
                            return token.substring(token.indexOf("=") + 2, token.length()-1);
                    }
            }
            return "";
    }

    public String uploadFile(Part filePart) throws Exception {
            String fileName = getFileName(filePart); 

            // Checking if the directory exists
            File file = new File(savePath);
            if (!file.exists()) file.mkdirs();

            OutputStream out = new FileOutputStream(new File(savePath+fileName));
            InputStream in = filePart.getInputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) != -1) {
                    out.write(buffer,0,length);
            }
            in.close();
            out.close();

            return savePath+fileName;
    }
    
    public static int getSequenceValue()throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try{
            connection = dbAccess.ConnectTo.postgreS();
            String sql = "SELECT getMatricule()";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1; // Return -1 if an error occurs
    }
    
    public static String getMatricule()throws Exception{
        String prefixe = "EMP";
        int taille = 12;
        String sequence = String.valueOf(Utils.getSequenceValue());
        String manyZero = "";
        while(taille >= prefixe.length() + manyZero.length() + sequence.length()) {
            manyZero += "0";
        }
        return prefixe+manyZero+sequence; 
    }
}
