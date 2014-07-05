<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ page import="java.util.*,com.transitionsoft.*,com.transitionsoft.dao.*" %>
<%@include file="header.jsp" %> 


<style>
	#topBottom 
	{  
		border-top:1px solid #D6D2BD !important;
		border-bottom:1px solid #D6D2BD !important;
		background:#F5F3DD !important;
		font-weight:bold;
	}
</style>

<%! 
public String getCurrentDate() throws java.text.ParseException 
{    
	java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");    
	java.util.Date tDate = new java.util.Date();  
	return df.format(tDate);   
}     
%>

<%
    ArrayList<User> users = Database.getUserWithServices(request.getParameter("uid"));
    ArrayList<UserService> services = users.get(0).getUserServices();
%>

<table border="0" width="99%" cellspacing="0" cellpadding="0" align="center">
<tr>
	<td colspan="5">
    	<h1>User Info</h1>
    </td>
</tr>
<tr>
	
	<td valign="top" colspan="2">
	<div id="columnTwo">
            
<%            
    for(User user : users)
    {            
%>            
	<div class="node">
		<table border="0" width="99%" cellpadding="5" cellspacing="0">
			<tr>
				<td width="35%"><div id="topBottom">Service Info</div></td>
				<td width="30%" colspan="2"><div id="topBottom">Details</div></td>
				<td width="*" align="right"><div id="topBottom">Links</div></td>
			</tr> 
               
               <%
            	//for each user, output their ordered services information
                    for(UserService us : services)
                    {            
            	%>
                <tr class="even">
                        <td>
                            <strong>Id: </strong><%= us.getServiceId() %><br />
                            Start: <%= us.getStartDate().substring(0,11)%><br />
                            &nbsp;End: <%=us.getEndDate().substring(0,11)%><br /><br />
                            &nbsp;Deposit: $<%=us.getDepositAmount()%><br />
                        </td> 
                        <td colspan="2" valign="top">
                             <%=us.getDetails()%><br /> 
                        </td> 
                        <td width="25%" align="right">
                        
                        <a href="<%= us.getDeliverableUrl() %>">View Deliverables</a><br />
                        <a href="<%= us.getContractUrl() %>">View Contract</a><br />                                                       
                        </td>  
                </tr>  
                
                <tr>
                	<td colspan="4">
                    <%
                    for(Post p : Database.getPostsByUserId(user.getUserId() + ""))
                    {
                    %>
                    <table cellSpacing="0" cellPadding="5" width="90%" align="center" border="0">
                        <tr> 
                            <td colspan="2" bgcolor="#CCCCCC" >
                                <h2><%= p.getPostTitle()%></h2>
                                <h4 style="float:left; margin: 0 0 0 0;">&nbsp;posted on &nbsp;<%= p.getPostDate().substring(0, 10)%></h4>
                                <a onclick="generateCommentDiv(<%= p.getPostId()%>,'<%= getCurrentDate().substring(0, 19)%>',<%= us.getBlogId()%>,'${sessionScope.username}');" style="cursor:hand">Post Comment</a>
                            </td>
                        </tr>

                        <tr>
                            <td width="70%" valign="top">				                                
                                <strong>Tags:</strong> <%= p.getPostTags()%><hr />
                                <div style="float:left; margin: 5px;"><img class="shadow" src="<%= p.getPostPicURL()%>" border="0"/></div>
                                <h5><%= p.getPostBody()%></h5>
                            </td>
                            <td width="*" valign="top">
                                <%
                                    for (Comment c : p.getComments()) {
                                %>                            
                                    <div style="border: 1px #D6D2BD solid; overflow: cursor; padding: 5px;">
                                        <strong><%= c.getCommentTitle()%></strong> by <%= user.getUserName() %> posted on &nbsp;<%= c.getCommentDate().substring(0, 10)%><br />
                                            <%= c.getCommentBody()%><hr />                   
                                    </div>
                                <%   }   %>                                                          
                            </td>                   
                        </tr>

                    </table>
                             <%  } %>
                    </td>
                </tr>                  
				
                <%
                }
                %>
                
             
            
          
			
												
		</table>
	</div> 
    <br />
    
    
     <%
		}
	%>
    
	  </div>
			</td>
		</tr>
	</table> 
    
    
<%@ include file="footer.jsp" %>