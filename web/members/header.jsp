<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.transitionsoft.*" %>
<%@ page import="com.transitionsoft.dao.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<%
    String userName = request.getUserPrincipal().getName();
    User u = null;

    if(userName == null)
    {
        //user is not logged in
    }
    else
    {
        //see if the user is already logged in before
        String name = (String)session.getAttribute("userName");
        
        if(name == null)
        {
            //a new user is being logged-in
            session.setAttribute("userName", userName);
        
            //find out who the logged-on user is
            u = Database.getUser(request.getUserPrincipal().getName()); 

            //record the login date and time
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String loginTime = sdf.format(new Date(session.getCreationTime()));

            Database.RecordUserLoginAction(u.getUserId().toString(), u.getUserName(), loginTime, "Site Members Area");
        }
        else{
            u = Database.getUser(name); 
        }
    }

%>


<div id="nav">
    <div id="mydroplinemenu" class="droplinebar">
        <%@include file="nav.jsp" %>
    </div>    
</div>
