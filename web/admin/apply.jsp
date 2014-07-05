
<%@ page import="
com.transitionsoft.*,
com.transitionsoft.dao.*,
java.io.*,
java.util.*,
java.text.*,
javax.servlet.*,
javax.servlet.http.*, 
javax.servlet.http.HttpServletRequest,
javax.servlet.ServletInputStream,
java.util.Dictionary,
java.util.Hashtable,
java.io.IOException"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<%  
    try
    { 
        for(AbstractMap.SimpleEntry e : Database.getLanguageStrings())
        {
            application.setAttribute((String)e.getKey(),e.getValue());
            System.out.println("setting Application attribute: (" + e.getKey() + ":" + e.getValue() + ")");
        }
        
        response.sendRedirect("strings.jsp");
    }
    catch (Exception e) 
    {
        e.printStackTrace();
        response.sendError(500);
    }


%>