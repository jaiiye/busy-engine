<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.DatabaseMetaData"%>
<%@ page import="java.text.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@include file="connection.jsp" %> 

<%!
public String generateHeaderCode(String objectName, String instanceName)
{        
    String percent = '%' + "";
    String bet = "<" + percent + ">";
    String bt = bet.substring(0,2);
    String et = bet.substring(1,3);
    String lineBreak = "" + "\n";
    
    String code = "";
    code += bt + "@page import=\"java.text.*\"" + et + lineBreak;
    
    code += bt + "@page import=\"java.util.*\""+ et + lineBreak;
    code += bt + "@page import=\"com.busy.dao.*\"" + et + lineBreak;
    code += bt + "@page import=\"com.transitionsoft.*\""+ et + lineBreak;

    code += bt + "@page contentType=\"text/html; charset=utf-8\""+ et + lineBreak;    
    code += bt + "@taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\""+ et + lineBreak;

    code += bt + lineBreak;
    
    code += "ArrayList<" + objectName + "> " + instanceName + "List = new ArrayList<" + objectName + ">();" + lineBreak;
    code += "if (request.getParameter(\"column\") != null && request.getParameter(\"columnValue\") != null)" + lineBreak;
    code += "{" + lineBreak;
    code += "    " + instanceName + "List = " + objectName + ".getAll" + objectName + "ByColumn(request.getParameter(\"column\"), request.getParameter(\"columnValue\"));" + lineBreak;
    code += "}" + lineBreak;
    code += "else" + lineBreak;
    code += "{" + lineBreak;
    code += "    " + instanceName + "List = " + objectName + ".getAll" + objectName + "();" + lineBreak;
    code += "}" + lineBreak;           
    code += "request.setAttribute(\"" + instanceName + "List\", " + instanceName + "List);" + lineBreak;
    code += "NumberFormat formatter = NumberFormat.getCurrencyInstance();" + lineBreak;
    code += et + lineBreak;
    
    return code; 
}
%>

<%!
public String includeFile(String fileName)
{    

    String percent = '%' + "";
    String bet = "<" + percent + ">";
    String bt = bet.substring(0,2);
    String et = bet.substring(1,3);
    String code = "";
    String lineBreak = "" + "\n";

    code += bt + "@include file=\"" + fileName + "\"" + et + lineBreak;
    return code;  
}
%>

<%!
public String checkParameterStart(String parameterName)
{    
    return "<c:if test=\"${param." + parameterName + " != null}\">";
}
%>                                           
  
<%!
public String checkParameterEnd()
{    
    return "</c:if>";
}
%>                                           

<%!
public String doForeachStart(String listName, String elementName)
{    
    return "<c:forEach var=\"" + elementName + "\" items=\"${" + listName + "}\" >"; 
}
%>                                           
  
<%!
public String doForeachEnd()
{    
    return "</c:forEach>";
}
%>                                           


<%!
public String generateGetterCode(String objectName, String columnName)
{    
    String percent = '%' + "";
    String bet = "<" + percent + ">";
    String bt = bet.substring(0,2);
    String et = bet.substring(1,3);
    String code = "";

    code += bt + "= " + objectName + ".get" + columnName + "() " + et;
    return code;  
}
%>  

 
<%!
public String generateRequestAttributeSetter(String key, String value)
{    
    String percent = '%' + "";
    String bet = "<" + percent + ">";
    String bt = bet.substring(0,2);
    String et = bet.substring(1,3);
    String code = "";

    code += bt + " request.setAttribute(\"" + key + "\", \"" + value + "\"); " + et;
    return code;  
}
%>            
            

<%!
public String generateElCode(String objectName, String columnName)
{    
    return  "${" + objectName + "." + columnName + "}";
}
%>  


<%!
public String generateDisplayConditional(String paramName, String objectNameLowercase, String objectInstanceName)
{              
    return "${param." + paramName + " == null ? \"none\" : " + objectInstanceName + "." + objectNameLowercase + "Id==param." + paramName + " ? \"block\" : \"none\"}";
}
%>  

