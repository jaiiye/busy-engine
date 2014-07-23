











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class ServiceType implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_SERVICE_TYPE_ID = "ServiceTypeId";
        public static final String PROP_TYPE_NAME = "TypeName";
        public static final String PROP_DESCIPTION = "Desciption";
        

        private Integer serviceTypeId;
                
        private String typeName;
                
        private String desciption;
                
                 
        ArrayList<Service> serviceList; 
        
        

        public ServiceType()
        {
            this.serviceTypeId = 0; 
       this.typeName = ""; 
       this.desciption = ""; 
        
       serviceList = null; 
        
       }
        
        public ServiceType(Integer ServiceTypeId, String TypeName, String Desciption)
        {
            this.serviceTypeId = ServiceTypeId;
       this.typeName = TypeName;
       this.desciption = Desciption;
              
       serviceList = null; 
        
       } 
        
             
        
            public Integer getServiceTypeId()
            {
                return this.serviceTypeId;
            }
            
            public void setServiceTypeId(Integer ServiceTypeId)
            {
                this.serviceTypeId = ServiceTypeId;
            }
            
            
        
            public String getTypeName()
            {
                return this.typeName;
            }
            
            public void setTypeName(String TypeName)
            {
                this.typeName = TypeName;
            }
            
            
        
            public String getDesciption()
            {
                return this.desciption;
            }
            
            public void setDesciption(String Desciption)
            {
                this.desciption = Desciption;
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

