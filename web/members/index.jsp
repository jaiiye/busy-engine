
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
    
<%@include file="../connection.jsp" %>
<%
    //get Item information	
	ArrayList<Item> items = null;
	ArrayList<String> itemImages = new ArrayList<String>();
	ArrayList<Integer> itemIds = new ArrayList<Integer>();
    ArrayList<Folder> folders = Database.getAllFoldersWithFiles();
		
	int number = 0; //means category 0 which is set to 'private'
		
	items = Database.getItemsByCategory(Integer.toString(number));
	for(Item i : items)
	{
		ResultSet rs2 = null;
		statement = connection.createStatement();
		rs2 = statement.executeQuery("SELECT ItemImageName FROM item_image WHERE ItemId = " + i.getItemId() + "  limit 1;");
		if(rs2.next())
		{
			itemImages.add(rs2.getString(1));
                        i.setItemImageName(rs2.getString(1));
			itemIds.add(Integer.parseInt(i.getItemId()));				
		}
	}
	

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
               
        <%@include file="includes.jsp" %>    
            
        <link href="../css/main.css" rel="stylesheet" type="text/css" />
		<link href="../css/scroll.css" rel="stylesheet" type="text/css" />
        
    <link href="../css/index_styles.css" type="text/css" rel="stylesheet" >
        
    	<script type="text/javascript" src="../scripts/Functions.js"></script>
        
        <script type="text/javascript">
			//build menu with DIV Id="myslidemenu" on page:
			droplinemenu.buildmenu("mydroplinemenu");
			
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
		}, 6000); //Timer speed in milliseconds (6 seconds)
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

<div>
    <%  	
        // get title images
	for(Image img : Database.getSiteImagesGivenCategory(5))
	{
    %>
	<a href="<%= img.getImageLinkUrl()%>"><img src="../images-site/<%= img.getImageFileName() %>" border="0"  alt="<%= img.getImageDescription() %>"></a>	
     <%
	}
     %> 
</div>

<%@include file="header.jsp" %> 

    <div id="topbarRightSide"></div>
   
    <div id="contentRightSide">
    
        <div id="left" style="padding:10px 10px 20px 20px; width:650px">
        
        
            
		<%  for (Folder f : folders)
        {  %>
        <div id="foldereBox<%= f.getFolderId() %>" title="<%= f.getFolderName()%>" style="display:<%=(request.getParameter("id") == null ? "none" : (Integer.toString(f.getFolderId()).equals(request.getParameter("id")) ? "block" : "none"))%>"> 
        <div id="sidebar">
            <dl>
                <dt><input type="button" onClick="toggleVisibility('foldereBox<%= f.getFolderId() %>')" value="x" /><%= f.getFolderName() %>
                <dd> 
                    <table cellSpacing="5" cellPadding="0" width="100%">
                        <%
                        for(File file : f.getFiles())
                        {
                        %>
                        <tr> 
                            <td valign="top"> 
                                <%= file.getFileLabel() %>
                                <form name="count"  action="../DownloadCounter" method="post" >
                                	<input type="hidden" name="FileId" value="<%= file.getFileId() %>" />
                                	<input type="hidden" name="FileName" value="<%= file.getFileName() %>" />
                                	<input type="hidden" name="FolderId" value="<%= f.getFolderId() %>" />
                                	<input type="hidden" name="FolderName" value="<%= f.getFolderName() %>" />
                                	<input type="hidden" name="UserId" value="<%= u.getUserId() %>" />
                                	<input type="hidden" name="UserName" value="<%= u.getUserName() %>" />
                                    <input type="submit" value="Click here to download file..." />
                                </form>
                            </td>    
                        </tr>
                        <%
                        }
                        %>
                    </table>
                </dd>
            </dl>
        </div>
    </div>
    <%  }  %>
        
        
        <%  if(items.size() == 0)
            {   %>           
                <h1>There are no products available here at the moment!</h1> 
        <%  }   %>
    	
<%
	NumberFormat formatter = NumberFormat.getCurrencyInstance();
        for(Item i : items)
        {
            String msrpString = formatter.format(i.getItemMSRP());
            String costString = formatter.format(i.getItemPrice());
%>
                     
        <table cellSpacing="2" cellPadding="5" width="100%">
            <tbody>
            	<tr>
                	<td colspan="3" align="left">
                        <h4><%= i.getItemBrandName()%>&nbsp;<%= i.getItemName()%></h4>
                    </td>
                </tr>
                <tr>
                    <td vAlign="top" width="30%">
                        <a href="../products.jsp?Id=<%= i.getItemId()%>"><img src="../items/<%= i.getItemImageName()%>" width="200" border="0" /></a>
                    </td>
                    <td valign="top" width="40%">
                        MSRP: <%= msrpString %><br />
                        Cost: <%= costString %><br /><br />
                        <a href="../products.jsp?Id=<%= i.getItemId()%>">View more details...</a> &nbsp; &nbsp; 
                   </td>  
                   
                    <td valign="top" width="*">
                          <table width="100%" cellpadding="0" cellspacing="1" border="0">
                            <tr><td align="center" colspan="2"><h2>Files</h2></tr>
                            
                                <%	
                                    ResultSet rs6 = null;
                                    rs6 = statement.executeQuery("SELECT * FROM item_file WHERE ItemId = " + i.getItemId() + ";");
                                    while( rs6.next() ) 
                                    {            
                                %>
                                <tr>
                                    <td align="center">
                                     <a href="../item-files/<%= rs6.getString(3)%>"><%= rs6.getString(3)%></a><br />
                                    </td>
                                </tr>                                        
                            <% } %>                                     
                        </table>
                   </td>                  
                </tr>
            </tbody>
        </table>
        <hr width="100%" color="#222222" SIZE="1"> 
        
    <% } %>
    
        </div>
        <div id="right">
        	<a href="../LogOut?userId=<%= u.getUserId()%>&userName=<%= u.getUserName()%>&area=Site%20Members%20Area">Logout</a><br /><br />
        	<h2>Site Folders</h2>
            <table border="0" cellspacing="0" cellpadding="0" width="100%">
                <tbody>
                    <tr>
                        <td align="left">
                        	<ol>
                	<%
					
                       	for(Folder f : folders)
						{
                    %>
                    	<li>
							<a href="#" onclick="toggleVisibility('foldereBox<%= f.getFolderId()%>')" ><%= f.getFolderName() %></a>
                        </li>
                    <% } %>
                    		</ol>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>  
    
    <div id="bottomRightSide"></div>

<%@include file="footer.jsp" %> 


  </div>
  
  
</body>
</html>
