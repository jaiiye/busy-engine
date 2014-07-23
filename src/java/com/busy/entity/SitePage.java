











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class SitePage implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_SITE_PAGE_ID = "SitePageId";
        public static final String PROP_SITE_ID = "SiteId";
        public static final String PROP_PAGE_ID = "PageId";
        

        private Integer sitePageId;
                
        private Integer siteId;
        private Site site;        
        private Integer pageId;
        private Page page;        
                 
        
        

        public SitePage()
        {
            this.sitePageId = 0; 
       this.siteId = 0; 
       this.pageId = 0; 
        
       
       }
        
        public SitePage(Integer SitePageId, Integer SiteId, Integer PageId)
        {
            this.sitePageId = SitePageId;
       this.siteId = SiteId;
       this.pageId = PageId;
              
       
       } 
        
             
        
            public Integer getSitePageId()
            {
                return this.sitePageId;
            }
            
            public void setSitePageId(Integer SitePageId)
            {
                this.sitePageId = SitePageId;
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
                   
            
        
            public Integer getPageId()
            {
                return this.pageId;
            }
            
            public void setPageId(Integer PageId)
            {
                this.pageId = PageId;
            }
            
            
                   
            public Page getPage()
                {
                    return this.page;
                }

                public void setPage(Page page)
                {
                    this.page = page;
                }
                   
            
         
        
        
            
    }

