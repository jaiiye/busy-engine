











 










    package com.busy.engine.domain;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    
    public class UserService extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_USER_SERVICE_ID = "UserServiceId";
        public static final String PROP_START_DATE = "StartDate";
        public static final String PROP_END_DATE = "EndDate";
        public static final String PROP_DETAILS = "Details";
        public static final String PROP_CONTRACT_URL = "ContractUrl";
        public static final String PROP_DELIVERABLE_URL = "DeliverableUrl";
        public static final String PROP_DEPOSIT_AMOUNT = "DepositAmount";
        public static final String PROP_USER_RANK = "UserRank";
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
                
        private Integer userRank;
                
        private Integer blogId;
        private Blog blog;        
        private Integer userId;
        private User user;        
        private Integer serviceId;
        private Service service;        
                 
        ArrayList<ServiceCharge> serviceChargeList; 
        
        

        public UserService()
        {
            this.userServiceId = 0; 
       this.startDate = null; 
       this.endDate = null; 
       this.details = ""; 
       this.contractUrl = ""; 
       this.deliverableUrl = ""; 
       this.depositAmount = 0.0; 
       this.userRank = 0; 
       this.blogId = 0; 
       this.userId = 0; 
       this.serviceId = 0; 
        
       serviceChargeList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return userServiceId;
       }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(UserService.PROP_USER_SERVICE_ID) || column.equals(UserService.PROP_START_DATE) || column.equals(UserService.PROP_END_DATE) || column.equals(UserService.PROP_DETAILS) || column.equals(UserService.PROP_CONTRACT_URL) || column.equals(UserService.PROP_DELIVERABLE_URL) || column.equals(UserService.PROP_DEPOSIT_AMOUNT) || column.equals(UserService.PROP_USER_RANK) || column.equals(UserService.PROP_BLOG_ID) || column.equals(UserService.PROP_USER_ID) || column.equals(UserService.PROP_SERVICE_ID) )
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
            if (column.equals(UserService.PROP_USER_SERVICE_ID) || column.equals(UserService.PROP_DEPOSIT_AMOUNT) || column.equals(UserService.PROP_USER_RANK) || column.equals(UserService.PROP_BLOG_ID) || column.equals(UserService.PROP_USER_ID) || column.equals(UserService.PROP_SERVICE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static UserService process(ResultSet rs) throws SQLException
        {        
            return new UserService(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11));
        }
              
       public UserService(Integer UserServiceId, Date StartDate, Date EndDate, String Details, String ContractUrl, String DeliverableUrl, Double DepositAmount, Integer UserRank, Integer BlogId, Integer UserId, Integer ServiceId)
       {
            this.userServiceId = UserServiceId;
       this.startDate = StartDate;
       this.endDate = EndDate;
       this.details = Details;
       this.contractUrl = ContractUrl;
       this.deliverableUrl = DeliverableUrl;
       this.depositAmount = DepositAmount;
       this.userRank = UserRank;
       this.blogId = BlogId;
       this.userId = UserId;
       this.serviceId = ServiceId;
              
       serviceChargeList = null; 
        
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
            
            
        
            public Integer getUserRank()
            {
                return this.userRank;
            }
            
            public void setUserRank(Integer UserRank)
            {
                this.userRank = UserRank;
            }
            
            
        
            public Integer getBlogId()
            {
                return this.blogId;
            }
            
            public void setBlogId(Integer BlogId)
            {
                this.blogId = BlogId;
            }
            
            
                   
            public Blog getBlog()
                {
                    return this.blog;
                }

                public void setBlog(Blog blog)
                {
                    this.blog = blog;
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
                   
            
        
            public Integer getServiceId()
            {
                return this.serviceId;
            }
            
            public void setServiceId(Integer ServiceId)
            {
                this.serviceId = ServiceId;
            }
            
            
                   
            public Service getService()
                {
                    return this.service;
                }

                public void setService(Service service)
                {
                    this.service = service;
                }
                   
            
         
        
        
            public ArrayList<ServiceCharge> getServiceChargeList()
            {
                return this.serviceChargeList;
            }
            
            public void setServiceChargeList(ArrayList<ServiceCharge> serviceChargeList)
            {
                this.serviceChargeList = serviceChargeList;
            }
        
            
    }

