






























 






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
        public static final String PROP_LOGO_IMAGE_URL = "LogoImageUrl";
        public static final String PROP_USE_AS_STORE = "UseAsStore";
        public static final String PROP_SITE_STATUS = "SiteStatus";
        public static final String PROP_LOCALE = "Locale";
        public static final String PROP_TEMPLATE_ID = "TemplateId";
        public static final String PROP_SITE_EMAIL_ID = "SiteEmailId";
        public static final String PROP_DASHBOARD_ID = "DashboardId";
        public static final String PROP_TENANT_ID = "TenantId";
        

        private Integer siteId;
                
        private String siteName;
                
        private String domain;
                
        private Integer mode;
                
        private String url;
                
        private String logoTitle;
                
        private String logoImageUrl;
                
        private Integer useAsStore;
                
        private Integer siteStatus;
                
        private String locale;
                
        private Integer templateId;
        private Template template;        
        private Integer siteEmailId;
        private SiteEmail siteEmail;        
        private Integer dashboardId;
        private Dashboard dashboard;        
        private Integer tenantId;
        private Tenant tenant;        
                 
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
       this.logoImageUrl = ""; 
       this.useAsStore = 0; 
       this.siteStatus = 0; 
       this.locale = ""; 
       this.templateId = 0; 
       this.siteEmailId = 0; 
       this.dashboardId = 0; 
       this.tenantId = 0; 
        
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
                
            builder.add("siteId", siteId == null ? 0 : siteId);
                
            builder.add("siteName", siteName == null ? "" : siteName);
                
            builder.add("domain", domain == null ? "" : domain);
                
            builder.add("mode", mode == null ? 0 : mode);
                
            builder.add("url", url == null ? "" : url);
                
            builder.add("logoTitle", logoTitle == null ? "" : logoTitle);
                
            builder.add("logoImageUrl", logoImageUrl == null ? "" : logoImageUrl);
                
            builder.add("useAsStore", useAsStore == null ? 0 : useAsStore);
                
            builder.add("siteStatus", siteStatus == null ? 0 : siteStatus);
                
            builder.add("locale", locale == null ? "" : locale);
                
            builder.add("templateId", templateId == null ? 0 : templateId);
                
            builder.add("siteEmailId", siteEmailId == null ? 0 : siteEmailId);
                
            builder.add("dashboardId", dashboardId == null ? 0 : dashboardId);
                
            builder.add("tenantId", tenantId == null ? 0 : tenantId);
        
        
    
     
     
     
     
     
     
     
     
     
     if(template != null) template.addJson(builder);
        
     if(siteEmail != null) siteEmail.addJson(builder);
        
     if(dashboard != null) dashboard.addJson(builder);
        
     if(tenant != null) tenant.addJson(builder);
        
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Site.PROP_SITE_ID) || column.equals(Site.PROP_SITE_NAME) || column.equals(Site.PROP_DOMAIN) || column.equals(Site.PROP_MODE) || column.equals(Site.PROP_URL) || column.equals(Site.PROP_LOGO_TITLE) || column.equals(Site.PROP_LOGO_IMAGE_URL) || column.equals(Site.PROP_USE_AS_STORE) || column.equals(Site.PROP_SITE_STATUS) || column.equals(Site.PROP_LOCALE) || column.equals(Site.PROP_TEMPLATE_ID) || column.equals(Site.PROP_SITE_EMAIL_ID) || column.equals(Site.PROP_DASHBOARD_ID) || column.equals(Site.PROP_TENANT_ID) )
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
            if (column.equals(Site.PROP_SITE_ID) || column.equals(Site.PROP_MODE) || column.equals(Site.PROP_USE_AS_STORE) || column.equals(Site.PROP_SITE_STATUS) || column.equals(Site.PROP_TEMPLATE_ID) || column.equals(Site.PROP_SITE_EMAIL_ID) || column.equals(Site.PROP_DASHBOARD_ID) || column.equals(Site.PROP_TENANT_ID) )
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
            if(rs.getRow() == 0)
            {
                rs.first();
            }
            return new Site(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getString(10), rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14));
        }
              
       public Site(Integer SiteId, String SiteName, String Domain, Integer Mode, String Url, String LogoTitle, String LogoImageUrl, Integer UseAsStore, Integer SiteStatus, String Locale, Integer TemplateId, Integer SiteEmailId, Integer DashboardId, Integer TenantId)
       {
            this.siteId = SiteId;
       this.siteName = SiteName;
       this.domain = Domain;
       this.mode = Mode;
       this.url = Url;
       this.logoTitle = LogoTitle;
       this.logoImageUrl = LogoImageUrl;
       this.useAsStore = UseAsStore;
       this.siteStatus = SiteStatus;
       this.locale = Locale;
       this.templateId = TemplateId;
       this.siteEmailId = SiteEmailId;
       this.dashboardId = DashboardId;
       this.tenantId = TenantId;
              
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
            
            
        
            public String getLogoImageUrl()
            {
                return this.logoImageUrl;
            }
            
            public void setLogoImageUrl(String LogoImageUrl)
            {
                this.logoImageUrl = LogoImageUrl;
            }
            
            
        
            public Integer getUseAsStore()
            {
                return this.useAsStore;
            }
            
            public void setUseAsStore(Integer UseAsStore)
            {
                this.useAsStore = UseAsStore;
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
                   
            
        
            public Integer getSiteEmailId()
            {
                return this.siteEmailId;
            }
            
            public void setSiteEmailId(Integer SiteEmailId)
            {
                this.siteEmailId = SiteEmailId;
            }
            
            
                   
            public SiteEmail getSiteEmail()
                {
                    return this.siteEmail;
                }

                public void setSiteEmail(SiteEmail siteEmail)
                {
                    this.siteEmail = siteEmail;
                }
                   
            
        
            public Integer getDashboardId()
            {
                return this.dashboardId;
            }
            
            public void setDashboardId(Integer DashboardId)
            {
                this.dashboardId = DashboardId;
            }
            
            
                   
            public Dashboard getDashboard()
                {
                    return this.dashboard;
                }

                public void setDashboard(Dashboard dashboard)
                {
                    this.dashboard = dashboard;
                }
                   
            
        
            public Integer getTenantId()
            {
                return this.tenantId;
            }
            
            public void setTenantId(Integer TenantId)
            {
                this.tenantId = TenantId;
            }
            
            
                   
            public Tenant getTenant()
                {
                    return this.tenant;
                }

                public void setTenant(Tenant tenant)
                {
                    this.tenant = tenant;
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

