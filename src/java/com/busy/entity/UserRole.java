











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class UserRole implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_USER_NAME = "UserName";
        public static final String PROP_ROLE_NAME = "RoleName";
        

        private String userName;
                
        private String roleName;
                
                 
        
        

        public UserRole()
        {
            this.userName = ""; 
       this.roleName = ""; 
        
       
       }
        
        public UserRole(String UserName, String RoleName)
        {
            this.userName = UserName;
       this.roleName = RoleName;
              
       
       } 
        
             
        
            public String getUserName()
            {
                return this.userName;
            }
            
            public void setUserName(String UserName)
            {
                this.userName = UserName;
            }
            
            
        
            public String getRoleName()
            {
                return this.roleName;
            }
            
            public void setRoleName(String RoleName)
            {
                this.roleName = RoleName;
            }
            
            
         
        
        
            
    }

