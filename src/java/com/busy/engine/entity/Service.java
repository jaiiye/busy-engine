






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class Service extends AbstractEntity implements EntityItem<Integer>
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
       
       @Override
       public Integer getId()
       {
            
            return serviceId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("serviceId", serviceId == null ? 0 : serviceId);
                
            builder.add("serviceName", serviceName == null ? "" : serviceName);
                
            builder.add("description", description == null ? "" : description);
                
            builder.add("serviceStatus", serviceStatus == null ? 0 : serviceStatus);
                
            builder.add("serviceChargeId", serviceChargeId == null ? 0 : serviceChargeId);
                
            builder.add("serviceTypeId", serviceTypeId == null ? 0 : serviceTypeId);
        
        
    
     
     
     
     if(serviceCharge != null) serviceCharge.addJson(builder);
        
     if(serviceType != null) serviceType.addJson(builder);
        
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Service.PROP_SERVICE_ID) || column.equals(Service.PROP_SERVICE_NAME) || column.equals(Service.PROP_DESCRIPTION) || column.equals(Service.PROP_SERVICE_STATUS) || column.equals(Service.PROP_SERVICE_CHARGE_ID) || column.equals(Service.PROP_SERVICE_TYPE_ID) )
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
            if (column.equals(Service.PROP_SERVICE_ID) || column.equals(Service.PROP_SERVICE_NAME) || column.equals(Service.PROP_DESCRIPTION) || column.equals(Service.PROP_SERVICE_STATUS) || column.equals(Service.PROP_SERVICE_CHARGE_ID) || column.equals(Service.PROP_SERVICE_TYPE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Service process(ResultSet rs) throws SQLException
        {        
            return new Service(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
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

