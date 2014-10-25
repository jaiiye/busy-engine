






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class ItemAttribute extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ITEM_ATTRIBUTE_ID = "ItemAttributeId";
        public static final String PROP_KEY = "Key";
        public static final String PROP_VALUE = "Value";
        public static final String PROP_LOCALE = "Locale";
        public static final String PROP_ITEM_ATTRIBUTE_TYPE_ID = "ItemAttributeTypeId";
        public static final String PROP_ITEM_ID = "ItemId";
        

        private Integer itemAttributeId;
                
        private String key;
                
        private String value;
                
        private String locale;
                
        private Integer itemAttributeTypeId;
        private ItemAttributeType itemAttributeType;        
        private Integer itemId;
        private Item item;        
                 
        
        

        public ItemAttribute()
        {
            this.itemAttributeId = 0; 
       this.key = ""; 
       this.value = ""; 
       this.locale = ""; 
       this.itemAttributeTypeId = 0; 
       this.itemId = 0; 
        
       
       }
       
       @Override
       public Integer getId()
       {
            
            return itemAttributeId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("itemAttributeId", itemAttributeId == null ? 0 : itemAttributeId);
                
            builder.add("key", key == null ? "" : key);
                
            builder.add("value", value == null ? "" : value);
                
            builder.add("locale", locale == null ? "" : locale);
                
            builder.add("itemAttributeTypeId", itemAttributeTypeId == null ? 0 : itemAttributeTypeId);
                
            builder.add("itemId", itemId == null ? 0 : itemId);
        
        
    
     
     
     
     if(itemAttributeType != null) itemAttributeType.addJson(builder);
        
     if(item != null) item.addJson(builder);
        
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemAttribute.PROP_ITEM_ATTRIBUTE_ID) || column.equals(ItemAttribute.PROP_KEY) || column.equals(ItemAttribute.PROP_VALUE) || column.equals(ItemAttribute.PROP_LOCALE) || column.equals(ItemAttribute.PROP_ITEM_ATTRIBUTE_TYPE_ID) || column.equals(ItemAttribute.PROP_ITEM_ID) )
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
            if (column.equals(ItemAttribute.PROP_ITEM_ATTRIBUTE_ID) || column.equals(ItemAttribute.PROP_ITEM_ATTRIBUTE_TYPE_ID) || column.equals(ItemAttribute.PROP_ITEM_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ItemAttribute process(ResultSet rs) throws SQLException
        {           
            rs.first();
            return new ItemAttribute(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
        }
              
       public ItemAttribute(Integer ItemAttributeId, String Key, String Value, String Locale, Integer ItemAttributeTypeId, Integer ItemId)
       {
            this.itemAttributeId = ItemAttributeId;
       this.key = Key;
       this.value = Value;
       this.locale = Locale;
       this.itemAttributeTypeId = ItemAttributeTypeId;
       this.itemId = ItemId;
              
       
       } 
        
             
        
            public Integer getItemAttributeId()
            {
                return this.itemAttributeId;
            }
            
            public void setItemAttributeId(Integer ItemAttributeId)
            {
                this.itemAttributeId = ItemAttributeId;
            }
            
            
        
            public String getKey()
            {
                return this.key;
            }
            
            public void setKey(String Key)
            {
                this.key = Key;
            }
            
            
        
            public String getValue()
            {
                return this.value;
            }
            
            public void setValue(String Value)
            {
                this.value = Value;
            }
            
            
        
            public String getLocale()
            {
                return this.locale;
            }
            
            public void setLocale(String Locale)
            {
                this.locale = Locale;
            }
            
            
        
            public Integer getItemAttributeTypeId()
            {
                return this.itemAttributeTypeId;
            }
            
            public void setItemAttributeTypeId(Integer ItemAttributeTypeId)
            {
                this.itemAttributeTypeId = ItemAttributeTypeId;
            }
            
            
                   
            public ItemAttributeType getItemAttributeType()
                {
                    return this.itemAttributeType;
                }

                public void setItemAttributeType(ItemAttributeType itemAttributeType)
                {
                    this.itemAttributeType = itemAttributeType;
                }
                   
            
        
            public Integer getItemId()
            {
                return this.itemId;
            }
            
            public void setItemId(Integer ItemId)
            {
                this.itemId = ItemId;
            }
            
            
                   
            public Item getItem()
                {
                    return this.item;
                }

                public void setItem(Item item)
                {
                    this.item = item;
                }
                   
            
         
        
        
            
    }

