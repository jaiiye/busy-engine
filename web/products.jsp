<%@ page import="java.util.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<%
    //get Item information	
	Item item = null;
	ArrayList<String> itemFileNames = new ArrayList<String>();
		
	if (request.getParameter("Id") == null) {
		response.sendRedirect("error.jsp");
	} else {
		int number = 0;
		try 
		{
			number = Integer.parseInt(request.getParameter("Id"));
				
		} catch (NumberFormatException ex) {
			response.sendRedirect("error.jsp");
		}
			
		item = Database.getItemById(Integer.toString(number));
		itemFileNames = Database.getItemFiles(item.getItemId());
            
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
    
		<%@include file="meta-tags.jsp" %>
        <%@include file="includes.jsp" %> 
        <%@include file="analytics.jsp" %>    
            
        <link href="css/main.css" rel="stylesheet" type="text/css" />
		<link href="css/scroll.css" rel="stylesheet" type="text/css" />
        
        <script type="text/javascript">
			//build menu with DIV Id="myslidemenu" on page:
			droplinemenu.buildmenu("mydroplinemenu")

			 //Image Slider/Rotator code
			$(document).ready(function() 
			{
			 
				//Set Default State of each portfolio piece
				$(".paging").show();
				$(".paging a:first").addClass("active");
					
				//Get size of images, how many there are, then determine the size of the image reel.
				var imageWidth = 600;
				var imageSum = $(".image_reel img").size();
				var imageReelWidth = imageWidth * imageSum;
				
				//Adjust the image reel to its new size
				$(".image_reel").css({'width' : imageReelWidth});
				
				//Paging + Slider Function
				rotate = function()
				{	
					var triggerId = $active.attr("rel") - 1; //Get number of times to slide
					var image_reelPosition = triggerId * imageWidth; //Determines the distance the image reel needs to slide
			 
					$(".paging a").removeClass('active'); //Remove all active class
					$active.addClass('active'); //Add active class (the $active is declared in the rotateSwitch function)
					
					//Slider Animation
					$(".image_reel").animate(
					{ 
						left: -image_reelPosition
					}, 600 );
					
				}; 
	
				//Rotation + Timing Event
				rotateSwitch = function()
				{	
					//Set timer - this will repeat itself every 3 seconds	
					play = setInterval(function()
					{ 			
						$active = $('.paging a.active').next();
						
						//If paging reaches the end...
						if ( $active.length === 0) 
						{ 
							$active = $('.paging a:first'); //go back to first
						}
						rotate(); //Trigger the paging and slider function
					}, 7000); //Timer speed in milliseconds (3 seconds)
				};
				
				rotateSwitch(); //Run function on launch
				
				//On Hover
				$(".image_reel a").hover(function() 
				{
					clearInterval(play); //Stop the rotation
				}, function() 
				{
					rotateSwitch(); //Resume rotation
				});	
				
				//On Click
				$(".paging a").click(function() 
				{	
					$active = $(this); //Activate the clicked paging
					//Reset Timer
					clearInterval(play); //Stop the rotation
					rotate(); //Trigger rotation immediately
					rotateSwitch(); // Resume rotation
					return false; //Prevent browser jump to link anchor
				});	
				
			});
		</script>
	</head>
<body>

<div id="wrapper">

<%@include file="store_header.jsp" %> 

    <div id="topbarRightSide"></div>
   
    <div id="contentRightSide">
        <div id="left" style="padding:10px 10px 20px 20px; width:650px">
        	
               <h1>&nbsp;<%= item.getItemName()%></h1> 
				<div class="container" style="width:600px; height: 500px">	
					<div class="folio_block" style="Top: 600px; left: 45%">
        				<div class="main_view">
            				<div class="window" style="width: 600px;  height: 500px">	
                				<div class="image_reel">
<%
	if(request.getParameter("Id") == null)
	{
		response.sendRedirect("error.jsp");
	}
	else
	{
		statement = connection.createStatement();
		String query = "SELECT ItemImageName FROM item_image WHERE ItemId = " + request.getParameter("Id") + ";";
		rs = statement.executeQuery(query);
		int i = 1;
	while(rs.next())
	{
%>           
		<img src="items/<%= rs.getString(1)%>" alt="" border="0" width="600px" />
<%
		i++;
	} 
%>
               					</div>
            				</div>
                            
        <div class="paging">
<%
		for(int j = 1; j < (i) ; j++)
		{
%>
			<a href="#" rel="<%=j%>" style="color: #555;">.</a>                
<%
		}
	}
%> 

<p>&nbsp;<br /><br /><br /><br /></p>

            </div>
            
                            
        </div>      
    </div>	
</div>
            
<br />
				<%= Database.decodeHTML(item.getItemDescription()) %>
 
            
        </div>
        <div id="right">
            <table border="0" cellspacing="0" cellpadding="0" width="100%">
    <tbody>
        <tr>
            <td align="center"><a href="information-request.jsp?purpose=sales%20proposal&amp;itemId=<%=request.getParameter("Id")%>&name=<%=item.getItemName()%>"><img src="images-site/toolbar_proposal.png" border="0"/></a> 
            <br />
                <a href="information-request.jsp?purpose=Brochure&amp;itemId=<%=request.getParameter("Id")%>&name=<%=item.getItemName()%>"><img src="images-site/toolbar_brochure.png" border="0"/></a>                               
                <br />
                <a href="mail.jsp?purpose=email&amp;itemId=<%=request.getParameter("Id")%>&name=<%=item.getItemName()%>&brand=<%=item.getItemBrandName()%>"><img src="images-site/toolbar_email.png" border="0"/></a>
            </td>
        </tr>
        <tr>
            <td align="center">
				<%@include file="toolbar.jsp" %>
            </td>
        </tr>
        
        
        <tr>
            <td align="center">
        
        <% if(itemFileNames.size() > 0) { %>
          <table width="100%" cellpadding="0" cellspacing="1" border="0">
            <tr><td align="center" colspan="2"><h2>Files</h2></tr>
            
                <%	
					for(String f : itemFileNames) {                                              
                %>
                <tr>
                    <td align="center">
					 <a href="item-files/<%= f%>"><%= f%></a><br />
					</td>
                </tr>                                        
            <% } %>                                     
            <tr><td align="center" colspan="4"><hr></tr>
        </table>
        <% } %>
        
        
            </td>
        </tr>
        
        
    </tbody>
</table>
            
        </div>
    </div>  
    
    <div id="bottomRightSide"></div>

<%@include file="index_footer.jsp" %> 


  </div>
  
  
</body>
</html>