<%!
public String generateSelectConditional(String paramName, String paramName2, String trueValue, String falseValue)
{              
    return "${" + paramName + " == '" + paramName2 + "' ? \"" + trueValue + "\" : \"" + falseValue + "\" }";
}
%> 

<%!
public String generateElParameter(String parameterName)
{    
    return  "${param." + parameterName + "}";
}
%> 

<%!
public String getInputMarkup(String columnName, String columnNameLowercase, int columnType, int columnSize, String columnDefaultValue)
{        
    String percent = '%' + "";
    String bet = "<" + percent + ">";
    String bt = bet.substring(0,2);
    String et = bet.substring(1,3);
    
    String inputHtmlMarkup = "";
    switch(columnType)
    {
        case java.sql.Types.SMALLINT:
        case java.sql.Types.TINYINT:
        case java.sql.Types.INTEGER:
            if(columnName.contains("Id")) 
            {
                inputHtmlMarkup = "<select name=\"" + columnNameLowercase + "\" class=\"form-control\">" + "\n";
                inputHtmlMarkup += "                                                                    " +  bt  + "= Database.generateSelectOptionsFromTableAndColumn(\"table_name:" +  columnName.replace("Id", "") + "\", \"\", 2)" + et + "\n";
                inputHtmlMarkup += "                                                               </select>";                                                        
            }
            else
            {
                inputHtmlMarkup = "<input type=\"text\" name=\"" + columnNameLowercase + "\" class=\"form-control\" placeholder=\"Enter Integer\" />";
            }            
            break;                
        case java.sql.Types.BOOLEAN:
        case java.sql.Types.BIT:
            inputHtmlMarkup = "<input type=\"text\" name=\"" + columnNameLowercase + "\" class=\"form-control\" />";
            break;
        case java.sql.Types.DATE:
        case java.sql.Types.TIMESTAMP:
            inputHtmlMarkup = "<input type=\"text\" name=\"" + columnNameLowercase + "\" class=\"form-control\" id=\"mask_date2\" />";
            break;
        case java.sql.Types.DECIMAL:
        case java.sql.Types.DOUBLE:
            inputHtmlMarkup = "<input type=\"text\" name=\"" + columnNameLowercase + "\" class=\"form-control\" placeholder=\"Enter Number(ex: 2.50)\" />";
            break;
        default:
            if(columnSize > 255){
                inputHtmlMarkup = "<textarea name=\"" + columnNameLowercase + "\" class=\"form-control maxlength-handler\" placeholder=\"Enter Text\" maxlength=\"" + columnSize + "\" rows=\"3\"></textarea>";
            } else {
                inputHtmlMarkup = "<input type=\"text\" name=\"" + columnNameLowercase + "\" class=\"form-control maxlength-handler\" placeholder=\"Enter Text\" maxlength=\"" + columnSize + "\" />";
            }
            
            break;
    }
    return inputHtmlMarkup; 

}
%>

