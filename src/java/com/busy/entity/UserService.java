


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class UserService implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_USER_SERVICE_ID = "UserServiceId";
        public static final String PROP_START_DATE = "StartDate";
        public static final String PROP_END_DATE = "EndDate";
        public static final String PROP_DETAILS = "Details";
        public static final String PROP_CONTRACT_URL = "ContractUrl";
        public static final String PROP_DELIVERABLE_URL = "DeliverableUrl";
        public static final String PROP_DEPOSIT_AMOUNT = "DepositAmount";
        public static final String PROP_BLOG_ID = "BlogId";
        public static final String PROP_USER_ID = "UserId";
        public static final String PROP_SERVICE_ID = "ServiceId";
        

        private Integer userServiceId;
        private Date startDate;
        private Date endDate;
        private String details;
        private String contractUrl;
        private String deliverableUrl;
        private Double depositAmount;
        private Integer blogId;
        private Integer userId;
        private Integer serviceId;
        

        public UserService()
        {
            this.userServiceId = 0; 
       this.startDate = null; 
       this.endDate = null; 
       this.details = ""; 
       this.contractUrl = ""; 
       this.deliverableUrl = ""; 
       this.depositAmount = 0.0; 
       this.blogId = 0; 
       this.userId = 0; 
       this.serviceId = 0; 
        }
        
        public UserService(Integer UserServiceId, Date StartDate, Date EndDate, String Details, String ContractUrl, String DeliverableUrl, Double DepositAmount, Integer BlogId, Integer UserId, Integer ServiceId)
        {
            this.userServiceId = UserServiceId;
       this.startDate = StartDate;
       this.endDate = EndDate;
       this.details = Details;
       this.contractUrl = ContractUrl;
       this.deliverableUrl = DeliverableUrl;
       this.depositAmount = DepositAmount;
       this.blogId = BlogId;
       this.userId = UserId;
       this.serviceId = ServiceId;
        } 
        
             
        
            public Integer getUserServiceId()
            {
                return this.userServiceId;
            }
            
            public void setUserServiceId(Integer UserServiceId)
            {
                this.userServiceId = UserServiceId;
            }
        
            public Date getStartDate()
            {
                return this.startDate;
            }
            
            public void setStartDate(Date StartDate)
            {
                this.startDate = StartDate;
            }
        
            public Date getEndDate()
            {
                return this.endDate;
            }
            
            public void setEndDate(Date EndDate)
            {
                this.endDate = EndDate;
            }
        
            public String getDetails()
            {
                return this.details;
            }
            
            public void setDetails(String Details)
            {
                this.details = Details;
            }
        
            public String getContractUrl()
            {
                return this.contractUrl;
            }
            
            public void setContractUrl(String ContractUrl)
            {
                this.contractUrl = ContractUrl;
            }
        
            public String getDeliverableUrl()
            {
                return this.deliverableUrl;
            }
            
            public void setDeliverableUrl(String DeliverableUrl)
            {
                this.deliverableUrl = DeliverableUrl;
            }
        
            public Double getDepositAmount()
            {
                return this.depositAmount;
            }
            
            public void setDepositAmount(Double DepositAmount)
            {
                this.depositAmount = DepositAmount;
            }
        
            public Integer getBlogId()
            {
                return this.blogId;
            }
            
            public void setBlogId(Integer BlogId)
            {
                this.blogId = BlogId;
            }
        
            public Integer getUserId()
            {
                return this.userId;
            }
            
            public void setUserId(Integer UserId)
            {
                this.userId = UserId;
            }
        
            public Integer getServiceId()
            {
                return this.serviceId;
            }
            
            public void setServiceId(Integer ServiceId)
            {
                this.serviceId = ServiceId;
            }
           
            
    }

