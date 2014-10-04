






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class ItemBrand extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ITEM_BRAND_ID = "ItemBrandId";
        public static final String PROP_BRAND_NAME = "BrandName";
        public static final String PROP_DESCRIPTION = "Description";
        

        private Integer itemBrandId;
                
        private String brandName;
                
        private String description;
                
                 
        ArrayList<Item> itemList; 
        ArrayList<User> userList; 
        
        

        public ItemBrand()
        {
            this.itemBrandId = 0; 
       this.brandName = ""; 
       this.description = ""; 
        
       itemList = null; 
        userList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return itemBrandId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("itemBrandId", itemBrandId == null ? 0 : itemBrandId);
                
            builder.add("brandName", brandName == null ? "" : brandName);
                
            builder.add("description", description == null ? "" : description);
        
        
    
     
     
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemBrand.PROP_ITEM_BRAND_ID) || column.equals(ItemBrand.PROP_BRAND_NAME) || column.equals(ItemBrand.PROP_DESCRIPTION) )
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
            if (column.equals(ItemBrand.PROP_ITEM_BRAND_ID) || column.equals(ItemBrand.PROP_BRAND_NAME) || column.equals(ItemBrand.PROP_DESCRIPTION) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ItemBrand process(ResultSet rs) throws SQLException
        {        
            return new ItemBrand(rs.getInt(1), rs.getString(2), rs.getString(3));
        }
              
       public ItemBrand(Integer ItemBrandId, String BrandName, String Description)
       {
            this.itemBrandId = ItemBrandId;
       this.brandName = BrandName;
       this.description = Description;
              
       itemList = null; 
        userList = null; 
        
       } 
        
             
        
            public Integer getItemBrandId()
            {
                return this.itemBrandId;
            }
            
            public void setItemBrandId(Integer ItemBrandId)
            {
                this.itemBrandId = ItemBrandId;
            }
            
            
        
            public String getBrandName()
            {
                return this.brandName;
            }
            
            public void setBrandName(String BrandName)
            {
                this.brandName = BrandName;
            }
            
            
        
            public String getDescription()
            {
                return this.description;
            }
            
            public void setDescription(String Description)
            {
                this.description = Description;
            }
            
            
         
        
        
        public ArrayList<Item> getItemList()
            {
                return this.itemList;
            }
            
            public void setItemList(ArrayList<Item> itemList)
            {
                this.itemList = itemList;
            }
        
        public ArrayList<User> getUserList()
            {
                return this.userList;
            }
            
            public void setUserList(ArrayList<User> userList)
            {
                this.userList = userList;
            }
        
            
    }

