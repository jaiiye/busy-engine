
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.transitionsoft.*"%>
<%@ page import="com.transitionsoft.dao.*"%>

 <%
	// Get the items from the cart.
    Vector items = cart.getItems();

	// If there are no items, tell the user that the cart is empty.
    if (items.size() == 0)
    {
        out.println("<h3>Shopping cart is empty!</h3>");
    }
    else
    {		
		    numItems = items.size();
			totalQty = cart.getTotalQuantity();
%>

       

<div id="ShopCart" style="margin-left:10px; margin-right:10px">
<table border="0" cellpadding="0" cellspacing="2"  width="75%" >
<tbody>
		<tr>
        	<td valign="top" bgcolor="#cccccc" align="left">
				<img src="images/corner_top_left.gif" alt="" border="0" width="8" height="10"><br>
				<font class="cart">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Qty</font><br>
				<img src="images/dotclear.gif" alt="" border="0" width="68" height="10">
		    </td>
            <td colspan="2" bgcolor="#cccccc" valign="top">
				<img src="images/dotclear.gif" alt="" border="0" width="270" height="10"><br>
                <font class="cart">&nbsp;&nbsp;Product Name</font><br>&nbsp;
            </td>
            <td valign="top" bgcolor="#cccccc">
				<img src="images/dotclear.gif" alt="" border="0" width="77" height="10"><br><font class="cart">&nbsp;Unit Price</font>
          </td>
            <td valign="top" bgcolor="#cccccc">
				<img src="images/dotclear.gif" alt="" border="0" width="77" height="10"><br><font class="cart">&nbsp;Total Price</font>
          </td>
        </tr>
        
                     

<%


        for (int i=0; i < numItems; i++)
        {
            Item item = (Item) items.elementAt(i);
	
			%>
			<!--item-->
			<tr>
            	<td align="center" bgcolor="#eeeeee" valign="top">
                	<p class="cart"><%=item.getItemQuantity()%></p>
                </td>
                <td colspan="2" bgcolor="#eeeeee" valign="top">
                	<p class="cart"><b><a href="javascript:popUp2('items.jsp?Id=<%=item.getItemId()%>')"><%=item.getItemName()%> - <%=item.getItemOption()%></a></b></p>
                </td>
                <td bgcolor="#eeeeee" valign="top"  align="center" >
                	<p class="cart"><% out.print(currency.format(item.getItemPrice())); %></p>
                </td>
                <td bgcolor="#eeeeee" valign="top"  align="right" >
                    <p class="cart"><b><% out.print(currency.format(item.getItemPrice() * item.getItemQuantity())); %></b></p>
                </td>
             </tr>
             
<!--end item-->
			
			<%            
        }		
		%>
        <tr>
        	<td bgcolor="#FFFFFF" align="middle" >&nbsp;</td>
        	<td bgcolor="#FFFFFF" valign="middle">&nbsp;</td>
            <td colspan="2" align="right" bgcolor="#FFFFFF" valign="middle"><p class="update"><b>Subtotal:</b></p></td>
			<td colspan="2" bgcolor="#cccccc" valign="middle"  align="right" ><p class="update"><b><%=currency.format(cart.getSubTotalPrice())%></b></p></td>
       </tr>
       <tr>
        	<td bgcolor="#FFFFFF" align="middle" >&nbsp;</td>
        	<td bgcolor="#FFFFFF" valign="middle">&nbsp;</td>
            <td colspan="2" align="right" bgcolor="#FFFFFF" valign="middle"><p class="update"><b>Tax:</b></p></td>
			<td colspan="2" bgcolor="#cccccc" valign="middle"  align="right" ><p class="update"><b><%=currency.format(cart.getTaxPrice())%></b></p></td>
       </tr>
       <tr>
        	<td bgcolor="#FFFFFF" align="middle" >&nbsp;</td>
        	<td bgcolor="#FFFFFF" valign="middle">&nbsp;</td>
            <td colspan="2" align="right" bgcolor="#FFFFFF" valign="middle"><p class="update"><b>Shipping:</b></p></td>
			<td colspan="2" bgcolor="#cccccc" valign="middle"  align="right" ><b><div id="shipprice">&nbsp;---</div></b></td>
       </tr>
       <tr>
        	<td bgcolor="#FFFFFF" align="middle" >&nbsp;</td>
        	<td bgcolor="#FFFFFF" valign="middle">&nbsp;</td>
            <td colspan="2" align="right" bgcolor="#FFFFFF" valign="middle"><p class="update"><b>Total:</b></p></td>
			<td colspan="2" bgcolor="#cccccc" valign="middle"  align="right" ><p class="update"><b><div id="totalprice">&nbsp;<%=currency.format(cart.getTotalPrice())%></div></b></p></td>
       </tr>
   </tbody>    
</table>
</div>
        <%
    }
%>
