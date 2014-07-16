


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class Status implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_STATUS_ID = "StatusId";
        public static final String PROP_CODE = "Code";
        public static final String PROP_STATUS_NAME = "StatusName";
        public static final String PROP_APPLIES_TO = "AppliesTo";
        

        private Integer statusId;
        private Integer code;
        private String statusName;
        private String appliesTo;
        

        public Status()
        {
            this.statusId = 0; 
       this.code = 0; 
       this.statusName = ""; 
       this.appliesTo = ""; 
        }
        
        public Status(Integer StatusId, Integer Code, String StatusName, String AppliesTo)
        {
            this.statusId = StatusId;
       this.code = Code;
       this.statusName = StatusName;
       this.appliesTo = AppliesTo;
        } 
        
             
        
            public Integer getStatusId()
            {
                return this.statusId;
            }
            
            public void setStatusId(Integer StatusId)
            {
                this.statusId = StatusId;
            }
        
            public Integer getCode()
            {
                return this.code;
            }
            
            public void setCode(Integer Code)
            {
                this.code = Code;
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

