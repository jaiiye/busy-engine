<%@ page import="java.util.*" %>
<%@ page import="com.transitionsoft.*"%>
<%@ page import="com.transitionsoft.dao.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8;charset=utf-8" />
        <%@include file="meta-tags.jsp" %>
        <%@include file="includes.jsp" %>
        <script type="text/javascript">
            //build menu with DIV Id="myslidemenu" on page:
            droplinemenu.buildmenu("mydroplinemenu");
        </script>
    </head>
    <body>
        <div id="wrapper">
            <%@include file="store_header.jsp" %> 
            <div id="topbarRightSide"></div>
            <div id="contentRightSide">
                <div id="rightStore" style="font-size:smaller">
                    <strong>Notes:</strong>
                    <p>
                        <strong>1.</strong> <%=application.getAttribute("cart-message-1")%><br />
                        <strong>2.</strong> <%=application.getAttribute("cart-message-2")%><br />
                        <strong>3.</strong> <%=application.getAttribute("cart-message-3")%><br />
                        <strong>4.</strong> <%=application.getAttribute("cart-message-4")%><br />
                        <strong>5.</strong> <%=application.getAttribute("cart-message-5")%><br />
                        <br />
                        <%=application.getAttribute("cart-message-7")%>
                        <br />
                        <br /> 
                        <%=application.getAttribute("cart-message-8")%>  
                    </p>
                </div>
                <div id="body">
                    <div id="titleGraphic" align="center"></div>
                    <div id="featuredDesigns">
                        <table id="Table1" width="70%" border="0" cellpadding="0" cellspacing="0">
                            <tbody>
                                <tr>
                                    <td style="padding-left: 15px;" valign="top">
                                        <div align="center">
                                            <div style="background-image: url('images-site/shopping_cart_title.jpg'); background-repeat: no-repeat; width: 75%; height: 52; position: relative; margin-left: auto; margin-right:auto; padding: 25px; border:0; margin-top: 20px; font-size:22px; font-weight:bold; color:#FFF; text-transform:uppercase"><%=application.getAttribute("shopping-cart")%>
                                            </div>
                                            <a href="info.jsp?id=3"><b><%=application.getAttribute("easy-returns")%></b></a> | <a href="info.jsp?id=4"><b><%=application.getAttribute("secure-shopping")%></b></a> | <a href="info.jsp?id=5"><b><%=application.getAttribute("privacy-policy")%></b></a>            
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <br />
                        <%
                        if (request.getParameter("error") != null)
                        {
                        %>
                            <div style="color:#F00; font-size:16px">
                                <strong>Processor: </strong> 
                                <br /><%= request.getParameter("error")%><br />Please Try Again!
                            </div>				
                        <%
                        }
                        %>
                        <%@include file="cart_shopping.jsp" %>  
                        <%
                            // Get the current shopping cart from the user's session.
                            // If the user doesn't have a shopping cart yet, create one.
                            if (session.getAttribute("ShoppingCart") == null || ((ShoppingCart) session.getAttribute("ShoppingCart")).getItems().size() == 0)
                            {
                            } 
                            else
                            {
                        %>

                        <br /><br />
                        <div style="font-weight:bold; font-size:24px; text-transform:uppercase; margin-left:250px">
                            <%=application.getAttribute("checkout")%>
                        </div>
                        <div align="center" style="width:75%">
                            <hr />
                            <form name="checkout" method="post" onSubmit="javascript:CheckPaymentMethod();">						
                                <input type="radio" name="PaymentOption" value="PayPal" checked="checked"/>
                                <img src="https://www.paypal.com/en_US/i/logo/PayPal_mark_37x23.gif" style="margin-right:7px;">
                                <span style="font-size:11px; font-family: Arial, Verdana;">The safer, easier way to pay!</span><br />
                                <input type="radio" name="PaymentOption" value="Credit"/>
                                <img src="images-site/credit_cards.png" style="margin-right:7px;">	
                                <span style="font-size:11px; font-family: Arial, Verdana;">Credit or Debit</span>
                                <div align="right">
                                    <input type="submit" class="xbutton" name="commit" value="Checkout"/>
                                </div>	            
                            </form>
                        </div>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>    
            <div id="bottomRightSide"></div>
            <%@include file="store_footer.jsp" %> 
        </div>

        <script language="JavaScript">
            function CheckPaymentMethod()
            {
                //PayPal option
                if (document.checkout.PaymentOption[0].checked)
                {
                    //alert('PayPal payment method chosen');
                    document.checkout.action = 'ExpressCheckout';
                    document.checkout.submit();
                }
                //Credit Option
                else if (document.checkout.PaymentOption[1].checked)
                {
                    //alert('Credit card payment method chosen');
                    document.checkout.action = 'checkout.jsp';
                    document.checkout.submit();
                }
            }
            function checkPayPal()
            {
                if (document.paypal.chkAgree.checked)
                {
                    //good!
                }
                else
                {
                    alert('You have to click agree check box before ordering.');
                    document.paypal.chkAgree.focus();
                    return;
                }
                document.paypal.action = 'ExpressCheckout';
                document.paypal.submit();
            }
            function popUp2(URL)
            {
                day = new Date();
                id = day.getTime();
                eval("page" + id + " = window.open(URL, '" + id + "', 'toolbar=0,scrollbars=1,location=0,statusbar=0,menubar=0,resizable=0,width=800,height=800,left = 0,top = 0');");
            }
            function popUp(URL)
            {
                day = new Date();
                id = day.getTime();
                eval("page" + id + " = window.open(URL, '" + id + "', 'toolbar=0,scrollbars=1,location=0,statusbar=0,menubar=0,resizable=0,width=600,height=500,left = 0,top = 0');");
            }
            function GetShipping()
            {
                var details = document.getElementById("shippingrules");

                if (document.shop.cboSCountry.options[document.shop.cboSCountry.selectedIndex].value != 'usa')
                {
                    alert("international (EMS) shipping must be chosen");
                    document.shop.txtShippingText.value = "EMS";
                    document.shop.chkSMethod[0].disabled = true;
                    document.shop.chkSMethod[1].disabled = true;
                    document.shop.chkSMethod[2].checked = true;
                }
                else
                {
                    document.shop.chkSMethod[0].disabled = false;
                    document.shop.chkSMethod[1].disabled = false;
                    document.shop.chkSMethod[0].checked = true;
                }
            }
            function PopWindowShipping()
            {
                day = new Date();
                id = day.getTime();
                eval("page" + id + " = window.open(URL, '" + id + "', 'toolbar=0,scrollbars=1,location=0,statusbar=0,menubar=0,resizable=0,width=490,height=410,left = 0,top = 0');");
            }
            function SelectShp(mystr)
            {
                document.getElementById("shipprice").innerHTML = mystr;
            }
            function popSize(URL)
            {
                day = new Date();
                id = day.getTime();
                eval("page" + id + " = window.open(URL, '" + id + "', 'toolbar=0,scrollbars=1,location=0,statusbar=0,menubar=0,resizable=0,width=800,height=480,left = 0,top = 0');");
            }
    </script>

  </body>
</html>