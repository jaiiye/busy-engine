<%@page import="java.text.*"%>
<%@page import="java.util.*"%>
<%@page import="com.busy.dao.*"%>
<%@page import="com.transitionsoft.*"%>
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
ArrayList<Paypal> paypalList = new ArrayList<Paypal>();
if (request.getParameter("column") != null && request.getParameter("columnValue") != null)
{
    paypalList = Paypal.getAllPaypalByColumn(request.getParameter("column"), request.getParameter("columnValue"));
}
else
{
    paypalList = Paypal.getAllPaypal();
}
request.setAttribute("paypalList", paypalList);
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

        <%@include file="index_global_styles.jsp"%>


        <!-- BEGIN PAGE LEVEL STYLES -->
            <link rel="stylesheet" type="text/css" href="../assets/global/plugins/select2/select2.css"/>
            <link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-datepicker/css/datepicker.css"/>   
            <link rel="stylesheet" type="text/css" href="../assets/global/plugins/datatables/extensions/Scroller/css/dataTables.scroller.min.css"/>
            <link rel="stylesheet" type="text/css" href="../assets/global/plugins/datatables/extensions/ColReorder/css/dataTables.colReorder.min.css"/>
            <link rel="stylesheet" type="text/css" href="../assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
        <!-- END PAGE LEVEL STYLES -->
        
        <!-- BEGIN THEME STYLES -->
            <link rel="stylesheet" type="text/css" href="../assets/global/css/components.css"/>
            <link rel="stylesheet" type="text/css" href="../assets/global/css/plugins.css" />
            <link rel="stylesheet" type="text/css" href="../assets/admin/layout/css/layout.css" />
            <link rel="stylesheet" type="text/css" href="../assets/admin/layout/css/themes/light.css" id="style_color"/>
            <link rel="stylesheet" type="text/css" href="../assets/admin/layout/css/custom.css"/>
		<!-- END THEME STYLES -->

        <%@include file="index_global_scripts.jsp"%>

        
	<script type="text/javascript" src="../uploadify/jquery.uploadify3.2.min.js"></script> 

        <link rel="shortcut icon" href="favicon.ico"/>
    </head>

    <body class="page-header-fixed page-footer-fixed">

        <%@include file="index_header.jsp"%>
 

        <div class="clearfix"></div>
        <!-- BEGIN CONTAINER -->
        <div class="page-container">

        <% request.setAttribute("category", "configuration"); %>
        <% request.setAttribute("subCategory", "preferences"); %>
        <%@include file="index_sidebar.jsp"%>


            <!-- BEGIN CONTENT -->
            <div class="page-content-wrapper">
    
                    <div class="page-content">

                        <!-- BEGIN PAGE HEADER-->
                        <div class="row">
                            <div class="col-md-12">
                                <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                                <h3 class="page-title"> Paypal </h3>
                                <ul class="page-breadcrumb breadcrumb">                                
                                    <li>
                                        <i class="fa fa-home"></i><a href="index.jsp">Home</a>
                                        <i class="fa fa-angle-right"></i>
                                    </li>
                                    <li>
                                        <a href="#"> Configuration </a>
                                        <i class="fa fa-angle-right"></i>
                                    </li>
                                    <li>
                                        <a href="#">Paypal</a>
                                    </li>
                                </ul>
                                <!-- END PAGE TITLE & BREADCRUMB-->
                                
                                <!-- BEGIN PAGE NOTIFICATIONS -->
                                <c:if test="${param.SuccessMsg != null}">
                                    <div class="alert alert-success alert-dismissable">
                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
                                        ${param.SuccessMsg}
                                    </div>				
                                </c:if>
                                <c:if test="${param.ErrorMsg != null}">
                                    <div class="alert alert-danger alert-dismissable">
                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
                                        ${param.ErrorMsg}
                                    </div>		
                                </c:if>
                                
                                <!-- END PAGE NOTIFICATIONS -->
                            </div>
                        </div>
                        <!-- END PAGE HEADER-->

                        <!-- BEGIN PAGE CONTENT-->        
                        <div class="row">
                            <div class="col-md-6">
                                <!-- BEGIN FILTER PORTLET-->
                                <div class="portlet box blue-madison">
                                    <div class="portlet-title">
                                        <div class="caption">
                                            <i class="fa fa-search"></i>Search
                                        </div>
                                        <div class="tools">
                                            <a href="javascript:;" class="collapse"></a>
                                            <a href="javascript:;" class="remove"></a>
                                        </div>
                                    </div>
                                    <div class="portlet-body">		                                    										
                                        <form method="post" action="PaypalUI.jsp" class="form-horizontal" role="form">
                                        	<div class="form-body">
                                                <div class="form-group">
                                                    <div class="col-md-4">
                                                        <select name="column" class="form-control">
                                                            <option value="PaypalId" ${param.column == 'PaypalId' ? "selected" : "" } >PaypalId</option>                                                            
                                                           <option value="PayPalURL" ${param.column == 'PayPalURL' ? "selected" : "" } >PayPalURL</option>                                                            
                                                           <option value="CurrencyCode" ${param.column == 'CurrencyCode' ? "selected" : "" } >CurrencyCode</option>                                                            
                                                           <option value="ApiUserName" ${param.column == 'ApiUserName' ? "selected" : "" } >ApiUserName</option>                                                            
                                                           <option value="ApiPassword" ${param.column == 'ApiPassword' ? "selected" : "" } >ApiPassword</option>                                                            
                                                           <option value="ApiSignature" ${param.column == 'ApiSignature' ? "selected" : "" } >ApiSignature</option>                                                            
                                                           <option value="ApiEndpoint" ${param.column == 'ApiEndpoint' ? "selected" : "" } >ApiEndpoint</option>                                                            
                                                           <option value="ActiveProfile" ${param.column == 'ActiveProfile' ? "selected" : "" } >ActiveProfile</option>                                                            
                                                           <option value="ReturnURL" ${param.column == 'ReturnURL' ? "selected" : "" } >ReturnURL</option>                                                            
                                                           <option value="CancelURL" ${param.column == 'CancelURL' ? "selected" : "" } >CancelURL</option>                                                            
                                                           <option value="PaymentType" ${param.column == 'PaymentType' ? "selected" : "" } >PaymentType</option>                                                            
                                                           <option value="Environment" ${param.column == 'Environment' ? "selected" : "" } >Environment</option>                                                            
                                                                                                                                                                                  
                                                        </select> 
                                                    </div>                                                         
                                                    <div class="col-md-5">
                                                        <input type="text" name="columnValue" class="form-control"/>
                                                    </div>
                                                    <div class="col-md-3">
	                                                <button type="submit" class="btn grey-silver">Filter</button>
                                                        <button type="button" class="btn grey-cascade" style="float:right" onclick="javascript:window.location = 'PaypalUI.jsp';"><i class="fa fa-refresh"></i></button>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>           
                                    </div>
                                </div>
                                <!-- END FILTER PORTLET-->
                            </div>
                            <div class="col-md-6">
                                <!-- BEGIN OPERATIONS PORTLET-->
                                <div class="portlet">
                                    <div class="portlet-title">
                                        <div class="caption">
                                            <i class="fa fa-cog"></i>Operations
                                        </div>
                                        <div class="tools">
                                            <a href="javascript:;" class="collapse"></a>
                                            <a href="javascript:;" class="remove"></a>
                                        </div>
                                    </div>
                                    <div class="portlet-body">
                                        
                                        
                                    </div>
                                </div>
                                <!-- END OPERATIONS PORTLET-->
                            </div>
                        </div>
                        <!-- START RECORD DETAILS -->
                                  
                        <c:forEach var="paypal" items="${paypalList}" >
                        <div class="row" id="itemBox${paypal.paypalId}" style="display:${param.id == null ? "none" : paypal.paypalId==param.id ? "block" : "none"}">                      
                            <div class="col-md-12">
                                <div class="portlet box green-seagreen">
                                    <div class="portlet-title">
                                        <div class="caption">Record Details</div>
                                        <div class="actions">                                                 
                                            <div class="btn-group">                                
                                                <a href="javascript:toggleVisibility('itemBox${paypal.paypalId}');" class="btn btn-default"><i class="fa fa-times"></i>&nbsp;Close</a> 
                                            </div>
                                        </div>
                                    </div>
                                    <div class="portlet-body">	
                                        <div class="portlet-body form">
                                            <form class="form-horizontal" name="edit" action="../Operations?form=paypal&action=2" method="post">

                                                <input type="hidden" name="paypalId" value="${paypal.paypalId}" />
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="payPalURL">PayPalURL:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="payPalURL" class="form-control maxlength-handler" maxlength="95" value="${paypal.payPalURL}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="currencyCode">CurrencyCode:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="currencyCode" class="form-control maxlength-handler" maxlength="5" value="${paypal.currencyCode}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="apiUserName">ApiUserName:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="apiUserName" class="form-control maxlength-handler" maxlength="80" value="${paypal.apiUserName}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="apiPassword">ApiPassword:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="apiPassword" class="form-control maxlength-handler" maxlength="25" value="${paypal.apiPassword}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="apiSignature">ApiSignature:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="apiSignature" class="form-control maxlength-handler" maxlength="65" value="${paypal.apiSignature}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="apiEndpoint">ApiEndpoint:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="apiEndpoint" class="form-control maxlength-handler" maxlength="125" value="${paypal.apiEndpoint}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="activeProfile">ActiveProfile:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="activeProfile" class="form-control" value="${paypal.activeProfile}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="returnURL">ReturnURL:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="returnURL" class="form-control maxlength-handler" maxlength="255" value="${paypal.returnURL}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="cancelURL">CancelURL:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="cancelURL" class="form-control maxlength-handler" maxlength="255" value="${paypal.cancelURL}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="paymentType">PaymentType:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="paymentType" class="form-control maxlength-handler" maxlength="15" value="${paypal.paymentType}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="environment">Environment:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="environment" class="form-control maxlength-handler" maxlength="15" value="${paypal.environment}" />
                                                    </div>
                                                </div>
                                                

                                                <div class="form-actions right">
                                                    <input type="submit" value="Save Changes" class="btn green" />
                                                </div>
                                            </form>
                                        </div> 
                                    </div>
                                </div> 
                            </div>
                        </div>

                        
                        </c:forEach>
                        <!-- END RECORD DETAILS-->


                        <!-- BEGIN MODAL NEW Paypal FORM-->                    
                        <div id="myModal_new_record" class="modal fade" role="dialog" aria-hidden="true">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                        <h4 class="modal-title">Add a new Paypal</h4>
                                    </div>
                                    <div class="modal-body form">
                                        <!-- BEGIN FORM-->
                                        <form method="post" action="../Operations?form=paypal&action=1" id="create_form" class="horizontal-form">
                                            <div class="form-body">
                                                <div class="alert alert-danger display-hide">
                                                    <button class="close" data-close="alert"></button>
                                                    You have some form errors. Please check below.
                                                </div>
                                                <div class="alert alert-success display-hide">
                                                    <button class="close" data-close="alert"></button>
                                                    Your form validation is successful!
                                                </div>

                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">PayPalURL</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="payPalURL" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="95" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">CurrencyCode</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="currencyCode" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="5" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">ApiUserName</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="apiUserName" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="80" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">ApiPassword</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="apiPassword" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="25" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">ApiSignature</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="apiSignature" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="65" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">ApiEndpoint</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="apiEndpoint" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="125" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">ActiveProfile</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="activeProfile" class="form-control" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">ReturnURL</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="returnURL" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="255" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">CancelURL</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="cancelURL" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="255" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">PaymentType</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="paymentType" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="15" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">Environment</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="environment" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="15" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                

                                            </div>

                                            <div class="form-actions right">
                                                <button type="button" class="btn red"  data-dismiss="modal"><i class="fa fa-minus"></i>&nbsp;Cancel</button>
                                                <button type="submit" class="btn green"><i class="fa fa-plus"></i>&nbsp;Create</button>
                                            </div>
                                        </form>
                                        <!-- END FORM-->
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- END MODAL NEW Paypal FORM-->


                        <!-- BEGIN DATA TABLE--> 
                        <div class="row">
                            <div class="col-md-12">
                                 <div class="portlet box red-flamingo">
                                    <div class="portlet-title">
                                        <div class="caption">
                                            <i class="fa fa-list-alt"></i>Paypal Listing
                                        </div>
                                        <div class="actions">
                                            <div class="btn-group">                                
                                                <a href="#myModal_new_record" class="btn btn-default" data-toggle="modal"><i class="fa fa-plus"></i> Add </a> 
                                            </div>
                                            <div class="btn-group"> 
                                                <a class="btn btn-default" href="#" data-toggle="dropdown">
                                                    <i class="fa fa-columns"></i> Columns <i class="fa fa-angle-down"></i>
                                                </a>
                                                <div id="sample_2_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">                                                    
                                                    <label><input type="checkbox" checked data-column="0">Id</label> 
                                                    <label><input type="checkbox" checked data-column="1">PayPalURL</label> 
                                                    <label><input type="checkbox" checked data-column="2">CurrencyCode</label> 
                                                    <label><input type="checkbox" checked data-column="3">ApiUserName</label> 
                                                    <label><input type="checkbox" checked data-column="4">ApiPassword</label> 
                                                    <label><input type="checkbox" checked data-column="5">ApiSignature</label> 
                                                    <label><input type="checkbox" checked data-column="6">ApiEndpoint</label> 
                                                    <label><input type="checkbox" checked data-column="7">ActiveProfile</label> 
                                                    <label><input type="checkbox" checked data-column="8">ReturnURL</label> 
                                                    <label><input type="checkbox" checked data-column="9">CancelURL</label> 
                                                    <label><input type="checkbox" checked data-column="10">PaymentType</label> 
                                                    <label><input type="checkbox" checked data-column="11">Environment</label> 
                                                    
                                                    <label><input type="checkbox" checked data-column="12">Actions</label>
                                                </div>
                                            </div>                                                 
                                            <div class="btn-group">                                
                                                <a href="#resetData" class="btn btn-default" data-toggle="modal"><i class="fa fa-flash"></i>&nbsp;Clear</a> 
                                            </div>
                                        </div>
                                    </div>
                                    <div class="portlet-body">
                                    	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
                                            <thead>							
                                                <tr>
                                                    <th>Id</th> 
                                                    <th>PayPalURL</th> 
                                                    <th>CurrencyCode</th> 
                                                    <th>ApiUserName</th> 
                                                    <th>ApiPassword</th> 
                                                    <th>ApiSignature</th> 
                                                    <th>ApiEndpoint</th> 
                                                    <th>ActiveProfile</th> 
                                                    <th>ReturnURL</th> 
                                                    <th>CancelURL</th> 
                                                    <th>PaymentType</th> 
                                                    <th>Environment</th> 
                                                                                                        
                                                    <th>Actions</th> 
                                                </tr>                                
                                            </thead>
                                            <tbody>                                                
                                                <c:forEach var="paypal" items="${paypalList}" >
                                                <tr>                                                    
                                                    <td>${paypal.paypalId}</td> 
                                                    <td>${paypal.payPalURL}</td> 
                                                    <td>${paypal.currencyCode}</td> 
                                                    <td>${paypal.apiUserName}</td> 
                                                    <td>${paypal.apiPassword}</td> 
                                                    <td>${paypal.apiSignature}</td> 
                                                    <td>${paypal.apiEndpoint}</td> 
                                                    <td>${paypal.activeProfile}</td> 
                                                    <td>${paypal.returnURL}</td> 
                                                    <td>${paypal.cancelURL}</td> 
                                                    <td>${paypal.paymentType}</td> 
                                                    <td>${paypal.environment}</td> 
                                                    
                                                    <td>
                                                        <button id="edit-item${paypal.paypalId}" class="btn btn-sm green filter-submit margin-bottom"><span class="glyphicon glyphicon-pencil"></span></button>&nbsp;
                                                        <button id="delete-item${paypal.paypalId}" class="btn btn-sm red filter-cancel"><span class="glyphicon glyphicon-trash"></span></button> 
                                                    </td>
                                                </tr>  
                                                <script type="text/javascript">
                                                    $("#edit-item${paypal.paypalId}").button().click(function() {
                                                        toggleVisibility('itemBox${paypal.paypalId}');                                                        
                                                        document.getElementById('itemBox${paypal.paypalId}').scrollIntoView();
                                                        window.scrollBy(0,-80);
                                                    });
                                                    $("#delete-item${paypal.paypalId}").button().click(function() {
                                                        window.location = '../Operations?form=paypal&action=3&id=${paypal.paypalId}';
                                                    });
                                                </script>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <!-- END DATA TABLE-->

                        <div id="resetData" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                        <h4 class="modal-title">Reset Confirmation</h4>
                                    </div>
                                    <div class="modal-body">
                                        <p>
                                             Are you sure you like to delete all records?
                                        </p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" data-dismiss="modal" class="btn default">No</button>
                                        
                                        <button onClick="window.location ='../Operations?form=paypal&action=4';" type="button" data-dismiss="modal" class="btn green">Yes</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- END PAGE CONTENT-->
                    </div>
                </div>
            <!-- END CONTENT -->
        </div>
        <!-- END CONTAINER -->
        
        <%@include file="index_footer.jsp"%>


        <!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->       

       <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script type="text/javascript" src="../assets/global/plugins/ckeditor/ckeditor.js"></script>
        <script type="text/javascript" src="../assets/global/plugins/jquery-validation/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="../assets/global/plugins/jquery-validation/js/additional-methods.min.js"></script>
        <script type="text/javascript" src="../assets/global/plugins/select2/select2.min.js"></script>
        <script type="text/javascript" src="../assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="../assets/global/plugins/datatables/extensions/TableTools/js/dataTables.tableTools.min.js"></script>
        <script type="text/javascript" src="../assets/global/plugins/datatables/extensions/ColReorder/js/dataTables.colReorder.min.js"></script>
        <script type="text/javascript" src="../assets/global/plugins/datatables/extensions/Scroller/js/dataTables.scroller.min.js"></script>
        <script type="text/javascript" src="../assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
        <script type="text/javascript" src="../assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
        <script type="text/javascript" src="../assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
        <script type="text/javascript" src="../assets/global/plugins/bootstrap-maxlength/bootstrap-maxlength.min.js"></script>
        <script type="text/javascript" src="../assets/global/plugins/jquery-inputmask/jquery.inputmask.bundle.min.js"></script>
        <!-- END PAGE LEVEL PLUGINS -->
        
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
        <script type="text/javascript" src="../assets/global/scripts/metronic.js"></script>
        <script type="text/javascript" src="../assets/admin/layout/scripts/layout.js"></script>
        <script src="../assets/global/scripts/datatable.js"></script>
        <!-- END PAGE LEVEL SCRIPTS -->
        
        <script>
            jQuery(document).ready(function() {

                Metronic.init(); // init metronic core components
                Layout.init(); // init current layout
                
                <%@include file="index_common_scripts.jsp"%>

                //init maxlength handler
                $('.maxlength-handler').maxlength({
                    limitReachedClass: "label label-danger",
                    alwaysShow: true,
                    threshold: 5
                });

                //Date InputMask Handler
                $.extend($.inputmask.defaults, {
                    'autounmask': true
                });

                $("#mask_date2").inputmask("y-m-d", {
                    "placeholder": "yyyy-mm-dd"
                }); //multi-char placeholder

                //DateTime Picker initializer code
                $(".form_datetime").datetimepicker({
                    autoclose: true,
                    isRTL: Metronic.isRTL(),
                    todayBtn: true,
                    format: "yyyy-mm-dd",
                    pickerPosition: (Metronic.isRTL() ? "bottom-right" : "bottom-left")
                });

                // Modal FORM VALIDATION SCRIPT
                // http://docs.jquery.com/Plugins/Validation
                var form2 = $('#create_form');
                var error2 = $('.alert-danger', form2);
                var success2 = $('.alert-success', form2);

                form2.validate({
                    errorElement: 'span', //default input error message container
                    errorClass: 'help-block', // default input error message class
                    focusInvalid: false, // do not focus the last invalid input
                    ignore: "",
                    rules: {                                
                        paypalId:    { required: true, number: true }, 
                        payPalURL:    { required: true, minlength: 1, maxlength: 95}, 
                        currencyCode:    { required: true, minlength: 1, maxlength: 5}, 
                        apiUserName:    { required: true, minlength: 1, maxlength: 80}, 
                        apiPassword:    { required: true, minlength: 1, maxlength: 25}, 
                        apiSignature:    { required: true, minlength: 1, maxlength: 65}, 
                        apiEndpoint:    { required: true, minlength: 1, maxlength: 125}, 
                        activeProfile:    { required: true }, 
                        returnURL:    { required: true, minlength: 1, maxlength: 255}, 
                        cancelURL:    { required: true, minlength: 1, maxlength: 255}, 
                        paymentType:    { required: true, minlength: 1, maxlength: 15}, 
                        environment:    { required: true, minlength: 1, maxlength: 15} 
                        
                    },
                    invalidHandler: function (event, validator) { //display error alert on form submit              
                        success2.hide();
                        error2.show();
                        Metronic.scrollTo(error2, -200);
                    },	
                    errorPlacement: function (error, element) { // render error placement for each input type
                        var icon = $(element).parent('.input-icon').children('i');
                        icon.removeClass('fa-check').addClass("fa-warning");  
                        icon.attr("data-original-title", error.text()).tooltip({'container': 'form'});
                    },
                    highlight: function (element) { // hightlight error inputs
                        $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
                    },	
                    unhighlight: function (element) { // revert the change done by hightlight

                    },
                    success: function (label, element) {
                        var icon = $(element).parent('.input-icon').children('i');
                        // set success class to the control group
                        $(element).closest('.form-group').removeClass('has-error').addClass('has-success'); 
                        icon.removeClass("fa-warning").addClass("fa-check");
                    },
                    submitHandler: function (form) {
                        success2.show();
                        error2.hide();                                               
                        form.submit();
                    }
                });


            });
        </script>
        <!-- END JAVASCRIPTS -->
    </body>
</html>

