











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class Locale implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_LOCALE_ID = "LocaleId";
        public static final String PROP_LOCALE_STRING = "LocaleString";
        public static final String PROP_LOCALE_CHARACTER_SET = "LocaleCharacterSet";
        

        private Integer localeId;
                
        private String localeString;
                
        private String localeCharacterSet;
                
                 
        
        

        public Locale()
        {
            this.localeId = 0; 
       this.localeString = ""; 
       this.localeCharacterSet = ""; 
        
       
       }
        
        public Locale(Integer LocaleId, String LocaleString, String LocaleCharacterSet)
        {
            this.localeId = LocaleId;
       this.localeString = LocaleString;
       this.localeCharacterSet = LocaleCharacterSet;
              
       
       } 
        
             
        
            public Integer getLocaleId()
            {
                return this.localeId;
            }
            
            public void setLocaleId(Integer LocaleId)
            {
                this.localeId = LocaleId;
            }
            
            
        
            public String getLocaleString()
            {
                return this.localeString;
            }
            
            public void setLocaleString(String LocaleString)
            {
                this.localeString = LocaleString;
            }
            
            
        
            public String getLocaleCharacterSet()
            {
                return this.localeCharacterSet;
            }
            
            public void setLocaleCharacterSet(String LocaleCharacterSet)
            {
                this.localeCharacterSet = LocaleCharacterSet;
            }
            
            
         
        
        
            
    }

