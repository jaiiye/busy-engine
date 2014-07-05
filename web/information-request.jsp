<%!
    String testNull(String sessionVariable)
    {
        if(sessionVariable != null)	     
        {  
            return sessionVariable;    
        }
        else
        {
            return "";
        }     		
    }
%> 

<%!
    String testRadioButton(String sessionVariable, String value)
    {
        if(sessionVariable != null)	     
        {  
            if(sessionVariable.equals(value))
            {
                return "checked";    
            }
            else
            {
                return "";
            }
        }
        else
        {
            return "";
        }     		
    }
%> 

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

    <div id="topbarRightSide">
    </div>
    
    <div id="contentRightSide">
        <div id="left" style="padding:10px 10px 20px 20px; width:570px">
 <h1><%=request.getParameter("purpose")%> Request Form:</h1>
 <br />
 
        <table width="100%" cellpadding="0" cellspacing="3">
        <tr>
            <td width="24%" rowspan="6" valign="top" >
				<%                
					if(request.getParameter("error") != null)
					{				
					%>
					<div style="color:#F00; font-size:16px"><strong><%=application.getAttribute("register-error")%></strong>: 
                                        <br />
					<%= request.getParameter("error")%>
					
					</div>				
					<%
					}
					if(request.getParameter("msg") != null)
					{
					%>
					<h3><%= request.getParameter("msg")%></h3>				
					<%
					}
                %>
            </td>
        </tr>        
        </table> 
                    
                    
			<form action="ProcessForm" method="post">
                  <h3>Required Information:</h3>
                  
                  <div style="background-color:#eee; border: 1px solid #AAA; padding-top:10px;padding-bottom:10px;">

                  <table width="100%" cellpadding="0" cellspacing="3">
                  <tr>
                  	<td style="text-align: right">Full Name:</td>
                  	<td><input name="name" value="<%=testNull((String)session.getAttribute("name")) %>" maxlength="30" type="text" /></td>
                        
                        


                  	<td style="text-align: right">&nbsp;</td>
                  	<td>&nbsp;</td>
                  </tr>
                  <tr>
                  	<td style="text-align: right">&nbsp;</td>
                  	<td>&nbsp;</td>
                  	<td style="text-align: right">&nbsp;</td>
                  	<td>&nbsp;</td>
                  </tr>
                  <tr>
                  	
                  	<td style="text-align: right">Address:</td>
                  	<td><input name="address" value="<%=testNull((String)session.getAttribute("address")) %>" maxlength="30" class="claim_input"  type="text" /></td>
                    <td style="text-align: right">City:</td>
                  	<td><input name="city" value="<%=testNull((String)session.getAttribute("city")) %>" maxlength="30" class="claim_input"  type="text" /></td>
                  </tr>                  
                  <tr>
                  	<td style="text-align: right">State: </td>
                  	<td><input name="state" value="<%=testNull((String)session.getAttribute("state")) %>" maxlength="30" class="claim_input"  type="text" /></td>
                  	<td style="text-align: right">Zip Code:  </td>
                  	<td><input name="zip" value="<%=testNull((String)session.getAttribute("zip")) %>" size="10" class="claim_input"  type="text" /> </td>
                  </tr>
                  
                  <tr>
                  	<td style="text-align: right">&nbsp;</td>
                  	<td>&nbsp;</td>
                  	<td style="text-align: right">&nbsp;</td>
                  	<td>&nbsp;</td>
                  </tr>
                  
                  <tr>
                  	<td style="text-align: right">Phone: </td>
                  	<td><input name="phone" value="<%=testNull((String)session.getAttribute("phone")) %>" maxlength="30" class="claim_input"  type="text" /></td>
                  	<td style="text-align: right">&nbsp;</td>
                  	<td>&nbsp;</td>
                  </tr>
                  <tr>
                  	<td style="text-align: right">E-mail Address:</td>
                  	<td colspan="3"><input name="email" value="<%=testNull((String)session.getAttribute("email")) %>" size="40" class="claim_input"  type="text" /></td>
                  </tr>
                  
                  
                  <tr>
                  	<td style="text-align: right">Request Details:</td>
                  	<td colspan="3"><textarea name="details" cols="50" rows="10" class="claim_input"><% if(request.getParameter("purpose") != null) {  %>I would like to receive a <%= request.getParameter("purpose") %> for the following product: <%= request.getParameter("name") %>
<% } %></textarea></td>
                  </tr>
                  	
                  </table>  
  </div>
                  
  

    
 
  <table align="center" width="90%">
  
  <tr>
	<td>&nbsp;</td>
	
	<td>&nbsp;</td>
</tr>
<td colspan="2" align="center">
  		<input value=" Submit Form " type="submit" /> <input type="reset" value=" Reset "/>
</td>
</tr>
</table>                  
<br />

  </form>
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
