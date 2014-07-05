<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.transitionsoft.*"%>
<%@ page import="com.transitionsoft.dao.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>

		<%@include file="meta-tags.jsp" %>
        <%@include file="includes.jsp" %> 
        <%@include file="analytics.jsp" %>
        
        <script type="text/javascript">
			//build menu with DIV Id="myslidemenu" on page:
			droplinemenu.buildmenu("mydroplinemenu")

    	    $(document).ready(function() 
			{
    	        $('#coin-slider').coinslider({ width: 919, height: 298, navigation: true, delay: 4000 });
        	});
		</script>

	</head>
<body>

    <div id="wrapper">
    
        <%@include file="index_header.jsp" %> 
    
        <div id="topbarRightSide"></div>
        
        <div id="contentRightSide">
            <div id="left" style="padding:10px 10px 20px 20px; width:650px">
                <%= Database.getPageContentByName("Contact") %>
            </div>
            <div id="right">
                <%@include file="toolbar.jsp" %> 
            </div>
        </div>    
        
        <div id="bottomRightSide"></div>
    
        <%@include file="index_footer.jsp" %> 
    
    </div>
  
</body>
</html>
