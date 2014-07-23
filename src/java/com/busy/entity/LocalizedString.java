











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class LocalizedString implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_LOCALIZED_STRING_ID = "LocalizedStringId";
        public static final String PROP_LOCALE = "Locale";
        public static final String PROP_STRING_VALUE = "StringValue";
        public static final String PROP_TEXT_STRING_ID = "TextStringId";
        

        private Integer localizedStringId;
                
        private Integer locale;
                
        private String stringValue;
                
        private Integer textStringId;
        private TextString textString;        
                 
        ArrayList<TextStringLocal> textStringLocalList; 
        
        

        public LocalizedString()
        {
            this.localizedStringId = 0; 
       this.locale = 0; 
       this.stringValue = ""; 
       this.textStringId = 0; 
        
       textStringLocalList = null; 
        
       }
        
        public LocalizedString(Integer LocalizedStringId, Integer Locale, String StringValue, Integer TextStringId)
        {
            this.localizedStringId = LocalizedStringId;
       this.locale = Locale;
       this.stringValue = StringValue;
       this.textStringId = TextStringId;
              
       textStringLocalList = null; 
        
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
            
            
        
            public String getStringValue()
            {
                return this.stringValue;
            }
            
            public void setStringValue(String StringValue)
            {
                this.stringValue = StringValue;
            }
            
            
        
            public Integer getTextStringId()
            {
                return this.textStringId;
            }
            
            public void setTextStringId(Integer TextStringId)
            {
                this.textStringId = TextStringId;
            }
            
            
                   
            public TextString getTextString()
                {
                    return this.textString;
                }

                public void setTextString(TextString textString)
                {
                    this.textString = textString;
                }
                   
            
         
        
        
            public ArrayList<TextStringLocal> getTextStringLocalList()
            {
                return this.textStringLocalList;
            }
            
            public void setTextStringLocalList(ArrayList<TextStringLocal> textStringLocalList)
            {
                this.textStringLocalList = textStringLocalList;
            }
        
            
    }

