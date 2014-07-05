
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.transitionsoft.*"%>
<%@ page import="com.transitionsoft.dao.*"%>
<%@ page import="java.math.BigDecimal" %> 
<%@ page import="java.util.Map" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8;charset=utf-8" />

        <%@include file="meta-tags.jsp" %>
        <%@include file="includes.jsp" %>

        <script type="text/javascript" src="scripts/checkout.js"></script> 
        <script type="text/javascript">
            //build menu with DIV Id="myslidemenu" on page:
            droplinemenu.buildmenu("mydroplinemenu");
        </script>
    </head>
    <body>

        <div id="wrapper">        
            <%@include file="store_header.jsp" %>

            <div id="topbarNoSide">        
            </div>


            <div id="contentNoSide">            
                <div id="body">

                    <h2>&nbsp;&nbsp;&nbsp;&nbsp;Order Confirmation</h2>                    
                    <div id="titleGraphic" align="center">       
                    </div>

                    <div id="featuredDesigns">

                        <%
                            // Get a formatter to write out currency values.
                            NumberFormat currency = NumberFormat.getCurrencyInstance();
                            NumberFormat formatter = new DecimalFormat("0.00");

                            int numItems = 0;
                            int totalQty = 0;

                            // Get the current shopping cart from the user's session.
                            ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");

                            // If the user doesn't have a shopping cart yet, create one.
                            if (cart == null)
                            {
                                cart = new ShoppingCart();
                                session.setAttribute("ShoppingCart", cart);
                            }
                            
                            /*==================================================================
                             PayPal Express Checkout Call
                             5b. Save the total payment amount in a Session variable named "Payment_Amount". 
                             5c. Process the information returned by your code and complete your order processing.
                             ===================================================================
                             */

                            PayPalFunctions PayPal = new PayPalFunctions(Database.getPayPalInfo());

                            /*
                             ' Get the token parameter value stored in the session from the previous SetExpressCheckout call
                             */
                            String token = (String) session.getAttribute("TOKEN");

                            if (token == null)
                            {
                                System.out.println("Token was NULL, trying the request");
                                token = (String) request.getAttribute("token");
                            }

                            String orderTime = "";
                            String amt = "";
                            String orderId = "";

                            if (token != null)
                            {
                                HashMap shipping_nvp = PayPal.GetShippingDetails(session, token);

                                String strAck = shipping_nvp.get("ACK").toString();
                                if (strAck != null && (strAck.equalsIgnoreCase("Success") || strAck.equalsIgnoreCase("SuccessWithWarning")))
                                {
                                    cart.setShippingAddress(PayPal.getShippingAddress(shipping_nvp));
                                    cart.finalizeTax();
                                } 
                                else
                                {
                                    // Display a user friendly Error on the page using any of the following error information returned by PayPal
                                    System.out.println("Error occured on the confirmation.jsp page in getting shipping details:");
                                    System.out.println("     Code: " + shipping_nvp.get("L_ERRORCODE0").toString());
                                    System.out.println("Short Msg: " + shipping_nvp.get("L_SHORTMESSAGE0").toString());
                                    System.out.println(" Long Msg: " + shipping_nvp.get("L_LONGMESSAGE0").toString());
                                    System.out.println(" Severity: " + shipping_nvp.get("L_SEVERITYCODE0").toString());
                                    System.out.println("-----------------------------------------------");
                                }
                            }

                        %>

                        <table width="75%" border="0" cellpadding="0" cellspacing="0">
                            <tbody>
                                <tr>
                                    <td>
                                        <%@include file="cart_checkout.jsp" %>  
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <table width="80%" border="0" cellpadding="0" cellspacing="2">
                                            <tbody>
                                                <tr>
                                                    <td valign="top" width="50%" bgcolor="#cccccc"><font class="cart"><b>Shipping Information</b></font></td>
                                                    <td valign="top" width="*" bgcolor="#cccccc"><font class="cart"><b>Select Shipping Method</b></font></td>
                                                </tr>
                                                <tr>        
                                                    <td valign="top" bgcolor="#eeeeee">
                                                        <table width="100%" border="0" cellpadding="0" cellspacing="3">
                                                            <tbody>
                                                                <tr>
                                                                    <td>
                                                                        <p><strong><%=cart.getShippingAddress().getFirstName()%> <%=cart.getShippingAddress().getLastName()%></strong></p>
                                                                        <p><%=cart.getShippingAddress().getAddress1()%>,<br>
                                                                                <%=cart.getShippingAddress().getCity()%>, <%=cart.getShippingAddress().getState()%> <%=cart.getShippingAddress().getPostalCode()%><br><%=cart.getShippingAddress().getCountry()%>
                                                                                    <br><br><%=cart.getShippingAddress().getEmail()%></p>                        
                                                                                            </td>
                                                                                            </tr>
                                                                                            </tbody>
                                                                                            </table>
                                                                                            </td>
                                                                                            <td valign="top" bgcolor="#eeeeee">                
                                                                                                <form name="shipping" action="Order" method="post">
                                                                                                    <table width="100%" border="0" cellpadding="0" cellspacing="3">
                                                                                                        <tbody>
                                                                                                            <tr>
                                                                                                                <td>
                                                                                                                    <input value="0" name="txtShippingText" type="hidden">                       
                                                                                                                    <%
                                                                                                                        int i = 1; //means no default is chosen                                
                                                                                                                        for (ShippingMethod sm : Database.getShippingMethods())
                                                                                                                        {
                                                                                                                    %>
                                                                                                                    <input onClick="SelectShp('<%= sm.getMethodId()%>',<%= sm.getShippingRate()%>,<%= sm.getShippingAdditional()%>,<%=(totalQty - 1)%>,<%=formatter.format(cart.getTotalPrice())%>)" value="<%= sm.getShippingRate()%>" name="chkSMethod" type="radio" <% if (i == 0) { out.print(" checked "); i++; } %>> $<%= sm.getShippingRate()%> - <%= sm.getShippingMethod()%><br>
                                                                                                                    <%  }%>  
                                                                                                                </td>
                                                                                                            </tr>
                                                                                                            <tr>
                                                                                                                <td align="center">                        
                                                                                                                    <input type="button" value="Pay" onclick="checkShipping()" />
                                                                                                                </td>
                                                                                                            </tr>                        
                                                                                                        </tbody>
                                                                                                    </table>
                                                                                                </form>
                                                                                                <span id="PayPal"></span><span id="CreditCard"></span>

                                                                                            </td>
                                                                                        </tr>
                                                                                    </tbody>
                                                                                </table>

                                                                            </td>
                                                                        </tr>

                                                                        </tbody>
                                                                    </table>
                                                                </div>
                                                            </div>  
                                                        </div>    
                                                        <div id="bottomNoSide"></div>
                                                        <%@include file="store_footer.jsp" %> 
                                                    </div>
                                                </body>
                                            </html>
