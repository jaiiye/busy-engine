
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.transitionsoft.*"%>
<%@ page import="com.transitionsoft.dao.*"%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<%@include file="connection.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
            
        <style>
		/* CSS Document */
			html, body, h1, form, fieldset, input {
			  margin: 0;
			  padding: 0;
			  border: none;
			}
			
			body { 
				font-family: Helvetica, Arial, sans-serif; font-size: 12px;
			}
		
			#registration {
				color: #fff;	
				background: rgb(181,189,200); /* Old browsers */
				background: -moz-linear-gradient(top, rgba(181,189,200,1) 0%, rgba(130,140,149,1) 36%, rgba(40,52,59,1) 100%); /* FF3.6+ */
				background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(181,189,200,1)), color-stop(36%,rgba(130,140,149,1)), color-stop(100%,rgba(40,52,59,1))); /* Chrome,Safari4+ */
				background: -webkit-linear-gradient(top, rgba(181,189,200,1) 0%,rgba(130,140,149,1) 36%,rgba(40,52,59,1) 100%); /* Chrome10+,Safari5.1+ */
				background: -o-linear-gradient(top, rgba(181,189,200,1) 0%,rgba(130,140,149,1) 36%,rgba(40,52,59,1) 100%); /* Opera 11.10+ */
				background: -ms-linear-gradient(top, rgba(181,189,200,1) 0%,rgba(130,140,149,1) 36%,rgba(40,52,59,1) 100%); /* IE10+ */
				background: linear-gradient(to bottom, rgba(181,189,200,1) 0%,rgba(130,140,149,1) 36%,rgba(40,52,59,1) 100%); /* W3C */
				filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#b5bdc8', endColorstr='#28343b',GradientType=0 ); /* IE6-9 */
				-moz-border-radius: 10px;
				-webkit-border-radius: 10px;
				border-radius: 10px;
				width: 430px;
				margin-top: 10px;
				margin-left: auto;
				margin-right: auto; 
			}
		
			#registration a {
				color: #8c910b;
				text-shadow: 0px -1px 0px #000;
			}
			  
			#registration fieldset {
				padding: 20px;
			}
			  
			input.text {
				  -webkit-border-radius: 15px;
				  -moz-border-radius: 15px;
				  border-radius: 15px;
				  border:solid 1px #444;
				  font-size: 14px;
				  width: 90%;
				  padding: 7px 8px 7px 30px;
				  -moz-box-shadow: 0px 1px 0px #777;
				  -webkit-box-shadow: 0px 1px 0px #777;
				  background: #ddd url('images/inputSprite.png') no-repeat 4px 5px;
				  background: url('images/inputSprite.png') no-repeat 4px 5px, -moz-linear-gradient(
					   center bottom,
					   rgb(225,225,225) 0%,
					   rgb(215,215,215) 54%,
					   rgb(173,173,173) 100%
					   );
				  background:  url('images/inputSprite.png') no-repeat 4px 5px, -webkit-gradient(
					  linear,
					  left bottom,
					  left top,
					  color-stop(0, rgb(225,225,225)),
					  color-stop(0.54, rgb(215,215,215)),
					  color-stop(1, rgb(173,173,173))
					  );
				  color:#333;
				  text-shadow:0px 1px 0px #FFF;
			}	  
		
			input#email { 
				background-position: 4px 5px; 
				background-position: 4px 5px, 0px 0px;
			}
			
			input#j_password { 
				background-position: 4px -20px; 
				background-position: 4px -20px, 0px 0px;
			}
			
			input#password { 
				background-position: 4px -20px; 
				background-position: 4px -20px, 0px 0px;
			}
			
			input#pass2 { 
				background-position: 4px -20px; 
				background-position: 4px -20px, 0px 0px;
			}
			
			input#name { 
				background-position: 4px -46px; 
				background-position: 4px -46px, 0px 0px; 
			}
			
			input#tel { 
				background-position: 4px -76px; 
				background-position: 4px -76px, 0px 0px; 
			}
			
			#registration h2 {
				color: #fff;
				text-shadow: 0px -1px 0px #000;
				border-bottom: solid #181818 1px;
				-moz-box-shadow: 0px 1px 0px #3a3a3a;
				text-align: center;
				padding: 18px;
				margin: 0px;
				font-weight: normal;
				font-size: 24px;
				font-family: Lucida Grande, Helvetica, Arial, sans-serif;
			}
			
			#registerNew {
				width: 203px;
				height: 40px;
				border: none;
				text-indent: -9999px;
				background: url('images/createAccountButton.png') no-repeat;
				cursor: pointer;
				float: right;
			}
				
			#registerNew:hover { 
				background-position: 0px -41px; 
			}
			#registerNew:active { 
				background-position: 0px -82px; 
			}
			
			#loginButton {
				width: 203px;
				height: 40px;
				border: none;
				text-indent: -9999px;
				background: url('images/loginButton.png') no-repeat;
				cursor: pointer;
				float: right;
			}
			
			#loginButton:hover { 
				background-position: 0px -41px; 
			}
			#loginButton:active { 
				background-position: 0px -82px; 
			}
			
			#registration p {
			  position: relative;
			 }
			  
			fieldset label.infield /* .infield label added by JS */ {
				color: #333;
				text-shadow: 0px 1px 0px #fff;
				position: absolute;
				text-align: left;
				top: 3px !important;
				left: 35px !important;
				line-height: 29px;
			}
		</style>
        
        
		<%@include file="meta-tags.jsp" %>
        <%@include file="includes.jsp" %> 
        <%@include file="analytics.jsp" %>    
            
        <link href="css/main.css" rel="stylesheet" type="text/css" />
		<link href="css/scroll.css" rel="stylesheet" type="text/css" />
        
        <script type="text/javascript">
			//build menu with DIV Id="myslidemenu" on page:
			droplinemenu.buildmenu("mydroplinemenu");
			
         $(document).ready(function() {
        /*
        * In-Field Label jQuery Plugin
        * http://fuelyourcoding.com/scripts/infield.html
        *
        * Copyright (c) 2009 Doug Neiner
        * Dual licensed under the MIT and GPL licenses.
        * Uses the same license as jQuery, see:
        * http://docs.jquery.com/License
        *
        * @version 0.1
        */
        (function($) { $.InFieldLabels = function(label, field, options) { var base = this; base.$label = $(label); base.$field = $(field); base.$label.data("InFieldLabels", base); base.showing = true; base.init = function() { base.options = $.extend({}, $.InFieldLabels.defaultOptions, options); base.$label.css('position', 'absolute'); var fieldPosition = base.$field.position(); base.$label.css({ 'left': fieldPosition.left, 'top': fieldPosition.top }).addClass(base.options.labelClass); if (base.$field.val() != "") { base.$label.hide(); base.showing = false; }; base.$field.focus(function() { base.fadeOnFocus(); }).blur(function() { base.checkForEmpty(true); }).bind('keydown.infieldlabel', function(e) { base.hideOnChange(e); }).change(function(e) { base.checkForEmpty(); }).bind('onPropertyChange', function() { base.checkForEmpty(); }); }; base.fadeOnFocus = function() { if (base.showing) { base.setOpacity(base.options.fadeOpacity); }; }; base.setOpacity = function(opacity) { base.$label.stop().animate({ opacity: opacity }, base.options.fadeDuration); base.showing = (opacity > 0.0); }; base.checkForEmpty = function(blur) { if (base.$field.val() == "") { base.prepForShow(); base.setOpacity(blur ? 1.0 : base.options.fadeOpacity); } else { base.setOpacity(0.0); }; }; base.prepForShow = function(e) { if (!base.showing) { base.$label.css({ opacity: 0.0 }).show(); base.$field.bind('keydown.infieldlabel', function(e) { base.hideOnChange(e); }); }; }; base.hideOnChange = function(e) { if ((e.keyCode == 16) || (e.keyCode == 9)) return; if (base.showing) { base.$label.hide(); base.showing = false; }; base.$field.unbind('keydown.infieldlabel'); }; base.init(); }; $.InFieldLabels.defaultOptions = { fadeOpacity: 0.5, fadeDuration: 300, labelClass: 'infield' }; $.fn.inFieldLabels = function(options) { return this.each(function() { var for_attr = $(this).attr('for'); if (!for_attr) return; var $field = $("input#" + for_attr + "[type='text']," + "input#" + for_attr + "[type='password']," + "input#" + for_attr + "[type='tel']," + "input#" + for_attr + "[type='email']," + "textarea#" + for_attr); if ($field.length == 0) return; (new $.InFieldLabels(this, $field[0], options)); }); }; })(jQuery);
        
        
            $("#RegisterUserForm label").inFieldLabels();
        });
        
        </script>

	</head>
    <body>
    
        <div id="wrapper">
        
            <%@include file="store_header.jsp" %> 
        
            <div id="topbarRightSide">
            </div>
        
            <div id="contentRightSide">
                <div id="left" style="padding:10px 10px 20px 20px; width:645px">
                
                
                        <div id="registration">
                                    <%
                                    if(request.getParameter("status") != null)
                                    {				
                                        %>
                                        <h1 style="color:#FFF">
                                            <%=application.getAttribute("register-success")%>
                                        </h1>				
                                        <%
                                    }
                                    else
                                    {
                                    %>				
                                    
                         <h2>Create an Account</h2>
                         <form id="RegisterUserForm" method="post" action="add.jsp?fn=newUser">
                            <fieldset>
                                 <p>
                                    <label for="name">Username</label>
                                    <input id="name" name="name" type="text" class="text" value="" />
                                 </p>
                                
                                
                                 <p>
                                    <label for="password">Password</label>
                                    <input id="password" name="password" class="text" type="password" />
                                 </p>
                                 
                                 <p>
                                    <label for="pass2">Confirm Password</label>
                                    <input id="pass2" name="pass2" class="text" type="password" />
                                 </p>
                                
                                 <p>
                                    <label for="email">Email</label>
                                    <input id="email" name="email" type="email" class="text" value="" />
                                 </p>
                                
                                 <p>
                                    <input id="newsletter" name="newsletter" type="checkbox" value="NewsLetter"/>
                                    <label for="newsletter">Newsletter</label>
                                    
                                    
                                    <input id="acceptTerms" name="acceptTerms" type="checkbox" />
                                    <label for="acceptTerms">
                                        I agree to the <a href="">Terms and Conditions</a>
                                    </label>
                                 </p>
                                
                                 <p>
                                    <button id="registerNew" type="submit">Register</button>
                                 </p>
                                 
                                 <p>
                                    <%
                                    if(request.getParameter("error") != null)
                                    {				
                                        %>
                                        <div style="color:#F00"><%=application.getAttribute("register-error")%>: 
                                            <%= request.getParameter("error")%>
                                        </div>				
                                        <%
                                    }
                                    if(request.getParameter("msg") != null)
                                    {
                                    %>
                                        <h3><%= request.getParameter("msg")%></h3>				
                                    <%
                                    }
                                    %>
                                 </p>
                            </fieldset>
                        
                         </form>
                         <%
						}
						%>
                    </div>

                </div>
            </div>  
        
            <div id="bottomRightSide">
            </div>
        
            <%@include file="index_footer.jsp" %> 
        
        </div>
      
    </body>
</html>



