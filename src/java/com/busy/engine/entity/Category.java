






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class Category extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_CATEGORY_ID = "CategoryId";
        public static final String PROP_CATEGORY_NAME = "CategoryName";
        public static final String PROP_DISCOUNT_ID = "DiscountId";
        public static final String PROP_PARENT_CATEGORY_ID = "ParentCategoryId";
        

        private Integer categoryId;
                
        private String categoryName;
                
        private Integer discountId;
        private Discount discount;        
        private Integer parentCategoryId;
        private Category parentCategory;        
                 
        ArrayList<ItemCategory> itemCategoryList; 
        
        

        public Category()
        {
            this.categoryId = 0; 
       this.categoryName = ""; 
       this.discountId = 0; 
       this.parentCategoryId = 0; 
        
       itemCategoryList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return categoryId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("categoryId", categoryId == null ? 0 : categoryId);
                
            builder.add("categoryName", categoryName == null ? "" : categoryName);
                
            builder.add("discountId", discountId == null ? 0 : discountId);
                
            builder.add("parentCategoryId", parentCategoryId == null ? 0 : parentCategoryId);
        
        
    
     
     if(discount != null) discount.addJson(builder);
        
     if(parentCategory != null) parentCategory.addJson(builder);
        
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Category.PROP_CATEGORY_ID) || column.equals(Category.PROP_CATEGORY_NAME) || column.equals(Category.PROP_DISCOUNT_ID) || column.equals(Category.PROP_PARENT_CATEGORY_ID) )
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
            if (column.equals(Category.PROP_CATEGORY_ID) || column.equals(Category.PROP_DISCOUNT_ID) || column.equals(Category.PROP_PARENT_CATEGORY_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Category process(ResultSet rs) throws SQLException
        {           
            if(rs.getRow() == 0)
            {
                rs.first();
            }
            return new Category(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
        }
              
       public Category(Integer CategoryId, String CategoryName, Integer DiscountId, Integer ParentCategoryId)
       {
            this.categoryId = CategoryId;
       this.categoryName = CategoryName;
       this.discountId = DiscountId;
       this.parentCategoryId = ParentCategoryId;
              
       itemCategoryList = null; 
        
       } 
        
             
        
            public Integer getCategoryId()
            {
                return this.categoryId;
            }
            
            public void setCategoryId(Integer CategoryId)
            {
                this.categoryId = CategoryId;
            }
            
            
        
            public String getCategoryName()
            {
                return this.categoryName;
            }
            
            public void setCategoryName(String CategoryName)
            {
                this.categoryName = CategoryName;
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
                   
            
        
            public Integer getParentCategoryId()
            {
                return this.parentCategoryId;
            }
            
            public void setParentCategoryId(Integer ParentCategoryId)
            {
                this.parentCategoryId = ParentCategoryId;
            }
            
            
                   
            public Category getParentCategory()
                {
                    return this.parentCategory;
                }

                public void setParentCategory(Category parentCategory)
                {
                    this.parentCategory = parentCategory;
                }
                   
            
         
        
        
        public ArrayList<ItemCategory> getItemCategoryList()
            {
                return this.itemCategoryList;
            }
            
            public void setItemCategoryList(ArrayList<ItemCategory> itemCategoryList)
            {
                this.itemCategoryList = itemCategoryList;
            }
        
            
    }

