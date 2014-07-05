    
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.transitionsoft.*"%>
<%@ page import="com.transitionsoft.dao.*"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>        
        <%@include file="meta-tags.jsp" %>                
        <%@include file="includes.jsp" %>    

        <link href="images/shopcart.css" rel="stylesheet">

        <script language="JavaScript" src="images/isZipCode.js"></script>
        <script language="JavaScript" src="images/ValidateDate.js"></script>
        <script language="JavaScript" src="images/isEmail.js"></script>        
        <script language="JavaScript" src="scripts/checkout.js"></script>
        <script type="text/javascript">
            //build menu with DIV Id="myslidemenu" on page:
            droplinemenu.buildmenu("mydroplinemenu")
        </script>
    </head>
    <body>

<div id="wrapper">
        <%@include file="store_header.jsp" %> 

        <div id="topbarRightSide">
        </div>

        <div id="contentRightSide">
            <div id="rightStore">

                <strong>Notes:</strong>
                <p>
                    <strong>1.</strong> <%=application.getAttribute("cart-message-5")%><br />
                    <br />
                    <%=application.getAttribute("cart-message-7")%>
                    <br />
                    <br /> 
                    <%=application.getAttribute("cart-message-8")%>  
                </p>

            </div>

            <div id="body">

                <div id="titleGraphic" align="center">
                </div>

                <div id="featuredDesigns">
        <table id="Table1" width="70%" border="0" cellpadding="0" cellspacing="0">
  			<tbody>
  				<tr>
                    <td style="padding-left: 15px;" valign="top">
                    	<div align="center">
		                    <div style="background-image: url('images-site/shopping_cart_title.jpg'); background-repeat: no-repeat; width: 75%; height: 52; position: relative; margin-left: auto; margin-right:auto; padding: 25px; border:0; margin-top: 20px; font-size:22px; font-weight:bold; color:#FFF; text-transform:uppercase"><%=application.getAttribute("checkout")%>
                            </div>            
                        </div>
                    </td>
              </tr>
            </tbody>
        </table>

		<%
            // Get the current shopping cart from the user's session.
            ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");

            // Get a formatter to write out currency values.
            NumberFormat currency = NumberFormat.getCurrencyInstance();
            NumberFormat formatter = new DecimalFormat("0.00");

            int numItems = 0;
            int totalQty = 0;

            // If the user doesn't have a shopping cart yet, create one.
            if (cart == null)
            {
                cart = new ShoppingCart();
                session.setAttribute("ShoppingCart", cart);
            }

        %>
         
		<%@include file="cart_checkout.jsp" %><br />

        <form name="shop" method="post" action="Checkout">
            <table width="75%" border="0" cellpadding="0" cellspacing="2">
                <tbody>
                    <tr>
                        <td>
                            <table width="100%" border="0" cellpadding="0" cellspacing="3">
                                <tbody>
                                    <tr>
                                        <td>			
                                            <%
                                                if (request.getParameter("error") != null)
                                                {
                                            %>
                                            <div style="color:#F00; font-size:16px">
                                                <strong>Processor: </strong> 
                                                <br />
                                                <%= request.getParameter("error")%>	
                                                <br />
                                                Please Try Again!
                                            </div>				
                                            <%
                                                }
                                            %>


                                            <h2>Shipping Method:</h2>
                                            <input value="0" name="txtShippingText" type="hidden">                       
                                                <%
                                                    statement = connection.createStatement();
                                                    rs = statement.executeQuery("SELECT * FROM shipping;");
                                                    int i = 1; //means no default is chosen

                                                    while (rs.next())
                                                    {
                                                %>
                                                <input onClick="SelectShpCheckout('<%= rs.getString(1)%>',<%= rs.getString(4)%>,<%= rs.getString(5)%>,<%=(totalQty - 1)%>,<%=formatter.format(cart.getTotalPrice())%>)" value="<%= rs.getString(4)%>" name="chkSMethod" type="radio" <% if (i == 0)
                        {
                            out.print(" checked ");
                            i++;
                        }%> > $<%= rs.getString(4)%> - <%= rs.getString(3)%><br>
                                                        <% }%>  

                                                        </td>
                                                    </tr>                       
                                                </tbody>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" valign="top" width="70%" bgcolor="#DDD">
                                            <table width="100%" align="center" border="0" cellpadding="0" cellspacing="2"><tbody>
                                                <tr>
                                                    <td valign="top" width="300" bgcolor="#cccccc">
                                                        <font class="cart">
                                                            <b>Shipping Information (NO PO BOXES)</b>
                                                        </font>
                                                    </td>
                                                    <td valign="top" bgcolor="#cccccc">
                                                        <font class="cart">
                                                            <b>Billing Information</b>
                                                        </font>
                                                    </td>
                                                </tr>
                                                    <tr>
                                                        <td valign="top" bgcolor="#eeeeee">
                                                            <table width="100%" border="0" cellpadding="0" cellspacing="3">
                                                                <tbody>
                                                                    <tr>
                                                                        <td>
                                                                            <p class="cart">
                                                                                <font color="#800000" face="Verdana">*</font>First Name: </p>
                                                                        </td>
                                                                        <td>
                                                                            <input style="border: 1px solid rgb(204, 204, 204); padding: 2px; font-weight: bold; font-size: 9px; width: 160px; color: rgb(153, 153, 153); font-family: verdana,arial,helvetica,sans-serif; background-color: rgb(255, 255, 255);" tabindex="1" name="ship_fname" />
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td >
                                                                            <p class="cart"><font color="#800000" face="Verdana">*</font>Last Name:</p></td>
                                                                        <td ><input style="border: 1px solid rgb(204, 204, 204); padding: 2px; font-weight: bold; font-size: 9px; width: 160px; color: rgb(153, 153, 153); font-family: verdana,arial,helvetica,sans-serif; background-color: rgb(255, 255, 255);" tabindex="2" name="ship_lname" /></td></tr>
                                                                    <tr>
                                                                        <td >
                                                                            <p class="cart"><font color="#800000" face="Verdana">*</font>Address 1:</p></td>

                                                                        <td ><input style="border: 1px solid rgb(204, 204, 204); padding: 2px; font-weight: bold; font-size: 9px; width: 160px; color: rgb(153, 153, 153); font-family: verdana,arial,helvetica,sans-serif; background-color: rgb(255, 255, 255);" tabindex="3" name="ship_address1" /></td></tr>
                                                                    <tr>
                                                                        <td >
                                                                            <p class="cart">Address 2:</p></td>
                                                                        <td ><input style="border: 1px solid rgb(204, 204, 204); padding: 2px; font-weight: bold; font-size: 9px; width: 160px; color: rgb(153, 153, 153); font-family: verdana,arial,helvetica,sans-serif; background-color: rgb(255, 255, 255);" tabindex="4" name="ship_address2" /></td></tr>
                                                                    <tr>
                                                                        <td >
                                                                            <p class="cart"><font color="#800000" face="Verdana">*</font>City:</p></td>

                                                                        <td ><input style="border: 1px solid rgb(204, 204, 204); padding: 2px; font-weight: bold; font-size: 9px; width: 160px; color: rgb(153, 153, 153); font-family: verdana,arial,helvetica,sans-serif; background-color: rgb(255, 255, 255);" tabindex="5" name="ship_city" /></td></tr>
                                                                    <tr>
                                                                        <td >
                                                                            <p class="cart"><font color="#800000" face="Verdana">*</font>State:</p></td>
                                                                        <td >

                                                                            <select class="formitem" tabindex="6" name="ship_state">
                                                                                <option value="" selected="selected">Select a State</option>
                                                                                <option value="AL">Alabama</option>
                                                                                <option value="AK">Alaska</option>
                                                                                <option value="AZ">Arizona</option>
                                                                                <option value="AR">Arkansas</option>
                                                                                <option value="CA">California</option>
                                                                                <option value="CO">Colorado</option>
                                                                                <option value="CT">Connecticut</option>
                                                                                <option value="DE">Delaware</option>
                                                                                <option value="FL">Florida</option>
                                                                                <option value="GA">Georgia</option>
                                                                                <option value="HI">Hawaii</option>
                                                                                <option value="Id">Idaho</option>
                                                                                <option value="IL">Illinois</option>
                                                                                <option value="IN">Indiana</option>
                                                                                <option value="IA">Iowa</option>
                                                                                <option value="KS">Kansas</option>
                                                                                <option value="KY">Kentucky</option>
                                                                                <option value="LA">Louisiana</option>
                                                                                <option value="ME">Maine</option>
                                                                                <option value="MD">Maryland</option>
                                                                                <option value="MA">Massachusetts</option>
                                                                                <option value="MI">Michigan</option>
                                                                                <option value="MN">Minnesota</option>
                                                                                <option value="MS">Mississippi</option>
                                                                                <option value="MO">Missouri</option>
                                                                                <option value="MT">Montana</option>
                                                                                <option value="NE">Nebraska</option>
                                                                                <option value="NV">Nevada</option>
                                                                                <option value="NH">New Hampshire</option>
                                                                                <option value="NJ">New Jersey</option>
                                                                                <option value="NM">New Mexico</option>
                                                                                <option value="NY">New York</option>
                                                                                <option value="NC">North Carolina</option>
                                                                                <option value="ND">North Dakota</option>
                                                                                <option value="OH">Ohio</option>
                                                                                <option value="OK">Oklahoma</option>
                                                                                <option value="OR">Oregon</option>
                                                                                <option value="PA">Pennsylvania</option>
                                                                                <option value="RI">Rhode Island</option>
                                                                                <option value="SC">South Carolina</option>
                                                                                <option value="SD">South Dakota</option>
                                                                                <option value="TN">Tennessee</option>
                                                                                <option value="TX">Texas</option>
                                                                                <option value="UT">Utah</option>
                                                                                <option value="VT">Vermont</option>
                                                                                <option value="VA">Virginia</option>
                                                                                <option value="WA">Washington</option>
                                                                                <option value="DC">Washington, D.C.</option>
                                                                                <option value="WV">West Virginia</option>
                                                                                <option value="WI">Wisconsin</option>
                                                                                <option value="WY">Wyoming</option>
                                                                            </select>                          
                                                                        </td></tr>

                                                                        <tr>
                                                                            <td >
                                                                                <p class="cart"><font color="#800000" face="Verdana">*</font>Zip: </p></td>
                                                                            <td ><input style="border: 1px solid rgb(204, 204, 204); padding: 2px; font-weight: bold; font-size: 9px; width: 60px; color: rgb(153, 153, 153); font-family: verdana,arial,helvetica,sans-serif; background-color: rgb(255, 255, 255);" tabindex="7" size="25" name="ship_postalCode"></td></tr>
                                                                        <tr>
                                                                            <td >
                                                                                <p class="cart"><font color="#800000" face="Verdana">*</font>Country:</p></td>

                                                                            <td >
                                                                                <select class="formitem" tabindex="8" onChange="javascript:GetShipping();" name="ship_country">
                                                                                    <option value="CA">Canada</option>
                                                                                    <option value="GB">United Kingdom</option>
                                                                                    <option value="US" selected="selected">United States</option>
                                                                                </select> 

                                                                            </td></tr>
                                                                        <tr>
                                                                            <td >
                                                                                <p class="cart"><font color="#800000" face="Verdana">*</font>Email:</p></td>
                                                                            <td ><input onBlur="javascript:if (!checkEmail(this, false))
