











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class ItemLocation implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ITEM_LOCATION_ID = "ItemLocationId";
        public static final String PROP_LATITUDE = "Latitude";
        public static final String PROP_LONGITUDE = "Longitude";
        public static final String PROP_ITEM_ID = "ItemId";
        public static final String PROP_ADDRESS_ID = "AddressId";
        public static final String PROP_CONTACT_ID = "ContactId";
        

        private Integer itemLocationId;
                
        private String latitude;
                
        private String longitude;
                
        private Integer itemId;
        private Item item;        
        private Integer addressId;
        private Address address;        
        private Integer contactId;
        private Contact contact;        
                 
        
        

        public ItemLocation()
        {
            this.itemLocationId = 0; 
       this.latitude = ""; 
       this.longitude = ""; 
       this.itemId = 0; 
       this.addressId = 0; 
       this.contactId = 0; 
        
       
       }
        
        public ItemLocation(Integer ItemLocationId, String Latitude, String Longitude, Integer ItemId, Integer AddressId, Integer ContactId)
        {
            this.itemLocationId = ItemLocationId;
       this.latitude = Latitude;
       this.longitude = Longitude;
       this.itemId = ItemId;
       this.addressId = AddressId;
       this.contactId = ContactId;
              
       
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
            
            
                   
            public Item getItem()
                {
                    return this.item;
                }

                public void setItem(Item item)
                {
                    this.item = item;
                }
                   
            
        
            public Integer getAddressId()
            {
                return this.addressId;
            }
            
            public void setAddressId(Integer AddressId)
            {
                this.addressId = AddressId;
            }
            
            
                   
            public Address getAddress()
                {
                    return this.address;
                }

                public void setAddress(Address address)
                {
                    this.address = address;
                }
                   
            
        
            public Integer getContactId()
            {
                return this.contactId;
            }
            
            public void setContactId(Integer ContactId)
            {
                this.contactId = ContactId;
            }
            
            
                   
            public Contact getContact()
                {
                    return this.contact;
                }

                public void setContact(Contact contact)
                {
                    this.contact = contact;
                }
                   
            
         
        
        
            
    }

