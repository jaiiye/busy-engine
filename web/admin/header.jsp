
<%@page import="java.util.*" %>
<%@page import="com.transitionsoft.*" %>
<%@page import="com.transitionsoft.dao.*" %>
<%@page import="java.text.SimpleDateFormat"%>

<%@page contentType="text/html; charset=ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<%@include file="../connection.jsp" %> 

<%
    String userName = request.getUserPrincipal().getName();
    User u = null;

    if(userName == null)
    {
        //user is not logged in
    }
    else
    {
        //see if the user is already logged in before
        String name = (String)session.getAttribute("userName");
        
        if(name == null)
        {
            //a new user is being logged-in
            session.setAttribute("userName", userName);
        
            //find out who the logged-on user is
            u = Database.getUser(request.getUserPrincipal().getName()); 

            //record the login date and time
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String loginTime = sdf.format(new Date(session.getCreationTime()));

            Database.RecordUserLoginAction(u.getUserId().toString(), u.getUserName(), loginTime, "Site Administration");
        }
        else{
            u = Database.getUser(name); 
        }
    }



%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
    <title>Administrator Application</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="Content-Language" content="en-us">
    <meta content="TRUE" name="MSSmartTagsPreventParsing">
    
    <script type="text/javascript" src="../scripts/Functions.js"></script>

    <link href="../css/index_styles.css" type="text/css" rel="stylesheet" >
    <link href="../images2/nav.css" type="text/css" rel="stylesheet" />
    
    <!-- JQuery Start -->
    <script type="text/javascript" src="../scripts/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="../scripts/jquery-ui-1.10.2.custom.min.js"></script>
    <link href="../css/smoothness/jquery-ui-1.10.2.custom.css" rel="stylesheet">
	<!-- JQuery End -->
    
	<!-- Data Tables + Table Tools Start -->
	<script type="text/javascript" language="javascript" src="DataTables-1.9.4/media/js/jquery.dataTables.js"></script>
	<script type="text/javascript" charset="utf-8" src="TableTools-2.1.5/media/js/ZeroClipboard.js"></script>
	<script type="text/javascript" charset="utf-8" src="TableTools-2.1.5/media/js/TableTools.js"></script>
    
	<link href="DataTables-1.9.4/media/css/demo_table_jui.css" type="text/css" rel="stylesheet">
	<link href="TableTools-2.1.5/media/css/TableTools.css" type="text/css" rel="stylesheet">
    
    <script type="text/javascript" charset="utf-8">
	var dataTable;
	$(document).ready(function() 
	{
		var oTable = $('#example').dataTable( {
			"bJQueryUI": true,			
			"sDom": 'pT<"clear"><"H"ifr>t<"F"l>',
			"sPaginationType": "full_numbers",
			"aLengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
			"bProcessing": true,
			"iDisplayLength": 25,
			"oTableTools": { 
				"sSwfPath": "TableTools-2.1.5/media/swf/copy_csv_xls_pdf.swf", 
				"sRowSelect": "single", 							
				"aButtons": [
								{
									"sExtends": "copy",
									"sButtonText": "Copy to Clipboard"
								},
								{
									"sExtends": "print",
									"sButtonText": "Print Data"
								}, 
								{
									"sExtends":    "collection",
									"sButtonText": "Save",
									"aButtons":    [ "csv", "xls", "pdf" ]
								}
	
							]
			} 					
		} );
		dataTable = oTable;
	} );
	</script>
    
	<!-- Data Tables + Table Tools End -->
    
<style>
		input.text { margin-bottom:12px; width:95%; padding: .4em; }
		fieldset { padding:0; border:0; margin-top:15px; }
		.ui-dialog .ui-state-error { padding: .3em; }
		.validateTips { border: 1px solid transparent; padding: 0.3em; }
		
		
Body
{
	font-family:Arial, Helvetica, sans-serif;
	font-size:12px;
}
.editor-hidden {
    visibility: hidden;
    top: -9999px;
    left: -9999px;
    position: absolute;
}
textarea {
    border: 0;
    margin: 0;
    padding: 0;
}


#Inputdivision 
{
	PADDING-RIGHT: 0px; 
	PADDING-LEFT: 0px; 
	FONT-SIZE: 85%; 
	PADDING-BOTTOM: 0px; 
	PADDING-TOP: 20px; 
	POSITION: fixed;
	WIdTH:65%;
	visibility: visible;
}

#Inputdivision DL 
{
	BORDER-RIGHT: #999 1px solid; 
	PADDING-RIGHT: 0px; 
	BORDER-TOP: #999 4px solid; 
	PADDING-LEFT: 0px; 
	BACKGROUND: #ddd; 
	PADDING-BOTTOM: 0px; 
	MARGIN: 0px 0px 10px; 
	BORDER-LEFT: #999 1px solid; 
	PADDING-TOP: 0px; 
	BORDER-BOTTOM: #999 1px solid
}

#Inputdivision DT 
{
	PADDING-RIGHT: 10px; 
	PADDING-LEFT: 10px; 
	FONT-WEIGHT: bold; 
	FONT-SIZE: 120%; 
	PADDING-BOTTOM: 6px; 
	TEXT-TRANSFORM: uppercase; 
	COLOR: #000; 
	PADDING-TOP: 6px; 
	FONT-FAMILY: Arial, "Lucida Grande", Verdana, sans-serif;
}

#Inputdivision DD 
{
	PADDING-RIGHT: 10px; 
	PADDING-LEFT: 10px; 
	BACKGROUND: #fff; 
	PADDING-BOTTOM: 10px; 
	MARGIN: 0px; 
	COLOR: #666; 
	PADDING-TOP: 10px;
}

