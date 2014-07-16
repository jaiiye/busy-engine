


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class ResourceType implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_RESOURCE_TYPE_ID = "ResourceTypeId";
        public static final String PROP_TYPE_NAME = "TypeName";
        public static final String PROP_VALUE = "Value";
        

        private Integer resourceTypeId;
        private String typeName;
        private String value;
        

        public ResourceType()
        {
            this.resourceTypeId = 0; 
       this.typeName = ""; 
       this.value = ""; 
        }
        
        public ResourceType(Integer ResourceTypeId, String TypeName, String Value)
        {
            this.resourceTypeId = ResourceTypeId;
       this.typeName = TypeName;
       this.value = Value;
        } 
        
             
        
            public Integer getResourceTypeId()
            {
                return this.resourceTypeId;
            }
            
            public void setResourceTypeId(Integer ResourceTypeId)
            {
                this.resourceTypeId = ResourceTypeId;
            }
        
            public String getTypeName()
            {
                return this.typeName;
            }
            
            public void setTypeName(String TypeName)
            {
                this.typeName = TypeName;
            }
        
            public String getValue()
            {
                return this.value;
            }
            
            public void setValue(String Value)
            {
                this.value = Value;
            }
           
            
    }

