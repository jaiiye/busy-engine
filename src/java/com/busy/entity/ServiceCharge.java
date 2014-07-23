











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class ServiceCharge implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_SERVICE_CHARGE_ID = "ServiceChargeId";
        public static final String PROP_CHARGE_NAME = "ChargeName";
        public static final String PROP_DESCRIPTION = "Description";
        public static final String PROP_RATE = "Rate";
        public static final String PROP_UNITS = "Units";
        public static final String PROP_DATE = "Date";
        public static final String PROP_USER_SERVICE_ID = "UserServiceId";
        

        private Integer serviceChargeId;
                
        private String chargeName;
                
        private String description;
                
        private String rate;
                
        private String units;
                
        private Date date;
                
        private Integer userServiceId;
        private UserService userService;        
                 
        ArrayList<Service> serviceList; 
        
        

        public ServiceCharge()
        {
            this.serviceChargeId = 0; 
       this.chargeName = ""; 
       this.description = ""; 
       this.rate = ""; 
       this.units = ""; 
       this.date = null; 
       this.userServiceId = 0; 
        
       serviceList = null; 
        
       }
        
        public ServiceCharge(Integer ServiceChargeId, String ChargeName, String Description, String Rate, String Units, Date Date, Integer UserServiceId)
        {
            this.serviceChargeId = ServiceChargeId;
       this.chargeName = ChargeName;
       this.description = Description;
       this.rate = Rate;
       this.units = Units;
       this.date = Date;
       this.userServiceId = UserServiceId;
              
       serviceList = null; 
        
       } 
        
             
        
            public Integer getServiceChargeId()
            {
                return this.serviceChargeId;
            }
            
            public void setServiceChargeId(Integer ServiceChargeId)
            {
                this.serviceChargeId = ServiceChargeId;
            }
            
            
        
            public String getChargeName()
            {
                return this.chargeName;
            }
            
            public void setChargeName(String ChargeName)
            {
                this.chargeName = ChargeName;
            }
            
            
        
            public String getDescription()
            {
                return this.description;
            }
            
            public void setDescription(String Description)
            {
                this.description = Description;
            }
            
            
        
            public String getRate()
            {
                return this.rate;
            }
            
            public void setRate(String Rate)
            {
                this.rate = Rate;
            }
            
            
        
            public String getUnits()
            {
                return this.units;
            }
            
            public void setUnits(String Units)
            {
                this.units = Units;
            }
            
            
        
            public Date getDate()
            {
                return this.date;
            }
            
            public void setDate(Date Date)
            {
                this.date = Date;
            }
            
            
        
            public Integer getUserServiceId()
            {
                return this.userServiceId;
            }
            
            public void setUserServiceId(Integer UserServiceId)
            {
                this.userServiceId = UserServiceId;
            }
            
            
                   
            public UserService getUserService()
                {
                    return this.userService;
                }

                public void setUserService(UserService userService)
                {
                    this.userService = userService;
                }
                   
            
         
        
        
            public ArrayList<Service> getServiceList()
            {
                return this.serviceList;
            }
            
            public void setServiceList(ArrayList<Service> serviceList)
            {
                this.serviceList = serviceList;
            }
        
            
    }

