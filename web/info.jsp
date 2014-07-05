
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.transitionsoft.*"%>
<%@ page import="com.transitionsoft.dao.*"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<%
	PageInfo info = Database.getPageInfo(request.getParameter("id"));
	String formId = "0";
%>
                        

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
    
        <title><%= info.getPageSeoTitle() %></title>
        <meta name="description" content="<%= info.getPageSeoDescription() %>" />
        <meta name="keywords" content="<%= info.getPageSeoKeywords() %>" />
                                    
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

    <div id="topbarRightSide">
    </div>
    
    <div id="contentRightSide">
        <div id="left" style="padding:10px 10px 20px 20px; width:650px">
			<table cellSpacing="5" cellPadding="5" width="100%" border=0>
                  <tbody>
                       <tr>                   
                            <td width="*" valign="top">
								<%= info.getPageContent() %>
                                <% formId = info.getPageFormId(); %>  
                            </td>
                        </tr>
                        <tr>
                            <td width="*" valign="top">
								  <% request.setAttribute("formId", formId); %>
                                  <% if( !("0".equals(formId)) )  { %>
                                    <jsp:include page="generate_form.jsp" flush="true" />  
                                  <% } %>
                            </td>
                        </tr>
                </tbody>
            </table>
        </div>
        <div id="right">
            <table border="0" cellspacing="0" cellpadding="0" width="100%" style=" margin-left:5px;">
                <tbody>
                    <tr>
                        <td align="center">
                            <%@include file="toolbar.jsp" %> 
                        </td>
                    </tr>
                    
                </tbody>
            </table>
        </div>
    </div>    
    
    <div id="bottomRightSide"></div>

	<%@include file="index_footer.jsp" %> 


  </div>
  
  
</body>
</html>



