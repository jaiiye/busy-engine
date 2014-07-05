<%@page import="java.text.*"%>
<%@page import="com.busy.dao.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<%
    request.setAttribute("preferences", new Preferences());
    NumberFormat formatter = NumberFormat.getCurrencyInstance();
%>

<!DOCTYPE html>

    <!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
    <!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
    <!--[if !IE]><!-->
    <html lang="en" class="no-js">
    <!--<![endif]-->

    <head>
        <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport"/>
        <title>Busy Administrator: Business Website Administration Portal</title>
        
        <%@include file="index_global_styles.jsp" %> 
        
        <!-- BEGIN PAGE LEVEL STYLES -->
            <link rel="stylesheet" type="text/css" href="../assets/global/plugins/select2/select2.css"/>
            <link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-datepicker/css/datepicker.css"/>   
            <link rel="stylesheet" type="text/css" href="../assets/global/plugins/datatables/extensions/Scroller/css/dataTables.scroller.min.css"/>
            <link rel="stylesheet" type="text/css" href="../assets/global/plugins/datatables/extensions/ColReorder/css/dataTables.colReorder.min.css"/>
            <link rel="stylesheet" type="text/css" href="../assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
        <!-- END PAGE LEVEL STYLES -->
        
        <!-- BEGIN THEME STYLES -->
	        <link rel="stylesheet" type="text/css" href="../assets/admin/pages/css/profile.css">
            <link rel="stylesheet" type="text/css" href="../assets/global/css/components.css"/>
            <link rel="stylesheet" type="text/css" href="../assets/global/css/plugins.css" />
            <link rel="stylesheet" type="text/css" href="../assets/admin/layout/css/layout.css" />
            <link rel="stylesheet" type="text/css" href="../assets/admin/layout/css/themes/light.css" id="style_color"/>
            <link rel="stylesheet" type="text/css" href="../assets/admin/layout/css/custom.css"/>
		<!-- END THEME STYLES -->

        <link rel="shortcut icon" href="favicon.ico"/>
    </head>

