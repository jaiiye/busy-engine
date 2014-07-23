











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class StoreInfo implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_STORE_INFO_ID = "StoreInfoId";
        public static final String PROP_LOGO_TITLE = "LogoTitle";
        public static final String PROP_LOGO_IMAGE = "LogoImage";
        public static final String PROP_STORE_NAME = "StoreName";
        public static final String PROP_COMPANY_NAME = "CompanyName";
        public static final String PROP_LOCALE = "Locale";
        

        private Integer storeInfoId;
                
        private String logoTitle;
                
        private String logoImage;
                
        private String storeName;
                
        private String companyName;
                
        private String locale;
                
                 
        
        

        public StoreInfo()
        {
            this.storeInfoId = 0; 
       this.logoTitle = ""; 
       this.logoImage = ""; 
       this.storeName = ""; 
       this.companyName = ""; 
       this.locale = ""; 
        
       
       }
        
        public StoreInfo(Integer StoreInfoId, String LogoTitle, String LogoImage, String StoreName, String CompanyName, String Locale)
        {
            this.storeInfoId = StoreInfoId;
       this.logoTitle = LogoTitle;
       this.logoImage = LogoImage;
       this.storeName = StoreName;
       this.companyName = CompanyName;
       this.locale = Locale;
              
       
       } 
        
             
        
            public Integer getStoreInfoId()
            {
                return this.storeInfoId;
            }
            
            public void setStoreInfoId(Integer StoreInfoId)
            {
                this.storeInfoId = StoreInfoId;
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
            
            
        
            public String getStoreName()
            {
                return this.storeName;
            }
            
            public void setStoreName(String StoreName)
            {
                this.storeName = StoreName;
            }
            
            
        
            public String getCompanyName()
            {
                return this.companyName;
            }
            
            public void setCompanyName(String CompanyName)
            {
                this.companyName = CompanyName;
            }
            
            
        
            public String getLocale()
            {
                return this.locale;
            }
            
            public void setLocale(String Locale)
            {
                this.locale = Locale;
            }
            
            
         
        
        
            
    }

