


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class KnowledgeBase implements Serializable
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
        

        public KnowledgeBase()
        {
            this.knowledgeBaseId = 0; 
       this.knowledgeBaseName = ""; 
       this.description = ""; 
       this.rank = 0; 
       this.lastModified = null; 
       this.latestTopic = 0; 
       this.latestPost = 0; 
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
           
            
    }

