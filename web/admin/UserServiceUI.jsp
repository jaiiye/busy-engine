<%@page import="java.text.*"%>
<%@page import="java.util.*"%>
<%@page import="com.busy.dao.*"%>
<%@page import="com.transitionsoft.*"%>
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
ArrayList<UserService> user_serviceList = new ArrayList<UserService>();
if (request.getParameter("column") != null && request.getParameter("columnValue") != null)
{
    user_serviceList = UserService.getAllUserServiceByColumn(request.getParameter("column"), request.getParameter("columnValue"));
}
else
{
    user_serviceList = UserService.getAllUserService();
}
request.setAttribute("user_serviceList", user_serviceList);
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

        <% request.setAttribute("category", "E-Commerce"); %>
        <% request.setAttribute("subCategory", "UserService"); %>
        <%@include file="index_sidebar.jsp"%>


            <!-- BEGIN CONTENT -->
            <div class="page-content-wrapper">
    
                    <div class="page-content">

                        <!-- BEGIN PAGE HEADER-->
                        <div class="row">
                            <div class="col-md-12">
                                <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                                <h3 class="page-title"> UserService </h3>
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
                                        <a href="#">UserService</a>
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
                                        <form method="post" action="UserServiceUI.jsp" class="form-horizontal" role="form">
                                        	<div class="form-body">
                                                <div class="form-group">
                                                    <div class="col-md-4">
                                                        <select name="column" class="form-control">
                                                            <option value="UserServiceId" ${param.column == 'UserServiceId' ? "selected" : "" } >UserServiceId</option>                                                            
                                                           <option value="ServiceId" ${param.column == 'ServiceId' ? "selected" : "" } >ServiceId</option>                                                            
                                                           <option value="UserId" ${param.column == 'UserId' ? "selected" : "" } >UserId</option>                                                            
                                                           <option value="BlogId" ${param.column == 'BlogId' ? "selected" : "" } >BlogId</option>                                                            
                                                           <option value="StartDate" ${param.column == 'StartDate' ? "selected" : "" } >StartDate</option>                                                            
                                                           <option value="EndDate" ${param.column == 'EndDate' ? "selected" : "" } >EndDate</option>                                                            
                                                           <option value="Details" ${param.column == 'Details' ? "selected" : "" } >Details</option>                                                            
                                                           <option value="ContractUrl" ${param.column == 'ContractUrl' ? "selected" : "" } >ContractUrl</option>                                                            
                                                           <option value="DeliverableUrl" ${param.column == 'DeliverableUrl' ? "selected" : "" } >DeliverableUrl</option>                                                            
                                                           <option value="DepositAmount" ${param.column == 'DepositAmount' ? "selected" : "" } >DepositAmount</option>                                                            
                                                                                                                                                                                  
                                                        </select> 
                                                    </div>                                                         
                                                    <div class="col-md-5">
                                                        <input type="text" name="columnValue" class="form-control"/>
                                                    </div>
                                                    <div class="col-md-3">
	                                                <button type="submit" class="btn grey-silver">Filter</button>
                                                        <button type="button" class="btn grey-cascade" style="float:right" onclick="javascript:window.location = 'UserServiceUI.jsp';"><i class="fa fa-refresh"></i></button>
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
                                  
                        <c:forEach var="user_service" items="${user_serviceList}" >
                        <div class="row" id="itemBox${user_service.userServiceId}" style="display:${param.id == null ? "none" : user_service.userServiceId==param.id ? "block" : "none"}">                      
                            <div class="col-md-12">
                                <div class="portlet box green-seagreen">
                                    <div class="portlet-title">
                                        <div class="caption">Record Details</div>
                                        <div class="actions">                                                 
                                            <div class="btn-group">                                
                                                <a href="javascript:toggleVisibility('itemBox${user_service.userServiceId}');" class="btn btn-default"><i class="fa fa-times"></i>&nbsp;Close</a> 
                                            </div>
                                        </div>
                                    </div>
                                    <div class="portlet-body">	
                                        <div class="portlet-body form">
                                            <form class="form-horizontal" name="edit" action="../Operations?form=user_service&action=2" method="post">

                                               <input type="hidden" name="userServiceId" value="${user_service.userServiceId}" />
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="serviceId">Service:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="serviceId" class="form-control" value="${user_service.serviceId}" />
                                                        <select name="serviceId" class="form-control">
                                                            <%UserService x = (UserService) pageContext.getAttribute("user_service"); %>
                                                            <%= Database.generateSelectOptionsFromTableAndColumn("service", x.getServiceId().toString(), 2)%>
                                                        </select>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="userId">User:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="userId" class="form-control" value="${user_service.userId}" />
                                                        <select name="userId" class="form-control">
                                                            <%= Database.generateSelectOptionsFromTableAndColumn("user", x.getUserId().toString(), 2)%>
                                                        </select>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="blogId">Blog:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="blogId" class="form-control" value="${user_service.blogId}" />
                                                        <select name="blogId" class="form-control">
                                                            <%= Database.generateSelectOptionsFromTableAndColumn("table_name:Blog", x.getBlogId().toString(), 2)%>
                                                        </select>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="startDate">StartDate:</label>
                                                    <div  class="col-md-10">
                                                        <div class="input-group date form_datetime" data-date="2012-12-21T15:25:00Z">        <input type="text" name="startDate" value="${user_service.startDate}" class="form-control">        <span class="input-group-btn">                <button class="btn default date-reset" type="button"><i class="fa fa-times"></i></button>        </span>        <span class="input-group-btn">                <button class="btn default date-set" type="button"><i class="fa fa-calendar"></i></button>        </span></div>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="endDate">EndDate:</label>
                                                    <div  class="col-md-10">
                                                        <div class="input-group date form_datetime" data-date="2012-12-21T15:25:00Z">        <input type="text" name="endDate" value="${user_service.endDate}" class="form-control">        <span class="input-group-btn">                <button class="btn default date-reset" type="button"><i class="fa fa-times"></i></button>        </span>        <span class="input-group-btn">                <button class="btn default date-set" type="button"><i class="fa fa-calendar"></i></button>        </span></div>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="details">Details:</label>
                                                    <div  class="col-md-10">
                                                        <textarea name="details" class="ckeditor form-control" rows="4">${user_service.details}</textarea>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="contractUrl">ContractUrl:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="contractUrl" class="form-control maxlength-handler" maxlength="255" value="${user_service.contractUrl}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="deliverableUrl">DeliverableUrl:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="deliverableUrl" class="form-control maxlength-handler" maxlength="255" value="${user_service.deliverableUrl}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="depositAmount">DepositAmount:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="depositAmount" class="form-control" value="${user_service.depositAmount}" />
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


                        <!-- BEGIN MODAL NEW UserService FORM-->                    
                        <div id="myModal_new_record" class="modal fade" role="dialog" aria-hidden="true">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                        <h4 class="modal-title">Add a new UserService</h4>
                                    </div>
                                    <div class="modal-body form">
                                        <!-- BEGIN FORM-->
                                        <form method="post" action="../Operations?form=user_service&action=1" id="create_form" class="horizontal-form">
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
                                                        <label class="col-md-2 control-label">ServiceId</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <select name="serviceId" class="form-control">
                                                                    <%= Database.generateSelectOptionsFromTableAndColumn("table_name:Service", "", 2)%>
                                                               </select>                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">UserId</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <select name="userId" class="form-control">
                                                                    <%= Database.generateSelectOptionsFromTableAndColumn("table_name:User", "", 2)%>
                                                               </select>                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">BlogId</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <select name="blogId" class="form-control">
                                                                    <%= Database.generateSelectOptionsFromTableAndColumn("table_name:Blog", "", 2)%>
                                                               </select>                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">StartDate</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="startDate" class="form-control" id="mask_date2" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">EndDate</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="endDate" class="form-control" id="mask_date2" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">Details</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <textarea name="details" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="65535" rows="3"></textarea>                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">ContractUrl</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="contractUrl" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="255" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">DeliverableUrl</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="deliverableUrl" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="255" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">DepositAmount</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="depositAmount" class="form-control" placeholder="Enter Number(ex: 2.50)" />                                                            
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

                        <!-- END MODAL NEW UserService FORM-->


                        <!-- BEGIN DATA TABLE--> 
                        <div class="row">
                            <div class="col-md-12">
                                 <div class="portlet box red-flamingo">
                                    <div class="portlet-title">
                                        <div class="caption">
                                            <i class="fa fa-list-alt"></i>UserService Listing
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
                                                    <label><input type="checkbox" checked data-column="1">ServiceId</label> 
                                                    <label><input type="checkbox" checked data-column="2">UserId</label> 
                                                    <label><input type="checkbox" checked data-column="3">BlogId</label> 
                                                    <label><input type="checkbox" checked data-column="4">StartDate</label> 
                                                    <label><input type="checkbox" checked data-column="5">EndDate</label> 
                                                    <label><input type="checkbox" checked data-column="6">Details</label> 
                                                    <label><input type="checkbox" checked data-column="7">ContractUrl</label> 
                                                    <label><input type="checkbox" checked data-column="8">DeliverableUrl</label> 
                                                    <label><input type="checkbox" checked data-column="9">DepositAmount</label> 
                                                    
                                                    <label><input type="checkbox" checked data-column="10">Actions</label>
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
                                                    <th>ServiceId</th> 
                                                    <th>UserId</th> 
                                                    <th>BlogId</th> 
                                                    <th>StartDate</th> 
                                                    <th>EndDate</th> 
                                                    <th>Details</th> 
                                                    <th>ContractUrl</th> 
                                                    <th>DeliverableUrl</th> 
                                                    <th>DepositAmount</th> 
                                                                                                        
                                                    <th>Actions</th> 
                                                </tr>                                
                                            </thead>
                                            <tbody>                                                
                                                <c:forEach var="user_service" items="${user_serviceList}" >
                                                <tr>                                                    
                                                    <td>${user_service.userServiceId}</td> 
                                                    <td>${user_service.serviceId}</td> 
                                                    <td>${user_service.userId}</td> 
                                                    <td>${user_service.blogId}</td> 
                                                    <td>${user_service.startDate}</td> 
                                                    <td>${user_service.endDate}</td> 
                                                    <td>${user_service.details}</td> 
                                                    <td>${user_service.contractUrl}</td> 
                                                    <td>${user_service.deliverableUrl}</td> 
                                                    <td>${user_service.depositAmount}</td> 
                                                    
                                                    <td>
                                                        <button id="edit-item${user_service.userServiceId}" class="btn btn-sm green filter-submit margin-bottom"><span class="glyphicon glyphicon-pencil"></span></button>&nbsp;
                                                        <button id="delete-item${user_service.userServiceId}" class="btn btn-sm red filter-cancel"><span class="glyphicon glyphicon-trash"></span></button> 
                                                    </td>
                                                </tr>  
                                                <script type="text/javascript">
                                                    $("#edit-item${user_service.userServiceId}").button().click(function() {
                                                        toggleVisibility('itemBox${user_service.userServiceId}');                                                        
                                                        document.getElementById('itemBox${user_service.userServiceId}').scrollIntoView();
                                                        window.scrollBy(0,-80);
                                                    });
                                                    $("#delete-item${user_service.userServiceId}").button().click(function() {
                                                        window.location = '../Operations?form=user_service&action=3&id=${user_service.userServiceId}';
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
                                        
                                        <button onClick="window.location ='../Operations?form=user_service&action=4';" type="button" data-dismiss="modal" class="btn green">Yes</button>
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


                var table = $('#sample_2');
                /* Table tools samples: https://www.datatables.net/release-datatables/extras/TableTools/ */
                /* Set tabletools buttons and button container */
                $.extend(true, $.fn.DataTable.TableTools.classes, {
                    "container": "btn-group tabletools-dropdown-on-portlet",
                    "buttons": {
                        "normal": "btn btn-sm default",
                        "disabled": "btn btn-sm default disabled"
                    },
                    "collection": {
                        "container": "DTTT_dropdown dropdown-menu tabletools-dropdown-menu"
                    }
                });


                if( /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) ) 
                {
                    var tableToolsButtons = [{
                        "sExtends": "print",
                        "sButtonText": "Print",
                        "sInfo": 'Print View!'
                    }];
                }
                else 
                {
                    var tableToolsButtons = [{
                        "sExtends": "copy",
                        "sButtonText": "Copy"
                    }, {
                        "sExtends": "print",
                        "sButtonText": "Print",
                        "sInfo": 'Please press "CTRL+P" to print or "ESC" to quit'
                    }, {
                        "sExtends": "pdf",
                        "sButtonText": "PDF"
                    }, {
                        "sExtends": "xls",
                        "sButtonText": "Excel"
                    }];
                }

                var oTable = table.dataTable({					
                    "aaSorting": [[0, 'asc']],
                    "aLengthMenu": [ [10, 15, 25, 50, 100, 250, -1],	[10, 15, 25, 50, 100, 250, "All"] ],						
                    "iDisplayLength": 10,   // set the initial value               
                    "sPaginationType": "bootstrap_full_number",
                    "sDom": "<'row'<'col-md-3 col-sm-12'l><'col-md-4 col-sm-12'Tf><'col-md-5 col-sm-12'p>><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", 			
                    // horizobtal scrollable datatable
                    "oTableTools": {
                        "sSwfPath": "../assets/global/plugins/data-tables/tabletools/swf/copy_csv_xls_pdf.swf",
                        "aButtons": tableToolsButtons
                    }
                });

                var tableWrapper = $('#sample_1_wrapper'); // datatable creates the table wrapper by adding with id {your_table_jd}_wrapper

                jQuery('.dataTables_filter input', tableWrapper).addClass("form-control input-small input-inline"); // modify table search input
                jQuery('.dataTables_length select', tableWrapper).addClass("form-control input-small"); // modify table per page dropdown
                jQuery('.dataTables_length select', tableWrapper).select2(); // initialize select2 dropdown

                $('#sample_2_column_toggler input[type="checkbox"]').change(function(){
                    /* Get the DataTables object again - this is not a recreation, just a get of the object */
                    var iCol = parseInt($(this).attr("data-column"));
                    var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
                    oTable.fnSetColumnVis(iCol, (bVis ? false : true));
                });

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
                        userServiceId:    { required: true, number: true }, 
                        serviceId:    { required: true, number: true }, 
                        userId:    { required: true, number: true }, 
                        blogId:    { required: true, number: true }, 
                        startDate:    { required: true }, 
                        endDate:    { required: true }, 
                        details:    { required: true, minlength: 1, maxlength: 65535}, 
                        contractUrl:    { required: true, minlength: 1, maxlength: 255}, 
                        deliverableUrl:    { required: true, minlength: 1, maxlength: 255}, 
                        depositAmount:    { required: true, digits: true } 
                        
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

