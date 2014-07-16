


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class Customer implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_CUSTOMER_ID = "CustomerId";
        public static final String PROP_CONTACT_ID = "ContactId";
        public static final String PROP_USER_ID = "UserId";
        public static final String PROP_BILLING_ADDRESS = "BillingAddress";
        public static final String PROP_SHIPPING_ADDRESS = "ShippingAddress";
        public static final String PROP_STATUS = "Status";
        

        private Integer customerId;
        private Integer contactId;
        private Integer userId;
        private Integer billingAddress;
        private Integer shippingAddress;
        private Integer status;
        

        public Customer()
        {
            this.customerId = 0; 
       this.contactId = 0; 
       this.userId = 0; 
       this.billingAddress = 0; 
       this.shippingAddress = 0; 
       this.status = 0; 
        }
        
        public Customer(Integer CustomerId, Integer ContactId, Integer UserId, Integer BillingAddress, Integer ShippingAddress, Integer Status)
        {
            this.customerId = CustomerId;
       this.contactId = ContactId;
       this.userId = UserId;
       this.billingAddress = BillingAddress;
       this.shippingAddress = ShippingAddress;
       this.status = Status;
        } 
        
             
        
            public Integer getCustomerId()
            {
                return this.customerId;
            }
            
            public void setCustomerId(Integer CustomerId)
            {
                this.customerId = CustomerId;
            }
        
            public Integer getContactId()
            {
                return this.contactId;
            }
            
            public void setContactId(Integer ContactId)
            {
                this.contactId = ContactId;
            }
        
            public Integer getUserId()
            {
                return this.userId;
            }
            
            public void setUserId(Integer UserId)
            {
                this.userId = UserId;
            }
        
            public Integer getBillingAddress()
            {
                return this.billingAddress;
            }
            
            public void setBillingAddress(Integer BillingAddress)
            {
                this.billingAddress = BillingAddress;
            }
        
            public Integer getShippingAddress()
            {
                return this.shippingAddress;
            }
            
            public void setShippingAddress(Integer ShippingAddress)
            {
                this.shippingAddress = ShippingAddress;
            }
        
            public Integer getStatus()
            {
                return this.status;
            }
            
            public void setStatus(Integer Status)
            {
                this.status = Status;
            }
           
            
    }

