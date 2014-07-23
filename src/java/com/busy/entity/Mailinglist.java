











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class Mailinglist implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_MAILINGLIST_ID = "MailinglistId";
        public static final String PROP_FULL_NAME = "FullName";
        public static final String PROP_EMAIL = "Email";
        public static final String PROP_LIST_STATUS = "ListStatus";
        public static final String PROP_USER_ID = "UserId";
        

        private Integer mailinglistId;
                
        private String fullName;
                
        private String email;
                
        private Integer listStatus;
                
        private Integer userId;
        private User user;        
                 
        
        

        public Mailinglist()
        {
            this.mailinglistId = 0; 
       this.fullName = ""; 
       this.email = ""; 
       this.listStatus = 0; 
       this.userId = 0; 
        
       
       }
        
        public Mailinglist(Integer MailinglistId, String FullName, String Email, Integer ListStatus, Integer UserId)
        {
            this.mailinglistId = MailinglistId;
       this.fullName = FullName;
       this.email = Email;
       this.listStatus = ListStatus;
       this.userId = UserId;
              
       
       } 
        
             
        
            public Integer getMailinglistId()
            {
                return this.mailinglistId;
            }
            
            public void setMailinglistId(Integer MailinglistId)
            {
                this.mailinglistId = MailinglistId;
            }
            
            
        
            public String getFullName()
            {
                return this.fullName;
            }
            
            public void setFullName(String FullName)
            {
                this.fullName = FullName;
            }
            
            
        
            public String getEmail()
            {
                return this.email;
            }
            
            public void setEmail(String Email)
            {
                this.email = Email;
            }
            
            
        
            public Integer getListStatus()
            {
                return this.listStatus;
            }
            
            public void setListStatus(Integer ListStatus)
            {
                this.listStatus = ListStatus;
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
                   
            
         
        
        
            
    }

