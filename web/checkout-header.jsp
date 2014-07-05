<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.transitionsoft.*"%>
<%@ page import="com.transitionsoft.dao.*"%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@include file="connection.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%

    NumberFormat formatter = new DecimalFormat("0.00");

	// If the user doesn't have a shopping cart yet, create one.
    if (session.getAttribute("ShoppingCart") == null)
    {
        session.setAttribute("ShoppingCart", new ShoppingCart());
    }

	// Get the items from the cart.
    Vector items2 = ((ShoppingCart) session.getAttribute("ShoppingCart")).getItems();

	//set the payment amount with the current total payment
    Double d = ((ShoppingCart) session.getAttribute("ShoppingCart")).getSubTotalPrice();
    System.out.println("Value passed as payment amount is: " + formatter.format(d.doubleValue()));
    session.setAttribute("Payment_Amount", formatter.format(d.doubleValue()));
    int ix = ((ShoppingCart) session.getAttribute("ShoppingCart")).getItems().size();
    String sx = Integer.toString(ix);
	
	
	if(	application.getAttribute("index-header-link-1") == null)
	{
		statement = connection.createStatement();   

		try
		{ 
			String query = "SELECT ls.StringId, ls.LocaleId, StringKey, StringValue, LocaleString  FROM locales l, strings s, localized_strings ls WHERE ls.LocaleId = l.LocaleId AND ls.StringId = s.StringId AND ls.LocaleId = " + Database.getStoreLocale() + ";";
			
			rs = statement.executeQuery(query);		
			
			while(rs.next())
			{
				application.setAttribute(rs.getString(3),rs.getString(4));
				System.out.println("setting Application attribute: (" + rs.getString(3) + "," + rs.getString(4) + ")");			
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response.sendError(500);
		}
	}

%>
<html>
<head>


	<%@include file="meta-tags.jsp" %>
    
    
  
    
    
   <%
    if(request.getParameter("action") != null && request.getParameter("Id") != null )
    {
	        statement = connection.createStatement();
            String query = "SELECT * FROM item WHERE ItemId = " + request.getParameter("Id") + ";";
            rs = statement.executeQuery(query);
            while(rs.next())
            {
		    %>
                <TITLE><%= rs.getString("ItemSEOTitle")%></TITLE>
                <META name="keywords" content="<%= rs.getString("ItemSEOKeywords")%>">
                <META name="description" content="<%= rs.getString("ItemSEODescription")%>" >
		    <%	
			//break;
			}
	}   
	else 
	{
	%>
        <TITLE>Online Storefront</TITLE>
    	<META name="keywords" content="Store, Items, Sale,online store">
    	<META name="description" content="This site is an online store that sells products at great prices online." >
    
	<% } %>

    
<script language="javascript" src="scripts/index_functions.js" type="text/javascript"></script>
<script language="javajcript" src="scripts/isEmail.js"         type="text/javascript"></script>
<script language="javajcript" src="scripts/Functions.js"       type="text/javascript"></script>
<script language="javajcript" src="scripts/flexcroll.js"       type="text/javascript"></script>
<script language="javajcript" src="scripts/jsScroller.js"      type="text/javascript"></script>
<script language="javajcript" src="scripts/jsScrollbar.js"     type="text/javascript"></script>
<script language="JavaScript" src="scripts/isZipCode.js"       type="text/javascript"></script>
<script language="JavaScript" src="scripts/ValidateDate.js"    type="text/javascript"></script>
<script language="JavaScript" src="scripts/jquery-1.4.2.min.js"type="text/javascript"></script>

    <script type="text/javascript"> 
 //Image Slider/Rotator code
$(document).ready(function() 
{
 
	//Set Default State of each portfolio piece
	$(".paging").show();
	$(".paging a:first").addClass("active");
		
	//Get size of images, how many there are, then determin the size of the image reel.
	var imageWidth = $(".window").width();
	var imageSum = $(".image_reel img").size();
	var imageReelWidth = imageWidth * imageSum;
	
	//Adjust the image reel to its new size
	$(".image_reel").css({'width' : imageReelWidth});
	
	//Paging + Slider Function
	rotate = function()
	{	
		var triggerId = $active.attr("rel") - 1; //Get number of times to slide
		var image_reelPosition = triggerId * imageWidth; //Determines the distance the image reel needs to slide
 
		$(".paging a").removeClass('active'); //Remove all active class
		$active.addClass('active'); //Add active class (the $active is declared in the rotateSwitch function)
		
		//Slider Animation
		$(".image_reel").animate(
		{ 
			left: -image_reelPosition
		}, 500 );
		
	}; 
	
	//Rotation + Timing Event
	rotateSwitch = function()
	{	
		//Set timer - this will repeat itself every 3 seconds	
		play = setInterval(function()
		{ 			
			$active = $('.paging a.active').next();
			
			//If paging reaches the end...
			if ( $active.length === 0) 
			{ 
				$active = $('.paging a:first'); //go back to first
			}
			rotate(); //Trigger the paging and slider function
		}, 7000); //Timer speed in milliseconds (3 seconds)
	};
	
	rotateSwitch(); //Run function on launch
	
	//On Hover
	$(".image_reel a").hover(function() 
	{
		clearInterval(play); //Stop the rotation
	}, function() 
	{
		rotateSwitch(); //Resume rotation
	});	
	
	//On Click
	$(".paging a").click(function() 
	{	
		$active = $(this); //Activate the clicked paging
		//Reset Timer
		clearInterval(play); //Stop the rotation
		rotate(); //Trigger rotation immediately
		rotateSwitch(); // Resume rotation
		return false; //Prevent browser jump to link anchor
	});	
	
});
</script>

<link href="css/flexcrollstyles2.css" type="text/css" rel="stylesheet">
<link href="css/firstpage_eu.css" rel="stylesheet">
<link href="css/scroll.css" rel="stylesheet">
<link href="css/solidblocksmenu.css" rel="stylesheet">
<link href="css/index_styles.css" type="text/css" media=all rel="stylesheet">
<link href="css/index.css" type="text/css" media=all rel="stylesheet">
<link href="css/main.css" type="text/css" media=all rel="stylesheet">



<script>
		var timestart     = 500;
        var timeout     = 500;
        var tabclosetimer  = 0;
        var tabitem  = 0;
	    var tabheader  = 0;
		
        // open hidden layer
        function tabopen(id)
        {   
              // cancel close timer
              tabcancelclose();

              // close old layer
              if(tabitem) tabitem.style.visibility = 'hidden';
			  if(tabheader) tabheader.className = 'fullTab';
			  
              // get new layer and show it
              tabitem = document.getElementById(id);
              tabitem.style.visibility = 'visible';
			  tabheader = document.getElementById(id + 'TB');
			  tabheader.className = 'highTab';
        }
        // close showed layer
        function closetab()
        {
              if(tabitem) tabitem.style.visibility = 'hidden';
			  if(tabheader) tabheader.className = 'fullTab';
        }
        // go close timer
        function tabclose()
        {
              tabclosetimer = window.setTimeout(closetab, timeout);
        }
        // cancel close timer
        function tabcancelclose()
        {
              if(tabclosetimer)
              {
                    window.clearTimeout(tabclosetimer);
                    tabclosetimer = null;
              }
        }
        // close layer when click-out
        document.onclick = closetab; 
var isActive = false;


</script>
	

	</head>
	<body  topmargin="0" bottommargin="0" leftmargin="0" rightmargin="0" marginwidth="0" marginheight="0" vlink="#000000" alink="#333333" link="#000000">




<div class="mainContainer">
	<div id="centerDiv" class="centerContainer">
		 <div id="CartHeader">	    
            
            <div id="logo">        
                <div style="position:relative; float:left;">
                	<a href="store.jsp"><img src="images/clear.gif" width="120" height="125" border="0"></a>
                </div>
            </div>
        
            <div id="accountSearchHolder">
                <div id="accountDiv">
                    <a href="members/index.jsp" class="accountLinks" target="_top"><%=application.getAttribute("index-header-top-link-1")%></a>&nbsp;|&nbsp;<a href="register.jsp" class="accountLinks" target="_top"><%=application.getAttribute("index-header-top-link-2")%></a>
                 </div>
            </div>
					            
            
            
				
	
            <ul class="tabdd" style="left:165px; top:93px;">
            	<li>
                    <table id="prodDropTB2" cellpadding="0" cellspacing="0" border="0"  >
                        <tr>
                            <td class="tabLeft" style="width:42px;" ><a href="store.jsp" class="tabLink"><%=application.getAttribute("index-header-link-1")%></a></td>			
                         </tr>
                    </table>	        
                </li>
             </ul>
	        
			
						
	<ul class="tabdd" style="left:220px;">
	    <li>
			<table id="prodDropTB" cellpadding="0" cellspacing="0" border="0" class="fullTab" onmouseover="tabopen('prodDrop')" onmouseout="tabclose()" >
				<tr>
					<td class="tabLeft" style="width:55px;"><a href="products.jsp" class="tabLink"><%=application.getAttribute("index-header-link-2")%></a></td>
					<td class="tabRight"></td>
				</tr>
			</table>
            
	        <div id="prodDrop" onmouseover="tabcancelclose()" onmouseout="tabclose()">
	            <table style="width:127px;" cellspacing="0" cellpadding="0" border="0">
					<tr>
						<td class="ddContents" colspan="2">
						<ul class="ddll">                                   
                       <li style="width:125px;"><A href="products.jsp" rel="sb2"  target="_top"><%=application.getAttribute("index-header-link-2-1")%></A> </li>		
                        <li style="width:125px;"><A href="products.jsp?category=18" rel="sb2"  target="_top"><%=application.getAttribute("index-header-link-2-2")%></A> </li>	                       
                        <li style="width:125px;"><A href="products.jsp?category=19" rel="sb2"  target="_top"><%=application.getAttribute("index-header-link-2-3")%></A> </li>		
						</ul>
					</td>
					</tr>
					<tr>
                        <td valign="top" class="ddbottomleftcorner" style="width:82px;"></td>
						<td valign="top" class="ddbottomrightcorner"></td>
					</tr>
				</table>
			</div>
	    </li>
	 </ul>
	 <ul class="tabdd" style="left:310px;">
	     <li>
			<table id="dealDropTB" cellpadding="0" cellspacing="0" border="0" class="fullTab" onmouseover="tabopen('dealDrop')" onmouseout="tabclose()" >
				<tr>
					<td class="tabLeft" style="width:70px;"><a href="submit.jsp" class="tabLink"><%=application.getAttribute("index-header-link-3")%></a></td>
					<td class="tabRight"></td>
				</tr>
			</table>
	        <div id="dealDrop" onmouseover="tabcancelclose()" onmouseout="tabclose()">
	   		<table style="width:200;" cellspacing="0" cellpadding="0" border="0">
                <tr>
                    <td class="ddContents" colspan="2">
                    <ul class="ddll">
                        <li style="width:198px;"><a href="submit.jsp" style=""><%=application.getAttribute("index-header-link-3-1")%></a></li>
                        <li style="width:198px;"><a href="vote.jsp" style=""><%=application.getAttribute("index-header-link-3-2")%></a></li>				
                    </ul>
                    </td>
                </tr>
					<tr>
                        <td valign="top" class="ddbottomleftcorner" style="width:155px;"></td>
						<td valign="top" class="ddbottomrightcorner"></td>
					</tr>
				</table>
	        </div>
	    </li>
	   </ul>
	   
		<ul class="tabdd" style="left:410px;">
	    <li><table id="newsDropTB" cellpadding="0" cellspacing="0" border="0" class="fullTab" onmouseover="tabopen('newsDrop')" onmouseout="tabclose()">
				<tr>
					<td class="tabLeft" style="width:70px;"><a href="challenge.jsp" class="tabLink"><%=application.getAttribute("index-header-link-4")%></a></td>
					<td class="tabRight"></td>
				</tr>
			</table>
			 <div id="newsDrop" onmouseover="tabcancelclose()" onmouseout="tabclose()">
	            <table style="width:200px;" cellspacing="0" cellpadding="0" border="0">
					<tr>
						<td class="ddContents" colspan="2">
						<ul class="ddll">
							<li style="width:198px;"><a href="challenge.jsp" style=""><%=application.getAttribute("index-header-link-4-1")%></a></li>
							<li style="width:198px;"><a href="vote.jsp" style=""><%=application.getAttribute("index-header-link-4-2")%></a></li>
						</ul>
						</td>
					</tr>
				    <tr>
                        <td valign="top" class="ddbottomleftcorner" style="width:155px;"></td>
						<td valign="top" class="ddbottomrightcorner"></td>
					</tr> 
				</table>
	        </div>
	   </li>
	</ul>
	
       
       
       
       
	<div id="cartDiv">

    </div>
	

</div>