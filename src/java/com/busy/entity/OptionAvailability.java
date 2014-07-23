











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class OptionAvailability implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_OPTION_AVAILABILITY_ID = "OptionAvailabilityId";
        public static final String PROP_ITEM_ID = "ItemId";
        public static final String PROP_ITEM_OPTION_ID = "ItemOptionId";
        public static final String PROP_ITEM_AVAILABILITY_ID = "ItemAvailabilityId";
        public static final String PROP_AVAILABLE_QUANTITY = "AvailableQuantity";
        public static final String PROP_PRICE = "Price";
        public static final String PROP_AVAILABLE_FROM = "AvailableFrom";
        public static final String PROP_AVAILABLE_TO = "AvailableTo";
        public static final String PROP_MAXIMUM_QUANTITY = "MaximumQuantity";
        

        private Integer optionAvailabilityId;
                
        private Integer itemId;
        private Item item;        
        private Integer itemOptionId;
        private ItemOption itemOption;        
        private Integer itemAvailabilityId;
        private ItemAvailability itemAvailability;        
        private Integer availableQuantity;
                
        private Double price;
                
        private Date availableFrom;
                
        private Date availableTo;
                
        private Integer maximumQuantity;
                
                 
        
        

        public OptionAvailability()
        {
            this.optionAvailabilityId = 0; 
       this.itemId = 0; 
       this.itemOptionId = 0; 
       this.itemAvailabilityId = 0; 
       this.availableQuantity = 0; 
       this.price = 0.0; 
       this.availableFrom = null; 
       this.availableTo = null; 
       this.maximumQuantity = 0; 
        
       
       }
        
        public OptionAvailability(Integer OptionAvailabilityId, Integer ItemId, Integer ItemOptionId, Integer ItemAvailabilityId, Integer AvailableQuantity, Double Price, Date AvailableFrom, Date AvailableTo, Integer MaximumQuantity)
        {
            this.optionAvailabilityId = OptionAvailabilityId;
       this.itemId = ItemId;
       this.itemOptionId = ItemOptionId;
       this.itemAvailabilityId = ItemAvailabilityId;
       this.availableQuantity = AvailableQuantity;
       this.price = Price;
       this.availableFrom = AvailableFrom;
       this.availableTo = AvailableTo;
       this.maximumQuantity = MaximumQuantity;
              
       
       } 
        
             
        
            public Integer getOptionAvailabilityId()
            {
                return this.optionAvailabilityId;
            }
            
            public void setOptionAvailabilityId(Integer OptionAvailabilityId)
            {
                this.optionAvailabilityId = OptionAvailabilityId;
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
                   
            
        
            public Integer getItemOptionId()
            {
                return this.itemOptionId;
            }
            
            public void setItemOptionId(Integer ItemOptionId)
            {
                this.itemOptionId = ItemOptionId;
            }
            
            
                   
            public ItemOption getItemOption()
                {
                    return this.itemOption;
                }

                public void setItemOption(ItemOption itemOption)
                {
                    this.itemOption = itemOption;
                }
                   
            
        
            public Integer getItemAvailabilityId()
            {
                return this.itemAvailabilityId;
            }
            
            public void setItemAvailabilityId(Integer ItemAvailabilityId)
            {
                this.itemAvailabilityId = ItemAvailabilityId;
            }
            
            
                   
            public ItemAvailability getItemAvailability()
                {
                    return this.itemAvailability;
                }

                public void setItemAvailability(ItemAvailability itemAvailability)
                {
                    this.itemAvailability = itemAvailability;
                }
                   
            
        
            public Integer getAvailableQuantity()
            {
                return this.availableQuantity;
            }
            
            public void setAvailableQuantity(Integer AvailableQuantity)
            {
                this.availableQuantity = AvailableQuantity;
            }
            
            
        
            public Double getPrice()
            {
                return this.price;
            }
            
            public void setPrice(Double Price)
            {
                this.price = Price;
            }
            
            
        
            public Date getAvailableFrom()
            {
                return this.availableFrom;
            }
            
            public void setAvailableFrom(Date AvailableFrom)
            {
                this.availableFrom = AvailableFrom;
            }
            
            
        
            public Date getAvailableTo()
            {
                return this.availableTo;
            }
            
            public void setAvailableTo(Date AvailableTo)
            {
                this.availableTo = AvailableTo;
            }
            
            
        
            public Integer getMaximumQuantity()
            {
                return this.maximumQuantity;
            }
            
            public void setMaximumQuantity(Integer MaximumQuantity)
            {
                this.maximumQuantity = MaximumQuantity;
            }
            
            
         
        
        
            
    }

