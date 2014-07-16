


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class TextString implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_TEXT_STRING_ID = "TextStringId";
        public static final String PROP_KEY = "Key";
        

        private Integer textStringId;
        private String key;
        

        public TextString()
        {
            this.textStringId = 0; 
       this.key = ""; 
        }
        
        public TextString(Integer TextStringId, String Key)
        {
            this.textStringId = TextStringId;
       this.key = Key;
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
           
            
    }

