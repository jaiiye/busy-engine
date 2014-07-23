











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class SiteImage implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_SITE_IMAGE_ID = "SiteImageId";
        public static final String PROP_FILE_NAME = "FileName";
        public static final String PROP_DESCRIPTION = "Description";
        public static final String PROP_LINK_URL = "LinkUrl";
        public static final String PROP_RANK = "Rank";
        public static final String PROP_IMAGE_TYPE_ID = "ImageTypeId";
        public static final String PROP_SITE_ID = "SiteId";
        

        private Integer siteImageId;
                
        private String fileName;
                
        private String description;
                
        private String linkUrl;
                
        private Integer rank;
                
        private Integer imageTypeId;
        private ImageType imageType;        
        private Integer siteId;
        private Site site;        
                 
        
        

        public SiteImage()
        {
            this.siteImageId = 0; 
       this.fileName = ""; 
       this.description = ""; 
       this.linkUrl = ""; 
       this.rank = 0; 
       this.imageTypeId = 0; 
       this.siteId = 0; 
        
       
       }
        
        public SiteImage(Integer SiteImageId, String FileName, String Description, String LinkUrl, Integer Rank, Integer ImageTypeId, Integer SiteId)
        {
            this.siteImageId = SiteImageId;
       this.fileName = FileName;
       this.description = Description;
       this.linkUrl = LinkUrl;
       this.rank = Rank;
       this.imageTypeId = ImageTypeId;
       this.siteId = SiteId;
              
       
       } 
        
             
        
            public Integer getSiteImageId()
            {
                return this.siteImageId;
            }
            
            public void setSiteImageId(Integer SiteImageId)
            {
                this.siteImageId = SiteImageId;
            }
            
            
        
            public String getFileName()
            {
                return this.fileName;
            }
            
            public void setFileName(String FileName)
            {
                this.fileName = FileName;
            }
            
            
        
            public String getDescription()
            {
                return this.description;
            }
            
            public void setDescription(String Description)
            {
                this.description = Description;
            }
            
            
        
            public String getLinkUrl()
            {
                return this.linkUrl;
            }
            
            public void setLinkUrl(String LinkUrl)
            {
                this.linkUrl = LinkUrl;
            }
            
            
        
            public Integer getRank()
            {
                return this.rank;
            }
            
            public void setRank(Integer Rank)
            {
                this.rank = Rank;
            }
            
            
        
            public Integer getImageTypeId()
            {
                return this.imageTypeId;
            }
            
            public void setImageTypeId(Integer ImageTypeId)
            {
                this.imageTypeId = ImageTypeId;
            }
            
            
                   
            public ImageType getImageType()
                {
                    return this.imageType;
                }

                public void setImageType(ImageType imageType)
                {
                    this.imageType = imageType;
                }
                   
            
        
            public Integer getSiteId()
            {
                return this.siteId;
            }
            
            public void setSiteId(Integer SiteId)
            {
                this.siteId = SiteId;
            }
            
            
                   
            public Site getSite()
                {
                    return this.site;
                }

                public void setSite(Site site)
                {
                    this.site = site;
                }
                   
            
         
        
        
            
    }

