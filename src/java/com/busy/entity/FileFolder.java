


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class FileFolder implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_FILE_FOLDER_ID = "FileFolderId";
        public static final String PROP_SITE_FILE_ID = "SiteFileId";
        public static final String PROP_SITE_FOLDER_ID = "SiteFolderId";
        

        private Integer fileFolderId;
        private Integer siteFileId;
        private Integer siteFolderId;
        

        public FileFolder()
        {
            this.fileFolderId = 0; 
       this.siteFileId = 0; 
       this.siteFolderId = 0; 
        }
        
        public FileFolder(Integer FileFolderId, Integer SiteFileId, Integer SiteFolderId)
        {
            this.fileFolderId = FileFolderId;
       this.siteFileId = SiteFileId;
       this.siteFolderId = SiteFolderId;
        } 
        
             
        
            public Integer getFileFolderId()
            {
                return this.fileFolderId;
            }
            
            public void setFileFolderId(Integer FileFolderId)
            {
                this.fileFolderId = FileFolderId;
            }
        
            public Integer getSiteFileId()
            {
                return this.siteFileId;
            }
            
            public void setSiteFileId(Integer SiteFileId)
            {
                this.siteFileId = SiteFileId;
            }
        
            public Integer getSiteFolderId()
            {
                return this.siteFolderId;
            }
            
            public void setSiteFolderId(Integer SiteFolderId)
            {
                this.siteFolderId = SiteFolderId;
            }
           
            
    }

