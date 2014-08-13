











 











    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class SliderType extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_SLIDER_TYPE_ID = "SliderTypeId";
        public static final String PROP_TYPE_NAME = "TypeName";
        public static final String PROP_CODE = "Code";
        

        private Integer sliderTypeId;
                
        private String typeName;
                
        private String code;
                
                 
        ArrayList<Slider> sliderList; 
        
        

        public SliderType()
        {
            this.sliderTypeId = 0; 
       this.typeName = ""; 
       this.code = ""; 
        
       sliderList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return sliderTypeId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
        builder.add("sliderTypeId", sliderTypeId).add("typeName", typeName).add("code", code);
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(SliderType.PROP_SLIDER_TYPE_ID) || column.equals(SliderType.PROP_TYPE_NAME) || column.equals(SliderType.PROP_CODE) )
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
            if (column.equals(SliderType.PROP_SLIDER_TYPE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static SliderType process(ResultSet rs) throws SQLException
        {        
            return new SliderType(rs.getInt(1), rs.getString(2), rs.getString(3));
        }
              
       public SliderType(Integer SliderTypeId, String TypeName, String Code)
       {
            this.sliderTypeId = SliderTypeId;
       this.typeName = TypeName;
       this.code = Code;
              
       sliderList = null; 
        
       } 
        
             
        
            public Integer getSliderTypeId()
            {
                return this.sliderTypeId;
            }
            
            public void setSliderTypeId(Integer SliderTypeId)
            {
                this.sliderTypeId = SliderTypeId;
            }
            
            
        
            public String getTypeName()
            {
                return this.typeName;
            }
            
            public void setTypeName(String TypeName)
            {
                this.typeName = TypeName;
            }
            
            
        
            public String getCode()
            {
                return this.code;
            }
            
            public void setCode(String Code)
            {
                this.code = Code;
            }
            
            
         
        
        
        public ArrayList<Slider> getSliderList()
            {
                return this.sliderList;
            }
            
            public void setSliderList(ArrayList<Slider> sliderList)
            {
                this.sliderList = sliderList;
            }
        
            
    }

