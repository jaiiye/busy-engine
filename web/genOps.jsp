<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.DatabaseMetaData"%>
<%@page import="java.text.*" %>
<%@page import="java.util.*" %>
<%@page import="com.transitionsoft.*" %>
<%@page import="com.transitionsoft.dao.*" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html; charset=ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@include file="../connection.jsp" %> 
<%!
public String parseData(int type, String columnName)
{
    switch(type)
    {
        case java.sql.Types.INTEGER:
        case java.sql.Types.SMALLINT:  
        case java.sql.Types.TINYINT:          
            return "Integer.parseInt(request.getParameter(\"" + columnName + "\"))";            
        case java.sql.Types.BOOLEAN:
        case java.sql.Types.BIT:
            return "Boolean.parseBoolean(request.getParameter(\"" + columnName + "\"))"; 
        case java.sql.Types.DATE:
        case java.sql.Types.TIMESTAMP:
            return "DateFormat.getDateInstance().parse(request.getParameter(\"" + columnName + "\"))";
        case java.sql.Types.DECIMAL:
        case java.sql.Types.DOUBLE:
            return "Double.parseDouble(request.getParameter(\"" + columnName + "\"))"; 
        default:
            return "request.getParameter(\"" + columnName + "\")"; 
    }    
}

%>
package com.transitionsoft;

import com.busy.dao.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Operations extends HttpServlet
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String userName = request.getUserPrincipal().getName();
        com.transitionsoft.dao.User u = null;
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(new java.util.Date());

        if(userName != null)
        {
            //see if the user is already logged in before
            String name = (String)request.getSession().getAttribute("userName");

            if(name == null)
            {
                //a new user is being logged-in
                request.getSession().setAttribute("userName", userName);

                //find out who the logged-on user is
                u = Database.getUser(userName); 
            }
            else
            {
                u = Database.getUser(name); 
            }
        }        
