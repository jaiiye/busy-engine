
       <div id="footer" style="height:100px">
<div style="clear:left">
<table cellpadding="0" cellspacing="0" border="0" width="90%"> 

    
    
            <tr>
                <td align="center" style="width:25%;"><strong><b class="XLargeBlackB">&nbsp;<%=application.getAttribute("footer-payment-title")%></b></strong></td>
              <td align="center" style="width:25%;"><b class="XLargeBlackB"><%=application.getAttribute("footer-links-title")%></b></td>
              <td align="center" style="width:25%;"><strong><b class="XLargeBlackB"><%=application.getAttribute("footer-links-title")%></b></strong></td>
                
            </tr>
            
            <tr>
                <td height="10" colspan="4" align="center"></td>
            </tr>
            
            <tr>
                <td align="center" valign="top"><img src="images-site/paypal.gif" border="0" alt="We accept all major credit cards including PayPal!"  />
                   
                </td>
                <td align="center" valign="top">					
                    <div style="margin-bottom:5px">
                        <a href="cart.jsp" class="MediumBlackBNU" target="_top"><%=application.getAttribute("footer-account-link-1")%></a>
                    </div>
                    <div style="margin-bottom:5px">
                        <a href="info.jsp?id=7" class="MediumBlackBNU" target="_top"><%=application.getAttribute("footer-account-link-2")%></a>
                    </div>
                    
                    <div style="margin-bottom:5px">
                        <a href="xx" class="MediumBlackBNU" target="_top"><%=application.getAttribute("footer-account-link-3")%></a>
                    </div>					
                </td>
                <td align="center" valign="top" > <div style="margin-bottom:5px">
                        <a href="info.jsp?id=3" class="MediumBlackBNU" target="_top"><%=application.getAttribute("footer-links-link-1")%></a>
                   </div>
                        
                    <div style="margin-bottom:5px">
                        <a href="sitemap.jsp" class="MediumBlackBNU" target="_top"><%=application.getAttribute("footer-links-link-2")%></a>
                    </div>
                    
                    <div style="margin-bottom:5px">
                        <a href="info.jsp?id=1" class="MediumBlackBNU" target="_top"><%=application.getAttribute("footer-links-link-3")%></a>
                    </div></td>				
            </tr>
        </table>
</div>

<div align="center" style="width:90%;"><!--start links div -->
    <div class="smBlkText" style="margin-top:10px; margin-bottom:5px; color:#CCC">                    
        <a href="info.jsp?id=4" class="smBlkText" target="_top"><%=application.getAttribute("terms-of-use")%></a>
        &nbsp;&nbsp;&nbsp;<%=application.getAttribute("copyright")%> 2011 Armolan, <%=application.getAttribute("all-rights-reserved")%>.&nbsp;&nbsp;&nbsp;
        <a href= "info.jsp?id=5" class="smBlkText" target="_top"><%=application.getAttribute("privacy-policy")%></a>
        <br />
        <a href="admin/index.jsp"><img src="images/clear.gif" width="10" height="10" border="0" /></a>
    </div>             
    
    <br clear="left">
    <img src="images/clear.gif" height="5" width="8" border="0" />
</div><!--end links div -->

</div><!--end main container div -->


 </div>

<% 
    connection.close();   
    statement.close();
%>


    	
   