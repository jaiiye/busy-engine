<%@page import="java.text.*"%>
<%@page import="java.util.*"%>
<%@page import="com.busy.dao.*"%>
<%@page import="com.transitionsoft.*"%>
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
ArrayList<User> userList = new ArrayList<User>();
if (request.getParameter("column") != null && request.getParameter("columnValue") != null)
{
    userList = User.getAllUserByColumn(request.getParameter("column"), request.getParameter("columnValue"));
}
else
{
    userList = User.getAllUser();
}
request.setAttribute("userList", userList);
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

        <% request.setAttribute("category", "users"); %>
        <% request.setAttribute("subCategory", "users"); %>
        <%@include file="index_sidebar.jsp"%>


            <!-- BEGIN CONTENT -->
            <div class="page-content-wrapper">
    
                    <div class="page-content">

                        <!-- BEGIN PAGE HEADER-->
                        <div class="row">
                            <div class="col-md-12">
                                <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                                <h3 class="page-title">User Manager</h3>
                                <ul class="page-breadcrumb breadcrumb">                                
                                    <li>
                                        <i class="fa fa-home"></i><a href="index.jsp">Home</a>
                                        <i class="fa fa-angle-right"></i>
                                    </li>
                                    <li>
                                        <a href="#"> Users </a>
                                        <i class="fa fa-angle-right"></i>
                                    </li>
                                    <li>
                                        <a href="#">User Manager</a>
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
                                        <form method="post" action="UserUI.jsp" class="form-horizontal" role="form">
                                        	<div class="form-body">
                                                <div class="form-group">
                                                    <div class="col-md-4">
                                                        <select name="column" class="form-control">
                                                            <option value="UserId" ${param.column == 'UserId' ? "selected" : "" } >UserId</option>                                                            
                                                           <option value="BrandId" ${param.column == 'BrandId' ? "selected" : "" } >BrandId</option>                                                            
                                                           <option value="TypeId" ${param.column == 'TypeId' ? "selected" : "" } >TypeId</option>                                                            
                                                           <option value="UserName" ${param.column == 'UserName' ? "selected" : "" } >UserName</option>                                                            
                                                           <option value="UserPassword" ${param.column == 'UserPassword' ? "selected" : "" } >UserPassword</option>                                                            
                                                           <option value="UserSecurityQuestion" ${param.column == 'UserSecurityQuestion' ? "selected" : "" } >UserSecurityQuestion</option>                                                            
                                                           <option value="UserSecurityAnswer" ${param.column == 'UserSecurityAnswer' ? "selected" : "" } >UserSecurityAnswer</option>                                                            
                                                           <option value="UserImgURL" ${param.column == 'UserImgURL' ? "selected" : "" } >UserImgURL</option>                                                            
                                                           <option value="UserEmail" ${param.column == 'UserEmail' ? "selected" : "" } >UserEmail</option>                                                            
                                                           <option value="UserEmailConfirmed" ${param.column == 'UserEmailConfirmed' ? "selected" : "" } >UserEmailConfirmed</option>                                                            
                                                           <option value="UserWebUrl" ${param.column == 'UserWebUrl' ? "selected" : "" } >UserWebUrl</option>                                                            
                                                           <option value="ContactId" ${param.column == 'ContactId' ? "selected" : "" } >ContactId</option>                                                            
                                                           <option value="AddressId" ${param.column == 'AddressId' ? "selected" : "" } >AddressId</option>                                                            
                                                                                                                                                                                  
                                                        </select> 
                                                    </div>                                                         
                                                    <div class="col-md-5">
                                                        <input type="text" name="columnValue" class="form-control"/>
                                                    </div>
                                                    <div class="col-md-3">
	                                                <button type="submit" class="btn grey-silver">Filter</button>
                                                        <button type="button" class="btn grey-cascade" style="float:right" onclick="javascript:window.location = 'UserUI.jsp';"><i class="fa fa-refresh"></i></button>
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
                                  
                        <c:forEach var="user" items="${userList}" >
                        <div class="row" id="itemBox${user.userId}" style="display:${param.id == null ? "none" : user.userId==param.id ? "block" : "none"}">                      
                            <div class="col-md-12">
                                <div class="portlet box green-seagreen">
                                    <div class="portlet-title">
                                        <div class="caption">Record Details</div>
                                        <div class="actions">                                                 
                                            <div class="btn-group">                                
                                                <a href="javascript:toggleVisibility('itemBox${user.userId}');" class="btn btn-default"><i class="fa fa-times"></i>&nbsp;Close</a> 
                                            </div>
                                        </div>
                                    </div>
                                    <div class="portlet-body">	
                                        <div class="portlet-body form">
                                            <form class="form-horizontal" name="edit" action="../Operations?form=user&action=2" method="post">

                                                <input type="hidden" name="userId" value="${user.userId}" />
                                                <input type="hidden" name="contactId" value="${user.contactId}" />
                                                <input type="hidden" name="addressId" value="${user.addressId}" />
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="brandId">Brand:</label>
                                                    <div  class="col-md-10">
                                                        <select name="brandId" class="form-control">
                                                            <%User x = (User) pageContext.getAttribute("user"); %>
                                                            <%= Database.generateSelectOptionsFromTableAndColumn("item_brand", x.getBrandId().toString(), 2)%>
                                                        </select>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="typeId">Type:</label>
                                                    <div  class="col-md-10">
                                                        <select name="typeId" class="form-control">
                                                            <%= Database.generateSelectOptionsFromTableAndColumn("type", x.getTypeId().toString(), 2)%>
                                                        </select>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="userName">User Name:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="userName" class="form-control maxlength-handler" maxlength="30" value="${user.userName}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="userPassword">Password:</label>
                                                    <div  class="col-md-10">
                                                        <input type="password" name="userPassword" class="form-control maxlength-handler" maxlength="15" value="${user.userPassword}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="userSecurityQuestion">Security Question:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="userSecurityQuestion" class="form-control maxlength-handler" maxlength="100" value="${user.userSecurityQuestion}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="userSecurityAnswer">Security Answer:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="userSecurityAnswer" class="form-control maxlength-handler" maxlength="30" value="${user.userSecurityAnswer}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="userImgURL">Image URL:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="userImgURL" class="form-control maxlength-handler" maxlength="255" value="${user.userImgURL}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="userEmail">User Email:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="userEmail" class="form-control maxlength-handler" maxlength="255" value="${user.userEmail}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="userEmailConfirmed">Email Confirmed:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="userEmailConfirmed" class="form-control" value="${user.userEmailConfirmed}" />
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="userWebUrl">Web Url:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="userWebUrl" class="form-control maxlength-handler" maxlength="255" value="${user.userWebUrl}" />
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


                        <!-- BEGIN MODAL NEW User FORM-->                    
                        <div id="myModal_new_record" class="modal fade" role="dialog" aria-hidden="true">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                        <h4 class="modal-title">Add a new User</h4>
                                    </div>
                                    <div class="modal-body form">
                                        <!-- BEGIN FORM-->
                                        <form method="post" action="../Operations?form=user&action=1" id="create_form" class="horizontal-form">
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
                                                        <label class="col-md-2 control-label">Brand</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <select name="brandId" class="form-control">
                                                                    <%= Database.generateSelectOptionsFromTableAndColumn("item_brand", "", 2)%>
                                                               </select>                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">Type</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <select name="typeId" class="form-control">
                                                                    <%= Database.generateSelectOptionsFromTableAndColumn("type", "", 2)%>
                                                               </select>                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">UserName</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="userName" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="30" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">Password</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="password" name="userPassword" class="form-control maxlength-handler" maxlength="15" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">Security Question</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <select name="userSecurityQuestion" class="form-control">
                                                                    <option value=""></option>
                                                                    <option value="What was your childhood nickname?">What was your childhood nickname?</option>
                                                                    <option value="What is the name of your favorite childhood friend?">What's the name of your favorite childhood friend?</option>
                                                                    <option value="What street did you live on in third grade?">What street did you live on in third grade?</option>
                                                                    <option value="In what city did you meet your spouse?">In what city did you meet your spouse?</option>
                                                                    <option value="What school did you attend for 6th grade?">What school did you attend for 6th grade?</option>
                                                                    <option value="What was the name of your first stuffed animal?">What was the name of your first stuffed animal?</option>
                                                                    <option value="In what city or town did your parents meet?">In what city or town did your parents meet?</option>
                                                                    <option value="Where were you when you had your first kiss?">Where were you when you had your first kiss?</option>
                                                                    <option value="What was the last name of your 3rd grade teacher?">What was the last name of your 3rd grade teacher?</option>
                                                                    <option value="In what city was your first job?">In what city was your first job?</option>
                                                                    <option value="What is the name of the place your wedding was held?">What is the name of the place your wedding was held?</option>
                                                                </select>                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">Security Answer</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="userSecurityAnswer" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="30" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">UserImgURL</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="userImgURL" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="255" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">UserEmail</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="userEmail" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="255" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">UserEmailConfirmed</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="userEmailConfirmed" class="form-control" placeholder="Enter Integer" />                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">UserWebUrl</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="userWebUrl" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="255" />                                                            
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

                        <!-- END MODAL NEW User FORM-->


                        <!-- BEGIN DATA TABLE--> 
                        <div class="row">
                            <div class="col-md-12">
                                 <div class="portlet box red-flamingo">
                                    <div class="portlet-title">
                                        <div class="caption">
                                            <i class="fa fa-list-alt"></i>User Listing
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
                                                    <label><input type="checkbox" checked data-column="2">ImgURL</label> 
                                                    <label><input type="checkbox" checked data-column="3">Email</label> 
                                                    <label><input type="checkbox" checked data-column="4">WebUrl</label> 
                                                    <label><input type="checkbox" checked data-column="5">Actions</label>
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
                                                    <th>ImgURL</th> 
                                                    <th>Email</th> 
                                                    <th>WebUrl</th>                                     
                                                    <th>Actions</th> 
                                                </tr>                                
                                            </thead>
                                            <tbody>                                                
                                                <c:forEach var="user" items="${userList}" >
                                                <tr>                                                    
                                                    <td>${user.userId}</td> 
                                                    <td>${user.userName}</td> 
                                                    <td>${user.userImgURL}</td> 
                                                    <td>${user.userEmail}</td> 
                                                    <td>${user.userWebUrl}</td> 
                                                    <td>
                                                        <button id="edit-item${user.userId}" class="btn btn-sm green filter-submit margin-bottom"><span class="glyphicon glyphicon-pencil"></span></button>&nbsp;
                                                        <button id="view-address${user.userId}" class="btn btn-sm grey filter-cancel"><i class="fa fa-eye"></i> Address</button>&nbsp;   
                                                        <button id="view-contact${user.userId}" class="btn btn-sm grey filter-cancel"><i class="fa fa-eye"></i> Contact</button>&nbsp;   
                                                        <button id="view-roles${user.userId}" class="btn btn-sm grey filter-cancel"><i class="fa fa-eye"></i> Roles</button>&nbsp;  
                                                        <button id="view-actions${user.userId}" class="btn btn-sm grey filter-cancel"><i class="fa fa-location-arrow"></i> Actions</button>&nbsp;
                                                        <button id="delete-item${user.userId}" class="btn btn-sm red filter-cancel"><span class="glyphicon glyphicon-trash"></span></button> 
                                                    </td>
                                                </tr>  
                                                <script type="text/javascript">
                                                    $("#edit-item${user.userId}").button().click(function() {
                                                        toggleVisibility('itemBox${user.userId}');                                                        
                                                        document.getElementById('itemBox${user.userId}').scrollIntoView();
                                                        window.scrollBy(0,-80);
                                                    });
                                                    $("#view-address${user.userId}").button().click(function() {
                                                        window.location = 'AddressUI.jsp?column=AddressId&columnValue=${user.addressId}';
                                                    });
                                                    $("#view-contact${user.userId}").button().click(function() {
                                                        window.location = 'ContactUI.jsp?column=ContactId&columnValue=${user.contactId}';
                                                    });
                                                    $("#view-roles${user.userId}").button().click(function() {
                                                        window.location = 'UserRoleUI.jsp?column=UserName&columnValue=${user.userName}';
                                                    });
                                                    $("#view-actions${user.userId}").button().click(function() {
                                                        window.location = 'UserActionUI.jsp?column=UserId&columnValue=${user.userId}';
                                                    });
                                                    $("#delete-item${user.userId}").button().click(function() {
                                                        window.location = '../Operations?form=user&action=3&id=${user.userId}';
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
                                        
                                        <button onClick="window.location ='../Operations?form=user&action=4';" type="button" data-dismiss="modal" class="btn green">Yes</button>
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
                        userId:    { required: true, number: true }, 
                        brandId:    { required: true, number: true }, 
                        typeId:    { required: true, number: true }, 
                        userName:    { required: true, minlength: 1, maxlength: 30}, 
                        userPassword:    { required: true, minlength: 1, maxlength: 15}, 
                        userSecurityQuestion:    { required: true, minlength: 1, maxlength: 100}, 
                        userSecurityAnswer:    { required: true, minlength: 1, maxlength: 30}, 
                        userImgURL:    { required: true, minlength: 1, maxlength: 255}, 
                        userEmail:    { required: true, minlength: 1, maxlength: 255}, 
                        userEmailConfirmed:    { required: true, number: true }, 
                        userWebUrl:    { required: true, minlength: 1, maxlength: 255}
                        
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

