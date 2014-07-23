











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class UserAction implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_USER_ACTION_ID = "UserActionId";
        public static final String PROP_DATE = "Date";
        public static final String PROP_DETAIL = "Detail";
        public static final String PROP_USER_ACTION_TYPE_ID = "UserActionTypeId";
        public static final String PROP_USER_ID = "UserId";
        

        private Integer userActionId;
                
        private Date date;
                
        private String detail;
                
        private Integer userActionTypeId;
        private UserActionType userActionType;        
        private Integer userId;
        private User user;        
                 
        
        

        public UserAction()
        {
            this.userActionId = 0; 
       this.date = null; 
       this.detail = ""; 
       this.userActionTypeId = 0; 
       this.userId = 0; 
        
       
       }
        
        public UserAction(Integer UserActionId, Date Date, String Detail, Integer UserActionTypeId, Integer UserId)
        {
            this.userActionId = UserActionId;
       this.date = Date;
       this.detail = Detail;
       this.userActionTypeId = UserActionTypeId;
       this.userId = UserId;
              
       
       } 
        
             
        
            public Integer getUserActionId()
            {
                return this.userActionId;
            }
            
            public void setUserActionId(Integer UserActionId)
            {
                this.userActionId = UserActionId;
            }
            
            
        
            public Date getDate()
            {
                return this.date;
            }
            
            public void setDate(Date Date)
            {
                this.date = Date;
            }
            
            
        
            public String getDetail()
            {
                return this.detail;
            }
            
            public void setDetail(String Detail)
            {
                this.detail = Detail;
            }
            
            
        
            public Integer getUserActionTypeId()
            {
                return this.userActionTypeId;
            }
            
            public void setUserActionTypeId(Integer UserActionTypeId)
            {
                this.userActionTypeId = UserActionTypeId;
            }
            
            
                   
            public UserActionType getUserActionType()
                {
                    return this.userActionType;
                }

                public void setUserActionType(UserActionType userActionType)
                {
                    this.userActionType = userActionType;
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

