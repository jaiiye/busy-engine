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

    <div id="topbarNoSide">
    </div>
    
    <div id="contentNoSide">
        <div id="left">

      <TABLE cellSpacing="5" cellPadding="5" width="100%" border=0>
          <TBODY>
               <TR>
                   
                    <TD width="*" valign="top">
                          <div align="center"> <h1><%=application.getAttribute("site-map")%></h1> <br /> </div>
                         
                        <table border="0" cellpadding="5" cellspacing="5">
                                   <%
                                    statement = connection.createStatement();
                                    rs = statement.executeQuery("SELECT * FROM item WHERE ITemType = 1;");
                                    while(rs.next())
                                    {%>
                                            <tr>
                                                <td>
          <a href="items.jsp?Id=<%= rs.getString("ItemId")%>"><strong><%= rs.getString("ItemName")%></strong> - <%= rs.getString("ItemSEOTitle")%></a>
                                                </td>
                                            </tr>
                                    <% }%>
                        </table>
                    </TD>
                </TR>
                
		</TBODY>
	</TABLE>
        </div>
        <div id="right">
        </div>
    </div>    
    
    <div id="bottomNoSide"></div>

<%@include file="index_footer.jsp" %> 


  </div>
  
  
</body>
</html>

