











 










    package com.busy.engine.domain;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    
    public class ItemAttributeType extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ITEM_ATTRIBUTE_TYPE_ID = "ItemAttributeTypeId";
        public static final String PROP_ATTRIBUTE_NAME = "AttributeName";
        public static final String PROP_DESCRIPTION = "Description";
        

        private Integer itemAttributeTypeId;
                
        private String attributeName;
                
        private String description;
                
                 
        ArrayList<ItemAttribute> itemAttributeList; 
        
        

        public ItemAttributeType()
        {
            this.itemAttributeTypeId = 0; 
       this.attributeName = ""; 
       this.description = ""; 
        
       itemAttributeList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return itemAttributeTypeId;
       }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemAttributeType.PROP_ITEM_ATTRIBUTE_TYPE_ID) || column.equals(ItemAttributeType.PROP_ATTRIBUTE_NAME) || column.equals(ItemAttributeType.PROP_DESCRIPTION) )
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
            if (column.equals(ItemAttributeType.PROP_ITEM_ATTRIBUTE_TYPE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ItemAttributeType process(ResultSet rs) throws SQLException
        {        
            return new ItemAttributeType(rs.getInt(1), rs.getString(2), rs.getString(3));
        }
              
       public ItemAttributeType(Integer ItemAttributeTypeId, String AttributeName, String Description)
       {
            this.itemAttributeTypeId = ItemAttributeTypeId;
       this.attributeName = AttributeName;
       this.description = Description;
              
       itemAttributeList = null; 
        
       } 
        
             
        
            public Integer getItemAttributeTypeId()
            {
                return this.itemAttributeTypeId;
            }
            
            public void setItemAttributeTypeId(Integer ItemAttributeTypeId)
            {
                this.itemAttributeTypeId = ItemAttributeTypeId;
            }
            
            
        
            public String getAttributeName()
            {
                return this.attributeName;
            }
            
            public void setAttributeName(String AttributeName)
            {
                this.attributeName = AttributeName;
            }
            
            
        
            public String getDescription()
            {
                return this.description;
            }
            
            public void setDescription(String Description)
            {
                this.description = Description;
            }
            
            
         
        
        
            public ArrayList<ItemAttribute> getItemAttributeList()
            {
                return this.itemAttributeList;
            }
            
            public void setItemAttributeList(ArrayList<ItemAttribute> itemAttributeList)
            {
                this.itemAttributeList = itemAttributeList;
            }
        
            
    }

