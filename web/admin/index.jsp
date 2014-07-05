<%@page import="java.text.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<%
    request.setAttribute("info", Database.getDashboardInfo());
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

        <!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
        <link href="../assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/>
        <link href="../assets/global/plugins/fullcalendar/fullcalendar/fullcalendar.css" rel="stylesheet" type="text/css"/>
        <!-- END PAGE LEVEL PLUGIN STYLES -->
        
        <!-- BEGIN PAGE STYLES -->
        <link href="../assets/admin/pages/css/tasks.css" rel="stylesheet" type="text/css"/>
        <!-- END PAGE STYLES -->
        
        <!-- BEGIN THEME STYLES -->
        <link href="../assets/global/css/components.css" rel="stylesheet" type="text/css"/>
        <link href="../assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
        <link href="../assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
        <link href="../assets/admin/layout/css/themes/light.css" rel="stylesheet" type="text/css" id="style_color"/>
        <link href="../assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
        <!-- END THEME STYLES -->

        <link rel="shortcut icon" href="favicon.ico"/>
    </head>

<body class="page-header-fixed page-footer-fixed">

	<%@include file="index_header.jsp" %> 

    <div class="clearfix"></div>
    <!-- BEGIN CONTAINER -->
    <div class="page-container">

        <% request.setAttribute("category", "dashboard"); %> 
        <% request.setAttribute("subCategory", ""); %>        
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
                        <h3 class="page-title">Dashboard <small>statistics...</small></h3>
                    </div>
                </div>
                
                <!-- BEGIN DASHBOARD STATS -->
                <div class="row">
                    <div class="col-lg-2 col-md-4 col-sm-6 col-xs-12">
                        <div class="dashboard-stat blue">
                            <div class="visual">
                                <i class="fa fa-user"></i>
                            </div>
                            <div class="details">
                                <div class="number">
                                     ${info.userCount}
                                </div>
                                <div class="desc">
                                     Users
                                </div>
                            </div>
                            <a class="more" href="UserUI.jsp">
                                 View more <i class="m-icon-swapright m-icon-white"></i>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-2 col-md-4 col-sm-6 col-xs-12">
                        <div class="dashboard-stat blue">
                            <div class="visual">
                                <i class="fa fa-comments"></i>
                            </div>
                            <div class="details">
                                <div class="number">
                                     ${info.blogPostCount}
                                </div>
                                <div class="desc">
                                     Posts
                                </div>
                            </div>
                            <a class="more" href="BlogPostUI.jsp">
                                 View more <i class="m-icon-swapright m-icon-white"></i>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-2 col-md-4 col-sm-6 col-xs-12">
                        <div class="dashboard-stat green">
                            <div class="visual">
                                <i class="fa fa-gift"></i>
                            </div>
                            <div class="details">
                                <div class="number">
                                     ${info.itemCount}
                                </div>
                                <div class="desc">
                                     Products
                                </div>
                            </div>
                            <a class="more" href="ItemUI.jsp">
                                 View more <i class="m-icon-swapright m-icon-white"></i>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-2 col-md-4 col-sm-6 col-xs-12">
                        <div class="dashboard-stat green">
                            <div class="visual">
                                <i class="fa fa-shopping-cart"></i>
                            </div>
                            <div class="details">
                                <div class="number">
                                     ${info.orderCount}
                                </div>
                                <div class="desc">
                                     Orders
                                </div> 
                            </div>                           
                            <a class="more" href="OrderUI.jsp">
                                 View more <i class="m-icon-swapright m-icon-white"></i>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-2 col-md-4 col-sm-6 col-xs-12">
                        <div class="dashboard-stat purple">
                            <div class="visual">
                                <i class="fa fa-folder-open-o"></i>
                            </div>
                            <div class="details">
                                <div class="number">
                                     ${info.fileCount}
                                </div>
                                <div class="desc">
                                     Files
                                </div> 
                            </div>                           
                            <a class="more" href="SiteFileUI.jsp">
                                 View more <i class="m-icon-swapright m-icon-white"></i>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-2 col-md-4 col-sm-6 col-xs-12">
                        <div class="dashboard-stat purple">
                            <div class="visual">
                                <i class="fa fa-picture-o"></i>
                            </div>
                            <div class="details">
                                <div class="number">
                                     ${info.imageCount}
                                </div>
                                <div class="desc">
                                     Images
                                </div> 
                            </div>                           
                            <a class="more" href="ImageUI.jsp">
                                 View more <i class="m-icon-swapright m-icon-white"></i>
                            </a>
                        </div>
                    </div>
                </div>
                <!-- END DASHBOARD STATS -->
                <div class="clearfix">
                </div>
                <div class="row">
                                        
                    <div class="col-md-6 col-sm-6">
                        <div class="portlet box blue-steel">
                            <div class="portlet-title">
                                <div class="caption">
                                    <i class="fa fa-eye"></i>Recent Activities
                                </div>
                            </div>
                            <div class="portlet-body">
                                <div class="scroller" style="height: 300px;" data-always-visible="1" data-rail-visible="0">
                                    <ul class="feeds">
                                                                                
                                        <c:forEach var="activity" items="${info.activities}" >                                        
                                        <li>
                                            <div class="col1">
                                                <div class="cont">
                                                    <div class="cont-col1">
                                                        <c:choose>
                                                            <c:when test="${activity.actionTypeId==1}">
                                                                <div class="label label-sm label-success">
                                                                    <i class="fa fa-sign-in"></i>
                                                                </div>
                                                            </c:when> 
                                                            <c:when test="${activity.actionTypeId==2}"><div class="label label-sm label-default"><i class="fa fa-sign-out"></i></div></c:when> 
                                                            <c:when test="${activity.actionTypeId==3}"><div class="label label-sm label-primary"><i class="fa fa-download"></i></div></c:when> 
                                                            <c:when test="${activity.actionTypeId==4}"><div class="label label-sm label-success"><i class="fa fa-pencil"></i></div></c:when> 
                                                            <c:when test="${activity.actionTypeId==5}"><div class="label label-sm label-success"><i class="fa fa-plus"></i></div></c:when> 
                                                            <c:when test="${activity.actionTypeId==6}"><div class="label label-sm label-info"><i class="fa fa-pencil"></i></div></c:when> 
                                                            <c:when test="${activity.actionTypeId==7}"><div class="label label-sm label-danger"><i class="fa fa-minus"></i></div></c:when> 
                                                            <c:when test="${activity.actionTypeId==8}"><div class="label label-sm label-danger"><i class="fa fa-bolt"></i></div></c:when> 
                                                            <c:otherwise><div class="label label-sm label-default"><i class="fa fa-exclamation"></i></div></c:otherwise>   
                                                         </c:choose>                                                            
                                                    </div>
                                                    <div class="cont-col2">
                                                        <div class="desc">
                                                            ${activity.actionDetail} on ${activity.actionDate}
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                        </c:forEach>                                        
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-md-6 col-sm-6">
                        <!-- BEGIN PORTLET-->
                        <div class="portlet solid grey-cararra bordered">
                            <div class="portlet-title">
                                <div class="caption">
                                    <i class="fa fa-bullhorn"></i>Activities
                                </div>
                                <div class="tools">
                                    <div class="btn-group pull-right" data-toggle="buttons">
                                        <a href="" class="btn blue btn-sm active">
                                             Users
                                        </a>
                                        <a href="" class="btn blue btn-sm">
                                             Orders
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body">
                                <div id="site_activities_loading">
                                    <img src="assets/img/loading.gif" alt="loading"/>
                                </div>
                                <div id="site_activities_content" class="display-none">
                                    <div id="site_activities" style="height: 300px;">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- END PORTLET-->
                    </div>
                                        
                </div>
                <div class="clearfix">
                </div>
                <div class="row ">
                    
                    <div class="col-md-12 col-sm-12">
                      <div class="tiles">
                            <div class="tile double-down bg-blue-chambray">
                                <div class="tile-body">
                                    <i class="fa fa-cog" style="margin-top:10px; margin-bottom:10px"></i>
                                    <h3>System</h3><br />
                                     Java v.<%= System.getProperty("java.version") %><br />
                                     Tomcat v.<%= application.getServerInfo().substring(14, application.getServerInfo().length()) %><br />
                                     Servlet v.<%= application.getMajorVersion() %>.<%= application.getMinorVersion() %><br />
                                     JSP v.<%= JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion() %><br />
                                     MySQL v.5.5<br />
                                </div>
                            </div>  
                            <div class="tile bg-red">
                                <div class="tile-body">
                                    <i class="fa fa-edit"></i>
                                </div>
                                <div class="tile-object">
                                    <div class="name">
                                         Blogs
                                    </div>
                                    <div class="number">
                                        ${info.blogCount}
                                    </div>
                                </div>
                            </div>
                            <div class="tile bg-yellow">
                                <div class="tile-body">
                                    <i class="fa fa-comments-o"></i>
                                </div>
                                <div class="tile-object">
                                    <div class="name">
                                         Comments
                                    </div>
                                    <div class="number">
                                        ${info.commentCount}
                                    </div>
                                </div>
                            </div>
                            <div class="tile bg-blue">
                                <div class="tile-body">
                                    <i class="fa fa-edit"></i>
                                </div>
                                <div class="tile-object">
                                    <div class="name">
                                         Pages
                                    </div>
                                    <div class="number">
                                        ${info.pageCount}
                                    </div>
                                </div>
                            </div>
                            <div class="tile bg-green">
                                <div class="tile-body">
                                    <i class="fa fa-check-square"></i>
                                </div>
                                <div class="tile-object">
                                    <div class="name">
                                         Forms
                                    </div>
                                    <div class="number">
                                        ${info.formCount}
                                    </div>
                                </div>
                            </div>                            
                            <div class="tile bg-blue-chambray">
                                <div class="tile-body">
                                    <i class="fa fa-random"></i>
                                </div>
                                <div class="tile-object">
                                    <div class="name">
                                         Sliders
                                    </div>
                                    <div class="number">
                                        ${info.sliderCount}
                                    </div>
                                </div>
                            </div>
                            <div class="tile bg-yellow">
                                <div class="tile-body">
                                    <i class="fa fa-barcode"></i>
                                </div>
                                <div class="tile-object">
                                    <div class="name">
                                         Brands
                                    </div>
                                    <div class="number">
                                        ${info.brandCount}
                                    </div>
                                </div>
                            </div>
                            <div class="tile bg-green">
                                <div class="tile-body">
                                    <i class="fa fa-sitemap"></i>
                                </div>
                                <div class="tile-object">
                                    <div class="name">
                                         Categories
                                    </div>
                                    <div class="number">
                                        ${info.categoryCount}
                                    </div>
                                </div>
                            </div>                            
                            <div class="tile bg-purple">
                                <div class="tile-body">
                                    <i class="fa fa-check-square-o"></i>
                                </div>
                                <div class="tile-object">
                                    <div class="name">
                                         Options
                                    </div>
                                    <div class="number">
                                        ${info.optionCount}
                                    </div>
                                </div>
                            </div>
                        </div>  
                    </div>
                </div>                           
                <div class="clearfix">
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
        <script src="../assets/global/plugins/flot/jquery.flot.min.js" type="text/javascript"></script>
        <script src="../assets/global/plugins/flot/jquery.flot.resize.min.js" type="text/javascript"></script>
        <script src="../assets/global/plugins/flot/jquery.flot.categories.min.js" type="text/javascript"></script>
        <script src="../assets/global/plugins/jquery.pulsate.min.js" type="text/javascript"></script>
        <!-- IMPORTANT! fullcalendar depends on jquery-ui-1.10.3.custom.min.js for drag & drop support -->
        <script src="../assets/global/plugins/fullcalendar/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
        <script src="../assets/global/plugins/jquery-easypiechart/jquery.easypiechart.min.js" type="text/javascript"></script>
        <script src="../assets/global/plugins/jquery.sparkline.min.js" type="text/javascript"></script>
    <!-- END PAGE LEVEL PLUGINS -->
    
    <!-- BEGIN PAGE LEVEL SCRIPTS -->
        <script src="../assets/global/scripts/metronic.js" type="text/javascript"></script>
        <script src="../assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
        <script src="../assets/admin/pages/scripts/index.js" type="text/javascript"></script>
        <script src="../assets/admin/pages/scripts/tasks.js" type="text/javascript"></script>
    <!-- END PAGE LEVEL SCRIPTS -->
    
    <script>
        jQuery(document).ready(function() {    
		   Metronic.init(); // init metronic core componets
		   Layout.init(); // init layout
		   Index.init();   
		   Index.initCharts(); // init index page's custom scripts
		   Index.initChat();
		   Index.initMiniCharts();
		   Index.initIntro();
		   Tasks.initDashboardWidget();
        });
    </script>
    
    <!-- END JAVASCRIPTS -->
    </body>
</html>