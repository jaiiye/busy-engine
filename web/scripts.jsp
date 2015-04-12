<%@page import="java.util.ArrayList"%>
<%@page import="com.busy.engine.entity.*"%>

<%
    ArrayList<ResourceUrl> scriptsCopy = (ArrayList<ResourceUrl>) request.getAttribute("scripts");    
    for(ResourceUrl script : scriptsCopy) 
    { 
%>
        <script type="text/javascript" src="<%= script.getUrl() %>"></script>
<% 
    } 
%> 