











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class Site implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_SITE_ID = "SiteId";
        public static final String PROP_SITE_NAME = "SiteName";
        public static final String PROP_DOMAIN = "Domain";
        public static final String PROP_MODE = "Mode";
        public static final String PROP_URL = "Url";
        public static final String PROP_EMAIL_HOST = "EmailHost";
        public static final String PROP_EMAIL_PORT = "EmailPort";
        public static final String PROP_EMAIL_USERNAME = "EmailUsername";
        public static final String PROP_EMAIL_PASSWORD = "EmailPassword";
        public static final String PROP_SITE_STATUS = "SiteStatus";
        public static final String PROP_LOCALE = "Locale";
        public static final String PROP_TEMPLATE_ID = "TemplateId";
        

        private Integer siteId;
                
        private String siteName;
                
        private String domain;
                
        private Integer mode;
                
        private String url;
                
        private String emailHost;
                
        private Integer emailPort;
                
        private String emailUsername;
                
        private String emailPassword;
                
        private Integer siteStatus;
                
        private String locale;
                
        private Integer templateId;
        private Template template;        
                 
        ArrayList<SiteAttribute> siteAttributeList; 
        ArrayList<SiteFolder> siteFolderList; 
        ArrayList<SiteImage> siteImageList; 
        ArrayList<SiteItem> siteItemList; 
        ArrayList<SiteLanguage> siteLanguageList; 
        ArrayList<SitePage> sitePageList; 
        ArrayList<UserGroup> userGroupList; 
        
        

        public Site()
        {
            this.siteId = 0; 
       this.siteName = ""; 
       this.domain = ""; 
       this.mode = 0; 
       this.url = ""; 
       this.emailHost = ""; 
       this.emailPort = 0; 
       this.emailUsername = ""; 
       this.emailPassword = ""; 
       this.siteStatus = 0; 
       this.locale = ""; 
       this.templateId = 0; 
        
       siteAttributeList = null; 
        siteFolderList = null; 
        siteImageList = null; 
        siteItemList = null; 
        siteLanguageList = null; 
        sitePageList = null; 
        userGroupList = null; 
        
       }
        
        public Site(Integer SiteId, String SiteName, String Domain, Integer Mode, String Url, String EmailHost, Integer EmailPort, String EmailUsername, String EmailPassword, Integer SiteStatus, String Locale, Integer TemplateId)
        {
            this.siteId = SiteId;
       this.siteName = SiteName;
       this.domain = Domain;
       this.mode = Mode;
       this.url = Url;
       this.emailHost = EmailHost;
       this.emailPort = EmailPort;
       this.emailUsername = EmailUsername;
       this.emailPassword = EmailPassword;
       this.siteStatus = SiteStatus;
       this.locale = Locale;
       this.templateId = TemplateId;
              
       siteAttributeList = null; 
        siteFolderList = null; 
        siteImageList = null; 
        siteItemList = null; 
        siteLanguageList = null; 
        sitePageList = null; 
        userGroupList = null; 
        
       } 
        
             
        
            public Integer getSiteId()
            {
                return this.siteId;
            }
            
            public void setSiteId(Integer SiteId)
            {
                this.siteId = SiteId;
            }
            
            
        
            public String getSiteName()
            {
                return this.siteName;
            }
            
            public void setSiteName(String SiteName)
            {
                this.siteName = SiteName;
            }
            
            
        
            public String getDomain()
            {
                return this.domain;
            }
            
            public void setDomain(String Domain)
            {
                this.domain = Domain;
            }
            
            
        
            public Integer getMode()
            {
                return this.mode;
            }
            
            public void setMode(Integer Mode)
            {
                this.mode = Mode;
            }
            
            
        
            public String getUrl()
            {
                return this.url;
            }
            
            public void setUrl(String Url)
            {
                this.url = Url;
            }
            
            
        
            public String getEmailHost()
            {
                return this.emailHost;
            }
            
            public void setEmailHost(String EmailHost)
            {
                this.emailHost = EmailHost;
            }
            
            
        
            public Integer getEmailPort()
            {
                return this.emailPort;
            }
            
            public void setEmailPort(Integer EmailPort)
            {
                this.emailPort = EmailPort;
            }
            
            
        
            public String getEmailUsername()
            {
                return this.emailUsername;
            }
            
            public void setEmailUsername(String EmailUsername)
            {
                this.emailUsername = EmailUsername;
            }
            
            
        
            public String getEmailPassword()
            {
                return this.emailPassword;
            }
            
            public void setEmailPassword(String EmailPassword)
            {
                this.emailPassword = EmailPassword;
            }
            
            
        
            public Integer getSiteStatus()
            {
                return this.siteStatus;
            }
            
            public void setSiteStatus(Integer SiteStatus)
            {
                this.siteStatus = SiteStatus;
            }
            
            
        
            public String getLocale()
            {
                return this.locale;
            }
            
            public void setLocale(String Locale)
            {
                this.locale = Locale;
            }
            
            
        
            public Integer getTemplateId()
            {
                return this.templateId;
            }
            
            public void setTemplateId(Integer TemplateId)
            {
                this.templateId = TemplateId;
            }
            
            
                   
            public Template getTemplate()
                {
                    return this.template;
                }

                public void setTemplate(Template template)
                {
                    this.template = template;
                }
                   
            
         
        
        
            public ArrayList<SiteAttribute> getSiteAttributeList()
            {
                return this.siteAttributeList;
            }
            
            public void setSiteAttributeList(ArrayList<SiteAttribute> siteAttributeList)
            {
                this.siteAttributeList = siteAttributeList;
            }
        
            public ArrayList<SiteFolder> getSiteFolderList()
            {
                return this.siteFolderList;
            }
            
            public void setSiteFolderList(ArrayList<SiteFolder> siteFolderList)
            {
                this.siteFolderList = siteFolderList;
            }
        
            public ArrayList<SiteImage> getSiteImageList()
            {
                return this.siteImageList;
            }
            
            public void setSiteImageList(ArrayList<SiteImage> siteImageList)
            {
                this.siteImageList = siteImageList;
            }
        
            public ArrayList<SiteItem> getSiteItemList()
            {
                return this.siteItemList;
            }
            
            public void setSiteItemList(ArrayList<SiteItem> siteItemList)
            {
                this.siteItemList = siteItemList;
            }
        
            public ArrayList<SiteLanguage> getSiteLanguageList()
            {
                return this.siteLanguageList;
            }
            
            public void setSiteLanguageList(ArrayList<SiteLanguage> siteLanguageList)
            {
                this.siteLanguageList = siteLanguageList;
            }
        
            public ArrayList<SitePage> getSitePageList()
            {
                return this.sitePageList;
            }
            
            public void setSitePageList(ArrayList<SitePage> sitePageList)
            {
                this.sitePageList = sitePageList;
            }
        
            public ArrayList<UserGroup> getUserGroupList()
            {
                return this.userGroupList;
            }
            
            public void setUserGroupList(ArrayList<UserGroup> userGroupList)
            {
                this.userGroupList = userGroupList;
            }
        
            
    }

