











 











    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class Blog extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_BLOG_ID = "BlogId";
        public static final String PROP_TOPIC = "Topic";
        public static final String PROP_BLOG_TYPE_ID = "BlogTypeId";
        public static final String PROP_KNOWLEDGE_BASE_ID = "KnowledgeBaseId";
        

        private Integer blogId;
                
        private String topic;
                
        private Integer blogTypeId;
        private BlogType blogType;        
        private Integer knowledgeBaseId;
        private KnowledgeBase knowledgeBase;        
                 
        ArrayList<BlogPost> blogPostList; 
        ArrayList<UserService> userServiceList; 
        
        

        public Blog()
        {
            this.blogId = 0; 
       this.topic = ""; 
       this.blogTypeId = 0; 
       this.knowledgeBaseId = 0; 
        
       blogPostList = null; 
        userServiceList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return blogId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
        builder.add("blogId", blogId).add("topic", topic).add("blogTypeId", blogTypeId).add("knowledgeBaseId", knowledgeBaseId);
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Blog.PROP_BLOG_ID) || column.equals(Blog.PROP_TOPIC) || column.equals(Blog.PROP_BLOG_TYPE_ID) || column.equals(Blog.PROP_KNOWLEDGE_BASE_ID) )
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
            if (column.equals(Blog.PROP_BLOG_ID) || column.equals(Blog.PROP_BLOG_TYPE_ID) || column.equals(Blog.PROP_KNOWLEDGE_BASE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Blog process(ResultSet rs) throws SQLException
        {        
            return new Blog(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
        }
              
       public Blog(Integer BlogId, String Topic, Integer BlogTypeId, Integer KnowledgeBaseId)
       {
            this.blogId = BlogId;
       this.topic = Topic;
       this.blogTypeId = BlogTypeId;
       this.knowledgeBaseId = KnowledgeBaseId;
              
       blogPostList = null; 
        userServiceList = null; 
        
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
            
            
                   
            public BlogType getBlogType()
                {
                    return this.blogType;
                }

                public void setBlogType(BlogType blogType)
                {
                    this.blogType = blogType;
                }
                   
            
        
            public Integer getKnowledgeBaseId()
            {
                return this.knowledgeBaseId;
            }
            
            public void setKnowledgeBaseId(Integer KnowledgeBaseId)
            {
                this.knowledgeBaseId = KnowledgeBaseId;
            }
            
            
                   
            public KnowledgeBase getKnowledgeBase()
                {
                    return this.knowledgeBase;
                }

                public void setKnowledgeBase(KnowledgeBase knowledgeBase)
                {
                    this.knowledgeBase = knowledgeBase;
                }
                   
            
         
        
        
        public ArrayList<BlogPost> getBlogPostList()
            {
                return this.blogPostList;
            }
            
            public void setBlogPostList(ArrayList<BlogPost> blogPostList)
            {
                this.blogPostList = blogPostList;
            }
        
        public ArrayList<UserService> getUserServiceList()
            {
                return this.userServiceList;
            }
            
            public void setUserServiceList(ArrayList<UserService> userServiceList)
            {
                this.userServiceList = userServiceList;
            }
        
            
    }

