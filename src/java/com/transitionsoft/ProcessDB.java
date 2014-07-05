package com.transitionsoft;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class ProcessDB extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String connectionURL = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null; 

        try
        {            
            Context initialContext = new InitialContext();
            Context environmentContext = (Context) initialContext.lookup("java:comp/env");
            DataSource ds = (DataSource) environmentContext.lookup("jdbc/dataSource");
            connection = ds.getConnection();
            statement = connection.createStatement();           
        }
        catch (Exception ex)
        {
            System.out.println("Open connection error: " + ex.getMessage());
        }
                
        String catalog = null;
        String schemaPattern = null;
        String tableNamePattern = null;
        String[] types = null;

        ArrayList<String> tables = new ArrayList<String>();
        
        DatabaseMetaData databaseMetaData = null;
        try
        {
            databaseMetaData = connection.getMetaData();
            rs = databaseMetaData.getTables(catalog, schemaPattern, tableNamePattern, types);
            while (rs.next())
            {
                tables.add(rs.getString(3));
                System.out.println("Found table: " + rs.getString(3));
            }
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        finally
        {
            try
            {
                //if (rs!=null) try  { rs.close(); } catch (Exception ex){ System.out.println("Close resultSet error: " + ex.getMessage()); }
                if (statement!=null) try  { statement.close(); } catch (Exception ex){System.out.println("Close statement error: " + ex.getMessage());}
                if (connection!=null) try { connection.close();} catch (Exception ex){System.out.println("Close connection error: " + ex.getMessage());}
            }
            catch (Exception ex)
            {
                System.out.println("Close connection error: " + ex.getMessage());
            }
        }

        int generatedLinesOfCode = 0;
        for (String table : tables)
        {
            String tableNameUppercase = table.substring(0, 1).toUpperCase() + table.substring(1, table.length());
            String objectName = tableNameUppercase.replace("_a", "A").replace("_b", "B").replace("_c", "C").replace("_d", "D").replace("_e", "E").replace("_f", "F").replace("_g", "G").replace("_h", "H").replace("_i", "I").replace("_j", "J").replace("_k", "K").replace("_l", "L").replace("_m", "M").replace("_n", "N").replace("_o", "O").replace("_p", "P").replace("_q", "Q").replace("_r", "R").replace("_s", "S").replace("_t", "T").replace("_u", "U").replace("_v", "V").replace("_w", "W").replace("_x", "X").replace("_y", "Y").replace("_z", "Z");
            objectName = objectName.replace("_a", "A").replace("_b", "B").replace("_c", "C").replace("_d", "D").replace("_e", "E").replace("_f", "F").replace("_g", "G").replace("_h", "H").replace("_i", "I").replace("_j", "J").replace("_k", "K").replace("_l", "L").replace("_m", "M").replace("_n", "N").replace("_o", "O").replace("_p", "P").replace("_q", "Q").replace("_r", "R").replace("_s", "S").replace("_t", "T").replace("_u", "U").replace("_v", "V").replace("_w", "W").replace("_x", "X").replace("_y", "Y").replace("_z", "Z");
            
            generatedLinesOfCode += ProcessFile("http://localhost:8084/STORE/genDao.jsp?table=", table, objectName, ".java"); 
            generatedLinesOfCode += ProcessFile("http://localhost:8084/STORE/genUI.jsp?table=", table, objectName, "UI2.jsp");            
        }
        
        System.out.println("Done generating Data Access Objects and UI files for all entities with full CRUD and column checking support!");        
        generatedLinesOfCode += ProcessFile("http://localhost:8084/STORE/genOps.jsp", "", "Operations", ".java"); 
        
        System.out.println("Done generating UI Create, Update, Delete Operations servlet!"); 
        System.out.println("Generated " + NumberFormat.getNumberInstance(Locale.US).format(generatedLinesOfCode) + " lines of code!");          
    }
    
    public int ProcessFile(String Url, String tableName, String objectName, String fileExtension) throws IOException
    {
        int lineCount = 0;
        HttpURLConnection conn = (HttpURLConnection) new URL(Url + tableName).openConnection();
        conn.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        try
        {
            File file = new File("E:/" + objectName + fileExtension);
            if (!file.exists())  // if file doesnt exists, then create it
            {
                file.createNewFile();
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));

            for (String x = br.readLine(); x != null; x = br.readLine())
            {
                bw.write(x + "\n");
                lineCount++;
            }

            bw.close();
            System.out.println("Created " + objectName + fileExtension);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return lineCount;
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
            throws ServletException, IOException
    {
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
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
