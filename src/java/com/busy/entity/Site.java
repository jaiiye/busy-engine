


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
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
        public static final String PROP_STATUS = "Status";
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
        private Integer status;
        private String locale;
        private Integer templateId;
        

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
       this.status = 0; 
       this.locale = ""; 
       this.templateId = 0; 
        }
        
        public Site(Integer SiteId, String SiteName, String Domain, Integer Mode, String Url, String EmailHost, Integer EmailPort, String EmailUsername, String EmailPassword, Integer Status, String Locale, Integer TemplateId)
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
       this.status = Status;
       this.locale = Locale;
       this.templateId = TemplateId;
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
        
            public Integer getStatus()
            {
                return this.status;
            }
            
            public void setStatus(Integer Status)
            {
                this.status = Status;
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
           
            
    }

