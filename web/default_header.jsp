
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
     
<%
    HashMap pageStructure = Database.getSiteStructure();
    request.setAttribute("pageStructure", pageStructure);
%>

<div>
    <%
        // get title images
        for(Image img : (ArrayList<Image>)pageStructure.get("headerImages"))
        {
    %>
            <a href="<%= img.getImageLinkUrl()%>"><img src="images-site/<%= img.getImageFileName()%>" border="0"  alt="<%= img.getImageDescription()%>"></a>	
    <%
        }
        //if the strings are not available then load them from the database only once!
        if (application.getAttribute("index-header-link-1") == null)
        {
            for (AbstractMap.SimpleEntry e : Database.getLanguageStrings())
            {
                application.setAttribute((String) e.getKey(), e.getValue());
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


<div id="header">
    <div id='coin-slider'>
        <%@include file="slider.jsp" %>
    </div>    
</div>