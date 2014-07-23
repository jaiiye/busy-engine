











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class ItemCategory implements Serializable
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

