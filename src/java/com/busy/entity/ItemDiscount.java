











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class ItemDiscount implements Serializable
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

