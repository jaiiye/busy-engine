<%@page import="java.util.ArrayList"%>
<%@page import="com.busy.engine.domain.ResourceUrl"%>

<%
    ArrayList<ResourceUrl> links = (ArrayList<ResourceUrl>) request.getAttribute("links");    
    for(ResourceUrl link : links) 
    { 
%>
        <link rel="stylesheet" type="text/css" href="<%= link.getUrl() %>"  />
<% 
    } 
%> 