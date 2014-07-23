











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class Comment implements Serializable
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