focus()" style="border: 1px solid rgb(204, 204, 204); padding: 2px; font-weight: bold; font-size: 9px; width: 160px; color: rgb(153, 153, 153); font-family: verdana,arial,helvetica,sans-serif; background-color: rgb(255, 255, 255);" tabindex="9" name="ship_email" /></td></tr>
                                                                        <tr>
                                                                            <td colspan="2" >
                                                                                <p class="cart"><input onClick="samebill()" value="" name="chk2" type="checkbox" tabindex="10" />Use this address as the billing address. 
                                                                                    <br>
                                                                                </p></td></tr>
                                                                        <tr>
                                                                            <td colspan="2">        
                                                                                <input type="checkbox" name="chkAgree" />        
                                                                                <b>I agree to all the terms and conditions and returns and exchange policies on this website.</b><br>
                                                                                    <br>     
                                                                                        </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td colspan="2" align="center">
                                                                                                <input type="button" name="PayButton" value=" Pay " onclick="PayPal()" />
                                                                                            </td>
                                                                                        </tr>



                                                                                        </tbody></table>
                                                                                        </td>
                                                                                        <td valign="top" bgcolor="#eeeeee" width="*">
                                                                                            <table width="100%" border="0" cellpadding="0" cellspacing="3">
                                                                                                <tbody>
                                                                                                    <td colspan="2"><font color="#000000" face="Verdana"><b>Enter 
                                                                                                                information  as it appears on your credit card 
                                                                                                                statement.</b></font></td></tr>

                                                                                                    <tr>
                                                                                                        <td>
                                                                                                            <p class="cart"><font color="#800000" face="Verdana">*</font>First Name:</p></td>
                                                                                                        <td><input style="border: 1px solid rgb(204, 204, 204); padding: 2px; font-weight: bold; font-size: 9px; width: 160px; color: rgb(153, 153, 153); font-family: verdana,arial,helvetica,sans-serif; background-color: rgb(255, 255, 255);" tabindex="11" name="bill_fname"></td></tr>
                                                                                                    <tr>
                                                                                                        <td>
                                                                                                            <p class="cart"><font color="#800000" face="Verdana">*</font>Last Name:</p></td>

                                                                                                        <td><input style="border: 1px solid rgb(204, 204, 204); padding: 2px; font-weight: bold; font-size: 9px; width: 160px; color: rgb(153, 153, 153); font-family: verdana,arial,helvetica,sans-serif; background-color: rgb(255, 255, 255);" tabindex="12" name="bill_lname"></td></tr>
                                                                                                    <tr>
                                                                                                        <td>
                                                                                                            <p class="cart"><font color="#800000" face="Verdana">*</font>Address 1:</p></td>
                                                                                                        <td><input style="border: 1px solid rgb(204, 204, 204); padding: 2px; font-weight: bold; font-size: 9px; width: 160px; color: rgb(153, 153, 153); font-family: verdana,arial,helvetica,sans-serif; background-color: rgb(255, 255, 255);" tabindex="13" name="bill_address1"></td></tr>
                                                                                                    <tr>
                                                                                                        <td>
                                                                                                            <p class="cart">Address 2:</p></td>

                                                                                                        <td><input style="border: 1px solid rgb(204, 204, 204); padding: 2px; font-weight: bold; font-size: 9px; width: 160px; color: rgb(153, 153, 153); font-family: verdana,arial,helvetica,sans-serif; background-color: rgb(255, 255, 255);" tabindex="14" name="bill_address2"></td></tr>
                                                                                                    <tr>
                                                                                                        <td>
                                                                                                            <p class="cart"><font color="#800000" face="Verdana">*</font>City:</p></td>
                                                                                                        <td><input style="border: 1px solid rgb(204, 204, 204); padding: 2px; font-weight: bold; font-size: 9px; width: 160px; color: rgb(153, 153, 153); font-family: verdana,arial,helvetica,sans-serif; background-color: rgb(255, 255, 255);" tabindex="15" name="bill_city"></td></tr>
                                                                                                    <tr>
                                                                                                        <td>
                                                                                                            <p class="cart"><font color="#800000" face="Verdana">*</font>State:</p></td>

                                                                                                        <td> <select class="formitem" name="bill_state" tabindex="16" >
                                                                                                                <option value="" selected="selected">Select a State</option>
                                                                                                                                <option value="AL">Alabama</option>
                                                                                                                                <option value="AK">Alaska</option>
                                                                                                                                <option value="AZ">Arizona</option>
                                                                                                                                <option value="AR">Arkansas</option>
                                                                                                                                <option value="CA">California</option>
                                                                                                                                <option value="CO">Colorado</option>
                                                                                                                                <option value="CT">Connecticut</option>
                                                                                                                                <option value="DE">Delaware</option>
                                                                                                                                <option value="FL">Florida</option>
                                                                                                                                <option value="GA">Georgia</option>
                                                                                                                                <option value="HI">Hawaii</option>
                                                                                                                                <option value="Id">Idaho</option>
                                                                                                                                <option value="IL">Illinois</option>
                                                                                                                                <option value="IN">Indiana</option>
                                                                                                                                <option value="IA">Iowa</option>
                                                                                                                                <option value="KS">Kansas</option>
                                                                                                                                <option value="KY">Kentucky</option>
                                                                                                                                <option value="LA">Louisiana</option>
                                                                                                                                <option value="ME">Maine</option>
                                                                                                                                <option value="MD">Maryland</option>
                                                                                                                                <option value="MA">Massachusetts</option>
                                                                                                                                <option value="MI">Michigan</option>
                                                                                                                                <option value="MN">Minnesota</option>
                                                                                                                                <option value="MS">Mississippi</option>
                                                                                                                                <option value="MO">Missouri</option>
                                                                                                                                <option value="MT">Montana</option>
                                                                                                                                <option value="NE">Nebraska</option>
                                                                                                                                <option value="NV">Nevada</option>
                                                                                                                                <option value="NH">New Hampshire</option>
                                                                                                                                <option value="NJ">New Jersey</option>
                                                                                                                                <option value="NM">New Mexico</option>
                                                                                                                                <option value="NY">New York</option>
                                                                                                                                <option value="NC">North Carolina</option>
                                                                                                                                <option value="ND">North Dakota</option>
                                                                                                                                <option value="OH">Ohio</option>
                                                                                                                                <option value="OK">Oklahoma</option>
                                                                                                                                <option value="OR">Oregon</option>
                                                                                                                                <option value="PA">Pennsylvania</option>
                                                                                                                                <option value="RI">Rhode Island</option>
                                                                                                                                <option value="SC">South Carolina</option>
                                                                                                                                <option value="SD">South Dakota</option>
                                                                                                                                <option value="TN">Tennessee</option>
                                                                                                                                <option value="TX">Texas</option>
                                                                                                                                <option value="UT">Utah</option>
                                                                                                                                <option value="VT">Vermont</option>
                                                                                                                                <option value="VA">Virginia</option>
                                                                                                                                <option value="WA">Washington</option>
                                                                                                                                <option value="DC">Washington, D.C.</option>
                                                                                                                                <option value="WV">West Virginia</option>
                                                                                                                                <option value="WI">Wisconsin</option>
                                                                                                                                <option value="WY">Wyoming</option>
                                                                                                                            </select>
                                                                                                                            <p class="cart"></p></td></tr>

                                                                                                                    <tr>
                                                                                                                        <td>
                                                                                                                            <p class="cart"><font color="#800000" face="Verdana">*</font>Zip: </p></td>
                                                                                                                        <td><input onBlur="javascript:if (!checkZIPCode(this))
        focus()" style="border: 1px solid rgb(204, 204, 204); padding: 2px; font-weight: bold; font-size: 9px; width: 60px; color: rgb(153, 153, 153); font-family: verdana,arial,helvetica,sans-serif; background-color: rgb(255, 255, 255);" tabindex="17" name="bill_postalCode">                    </td></tr>
                                                                                                                    <tr>
                                                                                                                        <td>

                                                                                                                            <p class="cart">*Country:</p></td>
                                                                                                                        <td>
                                                                                                                            <select class="formitem" tabindex="18" onChange="javascript:GetShipping();" name="bill_country">
                                                                                                                                <option value="CA">Canada</option>
                                                                                                                                <option value="GB">United Kingdom</option>
                                                                                                                                <option value="US" selected="selected">United States</option>
                                                                                                                            </select>  </td></tr>

                                                                                                                    <tr>
                                                                                                                        <td>
                                                                                                                            <p class="cart">*Phone #:</p>
                                                                                                                        </td>
                                                                                                                        <td>
                                                                                                                            <input  name="bill_phone" style="border: 1px solid rgb(204, 204, 204); padding: 2px; font-weight: bold; font-size: 9px; width: 160px; color: rgb(153, 153, 153); font-family: verdana,arial,helvetica,sans-serif; background-color: rgb(255, 255, 255);" tabindex="19">

                                                                                                                        </td>
                                                                                                                    </tr>

                                                                                                                    <tr>
                                                                                                                        <td>
                                                                                                                            <p>*Card #:</p>
                                                                                                                        </td>
                                                                                                                        <td>
                                                                                                                            <input name="bill_card_number" size="16" maxlength="19" tabindex="20" style="border: 1px solid rgb(204, 204, 204); padding: 2px; font-weight: bold; font-size: 9px; width: 160px; color: rgb(153, 153, 153); font-family: verdana,arial,helvetica,sans-serif; background-color: rgb(255, 255, 255);" />
                                                                                                                        </td>
                                                                                                                    </tr>

                                                                                                                    <tr>
                                                                                                                        <td>
                                                                                                                            <p>*Card Type:</p>
                                                                                                                        </td>
                                                                                                                        <td>
                                                                                                                            <select name="CardType"  tabindex="21">
                                                                                                                                <option value="Visa" selected="selected">Visa</option>
                                                                                                                                <option value="MasterCard">MasterCard</option>                
                                                                                                                                <option value="Amex">American Express</option>
                                                                                                                                <option value="Discover">Discover</option>
                                                                                                                            </select>
                                                                                                                        </td>
                                                                                                                    </tr>                      
                                                                                                                    <tr>
                                                                                                                        <td>
                                                                                                                            <p>*Expiry Month:</p>
                                                                                                                        </td>
                                                                                                                        <td>
                                                                                                                            <select name="ExpMon"  tabindex="22">
                                                                                                                                <option value="01" selected>1</option>
                                                                                                                                <option value="02">2</option>
                                                                                                                                <option value="03">3</option>
                                                                                                                                <option value="04">4</option>
                                                                                                                                <option value="05">5</option>
                                                                                                                                <option value="06">6</option>
                                                                                                                                <option value="07">7</option>
                                                                                                                                <option value="08">8</option>
                                                                                                                                <option value="09">9</option>
                                                                                                                                <option value="10">10</option>
                                                                                                                                <option value="11">11</option>
                                                                                                                                <option value="12">12</option>
                                                                                                                            </select>
                                                                                                                        </td>
                                                                                                                    </tr>                      
                                                                                                                    <tr>
                                                                                                                        <td>
                                                                                                                            <p>*Expiry Year:</p>
                                                                                                                        </td>
                                                                                                                        <td>
                                                                                                                            <select name="ExpYear"  tabindex="23">
                                                                                                                                <option value="2011" selected>2011</option>
                                                                                                                                <option value="2012">2012</option>
                                                                                                                                <option value="2013">2013</option>
                                                                                                                                <option value="2014">2014</option>
                                                                                                                                <option value="2015">2015</option>
                                                                                                                                <option value="2016">2016</option>
                                                                                                                                <option value="2017">2017</option>
                                                                                                                                <option value="2018">2018</option>
                                                                                                                                <option value="2019">2019</option>
                                                                                                                                <option value="2020">2020</option>
                                                                                                                                <option value="2021">2021</option>
                                                                                                                                <option value="2022">2022</option>
                                                                                                                                <option value="2023">2023</option>
                                                                                                                            </select>
                                                                                                                        </td>
                                                                                                                    </tr>                      
                                                                                                                    <tr>
                                                                                                                        <td>
                                                                                                                            <p>*CVV2 Code:</p>
                                                                                                                        </td>
                                                                                                                        <td>
                                                                                                                            <input name="CVV2" style="border: 1px solid rgb(204, 204, 204); padding: 2px; font-weight: bold; font-size: 9px; color: rgb(153, 153, 153); font-family: verdana,arial,helvetica,sans-serif; background-color: rgb(255, 255, 255);"  size="3" maxlength="4"  tabindex="24">
                                                                                                                        </td>
                                                                                                                    </tr>                  
                                                                                                            </table>


                                                                                                            <span id="PayPal"></span><span id="CreditCard">
                                                                                                            </span></td></tr>


                                                                                                        </table>

                                                                                                        </td></tr>

                                                                                                        </tbody></table>

                                                                                                        </form>

                                                                                                                    </td></tr></tbody></table>
                                                                                                                    </center>

                                                                                                                    </div>

                                                                                                                    </div>


                                                                                                                    </div>    

                                                                                                                    <div id="bottomRightSide"></div>

                                                                                                                    <%@include file="store_footer.jsp" %> 


                                                                                                                    </div>

</div>
                                                                                                                    </body>

                                                                                                                    </html>