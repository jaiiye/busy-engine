<%@page import="java.util.ArrayList"%>
<%@page import="com.busy.engine.domain.ResourceUrl"%>

<%
    ArrayList<ResourceUrl> scripts = (ArrayList<ResourceUrl>) request.getAttribute("scripts");    
    for(ResourceUrl script : scripts) 
    { 
%>
        <script type="text/javascript" src="<%= script.getUrl() %>"></script>
<% 
    } 
%> 