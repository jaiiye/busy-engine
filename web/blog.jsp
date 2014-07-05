<%@ page import="java.util.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<%! 
	public String getCurrentDate() throws java.text.ParseException 
	{    
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");    
		java.util.Date tDate = new java.util.Date();  
		return df.format(tDate);   
	}     
%>

<%
	int pageNumber = 0;
	int blogId = 1;
	int i = 1;
	if(request.getParameter("page") != null)
	{
	   pageNumber = Integer.parseInt(request.getParameter("page")); 
	}
	
	if(request.getParameter("id") != null)
	{
		blogId = Integer.parseInt(request.getParameter("id"));
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>

<style>

	/*/////////////////////////////
	//     blog.jsp Styles
	////////////////////////////*/
	.commentsButton A 
	{
		PADDING-LEFT: 5px;
		display:inline; 
		WIdTH: 94px; 
		BACKGROUND: url(images/commentsButton.png) no-repeat; 
		HEIGHT: 16px; 
		COLOR: #666;
		font-size:10px; 
		FONT-WEIGHT: bold; 
		TEXT-DECORATION: none; 
		float:right;
		top: 0;
	}
	
	.commentsNumber A 
	{
		margin-left: 14px;
		padding-bottom: 11px;
		display:block; 
		COLOR: #666;
		font-size:10px; 
		FONT-WEIGHT: bold; 
		TEXT-DECORATION: none;
		bottom: 10px;
		cursor: pointer;
	}
	/*/////////////////////////////
	//     blog.jsp Styles
	////////////////////////////*/
	
	/*/////////////////////////////
	//     Blog Post Styles
	////////////////////////////*/
	
	#Post	
	{
		margin: 3px;   
		width: 65%;
		border: 1px solid #999;
	}
	
	#PostHeader				
	{ 
		width: 100%; 
		position: relative; 
		border-bottom: 2px solid grey;
		background-color:#DDD
	}
	
	#PostTitle				
	{ 
		position: absolute; 
		left: 160px; 
		Top: 0; 
		width: 350px; 
		font-size: 16px; 
		height: 40px; 
		padding:3px;
	}
	
	#PostAuthorDateRating				
	{ 
		position: absolute; 
		Right: 0; 
		Top: 0; 
		width: 150px; 
		height: 40px; 
		padding:3px;
	}
	
	#PostImage				
	{ 
		width: 150px;
	}
	
	#PostBody				
	{ 
		padding: 10px; 
		position: relative; 
	}
	
	#PostBodyImage1				
	{ 
		position: absolute; 
		width: 10px; 
		top: 2px; 
		left: 2px;
	}
	
	#PostBodyImage2			
	{ 
		position: absolute; 
		width: 10px; 
		bottom: 2px; 
		right: 2px;
	}
	
	#PostTags			
	{ 
		border-top: 2px solid grey;
	}
	
	#PostFooterDiv			
	{ 
		border-top: 1px solid grey;
		width: 100%; 
		position: relative;	
		background-color:#EEE
	}
	
	#PostFooterShareIcons			
	{ 
		position: absolute; 
		Right: 0; 
		Top: 0; 
		width: 140px; 
		padding:3px;
	}
	
	/*/////////////////////////////
	//     Blog Post Styles End
	////////////////////////////*/
</style>


	<%@include file="meta-tags.jsp" %>
	<%@include file="includes.jsp" %>
    <%@include file="analytics.jsp" %> 
    
    <script type="text/javascript" src="scripts/Functions.js"></script>

        <script type="text/javascript">
			//build menu with DIV Id="myslidemenu" on page:
			droplinemenu.buildmenu("mydroplinemenu");
			
    	    $(document).ready(function() 
			{
    	        $('#coin-slider').coinslider({ width: 919, height: 298, navigation: true, delay: 4000 });
        	});
		</script>
        

	</head>
<body>

<div id="wrapper">

<%@include file="index_header.jsp" %> 

    <div id="topbarRightSide">
    </div>
    
    <div id="contentRightSide">
        <div id="left" style="padding:10px 10px 20px 20px; width:650px">
     
    <div id="titleGraphic" align="center">
	    <img src="images-site/blog.png" border="0" width="630px" />
     </div>
                
  <div id="featuredDesigns">
           
		<%
        ArrayList<Post> posts = Database.getPosts(blogId, pageNumber);	
		
        i = 1;
        for(Post p : posts)
        {

			ArrayList<Comment> postComments = p.getComments();

        %>
       
                <SCRIPT type="text/javascript">
                    //MakePost("post<%= i %>", "<%=p.getPostTitle()%>", "<%=p.getPostBody()%>", "<%=p.getPostPicURL()%>", "<%=p.getPostDate()%>", "<%=p.getPostTags()%>", "<%=p.getUserId()%>", "<%=p.getPostRating()%>", "<%=p.getPostUserName()%>"); 
                </SCRIPT>
                
      <table width="557" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-bottom:10px;" align="center">
       <tr>
           <td rowspan="3" valign="top" width="100" align="center">
           	<img src="images/<%=p.getPostPicURL()%>" border="0" width="65" height="65"  /><br /><a href="http://twitter.com/share" class="twitter-share-button" data-url="vote.jsp?action=view&item=" data-text="The Design by is awsome:" data-count="none" data-via="xtatico">Tweet</a><script type="text/javascript" src="http://platform.twitter.com/widgets.js"></script>
           </td>
           <td background="images/t.jpg" height="33" width="*">
		   <div style="margin-left:30px; margin-right:10px; font-weight:bold">
		   <%=p.getPostTitle()%>&nbsp;&nbsp;&nbsp;<%=p.getPostDate().substring(0,10)%></div>
           </td>
       </tr>
       <tr>       
           <td background="images/m.jpg">           
               <div style="margin-left:30px; margin-right:10px">
                    <%=p.getPostBody()%>
               </div>
           </td>
       </tr>
       <tr>
           <td background="images/b.jpg" height="33">
           
           
           <table width="100%" cellpadding="0" cellspacing="0" border="0">
           		<tr>
           			<td width="*">
               			<div style="margin-left:30px; margin-right:35px; font-size:10px">
                    		<%=p.getPostTags()%>
		                </div>
         		    </td>
               		<td width="35">
                    <div class="commentsNumber">
	    	<a href="javascript:void(0)" onClick="toggleVisibility('postComment<%=i%>');"><%=postComments.size()%></a>
        </div> 
               		</td>
               	</tr>
           </table>
           
           </td>
       </tr>
   </table>
   <div id="postComment<%=i%>" style="background: #e5e5e5; border: 1px solid #ccc; padding: 5px 10px; margin-bottom: 17px; width:537px;  margin-top: 7px;font-size:18px; font-weight:bold; display: none; margin-left:auto; margin-right:auto">
	

        
        <div style="margin-left:0px">
	    	<table width="100%">
            
           
                    <form method="post" action="add.jsp?fn=BlogPost" name="addBlogPost" >
                        <input type="hidden" name="postId" value="<%=p.getPostId()%>" />
                        <input type="hidden" name="UserId" value="999" />
                        <input name="PostCommentDate" type="hidden" value="<%= getCurrentDate().substring(0, 19)%>"/>
                        <tr>
                            <td >
                                <div class="commentsButton">
                                    <a><%= postComments.size()%></a>
                                </div>
                                <input type="text" name="PostCommentTitle"  maxlength="255" size="50" class="textField" value="Enter Title" />&nbsp;

                            </td>
                        </tr>
                        <tr>
                            <td>
                                <textarea name="PostCommentBody" class="textArea" cols="43" rows="4"></textarea>&nbsp;&nbsp;
                                <input type="submit" class="xbutton" id="comment_submit" value="Comment"  />
                            </td>
                        </tr>

                    </form>
  


<tr>
	<td width="100%">
			
        
	<div id="postComments">
    	
        <%
            
			for(Comment pc : postComments)
        	{
		%>
              <table width="100%">  
                  <tr> 
                    <td bgcolor="#CCCCCC" align="left">
                         <h4 style="float:right; margin: 0 5px 0 0; font-size:10px">&nbsp;<%= pc.getCommentDate().substring(0,10)%></h4><strong><%= pc.getCommentTitle()%></strong>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div style="font-size:11px"><%= pc.getCommentBody()%></div>
                    </td>
                </tr>
			</table>
        
        <%
		}
		%>
        
        
        </div>
		
	</td>
</tr>
</table>
        </div>         
		
                         
</div>
        
        
        <%
        i++;
        } 
        %> 

    </div>
        </div>
        <div id="right">
                <%@include file="toolbar.jsp" %> 
        </div>
    </div>    
    
    <div id="bottomRightSide"></div>

<%@include file="index_footer.jsp" %> 


  </div>
  
  
</body>
</html>
