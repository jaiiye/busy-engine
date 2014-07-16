


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class LocalizedString implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_LOCALIZED_STRING_ID = "LocalizedStringId";
        public static final String PROP_LOCALE = "Locale";
        public static final String PROP_VALUE = "Value";
        public static final String PROP_TEXT_STRING_ID = "TextStringId";
        

        private Integer localizedStringId;
        private Integer locale;
        private String value;
        private Integer textStringId;
        

        public LocalizedString()
        {
            this.localizedStringId = 0; 
       this.locale = 0; 
       this.value = ""; 
       this.textStringId = 0; 
        }
        
        public LocalizedString(Integer LocalizedStringId, Integer Locale, String Value, Integer TextStringId)
        {
            this.localizedStringId = LocalizedStringId;
       this.locale = Locale;
       this.value = Value;
       this.textStringId = TextStringId;
        } 
        
             
        
            public Integer getLocalizedStringId()
            {
                return this.localizedStringId;
            }
            
            public void setLocalizedStringId(Integer LocalizedStringId)
            {
                this.localizedStringId = LocalizedStringId;
            }
        
            public Integer getLocale()
            {
                return this.locale;
            }
            
            public void setLocale(Integer Locale)
            {
                this.locale = Locale;
            }
        
            public String getValue()
            {
                return this.value;
            }
            
            public void setValue(String Value)
            {
                this.value = Value;
            }
        
            public Integer getTextStringId()
            {
                return this.textStringId;
            }
            
            public void setTextStringId(Integer TextStringId)
            {
                this.textStringId = TextStringId;
            }
           
            
    }

