


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class TextStringLocal implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_LOCALIZED_STRING_ID = "LocalizedStringId";
        public static final String PROP_VALUE = "Value";
        public static final String PROP_LOCALE = "Locale";
        public static final String PROP_TEXT_STRING_ID = "TextStringId";
        

        private Integer localizedStringId;
        private String value;
        private String locale;
        private Integer textStringId;
        

        public TextStringLocal()
        {
            this.localizedStringId = 0; 
       this.value = ""; 
       this.locale = ""; 
       this.textStringId = 0; 
        }
        
        public TextStringLocal(Integer LocalizedStringId, String Value, String Locale, Integer TextStringId)
        {
            this.localizedStringId = LocalizedStringId;
       this.value = Value;
       this.locale = Locale;
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
        
            public String getValue()
            {
                return this.value;
            }
            
            public void setValue(String Value)
            {
                this.value = Value;
            }
        
            public String getLocale()
            {
                return this.locale;
            }
            
            public void setLocale(String Locale)
            {
                this.locale = Locale;
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