<body class="page-header-fixed page-footer-fixed">

	<%@include file="index_header.jsp" %> 

    <div class="clearfix"></div>
    <!-- BEGIN CONTAINER -->
    <div class="page-container">

        <% request.setAttribute("category", "configuration"); %> 
        <% request.setAttribute("subCategory", "preferences"); %>        
	<%@include file="index_sidebar.jsp" %> 

        <!-- BEGIN CONTENT -->
        <div class="page-content-wrapper">
            <div class="page-content">
            
                <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
                <div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                <h4 class="modal-title">Modal title</h4>
                            </div>
                            <div class="modal-body">
                                 Widget settings form goes here
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn blue">Save changes</button>
                                <button type="button" class="btn default" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
                <!-- /.modal -->
                <!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
               
                <div class="row">
                    <div class="col-md-12">
                        <h3 class="page-title">Preferences</h3>
                        <ul class="page-breadcrumb breadcrumb">                                
                            <li>
                                <i class="fa fa-home"></i><a href="index.jsp">Home</a>
                                <i class="fa fa-angle-right"></i>
                            </li>
                            <li>
                                <a href="#">Configuration</a>
                                <i class="fa fa-angle-right"></i>
                            </li>
                            <li>
                                <a href="#">Preferences</a>
                            </li>
                        </ul>
                    </div>
                </div>
                
                <!-- BEGIN DASHBOARD STATS -->
                
                <!-- END DASHBOARD STATS -->
                <div class="clearfix">
                </div> 
                
                <div class="row profile">
                    <div class="col-md-12">
                        <!--BEGIN TABS-->
                        <div class="tabbable tabbable-custom tabbable-full-width">
                            <ul class="nav nav-tabs">
                                <li class="active">
                                    <a href="#tab_1_1" data-toggle="tab">
                                        Administration
                                    </a>
                                </li>
                                <li class="">
                                    <a href="#tab_1_2" data-toggle="tab">
                                        Payments
                                    </a>
                                </li>
                                <li class="">
                                    <a href="#tab_1_3" data-toggle="tab">
                                        Product Showcase
                                    </a>
                                </li>
                                <li class="">
                                    <a href="#tab_1_4" data-toggle="tab">
                                        Site Layout
                                    </a>
                                </li>
                                <li class="">
                                    <a href="#tab_1_5" data-toggle="tab">
                                        Restore
                                    </a>
                                </li>
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane active" id="tab_1_1">
                					<div class="row">
                                        <div class="col-md-6 col-sm-12">
                                            <div class="portlet yellow box">
                                                <div class="portlet-title">
                                                    <div class="caption">
                                                        <i class="fa fa-cogs"></i>Site Administration
                                                    </div>
                                                    <div class="actions">
                                                        <a href="SiteInfoUI.jsp?id=${preferences.siteInfo.siteInfoId}" class="btn default btn-sm">
                                                            <i class="fa fa-pencil"></i> Edit
                                                        </a>
                                                    </div>
                                                </div>
                                                <div class="portlet-body">
                                                    <div class="row static-info">
                                                        <div class="col-md-4 name">
                                                             Administrator Name:
                                                        </div>
                                                        <div class="col-md-8 value">
                                                             ${preferences.siteInfo.siteAdministratorName}
                                                        </div>
                                                    </div>
                                                    <div class="row static-info">
                                                        <div class="col-md-4 name">
                                                             Administrator Email:
                                                        </div>
                                                        <div class="col-md-8 value">
                                                             ${preferences.siteInfo.siteAdministratorEmail}
                                                            <span class="label label-info label-sm">
                                                                 Email Confirmed
                                                            </span>
                                                        </div>
                                                    </div>
                                                    <div class="row static-info">
                                                        <div class="col-md-4 name">
                                                             Notifications Email:
                                                        </div>
                                                        <div class="col-md-8 value">
                                                            <span class="label label-success">
                                                             ${preferences.siteInfo.siteNotificationEmail}
                                                            </span>
                                                        </div>
                                                    </div>
                                                    <div class="row static-info">
                                                        <div class="col-md-4 name">
                                                             URL:
                                                        </div>
                                                        <div class="col-md-8 value">
                                                             ${preferences.siteInfo.siteURL}
                                                        </div>
                                                    </div>
                                                    <div class="row static-info">
                                                        <div class="col-md-4 name">
                                                             Testing URL:
                                                        </div>
                                                        <div class="col-md-8 value">
                                                             ${preferences.siteInfo.siteTestingURL}
                                                        </div>
                                                    </div>
                                                    <div class="row static-info">
                                                        <div class="col-md-4 name">
                                                             Mode: 
                                                        </div>
                                                        <div class="col-md-8 value">
                                                            <c:choose>
                                                                <c:when test="${preferences.siteInfo.siteMode==1}"><span class="label label-success">Live</span></c:when> 
                                                                <c:when test="${preferences.siteInfo.siteMode==2}"><span class="label label-info">Testing</c:when>  
                                                                <c:otherwise><span class="label label-info">Unknown</span></c:otherwise>   
                                                             </c:choose>
                                                        </div>
                                                    </div>
                                                    <div class="row static-info">
                                                        <div class="col-md-4 name">
                                                             Testing Email:
                                                        </div>
                                                        <div class="col-md-8 value">
                                                             ${preferences.siteInfo.siteTestingEmail}
                                                        </div>
                                                    </div>
                                                    <div class="row static-info">
                                                        <div class="col-md-4 name">
                                                             Email Password:
                                                        </div>
                                                        <div class="col-md-8 value">
                                                             ${preferences.siteInfo.siteTestingEmailPassword}
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-sm-12">
                                            <div class="portlet blue box">
                                                <div class="portlet-title">
                                                    <div class="caption">
                                                        <i class="fa fa-shopping-cart"></i>Store Information
                                                    </div>
                                                    <div class="actions">
                                                        <a href="StoreInfoUI.jsp?id=${preferences.storeInfo.storeInfoId}" class="btn default btn-sm">
                                                            <i class="fa fa-pencil"></i> Edit
                                                        </a>
                                                    </div>
                                                </div>
                                                <div class="portlet-body">
                                                    <div class="row static-info">
                                                        <div class="col-md-4 name">
                                                             Logo Title:
                                                        </div>
                                                        <div class="col-md-8 value">
                                                             ${preferences.storeInfo.storeLogoTitle}
                                                        </div>
                                                    </div>
                                                    <div class="row static-info">
                                                        <div class="col-md-4 name">
                                                             Logo Image URL:
                                                        </div>
                                                        <div class="col-md-8 value">
                                                             ${preferences.storeInfo.storeLogoImage}
                                                        </div>
                                                    </div>
                                                    <div class="row static-info">
                                                        <div class="col-md-4 name">
                                                             Store Name:
                                                        </div>
                                                        <div class="col-md-8 value">
                                                             ${preferences.storeInfo.storeName}
                                                        </div>
                                                    </div>
                                                    <div class="row static-info">
                                                        <div class="col-md-4 name">
                                                             Company Name:
                                                        </div>
                                                        <div class="col-md-8 value">
                                                             ${preferences.storeInfo.companyName}
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-sm-12">
                                            <div class="portlet blue box">
                                                <div class="portlet-title">
                                                    <div class="caption">
                                                        <i class="fa fa-flag"></i>Store Localization
                                                    </div>
                                                </div>
                                                <div class="portlet-body">
                                                    <form method="post" action="edit.jsp?form=changeDefaultLocale" class="form-horizontal" role="form">
                                                        <div class="form-body">
                                                            <div class="form-group">
                                                                <label for="name" class="col-md-2 control-label">Language</label>                                                                    
                                                                <div class="col-md-7">
                                                                    <select name="locale" class="form-control">
                                                                            <option value="1" selected>English</option>
                                                                            <option value="2">Spanish</option>
                                                                            <option value="3">German</option>
                                                                            <option value="4">French</option>
                                                                            <option value="5">Arabic</option>
                                                                            <option value="6">Farsi</option>
                                                                            <option value="7">Russian</option>
                                                                    </select>
                                                                </div>
                                                                
                                                                <div class="col-md-3">
                                                                    <button type="submit" class="btn green" style="float:right">Change Locality</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
									</div>
                </div>
                <div class="tab-pane" id="tab_1_2">
                    <div class="row profile">
                        <div class="col-md-3">
                            <ul class="ver-inline-menu tabbable margin-bottom-10">
                                <li class="active">
                                    <a data-toggle="tab" href="#tab_1-1">
                                        <i class="fa fa-money"></i> PayPal
                                    </a>
                                    <span class="after">
                                    </span>
                                </li>
                            </ul>
                        </div>
                        <div class="col-md-9">
                            <div class="tab-content">								                                                
                                <div id="tab_1-1" class="tab-pane active">
                                    <div class="portlet blue box">
                                        <div class="portlet-title">
                                            <div class="caption">
                                                <i class="fa fa-money"></i>PayPal Settings
                                            </div>
                                            <div class="actions">
                                                <a href="PaypalUI.jsp?id=${preferences.paypalInfo.paypalId}" class="btn default btn-sm">
                                                    <i class="fa fa-pencil"></i> Edit
                                                </a>
                                            </div>
                                        </div> 
                                        <div class="portlet-body">
                                            <div class="row static-info">
                                                <div class="col-md-5 name">
                                                     PayPal URL:
                                                </div>
                                                <div class="col-md-7 value">
                                                     ${preferences.paypalInfo.payPalURL}
                                                </div>
                                            </div>
                                            <div class="row static-info">
                                                <div class="col-md-5 name">
                                                     API UserName:
                                                </div>
                                                <div class="col-md-7 value">
                                                     ${preferences.paypalInfo.apiUserName}
                                                </div>
                                            </div>
                                            <div class="row static-info">
                                                <div class="col-md-5 name">
                                                     API Password:
                                                </div>
                                                <div class="col-md-7 value">
                                                     ${preferences.paypalInfo.apiPassword}
                                                </div>
                                            </div>
                                            <div class="row static-info">
                                                <div class="col-md-5 name">
                                                     API Signature:
                                                </div>
                                                <div class="col-md-7 value">
                                                     ${preferences.paypalInfo.apiSignature}
                                                </div>
                                            </div>
                                            <div class="row static-info">
                                                <div class="col-md-5 name">
                                                     API Endpoint:
                                                </div>
                                                <div class="col-md-7 value">
                                                     ${preferences.paypalInfo.apiEndpoint}
                                                </div>
                                            </div>
                                            <div class="row static-info">
                                                <div class="col-md-5 name">
                                                     Currency Code:
                                                </div>
                                                <div class="col-md-7 value">
                                                     ${preferences.paypalInfo.currencyCode}
                                                </div>
                                            </div>
                                            <div class="row static-info">
                                                <div class="col-md-5 name">
                                                     Active Profile:
                                                </div>
                                                <div class="col-md-7 value">
                                                     ${preferences.paypalInfo.activeProfile}
                                                </div>
                                            </div>
                                            <div class="row static-info">
                                                <div class="col-md-5 name">
                                                     Return URL:
                                                </div>
                                                <div class="col-md-7 value">
                                                     ${preferences.paypalInfo.returnURL}
                                                </div>
                                            </div>
                                            <div class="row static-info">
                                                <div class="col-md-5 name">
                                                     Cancel URL:
                                                </div>
                                                <div class="col-md-7 value">
                                                     ${preferences.paypalInfo.cancelURL}
                                                </div>
                                            </div>
                                            <div class="row static-info">
                                                <div class="col-md-5 name">
                                                     Payment Type:
                                                </div>
                                                <div class="col-md-7 value">
                                                     ${preferences.paypalInfo.paymentType}
                                                </div>
                                            </div>
                                            <div class="row static-info">
                                                <div class="col-md-5 name">
                                                     Environment:
                                                </div>
                                                <div class="col-md-7 value">
                                                     ${preferences.paypalInfo.environment}
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--end col-md-9--> 
                    </div>
                </div>
                <!--end tab-pane-->
                <div class="tab-pane" id="tab_1_3">                   
                    
                    <div class="row profile">
                        <div class="col-md-8 col-sm-12">
                            <div class="portlet yellow box">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="fa fa-cogs"></i>Product Showcase
                                    </div>
                                    <div class="actions">
                                        <a href="edit.jsp?form=OneColumnItemLayout" class="btn default btn-sm">
                                            <i class="fa fa-square"></i> One Column
                                        </a>  
                                        <a href="edit.jsp?form=TwoColumnItemLayout" class="btn default btn-sm">
                                            <i class="fa fa-th-large"></i> Two Column
                                        </a> 
                                        <a href="edit.jsp?form=ThreeColumnItemLayout" class="btn default btn-sm">
                                            <i class="fa fa-th"></i> Three Column
                                        </a> 
                                        <a href="SiteAttributeUI.jsp?column=AttributeType&columnValue=itemLayout" class="btn default btn-sm">
                                            <i class="fa fa-pencil"></i> Edit Attributes
                                        </a>  
                                    </div>
                                </div>
                                <div class="portlet-body">
                                    <div style="width:384px; height:398px; background:url(../images2/item-template.png);">
                                    </div>  
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-12">
                            &nbsp;
                        </div>                        
                    </div>                                                         
                </div>
                <div class="tab-pane" id="tab_1_4">                
                	<div class="row profile">
                        <div class="col-md-6 col-sm-12">
                            <div class="portlet yellow box">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="fa fa-cogs"></i>Site Header Types
                                    </div>
                                    <div class="actions">
                                        <a href="edit.jsp?form=header&type=compact-header" class="btn default btn-sm">
                                            1. Select Compact Header
                                        </a>  
                                        <a href="edit.jsp?form=header&type=full-header" class="btn default btn-sm">
                                            2. Select Full Header
                                        </a>   
                                    </div>
                                </div>
                                <div class="portlet-body">
                                    <div> 
                                        <img src="../images2/header_types.png" height="150px"  />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-sm-12">
                            <div class="portlet blue box">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="fa fa-shopping-cart"></i>Site Menu Types
                                    </div>
                                </div>
                                <div class="portlet-body"> 
                                    <table> 
                                        <tr>
                                            <td rowspan="12">
                                                <img src="../images2/menu_types.png"  />
                                            </td>
                                            <td>
                    
                                            </td>
                                        </tr>
                                        <tr>
                                            <td height="65px">
                                                <button id="underline-menu">Select</button>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td height="60px">
                                                <button id="box-menu">Select</button>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td  height="65px">
                                                <button id="separator-menu">Select</button>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td  height="50px">
                                                <button id="object-menu">Select</button>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td height="65px">
                                                <button id="single-tabbed-menu">Select</button>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td height="90px">
                                                <button id="full-tabbed-menu">Select</button>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td  height="35px">
                                                <button id="round-menu">Select</button>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td height="90px">
                                                <button id="box-full-width-menu">Select</button>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td height="50px">
                                                <button id="round-wide-menu">Select</button>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td height="45px">
                                                <button id="underline-wide-menu">Select</button>
                                            </td>
                                        </tr>
                                    </table>
                        
                                    <button id="square-menu">Square Menu</button> <button id="rounded-square-menu">Rounded Square</button> <button id="advanced-square-menu">Square Advanced</button>  <br />
                                </div>
                            </div>
                        </div>                        
                    </div> 
                </div>
                <div class="tab-pane" id="tab_1_5">
                    <div class="row portfolio-block">
                        <div class="col-md-10">
                            <div class="portfolio-text">                                
                                <div class="portfolio-text-info" style="margin-left:10px;">
                                    <h4>Products - Reset Products To Original Factory Settings</h4>
                                    <p>
                                        Note: This will remove all associated product data such as pictures, brands, categories, files, etc.
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="portfolio-btn">
                                <a href="clear.jsp?info=items" class="btn bigicn-only">
                                    <span>
                                        Reset
                                    </span>
                                </a>
                            </div>
                        </div>
                    </div>
                    <!--end row-->
                    <div class="row portfolio-block">
                        <div class="col-md-10 col-sm-12 portfolio-text">
                            <div class="portfolio-text-info" style="margin-left:10px;">                                
                                <h4>Site Files - Reset Files and Folders To Original Factory Settings</h4>
                                <p>
                                    Note: This will remove all associated data such as folders, etc.
                                </p>
                            </div>
                        </div>
                        <div class="col-md-2 col-sm-12 portfolio-btn">
                            <a href="clear.jsp?info=files" class="btn bigicn-only">
                                <span>
                                    Reset
                                </span>
                            </a>
                        </div>
                    </div>
                    <!--end row-->
                    <div class="row portfolio-block">
                        <div class="col-md-10 col-sm-12 portfolio-text">
                            <div class="portfolio-text-info" style="margin-left:10px;">                                
                                <h4>Site Images - Reset Images To Original Factory Settings</h4>
                                <p>
                                    Note: This will remove all associated data.
                                </p>
                            </div>
                        </div>
                        <div class="col-md-2 col-sm-12 portfolio-btn">
                            <a href="clear.jsp?info=images" class="btn bigicn-only">
                                <span>
                                    Reset
                                </span>
                            </a>
                        </div>
                    </div>
                    <!--end row-->
                    <div class="row portfolio-block">
                        <div class="col-md-10 col-sm-12 portfolio-text">
                            <div class="portfolio-text-info" style="margin-left:10px;">                                
                                <h4>User Actions - Reset All User Actions To Original Factory Settings</h4>
                                <p>
                                    Note: This will remove all associated data.
                                </p>
                            </div>
                        </div>
                        <div class="col-md-2 col-sm-12 portfolio-btn">
                            <a href="clear.jsp?info=actions" class="btn bigicn-only">
                                <span>
                                    Reset
                                </span>
                            </a>
                        </div>
                    </div>
                    <!--end row-->
                </div>
                <!--end tab-pane-->                
            </div>
        </div>
        <!--END TABS-->
    </div>
