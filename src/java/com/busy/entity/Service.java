











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class Service implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_SERVICE_ID = "ServiceId";
        public static final String PROP_SERVICE_NAME = "ServiceName";
        public static final String PROP_DESCRIPTION = "Description";
        public static final String PROP_SERVICE_STATUS = "ServiceStatus";
        public static final String PROP_SERVICE_CHARGE_ID = "ServiceChargeId";
        public static final String PROP_SERVICE_TYPE_ID = "ServiceTypeId";
        

        private Integer serviceId;
                
        private String serviceName;
                
        private String description;
                
        private Integer serviceStatus;
                
        private Integer serviceChargeId;
        private ServiceCharge serviceCharge;        
        private Integer serviceTypeId;
        private ServiceType serviceType;        
                 
        ArrayList<UserService> userServiceList; 
        
        

        public Service()
        {
            this.serviceId = 0; 
       this.serviceName = ""; 
       this.description = ""; 
       this.serviceStatus = 0; 
       this.serviceChargeId = 0; 
       this.serviceTypeId = 0; 
        
       userServiceList = null; 
        
       }
        
        public Service(Integer ServiceId, String ServiceName, String Description, Integer ServiceStatus, Integer ServiceChargeId, Integer ServiceTypeId)
        {
            this.serviceId = ServiceId;
       this.serviceName = ServiceName;
       this.description = Description;
       this.serviceStatus = ServiceStatus;
       this.serviceChargeId = ServiceChargeId;
       this.serviceTypeId = ServiceTypeId;
              
       userServiceList = null; 
        
       } 
        
             
        
            public Integer getServiceId()
            {
                return this.serviceId;
            }
            
            public void setServiceId(Integer ServiceId)
            {
                this.serviceId = ServiceId;
            }
            
            
        
            public String getServiceName()
            {
                return this.serviceName;
            }
            
            public void setServiceName(String ServiceName)
            {
                this.serviceName = ServiceName;
            }
            
            
        
            public String getDescription()
            {
                return this.description;
            }
            
            public void setDescription(String Description)
            {
                this.description = Description;
            }
            
            
        
            public Integer getServiceStatus()
            {
                return this.serviceStatus;
            }
            
            public void setServiceStatus(Integer ServiceStatus)
            {
                this.serviceStatus = ServiceStatus;
            }
            
            
        
            public Integer getServiceChargeId()
            {
                return this.serviceChargeId;
            }
            
            public void setServiceChargeId(Integer ServiceChargeId)
            {
                this.serviceChargeId = ServiceChargeId;
            }
            
            
                   
            public ServiceCharge getServiceCharge()
                {
                    return this.serviceCharge;
                }

                public void setServiceCharge(ServiceCharge serviceCharge)
                {
                    this.serviceCharge = serviceCharge;
                }
                   
            
        
            public Integer getServiceTypeId()
            {
                return this.serviceTypeId;
            }
            
            public void setServiceTypeId(Integer ServiceTypeId)
            {
                this.serviceTypeId = ServiceTypeId;
            }
            
            
                   
            public ServiceType getServiceType()
                {
                    return this.serviceType;
                }

                public void setServiceType(ServiceType serviceType)
                {
                    this.serviceType = serviceType;
                }
                   
            
         
        
        
            public ArrayList<UserService> getUserServiceList()
            {
                return this.userServiceList;
            }
            
            public void setUserServiceList(ArrayList<UserService> userServiceList)
            {
                this.userServiceList = userServiceList;
            }
        
            
    }

