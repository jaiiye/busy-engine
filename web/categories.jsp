<%@ page import="java.util.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<%
    //get Item information	
    ArrayList<Item> items = null;
    String categoryName = "";

    if (request.getParameter("Id") == null)
    {
        response.sendRedirect("error.jsp");
    } 
    else
    {
        int number = 0;
        try
        {
            number = Integer.parseInt(request.getParameter("Id"));
        } 
        catch (NumberFormatException ex)
        {
            response.sendRedirect("error.jsp");
        }

        items = Database.getItemsByCategory(Integer.toString(number));
	categoryName = Database.getCategoryName(number);
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
                $(".image_reel").css({'width': imageReelWidth});

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
                            }, 600);

                };

                rotateSwitch = function()  //Rotation + Timing Event
                {
                    play = setInterval(function()  //Set timer - this will repeat itself every 3 seconds
                    {
                        $active = $('.paging a.active').next();
                        if ($active.length === 0)  //If paging reaches the end...
                        {
                            $active = $('.paging a:first'); //go back to first
                        }
                        rotate(); //Trigger the paging and slider function
                    }, 6000); //Timer speed in milliseconds (6 seconds)
                };

                rotateSwitch(); //Run function on launch
                $(".image_reel a").hover(function()  //On Hover
                {
                    clearInterval(play); //Stop the rotation
                }, function()
                {
                    rotateSwitch(); //Resume rotation
                });

                $(".paging a").click(function()   //On Click
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
                    <%
                        if (items.size() == 0)
                        {
                    %>

                    <h1>This category does not contain any products!</h1> 

                    <% 
						}
						else {
					%>
							<h1> <%= categoryName %> Machines</h1> 
					<%
						}
					%>




                    <div class="container" style="width:600px; height: 500px">	
                        <div class="folio_block" style="Top: 600px; left: 45%">
                            <div class="main_view">
                                <div class="window" style="width: 600px;  height: 500px">	
                                    <div class="image_reel">
                                        <%
                                            for (Item item : items)
                                            {
                                        %>           
                                        <a href="products.jsp?Id=<%= item.getItemId() %>"><img src="items/<%= item.getItemImageName() %>" alt="" border="0" width="600px" /></a>
                                        <%
                                            }
                                        %>
                                    </div>
                                </div>

                                <div class="paging">
                                    <%
                                        for (int j = 1; j < (items.size()+1) ; j++)
                                        {
                                    %>
                                    <a href="#" rel="<%=j%>" style="color: #555;">.</a>                
                                    <%
                                        }

                                    %> 

                                    <p>&nbsp;<br /><br /></p>

                                </div>


                            </div>      
                        </div>	
                    </div>




                    <%
                        for (Item i : items)
                        {
                    %>

                    <table cellSpacing="2" cellPadding="5" width="100%">
                        <tbody>
                            <tr>
                                <td vAlign="top" align="middle" width="150" rowSpan="2">
                                    <a href="products.jsp?Id=<%= i.getItemId()%>">
                                        <img src="items/<%= i.getItemImageName()%>" width="300" height="250" border="0" />
                                    </a>
                                </td>
                                <td valign="top">
                                    <h4><%= i.getItemBrandName()%>&nbsp;<%= i.getItemName()%></h4>
                                    <p><%= i.getItemShortDescription()%></p><br />
                                    <a href="products.jsp?Id=<%= i.getItemId()%>">View Details</a> &nbsp; &nbsp; 
                                </td>                   
                            </tr>
                        </tbody>
                    </table>
                    <hr width="100%" color="#222222" SIZE="1"> 

                        <% }%>

                </div>
                <div id="right">
                    <table border="0" cellspacing="0" cellpadding="0" width="100%">
                        <tbody>
                            <tr>
                                <td align="center">
                                    <%@include file="toolbar.jsp" %>
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
