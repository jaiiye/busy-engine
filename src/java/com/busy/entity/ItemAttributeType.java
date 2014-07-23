











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class ItemAttributeType implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ITEM_ATTRIBUTE_TYPE_ID = "ItemAttributeTypeId";
        public static final String PROP_ATTRIBUTE_NAME = "AttributeName";
        public static final String PROP_DESCRIPTION = "Description";
        

        private Integer itemAttributeTypeId;
                
        private String attributeName;
                
        private String description;
                
                 
        ArrayList<ItemAttribute> itemAttributeList; 
        
        

        public ItemAttributeType()
        {
            this.itemAttributeTypeId = 0; 
       this.attributeName = ""; 
       this.description = ""; 
        
       itemAttributeList = null; 
        
       }
        
        public ItemAttributeType(Integer ItemAttributeTypeId, String AttributeName, String Description)
        {
            this.itemAttributeTypeId = ItemAttributeTypeId;
       this.attributeName = AttributeName;
       this.description = Description;
              
       itemAttributeList = null; 
        
       } 
        
             
        
            public Integer getItemAttributeTypeId()
            {
                return this.itemAttributeTypeId;
            }
            
            public void setItemAttributeTypeId(Integer ItemAttributeTypeId)
            {
                this.itemAttributeTypeId = ItemAttributeTypeId;
            }
            
            
        
            public String getAttributeName()
            {
                return this.attributeName;
            }
            
            public void setAttributeName(String AttributeName)
            {
                this.attributeName = AttributeName;
            }
            
            
        
            public String getDescription()
            {
                return this.description;
            }
            
            public void setDescription(String Description)
            {
                this.description = Description;
            }
            
            
         
        
        
            public ArrayList<ItemAttribute> getItemAttributeList()
            {
                return this.itemAttributeList;
            }
            
            public void setItemAttributeList(ArrayList<ItemAttribute> itemAttributeList)
            {
                this.itemAttributeList = itemAttributeList;
            }
        
            
    }

