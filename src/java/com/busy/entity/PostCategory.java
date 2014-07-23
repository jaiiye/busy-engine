











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class PostCategory implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_POST_CATEGORY_ID = "PostCategoryId";
        public static final String PROP_NAME = "Name";
        

        private Integer postCategoryId;
                
        private String name;
                
                 
        ArrayList<BlogPostCategory> blogPostCategoryList; 
        
        

        public PostCategory()
        {
            this.postCategoryId = 0; 
       this.name = ""; 
        
       blogPostCategoryList = null; 
        
       }
        
        public PostCategory(Integer PostCategoryId, String Name)
        {
            this.postCategoryId = PostCategoryId;
       this.name = Name;
              
       blogPostCategoryList = null; 
        
       } 
        
             
        
            public Integer getPostCategoryId()
            {
                return this.postCategoryId;
            }
            
            public void setPostCategoryId(Integer PostCategoryId)
            {
                this.postCategoryId = PostCategoryId;
            }
            
            
        
            public String getName()
            {
                return this.name;
            }
            
            public void setName(String Name)
            {
                this.name = Name;
            }
            
            
         
        
        
            public ArrayList<BlogPostCategory> getBlogPostCategoryList()
            {
                return this.blogPostCategoryList;
            }
            
            public void setBlogPostCategoryList(ArrayList<BlogPostCategory> blogPostCategoryList)
            {
                this.blogPostCategoryList = blogPostCategoryList;
            }
        
            
    }

