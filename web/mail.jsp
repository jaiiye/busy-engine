
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
        <div id="left">
<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
      <TBODY>
           <TR>
                <TD width="*" bgColor=#ffffff height=251 valign="top">
                <h1>&nbsp;&nbsp;E-Main Information:</h1>
                        <form id="EMailAFriendForm" style="MARGIN: 0px" name="form1" action="ProcessForm" method="post">
                            <table class="tableBorderGray" cellspacing="0" cellpadding="0" width="100%" border="0">
                                <tbody>
                                    <tr>
                                    <td valign="top" align="left"><table class="leftCellGrayBg" cellspacing="0" cellpadding="0" width="100%" border="0">
                                        <tbody>
                                            <tr>
                                                <td class="bgBlue" valign="center" align="middle" 
                                                width="3%">&nbsp;1.</td>
                                                <td class="bgFormHead" valign="center" align="left" width="97%">Required Information:</td>
                                            </tr>
                                            <tr>
                                                <td width="3%">&nbsp;</td>
                                                <td valign="top" align="left" width="97%" height="11"><table cellspacing="0" cellpadding="0" width="100%" 
                                                    border="0">
                                                    <tbody>
                                                        <tr>
                                                            <td width="100%">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td valign="top" align="left" width="100%"><table cellspacing="0" cellpadding="1" width="100%" 
                                                                border="0">
                                                                <tbody>
                                                                    <tr>
                                                                        <td valign="center" align="right" width="30%"><span class="textRed">*</span>Name:</td>
                                                                        <td valign="center" align="left" width="70%"><input name="FullName" /></td>
                                                                    </tr>
                                                                                                                                                                                              <tr>
                                                                        <td valign="center" align="right" width="30%"><span class="textRed">*</span>Email Address:</td>
                                                                        <td valign="center" align="left" width="70%"><input name="EMailAddress" size="26" /></td>
                                                                    </tr>
                                                                        
                                                                                                                                                                        
                                                                </tbody>
                                                            </table></td>
                                                        </tr>
                                                        <tr>
                                                            <td width="100%">&nbsp;</td>
                                                        </tr>
                                                    </tbody>
                                                </table></td>
                                            </tr>
                                            <tr>
                                                <td width="3%"></td>
                                                <td width="97%"></td>
                                            </tr>
                                            <tr>
                                                <td width="3%" rowspan="3">&nbsp;</td>
                                                <td width="97%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td valign="top" align="left" width="97%">                                                    </td>
                                            </tr>
                                            <tr>
                                                <td width="97%" height="12"></td>
                                            </tr><tr>
                                                <td class="bgBlue" valign="center" align="middle" 
                                                width="3%"><b>&nbsp;</b>2.</td>
                                                <td class="bgFormHead" valign="center" align="left" 
                                                width="97%">Message Details:</td>
                                            </tr>
                                            <tr>
                                                <td valign="top" align="middle" width="3%">&nbsp;</td>
                                                <td width="97%" height="9">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td valign="top" align="middle" width="3%">&nbsp;</td>
                                                <td valign="top" align="left" width="97%" height="12"><table cellspacing="0" cellpadding="0" width="100%" 
                                                    border="0">
                                                    <tbody>
                                                        <tr>
                                                            <td valign="top" align="left" width="100%">
                                                                <textarea name="msg" rows="4" cols="52"><% if(request.getParameter("purpose") != null) {  %>I found an interesting product: <%= request.getParameter("brand") %> <%= request.getParameter("name") %>
<% } %></textarea>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table></td>
                                            </tr>
                                            <tr>
                                                <td valign="top" align="middle" width="3%">&nbsp;</td>
                                                <td width="97%">&nbsp;</td>
                                            </tr>

                                            <tr>
                                                <td width="3%" height="50" rowspan="4">&nbsp;</td>
                                                
                                            </tr>
                                            <tr>
                                                <td valign="center" align="middle" width="97%">
                                                    <input class="bgsubmitButton" type="submit" value="Submit" name="Submit" />
                                                <input  class="bgsubmitButton" type="reset" name="Reset" value="Reset" />                                                    <br />
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table></td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                </TD>
            </TR>
                
                
		</TBODY>
	</TABLE>
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













