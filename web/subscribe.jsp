
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.transitionsoft.*"%>
<%@ page import="com.transitionsoft.dao.*"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<%
    boolean success = false;
	if( !request.getParameter("EMailAddress").equals("") && Validator.validate("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", request.getParameter("EMailAddress")) )
	{
		success = true;
		Database.addNewsletterSubscriber("Guest",  request.getParameter("EMailAddress"));
	}										
%>


<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
    
		<%@include file="meta-tags.jsp" %>
        <%@include file="includes.jsp" %>
        <%@include file="analytics.jsp" %>    
            
        <script type="text/javascript">
			//build menu with DIV Id="myslidemenu" on page:
			droplinemenu.buildmenu("mydroplinemenu");
			
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
        <div id="left">
<div style="font-family:Georgia, 'Times New Roman', Times, serif">
      <TABLE cellSpacing="5" cellPadding="5" width="100%" border=0>
          <TBODY>
               <TR>                   
                    <TD width="*" valign="top">
             <fieldset class="blockTitle" >
                 <%
                 if(success)
                     {
                    %>
                    
                    <p align="center">
                            <strong><%=application.getAttribute("subscription-successful")%></strong><br />
                            <br><%=application.getAttribute("register-email-address")%> : ${param.EMailAddress}                        
                    </p>                    
                    <p align="center">
                        <a class="header"><%=application.getAttribute("subscription-success-thank-message")%> </a>
                        <br /><a class="header" href="index.jsp"><%=application.getAttribute("click-here-to-return-to-the-site")%></a>                        
                    </p>
                    <%
                    }
                    else
                    {
                    %>
                     
                    <p align="center"><strong><%=application.getAttribute("subscription-failed")%></strong><br />
                        <a class="header"><%=application.getAttribute("invalid-email-address")%>  </a>
                        <br /><a class="header" href="index.jsp"><%=application.getAttribute("click-here-to-return-to-the-site")%></a>
                    </p>
                     <%
                    }
                    %>

                    <br />
                    </fieldset>
                    </TD>
                </TR>
                
		</TBODY>
	</TABLE>
    </div>
        </div>
        <div id="right">
        	<a href="store.jsp"><img src='main_img/store.jpg' border="0" align="right" ></a>
        </div>
    </div>    
    
    <div id="bottomRightSide"></div>

<%@include file="index_footer.jsp" %> 


  </div>
  
  
</body>
</html>
