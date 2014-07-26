











 










    package com.busy.engine.domain;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    
    public class StoreInfo extends AbstractEntity implements EntityItem<Integer>
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
       
       @Override
       public Integer getId()
       {
            
            return storeInfoId;
       }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(StoreInfo.PROP_STORE_INFO_ID) || column.equals(StoreInfo.PROP_LOGO_TITLE) || column.equals(StoreInfo.PROP_LOGO_IMAGE) || column.equals(StoreInfo.PROP_STORE_NAME) || column.equals(StoreInfo.PROP_COMPANY_NAME) || column.equals(StoreInfo.PROP_LOCALE) )
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
            if (column.equals(StoreInfo.PROP_STORE_INFO_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static StoreInfo process(ResultSet rs) throws SQLException
        {        
            return new StoreInfo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
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

