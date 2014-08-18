



























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class SiteLanguage extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_SITE_LANGUAGE_ID = "SiteLanguageId";
        public static final String PROP_LANGUAGE_NAME = "LanguageName";
        public static final String PROP_LOCALE = "Locale";
        public static final String PROP_RTL = "Rtl";
        public static final String PROP_FLAG_FILE_NAME = "FlagFileName";
        public static final String PROP_SITE_ID = "SiteId";
        

        private Integer siteLanguageId;
                
        private String languageName;
                
        private String locale;
                
        private Integer rtl;
                
        private String flagFileName;
                
        private Integer siteId;
        private Site site;        
                 
        
        

        public SiteLanguage()
        {
            this.siteLanguageId = 0; 
       this.languageName = ""; 
       this.locale = ""; 
       this.rtl = 0; 
       this.flagFileName = ""; 
       this.siteId = 0; 
        
       
       }
       
       @Override
       public Integer getId()
       {
            
            return siteLanguageId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("siteLanguageId", siteLanguageId == null ? 0 : siteLanguageId);
                
            builder.add("languageName", languageName == null ? "" : languageName);
                
            builder.add("locale", locale == null ? "" : locale);
                
            builder.add("rtl", rtl == null ? 0 : rtl);
                
            builder.add("flagFileName", flagFileName == null ? "" : flagFileName);
                
            builder.add("siteId", siteId == null ? 0 : siteId);
        
        
    
     
     
     
     
     if(site != null) site.addJson(builder);
        
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(SiteLanguage.PROP_SITE_LANGUAGE_ID) || column.equals(SiteLanguage.PROP_LANGUAGE_NAME) || column.equals(SiteLanguage.PROP_LOCALE) || column.equals(SiteLanguage.PROP_RTL) || column.equals(SiteLanguage.PROP_FLAG_FILE_NAME) || column.equals(SiteLanguage.PROP_SITE_ID) )
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
            if (column.equals(SiteLanguage.PROP_SITE_LANGUAGE_ID) || column.equals(SiteLanguage.PROP_RTL) || column.equals(SiteLanguage.PROP_SITE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static SiteLanguage process(ResultSet rs) throws SQLException
        {        
            return new SiteLanguage(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(6));
        }
              
       public SiteLanguage(Integer SiteLanguageId, String LanguageName, String Locale, Integer Rtl, String FlagFileName, Integer SiteId)
       {
            this.siteLanguageId = SiteLanguageId;
       this.languageName = LanguageName;
       this.locale = Locale;
       this.rtl = Rtl;
       this.flagFileName = FlagFileName;
       this.siteId = SiteId;
              
       
       } 
        
             
        
            public Integer getSiteLanguageId()
            {
                return this.siteLanguageId;
            }
            
            public void setSiteLanguageId(Integer SiteLanguageId)
            {
                this.siteLanguageId = SiteLanguageId;
            }
            
            
        
            public String getLanguageName()
            {
                return this.languageName;
            }
            
            public void setLanguageName(String LanguageName)
            {
                this.languageName = LanguageName;
            }
            
            
        
            public String getLocale()
            {
                return this.locale;
            }
            
            public void setLocale(String Locale)
            {
                this.locale = Locale;
            }
            
            
        
            public Integer getRtl()
            {
                return this.rtl;
            }
            
            public void setRtl(Integer Rtl)
            {
                this.rtl = Rtl;
            }
            
            
        
            public String getFlagFileName()
            {
                return this.flagFileName;
            }
            
            public void setFlagFileName(String FlagFileName)
            {
                this.flagFileName = FlagFileName;
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

