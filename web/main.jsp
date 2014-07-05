<!DOCTYPE html> 
<html>
    <head>    
        <title>BusyTechnologies - Customizable Responsive Website</title>	    
        <meta charset="UTF-8">
        <meta name="description" content="">
        <meta name="keywords" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
        <%@include file="main_includes.jsp" %>
    </head>
    
    <body class="home">	
        <div class="root">	
            <%@include file="main_header.jsp" %>
            
            <% request.setAttribute("sliderId", "1"); %> 	        
            <%@include file="main_slider.jsp" %>        
            
            <section class="content"> 
            Abcd   
            </section>    
            <%@include file="main_footer.jsp" %>    
        </div>
    
        <%@include file="main_scripts.jsp" %>
    </body>
</html>

