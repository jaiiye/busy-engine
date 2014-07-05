<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>

<%
    String category = (String)request.getAttribute("category");
    String subCategory = (String)request.getAttribute("subCategory");
%>

<!-- BEGIN SIdEBAR -->
<div class="page-sidebar-wrapper">
    <div class="page-sidebar navbar-collapse collapse">
        <!-- add "navbar-no-scroll" class to disable the scrolling of the sidebar menu -->
        <!-- BEGIN SIdEBAR MENU -->
        <ul class="page-sidebar-menu" data-auto-scroll="false" data-slide-speed="200">
            <li class="sidebar-toggler-wrapper">
                <!-- BEGIN SIdEBAR TOGGLER BUTTON -->
                <div class="sidebar-toggler hidden-phone"></div>
                <!-- BEGIN SIdEBAR TOGGLER BUTTON -->
            </li>
            <li <%= category.equals("dashboard") ?  "class=\"start active\"" : ""  %>>
                <a href="index.jsp"><i class="fa fa-dashboard"></i><span class="title">Dashboard</span><span <%= category.equals("dashboard") ?  "class=\"selected\"" : ""  %>></span></a>
            </li>
            <li <%= category.equals("E-Commerce") ?  "class=\"start active\"" : ""  %>>
                <a href="javascript:;">
                    <i class="fa fa-shopping-cart"></i>
                    <span class="title">E-Commerce</span>
                    <%= category.equals("E-Commerce") ?  "<span class=\"selected\" ></span>" : ""  %>
                    <span class="arrow <%= category.equals("E-Commerce") ?  "open" : ""  %>"></span>
                </a>
                <ul class="sub-menu">
                    <li <%= subCategory.equals("products") ? "class=\"active\"":"" %>><a href="products.jsp"><i class="fa fa-gift"></i>&nbsp;Products</a></li>
                    <li <%= subCategory.equals("orders") ? "class=\"active\"":"" %>><a href="OrderUI.jsp"><i class="fa fa-money"></i>&nbsp;Orders</a></li>
                    <li <%= subCategory.equals("brands") ? "class=\"active\"":"" %>><a href="ItemBrandUI.jsp"><i class="fa fa-tag"></i>&nbsp;Brands</a></li>
                    <li <%= subCategory.equals("categories") ? "class=\"active\"":"" %>><a href="CategoryUI.jsp"> <i class="fa fa-archive"></i>&nbsp;Categories</a></li>
                    <li <%= subCategory.equals("options") ? "class=\"active\"":"" %>><a href="ItemOptionUI.jsp"><i class="fa fa-exchange"></i>&nbsp;Options</a></li>
                    <li <%= subCategory.equals("tax") ? "class=\"active\"":"" %>><a href="TaxUI.jsp"><i class="fa fa-usd"></i>&nbsp;Tax</a></li>
                    <li <%= subCategory.equals("shipping") ? "class=\"active\"":"" %>> <a href="ShippingUI.jsp"><i class="fa fa-truck"></i>&nbsp;Shipping</a></li>
                </ul>
            </li>
            <li <%= category.equals("content") ?  "class=\"start active\"" : ""  %>>
                <a href="javascript:;">
                    <i class="fa fa-book"></i>
                    <span class="title">Content</span>                    
                    <%= category.equals("content") ?  "<span class=\"selected\" ></span>" : ""  %>
                    <span class="arrow <%= category.equals("content") ?  "open" : ""  %>"></span>
                </a>
                <ul class="sub-menu">
                    <li <%= subCategory.equals("blogs") ? "class=\"active\"":"" %>><a href="BlogUI.jsp"><i class="fa fa-comments-o"></i><span class="title">&nbsp;Blogs</span></a></li>
                    <li <%= subCategory.equals("pages") ? "class=\"active\"":"" %>><a href="InfoUI.jsp"><i class="fa fa-file-text-o"></i><span class="title">&nbsp;Pages</span></a></li>
                    <li <%= subCategory.equals("sliders") ? "class=\"active\"":"" %>><a href="SliderUI.jsp"><i class="fa fa-chain"></i><span class="title">&nbsp;Sliders</span></a> </li>
                    <li <%= subCategory.equals("forms") ? "class=\"active\"":"" %>><a href="FormUI.jsp"><i class="fa fa-italic"></i><span class="title">&nbsp;Forms</span></a></li>
                    <li <%= subCategory.equals("images") ? "class=\"active\"":"" %>><a href="ImageUI.jsp"><i class="fa fa-picture-o"></i><span class="title">&nbsp;Images</span></a></li>
                    <li <%= subCategory.equals("files") ? "class=\"active\"":"" %>><a href="SiteFileUI.jsp"><i class="fa fa-file-o"></i><span class="title">&nbsp;Files</span></a></li>
                    <li <%= subCategory.equals("folders") ? "class=\"active\"":"" %>><a href="SiteFolderUI.jsp"><i class="fa fa-folder-o"></i><span class="title">&nbsp;Folders</span></a></li>
                    <li <%= subCategory.equals("mailinglist") ? "class=\"active\"":"" %>> <a href="MailinglistUI.jsp"><i class="fa fa-bookmark"></i><span class="title">&nbsp;Newsletter</span></a></li>
                </ul>
            </li>
            <li <%= category.equals("templates") ?  "class=\"start active\"" : ""  %>>
                <a href="javascript:;">
                    <i class="fa fa-eye"></i>
                    <span class="title">Templates</span>                                        
                    <%= category.equals("templates") ?  "<span class=\"selected\" ></span>" : ""  %>
                    <span class="arrow <%= category.equals("templates") ?  "open" : ""  %>"></span>
                </a>
                <ul class="sub-menu">
                    <li <%= subCategory.equals("pageTemplates") ? "class=\"active\"":"" %>><a href="PageTemplateUI.jsp"><i class="fa fa-file"></i><span class="title">&nbsp;Page Templates</span></a></li>
                    <li <%= subCategory.equals("emailTemplates") ? "class=\"active\"":"" %>><a href="TemplateUI.jsp"><i class="fa fa-envelope"></i><span class="title">&nbsp;Email Templates</span></a></li>
                    <li <%= subCategory.equals("siteTemplates") ? "class=\"active\"":"" %>><a href="SiteTemplateUI.jsp"><i class="fa fa-sitemap"></i><span class="title">&nbsp;Site Templates</span></a></li>
                </ul>
            </li>
            <li <%= category.equals("users") ?  "class=\"start active\"" : ""  %>>
                <a href="javascript:;">
                    <i class="fa fa-users"></i>
                    <span class="title">Users</span>                                                        
                    <%= category.equals("users") ?  "<span class=\"selected\" ></span>" : ""  %>
                    <span class="arrow <%= category.equals("users") ?  "open" : ""  %>"></span>
                </a>
                <ul class="sub-menu">
                    <li <%= subCategory.equals("users") ? "class=\"active\"":"" %>><a href="UserUI.jsp"><i class="fa fa-user"></i><span class="badge badge-roundless badge-warning">new</span><span class="title">&nbsp;User Manager</span></a></li>
                </ul>
            </li>
            <li <%= category.equals("configuration") ?  "class=\"start active\"" : ""  %>>
                <a href="javascript:;">
                    <i class="fa fa-cogs"></i>
                    <span class="title">Configuration</span>              
                    <%= category.equals("configuration") ?  "<span class=\"selected\" ></span>" : ""  %>
                    <span class="arrow <%= category.equals("configuration") ?  "open" : ""  %>"></span>
                </a>
                <ul class="sub-menu">
                    <li <%= subCategory.equals("preferences") ? "class=\"active\"":"" %>><a href="Preferences2.jsp"><i class="fa fa-cog"></i><span class="title">&nbsp;Preferences</span></a></li>
                    <li <%= subCategory.equals("languages") ? "class=\"active\"":"" %>><a href="TextStringUI.jsp"><i class="fa fa-flag-o"></i><span class="title">&nbsp;Languages</span></a></li>
                    <li <%= subCategory.equals("attributes") ? "class=\"active\"":"" %>><a href="SiteAttributeUI.jsp"><i class="fa fa-pencil"></i><span class="title">&nbsp;Attributes</span></a></li>
                </ul>
            </li>                    
        </ul>
        <!-- END SIdEBAR MENU -->
    </div>
</div>
<!-- END SIdEBAR -->