


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class Category implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_CATEGORY_ID = "CategoryId";
        public static final String PROP_CATEGORY_NAME = "CategoryName";
        public static final String PROP_DISCOUNT_ID = "DiscountId";
        

        private Integer categoryId;
        private String categoryName;
        private Integer discountId;
        

        public Category()
        {
            this.categoryId = 0; 
       this.categoryName = ""; 
       this.discountId = 0; 
        }
        
        public Category(Integer CategoryId, String CategoryName, Integer DiscountId)
        {
            this.categoryId = CategoryId;
       this.categoryName = CategoryName;
       this.discountId = DiscountId;
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
           
            
    }

