
<%@ page import="java.util.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

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
        <div id="left" style="padding:10px 10px 20px 20px; width:570px">

 <h1>${param.purpose} Successful:</h1>
 <br />
 <br />
       <TABLE cellSpacing="5" cellPadding="5" width="90%" border=0>
          <TBODY>
               <TR>
                   
                    <TD width="*" valign="top">
             <fieldset class="blockTitle" >
                    
                    <p align="center">
                            <br><%=application.getAttribute("register-email-address")%> : ${param.EMailAddress}                        
                    </p>                    
                    <p align="center">
                        <a class="header"><%=application.getAttribute("subscription-success-thank-message")%> </a>
                        <br /><a class="header" href="index.jsp"><%=application.getAttribute("click-here-to-return-to-the-site")%></a>                        
                    </p>

                    <br />
                    </fieldset>
                    </TD>
                </TR>
                
		</TBODY>
	</TABLE>

        </div>
        <div id="right">

        </div>
    </div>    
    
    <div id="bottomRightSide"></div>

<%@include file="index_footer.jsp" %> 


  </div>
  
  
</body>
</html>
