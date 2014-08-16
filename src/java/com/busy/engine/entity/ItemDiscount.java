











 











    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class ItemDiscount extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ITEM_DISCOUNT_ID = "ItemDiscountId";
        public static final String PROP_ITEM_ID = "ItemId";
        public static final String PROP_DISCOUNT_ID = "DiscountId";
        public static final String PROP_APPLY_TO_OPTIONS = "ApplyToOptions";
        

        private Integer itemDiscountId;
                
        private Integer itemId;
        private Item item;        
        private Integer discountId;
        private Discount discount;        
        private Integer applyToOptions;
                
                 
        
        

        public ItemDiscount()
        {
            this.itemDiscountId = 0; 
       this.itemId = 0; 
       this.discountId = 0; 
       this.applyToOptions = 0; 
        
       
       }
       
       @Override
       public Integer getId()
       {
            
            return itemDiscountId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
        builder.add("itemDiscountId", itemDiscountId).add("itemId", itemId).add("discountId", discountId).add("applyToOptions", applyToOptions);
        
    if(item != null) item.addJson(builder);
        if(discount != null) discount.addJson(builder);
                 
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemDiscount.PROP_ITEM_DISCOUNT_ID) || column.equals(ItemDiscount.PROP_ITEM_ID) || column.equals(ItemDiscount.PROP_DISCOUNT_ID) || column.equals(ItemDiscount.PROP_APPLY_TO_OPTIONS) )
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
            if (column.equals(ItemDiscount.PROP_ITEM_DISCOUNT_ID) || column.equals(ItemDiscount.PROP_ITEM_ID) || column.equals(ItemDiscount.PROP_DISCOUNT_ID) || column.equals(ItemDiscount.PROP_APPLY_TO_OPTIONS) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ItemDiscount process(ResultSet rs) throws SQLException
        {        
            return new ItemDiscount(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
        }
              
       public ItemDiscount(Integer ItemDiscountId, Integer ItemId, Integer DiscountId, Integer ApplyToOptions)
       {
            this.itemDiscountId = ItemDiscountId;
       this.itemId = ItemId;
       this.discountId = DiscountId;
       this.applyToOptions = ApplyToOptions;
              
       
       } 
        
             
        
            public Integer getItemDiscountId()
            {
                return this.itemDiscountId;
            }
            
            public void setItemDiscountId(Integer ItemDiscountId)
            {
                this.itemDiscountId = ItemDiscountId;
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
                   
            
        
            public Integer getDiscountId()
            {
                return this.discountId;
            }
            
            public void setDiscountId(Integer DiscountId)
            {
                this.discountId = DiscountId;
            }
            
            
                   
            public Discount getDiscount()
                {
                    return this.discount;
                }

                public void setDiscount(Discount discount)
                {
                    this.discount = discount;
                }
                   
            
        
            public Integer getApplyToOptions()
            {
                return this.applyToOptions;
            }
            
            public void setApplyToOptions(Integer ApplyToOptions)
            {
                this.applyToOptions = ApplyToOptions;
            }
            
            
         
        
        
            
    }

