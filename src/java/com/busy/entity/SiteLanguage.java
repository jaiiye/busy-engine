


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class SiteLanguage implements Serializable
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
        private Boolean rtl;
        private String flagFileName;
        private Integer siteId;
        

        public SiteLanguage()
        {
            this.siteLanguageId = 0; 
       this.languageName = ""; 
       this.locale = ""; 
       this.rtl = null; 
       this.flagFileName = ""; 
       this.siteId = 0; 
        }
        
        public SiteLanguage(Integer SiteLanguageId, String LanguageName, String Locale, Boolean Rtl, String FlagFileName, Integer SiteId)
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
        
            public Boolean getRtl()
            {
                return this.rtl;
            }
            
            public void setRtl(Boolean Rtl)
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
           
            
    }

