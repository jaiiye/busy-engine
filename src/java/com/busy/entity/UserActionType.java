


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class UserActionType implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_USER_ACTION_TYPE_ID = "UserActionTypeId";
        public static final String PROP_TYPE_NAME = "TypeName";
        

        private Integer userActionTypeId;
        private String typeName;
        

        public UserActionType()
        {
            this.userActionTypeId = 0; 
       this.typeName = ""; 
        }
        
        public UserActionType(Integer UserActionTypeId, String TypeName)
        {
            this.userActionTypeId = UserActionTypeId;
       this.typeName = TypeName;
        } 
        
             
        
            public Integer getUserActionTypeId()
            {
                return this.userActionTypeId;
            }
            
            public void setUserActionTypeId(Integer UserActionTypeId)
            {
                this.userActionTypeId = UserActionTypeId;
            }
        
            public String getTypeName()
            {
                return this.typeName;
            }
            
            public void setTypeName(String TypeName)
            {
                this.typeName = TypeName;
            }
           
            
    }

