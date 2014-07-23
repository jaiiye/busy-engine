











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class UserActionType implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_USER_ACTION_TYPE_ID = "UserActionTypeId";
        public static final String PROP_TYPE_NAME = "TypeName";
        

        private Integer userActionTypeId;
                
        private String typeName;
                
                 
        ArrayList<UserAction> userActionList; 
        
        

        public UserActionType()
        {
            this.userActionTypeId = 0; 
       this.typeName = ""; 
        
       userActionList = null; 
        
       }
        
        public UserActionType(Integer UserActionTypeId, String TypeName)
        {
            this.userActionTypeId = UserActionTypeId;
       this.typeName = TypeName;
              
       userActionList = null; 
        
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
            
            
         
        
        
            public ArrayList<UserAction> getUserActionList()
            {
                return this.userActionList;
            }
            
            public void setUserActionList(ArrayList<UserAction> userActionList)
            {
                this.userActionList = userActionList;
            }
        
            
    }

