











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class ItemOption implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ITEM_OPTION_ID = "ItemOptionId";
        public static final String PROP_OPTION_NAME = "OptionName";
        public static final String PROP_DESCRIPTION = "Description";
        

        private Integer itemOptionId;
                
        private String optionName;
                
        private String description;
                
                 
        ArrayList<OptionAvailability> optionAvailabilityList; 
        
        

        public ItemOption()
        {
            this.itemOptionId = 0; 
       this.optionName = ""; 
       this.description = ""; 
        
       optionAvailabilityList = null; 
        
       }
        
        public ItemOption(Integer ItemOptionId, String OptionName, String Description)
        {
            this.itemOptionId = ItemOptionId;
       this.optionName = OptionName;
       this.description = Description;
              
       optionAvailabilityList = null; 
        
       } 
        
             
        
            public Integer getItemOptionId()
            {
                return this.itemOptionId;
            }
            
            public void setItemOptionId(Integer ItemOptionId)
            {
                this.itemOptionId = ItemOptionId;
            }
            
            
        
            public String getOptionName()
            {
                return this.optionName;
            }
            
            public void setOptionName(String OptionName)
            {
                this.optionName = OptionName;
            }
            
            
        
            public String getDescription()
            {
                return this.description;
            }
            
            public void setDescription(String Description)
            {
                this.description = Description;
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

