<%@page import="java.text.*"%>
<%@page import="java.util.*"%>
<%@page import="com.busy.engine.dao.*"%>
<%@page import="com.busy.engine.*"%>
<%@page import="com.busy.engine.data.*"%>
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
ArrayList<Item> itemList = new ArrayList<Item>();
if (request.getParameter("column") != null && request.getParameter("columnValue") != null)
{
    itemList = new ItemDaoImpl().findByColumn(request.getParameter("column"), request.getParameter("columnValue"), null, null);
}
else
{
    itemList = new ItemDaoImpl().findAll(null, null);
}
request.setAttribute("itemList", itemList);
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
            <link rel="stylesheet" href="../assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
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
        <% request.setAttribute("subCategory", "Item"); %>
        <%@include file="index_sidebar.jsp"%>


            <!-- BEGIN CONTENT -->
            <div class="page-content-wrapper">
    
                    <div class="page-content">

                        <!-- BEGIN PAGE HEADER-->
                        <div class="row">
                            <div class="col-md-12">
                                <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                                <h3 class="page-title"> Item </h3>
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
                                        <a href="#">Item</a>
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
                                        <form method="post" action="ItemUI.jsp" class="form-horizontal" role="form">
                                        	<div class="form-body">
                                                <div class="form-group">
                                                    <div class="col-md-4">
                                                        <select name="column" class="form-control">
                                                            <option value="ItemId" ${param.column == 'ItemId' ? "selected" : "" } >ItemId</option>                                                            
                                                           <option value="ItemName" ${param.column == 'ItemName' ? "selected" : "" } >ItemName</option>                                                            
                                                           <option value="Description" ${param.column == 'Description' ? "selected" : "" } >Description</option>                                                            
                                                           <option value="ListPrice" ${param.column == 'ListPrice' ? "selected" : "" } >ListPrice</option>                                                            
                                                           <option value="Price" ${param.column == 'Price' ? "selected" : "" } >Price</option>                                                            
                                                           <option value="ShortDescription" ${param.column == 'ShortDescription' ? "selected" : "" } >ShortDescription</option>                                                            
                                                           <option value="Adjustment" ${param.column == 'Adjustment' ? "selected" : "" } >Adjustment</option>                                                            
                                                           <option value="Sku" ${param.column == 'Sku' ? "selected" : "" } >Sku</option>                                                            
                                                           <option value="RatingSum" ${param.column == 'RatingSum' ? "selected" : "" } >RatingSum</option>                                                            
                                                           <option value="VoteCount" ${param.column == 'VoteCount' ? "selected" : "" } >VoteCount</option>                                                            
                                                           <option value="Rank" ${param.column == 'Rank' ? "selected" : "" } >Rank</option>                                                            
                                                           <option value="ItemStatus" ${param.column == 'ItemStatus' ? "selected" : "" } >ItemStatus</option>                                                            
                                                           <option value="Locale" ${param.column == 'Locale' ? "selected" : "" } >Locale</option>                                                            
                                                           <option value="ItemTypeId" ${param.column == 'ItemTypeId' ? "selected" : "" } >ItemTypeId</option>                                                            
                                                           <option value="ItemBrandId" ${param.column == 'ItemBrandId' ? "selected" : "" } >ItemBrandId</option>                                                            
                                                           <option value="MetaTagId" ${param.column == 'MetaTagId' ? "selected" : "" } >MetaTagId</option>                                                            
                                                           <option value="TemplateId" ${param.column == 'TemplateId' ? "selected" : "" } >TemplateId</option>                                                            
                                                           <option value="VendorId" ${param.column == 'VendorId' ? "selected" : "" } >VendorId</option>                                                            
                                                                                                                                                                                  
                                                        </select> 
                                                    </div>                                                         
                                                    <div class="col-md-5">
                                                        <input type="text" name="columnValue" class="form-control"/>
                                                    </div>
                                                    <div class="col-md-3">
	                                                <button type="submit" class="btn grey-silver">Filter</button>
                                                        <button type="button" class="btn grey-cascade" style="float:right" onclick="javascript:window.location = 'ItemUI.jsp';"><i class="fa fa-refresh"></i></button>
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
                                  
                        <c:forEach var="item" items="${itemList}" >
                        <div class="row" id="itemBox${item.itemId}" style="display:${param.id == null ? "none" : item.itemId==param.id ? "block" : "none"}">                      
                            <div class="col-md-12">
                                <div class="portlet box green-seagreen">
                                    <div class="portlet-title">
                                        <div class="caption">Record Details</div>
                                        <div class="actions">                                                 
                                            <div class="btn-group">                                
                                                <a href="javascript:toggleVisibility('itemBox${item.itemId}');" class="btn btn-default"><i class="fa fa-times"></i>&nbsp;Close</a> 
                                            </div>
                                        </div>
                                    </div>
                                    <div class="portlet-body">	
                                        <div class="portlet-body form">
                                            <form class="form-horizontal" name="edit" action="../Operations?form=item&action=2" method="post">

                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="itemId">Item:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="itemId" class="form-control" value="${item.itemId}" />

                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="itemName">ItemName:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="itemName" class="form-control maxlength-handler" maxlength="255" value="${item.itemName}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="description">Description:</label>
                                                    <div  class="col-md-10">
                                                        <textarea name="description" class="ckeditor form-control" rows="4">${item.description}</textarea>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="listPrice">ListPrice:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="listPrice" class="form-control" value="${item.listPrice}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="price">Price:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="price" class="form-control" value="${item.price}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="shortDescription">ShortDescription:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="shortDescription" class="form-control maxlength-handler" maxlength="255" value="${item.shortDescription}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="adjustment">Adjustment:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="adjustment" class="form-control" value="${item.adjustment}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="sku">Sku:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="sku" class="form-control maxlength-handler" maxlength="30" value="${item.sku}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="ratingSum">RatingSum:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="ratingSum" class="form-control" value="${item.ratingSum}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="voteCount">VoteCount:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="voteCount" class="form-control" value="${item.voteCount}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="rank">Rank:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="rank" class="form-control" value="${item.rank}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="itemStatus">ItemStatus:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="itemStatus" class="form-control" value="${item.itemStatus}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="locale">Locale:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="locale" class="form-control maxlength-handler" maxlength="10" value="${item.locale}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="itemTypeId">ItemType:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="itemTypeId" class="form-control" value="${item.itemTypeId}" />
                                                        <select name="itemTypeId" class="form-control">
                                                            <%Item x = (Item) pageContext.getAttribute("item"); %>
                                                            <%= Database.generateSelectOptionsFromTableAndColumn("item_type", x.getItemTypeId().toString(), 2)%>
                                                        </select>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="itemBrandId">ItemBrand:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="itemBrandId" class="form-control" value="${item.itemBrandId}" />
                                                        <select name="itemBrandId" class="form-control">
                                                            <%= Database.generateSelectOptionsFromTableAndColumn("item_brand", x.getItemBrandId().toString(), 2)%>
                                                        </select>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="metaTagId">MetaTag:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="metaTagId" class="form-control" value="${item.metaTagId}" />
                                                        <select name="metaTagId" class="form-control">
                                                            <%= Database.generateSelectOptionsFromTableAndColumn("meta_tag", x.getMetaTagId().toString(), 2)%>
                                                        </select>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="templateId">Template:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="templateId" class="form-control" value="${item.templateId}" />
                                                        <select name="templateId" class="form-control">
                                                            <%= Database.generateSelectOptionsFromTableAndColumn("template", x.getTemplateId().toString(), 2)%>
                                                        </select>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="vendorId">Vendor:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="vendorId" class="form-control" value="${item.vendorId}" />
                                                        <select name="vendorId" class="form-control">
                                                            <%= Database.generateSelectOptionsFromTableAndColumn("vendor", x.getVendorId().toString(), 2)%>
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


                        <!-- BEGIN MODAL NEW Item FORM-->                    
                        <div id="myModal_new_record" class="modal fade" role="dialog" aria-hidden="true">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                        <h4 class="modal-title">Add a new Item</h4>
                                    </div>
                                    <div class="modal-body form">
                                        <!-- BEGIN FORM-->
                                        <form method="post" action="../Operations?form=item&action=1" id="create_form" class="horizontal-form">
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
                                                        <label class="col-md-2 control-label">ItemName</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="itemName" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="255" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">Description</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <textarea name="description" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="65535" rows="3"></textarea>                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">ListPrice</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="listPrice" class="form-control" placeholder="Enter Number(ex: 2.50)" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">Price</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="price" class="form-control" placeholder="Enter Number(ex: 2.50)" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">ShortDescription</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="shortDescription" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="255" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">Adjustment</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="adjustment" class="form-control" placeholder="Enter Integer" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">Sku</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="sku" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="30" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">RatingSum</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="ratingSum" class="form-control" placeholder="Enter Integer" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">VoteCount</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="voteCount" class="form-control" placeholder="Enter Integer" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">Rank</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="rank" class="form-control" placeholder="Enter Integer" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">ItemStatus</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="itemStatus" class="form-control" placeholder="Enter Integer" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">Locale</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="locale" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="10" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">ItemTypeId</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <select name="itemTypeId" class="form-control">
                                                                    <%= Database.generateSelectOptionsFromTableAndColumn("item_type", "", 2)%>
                                                               </select>                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">ItemBrandId</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <select name="itemBrandId" class="form-control">
                                                                    <%= Database.generateSelectOptionsFromTableAndColumn("item_brand", "", 2)%>
                                                               </select>                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">MetaTagId</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <select name="metaTagId" class="form-control">
                                                                    <%= Database.generateSelectOptionsFromTableAndColumn("meta_tag", "", 2)%>
                                                               </select>                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">TemplateId</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <select name="templateId" class="form-control">
                                                                    <%= Database.generateSelectOptionsFromTableAndColumn("template", "", 2)%>
                                                               </select>                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">VendorId</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <select name="vendorId" class="form-control">
                                                                    <%= Database.generateSelectOptionsFromTableAndColumn("vendor", "", 2)%>
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

                        <!-- END MODAL NEW Item FORM-->


                        <!-- BEGIN DATA TABLE--> 
                        <div class="row">
                            <div class="col-md-12">
                                 <div class="portlet box red-flamingo">
                                    <div class="portlet-title">
                                        <div class="caption">
                                            <i class="fa fa-list-alt"></i>Item Listing
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
                                                    <label><input type="checkbox" checked data-column="1">Name</label> 
                                                    <label><input type="checkbox" checked data-column="2">Description</label> 
                                                    <label><input type="checkbox" checked data-column="3">ListPrice</label> 
                                                    <label><input type="checkbox" checked data-column="4">Price</label> 
                                                    <label><input type="checkbox" checked data-column="5">ShortDescription</label> 
                                                    <label><input type="checkbox" checked data-column="6">Adjustment</label> 
                                                    <label><input type="checkbox" checked data-column="7">Sku</label> 
                                                    <label><input type="checkbox" checked data-column="8">RatingSum</label> 
                                                    <label><input type="checkbox" checked data-column="9">VoteCount</label> 
                                                    <label><input type="checkbox" checked data-column="10">Rank</label> 
                                                    <label><input type="checkbox" checked data-column="11">Status</label> 
                                                    <label><input type="checkbox" checked data-column="12">Locale</label> 
                                                    <label><input type="checkbox" checked data-column="13">TypeId</label> 
                                                    <label><input type="checkbox" checked data-column="14">BrandId</label> 
                                                    <label><input type="checkbox" checked data-column="15">MetaTagId</label> 
                                                    <label><input type="checkbox" checked data-column="16">TemplateId</label> 
                                                    <label><input type="checkbox" checked data-column="17">VendorId</label> 
                                                    
                                                    <label><input type="checkbox" checked data-column="18">Actions</label>
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
                                                    <th>Name</th> 
                                                    <th>Description</th> 
                                                    <th>ListPrice</th> 
                                                    <th>Price</th> 
                                                    <th>ShortDescription</th> 
                                                    <th>Adjustment</th> 
                                                    <th>Sku</th> 
                                                    <th>RatingSum</th> 
                                                    <th>VoteCount</th> 
                                                    <th>Rank</th> 
                                                    <th>Status</th> 
                                                    <th>Locale</th> 
                                                    <th>TypeId</th> 
                                                    <th>BrandId</th> 
                                                    <th>MetaTagId</th> 
                                                    <th>TemplateId</th> 
                                                    <th>VendorId</th> 
                                                                                                        
                                                    <th>Actions</th> 
                                                </tr>                                
                                            </thead>
                                            <tbody>                                                
                                                <c:forEach var="item" items="${itemList}" >
                                                <tr>                                                    
                                                    <td>${item.itemId}</td> 
                                                    <td>${item.itemName}</td> 
                                                    <td>${item.description}</td> 
                                                    <td>${item.listPrice}</td> 
                                                    <td>${item.price}</td> 
                                                    <td>${item.shortDescription}</td> 
                                                    <td>${item.adjustment}</td> 
                                                    <td>${item.sku}</td> 
                                                    <td>${item.ratingSum}</td> 
                                                    <td>${item.voteCount}</td> 
                                                    <td>${item.rank}</td> 
                                                    <td>${item.itemStatus}</td> 
                                                    <td>${item.locale}</td> 
                                                    <td>${item.itemTypeId}</td> 
                                                    <td>${item.itemBrandId}</td> 
                                                    <td>${item.metaTagId}</td> 
                                                    <td>${item.templateId}</td> 
                                                    <td>${item.vendorId}</td> 
                                                    
                                                    <td>
                                                        <button id="edit-item${item.itemId}" class="btn btn-sm green filter-submit margin-bottom"><span class="glyphicon glyphicon-pencil"></span></button>&nbsp;
                                                        <button id="delete-item${item.itemId}" class="btn btn-sm red filter-cancel"><span class="glyphicon glyphicon-trash"></span></button> 
                                                    </td>
                                                </tr>  
                                                <script type="text/javascript">
                                                    $("#edit-item${item.itemId}").button().click(function() {
                                                        toggleVisibility('itemBox${item.itemId}');                                                        
                                                        document.getElementById('itemBox${item.itemId}').scrollIntoView();
                                                        window.scrollBy(0,-80);
                                                    });
                                                    $("#delete-item${item.itemId}").button().click(function() {
                                                        window.location = '../Operations?form=item&action=3&id=${item.itemId}';
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
                                        
                                        <button onClick="window.location ='../Operations?form=item&action=4';" type="button" data-dismiss="modal" class="btn green">Yes</button>
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
                        itemId:    { required: true, number: true }, 
                        itemName:    { required: true, minlength: 1, maxlength: 255}, 
                        description:    { required: true, minlength: 1, maxlength: 65535}, 
                        listPrice:    { required: true, digits: true }, 
                        price:    { required: true, digits: true }, 
                        shortDescription:    { required: true, minlength: 1, maxlength: 255}, 
                        adjustment:    { required: true, number: true }, 
                        sku:    { required: true, minlength: 1, maxlength: 30}, 
                        ratingSum:    { required: true, number: true }, 
                        voteCount:    { required: true, number: true }, 
                        rank:    { required: true, number: true }, 
                        itemStatus:    { required: true, number: true }, 
                        locale:    { required: true, minlength: 1, maxlength: 10}, 
                        itemTypeId:    { required: true, number: true }, 
                        itemBrandId:    { required: true, number: true }, 
                        metaTagId:    { required: true, number: true }, 
                        templateId:    { required: true, number: true }, 
                        vendorId:    { required: true, number: true } 
                        
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

