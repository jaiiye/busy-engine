


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class UserType implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_TYPE_ID = "TypeId";
        public static final String PROP_TYPE_NAME = "TypeName";
        public static final String PROP_DESCRIPTION = "Description";
        public static final String PROP_REDIRECT_U_R_L = "RedirectURL";
        

        private Integer typeId;
        private String typeName;
        private String description;
        private String redirectURL;
        

        public UserType()
        {
            this.typeId = 0; 
       this.typeName = ""; 
       this.description = ""; 
       this.redirectURL = ""; 
        }
        
        public UserType(Integer TypeId, String TypeName, String Description, String RedirectURL)
        {
            this.typeId = TypeId;
       this.typeName = TypeName;
       this.description = Description;
       this.redirectURL = RedirectURL;
        } 
        
             
        
            public Integer getTypeId()
            {
                return this.typeId;
            }
            
            public void setTypeId(Integer TypeId)
            {
                this.typeId = TypeId;
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
           
            
    }