#input 
{	
	border-radius: 10px;
	box-shadow: 0px 0px 10px #222;
	position: fixed;
	background-color:#666666;
	color:#FFFFFF;
 
	top: 20%;
	left: 40%;
	margin-top: -100px;
	margin-left: -200px;
	z-index: 1000;  
}

</style>
</head>

<body>

<div id="maincontainer">

	<div id="header">
    
        <div id="subnav">
			<ul>
                <li id="dealer-locator"><a href="#">Welcome <%= u.getUserName()%></a></li>
                <li id="dealer-locator"><a href="../LogOut?userId=<%= u.getUserId()%>&userName=<%= u.getUserName()%>&area=Site%20Administration"><strong>(Logout)</strong></a></li>
                <li id="dealer-locator"><a href="index.jsp"><strong>Dashboard </strong></a></li>
                <li id="dealer-locator"><a href="../index.jsp"><strong>Main Site</strong></a></li>
                <li id="dealer-locator"><a href="../members/index.jsp"><strong>Members </strong></a></li>
				

				
			</ul>
		</div>
        
        <div id="nav">
            <ul class="nav">            
            	<li id="sales">
            		<a class="maintop"><strong>Sales</strong></a>
            		<div class="dropDown">
            			<!--[if lt IE 8]><table cellspacing="0"><tr><td><![endif]-->
            			<div class="dropBackground">
            				<ul>
            					<li class="man2009"><a href="orders.jsp" class="top">Orders</a></li>
            					<li class="man2009"><a href="shipping.jsp" class="top">Shipping</a></li>
                                <li class="man2009"><a href="tax.jsp" class="top">Tax</a></li>
            				</ul>
        				</div>
        				<!--[if lt IE 8]></td></tr></table><![endif]-->
        			</div>
        		</li>
                <li id="catalog">
            		<a class="maintop"><strong>Catalog</strong></a>
            		<div class="dropDown">
            			<!--[if lt IE 8]><table cellspacing="0"><tr><td><![endif]-->
            			<div class="dropBackground">
            				<ul>
            					<li class="man2009"><a href="items.jsp" class="top">Items</a></li>
            					<li class="man2009"><a href="brands.jsp" class="top">Brands</a></li>
                                <li class="man2009"><a href="categories.jsp" class="top">Categories</a></li>
                                <li class="man2009"><a href="options.jsp" class="top">Options</a></li>
            				</ul>
        				</div>
        				<!--[if lt IE 8]></td></tr></table><![endif]-->
        			</div>
        		</li>
                <li id="customers">
            		<a class="maintop"><strong>Customers</strong></a>
            		<div class="dropDown">
            			<!--[if lt IE 8]><table cellspacing="0"><tr><td><![endif]-->
            			<div class="dropBackground">
            				<ul>
                           		<li class="man2009"><a href="customers.jsp" class="top">Manage Customers</a></li>
            				</ul>
        				</div>
        				<!--[if lt IE 8]></td></tr></table><![endif]-->
        			</div>
        		</li>
                <li id="community">
            		<a class="maintop"><strong>Community</strong></a>
            		<div class="dropDown">
            			<!--[if lt IE 8]><table cellspacing="0"><tr><td><![endif]-->
            			<div class="dropBackground">
            				<ul>
            					<li class="man2009"><a href="blogs.jsp" class="top">Blogs</a></li>
            					<li class="man2009"><a href="newsletter.jsp" class="top">Newsletter</a></li>
								<li class="man2009"><a href="facebook.jsp" class="top">Facebook</a></li>

            				</ul>
        				</div>
        				<!--[if lt IE 8]></td></tr></table><![endif]-->
        			</div>
        		</li>
                <li id="content">
            		<a class="maintop"><strong>Content</strong></a>
            		<div class="dropDown">
            			<!--[if lt IE 8]><table cellspacing="0"><tr><td><![endif]-->
            			<div class="dropBackground">
            				<ul>            					
            					<li class="man2009"><a href="pages.jsp" class="top">Pages</a></li>           					
            					<li class="man2009"><a href="page-templates.jsp" class="top">Page Templates</a></li>
                                <li class="man2009"><a href="sliders.jsp" class="top">Sliders</a></li>
                                <li class="man2009"><a href="strings.jsp" class="top">Languages</a></li>
                                <li class="man2009"><a href="images.jsp" class="top">Images</a></li>
                                <li class="man2009"><a href="files.jsp" class="top">Files</a></li>
                                <li class="man2009"><a href="forms.jsp" class="top">Forms</a></li>
                                <li class="man2009"><a href="templates.jsp" class="top">Templates</a></li>
            				</ul>
        				</div>
        				<!--[if lt IE 8]></td></tr></table><![endif]-->
        			</div>
        		</li>
                <li id="store">
            		<a class="maintop"><strong>Store</strong></a>
            		<div class="dropDown">
            			<!--[if lt IE 8]><table cellspacing="0"><tr><td><![endif]-->
            			<div class="dropBackground">
            				<ul>
            					<li class="man2009"><a href="preferences.jsp" class="top">Info</a></li>
                                <li class="man2009"><a href="users.jsp" class="top">Users</a></li>
                                <li class="man2009"><a href="layouts.jsp" class="top">Layouts</a></li>
                                <li class="man2009"><a href="attributes.jsp" class="top">Attributes</a></li>
                                <li class="man2009"><a href="site-templates.jsp" class="top">Templates</a></li>
            				</ul>
        				</div>
        				<!--[if lt IE 8]></td></tr></table><![endif]-->
        			</div>
        		</li>                 
             </ul>

			
		</div>
	</div>
<br /><br />
<br /><br />

</div>
<div id="input">
</div>

					


