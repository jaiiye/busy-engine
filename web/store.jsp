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
		</script>
	</head>
<body>

<div id="wrapper">

<%@include file="store_header.jsp" %> 
  
  <div id="topbarNoSide">
<%
int pageNumber = 1;
int itemsPerPage = 50;
int count = 0;
int i = 1;
int itemType = 1;

if(request.getParameter("page") == null)
{
    pageNumber = 1;
	count = Database.getAllItemsCount(); 
}
else 
{
   pageNumber = Integer.parseInt(request.getParameter("page"));
   count = Database.getAllItemsCount(); 
}
%>
    </div>
    
    <div id="contentNoSide">
    
    
<div id="body">


    <div id="titleGraphic">
    <h2>&nbsp;&nbsp;Featured Products:</h2>
</div>
        
     <div id="featuredDesigns">
     
    

        
                                     
       <table cellpadding="5" cellspacing="0" border="0" width="80%">
                <tbody>
                    <tr>
						<%
                        ArrayList<Item> items = Database.getItems(itemType, itemsPerPage, pageNumber);
                        i = 1;
                        for(Item i1 : items)
                        {
                        %>
                            <td valign="top">
                                <SCRIPT type="text/javascript">
                                    MakeThumbnailTable("item<%= i %>", "<%=i1.getItemImageName()%>", "<%=i1.getItemId()%>", "<%=i1.getItemName()%>", "<%=i1.getItemMSRP()%>", "<%=i1.getItemPrice()%>", "<%=i1.getItemBrandName()%>"); 
                                </SCRIPT>
                            <% if (i%2 == 0) { %></tr><tr><% } %>
                        <%
                        i++;
                        } 
                        %> 

                		</tr>                	
                	</tbody>
                </table>
                
                
       <table cellspacing="0" cellpadding="0" width="93%"  border="0" align="center" >
         <tbody>                                           
             <tr>
                <td width="600" height="50">
                              
                                
                <div id="newsletterDiv">
          
                    <form name="newletterbox" action="subscribe.jsp" method="post">
                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                        <tr>
                                <td class="tabLeft" colspan="2">
                                &nbsp;&nbsp;<%=application.getAttribute("index-middle-bar-newsletter-title")%>                                
                                </td>
                         </tr>
                            <tr>
                                <td>
                                <div style="margin-left:5px;">
                                <input tabindex="1" type="text" size="16" style="font-size:10px;width:200px" name="EMailAddress" value="<%=application.getAttribute("index-middle-bar-newsletter-text")%>"><br />                    	
                                </div>
                                </td>			
                                <td align="left" width="100%">
                                <div class="tabLeft" style="margin-left:4px;margin-right:5px;">
                                <input tabindex="2" type="submit" value="<%=application.getAttribute("index-middle-bar-newsletter-button")%>">
                                </div>
                                </td>			
                            </tr>
                        </table>
                    </form>
                </div>
                </td>
            </tr>                           
        </tbody>
    </table>
    </div>
        <div style="background: #e5e5e5; border: 1px solid #ccc; padding: 5px 10px; margin-bottom: 5px; width:55%; margin-left:8px; margin-right:auto; margin-top: 5px; text-align:center">
        <script type="text/javascript">
 MakeLinks(<%= count%>, <%= "\"index.jsp\""%>, <%= itemsPerPage%>);
 </script>                   
</div>




        </div>
    </div>    
    
    <div id="bottomNoSide"></div>

<%@include file="store_footer.jsp" %> 


  </div>
  
  
</body>
</html>