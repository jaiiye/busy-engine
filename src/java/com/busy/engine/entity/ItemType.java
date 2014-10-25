






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class ItemType extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ITEM_TYPE_ID = "ItemTypeId";
        public static final String PROP_TYPE_NAME = "TypeName";
        public static final String PROP_RANK = "Rank";
        

        private Integer itemTypeId;
                
        private String typeName;
                
        private Integer rank;
                
                 
        ArrayList<Item> itemList; 
        
        

        public ItemType()
        {
            this.itemTypeId = 0; 
       this.typeName = ""; 
       this.rank = 0; 
        
       itemList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return itemTypeId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("itemTypeId", itemTypeId == null ? 0 : itemTypeId);
                
            builder.add("typeName", typeName == null ? "" : typeName);
                
            builder.add("rank", rank == null ? 0 : rank);
        
        
    
     
     
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemType.PROP_ITEM_TYPE_ID) || column.equals(ItemType.PROP_TYPE_NAME) || column.equals(ItemType.PROP_RANK) )
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
            if (column.equals(ItemType.PROP_ITEM_TYPE_ID) || column.equals(ItemType.PROP_RANK) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ItemType process(ResultSet rs) throws SQLException
        {           
            rs.first();
            return new ItemType(rs.getInt(1), rs.getString(2), rs.getInt(3));
        }
              
       public ItemType(Integer ItemTypeId, String TypeName, Integer Rank)
       {
            this.itemTypeId = ItemTypeId;
       this.typeName = TypeName;
       this.rank = Rank;
              
       itemList = null; 
        
       } 
        
             
        
            public Integer getItemTypeId()
            {
                return this.itemTypeId;
            }
            
            public void setItemTypeId(Integer ItemTypeId)
            {
                this.itemTypeId = ItemTypeId;
            }
            
            
        
            public String getTypeName()
            {
                return this.typeName;
            }
            
            public void setTypeName(String TypeName)
            {
                this.typeName = TypeName;
            }
            
            
        
            public Integer getRank()
            {
                return this.rank;
            }
            
            public void setRank(Integer Rank)
            {
                this.rank = Rank;
            }
            
            
         
        
        
        public ArrayList<Item> getItemList()
            {
                return this.itemList;
            }
            
            public void setItemList(ArrayList<Item> itemList)
            {
                this.itemList = itemList;
            }
        
            
    }

