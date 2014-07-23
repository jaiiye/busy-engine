











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class SiteAttribute implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_SITE_ATTRIBUTE_ID = "SiteAttributeId";
        public static final String PROP_ATTRIBUTE_KEY = "AttributeKey";
        public static final String PROP_ATTRIBUTE_VALUE = "AttributeValue";
        public static final String PROP_ATTRIBUTE_TYPE = "AttributeType";
        public static final String PROP_SITE_ID = "SiteId";
        

        private Integer siteAttributeId;
                
        private String attributeKey;
                
        private String attributeValue;
                
        private String attributeType;
                
        private Integer siteId;
        private Site site;        
                 
        
        

        public SiteAttribute()
        {
            this.siteAttributeId = 0; 
       this.attributeKey = ""; 
       this.attributeValue = ""; 
       this.attributeType = ""; 
       this.siteId = 0; 
        
       
       }
        
        public SiteAttribute(Integer SiteAttributeId, String AttributeKey, String AttributeValue, String AttributeType, Integer SiteId)
        {
            this.siteAttributeId = SiteAttributeId;
       this.attributeKey = AttributeKey;
       this.attributeValue = AttributeValue;
       this.attributeType = AttributeType;
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
            
            
        
            public String getAttributeKey()
            {
                return this.attributeKey;
            }
            
            public void setAttributeKey(String AttributeKey)
            {
                this.attributeKey = AttributeKey;
            }
            
            
        
            public String getAttributeValue()
            {
                return this.attributeValue;
            }
            
            public void setAttributeValue(String AttributeValue)
            {
                this.attributeValue = AttributeValue;
            }
            
            
        
            public String getAttributeType()
            {
                return this.attributeType;
            }
            
            public void setAttributeType(String AttributeType)
            {
                this.attributeType = AttributeType;
            }
            
            
        
            public Integer getSiteId()
            {
                return this.siteId;
            }
            
            public void setSiteId(Integer SiteId)
            {
                this.siteId = SiteId;
            }
            
            
                   
            public Site getSite()
                {
                    return this.site;
                }

                public void setSite(Site site)
                {
                    this.site = site;
                }
                   
            
         
        
        
            
    }