<%!
public String getInputWithValueMarkup(String columnName, String columnNameLowercase, int columnType, int columnSize, String tableNameLowercase,int index, String objectName)
{    
    String code = "${" + tableNameLowercase + "." + columnNameLowercase + "}";
    
    String percent = '%' + "";
    String bet = "<" + percent + ">";
    String bt = bet.substring(0,2);
    String et = bet.substring(1,3);
    
    String inputHtmlMarkup = "";
    switch(columnType)
    {
        case java.sql.Types.SMALLINT:
        case java.sql.Types.TINYINT:
        case java.sql.Types.INTEGER:            
            if(columnName.contains("Id") && index == 0) 
            {
                inputHtmlMarkup = "<input type=\"text\" name=\"" + columnNameLowercase + "\" class=\"form-control\" value=\"" + code + "\" />" + "\n";
            }
            else if(columnName.contains("Id") && index > 0) 
            {                
                inputHtmlMarkup = "<input type=\"text\" name=\"" + columnNameLowercase + "\" class=\"form-control\" value=\"" + code + "\" />" + "\n";
                inputHtmlMarkup += "                                                        <select name=\"" + columnNameLowercase + "\" class=\"form-control\">"+ "\n";
                inputHtmlMarkup += "                                                            " +  bt  + objectName + " x = (" + objectName + ") pageContext.getAttribute(\"" + tableNameLowercase + "\"); " + et + "\n";
                inputHtmlMarkup += "                                                            " +  bt  + "= Database.generateSelectOptionsFromTableAndColumn(\"table_name:" +  columnName.replace("Id", "") + "\", x.get" + columnNameLowercase + "().toString(), 2)" + et + "\n";
                inputHtmlMarkup += "                                                        </select>";                                                        
            }
            else
            {
                inputHtmlMarkup = "<input type=\"text\" name=\"" + columnNameLowercase + "\" class=\"form-control\" value=\"" + code + "\" />";
            } 
            break;                
        case java.sql.Types.BOOLEAN:
        case java.sql.Types.BIT:
            inputHtmlMarkup = "<input type=\"text\" name=\"" + columnNameLowercase + "\" class=\"form-control\" value=\"" + code + "\" />";
            break;
        case java.sql.Types.DATE:                    
            inputHtmlMarkup = "<input type=\"text\" name=\"" + columnNameLowercase + "\" class=\"form-control\" id=\"mask_date2\" value=\"" + code + "\" />";            
            break;
        case java.sql.Types.TIMESTAMP:
            //inputHtmlMarkup = "<input type=\"text\" name=\"" + columnNameLowercase + "\" class=\"form-control maxlength-handler\" maxlength=\"11\" value=\"" + code + "\" />";
            inputHtmlMarkup += "<div class=\"input-group date form_datetime\" data-date=\"2012-12-21T15:25:00Z\">";
            inputHtmlMarkup += "        <input type=\"text\" name=\"" + columnNameLowercase + "\" value=\"" + code + "\" class=\"form-control\">";
            inputHtmlMarkup += "        <span class=\"input-group-btn\">";
            inputHtmlMarkup += "                <button class=\"btn default date-reset\" type=\"button\"><i class=\"fa fa-times\"></i></button>";
            inputHtmlMarkup += "        </span>";
            inputHtmlMarkup += "        <span class=\"input-group-btn\">";
            inputHtmlMarkup += "                <button class=\"btn default date-set\" type=\"button\"><i class=\"fa fa-calendar\"></i></button>";
            inputHtmlMarkup += "        </span>";
            inputHtmlMarkup += "</div>";
            break;
        case java.sql.Types.DECIMAL:
        case java.sql.Types.DOUBLE:
            inputHtmlMarkup = "<input type=\"text\" name=\"" + columnNameLowercase + "\" class=\"form-control\" value=\"" + code + "\" />";
            break;
        default:
            if(columnSize > 255){
                inputHtmlMarkup = "<textarea name=\"" + columnNameLowercase + "\" class=\"ckeditor form-control\" rows=\"4\">" + code + "</textarea>";
            } else {
                inputHtmlMarkup = "<input type=\"text\" name=\"" + columnNameLowercase + "\" class=\"form-control maxlength-handler\" maxlength=\"" + columnSize + "\" value=\"" + code + "\" />";
            }
            
            break;
    }
    return inputHtmlMarkup; 

}
%>

