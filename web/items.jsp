<%@ page import="java.util.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<%
    //get Item information	
    Item item = null;
    String msrpString = "-1";
    String costString = "-1";

    if (request.getParameter("Id") == null)
    {
        response.sendRedirect("error.jsp");
    } else
    {
        int number = 0;
        try
        {
            number = Integer.parseInt(request.getParameter("Id"));
        } catch (NumberFormatException ex)
        {
            response.sendRedirect("error.jsp");
        }

        ArrayList<Item> items = Database.getAllItemsWithInfoByType(1, Integer.toString(number), null);
        item = (Item) items.get(0);
        

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        msrpString = formatter.format(item.getItemMSRP());
        costString = formatter.format(item.getItemPrice());

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
            droplinemenu.buildmenu("mydroplinemenu");


  
            //Image Slider/Rotator code
            $(document).ready(function()
            {
				$(function() {
					$( "#tabs" ).tabs();
				});

				//Set Default State of each portfolio piece
                $(".paging").show();
                $(".paging a:first").addClass("active");

                //Get size of images, how many there are, then determine the size of the image reel.
                var imageWidth = 500;
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
                            }, 500);

                };

                //Rotation + Timing Event
                rotateSwitch = function()
                {
                    //Set timer - this will repeat itself every 3 seconds	
                    play = setInterval(function()
                    {
                        $active = $('.paging a.active').next();

                        //If paging reaches the end...
                        if ($active.length === 0)
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
        <style>
		.ui-tabs .ui-tabs-nav li {
    width:50%;
    text-align: center;
}
.ui-tabs .ui-tabs-nav li a {
    display: inline-block;
    float: none;
    padding: 5px;
    text-decoration: none;
    width: 100%;
}
		</style>

    </head>
    <body>

        <div id="wrapper">

           <%@include file="store_header.jsp" %> 

           <div id="topbarRightSide"></div>
            
           <div id="contentRightSide">
                <div id="left" style="padding:10px 10px 20px 20px; width:650px">

                    <h1>&nbsp;<%= item.getItemName()%></h1> 
                    <div class="container" style="width:500px;">	
                        <div class="folio_block" style="Top: 500px; left: 45%">
                            <div class="main_view">
                                <div class="window" style="width: 500px; height:450px">	
                                    <div class="image_reel">
                                        <%
                                            if (request.getParameter("Id") == null)
                                            {
                                                response.sendRedirect("error.jsp");
                                            } 
                                            else
                                            {
                                                int i = 1;
                                                for(Image img : Database.getItemImages(item.getItemId()))
                                                {
                                        %>           
                                        <img src="items/<%= img.getImageFileName()%>" alt="<%= img.getImageDescription()%>" border="0" width="500px" />
                                        <%
                                                i++;
                                            }
                                        %>
                                    </div>
                                </div>

                                <div class="paging">
                                    <%
                                        for (int j = 1; j < (i); j++)
                                        {
                                    %>
                                    <a href="#" rel="<%=j%>" style="color: #555;">.</a>                
                                    <%
                                            }
                                        }
                                    %> 

                                    <p>&nbsp;<br /></p>

                                </div>


                            </div>      
                        </div>	
                    </div>

                   
                    
                    <div id="tabs" style="margin-top:130px">
                      <ul>
                        <li   style="width:200px"><a href="#tabs-1">Details</a></li>
                        <li   style="width:200px"><a href="#tabs-2">Specifications</a></li>
                        <li   style="width:200px"><a href="#tabs-3">Reviews</a></li>
                      </ul>
                    
                      <div id="tabs-1">
                        <%= Database.decodeHTML(item.getItemDescription())%>
                      </div>
                    
                      <div id="tabs-2">
                        <p>Morbi tincidunt, dui sit amet facilisis feugiat, odio metus gravida ante, ut pharetra massa metus id nunc. Duis scelerisque molestie turpis. Sed fringilla, massa eget luctus malesuada, metus eros molestie lectus, ut tempus eros massa ut dolor. Aenean aliquet fringilla sem. Suspendisse sed ligula in ligula suscipit aliquam. Praesent in eros vestibulum mi adipiscing adipiscing. Morbi facilisis. Curabitur ornare consequat nunc. Aenean vel metus. Ut posuere viverra nulla. Aliquam erat volutpat. Pellentesque convallis. Maecenas feugiat, tellus pellentesque pretium posuere, felis lorem euismod felis, eu ornare leo nisi vel felis. Mauris consectetur tortor et purus.</p>
                    
                      </div>
                    
                      <div id="tabs-3">
                        <p>Mauris eleifend est et turpis. Duis id erat. Suspendisse potenti. Aliquam vulputate, pede vel vehicula accumsan, mi neque rutrum erat, eu congue orci lorem eget lorem. Vestibulum non ante. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce sodales. Quisque eu urna vel enim commodo pellentesque. Praesent eu risus hendrerit ligula tempus pretium. Curabitur lorem enim, pretium nec, feugiat nec, luctus a, lacus.</p>
                      </div>
                    </div>







                    


                </div>
                <div id="right">
                    <br />
                    <script type="text/javascript">
                        var s = '<%= Database.makeItemOptionDropDown("optionId", item.getItemId(), 2)%>';
                        MakePriceForm("<%= item.getItemId()%>", "<%=item.getItemBrandName()%> <%=item.getItemName()%>", "<%= msrpString%>", <%= item.getItemMSRP()%>, "<%=costString%>", <%= item.getItemPrice()%>, s, "<%=application.getAttribute("original-price")%>", "<%=application.getAttribute("savings")%>", "<%=application.getAttribute("item-options")%>", "<%=application.getAttribute("buy")%>");
                    </script>

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

                                    <% if (item.getItemInfo().getItemFiles().size() > 0)
            {%>
                                    <table width="100%" cellpadding="0" cellspacing="1" border="0">
                                        <tr><td align="center" colspan="2"><h2>Files</h2></tr>

                                        <%
                                            for (File f : item.getItemInfo().getItemFiles())
                                            {
                                        %>
                                        <tr>
                                            <td align="center">
                                                <a href="item-files/<%= f.getFileName()%>"><%= f.getFileName()%></a><br />
                                            </td>
                                        </tr>                                        
                                        <% }%>                                     
                                        <tr><td align="center" colspan="4"><hr></tr>
                                                    </table>
                                                    <% }%>


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
