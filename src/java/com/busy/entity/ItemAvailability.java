











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class ItemAvailability implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ITEM_AVAILABILITY_ID = "ItemAvailabilityId";
        public static final String PROP_TYPE = "Type";
        

        private Integer itemAvailabilityId;
                
        private String type;
                
                 
        ArrayList<OptionAvailability> optionAvailabilityList; 
        
        

        public ItemAvailability()
        {
            this.itemAvailabilityId = 0; 
       this.type = ""; 
        
       optionAvailabilityList = null; 
        
       }
        
        public ItemAvailability(Integer ItemAvailabilityId, String Type)
        {
            this.itemAvailabilityId = ItemAvailabilityId;
       this.type = Type;
              
       optionAvailabilityList = null; 
        
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
            
            
         
        
        
            public ArrayList<OptionAvailability> getOptionAvailabilityList()
            {
                return this.optionAvailabilityList;
            }
            
            public void setOptionAvailabilityList(ArrayList<OptionAvailability> optionAvailabilityList)
            {
                this.optionAvailabilityList = optionAvailabilityList;
            }
        
            
    }