<%!
public String getValidationMarkup(String columnName, String columnNameLowercase, int columnType, int columnSize)
{    
    String validationJs = "";
    switch(columnType)
    {
        case java.sql.Types.SMALLINT:
        case java.sql.Types.TINYINT:
        case java.sql.Types.INTEGER:
            //<input type=\"text\" name=\"" + columnNameLowercase + "\" class=\"form-control\" placeholder=\"Enter Integer\" />";
            validationJs = "" + columnNameLowercase + ":    { required: true, number: true }";
            break;                
        case java.sql.Types.BOOLEAN:
        case java.sql.Types.BIT:
            //<input type=\"checkbox\" name=\"" + columnNameLowercase + "\" class=\"form-control\" />";
            validationJs = "" + columnNameLowercase + ":    { required: true }";
            break;
        case java.sql.Types.DATE:
        case java.sql.Types.TIMESTAMP:
            //<input type=\"text\" name=\"" + columnNameLowercase + "\" class=\"form-control maxlength-handler\" placeholder=\"Enter Date\" maxlength=\"11\" />";
            validationJs = "" + columnNameLowercase + ":    { required: true }";
            break;
        case java.sql.Types.DECIMAL:
        case java.sql.Types.DOUBLE:
            //<input type=\"text\" name=\"" + columnNameLowercase + "\" class=\"form-control\" placeholder=\"Enter Number(ex: 2.50)\" />";
            validationJs = "" + columnNameLowercase + ":    { required: true, digits: true }";
            break;
        default:
            //<textarea name=\"" + columnNameLowercase + "\" class=\"form-control maxlength-handler\" placeholder=\"Enter Text\" maxlength=\"" + columnSize + "\" rows=\"3\"></textarea>";
            //<input type=\"text\" name=\"" + columnNameLowercase + "\" class=\"form-control maxlength-handler\" placeholder=\"Enter Integer\" maxlength=\"" + columnSize + "\" />";
            validationJs = "" + columnNameLowercase + ":    { required: true, minlength: 1, maxlength: " + columnSize + "}";
            
            break;
    }
    return validationJs; 

}
%>

<%!
public String getColumnDataType(int type)
{
    String columnDataType = "String";
    switch(type)
    {
        case java.sql.Types.INTEGER:
            columnDataType = "Integer";
            break;                
        case java.sql.Types.BOOLEAN:
            columnDataType = "Boolean";
            break;
        case java.sql.Types.BIT:
            columnDataType = "Boolean";
            break;
        case java.sql.Types.DATE:
            columnDataType = "Date";
            break;
        case java.sql.Types.DECIMAL:
            columnDataType = "Double";
            break;
        case java.sql.Types.SMALLINT:
            columnDataType = "Integer";
            break;
        case java.sql.Types.TINYINT:
            columnDataType = "Integer";
            break;
        case java.sql.Types.DOUBLE:
            columnDataType = "Double";
            break;
        case java.sql.Types.TIMESTAMP:
            columnDataType = "Date";
            break;
        default:
            columnDataType = "String";
            break;
    }
    return columnDataType;     
}

%>

<% 
    String tableNameLowercase = request.getParameter("table"); 
    String tableNameUppercase = request.getParameter("table").substring(0, 1).toUpperCase() + request.getParameter("table").substring(1, request.getParameter("table").length());
    String objectName = tableNameUppercase.replace("_a", "A").replace("_b", "B").replace("_c", "C").replace("_d", "D").replace("_e", "E").replace("_f", "F").replace("_g", "G").replace("_h", "H").replace("_i", "I").replace("_j", "J").replace("_k", "K").replace("_l", "L").replace("_m", "M").replace("_n", "N").replace("_o", "O").replace("_p", "P").replace("_q", "Q").replace("_r", "R").replace("_s", "S").replace("_t", "T").replace("_u", "U").replace("_v", "V").replace("_w", "W").replace("_x", "X").replace("_y", "Y").replace("_z", "Z");
    objectName = objectName.replace("_a", "A").replace("_b", "B").replace("_c", "C").replace("_d", "D").replace("_e", "E").replace("_f", "F").replace("_g", "G").replace("_h", "H").replace("_i", "I").replace("_j", "J").replace("_k", "K").replace("_l", "L").replace("_m", "M").replace("_n", "N").replace("_o", "O").replace("_p", "P").replace("_q", "Q").replace("_r", "R").replace("_s", "S").replace("_t", "T").replace("_u", "U").replace("_v", "V").replace("_w", "W").replace("_x", "X").replace("_y", "Y").replace("_z", "Z");
    String objectNameLowercase = objectName.substring(0,1).toLowerCase() + objectName.substring(1,objectName.length());

    DatabaseMetaData databaseMetaData = connection.getMetaData();
    String   catalog          = null;
    String   schemaPattern    = null;
    String   tableNamePattern = tableNameLowercase;
    String[] types            = null;
    String   columnNamePattern = null;

    rs = databaseMetaData.getColumns(catalog, schemaPattern,  tableNamePattern, columnNamePattern);

    ArrayList<String> columns = new ArrayList<String>();    
    ArrayList<Integer> columnTypes = new ArrayList<Integer>();
    ArrayList<Integer> columnSizes = new ArrayList<Integer>();
    ArrayList<String> columnDefaultValues = new ArrayList<String>();
    
    while(rs.next())
    {
        columns.add(rs.getString(4));
        columnTypes.add(rs.getInt(5));  
        columnSizes.add(rs.getInt("COLUMN_SIZE"));
        columnDefaultValues.add(rs.getString("COLUMN_DEF"));
    }
    
    int numColumns = columns.size(); 
    
    
