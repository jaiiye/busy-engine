


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class BlogType implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_BLOG_TYPE_ID = "BlogTypeId";
        public static final String PROP_TYPE_NAME = "TypeName";
        

        private Integer blogTypeId;
        private String typeName;
        

        public BlogType()
        {
            this.blogTypeId = 0; 
       this.typeName = ""; 
        }
        
        public BlogType(Integer BlogTypeId, String TypeName)
        {
            this.blogTypeId = BlogTypeId;
       this.typeName = TypeName;
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
           
            
    }

