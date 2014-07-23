











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class ResourceType implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_RESOURCE_TYPE_ID = "ResourceTypeId";
        public static final String PROP_TYPE_NAME = "TypeName";
        public static final String PROP_TYPE_VALUE = "TypeValue";
        

        private Integer resourceTypeId;
                
        private String typeName;
                
        private String typeValue;
                
                 
        ArrayList<ResourceUrl> resourceUrlList; 
        
        

        public ResourceType()
        {
            this.resourceTypeId = 0; 
       this.typeName = ""; 
       this.typeValue = ""; 
        
       resourceUrlList = null; 
        
       }
        
        public ResourceType(Integer ResourceTypeId, String TypeName, String TypeValue)
        {
            this.resourceTypeId = ResourceTypeId;
       this.typeName = TypeName;
       this.typeValue = TypeValue;
              
       resourceUrlList = null; 
        
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
            
            
        
            public String getTypeValue()
            {
                return this.typeValue;
            }
            
            public void setTypeValue(String TypeValue)
            {
                this.typeValue = TypeValue;
            }
            
            
         
        
        
            public ArrayList<ResourceUrl> getResourceUrlList()
            {
                return this.resourceUrlList;
            }
            
            public void setResourceUrlList(ArrayList<ResourceUrl> resourceUrlList)
            {
                this.resourceUrlList = resourceUrlList;
            }
        
            
    }