%>
       


<%= generateHeaderCode(objectName, tableNameLowercase) %>

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

        <%= includeFile("index_global_styles.jsp") %>

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

        <%= includeFile("index_global_scripts.jsp") %>
        
	<script type="text/javascript" src="../uploadify/jquery.uploadify3.2.min.js"></script> 

        <link rel="shortcut icon" href="favicon.ico"/>
    </head>

    <body class="page-header-fixed page-footer-fixed">

        <%= includeFile("index_header.jsp") %> 

        <div class="clearfix"></div>
        <!-- BEGIN CONTAINER -->
        <div class="page-container">

        <%= generateRequestAttributeSetter("category", "E-Commerce") %>
        <%= generateRequestAttributeSetter("subCategory", objectName) %>
        <%= includeFile("index_sidebar.jsp") %>

            <!-- BEGIN CONTENT -->
            <div class="page-content-wrapper">
    
                    <div class="page-content">

                        <!-- BEGIN PAGE HEADER-->
                        <div class="row">
                            <div class="col-md-12">
                                <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                                <h3 class="page-title"> <%= objectName %> </h3>
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
                                        <a href="#"><%= objectName %></a>
                                    </li>
                                </ul>
                                <!-- END PAGE TITLE & BREADCRUMB-->
                                
                                <!-- BEGIN PAGE NOTIFICATIONS -->
                                <%= checkParameterStart("SuccessMsg") %>
                                    <div class="alert alert-success alert-dismissable">
                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
                                        <%= generateElParameter("SuccessMsg") %>
                                    </div>				
                                <%= checkParameterEnd() %>
                                <%= checkParameterStart("ErrorMsg") %>
                                    <div class="alert alert-danger alert-dismissable">
                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
                                        <%= generateElParameter("ErrorMsg") %>
                                    </div>		
                                <%= checkParameterEnd() %>
                                
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
                                        <form method="post" action="<%= objectName %>UI.jsp" class="form-horizontal" role="form">
                                        	<div class="form-body">
                                                <div class="form-group">
                                                    <div class="col-md-4">
                                                        <select name="column" class="form-control">
                                                            <%
                                                            for(int index = 0; index < columns.size(); index++)
                                                            {
                                                                String column = columns.get(index);            
                                                                String columnNameLowercase = column.substring(0, 1).toLowerCase() + column.substring(1, column.length());                                                
                                                            %><option value="<%= column %>" <%= generateSelectConditional("param.column", column, "selected", "")%> ><%= column %></option>                                                            
                                                           <% } %>                                                                                                                       
                                                        </select> 
                                                    </div>                                                         
                                                    <div class="col-md-5">
                                                        <input type="text" name="columnValue" class="form-control"/>
                                                    </div>
                                                    <div class="col-md-3">
	                                                <button type="submit" class="btn grey-silver">Filter</button>
                                                        <button type="button" class="btn grey-cascade" style="float:right" onclick="javascript:window.location = '<%= objectName %>UI.jsp';"><i class="fa fa-refresh"></i></button>
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
                                  
                        <%= doForeachStart(tableNameLowercase + "List", tableNameLowercase) %>
                        <div class="row" id="itemBox<%= generateElCode(tableNameLowercase,objectNameLowercase + "Id") %>" style="display:<%= generateDisplayConditional("id", objectNameLowercase, tableNameLowercase) %>">                      
                            <div class="col-md-12">
                                <div class="portlet box green-seagreen">
                                    <div class="portlet-title">
                                        <div class="caption">Record Details</div>
                                        <div class="actions">                                                 
                                            <div class="btn-group">                                
                                                <a href="javascript:toggleVisibility('itemBox<%= generateElCode(tableNameLowercase,objectNameLowercase + "Id") %>');" class="btn btn-default"><i class="fa fa-times"></i>&nbsp;Close</a> 
                                            </div>
                                        </div>
                                    </div>
                                    <div class="portlet-body">	
                                        <div class="portlet-body form">
                                            <form class="form-horizontal" name="edit" action="../Operations?form=<%= tableNameLowercase %>&action=2" method="post">

                                                <%
                                                for(int index = 0; index < columns.size(); index++)
                                                {
                                                    String column = columns.get(index);            
                                                    String columnNameLowercase = column.substring(0, 1).toLowerCase() + column.substring(1, column.length());                                                
                                                %>
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label" for="<%= columnNameLowercase %>"><%= column %>:</label>
                                                    <div  class="col-md-10">
                                                        <%= getInputWithValueMarkup(column, columnNameLowercase, columnTypes.get(index), columnSizes.get(index), tableNameLowercase, index, objectName) %>
                                                    </div>
                                                </div>
                                                <% } %>

                                                <div class="form-actions right">
                                                    <input type="submit" value="Save Changes" class="btn green" />
                                                </div>
                                            </form>
                                        </div> 
                                    </div>
                                </div> 
                            </div>
                        </div>

                        
                        <%= doForeachEnd() %>
                        <!-- END RECORD DETAILS-->


                        <!-- BEGIN MODAL NEW <%= objectName %> FORM-->                    
                        <div id="myModal_new_record" class="modal fade" role="dialog" aria-hidden="true">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                        <h4 class="modal-title">Add a new <%= objectName %></h4>
                                    </div>
                                    <div class="modal-body form">
                                        <!-- BEGIN FORM-->
                                        <form method="post" action="../Operations?form=<%= tableNameLowercase %>&action=1" id="create_form" class="horizontal-form">
                                            <div class="form-body">
                                                <div class="alert alert-danger display-hide">
                                                    <button class="close" data-close="alert"></button>
                                                    You have some form errors. Please check below.
                                                </div>
                                                <div class="alert alert-success display-hide">
                                                    <button class="close" data-close="alert"></button>
                                                    Your form validation is successful!
                                                </div>

                                                <%
                                                for(int index = 1; index < columns.size(); index++)
                                                {
                                                    String column = columns.get(index);            
                                                    String columnNameLowercase = column.substring(0, 1).toLowerCase() + column.substring(1, column.length());                                                
                                                %>
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label class="col-md-2 control-label"><%= column %></label>
                                                        <div class="col-md-10" style="margin-bottom:25px;">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <%= getInputMarkup(column, columnNameLowercase, columnTypes.get(index), columnSizes.get(index),columnDefaultValues.get(index)) %>                                                            
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <% } %>

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

                        <!-- END MODAL NEW <%= objectName %> FORM-->


                        <!-- BEGIN DATA TABLE--> 
                        <div class="row">
                            <div class="col-md-12">
                                 <div class="portlet box red-flamingo">
                                    <div class="portlet-title">
                                        <div class="caption">
                                            <i class="fa fa-list-alt"></i><%= objectName %> Listing
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
                                                    <%
                                                    int lastIndex = 0;
                                                    for(int index = 0; index < columns.size(); index++)
                                                    {
                                                        String column = columns.get(index);            
                                                        String columnNameLowercase = column.substring(0, 1).toLowerCase() + column.substring(1, column.length()); 
                                                        lastIndex = index;
                                                    %><label><input type="checkbox" checked data-column="<%= index%>"><%= column.replaceAll(objectName,"") %></label> 
                                                    <% } %>
                                                    <label><input type="checkbox" checked data-column="<%= lastIndex+1 %>">Actions</label>
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
                                                    <%
                                                    for(int index = 0; index < columns.size(); index++)
                                                    {
                                                        String column = columns.get(index);            
                                                        String columnNameLowercase = column.substring(0, 1).toLowerCase() + column.substring(1, column.length());                                                
                                                    %><th><%= column.replaceAll(objectName,"") %></th> 
                                                    <% } %>                                                    
                                                    <th>Actions</th> 
                                                </tr>                                
                                            </thead>
                                            <tbody>                                                
                                                <%= doForeachStart(tableNameLowercase + "List", tableNameLowercase) %>
                                                <tr>                                                    
                                                    <%
                                                    for(int index = 0; index < columns.size(); index++)
                                                    {
                                                        String column = columns.get(index);            
                                                        String columnNameLowercase = column.substring(0, 1).toLowerCase() + column.substring(1, column.length());                                                
                                                    %><td><%= generateElCode(tableNameLowercase,columnNameLowercase) %></td> 
                                                    <% } %>
                                                    <td>
                                                        <button id="edit-item<%= generateElCode(tableNameLowercase,objectNameLowercase + "Id") %>" class="btn btn-sm green filter-submit margin-bottom"><span class="glyphicon glyphicon-pencil"></span></button>&nbsp;
                                                        <button id="delete-item<%= generateElCode(tableNameLowercase,objectNameLowercase + "Id") %>" class="btn btn-sm red filter-cancel"><span class="glyphicon glyphicon-trash"></span></button> 
                                                    </td>
                                                </tr>  
                                                <script type="text/javascript">
                                                    $("#edit-item<%= generateElCode(tableNameLowercase,objectNameLowercase + "Id") %>").button().click(function() {
                                                        toggleVisibility('itemBox<%= generateElCode(tableNameLowercase,objectNameLowercase + "Id") %>');                                                        
                                                        document.getElementById('itemBox<%= generateElCode(tableNameLowercase,objectNameLowercase + "Id") %>').scrollIntoView();
                                                        window.scrollBy(0,-80);
                                                    });
                                                    $("#delete-item<%= generateElCode(tableNameLowercase,objectNameLowercase + "Id") %>").button().click(function() {
                                                        window.location = '../Operations?form=<%= tableNameLowercase %>&action=3&id=<%= generateElCode(tableNameLowercase,objectNameLowercase + "Id") %>';
                                                    });
                                                </script>
                                                <%= doForeachEnd() %>
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
                                        
                                        <button onClick="window.location ='../Operations?form=<%= tableNameLowercase %>&action=4';" type="button" data-dismiss="modal" class="btn green">Yes</button>
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
        
        <%= includeFile("index_footer.jsp") %>

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

                $("#mask_date2").inputmask("y/m/d", {
                    "placeholder": "yyyy/mm/dd"
                }); //multi-char placeholder

                //DateTime Picker initializer code
                $(".form_datetime").datetimepicker({
                    autoclose: true,
                    isRTL: Metronic.isRTL(),
                    todayBtn: true,
                    format: "dd MM yyyy - hh:ii",
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
                        <%
                        for(int index = 0; index < columns.size(); index++)
                        {
                                String column = columns.get(index);            
                                String columnNameLowercase = column.substring(0, 1).toLowerCase() + column.substring(1, column.length());                                                
                        %><%= getValidationMarkup(column, columnNameLowercase, columnTypes.get(index), columnSizes.get(index)) %><%= index == (columns.size()-1) ? "" : "," %> 
                        <% } %>
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

<%
    connection.close();
    statement.close();
%>