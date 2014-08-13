
package com.busy.engine.mail;

import com.busy.engine.dao.SiteDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sourena
 */
public class ProcessForm extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try 
        {
            EmailComposer email = new EmailComposer("", "Request Details:", buildEmailBody(request.getParameterNames(), request), request.getParameter("submissionEmailAddress"), request.getParameter("bcc"), request.getServletContext().getRealPath("/images-site/"), new SiteDaoImpl().find(1));
            email.prepareEmail();
            email.sendEmail();
            
            response.sendRedirect("index.jsp");                
        } 
        finally 
        { 
            out.close();
        }
    } 

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        processRequest(request, response);
    } 

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        processRequest(request, response);
    }

    public String getServletInfo() 
    {
        return "Short description";
    }

    private String buildEmailBody(Enumeration paramNames, HttpServletRequest request) 
    {
        String result = "";
        result += "<table border=\"1\" align=\"center\">\n" +
                      "<tr BGCOLOR=\"#FFAD00\">\n" +
                          "<th>Parameter Name</th>" + 
                          "<th>Parameter Value(s)</th>";
        
        while(paramNames.hasMoreElements())
        {
            String paramName = (String)paramNames.nextElement();

            result +="<tr><td>" + paramName + "\n<td>";

            String[] paramValues = request.getParameterValues(paramName);

            if (paramValues.length == 1)
            {
                String paramValue = paramValues[0];
                if (paramValue.length() == 0)
                {         
                    result +="<i>Empty</i>";
                }
                else         
                    result += paramValue;
            }
            else
            {
                result +="<ul>";
                for(int i=0; i<paramValues.length; i++)
                {         
                    result +="<li>" + paramValues[i];
                }         
                result +="</ul>";
            }
        }
        result +="</TABLE>\n</BODY></HTML>";
        return result; 
            
    }

}
