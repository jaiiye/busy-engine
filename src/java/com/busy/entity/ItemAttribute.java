


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class ItemAttribute implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ITEM_ATTRIBUTE_ID = "ItemAttributeId";
        public static final String PROP_KEY = "Key";
        public static final String PROP_VALUE = "Value";
        public static final String PROP_LOCALE = "Locale";
        public static final String PROP_ATTRIBUTE_TYPE_ID = "AttributeTypeId";
        public static final String PROP_ITEM_ID = "ItemId";
        

        private Integer itemAttributeId;
        private String key;
        private String value;
        private String locale;
        private Integer attributeTypeId;
        private Integer itemId;
        

        public ItemAttribute()
        {
            this.itemAttributeId = 0; 
       this.key = ""; 
       this.value = ""; 
       this.locale = ""; 
       this.attributeTypeId = 0; 
       this.itemId = 0; 
        }
        
        public ItemAttribute(Integer ItemAttributeId, String Key, String Value, String Locale, Integer AttributeTypeId, Integer ItemId)
        {
            this.itemAttributeId = ItemAttributeId;
       this.key = Key;
       this.value = Value;
       this.locale = Locale;
       this.attributeTypeId = AttributeTypeId;
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
        
            public Integer getAttributeTypeId()
            {
                return this.attributeTypeId;
            }
            
            public void setAttributeTypeId(Integer AttributeTypeId)
            {
                this.attributeTypeId = AttributeTypeId;
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

