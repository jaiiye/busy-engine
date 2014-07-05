
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.transitionsoft.*"%>
<%@ page import="com.transitionsoft.dao.*"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<%@include file="connection.jsp" %>      
   
<div>
<%
	// get title images
	for(Image img : Database.getSiteImagesGivenCategory(5))
	{
%>
	<a href="<%= img.getImageLinkUrl()%>"><img src="images-site/<%= img.getImageFileName() %>" border="0"  alt="<%= img.getImageDescription() %>"></a>	
<%
	}	
	//if the strings are not available then load them from the database only once!
	if(	application.getAttribute("index-header-link-1") == null)
	{
		for(AbstractMap.SimpleEntry e : Database.getLanguageStrings())
		{
			application.setAttribute((String)e.getKey(),e.getValue());
			System.out.println("setting Application attribute: (" + e.getKey() + ":" + e.getValue() + ")");
		}
	}
	
%> 
</div>

<div id="nav">
    <div id="mydroplinemenu" class="droplinebar">
        <%@include file="nav.jsp" %>
    </div>    
</div>
