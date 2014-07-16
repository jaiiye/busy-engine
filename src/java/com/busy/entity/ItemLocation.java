


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class ItemLocation implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ITEM_LOCATION_ID = "ItemLocationId";
        public static final String PROP_LATITUDE = "Latitude";
        public static final String PROP_LONGITUDE = "Longitude";
        public static final String PROP_ITEM_ID = "ItemId";
        public static final String PROP_ADDRESS_ID = "AddressId";
        

        private Integer itemLocationId;
        private String latitude;
        private String longitude;
        private Integer itemId;
        private Integer addressId;
        

        public ItemLocation()
        {
            this.itemLocationId = 0; 
       this.latitude = ""; 
       this.longitude = ""; 
       this.itemId = 0; 
       this.addressId = 0; 
        }
        
        public ItemLocation(Integer ItemLocationId, String Latitude, String Longitude, Integer ItemId, Integer AddressId)
        {
            this.itemLocationId = ItemLocationId;
       this.latitude = Latitude;
       this.longitude = Longitude;
       this.itemId = ItemId;
       this.addressId = AddressId;
        } 
        
             
        
            public Integer getItemLocationId()
            {
                return this.itemLocationId;
            }
            
            public void setItemLocationId(Integer ItemLocationId)
            {
                this.itemLocationId = ItemLocationId;
            }
        
            public String getLatitude()
            {
                return this.latitude;
            }
            
            public void setLatitude(String Latitude)
            {
                this.latitude = Latitude;
            }
        
            public String getLongitude()
            {
                return this.longitude;
            }
            
            public void setLongitude(String Longitude)
            {
                this.longitude = Longitude;
            }
        
            public Integer getItemId()
            {
                return this.itemId;
            }
            
            public void setItemId(Integer ItemId)
            {
                this.itemId = ItemId;
            }
        
            public Integer getAddressId()
            {
                return this.addressId;
            }
            
            public void setAddressId(Integer AddressId)
            {
                this.addressId = AddressId;
            }
           
            
    }