<% 
    DatabaseMetaData databaseMetaData = connection.getMetaData();
    String   catalog          = null;
    String   schemaPattern    = null;
    String   tableNamePattern = null;
    String[] types            = null;
    String   columnNamePattern = null;
    
    rs = databaseMetaData.getTables(catalog, schemaPattern, tableNamePattern, types );

    ArrayList<String> tables = new ArrayList<String>(); 
    
    while(rs.next()) 
    {
        tables.add(rs.getString(3));
    }

    for(String table : tables)
    {
        String tableNameLowercase = table; 
        String tableNameUppercase = table.substring(0, 1).toUpperCase() + table.substring(1, table.length());
        String objectName = tableNameUppercase.replace("_a", "A").replace("_b", "B").replace("_c", "C").replace("_d", "D").replace("_e", "E").replace("_f", "F").replace("_g", "G").replace("_h", "H").replace("_i", "I").replace("_j", "J").replace("_k", "K").replace("_l", "L").replace("_m", "M").replace("_n", "N").replace("_o", "O").replace("_p", "P").replace("_q", "Q").replace("_r", "R").replace("_s", "S").replace("_t", "T").replace("_u", "U").replace("_v", "V").replace("_w", "W").replace("_x", "X").replace("_y", "Y").replace("_z", "Z");
        objectName = objectName.replace("_a", "A").replace("_b", "B").replace("_c", "C").replace("_d", "D").replace("_e", "E").replace("_f", "F").replace("_g", "G").replace("_h", "H").replace("_i", "I").replace("_j", "J").replace("_k", "K").replace("_l", "L").replace("_m", "M").replace("_n", "N").replace("_o", "O").replace("_p", "P").replace("_q", "Q").replace("_r", "R").replace("_s", "S").replace("_t", "T").replace("_u", "U").replace("_v", "V").replace("_w", "W").replace("_x", "X").replace("_y", "Y").replace("_z", "Z");

        rs = databaseMetaData.getColumns(catalog, schemaPattern,  table, columnNamePattern);

        ArrayList<String> columns = new ArrayList<String>();    
        ArrayList<Integer> columnTypes = new ArrayList<Integer>();

        while(rs.next())
        {
            columns.add(rs.getString(4));
            columnTypes.add(rs.getInt(5));
        }

        int numColumns = columns.size();     
%>        
        if(request.getParameter("form").equals("<%= tableNameLowercase %>"))
        {   
            try
            {     
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1: //create
                        int id = <%= objectName%>.add<%= objectName%>(<%for(int i = 1; i < numColumns; i++) { String columnNameLowercase = columns.get(i).substring(0, 1).toLowerCase() + columns.get(i).substring(1, columns.get(i).length());  %><%= parseData(columnTypes.get(i), columnNameLowercase) %><% if(i!=numColumns-1) { %>, <% } }%>);                
                        if(id != 0) { Database.RecordUserObjectCreationAction(u.getUserId().toString(), u.getUserName(), currentTime, "<%= objectName%>", id); }
                        response.sendRedirect("admin/<%= objectName%>UI.jsp?SuccessMsg=Added <%= objectName%> Successfully!");
                        break;
                    case 2: //update            
                        <%= objectName%>.update<%= objectName%>(<%for(int i = 0; i < numColumns; i++) { String columnNameLowercase = columns.get(i).substring(0, 1).toLowerCase() + columns.get(i).substring(1, columns.get(i).length());   %><%= parseData(columnTypes.get(i), columnNameLowercase) %><% if(i!=numColumns-1) { %>, <% } }%>);
                        Database.RecordUserObjectUpdateAction(u.getUserId().toString(), u.getUserName(), currentTime, "<%= objectName%>", Integer.parseInt(request.getParameter("<%= columns.get(0).substring(0, 1).toLowerCase() + columns.get(0).substring(1, columns.get(0).length()) %>")));
                        response.sendRedirect("admin/<%= objectName%>UI.jsp?id=" + request.getParameter("<%= columns.get(0).substring(0, 1).toLowerCase() + columns.get(0).substring(1, columns.get(0).length()) %>") + "&SuccessMsg=Updated <%= objectName%> Successfully!");
                        break;
                    case 3:  //delete
                        <%= objectName%>.delete<%= objectName%>ById(request.getParameter("id"));                        
                        Database.RecordUserObjectDeletionAction(u.getUserId().toString(), u.getUserName(), currentTime, "<%= objectName%>", request.getParameter("id"));
                        response.sendRedirect("admin/<%= objectName%>UI.jsp?SuccessMsg=Deleted <%= objectName%> Successfully!");
                        break;
                    case 4:  //remove all records
                        <%= objectName%>.deleteAll<%= objectName%>();                        
                        Database.RecordUserObjectClearAction(u.getUserId().toString(), u.getUserName(), currentTime, "<%= objectName%>");
                        response.sendRedirect("admin/<%= objectName%>UI.jsp?SuccessMsg=Removed All Records Successfully!");
                        break;
                    default:
                        response.sendRedirect("admin/<%= objectName%>UI.jsp?ErrorMsg=Error editing <%= objectName%>, Invalid Action."); break;
                }        
            }
            catch (Exception e) 
            {
                System.out.println("Error:" + e.getMessage());        
                switch (Integer.parseInt(request.getParameter("action")))
                {
                    case 1:  response.sendRedirect("admin/<%= objectName%>UI.jsp?ErrorMsg=Error adding <%= objectName%>.");   break; //create
                    case 2:  response.sendRedirect("admin/<%= objectName%>UI.jsp?ErrorMsg=Error editing <%= objectName%>.");  break; //update                                                    
                    case 3:  response.sendRedirect("admin/<%= objectName%>UI.jsp?ErrorMsg=Error deleting <%= objectName%>."); break; //delete                                                    
                    case 4:  response.sendRedirect("admin/<%= objectName%>UI.jsp?ErrorMsg=Error clearing <%= objectName%>."); break; //clear                          
                    default: response.sendRedirect("admin/<%= objectName%>UI.jsp?ErrorMsg=Unknown Error <%= objectName%>, possibly an invalid action."); break;
                }
            }
        }
    <% } %>
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }
}

<%
    connection.close();
    statement.close();
%>