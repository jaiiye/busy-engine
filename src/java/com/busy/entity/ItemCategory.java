


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class ItemCategory implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ITEM_CATEGORY_ID = "ItemCategoryId";
        public static final String PROP_CATEGORY_ID = "CategoryId";
        public static final String PROP_ITEM_ID = "ItemId";
        

        private Integer itemCategoryId;
        private Integer categoryId;
        private Integer itemId;
        

        public ItemCategory()
        {
            this.itemCategoryId = 0; 
       this.categoryId = 0; 
       this.itemId = 0; 
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
        
            public Integer getItemId()
            {
                return this.itemId;
            }
            
            public void setItemId(Integer ItemId)
            {
                this.itemId = ItemId;
            }
           
            
    }

