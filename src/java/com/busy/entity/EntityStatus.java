











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class EntityStatus implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_ENTITY_STATUS_ID = "EntityStatusId";
        public static final String PROP_STATUS_CODE = "StatusCode";
        public static final String PROP_STATUS_NAME = "StatusName";
        public static final String PROP_APPLIES_TO = "AppliesTo";
        

        private Integer entityStatusId;
                
        private Integer statusCode;
                
        private String statusName;
                
        private String appliesTo;
                
                 
        
        

        public EntityStatus()
        {
            this.entityStatusId = 0; 
       this.statusCode = 0; 
       this.statusName = ""; 
       this.appliesTo = ""; 
        
       
       }
        
        public EntityStatus(Integer EntityStatusId, Integer StatusCode, String StatusName, String AppliesTo)
        {
            this.entityStatusId = EntityStatusId;
       this.statusCode = StatusCode;
       this.statusName = StatusName;
       this.appliesTo = AppliesTo;
              
       
       } 
        
             
        
            public Integer getEntityStatusId()
            {
                return this.entityStatusId;
            }
            
            public void setEntityStatusId(Integer EntityStatusId)
            {
                this.entityStatusId = EntityStatusId;
            }
            
            
        
            public Integer getStatusCode()
            {
                return this.statusCode;
            }
            
            public void setStatusCode(Integer StatusCode)
            {
                this.statusCode = StatusCode;
            }
            
            
        
            public String getStatusName()
            {
                return this.statusName;
            }
            
            public void setStatusName(String StatusName)
            {
                this.statusName = StatusName;
            }
            
            
        
            public String getAppliesTo()
            {
                return this.appliesTo;
            }
            
            public void setAppliesTo(String AppliesTo)
            {
                this.appliesTo = AppliesTo;
            }
            
            
         
        
        
            
    }

