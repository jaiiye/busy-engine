


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class ItemAvailability implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ITEM_AVAILABILITY_ID = "ItemAvailabilityId";
        public static final String PROP_TYPE = "Type";
        

        private Integer itemAvailabilityId;
        private String type;
        

        public ItemAvailability()
        {
            this.itemAvailabilityId = 0; 
       this.type = ""; 
        }
        
        public ItemAvailability(Integer ItemAvailabilityId, String Type)
        {
            this.itemAvailabilityId = ItemAvailabilityId;
       this.type = Type;
        } 
        
             
        
            public Integer getItemAvailabilityId()
            {
                return this.itemAvailabilityId;
            }
            
            public void setItemAvailabilityId(Integer ItemAvailabilityId)
            {
                this.itemAvailabilityId = ItemAvailabilityId;
            }
        
            public String getType()
            {
                return this.type;
            }
            
            public void setType(String Type)
            {
                this.type = Type;
            }
           
            
    }

