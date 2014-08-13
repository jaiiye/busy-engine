











 











    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class Site extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_SITE_ID = "SiteId";
        public static final String PROP_SITE_NAME = "SiteName";
        public static final String PROP_DOMAIN = "Domain";
        public static final String PROP_MODE = "Mode";
        public static final String PROP_URL = "Url";
        public static final String PROP_LOGO_TITLE = "LogoTitle";
        public static final String PROP_LOGO_IMAGE = "LogoImage";
        public static final String PROP_USE_AS_STORE = "UseAsStore";
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
                
        private String logoTitle;
                
        private String logoImage;
                
        private Integer useAsStore;
                
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
       this.logoTitle = ""; 
       this.logoImage = ""; 
       this.useAsStore = 0; 
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
       
       @Override
       public Integer getId()
       {
            
            return siteId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
        builder.add("siteId", siteId).add("siteName", siteName).add("domain", domain).add("mode", mode).add("url", url).add("logoTitle", logoTitle).add("logoImage", logoImage).add("useAsStore", useAsStore).add("emailHost", emailHost).add("emailPort", emailPort).add("emailUsername", emailUsername).add("emailPassword", emailPassword).add("siteStatus", siteStatus).add("locale", locale).add("templateId", templateId);
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Site.PROP_SITE_ID) || column.equals(Site.PROP_SITE_NAME) || column.equals(Site.PROP_DOMAIN) || column.equals(Site.PROP_MODE) || column.equals(Site.PROP_URL) || column.equals(Site.PROP_LOGO_TITLE) || column.equals(Site.PROP_LOGO_IMAGE) || column.equals(Site.PROP_USE_AS_STORE) || column.equals(Site.PROP_EMAIL_HOST) || column.equals(Site.PROP_EMAIL_PORT) || column.equals(Site.PROP_EMAIL_USERNAME) || column.equals(Site.PROP_EMAIL_PASSWORD) || column.equals(Site.PROP_SITE_STATUS) || column.equals(Site.PROP_LOCALE) || column.equals(Site.PROP_TEMPLATE_ID) )
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
            if (column.equals(Site.PROP_SITE_ID) || column.equals(Site.PROP_MODE) || column.equals(Site.PROP_USE_AS_STORE) || column.equals(Site.PROP_EMAIL_PORT) || column.equals(Site.PROP_SITE_STATUS) || column.equals(Site.PROP_TEMPLATE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Site process(ResultSet rs) throws SQLException
        {        
            return new Site(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getString(12), rs.getInt(13), rs.getString(14), rs.getInt(15));
        }
              
       public Site(Integer SiteId, String SiteName, String Domain, Integer Mode, String Url, String LogoTitle, String LogoImage, Integer UseAsStore, String EmailHost, Integer EmailPort, String EmailUsername, String EmailPassword, Integer SiteStatus, String Locale, Integer TemplateId)
       {
            this.siteId = SiteId;
       this.siteName = SiteName;
       this.domain = Domain;
       this.mode = Mode;
       this.url = Url;
       this.logoTitle = LogoTitle;
       this.logoImage = LogoImage;
       this.useAsStore = UseAsStore;
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
            
            
        
            public String getLogoTitle()
            {
                return this.logoTitle;
            }
            
            public void setLogoTitle(String LogoTitle)
            {
                this.logoTitle = LogoTitle;
            }
            
            
        
            public String getLogoImage()
            {
                return this.logoImage;
            }
            
            public void setLogoImage(String LogoImage)
            {
                this.logoImage = LogoImage;
            }
            
            
        
            public Integer getUseAsStore()
            {
                return this.useAsStore;
            }
            
            public void setUseAsStore(Integer UseAsStore)
            {
                this.useAsStore = UseAsStore;
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

