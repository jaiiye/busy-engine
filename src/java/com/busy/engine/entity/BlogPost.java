



























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class BlogPost extends AbstractEntity implements EntityItem<Integer>
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
        public static final String PROP_POST_STATUS = "PostStatus";
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
                
        private Integer postStatus;
                
        private String excerpt;
                
        private Date lastModified;
                
        private String locale;
                
        private Integer userId;
        private User user;        
        private Integer blogId;
        private Blog blog;        
        private Integer metaTagId;
        private MetaTag metaTag;        
                 
        ArrayList<BlogPostCategory> blogPostCategoryList; 
        ArrayList<Comment> commentList; 
        
        

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
       this.postStatus = 0; 
       this.excerpt = ""; 
       this.lastModified = null; 
       this.locale = ""; 
       this.userId = 0; 
       this.blogId = 0; 
       this.metaTagId = 0; 
        
       blogPostCategoryList = null; 
        commentList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return blogPostId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("blogPostId", blogPostId == null ? 0 : blogPostId);
                
            builder.add("title", title == null ? "" : title);
                
            builder.add("content", content == null ? "" : content);
                
            builder.add("imageURL", imageURL == null ? "" : imageURL);
                
            builder.add("tags", tags == null ? "" : tags);
                
            builder.add("featured", featured == null ? 0 : featured);
                
            builder.add("ratingSum", ratingSum == null ? 0 : ratingSum);
                
            builder.add("voteCount", voteCount == null ? 0 : voteCount);
                
            builder.add("commentCount", commentCount == null ? 0 : commentCount);
                
            builder.add("postStatus", postStatus == null ? 0 : postStatus);
                
            builder.add("excerpt", excerpt == null ? "" : excerpt);
                
            builder.add("lastModified", lastModified == null ? "" : new SimpleDateFormat("yyyyMMdd").format(lastModified));
                
            builder.add("locale", locale == null ? "" : locale);
                
            builder.add("userId", userId == null ? 0 : userId);
                
            builder.add("blogId", blogId == null ? 0 : blogId);
                
            builder.add("metaTagId", metaTagId == null ? 0 : metaTagId);
        
        
    
     
     
     
     
     
     
     
     
     
     
     
     
     if(user != null) user.addJson(builder);
        
     if(blog != null) blog.addJson(builder);
        
     if(metaTag != null) metaTag.addJson(builder);
        
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(BlogPost.PROP_BLOG_POST_ID) || column.equals(BlogPost.PROP_TITLE) || column.equals(BlogPost.PROP_CONTENT) || column.equals(BlogPost.PROP_IMAGE_U_R_L) || column.equals(BlogPost.PROP_TAGS) || column.equals(BlogPost.PROP_FEATURED) || column.equals(BlogPost.PROP_RATING_SUM) || column.equals(BlogPost.PROP_VOTE_COUNT) || column.equals(BlogPost.PROP_COMMENT_COUNT) || column.equals(BlogPost.PROP_POST_STATUS) || column.equals(BlogPost.PROP_EXCERPT) || column.equals(BlogPost.PROP_LAST_MODIFIED) || column.equals(BlogPost.PROP_LOCALE) || column.equals(BlogPost.PROP_USER_ID) || column.equals(BlogPost.PROP_BLOG_ID) || column.equals(BlogPost.PROP_META_TAG_ID) )
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
            if (column.equals(BlogPost.PROP_BLOG_POST_ID) || column.equals(BlogPost.PROP_FEATURED) || column.equals(BlogPost.PROP_RATING_SUM) || column.equals(BlogPost.PROP_VOTE_COUNT) || column.equals(BlogPost.PROP_COMMENT_COUNT) || column.equals(BlogPost.PROP_POST_STATUS) || column.equals(BlogPost.PROP_USER_ID) || column.equals(BlogPost.PROP_BLOG_ID) || column.equals(BlogPost.PROP_META_TAG_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static BlogPost process(ResultSet rs) throws SQLException
        {        
            return new BlogPost(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getString(11), rs.getDate(12), rs.getString(13), rs.getInt(14), rs.getInt(15), rs.getInt(16));
        }
              
       public BlogPost(Integer BlogPostId, String Title, String Content, String ImageURL, String Tags, Integer Featured, Integer RatingSum, Integer VoteCount, Integer CommentCount, Integer PostStatus, String Excerpt, Date LastModified, String Locale, Integer UserId, Integer BlogId, Integer MetaTagId)
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
       this.postStatus = PostStatus;
       this.excerpt = Excerpt;
       this.lastModified = LastModified;
       this.locale = Locale;
       this.userId = UserId;
       this.blogId = BlogId;
       this.metaTagId = MetaTagId;
              
       blogPostCategoryList = null; 
        commentList = null; 
        
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
            
            
        
            public Integer getPostStatus()
            {
                return this.postStatus;
            }
            
            public void setPostStatus(Integer PostStatus)
            {
                this.postStatus = PostStatus;
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
            
            
                   
            public User getUser()
                {
                    return this.user;
                }

                public void setUser(User user)
                {
                    this.user = user;
                }
                   
            
        
            public Integer getBlogId()
            {
                return this.blogId;
            }
            
            public void setBlogId(Integer BlogId)
            {
                this.blogId = BlogId;
            }
            
            
                   
            public Blog getBlog()
                {
                    return this.blog;
                }

                public void setBlog(Blog blog)
                {
                    this.blog = blog;
                }
                   
            
        
            public Integer getMetaTagId()
            {
                return this.metaTagId;
            }
            
            public void setMetaTagId(Integer MetaTagId)
            {
                this.metaTagId = MetaTagId;
            }
            
            
                   
            public MetaTag getMetaTag()
                {
                    return this.metaTag;
                }

                public void setMetaTag(MetaTag metaTag)
                {
                    this.metaTag = metaTag;
                }
                   
            
         
        
        
        public ArrayList<BlogPostCategory> getBlogPostCategoryList()
            {
                return this.blogPostCategoryList;
            }
            
            public void setBlogPostCategoryList(ArrayList<BlogPostCategory> blogPostCategoryList)
            {
                this.blogPostCategoryList = blogPostCategoryList;
            }
        
        public ArrayList<Comment> getCommentList()
            {
                return this.commentList;
            }
            
            public void setCommentList(ArrayList<Comment> commentList)
            {
                this.commentList = commentList;
            }
        
            
    }

