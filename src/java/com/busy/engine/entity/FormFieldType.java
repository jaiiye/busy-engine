



























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class FormFieldType extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_FORM_FIELD_TYPE_ID = "FormFieldTypeId";
        public static final String PROP_TYPE_NAME = "TypeName";
        public static final String PROP_INPUT_TYPE = "InputType";
        

        private Integer formFieldTypeId;
                
        private String typeName;
                
        private String inputType;
                
                 
        ArrayList<FormField> formFieldList; 
        
        

        public FormFieldType()
        {
            this.formFieldTypeId = 0; 
       this.typeName = ""; 
       this.inputType = ""; 
        
       formFieldList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return formFieldTypeId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("formFieldTypeId", formFieldTypeId == null ? 0 : formFieldTypeId);
                
            builder.add("typeName", typeName == null ? "" : typeName);
                
            builder.add("inputType", inputType == null ? "" : inputType);
        
        
    
     
     
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(FormFieldType.PROP_FORM_FIELD_TYPE_ID) || column.equals(FormFieldType.PROP_TYPE_NAME) || column.equals(FormFieldType.PROP_INPUT_TYPE) )
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
            if (column.equals(FormFieldType.PROP_FORM_FIELD_TYPE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static FormFieldType process(ResultSet rs) throws SQLException
        {        
            return new FormFieldType(rs.getInt(1), rs.getString(2), rs.getString(3));
        }
              
       public FormFieldType(Integer FormFieldTypeId, String TypeName, String InputType)
       {
            this.formFieldTypeId = FormFieldTypeId;
       this.typeName = TypeName;
       this.inputType = InputType;
              
       formFieldList = null; 
        
       } 
        
             
        
            public Integer getFormFieldTypeId()
            {
                return this.formFieldTypeId;
            }
            
            public void setFormFieldTypeId(Integer FormFieldTypeId)
            {
                this.formFieldTypeId = FormFieldTypeId;
            }
            
            
        
            public String getTypeName()
            {
                return this.typeName;
            }
            
            public void setTypeName(String TypeName)
            {
                this.typeName = TypeName;
            }
            
            
        
            public String getInputType()
            {
                return this.inputType;
            }
            
            public void setInputType(String InputType)
            {
                this.inputType = InputType;
            }
            
            
         
        
        
        public ArrayList<FormField> getFormFieldList()
            {
                return this.formFieldList;
            }
            
            public void setFormFieldList(ArrayList<FormField> formFieldList)
            {
                this.formFieldList = formFieldList;
            }
        
            
    }

