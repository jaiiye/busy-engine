






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class ItemAvailability extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ITEM_AVAILABILITY_ID = "ItemAvailabilityId";
        public static final String PROP_TYPE = "Type";
        

        private Integer itemAvailabilityId;
                
        private String type;
                
                 
        ArrayList<OptionAvailability> optionAvailabilityList; 
        
        

        public ItemAvailability()
        {
            this.itemAvailabilityId = 0; 
       this.type = ""; 
        
       optionAvailabilityList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return itemAvailabilityId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("itemAvailabilityId", itemAvailabilityId == null ? 0 : itemAvailabilityId);
                
            builder.add("type", type == null ? "" : type);
        
        
    
     
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemAvailability.PROP_ITEM_AVAILABILITY_ID) || column.equals(ItemAvailability.PROP_TYPE) )
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
            if (column.equals(ItemAvailability.PROP_ITEM_AVAILABILITY_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ItemAvailability process(ResultSet rs) throws SQLException
        {           
            if(rs.getRow() == 0)
            {
                rs.first();
            }
            return new ItemAvailability(rs.getInt(1), rs.getString(2));
        }
              
       public ItemAvailability(Integer ItemAvailabilityId, String Type)
       {
            this.itemAvailabilityId = ItemAvailabilityId;
       this.type = Type;
              
       optionAvailabilityList = null; 
        
       } 
        
             
        
            public Integer getItemAvailabilityId()
            {
                return this.itemAvailabilityId;
            }
            
            public void setItemAvailabilityId(Integer ItemAvailabilityId)
            {
                this.itemAvailabilityId = ItemAvailabilityId;
            }
            
            
        
            public String getType()
            {
                return this.type;
            }
            
            public void setType(String Type)
            {
                this.type = Type;
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

