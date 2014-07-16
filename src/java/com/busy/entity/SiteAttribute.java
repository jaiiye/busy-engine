


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class SiteAttribute implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_SITE_ATTRIBUTE_ID = "SiteAttributeId";
        public static final String PROP_KEY = "Key";
        public static final String PROP_VALUE = "Value";
        public static final String PROP_TYPE = "Type";
        public static final String PROP_SITE_ID = "SiteId";
        

        private Integer siteAttributeId;
        private String key;
        private String value;
        private String type;
        private Integer siteId;
        

        public SiteAttribute()
        {
            this.siteAttributeId = 0; 
       this.key = ""; 
       this.value = ""; 
       this.type = ""; 
       this.siteId = 0; 
        }
        
        public SiteAttribute(Integer SiteAttributeId, String Key, String Value, String Type, Integer SiteId)
        {
            this.siteAttributeId = SiteAttributeId;
       this.key = Key;
       this.value = Value;
       this.type = Type;
       this.siteId = SiteId;
        } 
        
             
        
            public Integer getSiteAttributeId()
            {
                return this.siteAttributeId;
            }
            
            public void setSiteAttributeId(Integer SiteAttributeId)
            {
                this.siteAttributeId = SiteAttributeId;
            }
        
            public String getKey()
            {
                return this.key;
            }
            
            public void setKey(String Key)
            {
                this.key = Key;
            }
        
            public String getValue()
            {
                return this.value;
            }
            
            public void setValue(String Value)
            {
                this.value = Value;
            }
        
            public String getType()
            {
                return this.type;
            }
            
            public void setType(String Type)
            {
                this.type = Type;
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

