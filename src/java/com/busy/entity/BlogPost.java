


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class BlogPost implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_BLOG_POST_ID = "BlogPostId";
        public static final String PROP_TITLE = "Title";
        public static final String PROP_CONTENT = "Content";
        public static final String PROP_IMAGE_U_R_L = "ImageURL";
        public static final String PROP_TAGS = "Tags";
        public static final String PROP_FEATURED = "Featured";
        public static final String PROP_RATING_SUM = "RatingSum";
        public static final String PROP_VOTE_COUNT = "VoteCount";
        public static final String PROP_COMMENT_COUNT = "CommentCount";
        public static final String PROP_STATUS = "Status";
        public static final String PROP_EXCERPT = "Excerpt";
        public static final String PROP_LAST_MODIFIED = "LastModified";
        public static final String PROP_LOCALE = "Locale";
        public static final String PROP_USER_ID = "UserId";
        public static final String PROP_BLOG_ID = "BlogId";
        public static final String PROP_META_TAG_ID = "MetaTagId";
        

        private Integer blogPostId;
        private String title;
        private String content;
        private String imageURL;
        private String tags;
        private Integer featured;
        private Integer ratingSum;
        private Integer voteCount;
        private Integer commentCount;
        private Integer status;
        private String excerpt;
        private Date lastModified;
        private String locale;
        private Integer userId;
        private Integer blogId;
        private Integer metaTagId;
        

        public BlogPost()
        {
            this.blogPostId = 0; 
       this.title = ""; 
       this.content = ""; 
       this.imageURL = ""; 
       this.tags = ""; 
       this.featured = 0; 
       this.ratingSum = 0; 
       this.voteCount = 0; 
       this.commentCount = 0; 
       this.status = 0; 
       this.excerpt = ""; 
       this.lastModified = null; 
       this.locale = ""; 
       this.userId = 0; 
       this.blogId = 0; 
       this.metaTagId = 0; 
        }
        
        public BlogPost(Integer BlogPostId, String Title, String Content, String ImageURL, String Tags, Integer Featured, Integer RatingSum, Integer VoteCount, Integer CommentCount, Integer Status, String Excerpt, Date LastModified, String Locale, Integer UserId, Integer BlogId, Integer MetaTagId)
        {
            this.blogPostId = BlogPostId;
       this.title = Title;
       this.content = Content;
       this.imageURL = ImageURL;
       this.tags = Tags;
       this.featured = Featured;
       this.ratingSum = RatingSum;
       this.voteCount = VoteCount;
       this.commentCount = CommentCount;
       this.status = Status;
       this.excerpt = Excerpt;
       this.lastModified = LastModified;
       this.locale = Locale;
       this.userId = UserId;
       this.blogId = BlogId;
       this.metaTagId = MetaTagId;
        } 
        
             
        
            public Integer getBlogPostId()
            {
                return this.blogPostId;
            }
            
            public void setBlogPostId(Integer BlogPostId)
            {
                this.blogPostId = BlogPostId;
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
        
            public String getImageURL()
            {
                return this.imageURL;
            }
            
            public void setImageURL(String ImageURL)
            {
                this.imageURL = ImageURL;
            }
        
            public String getTags()
            {
                return this.tags;
            }
            
            public void setTags(String Tags)
            {
                this.tags = Tags;
            }
        
            public Integer getFeatured()
            {
                return this.featured;
            }
            
            public void setFeatured(Integer Featured)
            {
                this.featured = Featured;
            }
        
            public Integer getRatingSum()
            {
                return this.ratingSum;
            }
            
            public void setRatingSum(Integer RatingSum)
            {
                this.ratingSum = RatingSum;
            }
        
            public Integer getVoteCount()
            {
                return this.voteCount;
            }
            
            public void setVoteCount(Integer VoteCount)
            {
                this.voteCount = VoteCount;
            }
        
            public Integer getCommentCount()
            {
                return this.commentCount;
            }
            
            public void setCommentCount(Integer CommentCount)
            {
                this.commentCount = CommentCount;
            }
        
            public Integer getStatus()
            {
                return this.status;
            }
            
            public void setStatus(Integer Status)
            {
                this.status = Status;
            }
        
            public String getExcerpt()
            {
                return this.excerpt;
            }
            
            public void setExcerpt(String Excerpt)
            {
                this.excerpt = Excerpt;
            }
        
            public Date getLastModified()
            {
                return this.lastModified;
            }
            
            public void setLastModified(Date LastModified)
            {
                this.lastModified = LastModified;
            }
        
            public String getLocale()
            {
                return this.locale;
            }
            
            public void setLocale(String Locale)
            {
                this.locale = Locale;
            }
        
            public Integer getUserId()
            {
                return this.userId;
            }
            
            public void setUserId(Integer UserId)
            {
                this.userId = UserId;
            }
        
            public Integer getBlogId()
            {
                return this.blogId;
            }
            
            public void setBlogId(Integer BlogId)
            {
                this.blogId = BlogId;
            }
        
            public Integer getMetaTagId()
            {
                return this.metaTagId;
            }
            
            public void setMetaTagId(Integer MetaTagId)
            {
                this.metaTagId = MetaTagId;
            }
           
            
    }

