


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class SiteFolder implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_SITE_FOLDER_ID = "SiteFolderId";
        public static final String PROP_FOLDER_NAME = "FolderName";
        public static final String PROP_DESCRIPTION = "Description";
        public static final String PROP_RANK = "Rank";
        public static final String PROP_SITE_ID = "SiteId";
        

        private Integer siteFolderId;
        private String folderName;
        private String description;
        private Integer rank;
        private Integer siteId;
        

        public SiteFolder()
        {
            this.siteFolderId = 0; 
       this.folderName = ""; 
       this.description = ""; 
       this.rank = 0; 
       this.siteId = 0; 
        }
        
        public SiteFolder(Integer SiteFolderId, String FolderName, String Description, Integer Rank, Integer SiteId)
        {
            this.siteFolderId = SiteFolderId;
       this.folderName = FolderName;
       this.description = Description;
       this.rank = Rank;
       this.siteId = SiteId;
        } 
        
             
        
            public Integer getSiteFolderId()
            {
                return this.siteFolderId;
            }
            
            public void setSiteFolderId(Integer SiteFolderId)
            {
                this.siteFolderId = SiteFolderId;
            }
        
            public String getFolderName()
            {
                return this.folderName;
            }
            
            public void setFolderName(String FolderName)
            {
                this.folderName = FolderName;
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
        
            public Integer getSiteId()
            {
                return this.siteId;
            }
            
            public void setSiteId(Integer SiteId)
            {
                this.siteId = SiteId;
            }
           
            
    }