</div>
                                    
                                    
                                    
                
                </div>
            </div>
        </div>
        <!-- END CONTENT -->
    </div>
    <!-- END CONTAINER -->
   
	<%@include file="index_footer.jsp" %> 
    
    <!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->       
          
		<%@include file="index_global_scripts.jsp" %> 
        
        <!-- BEGIN PAGE LEVEL PLUGINS -->
		<script type="text/javascript" src="../assets/global/plugins/select2/select2.min.js"></script>
        <!-- END PAGE LEVEL PLUGINS -->
        
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
        <script src="../assets/global/scripts/metronic.js" type="text/javascript"></script>
        <script src="../assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL SCRIPTS -->
        
        <script>
            jQuery(document).ready(function() {    
           		Metronic.init(); // init metronic core components
				Layout.init(); // init current layout
				
				$("#underline-menu").button().click(function() {
					window.location = 'edit.jsp?form=menu&type=underline-menu';
				});
				$("#box-menu").button().click(function() {
					window.location = 'edit.jsp?form=menu&type=box-menu';
				});
				$("#separator-menu").button().click(function() {
					window.location = 'edit.jsp?form=menu&type=separator-menu';
				});
				$("#object-menu").button().click(function() {
					window.location = 'edit.jsp?form=menu&type=object-menu';
				});
				$("#single-tabbed-menu").button().click(function() {
					window.location = 'edit.jsp?form=menu&type=single-tabbed-menu';
				});
				$("#full-tabbed-menu").button().click(function() {
					window.location = 'edit.jsp?form=menu&type=full-tabbed-menu';
				});
				$("#round-menu").button().click(function() {
					window.location = 'edit.jsp?form=menu&type=round-menu';
				});
				$("#box-full-width-menu").button().click(function() {
					window.location = 'edit.jsp?form=menu&type=box-full-width-menu';
				});
				$("#round-wide-menu").button().click(function() {
					window.location = 'edit.jsp?form=menu&type=round-wide-menu';
				});
				$("#underline-wide-menu").button().click(function() {
					window.location = 'edit.jsp?form=menu&type=underline-wide-menu';
				});
				$("#square-menu").button().click(function() {
					window.location = 'edit.jsp?form=menu&type=square-menu';
				});
				$("#rounded-square-menu").button().click(function() {
					window.location = 'edit.jsp?form=menu&type=rounded-square-menu';
				});
				$("#advanced-square-menu").button().click(function() {
					window.location = 'edit.jsp?form=menu&type=advanced-square-menu';
				});
            });
        </script>
    <!-- END JAVASCRIPTS -->
    </body>
</html>