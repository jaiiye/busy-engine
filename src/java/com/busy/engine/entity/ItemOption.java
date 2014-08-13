











 











    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class ItemOption extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ITEM_OPTION_ID = "ItemOptionId";
        public static final String PROP_OPTION_NAME = "OptionName";
        public static final String PROP_DESCRIPTION = "Description";
        

        private Integer itemOptionId;
                
        private String optionName;
                
        private String description;
                
                 
        ArrayList<OptionAvailability> optionAvailabilityList; 
        
        

        public ItemOption()
        {
            this.itemOptionId = 0; 
       this.optionName = ""; 
       this.description = ""; 
        
       optionAvailabilityList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return itemOptionId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
        builder.add("itemOptionId", itemOptionId).add("optionName", optionName).add("description", description);
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemOption.PROP_ITEM_OPTION_ID) || column.equals(ItemOption.PROP_OPTION_NAME) || column.equals(ItemOption.PROP_DESCRIPTION) )
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
            if (column.equals(ItemOption.PROP_ITEM_OPTION_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ItemOption process(ResultSet rs) throws SQLException
        {        
            return new ItemOption(rs.getInt(1), rs.getString(2), rs.getString(3));
        }
              
       public ItemOption(Integer ItemOptionId, String OptionName, String Description)
       {
            this.itemOptionId = ItemOptionId;
       this.optionName = OptionName;
       this.description = Description;
              
       optionAvailabilityList = null; 
        
       } 
        
             
        
            public Integer getItemOptionId()
            {
                return this.itemOptionId;
            }
            
            public void setItemOptionId(Integer ItemOptionId)
            {
                this.itemOptionId = ItemOptionId;
            }
            
            
        
            public String getOptionName()
            {
                return this.optionName;
            }
            
            public void setOptionName(String OptionName)
            {
                this.optionName = OptionName;
            }
            
            
        
            public String getDescription()
            {
                return this.description;
            }
            
            public void setDescription(String Description)
            {
                this.description = Description;
            }
            
            
         
        
        
        public ArrayList<OptionAvailability> getOptionAvailabilityList()
            {
                return this.optionAvailabilityList;
            }
            
            public void setOptionAvailabilityList(ArrayList<OptionAvailability> optionAvailabilityList)
            {
                this.optionAvailabilityList = optionAvailabilityList;
            }
        
            
    }

