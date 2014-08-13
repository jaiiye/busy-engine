











 











    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class BlogPostCategory extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_BLOG_POST_CATEGORY_ID = "BlogPostCategoryId";
        public static final String PROP_BLOG_POST_ID = "BlogPostId";
        public static final String PROP_POST_CATEGORY_ID = "PostCategoryId";
        

        private Integer blogPostCategoryId;
                
        private Integer blogPostId;
        private BlogPost blogPost;        
        private Integer postCategoryId;
        private PostCategory postCategory;        
                 
        
        

        public BlogPostCategory()
        {
            this.blogPostCategoryId = 0; 
       this.blogPostId = 0; 
       this.postCategoryId = 0; 
        
       
       }
       
       @Override
       public Integer getId()
       {
            
            return blogPostCategoryId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
        builder.add("blogPostCategoryId", blogPostCategoryId).add("blogPostId", blogPostId).add("postCategoryId", postCategoryId);
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(BlogPostCategory.PROP_BLOG_POST_CATEGORY_ID) || column.equals(BlogPostCategory.PROP_BLOG_POST_ID) || column.equals(BlogPostCategory.PROP_POST_CATEGORY_ID) )
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
            if (column.equals(BlogPostCategory.PROP_BLOG_POST_CATEGORY_ID) || column.equals(BlogPostCategory.PROP_BLOG_POST_ID) || column.equals(BlogPostCategory.PROP_POST_CATEGORY_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static BlogPostCategory process(ResultSet rs) throws SQLException
        {        
            return new BlogPostCategory(rs.getInt(1), rs.getInt(2), rs.getInt(3));
        }
              
       public BlogPostCategory(Integer BlogPostCategoryId, Integer BlogPostId, Integer PostCategoryId)
       {
            this.blogPostCategoryId = BlogPostCategoryId;
       this.blogPostId = BlogPostId;
       this.postCategoryId = PostCategoryId;
              
       
       } 
        
             
        
            public Integer getBlogPostCategoryId()
            {
                return this.blogPostCategoryId;
            }
            
            public void setBlogPostCategoryId(Integer BlogPostCategoryId)
            {
                this.blogPostCategoryId = BlogPostCategoryId;
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
                   
            
        
            public Integer getPostCategoryId()
            {
                return this.postCategoryId;
            }
            
            public void setPostCategoryId(Integer PostCategoryId)
            {
                this.postCategoryId = PostCategoryId;
            }
            
            
                   
            public PostCategory getPostCategory()
                {
                    return this.postCategory;
                }

                public void setPostCategory(PostCategory postCategory)
                {
                    this.postCategory = postCategory;
                }
                   
            
         
        
        
            
    }

