<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.transitionsoft.*"%>
<%@ page import="com.transitionsoft.dao.*"%>

<%
    // Get the current shopping cart from the user's session.
    ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
    
    // Get a formatter to write out currency values.
    NumberFormat currency = NumberFormat.getCurrencyInstance();

    // If the user doesn't have a shopping cart yet, create one.
    if (cart == null)
    {
        cart = new ShoppingCart();
        session.setAttribute("ShoppingCart", cart);
    }

    // Get the items from the cart.
    Vector items = cart.getItems();

    // If there are no items, tell the user that the cart is empty.
    if (items.size() == 0)
    {
        out.println("<div align=\"center\"><h3>Shopping cart is empty!</h3><br /><a href=\"index.jsp\">Click Here to Continue Shopping</a></div>");
    }
    else
    {
%>

<div id="ShopCart" style="margin-left:10px; margin-right:10px">
        <form name="Shop" method="get" action="UpdateQuantities">
        <input value="1" name="txtCount" type="hidden"> 
        
<%-- Display the header for the shopping cart table --%>
<table border="0" cellpadding="0" cellspacing="2" width="70%" bordercolor="#cccccc">
<tbody>
    <tr>
        <td width="40px" valign="top" bgcolor="#cccccc" align="left">
            <img src="images/corner_top_left.gif" alt="" border="0" width="8" height="10"><br>
            &nbsp;&nbsp;&nbsp;Qty<br />&nbsp;
        </td>
        <td width="*" bgcolor="#cccccc" valign="top" align="center">
            <img src="images/dotclear.gif" alt="" border="0" width="270" height="10"><br>
            <%=application.getAttribute("product-name")%>
        </td>
        <td width="70px" valign="top" bgcolor="#cccccc"  align="center">
            <img src="images/dotclear.gif" alt="" border="0" width="77" height="10"><br>	<%=application.getAttribute("unit-price")%>
        </td>
        <td width="90px" valign="top" bgcolor="#cccccc"  align="center">
            <img src="images/dotclear.gif" alt="" border="0" width="77" height="10"><br><%=application.getAttribute("total-price")%>
        </td>
        <td width="25px" valign="top" bgcolor="#ffffff">

        </td>
    </tr>
        
                     

<%
        int numItems = items.size();

        for (int i=0; i < numItems; i++)
        {
            Item item = (Item) items.elementAt(i);
	
			%>
            <tr>
            	<td align="center"  bgcolor="#eeeeee">
                	<input name="txtQ<%=i%>" value="<%=item.getItemQuantity()%>" onFocus="if(this.value==' 1')this.value='';" onBlur="if(this.value=='')this.value='2';" style="border: 1px solid rgb(204, 204, 204); padding: 2px; width: 20px; font-size: 9.5px; background-color: rgb(255, 255, 255); font-family: verdana,arial,helvetica,sans-serif; color: rgb(153, 153, 153); font-weight: bold;" size="20" type="text">
                </td>
                <td valign="top" bgcolor="#eeeeee">
                	<p class="cart"><b><a href="javascript:popUp2('items.jsp?Id=<%=item.getItemId()%>')"><%=item.getItemName()%> - <%=item.getItemOption()%></a></b></p>
                </td>
                <td valign="top" bgcolor="#eeeeee">
                	<p class="cart"><% out.print(currency.format(item.getItemPrice())); %></p>
                </td>
                <td  valign="top" bgcolor="#eeeeee">
                    <p class="cart"><b><% out.print(currency.format(item.getItemPrice() * item.getItemQuantity())); %></b></p>
                </td>
                <td bgcolor="#eeeeee">
                    <a href=<% out.println("\"RemoveFromShoppingCart?item=" + i + "\">"); %>X</a>
                </td>
             </tr>
             
<!--end item-->
			
			<%            
        }		
		%>
        
        
                <tr>
                    <td align="middle" bgcolor="#FFFFFF">            

                    </td>
                    <td bgcolor="#FFFFFF" >


                    </td>            
                    <td align="right" bgcolor="#FFFFFF" valign="middle">
                        <p class="update">
                            <strong><%=application.getAttribute("cart-subtotal")%>:</strong>
                        </p>    
                    </td>
                    <td colspan="2" bgcolor="#cccccc" valign="middle">
                        <p class="update">
                            <strong><%=currency.format(cart.getTotalPrice())%>*</strong>
                        </p>
                    </td>
                </tr>

                <tr>
                    <td colspan="6" valign="top">            
                        <p class="update">
                            <INPUT type="submit" class="xbutton" value="Update Qty" alt="Update Quantities">
                            <a href="index.jsp" class="xbutton2">Continue Shopping</a>

                        </p>

                    </td>        
                </tr>
        
        
        <tr>
            <td colspan="4" valign="top">            
        	    <span style="color:#F00">*<%=application.getAttribute("cart-message-6")%></span>
            </td>
            <td colspan="2">&nbsp;
            	
            </td>        
        </tr>
      </tbody>
    </table>
</form>
        <%
    }
%>
</div>








    
   
