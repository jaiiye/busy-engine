






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class ItemCategory extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ITEM_CATEGORY_ID = "ItemCategoryId";
        public static final String PROP_CATEGORY_ID = "CategoryId";
        public static final String PROP_ITEM_ID = "ItemId";
        

        private Integer itemCategoryId;
                
        private Integer categoryId;
        private Category category;        
        private Integer itemId;
        private Item item;        
                 
        
        

        public ItemCategory()
        {
            this.itemCategoryId = 0; 
       this.categoryId = 0; 
       this.itemId = 0; 
        
       
       }
       
       @Override
       public Integer getId()
       {
            
            return itemCategoryId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("itemCategoryId", itemCategoryId == null ? 0 : itemCategoryId);
                
            builder.add("categoryId", categoryId == null ? 0 : categoryId);
                
            builder.add("itemId", itemId == null ? 0 : itemId);
        
        
    
     if(category != null) category.addJson(builder);
        
     if(item != null) item.addJson(builder);
        
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemCategory.PROP_ITEM_CATEGORY_ID) || column.equals(ItemCategory.PROP_CATEGORY_ID) || column.equals(ItemCategory.PROP_ITEM_ID) )
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
            if (column.equals(ItemCategory.PROP_ITEM_CATEGORY_ID) || column.equals(ItemCategory.PROP_CATEGORY_ID) || column.equals(ItemCategory.PROP_ITEM_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ItemCategory process(ResultSet rs) throws SQLException
        {           
            if(rs.getRow() == 0)
            {
                rs.first();
            }
            return new ItemCategory(rs.getInt(1), rs.getInt(2), rs.getInt(3));
        }
              
       public ItemCategory(Integer ItemCategoryId, Integer CategoryId, Integer ItemId)
       {
            this.itemCategoryId = ItemCategoryId;
       this.categoryId = CategoryId;
       this.itemId = ItemId;
              
       
       } 
        
             
        
            public Integer getItemCategoryId()
            {
                return this.itemCategoryId;
            }
            
            public void setItemCategoryId(Integer ItemCategoryId)
            {
                this.itemCategoryId = ItemCategoryId;
            }
            
            
        
            public Integer getCategoryId()
            {
                return this.categoryId;
            }
            
            public void setCategoryId(Integer CategoryId)
            {
                this.categoryId = CategoryId;
            }
            
            
                   
            public Category getCategory()
                {
                    return this.category;
                }

                public void setCategory(Category category)
                {
                    this.category = category;
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

