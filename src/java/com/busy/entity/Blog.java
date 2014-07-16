


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class Blog implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_BLOG_ID = "BlogId";
        public static final String PROP_TOPIC = "Topic";
        public static final String PROP_BLOG_TYPE_ID = "BlogTypeId";
        public static final String PROP_KNOWLEDGE_BASE_ID = "KnowledgeBaseId";
        

        private Integer blogId;
        private String topic;
        private Integer blogTypeId;
        private Integer knowledgeBaseId;
        

        public Blog()
        {
            this.blogId = 0; 
       this.topic = ""; 
       this.blogTypeId = 0; 
       this.knowledgeBaseId = 0; 
        }
        
        public Blog(Integer BlogId, String Topic, Integer BlogTypeId, Integer KnowledgeBaseId)
        {
            this.blogId = BlogId;
       this.topic = Topic;
       this.blogTypeId = BlogTypeId;
       this.knowledgeBaseId = KnowledgeBaseId;
        } 
        
             
        
            public Integer getBlogId()
            {
                return this.blogId;
            }
            
            public void setBlogId(Integer BlogId)
            {
                this.blogId = BlogId;
            }
        
            public String getTopic()
            {
                return this.topic;
            }
            
            public void setTopic(String Topic)
            {
                this.topic = Topic;
            }
        
            public Integer getBlogTypeId()
            {
                return this.blogTypeId;
            }
            
            public void setBlogTypeId(Integer BlogTypeId)
            {
                this.blogTypeId = BlogTypeId;
            }
        
            public Integer getKnowledgeBaseId()
            {
                return this.knowledgeBaseId;
            }
            
            public void setKnowledgeBaseId(Integer KnowledgeBaseId)
            {
                this.knowledgeBaseId = KnowledgeBaseId;
            }
           
            
    }

