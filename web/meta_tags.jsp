<%@page import="com.busy.engine.entity.*"%>
<%
    Page pageInfo = (Page) request.getAttribute("pageInfo");    
    MetaTag mt = pageInfo.getMetaTag();
    if(mt == null){
        mt = new MetaTag(1, "Title not defined", "Description not defined","keywords, not defined");
    }
%>

<title><%= mt.getTitle() %></title>	    
<meta charset="UTF-8">
<meta name="description" content="<%= mt.getDescription() %>">
<meta name="keywords" content="<%= mt.getKeywords() %>">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
        