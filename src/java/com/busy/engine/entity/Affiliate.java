



























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class Affiliate extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_AFFILIATE_ID = "AffiliateId";
        public static final String PROP_COMPANY_NAME = "CompanyName";
        public static final String PROP_EMAIL = "Email";
        public static final String PROP_PHONE = "Phone";
        public static final String PROP_FAX = "Fax";
        public static final String PROP_WEB_URL = "WebUrl";
        public static final String PROP_DETAILS = "Details";
        public static final String PROP_SERVICE_HOURS = "ServiceHours";
        public static final String PROP_AFFILIATE_STATUS = "AffiliateStatus";
        public static final String PROP_USER_ID = "UserId";
        public static final String PROP_CONTACT_ID = "ContactId";
        public static final String PROP_ADDRESS_ID = "AddressId";
        

        private Integer affiliateId;
                
        private String companyName;
                
        private String email;
                
        private String phone;
                
        private String fax;
                
        private String webUrl;
                
        private String details;
                
        private Integer serviceHours;
                
        private Integer affiliateStatus;
                
        private Integer userId;
        private User user;        
        private Integer contactId;
        private Contact contact;        
        private Integer addressId;
        private Address address;        
                 
        ArrayList<Order> orderList; 
        
        

        public Affiliate()
        {
            this.affiliateId = 0; 
       this.companyName = ""; 
       this.email = ""; 
       this.phone = ""; 
       this.fax = ""; 
       this.webUrl = ""; 
       this.details = ""; 
       this.serviceHours = 0; 
       this.affiliateStatus = 0; 
       this.userId = 0; 
       this.contactId = 0; 
       this.addressId = 0; 
        
       orderList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return affiliateId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("affiliateId", affiliateId == null ? 0 : affiliateId);
                
            builder.add("companyName", companyName == null ? "" : companyName);
                
            builder.add("email", email == null ? "" : email);
                
            builder.add("phone", phone == null ? "" : phone);
                
            builder.add("fax", fax == null ? "" : fax);
                
            builder.add("webUrl", webUrl == null ? "" : webUrl);
                
            builder.add("details", details == null ? "" : details);
                
            builder.add("serviceHours", serviceHours == null ? 0 : serviceHours);
                
            builder.add("affiliateStatus", affiliateStatus == null ? 0 : affiliateStatus);
                
            builder.add("userId", userId == null ? 0 : userId);
                
            builder.add("contactId", contactId == null ? 0 : contactId);
                
            builder.add("addressId", addressId == null ? 0 : addressId);
        
        
    
     
     
     
     
     
     
     
     
     if(user != null) user.addJson(builder);
        
     if(contact != null) contact.addJson(builder);
        
     if(address != null) address.addJson(builder);
        
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Affiliate.PROP_AFFILIATE_ID) || column.equals(Affiliate.PROP_COMPANY_NAME) || column.equals(Affiliate.PROP_EMAIL) || column.equals(Affiliate.PROP_PHONE) || column.equals(Affiliate.PROP_FAX) || column.equals(Affiliate.PROP_WEB_URL) || column.equals(Affiliate.PROP_DETAILS) || column.equals(Affiliate.PROP_SERVICE_HOURS) || column.equals(Affiliate.PROP_AFFILIATE_STATUS) || column.equals(Affiliate.PROP_USER_ID) || column.equals(Affiliate.PROP_CONTACT_ID) || column.equals(Affiliate.PROP_ADDRESS_ID) )
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
            if (column.equals(Affiliate.PROP_AFFILIATE_ID) || column.equals(Affiliate.PROP_SERVICE_HOURS) || column.equals(Affiliate.PROP_AFFILIATE_STATUS) || column.equals(Affiliate.PROP_USER_ID) || column.equals(Affiliate.PROP_CONTACT_ID) || column.equals(Affiliate.PROP_ADDRESS_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Affiliate process(ResultSet rs) throws SQLException
        {        
            return new Affiliate(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12));
        }
              
       public Affiliate(Integer AffiliateId, String CompanyName, String Email, String Phone, String Fax, String WebUrl, String Details, Integer ServiceHours, Integer AffiliateStatus, Integer UserId, Integer ContactId, Integer AddressId)
       {
            this.affiliateId = AffiliateId;
       this.companyName = CompanyName;
       this.email = Email;
       this.phone = Phone;
       this.fax = Fax;
       this.webUrl = WebUrl;
       this.details = Details;
       this.serviceHours = ServiceHours;
       this.affiliateStatus = AffiliateStatus;
       this.userId = UserId;
       this.contactId = ContactId;
       this.addressId = AddressId;
              
       orderList = null; 
        
       } 
        
             
        
            public Integer getAffiliateId()
            {
                return this.affiliateId;
            }
            
            public void setAffiliateId(Integer AffiliateId)
            {
                this.affiliateId = AffiliateId;
            }
            
            
        
            public String getCompanyName()
            {
                return this.companyName;
            }
            
            public void setCompanyName(String CompanyName)
            {
                this.companyName = CompanyName;
            }
            
            
        
            public String getEmail()
            {
                return this.email;
            }
            
            public void setEmail(String Email)
            {
                this.email = Email;
            }
            
            
        
            public String getPhone()
            {
                return this.phone;
            }
            
            public void setPhone(String Phone)
            {
                this.phone = Phone;
            }
            
            
        
            public String getFax()
            {
                return this.fax;
            }
            
            public void setFax(String Fax)
            {
                this.fax = Fax;
            }
            
            
        
            public String getWebUrl()
            {
                return this.webUrl;
            }
            
            public void setWebUrl(String WebUrl)
            {
                this.webUrl = WebUrl;
            }
            
            
        
            public String getDetails()
            {
                return this.details;
            }
            
            public void setDetails(String Details)
            {
                this.details = Details;
            }
            
            
        
            public Integer getServiceHours()
            {
                return this.serviceHours;
            }
            
            public void setServiceHours(Integer ServiceHours)
            {
                this.serviceHours = ServiceHours;
            }
            
            
        
            public Integer getAffiliateStatus()
            {
                return this.affiliateStatus;
            }
            
            public void setAffiliateStatus(Integer AffiliateStatus)
            {
                this.affiliateStatus = AffiliateStatus;
            }
            
            
        
            public Integer getUserId()
            {
                return this.userId;
            }
            
            public void setUserId(Integer UserId)
            {
                this.userId = UserId;
            }
            
            
                   
            public User getUser()
                {
                    return this.user;
                }

                public void setUser(User user)
                {
                    this.user = user;
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
                   
            
         
        
        
        public ArrayList<Order> getOrderList()
            {
                return this.orderList;
            }
            
            public void setOrderList(ArrayList<Order> orderList)
            {
                this.orderList = orderList;
            }
        
            
    }

