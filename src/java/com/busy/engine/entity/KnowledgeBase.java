






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class KnowledgeBase extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_KNOWLEDGE_BASE_ID = "KnowledgeBaseId";
        public static final String PROP_KNOWLEDGE_BASE_NAME = "KnowledgeBaseName";
        public static final String PROP_DESCRIPTION = "Description";
        public static final String PROP_RANK = "Rank";
        public static final String PROP_LAST_MODIFIED = "LastModified";
        public static final String PROP_LATEST_TOPIC = "LatestTopic";
        public static final String PROP_LATEST_POST = "LatestPost";
        

        private Integer knowledgeBaseId;
                
        private String knowledgeBaseName;
                
        private String description;
                
        private Integer rank;
                
        private Date lastModified;
                
        private Integer latestTopic;
                
        private Integer latestPost;
                
                 
        ArrayList<Blog> blogList; 
        
        

        public KnowledgeBase()
        {
            this.knowledgeBaseId = 0; 
       this.knowledgeBaseName = ""; 
       this.description = ""; 
       this.rank = 0; 
       this.lastModified = null; 
       this.latestTopic = 0; 
       this.latestPost = 0; 
        
       blogList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return knowledgeBaseId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("knowledgeBaseId", knowledgeBaseId == null ? 0 : knowledgeBaseId);
                
            builder.add("knowledgeBaseName", knowledgeBaseName == null ? "" : knowledgeBaseName);
                
            builder.add("description", description == null ? "" : description);
                
            builder.add("rank", rank == null ? 0 : rank);
                
            builder.add("lastModified", lastModified == null ? "" : new SimpleDateFormat("yyyyMMdd").format(lastModified));
                
            builder.add("latestTopic", latestTopic == null ? 0 : latestTopic);
                
            builder.add("latestPost", latestPost == null ? 0 : latestPost);
        
        
    
     
     
     
     
     
     
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(KnowledgeBase.PROP_KNOWLEDGE_BASE_ID) || column.equals(KnowledgeBase.PROP_KNOWLEDGE_BASE_NAME) || column.equals(KnowledgeBase.PROP_DESCRIPTION) || column.equals(KnowledgeBase.PROP_RANK) || column.equals(KnowledgeBase.PROP_LAST_MODIFIED) || column.equals(KnowledgeBase.PROP_LATEST_TOPIC) || column.equals(KnowledgeBase.PROP_LATEST_POST) )
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
            if (column.equals(KnowledgeBase.PROP_KNOWLEDGE_BASE_ID) || column.equals(KnowledgeBase.PROP_RANK) || column.equals(KnowledgeBase.PROP_LATEST_TOPIC) || column.equals(KnowledgeBase.PROP_LATEST_POST) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static KnowledgeBase process(ResultSet rs) throws SQLException
        {           
            rs.first();
            return new KnowledgeBase(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDate(5), rs.getInt(6), rs.getInt(7));
        }
              
       public KnowledgeBase(Integer KnowledgeBaseId, String KnowledgeBaseName, String Description, Integer Rank, Date LastModified, Integer LatestTopic, Integer LatestPost)
       {
            this.knowledgeBaseId = KnowledgeBaseId;
       this.knowledgeBaseName = KnowledgeBaseName;
       this.description = Description;
       this.rank = Rank;
       this.lastModified = LastModified;
       this.latestTopic = LatestTopic;
       this.latestPost = LatestPost;
              
       blogList = null; 
        
       } 
        
             
        
            public Integer getKnowledgeBaseId()
            {
                return this.knowledgeBaseId;
            }
            
            public void setKnowledgeBaseId(Integer KnowledgeBaseId)
            {
                this.knowledgeBaseId = KnowledgeBaseId;
            }
            
            
        
            public String getKnowledgeBaseName()
            {
                return this.knowledgeBaseName;
            }
            
            public void setKnowledgeBaseName(String KnowledgeBaseName)
            {
                this.knowledgeBaseName = KnowledgeBaseName;
            }
            
            
        
            public String getDescription()
            {
                return this.description;
            }
            
            public void setDescription(String Description)
            {
                this.description = Description;
            }
            
            
        
            public Integer getRank()
            {
                return this.rank;
            }
            
            public void setRank(Integer Rank)
            {
                this.rank = Rank;
            }
            
            
        
            public Date getLastModified()
            {
                return this.lastModified;
            }
            
            public void setLastModified(Date LastModified)
            {
                this.lastModified = LastModified;
            }
            
            
        
            public Integer getLatestTopic()
            {
                return this.latestTopic;
            }
            
            public void setLatestTopic(Integer LatestTopic)
            {
                this.latestTopic = LatestTopic;
            }
            
            
        
            public Integer getLatestPost()
            {
                return this.latestPost;
            }
            
            public void setLatestPost(Integer LatestPost)
            {
                this.latestPost = LatestPost;
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

