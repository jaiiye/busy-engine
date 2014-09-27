<%@page import="java.util.*" %>
<%@page import="com.busy.engine.entity.*" %>
<%@page import="com.busy.engine.data.*" %>
<%@page import="static com.busy.engine.web.SecurityHelper.getSessionUser" %>
<%@page import="static com.busy.engine.web.SecurityHelper.setSessionUser" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html; charset=utf-8" language="java" %>

<%
    String userName = request.getUserPrincipal().getName();
    com.busy.engine.entity.User u = getSessionUser(request);	
    String siteUrl =  Database.getMainSiteURL();

    if(u == null)
    {   
        if(userName == null)
        {
            //user is not logged in
            System.out.println("User is not logged in");
            response.sendRedirect("../login.jsp");            
        }
        
        //find out who the logged-on user is
        u = Database.getUser(userName); 
        
        //user info is not saved in session
        setSessionUser(request, u);
        
        //record the login date and time
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String loginTime = sdf.format(new java.util.Date(session.getCreationTime()));

        Database.RecordUserLoginAction(u.getUserId().toString(), u.getUsername(), loginTime, "Site Administration");
    }
%>

<!-- BEGIN HEADER -->
<div class="page-header navbar navbar-fixed-top">
    <!-- BEGIN TOP NAVIGATION BAR -->
    <div class="header-inner">
    
        <!-- BEGIN LOGO -->
        <a class="navbar-brand" href="index.jsp">
            <img src="../assets/admin/layout/img/logo.png" alt="logo" class="img-responsive"/>
        </a>
        <!-- END LOGO -->
    
        <!-- BEGIN RESPONSIVE MENU TOGGLER -->
        <a href="javascript:;" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <img src="../assets/admin/layout/img/menu-toggler.png" alt=""/>
        </a>
        <!-- END RESPONSIVE MENU TOGGLER -->
    
        <!-- BEGIN TOP NAVIGATION MENU -->
        <div class="top-menu">
            <ul class="nav navbar-nav pull-right">
                
                <!-- BEGIN USER LOGIN DROPDOWN -->
                <li class="dropdown dropdown-user">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                        <img alt="" src="../images/<%= u.getImageUrl() %>" width="32px" height="32px"/>
                        <span class="username"><%= u.getUsername() %></span>
                        <i class="fa fa-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="UserProfile.jsp?id=<%= u.getUserId() %>">
                                <i class="fa fa-user"></i> My Profile
                                
                            </a>
                        </li>
                        <li>
                            <a href="<%= u.getWebUrl() %>" target="_blank">
                                <i class="fa fa-link"></i> Web Link
                                
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="../index.jsp">
                                <i class="fa fa-sign-in"></i> Main Site
                            </a>
                        </li>
                        <li>
                            <a href="../members/index.jsp">
                                <i class="fa fa-sign-in"></i> Members Area
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="../LogOut?userId=<%= u.getUserId()%>&userName=<%= u.getUsername()%>&area=Site%20Administration">
                                <i class="fa fa-sign-out"></i> Log Out
                            </a>
                        </li>
                    </ul>
                </li>
                <!-- END USER LOGIN DROPDOWN -->
            </ul>
        </div>
        <!-- END TOP NAVIGATION MENU -->
    </div>
    <!-- END TOP NAVIGATION BAR -->
</div>
<!-- END HEADER -->
