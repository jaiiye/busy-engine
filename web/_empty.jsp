<%@ page import="java.util.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>

	<%@include file="meta-tags.jsp" %> 

	<%@include file="includes.jsp" %>
    
        <script type="text/javascript">
			//build menu with DIV id="myslidemenu" on page
			droplinemenu.buildmenu("mydroplinemenu")
		</script>        
        <%@include file="analytics.jsp" %> 
	</head>
<body>

	<%@include file="store_header.jsp" %> 
    <div id="topbarRightSide"></div>
    <div id="contentRightSide">
    
    	<div id="right">b</div>
        <div id="body">a 
            <div id="titleGraphic" align="center"></div>
            <div id="featuredDesigns"></div>
        </div>
    </div>    
	<div id="bottomRightSide"></div>
	<%@include file="store_footer.jsp" %> 
  
  
</body>
</html>