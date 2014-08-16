











 











    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class Comment extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_COMMENT_ID = "CommentId";
        public static final String PROP_TITLE = "Title";
        public static final String PROP_CONTENT = "Content";
        public static final String PROP_DATE = "Date";
        public static final String PROP_COMMENT_STATUS = "CommentStatus";
        public static final String PROP_USER_ID = "UserId";
        public static final String PROP_BLOG_POST_ID = "BlogPostId";
        public static final String PROP_ITEM_REVIEW_ID = "ItemReviewId";
        

        private Integer commentId;
                
        private String title;
                
        private String content;
                
        private Date date;
                
        private Integer commentStatus;
                
        private Integer userId;
        private User user;        
        private Integer blogPostId;
        private BlogPost blogPost;        
        private Integer itemReviewId;
        private ItemReview itemReview;        
                 
        
        

        public Comment()
        {
            this.commentId = 0; 
       this.title = ""; 
       this.content = ""; 
       this.date = null; 
       this.commentStatus = 0; 
       this.userId = 0; 
       this.blogPostId = 0; 
       this.itemReviewId = 0; 
        
       
       }
       
       @Override
       public Integer getId()
       {
            
            return commentId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
        builder.add("commentId", commentId).add("title", title).add("content", content).add("date", new SimpleDateFormat("yyyyMMdd").format(date)).add("commentStatus", commentStatus).add("userId", userId).add("blogPostId", blogPostId).add("itemReviewId", itemReviewId);
        
    if(user != null) user.addJson(builder);
        if(blogPost != null) blogPost.addJson(builder);
        if(itemReview != null) itemReview.addJson(builder);
                 
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Comment.PROP_COMMENT_ID) || column.equals(Comment.PROP_TITLE) || column.equals(Comment.PROP_CONTENT) || column.equals(Comment.PROP_DATE) || column.equals(Comment.PROP_COMMENT_STATUS) || column.equals(Comment.PROP_USER_ID) || column.equals(Comment.PROP_BLOG_POST_ID) || column.equals(Comment.PROP_ITEM_REVIEW_ID) )
            {
                return column;
            }
            else
            {
                throw new SQLException("Invalid column name: " + column);
            }
        }
                
        public static void checkColumnSize(String column, int size) throws Exception
        {
            if (column.length() > size)
            {            
                throw new Exception("Invalid column length: " + size + "instead of " + column.length() + " for column: " + column);
            }
        }
                
        public static boolean isColumnNumeric(String column)
        {
            if (column.equals(Comment.PROP_COMMENT_ID) || column.equals(Comment.PROP_COMMENT_STATUS) || column.equals(Comment.PROP_USER_ID) || column.equals(Comment.PROP_BLOG_POST_ID) || column.equals(Comment.PROP_ITEM_REVIEW_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Comment process(ResultSet rs) throws SQLException
        {        
            return new Comment(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
        }
              
       public Comment(Integer CommentId, String Title, String Content, Date Date, Integer CommentStatus, Integer UserId, Integer BlogPostId, Integer ItemReviewId)
       {
            this.commentId = CommentId;
       this.title = Title;
       this.content = Content;
       this.date = Date;
       this.commentStatus = CommentStatus;
       this.userId = UserId;
       this.blogPostId = BlogPostId;
       this.itemReviewId = ItemReviewId;
              
       
       } 
        
             
        
            public Integer getCommentId()
            {
                return this.commentId;
            }
            
            public void setCommentId(Integer CommentId)
            {
                this.commentId = CommentId;
            }
            
            
        
            public String getTitle()
            {
                return this.title;
            }
            
            public void setTitle(String Title)
            {
                this.title = Title;
            }
            
            
        
            public String getContent()
            {
                return this.content;
            }
            
            public void setContent(String Content)
            {
                this.content = Content;
            }
            
            
        
            public Date getDate()
            {
                return this.date;
            }
            
            public void setDate(Date Date)
            {
                this.date = Date;
            }
            
            
        
            public Integer getCommentStatus()
            {
                return this.commentStatus;
            }
            
            public void setCommentStatus(Integer CommentStatus)
            {
                this.commentStatus = CommentStatus;
            }
            
            
        
            public Integer getUserId()
            {
                return this.userId;
            }
            
            public void setUserId(Integer UserId)
            {
                this.userId = UserId;
            }
            
            
                   
            public User getUser()
                {
                    return this.user;
                }

                public void setUser(User user)
                {
                    this.user = user;
                }
                   
            
        
            public Integer getBlogPostId()
            {
                return this.blogPostId;
            }
            
            public void setBlogPostId(Integer BlogPostId)
            {
                this.blogPostId = BlogPostId;
            }
            
            
                   
            public BlogPost getBlogPost()
                {
                    return this.blogPost;
                }

                public void setBlogPost(BlogPost blogPost)
                {
                    this.blogPost = blogPost;
                }
                   
            
        
            public Integer getItemReviewId()
            {
                return this.itemReviewId;
            }
            
            public void setItemReviewId(Integer ItemReviewId)
            {
                this.itemReviewId = ItemReviewId;
            }
            
            
                   
            public ItemReview getItemReview()
                {
                    return this.itemReview;
                }

                public void setItemReview(ItemReview itemReview)
                {
                    this.itemReview = itemReview;
                }
                   
            
         
        
        
            
    }

