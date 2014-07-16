











 








    package com.busy.entity;

    import java.io.Serializable;
    import java.util.Date;
    
    public class Service implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_SERVICE_ID = "ServiceId";
        public static final String PROP_SERVICE_NAME = "ServiceName";
        public static final String PROP_DESCRIPTION = "Description";
        public static final String PROP_STATUS = "Status";
        public static final String PROP_SERVICE_CHARGE_ID = "ServiceChargeId";
        public static final String PROP_SERVICE_TYPE_ID = "ServiceTypeId";
        

        private Integer serviceId;
        private String serviceName;
        private String description;
        private Integer status;
        private Integer serviceChargeId;
        private Integer serviceTypeId;
        

        public Service()
        {
            this.serviceId = 0; 
       this.serviceName = ""; 
       this.description = ""; 
       this.status = 0; 
       this.serviceChargeId = 0; 
       this.serviceTypeId = 0; 
        }
        
        public Service(Integer ServiceId, String ServiceName, String Description, Integer Status, Integer ServiceChargeId, Integer ServiceTypeId)
        {
            this.serviceId = ServiceId;
       this.serviceName = ServiceName;
       this.description = Description;
       this.status = Status;
       this.serviceChargeId = ServiceChargeId;
       this.serviceTypeId = ServiceTypeId;
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
        
            public Integer getStatus()
            {
                return this.status;
            }
            
            public void setStatus(Integer Status)
            {
                this.status = Status;
            }
        
            public Integer getServiceChargeId()
            {
                return this.serviceChargeId;
            }
            
            public void setServiceChargeId(Integer ServiceChargeId)
            {
                this.serviceChargeId = ServiceChargeId;
            }
        
            public Integer getServiceTypeId()
            {
                return this.serviceTypeId;
            }
            
            public void setServiceTypeId(Integer ServiceTypeId)
            {
                this.serviceTypeId = ServiceTypeId;
            }
           
            
    }

