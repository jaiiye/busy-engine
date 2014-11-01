






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class Address extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ADDRESS_ID = "AddressId";
        public static final String PROP_RECIPIENT = "Recipient";
        public static final String PROP_ADDRESS1 = "Address1";
        public static final String PROP_ADDRESS2 = "Address2";
        public static final String PROP_CITY = "City";
        public static final String PROP_STATE_PROVINCE = "StateProvince";
        public static final String PROP_ZIP_POSTAL_CODE = "ZipPostalCode";
        public static final String PROP_COUNTRY = "Country";
        public static final String PROP_REGION = "Region";
        public static final String PROP_ADDRESS_STATUS = "AddressStatus";
        public static final String PROP_LOCALE = "Locale";
        

        private Integer addressId;
                
        private String recipient;
                
        private String address1;
                
        private String address2;
                
        private String city;
                
        private String stateProvince;
                
        private String zipPostalCode;
                
        private String country;
                
        private String region;
                
        private Integer addressStatus;
                
        private String locale;
                
                 
        ArrayList<Affiliate> affiliateList; 
        ArrayList<ItemLocation> itemLocationList; 
        ArrayList<User> userList; 
        
        

        public Address()
        {
            this.addressId = 0; 
       this.recipient = ""; 
       this.address1 = ""; 
       this.address2 = ""; 
       this.city = ""; 
       this.stateProvince = ""; 
       this.zipPostalCode = ""; 
       this.country = ""; 
       this.region = ""; 
       this.addressStatus = 0; 
       this.locale = ""; 
        
       affiliateList = null; 
        itemLocationList = null; 
        userList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return addressId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("addressId", addressId == null ? 0 : addressId);
                
            builder.add("recipient", recipient == null ? "" : recipient);
                
            builder.add("address1", address1 == null ? "" : address1);
                
            builder.add("address2", address2 == null ? "" : address2);
                
            builder.add("city", city == null ? "" : city);
                
            builder.add("stateProvince", stateProvince == null ? "" : stateProvince);
                
            builder.add("zipPostalCode", zipPostalCode == null ? "" : zipPostalCode);
                
            builder.add("country", country == null ? "" : country);
                
            builder.add("region", region == null ? "" : region);
                
            builder.add("addressStatus", addressStatus == null ? 0 : addressStatus);
                
            builder.add("locale", locale == null ? "" : locale);
        
        
    
     
     
     
     
     
     
     
     
     
     
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Address.PROP_ADDRESS_ID) || column.equals(Address.PROP_RECIPIENT) || column.equals(Address.PROP_ADDRESS1) || column.equals(Address.PROP_ADDRESS2) || column.equals(Address.PROP_CITY) || column.equals(Address.PROP_STATE_PROVINCE) || column.equals(Address.PROP_ZIP_POSTAL_CODE) || column.equals(Address.PROP_COUNTRY) || column.equals(Address.PROP_REGION) || column.equals(Address.PROP_ADDRESS_STATUS) || column.equals(Address.PROP_LOCALE) )
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
            if (column.equals(Address.PROP_ADDRESS_ID) || column.equals(Address.PROP_ADDRESS_STATUS) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Address process(ResultSet rs) throws SQLException
        {           
            if(rs.getRow() == 0)
            {
                rs.first();
            }
            return new Address(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getString(11));
        }
              
       public Address(Integer AddressId, String Recipient, String Address1, String Address2, String City, String StateProvince, String ZipPostalCode, String Country, String Region, Integer AddressStatus, String Locale)
       {
            this.addressId = AddressId;
       this.recipient = Recipient;
       this.address1 = Address1;
       this.address2 = Address2;
       this.city = City;
       this.stateProvince = StateProvince;
       this.zipPostalCode = ZipPostalCode;
       this.country = Country;
       this.region = Region;
       this.addressStatus = AddressStatus;
       this.locale = Locale;
              
       affiliateList = null; 
        itemLocationList = null; 
        userList = null; 
        
       } 
        
             
        
            public Integer getAddressId()
            {
                return this.addressId;
            }
            
            public void setAddressId(Integer AddressId)
            {
                this.addressId = AddressId;
            }
            
            
        
            public String getRecipient()
            {
                return this.recipient;
            }
            
            public void setRecipient(String Recipient)
            {
                this.recipient = Recipient;
            }
            
            
        
            public String getAddress1()
            {
                return this.address1;
            }
            
            public void setAddress1(String Address1)
            {
                this.address1 = Address1;
            }
            
            
        
            public String getAddress2()
            {
                return this.address2;
            }
            
            public void setAddress2(String Address2)
            {
                this.address2 = Address2;
            }
            
            
        
            public String getCity()
            {
                return this.city;
            }
            
            public void setCity(String City)
            {
                this.city = City;
            }
            
            
        
            public String getStateProvince()
            {
                return this.stateProvince;
            }
            
            public void setStateProvince(String StateProvince)
            {
                this.stateProvince = StateProvince;
            }
            
            
        
            public String getZipPostalCode()
            {
                return this.zipPostalCode;
            }
            
            public void setZipPostalCode(String ZipPostalCode)
            {
                this.zipPostalCode = ZipPostalCode;
            }
            
            
        
            public String getCountry()
            {
                return this.country;
            }
            
            public void setCountry(String Country)
            {
                this.country = Country;
            }
            
            
        
            public String getRegion()
            {
                return this.region;
            }
            
            public void setRegion(String Region)
            {
                this.region = Region;
            }
            
            
        
            public Integer getAddressStatus()
            {
                return this.addressStatus;
            }
            
            public void setAddressStatus(Integer AddressStatus)
            {
                this.addressStatus = AddressStatus;
            }
            
            
        
            public String getLocale()
            {
                return this.locale;
            }
            
            public void setLocale(String Locale)
            {
                this.locale = Locale;
            }
            
            
         
        
        
        public ArrayList<Affiliate> getAffiliateList()
            {
                return this.affiliateList;
            }
            
            public void setAffiliateList(ArrayList<Affiliate> affiliateList)
            {
                this.affiliateList = affiliateList;
            }
        
        public ArrayList<ItemLocation> getItemLocationList()
            {
                return this.itemLocationList;
            }
            
            public void setItemLocationList(ArrayList<ItemLocation> itemLocationList)
            {
                this.itemLocationList = itemLocationList;
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

