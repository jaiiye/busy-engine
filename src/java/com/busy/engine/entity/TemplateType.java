






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class TemplateType extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_TEMPLATE_TYPE_ID = "TemplateTypeId";
        public static final String PROP_TYPE_NAME = "TypeName";
        public static final String PROP_TYPE_VALUE = "TypeValue";
        

        private Integer templateTypeId;
                
        private String typeName;
                
        private String typeValue;
                
                 
        ArrayList<Template> templateList; 
        
        

        public TemplateType()
        {
            this.templateTypeId = 0; 
       this.typeName = ""; 
       this.typeValue = ""; 
        
       templateList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return templateTypeId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("templateTypeId", templateTypeId == null ? 0 : templateTypeId);
                
            builder.add("typeName", typeName == null ? "" : typeName);
                
            builder.add("typeValue", typeValue == null ? "" : typeValue);
        
        
    
     
     
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(TemplateType.PROP_TEMPLATE_TYPE_ID) || column.equals(TemplateType.PROP_TYPE_NAME) || column.equals(TemplateType.PROP_TYPE_VALUE) )
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
            if (column.equals(TemplateType.PROP_TEMPLATE_TYPE_ID) || column.equals(TemplateType.PROP_TYPE_NAME) || column.equals(TemplateType.PROP_TYPE_VALUE) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static TemplateType process(ResultSet rs) throws SQLException
        {        
            return new TemplateType(rs.getInt(1), rs.getString(2), rs.getString(3));
        }
              
       public TemplateType(Integer TemplateTypeId, String TypeName, String TypeValue)
       {
            this.templateTypeId = TemplateTypeId;
       this.typeName = TypeName;
       this.typeValue = TypeValue;
              
       templateList = null; 
        
       } 
        
             
        
            public Integer getTemplateTypeId()
            {
                return this.templateTypeId;
            }
            
            public void setTemplateTypeId(Integer TemplateTypeId)
            {
                this.templateTypeId = TemplateTypeId;
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
            
            
         
        
        
        public ArrayList<Template> getTemplateList()
            {
                return this.templateList;
            }
            
            public void setTemplateList(ArrayList<Template> templateList)
            {
                this.templateList = templateList;
            }
        
            
    }

