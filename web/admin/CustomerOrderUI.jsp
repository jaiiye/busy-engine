<%@page import="java.text.*"%>
<%@page import="java.util.*"%>
<%@page import="com.busy.engine.dao.*"%>
<%@page import="com.busy.engine.*"%>
<%@page import="com.busy.engine.data.*"%>
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
ArrayList<CustomerOrder> customer_orderList = new ArrayList<CustomerOrder>();
if (request.getParameter("column") != null && request.getParameter("columnValue") != null)
{
    customer_orderList = new CustomerOrderDaoImpl().findByColumn(request.getParameter("column"), request.getParameter("columnValue"), null, null);
}
else
{
    customer_orderList = new CustomerOrderDaoImpl().findAll(null, null);
}
request.setAttribute("customer_orderList", customer_orderList);
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
                                <h3 class="page-title"> CustomerOrder </h3>
                                <ul class="page-breadcrumb breadcrumb">                                
                                    <li>
                                        <i class="fa fa-home"></i><a href="index.jsp">Home</a>
                                    </li>
                                    <li>
                                        <a href="#"> E-Commerce </a>
                                    </li>
                                    <li>
                                        <a href="#">CustomerOrder</a>
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
                                        <form method="post" action="CustomerOrderUI.jsp" class="form-horizontal" role="form">
                                        	<div class="form-body">
                                                <div class="form-group">
                                                    <div class="col-md-4">
                                                        <select name="column" class="form-control">
                                                            <option value="CustomerOrderId" ${param.column == 'CustomerOrderId' ? "selected" : "" } >CustomerOrderId</option>                                                            
                                                           <option value="CustomerId" ${param.column == 'CustomerId' ? "selected" : "" } >CustomerId</option>                                                            
                                                           <option value="OrderId" ${param.column == 'OrderId' ? "selected" : "" } >OrderId</option>                                                            
                                                           <option value="DiscountId" ${param.column == 'DiscountId' ? "selected" : "" } >DiscountId</option>                                                            
                                                           <option value="CustomerIp" ${param.column == 'CustomerIp' ? "selected" : "" } >CustomerIp</option>                                                            
                                                                                                                                                                                  
                                                        </select> 
                                                    </div>                                                         
                                                    <div class="col-md-5">
                                                        <input type="text" name="columnValue" class="form-control"/>
                                                    </div>
                                                    <div class="col-md-3">
	                                                <button type="submit" class="btn grey-silver">Filter</button>
                                                        <button type="button" class="btn grey-cascade" style="float:right" onclick="javascript:window.location = 'CustomerOrderUI.jsp';"><i class="fa fa-refresh"></i></button>
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
                                  
                        <c:forEach var="customer_order" items="${customer_orderList}" >
                        <div class="row" id="itemBox${customer_order.customerOrderId}" style="display:${param.id == null ? "none" : customer_order.customerOrderId==param.id ? "block" : "none"}">                      
                            <div class="col-md-12">
                                <div class="portlet box green-seagreen">
                                    <div class="portlet-title">
                                        <div class="caption">Record Details</div>
                                        <div class="actions">                                                 
                                            <div class="btn-group">                                
                                                <a href="javascript:toggleVisibility('itemBox${customer_order.customerOrderId}');" class="btn btn-default"><i class="fa fa-times"></i>&nbsp;Close</a> 
                                            </div>
                                        </div>
                                    </div>
                                    <div class="portlet-body">	
                                        <div class="portlet-body form">
                                            <form class="form-horizontal" name="edit" action="../Operations?form=customer_order&action=2" method="post">

                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="customerOrderId">CustomerOrder:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="customerOrderId" class="form-control" value="${customer_order.customerOrderId}" />

                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="customerId">Customer:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="customerId" class="form-control" value="${customer_order.customerId}" />
                                                        <select name="customerId" class="form-control">
                                                            <%CustomerOrder x = (CustomerOrder) pageContext.getAttribute("customer_order"); %>
                                                            <%= Database.generateSelectOptionsFromTableAndColumn("customer", x.getCustomerId().toString(), 2)%>
                                                        </select>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="orderId">Order:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="orderId" class="form-control" value="${customer_order.orderId}" />
                                                        <select name="orderId" class="form-control">
                                                            <%= Database.generateSelectOptionsFromTableAndColumn("order", x.getOrderId().toString(), 2)%>
                                                        </select>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="discountId">Discount:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="discountId" class="form-control" value="${customer_order.discountId}" />
                                                        <select name="discountId" class="form-control">
                                                            <%= Database.generateSelectOptionsFromTableAndColumn("discount", x.getDiscountId().toString(), 2)%>
                                                        </select>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="customerIp">CustomerIp:</label>
                                                    <div  class="col-md-10">
                                                        <input type="text" name="customerIp" class="form-control maxlength-handler" maxlength="100" value="${customer_order.customerIp}" />
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


                        <!-- BEGIN MODAL NEW CustomerOrder FORM-->                    
                        <div id="myModal_new_record" class="modal fade" role="dialog" aria-hidden="true">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                        <h4 class="modal-title">Add a new CustomerOrder</h4>
                                    </div>
                                    <div class="modal-body form">
                                        <!-- BEGIN FORM-->
                                        <form method="post" action="../Operations?form=customer_order&action=1" id="create_form" class="horizontal-form">
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
                                                        <label class="col-md-2 control-label">CustomerOrderId</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <select name="customerOrderId" class="form-control">
                                                                    <%= Database.generateSelectOptionsFromTableAndColumn("customer_order", "", 2)%>
                                                               </select>                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">CustomerId</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <select name="customerId" class="form-control">
                                                                    <%= Database.generateSelectOptionsFromTableAndColumn("customer", "", 2)%>
                                                               </select>                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">OrderId</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <select name="orderId" class="form-control">
                                                                    <%= Database.generateSelectOptionsFromTableAndColumn("order", "", 2)%>
                                                               </select>                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">DiscountId</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <select name="discountId" class="form-control">
                                                                    <%= Database.generateSelectOptionsFromTableAndColumn("discount", "", 2)%>
                                                               </select>                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label">CustomerIp</label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="customerIp" class="form-control maxlength-handler" placeholder="Enter Text" maxlength="100" />                                                            
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

                        <!-- END MODAL NEW CustomerOrder FORM-->


                        <!-- BEGIN DATA TABLE--> 
                        <div class="row">
                            <div class="col-md-12">
                                 <div class="portlet box red-flamingo">
                                    <div class="portlet-title">
                                        <div class="caption">
                                            <i class="fa fa-list-alt"></i>CustomerOrder Listing
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
                                                    <label><input type="checkbox" checked data-column="1">CustomerId</label> 
                                                    <label><input type="checkbox" checked data-column="2">OrderId</label> 
                                                    <label><input type="checkbox" checked data-column="3">DiscountId</label> 
                                                    <label><input type="checkbox" checked data-column="4">CustomerIp</label> 
                                                    
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
                                                    <th>CustomerId</th> 
                                                    <th>OrderId</th> 
                                                    <th>DiscountId</th> 
                                                    <th>CustomerIp</th> 
                                                                                                        
                                                    <th>Actions</th> 
                                                </tr>                                
                                            </thead>
                                            <tbody>                                                
                                                <c:forEach var="customer_order" items="${customer_orderList}" >
                                                <tr>                                                    
                                                    <td>${customer_order.customerOrderId}</td> 
                                                    <td>${customer_order.customerId}</td> 
                                                    <td>${customer_order.orderId}</td> 
                                                    <td>${customer_order.discountId}</td> 
                                                    <td>${customer_order.customerIp}</td> 
                                                    
                                                    <td>
                                                        <button id="edit-item${customer_order.customerOrderId}" class="btn btn-sm green filter-submit margin-bottom"><span class="glyphicon glyphicon-pencil"></span></button>&nbsp;
                                                        <button id="delete-item${customer_order.customerOrderId}" class="btn btn-sm red filter-cancel"><span class="glyphicon glyphicon-trash"></span></button> 
                                                    </td>
                                                </tr>  
                                                <script type="text/javascript">
                                                    $("#edit-item${customer_order.customerOrderId}").button().click(function() {
                                                        toggleVisibility('itemBox${customer_order.customerOrderId}');                                                        
                                                        document.getElementById('itemBox${customer_order.customerOrderId}').scrollIntoView();
                                                        window.scrollBy(0,-80);
                                                    });
                                                    $("#delete-item${customer_order.customerOrderId}").button().click(function() {
                                                        window.location = '../Operations?form=customer_order&action=3&id=${customer_order.customerOrderId}';
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
                                        
                                        <button onClick="window.location ='../Operations?form=customer_order&action=4';" type="button" data-dismiss="modal" class="btn green">Yes</button>
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
                        customerOrderId:    { required: true, number: true }, 
                        customerId:    { required: true, number: true }, 
                        orderId:    { required: true, number: true }, 
                        discountId:    { required: true, number: true }, 
                        customerIp:    { required: true, minlength: 1, maxlength: 100} 
                        
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

