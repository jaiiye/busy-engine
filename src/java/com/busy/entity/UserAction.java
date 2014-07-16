


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class UserAction implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_USER_ACTION_ID = "UserActionId";
        public static final String PROP_DATE = "Date";
        public static final String PROP_DETAIL = "Detail";
        public static final String PROP_ACTION_TYPE_ID = "ActionTypeId";
        public static final String PROP_USER_ID = "UserId";
        

        private Integer userActionId;
        private Date date;
        private String detail;
        private Integer actionTypeId;
        private Integer userId;
        

        public UserAction()
        {
            this.userActionId = 0; 
       this.date = null; 
       this.detail = ""; 
       this.actionTypeId = 0; 
       this.userId = 0; 
        }
        
        public UserAction(Integer UserActionId, Date Date, String Detail, Integer ActionTypeId, Integer UserId)
        {
            this.userActionId = UserActionId;
       this.date = Date;
       this.detail = Detail;
       this.actionTypeId = ActionTypeId;
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
        
            public Integer getActionTypeId()
            {
                return this.actionTypeId;
            }
            
            public void setActionTypeId(Integer ActionTypeId)
            {
                this.actionTypeId = ActionTypeId;
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

