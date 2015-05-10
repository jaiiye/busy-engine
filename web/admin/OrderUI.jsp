<%@page import="java.text.*"%>
<%@page import="java.util.*"%>
<%@page import="com.busy.engine.dao.*"%>
<%@page import="com.busy.engine.*"%>
<%@page import="com.busy.engine.data.*"%>
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
ArrayList<Order> orderList = new ArrayList<Order>();
if (request.getParameter("column") != null && request.getParameter("columnValue") != null)
{
    orderList = new OrderDaoImpl().findByColumn(request.getParameter("column"), request.getParameter("columnValue"), null, null);
}
else
{
    orderList = new OrderDaoImpl().findAll(null, null);
}
request.setAttribute("orderList", orderList);
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
        <title>Busy Administrator: Business Administration Portal</title>

        <%@include file="index_global_styles.jsp"%>


        <!-- BEGIN PAGE LEVEL STYLES -->
            <link rel="stylesheet" type="text/css" href="../assets/global/plugins/select2/select2.css"/>
            <link rel="stylesheet" href="../assets/global/plugins/data-tables/DT_bootstrap.css"/>
            <link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-datepicker/css/datepicker.css"/>
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

        <% request.setAttribute("category", "E-Commerce"); %>
        <% request.setAttribute("subCategory", "orders"); %>
        <%@include file="index_sidebar.jsp"%>


            <!-- BEGIN CONTENT -->
            <div class="page-content-wrapper">
    
                    <div class="page-content">

                        <!-- BEGIN PAGE HEADER-->
                        <div class="row">
                            <div class="col-md-12">
                                <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                                <h3 class="page-title"> Order </h3>
                                <ul class="page-breadcrumb breadcrumb">                                
                                    <li>
                                        <i class="fa fa-home"></i><a href="index.jsp">Home</a>
                                        <i class="fa fa-angle-right"></i>
                                    </li>
                                    <li>
                                        <a href="#"> E-Commerce </a>
                                        <i class="fa fa-angle-right"></i>
                                    </li>
                                    <li>
                                        <a href="#">Order</a>
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
                                        <form method="post" action="OrderUI.jsp" class="form-horizontal" role="form">
                                        	<div class="form-body">
                                                <div class="form-group">
                                                    <div class="col-md-4">
                                                        <select name="column" class="form-control">
                                                            <option value="OrderId" ${param.column == 'OrderId' ? "selected" : "" } >OrderId</option>                                                            
                                                           <option value="OrderDate" ${param.column == 'OrderDate' ? "selected" : "" } >OrderDate</option>                                                            
                                                           <option value="ShipDate" ${param.column == 'ShipDate' ? "selected" : "" } >ShipDate</option>                                                            
                                                           <option value="PaymentMethod" ${param.column == 'PaymentMethod' ? "selected" : "" } >PaymentMethod</option>                                                            
                                                           <option value="PurchaseOrder" ${param.column == 'PurchaseOrder' ? "selected" : "" } >PurchaseOrder</option>                                                            
                                                           <option value="TransactionId" ${param.column == 'TransactionId' ? "selected" : "" } >TransactionId</option>                                                            
                                                           <option value="AmountBilled" ${param.column == 'AmountBilled' ? "selected" : "" } >AmountBilled</option>                                                            
                                                           <option value="PaymentStatus" ${param.column == 'PaymentStatus' ? "selected" : "" } >PaymentStatus</option>                                                            
                                                           <option value="PendingReason" ${param.column == 'PendingReason' ? "selected" : "" } >PendingReason</option>                                                            
                                                           <option value="PaymentType" ${param.column == 'PaymentType' ? "selected" : "" } >PaymentType</option>                                                            
                                                           <option value="TransactionFee" ${param.column == 'TransactionFee' ? "selected" : "" } >TransactionFee</option>                                                            
                                                           <option value="CurrencyCode" ${param.column == 'CurrencyCode' ? "selected" : "" } >CurrencyCode</option>                                                            
                                                           <option value="PayerId" ${param.column == 'PayerId' ? "selected" : "" } >PayerId</option>                                                            
                                                           <option value="SubtotalAmount" ${param.column == 'SubtotalAmount' ? "selected" : "" } >SubtotalAmount</option>                                                            
                                                           <option value="DiscountAmount" ${param.column == 'DiscountAmount' ? "selected" : "" } >DiscountAmount</option>                                                            
                                                           <option value="TaxAmount" ${param.column == 'TaxAmount' ? "selected" : "" } >TaxAmount</option>                                                            
                                                           <option value="ShippingAmount" ${param.column == 'ShippingAmount' ? "selected" : "" } >ShippingAmount</option>                                                            
                                                           <option value="TotalAmount" ${param.column == 'TotalAmount' ? "selected" : "" } >TotalAmount</option>                                                            
                                                           <option value="RefundAmount" ${param.column == 'RefundAmount' ? "selected" : "" } >RefundAmount</option>                                                            
                                                           <option value="Notes" ${param.column == 'Notes' ? "selected" : "" } >Notes</option>                                                            
                                                           <option value="OrderStatus" ${param.column == 'OrderStatus' ? "selected" : "" } >OrderStatus</option>                                                            
                                                           <option value="ShippingId" ${param.column == 'ShippingId' ? "selected" : "" } >ShippingId</option>                                                            
                                                           <option value="AffiliateId" ${param.column == 'AffiliateId' ? "selected" : "" } >AffiliateId</option>                                                            
                                                                                                                                                                                  
                                                        </select> 
                                                    </div>                                                         
                                                    <div class="col-md-5">
                                                        <input type="text" name="columnValue" class="form-control"/>
                                                    </div>
                                                    <div class="col-md-3">
	                                                <button type="submit" class="btn grey-silver">Filter</button>
                                                        <button type="button" class="btn grey-cascade" style="float:right" onclick="javascript:window.location = 'OrderUI.jsp';"><i class="fa fa-refresh"></i></button>
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
                                  
                        <c:forEach var="order" items="${orderList}" >
                        <div class="row" id="itemBox${order.orderId}" style="display:${param.id == null ? "none" : order.orderId==param.id ? "block" : "none"}">                      
                            <div class="col-md-12">
                                <div class="portlet box green-seagreen">
                                    <div class="portlet-title">
                                        <div class="caption">Record Details</div>
                                        <div class="actions">                                                 
                                            <div class="btn-group">                                
                                                <a href="javascript:toggleVisibility('itemBox${order.orderId}');" class="btn btn-default"><i class="fa fa-times"></i>&nbsp;Close</a> 
                                            </div>
                                        </div>
                                    </div>
                                    <div class="portlet-body">	
                                        <div class="portlet-body form">
                                            <form class="form-horizontal" name="edit" action="../Operations?form=order&action=2" method="post">

                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="orderId">Order:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="orderId" class="form-control" value="${order.orderId}" />

                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="orderDate">OrderDate:</label>
                                                    <div  class="col-md-10">
                                                        <div class="input-group date form_datetime" data-date="2012-12-21T15:25:00Z">        <input type="text" name="orderDate" value="${order.orderDate}" class="form-control">        <span class="input-group-btn">                <button class="btn default date-reset" type="button"><i class="fa fa-times"></i></button>        </span>        <span class="input-group-btn">                <button class="btn default date-set" type="button"><i class="fa fa-calendar"></i></button>        </span></div>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="shipDate">ShipDate:</label>
                                                    <div  class="col-md-10">
                                                        <div class="input-group date form_datetime" data-date="2012-12-21T15:25:00Z">        <input type="text" name="shipDate" value="${order.shipDate}" class="form-control">        <span class="input-group-btn">                <button class="btn default date-reset" type="button"><i class="fa fa-times"></i></button>        </span>        <span class="input-group-btn">                <button class="btn default date-set" type="button"><i class="fa fa-calendar"></i></button>        </span></div>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="paymentMethod">PaymentMethod:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="paymentMethod" class="form-control maxlength-handler" maxlength="100" value="${order.paymentMethod}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="purchaseOrder">PurchaseOrder:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="purchaseOrder" class="form-control maxlength-handler" maxlength="100" value="${order.purchaseOrder}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="transactionId">Transaction:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="transactionId" class="form-control maxlength-handler" maxlength="45" value="${order.transactionId}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="amountBilled">AmountBilled:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="amountBilled" class="form-control" value="${order.amountBilled}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="paymentStatus">PaymentStatus:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="paymentStatus" class="form-control maxlength-handler" maxlength="45" value="${order.paymentStatus}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="pendingReason">PendingReason:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="pendingReason" class="form-control maxlength-handler" maxlength="45" value="${order.pendingReason}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="paymentType">PaymentType:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="paymentType" class="form-control maxlength-handler" maxlength="15" value="${order.paymentType}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="transactionFee">TransactionFee:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="transactionFee" class="form-control" value="${order.transactionFee}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="currencyCode">CurrencyCode:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="currencyCode" class="form-control maxlength-handler" maxlength="45" value="${order.currencyCode}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="payerId">Payer:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="payerId" class="form-control maxlength-handler" maxlength="45" value="${order.payerId}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="subtotalAmount">SubtotalAmount:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="subtotalAmount" class="form-control" value="${order.subtotalAmount}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="discountAmount">DiscountAmount:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="discountAmount" class="form-control" value="${order.discountAmount}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="taxAmount">TaxAmount:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="taxAmount" class="form-control" value="${order.taxAmount}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="shippingAmount">ShippingAmount:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="shippingAmount" class="form-control" value="${order.shippingAmount}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="totalAmount">TotalAmount:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="totalAmount" class="form-control" value="${order.totalAmount}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="refundAmount">RefundAmount:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="refundAmount" class="form-control" value="${order.refundAmount}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="notes">Notes:</label>
                                                    <div  class="col-md-10">
                                                        <textarea name="notes" class="ckeditor form-control" rows="4">${order.notes}</textarea>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="orderStatus">OrderStatus:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="orderStatus" class="form-control" value="${order.orderStatus}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="shippingId">Shipping:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="shippingId" class="form-control" value="${order.shippingId}" />
                                                        <select name="shippingId" class="form-control">
                                                            <%Order x = (Order) pageContext.getAttribute("order"); %>
                                                            <%= Database.generateSelectOptionsFromTableAndColumn("shipping", x.getShippingId().toString(), 2)%>
                                                        </select>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="affiliateId">Affiliate:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="affiliateId" class="form-control" value="${order.affiliateId}" />
                                                        <select name="affiliateId" class="form-control">
                                                            <%= Database.generateSelectOptionsFromTableAndColumn("affiliate", x.getAffiliateId().toString(), 2)%>
                                                        </select>
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


                        <!-- BEGIN MODAL NEW Order FORM-->                    
                        <div id="myModal_new_record" class="modal fade" role="dialog" aria-hidden="true">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                        <h4 class="modal-title">Add a new Order</h4>
                                    </div>
                                    <div class="modal-body form">
                                        <!-- BEGIN FORM-->
                                        <form method="post" action="../Operations?form=order&action=1" id="create_form" class="horizontal-form">
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
                                                        <label class="col-md-2 control-label">OrderId</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <select name="orderId" class="form-control">
                                                                    <%= Database.generateSelectOptionsFromTableAndColumn("`order`", "", 2)%>
                                                               </select>                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">OrderDate</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="orderDate" class="form-control" id="mask_date2" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">ShipDate</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="shipDate" class="form-control" id="mask_date2" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">PaymentMethod</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="paymentMethod" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="100" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">PurchaseOrder</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="purchaseOrder" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="100" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">TransactionId</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="transactionId" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="45" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">AmountBilled</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="amountBilled" class="form-control" placeholder="Enter Number(ex: 2.50)" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">PaymentStatus</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="paymentStatus" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="45" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">PendingReason</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="pendingReason" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="45" />                                                            
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
                                                        <label class="col-md-2 control-label">TransactionFee</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="transactionFee" class="form-control" placeholder="Enter Number(ex: 2.50)" />                                                            
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
                                                                <input type="text" name="currencyCode" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="45" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">PayerId</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="payerId" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="45" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">SubtotalAmount</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="subtotalAmount" class="form-control" placeholder="Enter Number(ex: 2.50)" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">DiscountAmount</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="discountAmount" class="form-control" placeholder="Enter Number(ex: 2.50)" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">TaxAmount</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="taxAmount" class="form-control" placeholder="Enter Number(ex: 2.50)" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">ShippingAmount</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="shippingAmount" class="form-control" placeholder="Enter Number(ex: 2.50)" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">TotalAmount</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="totalAmount" class="form-control" placeholder="Enter Number(ex: 2.50)" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">RefundAmount</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="refundAmount" class="form-control" placeholder="Enter Number(ex: 2.50)" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">Notes</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <textarea name="notes" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="65535" rows="3"></textarea>                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">OrderStatus</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="orderStatus" class="form-control" placeholder="Enter Integer" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">ShippingId</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <select name="shippingId" class="form-control">
                                                                    <%= Database.generateSelectOptionsFromTableAndColumn("shipping", "", 2)%>
                                                               </select>                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">AffiliateId</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <select name="affiliateId" class="form-control">
                                                                    <%= Database.generateSelectOptionsFromTableAndColumn("affiliate", "", 2)%>
                                                               </select>                                                            
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

                        <!-- END MODAL NEW Order FORM-->


                        <!-- BEGIN DATA TABLE--> 
                        <div class="row">
                            <div class="col-md-12">
                                 <div class="portlet box red-flamingo">
                                    <div class="portlet-title">
                                        <div class="caption">
                                            <i class="fa fa-list-alt"></i>Order Listing
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
                                                    <label><input type="checkbox" checked data-column="1">Date</label> 
                                                    <label><input type="checkbox" checked data-column="2">ShipDate</label> 
                                                    <label><input type="checkbox" checked data-column="3">PaymentMethod</label> 
                                                    <label><input type="checkbox" checked data-column="4">Purchase</label> 
                                                    <label><input type="checkbox" checked data-column="5">TransactionId</label> 
                                                    <label><input type="checkbox" checked data-column="6">AmountBilled</label> 
                                                    <label><input type="checkbox" checked data-column="7">PaymentStatus</label> 
                                                    <label><input type="checkbox" checked data-column="8">PendingReason</label> 
                                                    <label><input type="checkbox" checked data-column="9">PaymentType</label> 
                                                    <label><input type="checkbox" checked data-column="10">TransactionFee</label> 
                                                    <label><input type="checkbox" checked data-column="11">CurrencyCode</label> 
                                                    <label><input type="checkbox" checked data-column="12">PayerId</label> 
                                                    <label><input type="checkbox" checked data-column="13">SubtotalAmount</label> 
                                                    <label><input type="checkbox" checked data-column="14">DiscountAmount</label> 
                                                    <label><input type="checkbox" checked data-column="15">TaxAmount</label> 
                                                    <label><input type="checkbox" checked data-column="16">ShippingAmount</label> 
                                                    <label><input type="checkbox" checked data-column="17">TotalAmount</label> 
                                                    <label><input type="checkbox" checked data-column="18">RefundAmount</label> 
                                                    <label><input type="checkbox" checked data-column="19">Notes</label> 
                                                    <label><input type="checkbox" checked data-column="20">Status</label> 
                                                    <label><input type="checkbox" checked data-column="21">ShippingId</label> 
                                                    <label><input type="checkbox" checked data-column="22">AffiliateId</label> 
                                                    
                                                    <label><input type="checkbox" checked data-column="23">Actions</label>
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
                                                    <th>Date</th> 
                                                    <th>ShipDate</th> 
                                                    <th>PaymentMethod</th> 
                                                    <th>Purchase</th> 
                                                    <th>TransactionId</th> 
                                                    <th>AmountBilled</th> 
                                                    <th>PaymentStatus</th> 
                                                    <th>PendingReason</th> 
                                                    <th>PaymentType</th> 
                                                    <th>TransactionFee</th> 
                                                    <th>CurrencyCode</th> 
                                                    <th>PayerId</th> 
                                                    <th>SubtotalAmount</th> 
                                                    <th>DiscountAmount</th> 
                                                    <th>TaxAmount</th> 
                                                    <th>ShippingAmount</th> 
                                                    <th>TotalAmount</th> 
                                                    <th>RefundAmount</th> 
                                                    <th>Notes</th> 
                                                    <th>Status</th> 
                                                    <th>ShippingId</th> 
                                                    <th>AffiliateId</th> 
                                                                                                        
                                                    <th>Actions</th> 
                                                </tr>                                
                                            </thead>
                                            <tbody>                                                
                                                <c:forEach var="order" items="${orderList}" >
                                                <tr>                                                    
                                                    <td>${order.orderId}</td> 
                                                    <td>${order.orderDate}</td> 
                                                    <td>${order.shipDate}</td> 
                                                    <td>${order.paymentMethod}</td> 
                                                    <td>${order.purchaseOrder}</td> 
                                                    <td>${order.transactionId}</td> 
                                                    <td>${order.amountBilled}</td> 
                                                    <td>${order.paymentStatus}</td> 
                                                    <td>${order.pendingReason}</td> 
                                                    <td>${order.paymentType}</td> 
                                                    <td>${order.transactionFee}</td> 
                                                    <td>${order.currencyCode}</td> 
                                                    <td>${order.payerId}</td> 
                                                    <td>${order.subtotalAmount}</td> 
                                                    <td>${order.discountAmount}</td> 
                                                    <td>${order.taxAmount}</td> 
                                                    <td>${order.shippingAmount}</td> 
                                                    <td>${order.totalAmount}</td> 
                                                    <td>${order.refundAmount}</td> 
                                                    <td>${order.notes}</td> 
                                                    <td>${order.orderStatus}</td> 
                                                    <td>${order.shippingId}</td> 
                                                    <td>${order.affiliateId}</td> 
                                                    
                                                    <td>
                                                        <button id="edit-item${order.orderId}" class="btn btn-sm green filter-submit margin-bottom"><span class="glyphicon glyphicon-pencil"></span></button>&nbsp;
                                                        <button id="delete-item${order.orderId}" class="btn btn-sm red filter-cancel"><span class="glyphicon glyphicon-trash"></span></button> 
                                                    </td>
                                                </tr>  
                                                <script type="text/javascript">
                                                    $("#edit-item${order.orderId}").button().click(function() {
                                                        toggleVisibility('itemBox${order.orderId}');                                                        
                                                        document.getElementById('itemBox${order.orderId}').scrollIntoView();
                                                        window.scrollBy(0,-80);
                                                    });
                                                    $("#delete-item${order.orderId}").button().click(function() {
                                                        window.location = '../Operations?form=order&action=3&id=${order.orderId}';
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
                                        
                                        <button onClick="window.location ='../Operations?form=order&action=4';" type="button" data-dismiss="modal" class="btn green">Yes</button>
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
                        orderId:    { required: true, number: true }, 
                        orderDate:    { required: true }, 
                        shipDate:    { required: true }, 
                        paymentMethod:    { required: true, minlength: 1, maxlength: 100}, 
                        purchaseOrder:    { required: true, minlength: 1, maxlength: 100}, 
                        transactionId:    { required: true, minlength: 1, maxlength: 45}, 
                        amountBilled:    { required: true, digits: true }, 
                        paymentStatus:    { required: true, minlength: 1, maxlength: 45}, 
                        pendingReason:    { required: true, minlength: 1, maxlength: 45}, 
                        paymentType:    { required: true, minlength: 1, maxlength: 15}, 
                        transactionFee:    { required: true, digits: true }, 
                        currencyCode:    { required: true, minlength: 1, maxlength: 45}, 
                        payerId:    { required: true, minlength: 1, maxlength: 45}, 
                        subtotalAmount:    { required: true, digits: true }, 
                        discountAmount:    { required: true, digits: true }, 
                        taxAmount:    { required: true, digits: true }, 
                        shippingAmount:    { required: true, digits: true }, 
                        totalAmount:    { required: true, digits: true }, 
                        refundAmount:    { required: true, digits: true }, 
                        notes:    { required: true, minlength: 1, maxlength: 65535}, 
                        orderStatus:    { required: true, number: true }, 
                        shippingId:    { required: true, number: true }, 
                        affiliateId:    { required: true, number: true } 
                        
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

