











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class ItemBrand implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ITEM_BRAND_ID = "ItemBrandId";
        public static final String PROP_NAME = "Name";
        public static final String PROP_DESCRIPTION = "Description";
        

        private Integer itemBrandId;
                
        private String name;
                
        private String description;
                
                 
        ArrayList<Item> itemList; 
        ArrayList<User> userList; 
        
        

        public ItemBrand()
        {
            this.itemBrandId = 0; 
       this.name = ""; 
       this.description = ""; 
        
       itemList = null; 
        userList = null; 
        
       }
        
        public ItemBrand(Integer ItemBrandId, String Name, String Description)
        {
            this.itemBrandId = ItemBrandId;
       this.name = Name;
       this.description = Description;
              
       itemList = null; 
        userList = null; 
        
       } 
        
             
        
            public Integer getItemBrandId()
            {
                return this.itemBrandId;
            }
            
            public void setItemBrandId(Integer ItemBrandId)
            {
                this.itemBrandId = ItemBrandId;
            }
            
            
        
            public String getName()
            {
                return this.name;
            }
            
            public void setName(String Name)
            {
                this.name = Name;
            }
            
            
        
            public String getDescription()
            {
                return this.description;
            }
            
            public void setDescription(String Description)
            {
                this.description = Description;
            }
            
            
         
        
        
            public ArrayList<Item> getItemList()
            {
                return this.itemList;
            }
            
            public void setItemList(ArrayList<Item> itemList)
            {
                this.itemList = itemList;
            }
        
            public ArrayList<User> getUserList()
            {
                return this.userList;
            }
            
            public void setUserList(ArrayList<User> userList)
            {
                this.userList = userList;
            }
        
            
    }

