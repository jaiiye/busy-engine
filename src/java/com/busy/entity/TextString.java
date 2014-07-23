











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class TextString implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_TEXT_STRING_ID = "TextStringId";
        public static final String PROP_KEY = "Key";
        

        private Integer textStringId;
                
        private String key;
                
                 
        ArrayList<LocalizedString> localizedStringList; 
        ArrayList<TextStringLocal> textStringLocalList; 
        
        

        public TextString()
        {
            this.textStringId = 0; 
       this.key = ""; 
        
       localizedStringList = null; 
        textStringLocalList = null; 
        
       }
        
        public TextString(Integer TextStringId, String Key)
        {
            this.textStringId = TextStringId;
       this.key = Key;
              
       localizedStringList = null; 
        textStringLocalList = null; 
        
       } 
        
             
        
            public Integer getTextStringId()
            {
                return this.textStringId;
            }
            
            public void setTextStringId(Integer TextStringId)
            {
                this.textStringId = TextStringId;
            }
            
            
        
            public String getKey()
            {
                return this.key;
            }
            
            public void setKey(String Key)
            {
                this.key = Key;
            }
            
            
         
        
        
            public ArrayList<LocalizedString> getLocalizedStringList()
            {
                return this.localizedStringList;
            }
            
            public void setLocalizedStringList(ArrayList<LocalizedString> localizedStringList)
            {
                this.localizedStringList = localizedStringList;
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

