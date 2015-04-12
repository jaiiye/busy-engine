<%@page import="java.util.ArrayList"%>
<%@page import="com.busy.engine.entity.*"%>

<%
    ArrayList<ResourceUrl> linksCopy = (ArrayList<ResourceUrl>) request.getAttribute("links");    
    for(ResourceUrl link : linksCopy) 
    { 
%>
        <link rel="stylesheet" type="text/css" href="<%= link.getUrl() %>"  />
<% 
    } 
%> 