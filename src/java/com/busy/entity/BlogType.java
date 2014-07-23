











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class BlogType implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_BLOG_TYPE_ID = "BlogTypeId";
        public static final String PROP_TYPE_NAME = "TypeName";
        

        private Integer blogTypeId;
                
        private String typeName;
                
                 
        ArrayList<Blog> blogList; 
        
        

        public BlogType()
        {
            this.blogTypeId = 0; 
       this.typeName = ""; 
        
       blogList = null; 
        
       }
        
        public BlogType(Integer BlogTypeId, String TypeName)
        {
            this.blogTypeId = BlogTypeId;
       this.typeName = TypeName;
              
       blogList = null; 
        
       } 
        
             
        
            public Integer getBlogTypeId()
            {
                return this.blogTypeId;
            }
            
            public void setBlogTypeId(Integer BlogTypeId)
            {
                this.blogTypeId = BlogTypeId;
            }
            
            
        
            public String getTypeName()
            {
                return this.typeName;
            }
            
            public void setTypeName(String TypeName)
            {
                this.typeName = TypeName;
            }
            
            
         
        
        
            public ArrayList<Blog> getBlogList()
            {
                return this.blogList;
            }
            
            public void setBlogList(ArrayList<Blog> blogList)
            {
                this.blogList = blogList;
            }
        
            
    }

