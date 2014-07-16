


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class Affiliate implements Serializable
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
        public static final String PROP_STATUS = "Status";
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
        private Integer status;
        private Integer userId;
        private Integer contactId;
        private Integer addressId;
        

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
       this.status = 0; 
       this.userId = 0; 
       this.contactId = 0; 
       this.addressId = 0; 
        }
        
        public Affiliate(Integer AffiliateId, String CompanyName, String Email, String Phone, String Fax, String WebUrl, String Details, Integer ServiceHours, Integer Status, Integer UserId, Integer ContactId, Integer AddressId)
        {
            this.affiliateId = AffiliateId;
       this.companyName = CompanyName;
       this.email = Email;
       this.phone = Phone;
       this.fax = Fax;
       this.webUrl = WebUrl;
       this.details = Details;
       this.serviceHours = ServiceHours;
       this.status = Status;
       this.userId = UserId;
       this.contactId = ContactId;
       this.addressId = AddressId;
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
        
            public Integer getStatus()
            {
                return this.status;
            }
            
            public void setStatus(Integer Status)
            {
                this.status = Status;
            }
        
            public Integer getUserId()
            {
                return this.userId;
            }
            
            public void setUserId(Integer UserId)
            {
                this.userId = UserId;
            }
        
            public Integer getContactId()
            {
                return this.contactId;
            }
            
            public void setContactId(Integer ContactId)
            {
                this.contactId = ContactId;
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

