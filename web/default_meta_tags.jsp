<%@page import="com.busy.engine.domain.MetaTag"%>
<%@page import="com.busy.engine.domain.Page"%>
<%
    Page p = (Page) request.getAttribute("pageInfo");    
    MetaTag mt = p.getMetaTag();
%>

<title><%= mt.getTitle() %></title>	    
<meta charset="UTF-8">
<meta name="description" content="<%= mt.getDescription() %>">
<meta name="keywords" content="<%= mt.getKeywords() %>">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
        