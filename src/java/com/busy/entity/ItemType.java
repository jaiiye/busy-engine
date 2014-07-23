











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class ItemType implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ITEM_TYPE_ID = "ItemTypeId";
        public static final String PROP_TYPE_NAME = "TypeName";
        public static final String PROP_RANK = "Rank";
        

        private Integer itemTypeId;
                
        private String typeName;
                
        private Integer rank;
                
                 
        ArrayList<Item> itemList; 
        
        

        public ItemType()
        {
            this.itemTypeId = 0; 
       this.typeName = ""; 
       this.rank = 0; 
        
       itemList = null; 
        
       }
        
        public ItemType(Integer ItemTypeId, String TypeName, Integer Rank)
        {
            this.itemTypeId = ItemTypeId;
       this.typeName = TypeName;
       this.rank = Rank;
              
       itemList = null; 
        
       } 
        
             
        
            public Integer getItemTypeId()
            {
                return this.itemTypeId;
            }
            
            public void setItemTypeId(Integer ItemTypeId)
            {
                this.itemTypeId = ItemTypeId;
            }
            
            
        
            public String getTypeName()
            {
                return this.typeName;
            }
            
            public void setTypeName(String TypeName)
            {
                this.typeName = TypeName;
            }
            
            
        
            public Integer getRank()
            {
                return this.rank;
            }
            
            public void setRank(Integer Rank)
            {
                this.rank = Rank;
            }
            
            
         
        
        
            public ArrayList<Item> getItemList()
            {
                return this.itemList;
            }
            
            public void setItemList(ArrayList<Item> itemList)
            {
                this.itemList = itemList;
            }
        
            
    }

