


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class Mailinglist implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_MAILINGLIST_ID = "MailinglistId";
        public static final String PROP_LIST_NAME = "ListName";
        public static final String PROP_EMAIL = "Email";
        public static final String PROP_STATUS = "Status";
        public static final String PROP_USER_ID = "UserId";
        

        private Integer mailinglistId;
        private String listName;
        private String email;
        private Integer status;
        private Integer userId;
        

        public Mailinglist()
        {
            this.mailinglistId = 0; 
       this.listName = ""; 
       this.email = ""; 
       this.status = 0; 
       this.userId = 0; 
        }
        
        public Mailinglist(Integer MailinglistId, String ListName, String Email, Integer Status, Integer UserId)
        {
            this.mailinglistId = MailinglistId;
       this.listName = ListName;
       this.email = Email;
       this.status = Status;
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
        
            public String getListName()
            {
                return this.listName;
            }
            
            public void setListName(String ListName)
            {
                this.listName = ListName;
            }
        
            public String getEmail()
            {
                return this.email;
            }
            
            public void setEmail(String Email)
            {
                this.email = Email;
            }
        
            public Integer getStatus()
            {
                return this.status;
            }
            
            public void setStatus(Integer Status)
            {
                this.status = Status;
            }
        
            public Integer getUserId()
            {
                return this.userId;
            }
            
            public void setUserId(Integer UserId)
            {
                this.userId = UserId;
            }
           
            
    }

