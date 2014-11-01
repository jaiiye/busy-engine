






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class ResourceType extends AbstractEntity implements EntityItem<Integer>
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
       
       @Override
       public Integer getId()
       {
            
            return resourceTypeId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("resourceTypeId", resourceTypeId == null ? 0 : resourceTypeId);
                
            builder.add("typeName", typeName == null ? "" : typeName);
                
            builder.add("typeValue", typeValue == null ? "" : typeValue);
        
        
    
     
     
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ResourceType.PROP_RESOURCE_TYPE_ID) || column.equals(ResourceType.PROP_TYPE_NAME) || column.equals(ResourceType.PROP_TYPE_VALUE) )
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
            if (column.equals(ResourceType.PROP_RESOURCE_TYPE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ResourceType process(ResultSet rs) throws SQLException
        {           
            if(rs.getRow() == 0)
            {
                rs.first();
            }
            return new ResourceType(rs.getInt(1), rs.getString(2), rs.getString(3));
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

