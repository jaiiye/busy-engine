
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.transitionsoft.*"%>
<%@ page import="com.transitionsoft.dao.*"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<%
	PageInfo info = Database.getPageInfo(request.getParameter("id"));
	String formId = "0";
%>

<!DOCTYPE html> 
<html>
    <head>    
        <meta charset="UTF-8"> 
        <title><%= info.getPageSeoTitle() %></title>
        <meta name="description" content="<%= info.getPageSeoDescription() %>" />
        <meta name="keywords" content="<%= info.getPageSeoKeywords() %>" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
        <%@include file="main_includes.jsp" %>
    </head>
    
    <body class="home">	
        <div class="root">	
            <%@include file="main_header.jsp" %>	
            <% request.setAttribute("sliderId", info.getPageSliderId()); %>        
            <% if( !("0".equals(info.getPageSliderId())) )  { %>
               <%@include file="main_slider.jsp"%>  
            <% } %>       
            <section class="content"> 
				<%= info.getPageContent() %> 
		        <% request.setAttribute("formId", info.getPageFormId()); %>
                <% if( !("0".equals(info.getPageFormId())) )  { %>
                    <jsp:include page="generate_form.jsp" flush="true" />  
                <% } %> 
            </section>    
            <%@include file="main_footer.jsp" %>    
        </div>
    
        <%@include file="main_scripts.jsp" %>	        
    </body>
</html>



