
<%@page import="java.text.*" %>
<%@page import="java.util.*" %>
<%@page import="com.busy.engine.dao.*" %>
<%@page import="com.busy.engine.entity.*" %>
<%@page import="com.busy.engine.data.*" %>

<%@page contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<%
        ArrayList<Item> items = Database.getAllItemsWithInfoByType(1, null, null);		
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
            <link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-datepicker/css/datepicker.css"/>            
            <link rel="stylesheet" type="text/css" href="../assets/global/plugins/select2/select2.css"/>
            <link rel="stylesheet" type="text/css" href="../assets/global/plugins/datatables/extensions/Scroller/css/dataTables.scroller.min.css"/>
            <link rel="stylesheet" type="text/css" href="../assets/global/plugins/datatables/extensions/ColReorder/css/dataTables.colReorder.min.css"/>
            <link rel="stylesheet" type="text/css" href="../assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
            <link rel="stylesheet" type="text/css" href="../assets/global/plugins/fancybox/source/jquery.fancybox.css"  >
        <!-- END PAGE LEVEL STYLES -->
        
        <!-- BEGIN THEME STYLES -->
            <link rel="stylesheet" type="text/css" href="../assets/global/css/components.css"/>
            <link rel="stylesheet" type="text/css" href="../assets/global/css/plugins.css" />
            <link rel="stylesheet" type="text/css" href="../assets/admin/layout/css/layout.css" />
            <link rel="stylesheet" type="text/css" href="../assets/admin/layout/css/themes/light.css" id="style_color"/>
            <link rel="stylesheet" type="text/css" href="../assets/admin/layout/css/custom.css"/>
        <!-- END THEME STYLES -->

        <%@include file="index_global_scripts.jsp" %>
        
        <script type="text/javascript" src="../uploadify/jquery.uploadify3.2.min.js"></script> 

        <link rel="shortcut icon" href="favicon.ico"/>
    </head>

    <body class="page-header-fixed page-footer-fixed">

        <%@include file="index_header.jsp" %> 

        <div class="clearfix"></div>
        <!-- BEGIN CONTAINER -->
        <div class="page-container">

            <% request.setAttribute("category", "E-Commerce"); %> 
            <% request.setAttribute("subCategory", "products"); %>
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
                                    Widget settings form goes here asdasd
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

                    <!-- BEGIN PAGE HEADER-->
                    <div class="row">
                        <div class="col-md-12">
                            <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                            <h3 class="page-title"> Products </h3>
                            <ul class="page-breadcrumb breadcrumb">                                
                                <li>
                                    <i class="fa fa-home"></i><a href="index.jsp">Home</a>
                                </li>
                                <li>
                                    <a href="#">E-Commerce</a>
                                </li>
                                <li>
                                    <a href="#">Products</a>
                                </li>
                            </ul>
                            <!-- END PAGE TITLE & BREADCRUMB-->
                        </div>
                    </div>
                    <!-- END PAGE HEADER-->
                    
                    <!-- BEGIN PAGE CONTENT-->        
                    
                    <div class="row">
				<div class="col-md-6">
					<!-- BEGIN ALERTS PORTLET-->
					<div class="portlet">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-comment"></i>Notifications
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
                                <a href="#portlet-config" data-toggle="modal" class="config"></a>
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body">		
                        
                        <%  if(request.getParameter("SuccessMsg") != null)
							{										%>
                            <div class="alert alert-success alert-dismissable">
								<button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
								<%= request.getParameter("SuccessMsg")%>
							</div>				
						<%	}
						  if(request.getParameter("ErrorMsg") != null)
							{										%>
                            <div class="alert alert-danger alert-dismissable">
								<button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
								<%= request.getParameter("ErrorMsg")%>
							</div>			
						<%	}	%>
                        
						</div>
					</div>
					<!-- END ALERTS PORTLET-->
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
							<div class="tabbable-custom ">
								<ul class="nav nav-tabs ">
									<li class="active">
										<a href="#tab_5_1" data-toggle="tab"><i class="fa fa-plus"></i>New Category</a>
									</li>
									<li>
										<a href="#tab_5_2" data-toggle="tab"><i class="fa fa-plus"></i>New Brand</a>
									</li>
									<li>
										<a href="#tab_5_3" data-toggle="tab"><i class="fa fa-plus"></i>New Product</a>
									</li>
								</ul>
								<div class="tab-content">
									<div class="tab-pane active" id="tab_5_1">
										<p>
											 Add a new product Cateogry:
										</p>										
                                        <form method="post" action="add.jsp?form=categories" class="form-horizontal" role="form">
                                        	<div class="form-body">
                                                <div class="form-group">
                                                    <label for="name" class="col-md-2 control-label">Name</label>
                                                    <div class="col-md-8">
                                                        <input type="text" name="name" id="name" class="form-control maxlength-handler" maxlength="45" placeholder="Enter Text" />
                                                        <span class="help-block">max 45 chars</span>    
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label">Discount</label>
                                                    <div class="col-md-8">
                                                        <select name="discountId" class="form-control">
                                                            <%= Database.generateSelectOptionsFromTableAndColumn("discount", "", 2)%>
                                                        </select>
                                                    </div>
                                            	</div>
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label">Parent Category</label>
                                                    <div class="col-md-8">
                                                        <select name="parentCategoryId" class="form-control">
                                                            <%= Database.generateSelectOptionsFromTableAndColumn("category", "", 2)%>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-md-10">
	                                                    <button type="submit" class="btn green" style="float:right">Create</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
									</div>
									<div class="tab-pane" id="tab_5_2">
										<p>
											 Add a new product Brand:
										</p>	
                                        <form method="post" action="add.jsp?form=item_brands" class="form-horizontal" role="form">
                                        	<div class="form-body">
                                                <div class="form-group">
                                                    <label for="name" class="col-md-2 control-label">Name</label>
                                                    <div class="col-md-10">
                                                    	<input type="text" name="name" id="name" class="form-control maxlength-handler" maxlength="100" placeholder="Enter Text" />
                                                		<span class="help-block">max 100 chars</span> 
                                                    </div>
                                                </div>                                             
                                                <div class="form-group">                                                	
                                                    <label for="desc" class="col-md-2 control-label">Description</label>
                                                    <div class="col-md-10">
                                                   		<input type="text" name="desc" id="desc" class="form-control maxlength-handler" maxlength="1000" placeholder="Enter Text" />
                                                		<span class="help-block">max 1000 chars</span>
                                                    	<button type="submit" class="btn green"  style="float:right">Create</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
									</div>
                                    <div class="tab-pane" id="tab_5_3">
                                        <form method="post" action="../Operations?form=item&action=1" id="form_sample_1" class="horizontal-form">
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
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label class="control-label">Name<span class="required">*</span></label>
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                    <input type="text" name="itemName" class="form-control maxlength-handler" maxlength="255" placeholder="Enter Text">
                                                                <span class="help-block">
                                                                     max 255 char
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label class="control-label">Brand</label>
                                                            <select name="itemBrandId"class="form-control">
                                                                <%= Database.generateSelectOptionsFromTableAndColumn("item_brand", "xx", 2)%>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label class="control-label">Type</label>
                                                            <select name="itemTypeId"class="form-control">
                                                                <%= Database.generateSelectOptionsFromTableAndColumn("item_type", "xx", 2)%>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label class="control-label">Template</label>
                                                            <select name="templateId"class="form-control">
                                                                <%= Database.generateSelectOptionsFromTableAndColumn("template", "xx", 2)%>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="form-group">
                                                            <label class="control-label">Vendor</label>
                                                            <select name="vendorId"class="form-control">
                                                                <%= Database.generateSelectOptionsFromTableAndColumn("vendor", "xx", 2)%>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="form-group">
                                                            <label class="control-label">Description</label>
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <textarea name="description" id="desc" class="form-control maxlength-handler" maxlength="65535" rows="3" ></textarea>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="form-group">
                                                            <label class="control-label">Short Description</label>
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text"  name="shortDescription" class="form-control maxlength-handler" maxlength="255" rows="3" >
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label class="control-label">List Price</label>
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="listPrice" class="form-control" placeholder="Enter Number (ex: 55.00)">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group">															
                                                            <label class="control-label">Price</label>
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="price" class="form-control" placeholder="Enter Number (ex: 25.50)">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--/row-->
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label class="control-label">Adjustment</label>	
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>														
                                                                <input type="text" name="adjustment" class="form-control" placeholder="Enter Integer">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label class="control-label">SKU</label>	
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>														
                                                                <input type="text" name="sku" class="form-control  maxlength-handler" maxlength="30">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-12 ">
                                                        <div class="form-group">
                                                            <label>Status</label>
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <select name="itemStatus" class="form-control">
                                                                    <%= Database.generateSelectFromStatusForTable("Item", "1")%>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-12 ">
                                                        <div class="form-group">
                                                            <label>SEO Title</label>
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="seotitle" class="form-control maxlength-handler" maxlength="150">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label>SEO Description</label>
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="seodesc" class="form-control maxlength-handler" maxlength="255">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label>SEO Keywords</label>
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" name="seokeywords" class="form-control maxlength-handler" maxlength="255">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                                
                                            <div class="form-actions right">
                                                <button type="button" class="btn red"><i class="fa fa-minus"></i>&nbsp;Cancel</button>
                                                <button type="submit" class="btn green"><i class="fa fa-plus"></i>&nbsp;Create</button>
                                            </div>
                                        </form>
                                    </div>
                                    
								</div>
							</div>
							
						</div>
					</div>
					<!-- END OPERATIONS PORTLET-->
				</div>
			</div>
                    
                    
            <!-- START RECORD DETAILS -->
                                    
            <%
            for (Item item : items)
            {
                    String msrpString = formatter.format(item.getListPrice());
                    String costString = formatter.format(item.getPrice());
            %>            
            <div class="row">                      
                <div class="col-md-12">
					<div class="portlet" id="itemBox<%= item.getItemId()%>" style="display:<%=(request.getParameter("id") == null ? "none" : (item.getItemId().equals(request.getParameter("id")) ? "block" : "none"))%>">
						<div class="portlet-title">
							<div class="caption">
								<a href="#" onClick="toggleVisibility('itemBox<%= item.getItemId()%>');" class="btn btn-xs btn-default"><span class="glyphicon glyphicon-remove"></span></a>&nbsp;(<%= item.getItemId()%>) <%= item.getItemName()%>
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
							</div>
						</div>
						<div class="portlet-body">	
                            <div class="col-md-2">
                                <div class="portlet box blue">
                                    <div class="portlet-title">
                                        <div class="caption">Images</div> 
                                        <div class="actions">
                                            <div class="btn-group">          
                                                <input type="file" name="image_upload" id="image_upload<%= item.getItemId()%>">                                            
                                                <script type="text/javascript">
                                                    $('#image_upload<%= item.getItemId()%>').uploadify({
                                                        'buttonText': 'Add Images...',	
                                                        'buttonClass' : 'btn btn-default btn-sm float-right',
                                                        'style' : '',
                                                        'formData': {'id': '<%= item.getItemId()%>'},
                                                        'fileTypeExts': '*.gif; *.jpg; *.png',
                                                        'swf': 'uploadify.swf',
                                                        'uploader': 'upload2.jsp',
                                                        'fileSizeLimit': '3MB',
                                                        'uploadLimit': 15,
                                                        'fileTypeDesc': 'Image Files...',
                                                        'method': 'post',
                                                        'height': 25,
                                                        'onQueueComplete': function(file, data, response) {
                                                            window.location = "products.jsp?id=<%= item.getItemId()%>";
                                                            //alert('The file ' + file.name + ' was successfully uploaded with a response of ' + response + ':' + data);
                                                        }
                                                    });
                                                </script>
                                            </div>
                                        </div>                                       
                                    </div>

                                    <div class="portlet-body">	
                                        <a name="<%= item.getItemId()%>"></a>
                                        <div style="height: 700px; width:100%; overflow: auto; padding: 5px;">
                                            <%									
                                            for(ItemImage i : item.getItemImageList())
                                            {
                                            %>
                                                <a href="../items/<%= i.getImageName()%>" class="fancybox-button" data-rel="fancybox-button">
                                                    <img class="shadow" src="../items/sm_<%= i.getImageName()%>" border="0" />
                                                </a>
                                                <a href="../Operations?form=item_image&action=3&id=<%= i.getId()%>">
                                                    <img src="../images/delete.jpg" border="0" style="float:right" />
                                                </a> 
                                                <hr />                                 
                                            <% }%>                        
                                        </div>                                            
                                    </div>
                                </div> 
                            </div>
    
                            <div class="col-md-7">
                                <div class="portlet box grey">
                                    <div class="portlet-title">
                                        <div class="caption">Product Details</div>
                                    </div>
                                    <div class="portlet-body">	
                                        <div class="form-body">
                                            <form class="form-horizontal" name="edit" action="edit.jsp?form=item" onSubmit="return validate();" method="post">
                                                <div class="alert alert-danger display-hide">
                                                    <button class="close" data-close="alert"></button>
                                                    You have some form errors. Please check below.
                                                </div>
                                                
                                                <div class="alert alert-success display-hide">
                                                    <button class="close" data-close="alert"></button>
                                                    Your form validation is successful!
                                                </div>
                                            
                                                <input type="hidden" name="id" value="<%= item.getItemId()%>"/>

                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="desc">Description:</label>
                                                    <div  class="col-md-10">
                                                        <textarea name="desc" rows="8" class="ckeditor form-control">
                                                            <%= item.getDescription()%>
                                                        </textarea>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="name">Name:</label>
                                                    <div  class="col-md-10">
                                                        <input name="name" type="text" value="<%= item.getItemName()%>" size="50" class="form-control input-sm" />
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="shortDesc">Short Desc:</label>
                                                    <div  class="col-md-10">
                                                        <input name="shortDesc" type="text" value="<%= item.getShortDescription()%>" size="50" class="form-control input-sm" />
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="brandId">Brand:</label>
                                                    <div  class="col-md-10">
                                                        <select name="brandId" class="form-control input-sm" >
                                                            <%= Database.generateSelectOptionsFromTableAndColumn("item_brand", item.getItemBrandId() + "", 2)%>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="listPrice">List Price:</label>
                                                    <div  class="col-md-10">
                                                        <input name="listPrice" type="text" value="<%= item.getListPrice()%>" class="form-control input-sm" />
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="price">Price:</label>
                                                    <div  class="col-md-10">
                                                        <input name="price" type="text" value="<%= item.getPrice()%>" class="form-control input-sm" />
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="adjust">Adjustment:</label>
                                                    <div  class="col-md-10">
                                                        <input name="adjust" type="text" value="<%= item.getAdjustment()%>" class="form-control input-sm" />
                                                    </div>
                                                </div>
                                                    <%
                                                    if(item.getMetaTag() == null) {
                                                        item.setMetaTag(new MetaTag(null,"","",""));
                                                    }                                                    
                                                    %>
                                                    
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="seotitle">SEO Title:</label>
                                                    <div  class="col-md-10">
                                                        <input name="seotitle" type="text" value="<%= item.getMetaTag().getTitle() %>" size="45" class="form-control input-sm" />
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="seodesc">SEO Desc:</label>
                                                    <div  class="col-md-10">
                                                        <input name="seodesc" type="text" value="<%= item.getMetaTag().getDescription() %>" size="45" class="form-control input-sm" />
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="seokeywords">SEO Keywords:</label>
                                                    <div  class="col-md-10">
                                                        <input name="seokeywords" type="text" value="<%= item.getMetaTag().getKeywords() %>" size="45" class="form-control input-sm" />
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
    
                            <div class="col-md-3">
                                <div class="portlet box red">
                                    <div class="portlet-title">
                                        <div class="caption">Metadata</div>                                       
                                        <div class="actions">
                                            <div class="btn-group">          
                                                <input type="file" name="file_upload" id="file_upload<%= item.getItemId()%>">                                                
                                                <script type="text/javascript">
                                                    $('#file_upload<%= item.getItemId()%>').uploadify({
                                                        'buttonText': 'Add Files...',
                                                        'formData': {'id': '<%= item.getItemId()%>'},
                                                        'swf': 'uploadify.swf',
                                                        'uploader': 'upload-file-for-item.jsp',
                                                        'fileSizeLimit': '30MB',
                                                        'uploadLimit': 15,
                                                        'fileTypeDesc': 'All Files...',
                                                        'method': 'post',
                                                        'width': 70,
                                                        'onQueueComplete': function(file, data, response) {
                                                            window.location = "products.jsp?id=<%= item.getItemId()%>";
                                                        }
                                                    });
                                                </script>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="portlet-body">	
                                        <table width="100%" cellpadding="0" cellspacing="1" border="0">
                                            <tr>
                                                <td align="center" colspan="4">
                                                    <h4>Options</h4>
                                                </td>
                                            </tr>
                                            <tr><td align="center">&nbsp;</td>
                                                <td align="center">Opt</td><td align="center">Qty</td><td align="center">Availability</td></tr>

                                            <%  for (OptionAvailability o : item.getOptionAvailabilityList())
                                                {
                                            %>
                                            <tr>
                                                <td align="center">
                                                    <a href="delete.jsp?form=item_options_available&ItemId=<%= item.getItemId()%>&ItemOptionId=<%= o.getId() %>">
                                                        <img src="../images/delete.jpg" border="0" />
                                                    </a>
                                                </td>
                                                <td align="center">
                                                    <%= o.getItemAvailability().getType() %> 
                                                </td>
                                                <td align="center">
                                                    <%= o.getAvailableQuantity() %>                               
                                                </td>
                                                <td align="center">
                                                    <select name="availability" class="form-control input-sm" >
                                                        <%= Database.generateSelectOptionsFromTableAndColumn("item_availability", o.getItemAvailabilityId() + "", 2) %>
                                                    </select>
                                                </td>
                                            </tr>                                        
                                            <% }%>  
                                            <form name="add" action="add.jsp?form=item_options_available" onSubmit="return validate();" method="post">
                                                <input type=hidden name="ItemId" value="<%= item.getItemId()%>"/>
                                                <tr>
                                                    <td align="center"><input type="image" src="../images/insert.png" border="0" /></td>
                                                    <td align="center"> 
                                                        <select name="optionId" class="form-control input-sm" >
                                                            <%= Database.generateSelectOptionsFromTableAndColumn("item_option", "xx", 2)%>
                                                        </select>
                                                    </td>
                                                    <td align="center"><input name="quantity" type="text" value="" class="form-control input-sm" /></td>
                                                    <td align="center">
                                                        <select name="availability" class="form-control input-sm" >
                                                            <%= Database.generateSelectOptionsFromTableAndColumn("item_availability", "xx", 2)%>
                                                        </select>
                                                    </td>
                                                </tr>
                                            </form>
                                        </table>
                                        <hr size="3" color="#000000">
                                        <table width="100%" cellpadding="0" cellspacing="1" border="0">
                                            <tr>
                                                <td align="center" colspan="2">
                                                    <h4>Categories</h4>
                                                </td>
                                            </tr>

                                            <%
                                                for (ItemCategory c : item.getItemCategoryList())
                                                {
                                                    ItemCategoryDaoImpl categoryDao = (ItemCategoryDaoImpl) application.getAttribute("itemCategoryDao");
                                                    categoryDao.getRelatedInfo(c);
                                            %>
                                            <tr>
                                                <td align="center">
                                                    <%= c.getCategory().getCategoryName()%>
                                                </td>
                                                <td align="center">
                                                    <a href="delete.jsp?form=item_categories&ItemId=<%= item.getItemId()%>&CategoryId=<%= c.getCategoryId()%>">
                                                        <img src="../images/delete.jpg" border="0" />
                                                    </a>
                                                </td>
                                            </tr>                                        
                                            <% }%>   

                                            <form name="add" action="../Operations?form=item_category&action=1" onSubmit="return validate();" method="post">
                                                <input type=hidden name="sourcePage" value="products.jsp"/>
                                                <input type=hidden name="itemId" value="<%= item.getItemId()%>"/>
                                                <tr>
                                                    <td align="center"><select name="categoryId" class="form-control input-sm" >
                                                            <%= Database.generateSelectOptionsFromTableAndColumn("category", "xx", 2)%>
                                                        </select></td>
                                                    <td align="center"> 

                                                        <input type="image" src="../images/insert.png" border="0" />
                                                    </td>
                                                </tr>

                                            </form>
                                        </table>  
                                        <hr size="3" color="#000000">
                                        <table width="100%" cellpadding="0" cellspacing="1" border="0">
                                            <tr><td align="center" colspan="2"><h4>Files</h4></tr>

                                            <%
                                                for (ItemFile f : item.getItemFileList())
                                                {
                                            %>
                                            <tr>
                                                <td align="center">
                                                    <a href="delete.jsp?form=item_files&ItemId=<%= item.getItemId()%>">
                                                        <img src="../images/delete.jpg" border="0" />
                                                    </a>
                                                </td>
                                                <td align="center"><%= f.getFileName()%></td>
                                            </tr>                                        
                                            <% }%>                                     
                                            <tr><td align="center" colspan="4"><hr></tr>

                                        </table>

                                    </div>                  
                                </div>
    
                            </div>
                        </div>
                        
                        
                    </div> 
                </div>
            </div>
                  
                    <% } %>
					<!-- END RECORD DETAILS-->
                    
                    
                    <!-- BEGIN MODAL NEW PRODUCT FORM-->
                    
                    <div id="myModal_new_product" class="modal fade" role="dialog" aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                    <h4 class="modal-title">Add a new Product</h4>
                                </div>
                                <div class="modal-body form">
                                    <!-- BEGIN FORM-->
                                    <form method="post" action="../Operations?form=item&action=1" id="form_sample_2" class="horizontal-form">
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
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label class="control-label">Name<span class="required">*</span></label>
                                                        <div class="input-icon right">
                                                            <i class="fa"></i>
                                                        	<input type="text" name="itemName" class="form-control" placeholder="Enter Text">
                                                            <span class="help-block">
                                                                 max 255 char
                                                            </span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label class="control-label">Brand</label>
                                                            <select name="itemBrandId"class="form-control">
                                                                <%= Database.generateSelectOptionsFromTableAndColumn("item_brand", "xx", 2)%>
                                                            </select>
                                                        </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label class="control-label">Type</label>
                                                        <select name="itemTypeId"class="form-control">
                                                            <%= Database.generateSelectOptionsFromTableAndColumn("item_type", "xx", 2)%>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label class="control-label">Description</label>
                                                        <div class="input-icon right">
                                                            <i class="fa"></i>
                                                        	<textarea name="description" id="desc" class="form-control" rows="3" ></textarea>
                                                            <span class="help-block">
                                                                 max 50,000 char
                                                            </span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label class="control-label">Short Description</label>
                                                        <div class="input-icon right">
                                                            <i class="fa"></i>
                                                        	<input type="text"  name="shortDescription" class="form-control" >
                                                        </div>
                                                        <span class="help-block">
                                                             max 255 char
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label class="control-label">List Price</label>
                                                        <div class="input-icon right">
                                                            <i class="fa"></i>
                                                        	<input type="text" name="listPrice" class="form-control" placeholder="Enter Text">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group">															
                                                        <label class="control-label">Price</label>
                                                        <div class="input-icon right">
                                                            <i class="fa"></i>
                                                        	<input type="text" name="price" class="form-control" placeholder="Enter Text">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!--/row-->
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label class="control-label">Adjustment</label>	
                                                        <div class="input-icon right">
                                                            <i class="fa"></i>														
	                                                        <input type="text" name="adjustment" class="form-control" placeholder="Enter Text">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label class="control-label">SKU</label>	
                                                        <div class="input-icon right">
                                                            <i class="fa"></i>														
                                                            <input type="text" name="sku" class="form-control  maxlength-handler" maxlength="30">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12 ">
                                                    <div class="form-group">
                                                        <label>Status</label>
                                                        <div class="input-icon right">
                                                            <i class="fa"></i>
                                                            <select name="itemStatus" class="form-control">
                                                                <%= Database.generateSelectFromStatusForTable("Item", "1")%>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                            
                                        <div class="form-actions right">
                                            <button type="button" class="btn red" data-dismiss="modal"><i class="fa fa-minus"></i>&nbsp;Cancel</button>
                                            <button type="submit" class="btn green"><i class="fa fa-plus"></i>&nbsp;Create</button>
                                        </div>
                                    </form>
                                    <!-- END FORM-->
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- END MODAL NEW PRODUCT FORM-->
                    
                    
                    <!-- BEGIN DATA TABLE--> 
                    <div class="row">
                        <div class="col-md-12">                        
                            <div class="portlet box grey-silver">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="fa fa-gift"></i>Product Listing
                                    </div>
                                    <div class="actions">
                                        <div class="btn-group">                                
                                            <a href="#myModal_new_product" class="btn btn-default" data-toggle="modal"><i class="fa fa-plus"></i> New Product </a> 
                                        </div>
                                        <div class="btn-group"> 
                                            <a class="btn btn-default" href="#" data-toggle="dropdown">
                                                  <i class="fa fa-columns"></i> Columns <i class="fa fa-angle-down"></i>
                                            </a>
                                            <div id="sample_2_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">
                                                <label><input type="checkbox" checked data-column="0">Id</label>
                                                <label><input type="checkbox" checked data-column="1">Product Name</label>
                                                <label><input type="checkbox" checked data-column="2">Category</label>
                                                <label><input type="checkbox" checked data-column="3">List Price</label>
                                                <label><input type="checkbox" checked data-column="4">Price</label>
                                                <label><input type="checkbox" checked data-column="5">Actions</label>                                        
                                            </div>
                                        </div>            
                                    </div>
                                </div>
                                <div class="portlet-body">
                                    <table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
                                    <thead>							
                                        <tr>
                                            <th width="5%">
                                                Id
                                            </th>
                                            <th width="25%">
                                                Product Name
                                            </th>
                                            <th width="20%">
                                                Category
                                            </th>
                                            <th width="10%">
                                                List Price
                                            </th>
                                            <th width="10%">
                                                Price
                                            </th>
                                            <th width="*">
                                                Actions
                                            </th>
                                        </tr>                                
                                    </thead>
                                    <tbody>							
                                        <%
                                        for (Item item : items)
                                        {
                                                String msrpString = formatter.format(item.getListPrice());
                                                String costString = formatter.format(item.getPrice());
                                        %>
                                        <tr>
                                            <td><%= item.getItemId()%></td>
                                            <td><%= item.getItemName()%></td>
                                            <td><%= item.getItemBrand().getBrandName()%></td>
                                            <td><%= msrpString%></td>
                                            <td><%= costString%></td>
                                            <td>
                                                <div class="margin-bottom-5">
                                                    <button id="edit-item<%= item.getItemId()%>" class="btn btn-sm green filter-submit margin-bottom"><i class="fa fa-edit"></i> Edit</button>&nbsp;
                                                    <button id="generate-code-for-item<%= item.getItemId()%>" class="btn btn-sm "><i class="fa fa-cog"></i> Code</button>&nbsp;
                                                    <button id="delete-item<%= item.getItemId()%>" class="btn btn-sm red filter-cancel"><i class="fa fa-trash-o"></i> Delete</button>
                                                    <script type="text/javascript">
                                                        $("#edit-item<%= item.getItemId()%>").button().click(function() {
                                                            toggleVisibility('itemBox<%= item.getItemId()%>');
                                                        });
                                                        $("#delete-item<%= item.getItemId()%>").button().click(function() {
                                                            window.location = 'delete.jsp?form=item&id=<%= item.getItemId()%>';
                                                        });
                                                        $("#generate-code-for-item<%= item.getItemId()%>").button().click(function() {
                                                            window.location = '../code.jsp?Id=<%= item.getItemId()%>';
                                                        });
														
														

														


                                                    </script>
                                                </div>
        
                                            </td>
                                        </tr>
                                        <% } %>
                                        
                                    </tbody>
                                    </table>
                                </div>
                            </div>
                        
                        </div>
                    </div>
                    <!-- END DATA TABLE-->



                    <!-- END PAGE CONTENT-->
                </div>
            </div>
            <!-- END CONTENT -->
        </div>
        <!-- END CONTAINER -->

        <%@include file="index_footer.jsp" %> 

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
        <script type="text/javascript" src="../assets/global/plugins/bootstrap-maxlength/bootstrap-maxlength.min.js"></script>
        <!-- END PAGE LEVEL PLUGINS -->
        
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
        <script type="text/javascript" src="../assets/global/plugins/jquery-mixitup/jquery.mixitup.min.js" ></script>
        <script type="text/javascript" src="../assets/global/plugins/fancybox/source/jquery.fancybox.pack.js"></script>
        <script src="../assets/global/scripts/metronic.js" type="text/javascript"></script>
        <script src="../assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
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
			
			

            // Modal FORM VALIdATION SCRIPT
            // http://docs.jquery.com/Plugins/Validation

            var form2 = $('#form_sample_2');
            var error2 = $('.alert-danger', form2);
            var success2 = $('.alert-success', form2);

            form2.validate({
                    errorElement: 'span', //default input error message container
                    errorClass: 'help-block', // default input error message class
                    focusInvalid: false, // do not focus the last invalid input
                    ignore: "",
                    rules: {
                        itemName:           { required: true, minlength: 3, maxlength: 255 },
						itemBrandId:        { required: true },
						itemTypeId:         { required: true },
						templateId:         { required: true },
						vendorId:           { required: true },
                        description:        { required: true, minlength: 1, maxlength: 50000  },
                        shortDescription:   { minlength: 1, maxlength: 255 },
                        listPrice:          { required: true, number: true },
                        price:              { required: true, number: true },
                        adjustment:         { digits: true },                            
                        sku:                { minlength: 1, maxlength: 30 },
						itemStatus:         { required: true, number: true },
                        seotitle:           { required: true, minlength: 3, maxlength: 150 },
                        seodesc:            { required: true, minlength: 3, maxlength: 255 },
                        seokeywords:        { required: true, minlength: 3, maxlength: 255 }
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
                            $(element).closest('.form-group').removeClass("has-success").addClass('has-error'); // set error class to the control group 
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