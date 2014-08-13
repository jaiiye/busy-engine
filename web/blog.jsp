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
	
	Blog b = Database.getBlog(blogId);
	int blogType = Integer.parseInt(b.getBlogType());
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
            <h1><%= b.getBlogName() %></h1>           
            <% if(blogType == 1 || blogType == 2) {%>
	       	<section class="main postlist">
            <% } %>
            <% if(blogType == 3) {%>
            	<section class="content postlist">
            <% } %>
            <% if(blogType == 4) {%>
            	<section class="columns">
            <% } %>
        	<% if(blogType == 5) {%>
            	<section class="columns">
            <% } %>            
        <%			
        	ArrayList<Post> posts = Database.getPosts(blogId, pageNumber);			
        	i = 1;			
	        for(Post p : posts)
	        {
			ArrayList<Comment> postComments = p.getComments();
			String postLink = "blogPost.jsp?id=" + p.getPostId();	
			String postDate = p.getPostDate().substring(0,10);
			String postTitle = p.getPostTitle();
			String postBody = p.getPostBody().length() >= 150 ?  p.getPostBody().substring(0,150).replaceAll("\\<.*?>","") + "..." : p.getPostBody().replaceAll("\\<.*?>","");		
        %>        
        	<% if(blogType == 1 || blogType == 2|| blogType ==3) {%>
            	<article class="post">
            <% } %>
        	<% if(blogType == 4) {%>
            	<article class="col2 post">
            <% } %>
        	<% if(blogType == 5) {%>
            	<article class="col3 post">
            <% } %>
                <% if(blogType == 1 ) { %>
                        <h2><a href="<%= postLink %>"><%= postTitle %></a></h2>
                        <p class="post-meta"><%= postDate %><span>|</span> by <%=p.getPostUserName()%> <span>|</span><a href="<%= postLink %>" class="comment-link"><%=postComments.size()%> comments</a></p>                    
                        <div class="img medium"><a href="<%= postLink %>"><img src="images/<%=p.getPostPicURL()%>" alt=""></a></div>
                        <p><%= postBody %></p>
                    	<p class="tags"><strong>Tags</strong>: <%=p.getPostTags()%></p>
                    <% 
                    }
                    if(blogType == 2 || blogType == 3 ) { 
                    %>
                        <h2><a href="<%= postLink %>"><%= postTitle %></a></h2>
                        <p class="post-meta"><%= postDate %><span>|</span> by <%=p.getPostUserName()%> <span>|</span>
                            <a href="<%= postLink %>" class="comment-link"><%=postComments.size()%> comments</a></p>
                        <a href="<%= postLink %>"><img src="images/<%=p.getPostPicURL()%>" alt=""></a>                        
                        <p><%= postBody %></p>
                    	<p class="tags"><strong>Tags</strong>: <%=p.getPostTags()%></p>
                    <% 
                    }
                    if(blogType == 4 || blogType == 5 ) { 
                    %>
	                    <div class="img"><a href="<%= postLink %>"><img src="images/<%=p.getPostPicURL()%>" alt=""></a></div>
                        <h2><a href="<%= postLink %>"><%= postTitle %></a></h2>
                        <p class="post-meta"><%= postDate %><span>|</span> by <%=p.getPostUserName()%> <span>|</span>
                            <a href="<%= postLink %>" class="comment-link"><%=postComments.size()%> comments</a></p>
                        <p><%= postBody %></p>
                    <% 
					}
					%>

                    <p class="more"><a href="<%= postLink %>">Read more</a></p>                                
            </article>  
            
       
        <%
        i++;
        } 
        %> 
    
        <% 
			if(blogType == 4 || blogType == 5 ) { 
		%>
    		</section>
        <% } %>
        
        
			<div class='wp-pagenavi'>
				<span class='current'>1</span><a href='#' class='page larger'>2</a><a href='#' class='page larger'>3</a><a href='#' class='page larger'>4</a><a href='#' class='page larger'>5</a><a href='#' class='nextpostslink'>Next page</a>
			</div>  
		</section>
        
        <% if(blogType != 3 && blogType != 4  && blogType != 5) { %>
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
                        <li>
                            <img src="images/temp/avatar3.jpg" alt=""> 
                            <p class="comment-head"><span class="who">Stefany</span> on <a href="#">Nullam dictum felis</a></p>
                            <p>Cum sociis natoque penatibus...</p>
                        </li>
                        <li>
                            <img src="images/temp/avatar3.jpg" alt=""> 
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
        <% } %>
		
            </section>    
            <%@include file="multipurpose_footer.jsp" %>    
        </div>
    
        <%@include file="multipurpose_scripts.jsp" %>
    </body>
</html>
