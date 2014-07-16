


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class ResourceUrl implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_RESOURCE_URL_ID = "ResourceUrlId";
        public static final String PROP_URL = "Url";
        public static final String PROP_TEMPLATE_ID = "TemplateId";
        public static final String PROP_RESOURCE_TYPE_ID = "ResourceTypeId";
        

        private Integer resourceUrlId;
        private String url;
        private Integer templateId;
        private Integer resourceTypeId;
        

        public ResourceUrl()
        {
            this.resourceUrlId = 0; 
       this.url = ""; 
       this.templateId = 0; 
       this.resourceTypeId = 0; 
        }
        
        public ResourceUrl(Integer ResourceUrlId, String Url, Integer TemplateId, Integer ResourceTypeId)
        {
            this.resourceUrlId = ResourceUrlId;
       this.url = Url;
       this.templateId = TemplateId;
       this.resourceTypeId = ResourceTypeId;
        } 
        
             
        
            public Integer getResourceUrlId()
            {
                return this.resourceUrlId;
            }
            
            public void setResourceUrlId(Integer ResourceUrlId)
            {
                this.resourceUrlId = ResourceUrlId;
            }
        
            public String getUrl()
            {
                return this.url;
            }
            
            public void setUrl(String Url)
            {
                this.url = Url;
            }
        
            public Integer getTemplateId()
            {
                return this.templateId;
            }
            
            public void setTemplateId(Integer TemplateId)
            {
                this.templateId = TemplateId;
            }
        
            public Integer getResourceTypeId()
            {
                return this.resourceTypeId;
            }
            
            public void setResourceTypeId(Integer ResourceTypeId)
            {
                this.resourceTypeId = ResourceTypeId;
            }
           
            
    }

