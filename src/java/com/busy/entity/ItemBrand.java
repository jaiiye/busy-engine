


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
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
        

        public ItemBrand()
        {
            this.itemBrandId = 0; 
       this.name = ""; 
       this.description = ""; 
        }
        
        public ItemBrand(Integer ItemBrandId, String Name, String Description)
        {
            this.itemBrandId = ItemBrandId;
       this.name = Name;
       this.description = Description;
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
           
            
    }

