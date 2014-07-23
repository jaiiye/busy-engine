











 










    package com.busy.entity;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Date;
    
    public class UserType implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_USER_TYPE_ID = "UserTypeId";
        public static final String PROP_TYPE_NAME = "TypeName";
        public static final String PROP_DESCRIPTION = "Description";
        public static final String PROP_REDIRECT_U_R_L = "RedirectURL";
        

        private Integer userTypeId;
                
        private String typeName;
                
        private String description;
                
        private String redirectURL;
                
                 
        ArrayList<User> userList; 
        
        

        public UserType()
        {
            this.userTypeId = 0; 
       this.typeName = ""; 
       this.description = ""; 
       this.redirectURL = ""; 
        
       userList = null; 
        
       }
        
        public UserType(Integer UserTypeId, String TypeName, String Description, String RedirectURL)
        {
            this.userTypeId = UserTypeId;
       this.typeName = TypeName;
       this.description = Description;
       this.redirectURL = RedirectURL;
              
       userList = null; 
        
       } 
        
             
        
            public Integer getUserTypeId()
            {
                return this.userTypeId;
            }
            
            public void setUserTypeId(Integer UserTypeId)
            {
                this.userTypeId = UserTypeId;
            }
            
            
        
            public String getTypeName()
            {
                return this.typeName;
            }
            
            public void setTypeName(String TypeName)
            {
                this.typeName = TypeName;
            }
            
            
        
            public String getDescription()
            {
                return this.description;
            }
            
            public void setDescription(String Description)
            {
                this.description = Description;
            }
            
            
        
            public String getRedirectURL()
            {
                return this.redirectURL;
            }
            
            public void setRedirectURL(String RedirectURL)
            {
                this.redirectURL = RedirectURL;
            }
            
            
         
        
        
            public ArrayList<User> getUserList()
            {
                return this.userList;
            }
            
            public void setUserList(ArrayList<User> userList)
            {
                this.userList = userList;
            }
        
            
    }

