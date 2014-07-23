











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class BlogPostCategory implements Serializable
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
