











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class ItemAttribute implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ITEM_ATTRIBUTE_ID = "ItemAttributeId";
        public static final String PROP_KEY = "Key";
        public static final String PROP_VALUE = "Value";
        public static final String PROP_LOCALE = "Locale";
        public static final String PROP_ITEM_ATTRIBUTE_TYPE_ID = "ItemAttributeTypeId";
        public static final String PROP_ITEM_ID = "ItemId";
        

        private Integer itemAttributeId;
                
        private String key;
                
        private String value;
                
        private String locale;
                
        private Integer itemAttributeTypeId;
        private ItemAttributeType itemAttributeType;        
        private Integer itemId;
        private Item item;        
                 
        
        

        public ItemAttribute()
        {
            this.itemAttributeId = 0; 
       this.key = ""; 
       this.value = ""; 
       this.locale = ""; 
       this.itemAttributeTypeId = 0; 
       this.itemId = 0; 
        
       
       }
        
        public ItemAttribute(Integer ItemAttributeId, String Key, String Value, String Locale, Integer ItemAttributeTypeId, Integer ItemId)
        {
            this.itemAttributeId = ItemAttributeId;
       this.key = Key;
       this.value = Value;
       this.locale = Locale;
       this.itemAttributeTypeId = ItemAttributeTypeId;
       this.itemId = ItemId;
              
       
       } 
        
             
        
            public Integer getItemAttributeId()
            {
                return this.itemAttributeId;
            }
            
            public void setItemAttributeId(Integer ItemAttributeId)
            {
                this.itemAttributeId = ItemAttributeId;
            }
            
            
        
            public String getKey()
            {
                return this.key;
            }
            
            public void setKey(String Key)
            {
                this.key = Key;
            }
            
            
        
            public String getValue()
            {
                return this.value;
            }
            
            public void setValue(String Value)
            {
                this.value = Value;
            }
            
            
        
            public String getLocale()
            {
                return this.locale;
            }
            
            public void setLocale(String Locale)
            {
                this.locale = Locale;
            }
            
            
        
            public Integer getItemAttributeTypeId()
            {
                return this.itemAttributeTypeId;
            }
            
            public void setItemAttributeTypeId(Integer ItemAttributeTypeId)
            {
                this.itemAttributeTypeId = ItemAttributeTypeId;
            }
            
            
                   
            public ItemAttributeType getItemAttributeType()
                {
                    return this.itemAttributeType;
                }

                public void setItemAttributeType(ItemAttributeType itemAttributeType)
                {
                    this.itemAttributeType = itemAttributeType;
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

