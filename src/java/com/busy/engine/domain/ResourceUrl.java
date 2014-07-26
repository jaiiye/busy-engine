











 










    package com.busy.engine.domain;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    
    public class ResourceUrl extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_RESOURCE_URL_ID = "ResourceUrlId";
        public static final String PROP_URL = "Url";
        public static final String PROP_TEMPLATE_ID = "TemplateId";
        public static final String PROP_RESOURCE_TYPE_ID = "ResourceTypeId";
        

        private Integer resourceUrlId;
                
        private String url;
                
        private Integer templateId;
        private Template template;        
        private Integer resourceTypeId;
        private ResourceType resourceType;        
                 
        
        

        public ResourceUrl()
        {
            this.resourceUrlId = 0; 
       this.url = ""; 
       this.templateId = 0; 
       this.resourceTypeId = 0; 
        
       
       }
       
       @Override
       public Integer getId()
       {
            
            return resourceUrlId;
       }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ResourceUrl.PROP_RESOURCE_URL_ID) || column.equals(ResourceUrl.PROP_URL) || column.equals(ResourceUrl.PROP_TEMPLATE_ID) || column.equals(ResourceUrl.PROP_RESOURCE_TYPE_ID) )
            {
                return column;
            }
            else
            {
                throw new SQLException("Invalid column name: " + column);
            }
        }
                
        public static void checkColumnSize(String column, int size) throws Exception
        {
            if (column.length() > size)
            {            
                throw new Exception("Invalid column length: " + size + "instead of " + column.length() + " for column: " + column);
            }
        }
                
        public static boolean isColumnNumeric(String column)
        {
            if (column.equals(ResourceUrl.PROP_RESOURCE_URL_ID) || column.equals(ResourceUrl.PROP_TEMPLATE_ID) || column.equals(ResourceUrl.PROP_RESOURCE_TYPE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ResourceUrl process(ResultSet rs) throws SQLException
        {        
            return new ResourceUrl(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
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
            
            
                   
            public Template getTemplate()
                {
                    return this.template;
                }

                public void setTemplate(Template template)
                {
                    this.template = template;
                }
                   
            
        
            public Integer getResourceTypeId()
            {
                return this.resourceTypeId;
            }
            
            public void setResourceTypeId(Integer ResourceTypeId)
            {
                this.resourceTypeId = ResourceTypeId;
            }
            
            
                   
            public ResourceType getResourceType()
                {
                    return this.resourceType;
                }

                public void setResourceType(ResourceType resourceType)
                {
                    this.resourceType = resourceType;
                }
                   
            
         
        
        
            
    }

