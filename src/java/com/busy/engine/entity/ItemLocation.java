



























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class ItemLocation extends AbstractEntity implements EntityItem<Integer>
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
       
       @Override
       public Integer getId()
       {
            
            return itemLocationId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("itemLocationId", itemLocationId == null ? 0 : itemLocationId);
                
            builder.add("latitude", latitude == null ? "" : latitude);
                
            builder.add("longitude", longitude == null ? "" : longitude);
                
            builder.add("itemId", itemId == null ? 0 : itemId);
                
            builder.add("addressId", addressId == null ? 0 : addressId);
                
            builder.add("contactId", contactId == null ? 0 : contactId);
        
        
    
     
     
     if(item != null) item.addJson(builder);
        
     if(address != null) address.addJson(builder);
        
     if(contact != null) contact.addJson(builder);
        
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(ItemLocation.PROP_ITEM_LOCATION_ID) || column.equals(ItemLocation.PROP_LATITUDE) || column.equals(ItemLocation.PROP_LONGITUDE) || column.equals(ItemLocation.PROP_ITEM_ID) || column.equals(ItemLocation.PROP_ADDRESS_ID) || column.equals(ItemLocation.PROP_CONTACT_ID) )
            {
                return column;
            }
            else
            {
                throw new SQLException("Invalid column name: " + column);
            }
        }
                
        public static void checkColumnSize(String column, int size) throws Exception
        {
            if (column.length() > size)
            {            
                throw new Exception("Invalid column length: " + size + "instead of " + column.length() + " for column: " + column);
            }
        }
                
        public static boolean isColumnNumeric(String column)
        {
            if (column.equals(ItemLocation.PROP_ITEM_LOCATION_ID) || column.equals(ItemLocation.PROP_ITEM_ID) || column.equals(ItemLocation.PROP_ADDRESS_ID) || column.equals(ItemLocation.PROP_CONTACT_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static ItemLocation process(ResultSet rs) throws SQLException
        {        
            return new ItemLocation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
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

