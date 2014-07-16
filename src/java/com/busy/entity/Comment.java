


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class Comment implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_COMMENT_ID = "CommentId";
        public static final String PROP_TITLE = "Title";
        public static final String PROP_CONTENT = "Content";
        public static final String PROP_DATE = "Date";
        public static final String PROP_STATUS = "Status";
        public static final String PROP_USER_ID = "UserId";
        public static final String PROP_POST_ID = "PostId";
        public static final String PROP_REVIEW_ID = "ReviewId";
        

        private Integer commentId;
        private String title;
        private String content;
        private Date date;
        private Integer status;
        private Integer userId;
        private Integer postId;
        private Integer reviewId;
        

        public Comment()
        {
            this.commentId = 0; 
       this.title = ""; 
       this.content = ""; 
       this.date = null; 
       this.status = 0; 
       this.userId = 0; 
       this.postId = 0; 
       this.reviewId = 0; 
        }
        
        public Comment(Integer CommentId, String Title, String Content, Date Date, Integer Status, Integer UserId, Integer PostId, Integer ReviewId)
        {
            this.commentId = CommentId;
       this.title = Title;
       this.content = Content;
       this.date = Date;
       this.status = Status;
       this.userId = UserId;
       this.postId = PostId;
       this.reviewId = ReviewId;
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
        
            public Integer getStatus()
            {
                return this.status;
            }
            
            public void setStatus(Integer Status)
            {
                this.status = Status;
            }
        
            public Integer getUserId()
            {
                return this.userId;
            }
            
            public void setUserId(Integer UserId)
            {
                this.userId = UserId;
            }
        
            public Integer getPostId()
            {
                return this.postId;
            }
            
            public void setPostId(Integer PostId)
            {
                this.postId = PostId;
            }
        
            public Integer getReviewId()
            {
                return this.reviewId;
            }
            
            public void setReviewId(Integer ReviewId)
            {
                this.reviewId = ReviewId;
            }
           
            
    }

