


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class MetaTag implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_META_TAG_ID = "MetaTagId";
        public static final String PROP_TITLE = "Title";
        public static final String PROP_DESCRIPTION = "Description";
        public static final String PROP_KEYWORDS = "Keywords";
        

        private Integer metaTagId;
        private String title;
        private String description;
        private String keywords;
        

        public MetaTag()
        {
            this.metaTagId = 0; 
       this.title = ""; 
       this.description = ""; 
       this.keywords = ""; 
        }
        
        public MetaTag(Integer MetaTagId, String Title, String Description, String Keywords)
        {
            this.metaTagId = MetaTagId;
       this.title = Title;
       this.description = Description;
       this.keywords = Keywords;
        } 
        
             
        
            public Integer getMetaTagId()
            {
                return this.metaTagId;
            }
            
            public void setMetaTagId(Integer MetaTagId)
            {
                this.metaTagId = MetaTagId;
            }
        
            public String getTitle()
            {
                return this.title;
            }
            
            public void setTitle(String Title)
            {
                this.title = Title;
            }
        
            public String getDescription()
            {
                return this.description;
            }
            
            public void setDescription(String Description)
            {
                this.description = Description;
            }
        
            public String getKeywords()
            {
                return this.keywords;
            }
            
            public void setKeywords(String Keywords)
            {
                this.keywords = Keywords;
            }
           
            
    }

