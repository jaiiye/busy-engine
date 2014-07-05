
<%@ page import="java.util.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@include file="code_header.jsp" %>    

<%
String url = Database.getSiteURL();
StoreInfo info = Database.getStoreInfo("1");
%>


                <a href="<%=url%>/index.jsp"><img src="<%=url%>/images-site/<%= info.getLogoImage()%>" alt="<%= info.getLogoTitle()%>" border="0"></a><br />

      			<table cellSpacing="0" cellPadding="0" width="100%" border="0">
          		<tbody>
               		<tr>                    
                    	<td width="*" bgColor="#ffffff" height="25"1 valign="top">


                      <table width="100%" border="0" cellspacing="0" cellpadding="5">
                        <tr>
                            <td rowspan="2" width="60%" align="center">                                  
                                
                                
                                <%
                                    if (request.getParameter("Id") == null) {   response.sendRedirect("error.jsp");  } 
                                    else {
                                        int i = 1;
                                        for(Image img : Database.getItemImages(request.getParameter("Id")))
                                        {
                                %>      
                                            <div id="item<%= i %>" style="display:<% if (i == 1){ %>block<% } else { %>none<% } %>"> 
                                                <img src="<%=url%>/items/<%= img.getImageFileName()%>" alt="<%= img.getImageDescription()%>" border="0" width="500px" />
                                            </div>
                                <%
                                            i++;
                                        }
                                    }
                                %>
                                        
                           </td>
    
    
			<%
                            if(request.getParameter("Id") == null || request.getParameter("Id").equals("-1"))
                            {
                                response.sendRedirect("error.jsp");
                            }
                           else
                           {   
				Item i = Database.getItemById(request.getParameter("Id"));

                            %>         
                                <td bgcolor="#CCCCCC" width="*">
                                	<b><%= i.getItemName()%></b>
                                </td>
                           </tr>
                           <tr>           
                             	<td valign="top"> 
                                     <p><b>Description: </b><%=Database.decodeHTML(i.getItemDescription())%></p><br /><br /> 
                                     Price: $<span style="font-size:18px"><%=i.getItemPrice() %></span>
                                  <hr /> 
                             	</td> 	
                           </tr>   
                            <%
                           }
                            %> 


                      <tr align="center">
                        <td colspan="2" valign="top">                            
                        <%
                        if(request.getParameter("Id") == null)
                        {
                            response.sendRedirect("error.jsp");
                        }
                        else
                        {
                                statement = connection.createStatement();
                                String query = "SELECT ItemThumbnailImage FROM item_image WHERE ItemId = " + request.getParameter("Id") + ";";
                                rs = statement.executeQuery(query);
                                int i = 1;
                                while(rs.next())
                                {
                        %>               
                                    <img class=imageBorder onMouseOver="HideAllDIV();DisplayDIV('item<%= i %>')" src="<%=url%>/items/<%= rs.getString(1)%>" border="0">&nbsp;
                        <%
                            	    i++;
                            	} 
                        }
                        %> 
                        
                        </td>
                      </tr>
                    </table>

                    </td>
                </tr>
                
                <tr>
                <td>
                <hr />
                <div align="center">
	                <%= Database.getPageContentByName("eBay") %>
                </div>
                </td>
                
                </tr>                
               
            </tbody>
        </table>