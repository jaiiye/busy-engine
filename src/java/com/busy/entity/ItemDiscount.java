


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
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
        private Integer discountId;
        private Boolean applyToOptions;
        

        public ItemDiscount()
        {
            this.itemDiscountId = 0; 
       this.itemId = 0; 
       this.discountId = 0; 
       this.applyToOptions = null; 
        }
        
        public ItemDiscount(Integer ItemDiscountId, Integer ItemId, Integer DiscountId, Boolean ApplyToOptions)
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
        
            public Integer getDiscountId()
            {
                return this.discountId;
            }
            
            public void setDiscountId(Integer DiscountId)
            {
                this.discountId = DiscountId;
            }
        
            public Boolean getApplyToOptions()
            {
                return this.applyToOptions;
            }
            
            public void setApplyToOptions(Boolean ApplyToOptions)
            {
                this.applyToOptions = ApplyToOptions;
            }
           
            
    }

