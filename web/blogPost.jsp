<%@ page import="java.util.*" %>
<%@ page import="com.busy.engine.entity.*" %>
<%@ page import="com.busy.engine.dao.*" %>
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
	int postId = 1;	
	if(request.getParameter("id") != null)
	{
         postId = Integer.parseInt(request.getParameter("id"));
	}
%>



<!DOCTYPE html> 
<html>
    <head>    
        <title>BusyTechnologies - Customizable Responsive Website</title>	    
        <meta charset="UTF-8">
        <meta name="description" content="">
        <meta name="keywords" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
        <%@include file="multipurpose_styles.jsp" %>
    </head>
    
    <body class="home">	
        <div class="root">	
            <%@include file="multipurpose_header.jsp" %>       
            <section class="content"> 
		<section class="main single">
        <%        	
            BlogPostDao dao = new BlogPostDaoImpl();
            BlogPost p = dao.find(postId);
            dao.getRelatedInfo(p);
            dao.getRelatedObjects(p);
            ArrayList<Comment> postComments = p.getCommentList();
        %>        
            <article class="post">
                    <h1><a href="#"><%=p.getTitle()%></a></h1>
                    <p class="post-meta"><%=p.getLastModified().toString().substring(0,10)%><span>|</span> by <a href="#" class="author"><%=p.getUser().getUsername()%></a> <span>|</span><a href="#" class="comment-link"><%=postComments.size()%> comments</a></p>
                    <p><img src="images/<%=p.getImageURL()%>" alt=""><p>
                    <p><%= p.getContent() %></p>
                    <p class="tags"><strong>Tags</strong>: <%=p.getTags()%></p>                                
            </article>            
            
            <section class="comments">
			   	<h2><span><%=postComments.size()%> comments</span></h2>
				<ul class="commentlist">                
				<%
                    for(Comment pc : postComments)
                    {
                %>  
                    <li class="comment even">
                        <div class="comment-author vcard">
                            <img alt="" src="images/unknown.jpg" class="avatar avatar-32 photo" height="32" width="32">		
                            <cite class="fn"><%= pc.getTitle()%></cite>
                            <p class="comment-meta commentmetadata"><%= pc.getDate().toString().substring(0,10)%></p>
                        </div>
                        <div class="comment-body">
                            <p><%= pc.getContent()%></p>
                        </div>						
                    </li>        
                <%
                }
                %>
                </ul>
                </section>
                
                <section class="comment-form">
                    <h2><span>Leave a Comment</span></h2>
                    <form action="add.jsp?fn=BlogPost" name="addBlogPost" method="post">
                        <input type="hidden" name="postId" value="<%= p.getBlogPostId()%>" />
                        <input type="hidden" name="UserId" value="999" />
                        <input name="PostCommentDate" type="hidden" value="<%= getCurrentDate().substring(0, 19)%>"/>
                        <fieldset>
                            <legend>Reply to the post</legend>
                            <label for="PostCommentTitle">Title</label><input id="PostCommentTitle" name="PostCommentTitle"><br>
                            <label for="PostCommentBody">Message</label>
                            <textarea name="PostCommentBody" id="PostCommentBody" rows="8" cols="50"></textarea>
                            <p><button type="submit" name="send">Post comment</button></p>
                        </fieldset>
                    </form>
                </section> 
        
		</section>
                
        
		<aside>
        	<section>
				<h3><span>Recent comments</span></h3>
				<ul class="recent-comments">
					<li>
						<img src="images/temp/avatar1.jpg" alt=""> 
						<p class="comment-head"><span class="who">Stefany</span> on <a href="#">Nullam dictum felis</a></p>
						<p>Cum sociis natoque penatibus...</p>
					</li>
					<li>
						<img src="images/temp/avatar2.jpg" alt=""> 
						<p class="comment-head"><span class="who">Stefany</span> on <a href="#">Nullam dictum felis</a></p>
						<p>Cum sociis natoque penatibus...</p>
					</li>
				</ul>
			</section>
            <section>
				<h3><span>Archives</span></h3>
				<select name="archive-dropdown"> 
					<option value="">Select Month</option> 	
					<option value="#"> October 2011 &nbsp;(1)</option>
					<option value="#"> September 2008 &nbsp;(3)</option>
					<option value="#"> June 2008 &nbsp;(10)</option>
					<option value="#"> May 2008 &nbsp;(5)</option>
					<option value="#"> April 2008 &nbsp;(1)</option>
					<option value="#"> March 2008 &nbsp;(3)</option>
				 </select>
			</section>
		</aside>
		
            </section>    
            <%@include file="multipurpose_footer.jsp" %>    
        </div>
    
        <%@include file="multipurpose_scripts.jsp" %>
    </body>
</html>
