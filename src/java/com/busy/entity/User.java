











 








    package com.busy.entity;

    import java.io.Serializable;
    import java.util.Date;
    
    public class User implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_USER_ID = "UserId";
        public static final String PROP_USERNAME = "Username";
        public static final String PROP_PASSWORD = "Password";
        public static final String PROP_EMAIL = "Email";
        public static final String PROP_SECURITY_QUESTION = "SecurityQuestion";
        public static final String PROP_SECURITY_ANSWER = "SecurityAnswer";
        public static final String PROP_REGISTER_DATE = "RegisterDate";
        public static final String PROP_IMAGE_U_R_L = "ImageURL";
        public static final String PROP_STATUS = "Status";
        public static final String PROP_RANK = "Rank";
        public static final String PROP_WEB_URL = "WebUrl";
        public static final String PROP_BRAND_ID = "BrandId";
        public static final String PROP_USER_TYPE_ID = "UserTypeId";
        public static final String PROP_ADDRESS_ID = "AddressId";
        public static final String PROP_CONTACT_ID = "ContactId";
        public static final String PROP_USER_GROUP_ID = "UserGroupId";
        

        private Integer userId;
        private String username;
        private String password;
        private String email;
        private String securityQuestion;
        private String securityAnswer;
        private Date registerDate;
        private String imageURL;
        private Integer status;
        private Integer rank;
        private String webUrl;
        private Integer brandId;
        private Integer userTypeId;
        private Integer addressId;
        private Integer contactId;
        private Integer userGroupId;
        

        public User()
        {
            this.userId = 0; 
       this.username = ""; 
       this.password = ""; 
       this.email = ""; 
       this.securityQuestion = ""; 
       this.securityAnswer = ""; 
       this.registerDate = null; 
       this.imageURL = ""; 
       this.status = 0; 
       this.rank = 0; 
       this.webUrl = ""; 
       this.brandId = 0; 
       this.userTypeId = 0; 
       this.addressId = 0; 
       this.contactId = 0; 
       this.userGroupId = 0; 
        }
        
        public User(Integer UserId, String Username, String Password, String Email, String SecurityQuestion, String SecurityAnswer, Date RegisterDate, String ImageURL, Integer Status, Integer Rank, String WebUrl, Integer BrandId, Integer UserTypeId, Integer AddressId, Integer ContactId, Integer UserGroupId)
        {
            this.userId = UserId;
       this.username = Username;
       this.password = Password;
       this.email = Email;
       this.securityQuestion = SecurityQuestion;
       this.securityAnswer = SecurityAnswer;
       this.registerDate = RegisterDate;
       this.imageURL = ImageURL;
       this.status = Status;
       this.rank = Rank;
       this.webUrl = WebUrl;
       this.brandId = BrandId;
       this.userTypeId = UserTypeId;
       this.addressId = AddressId;
       this.contactId = ContactId;
       this.userGroupId = UserGroupId;
        } 
        
             
        
            public Integer getUserId()
            {
                return this.userId;
            }
            
            public void setUserId(Integer UserId)
            {
                this.userId = UserId;
            }
        
            public String getUsername()
            {
                return this.username;
            }
            
            public void setUsername(String Username)
            {
                this.username = Username;
            }
        
            public String getPassword()
            {
                return this.password;
            }
            
            public void setPassword(String Password)
            {
                this.password = Password;
            }
        
            public String getEmail()
            {
                return this.email;
            }
            
            public void setEmail(String Email)
            {
                this.email = Email;
            }
        
            public String getSecurityQuestion()
            {
                return this.securityQuestion;
            }
            
            public void setSecurityQuestion(String SecurityQuestion)
            {
                this.securityQuestion = SecurityQuestion;
            }
        
            public String getSecurityAnswer()
            {
                return this.securityAnswer;
            }
            
            public void setSecurityAnswer(String SecurityAnswer)
            {
                this.securityAnswer = SecurityAnswer;
            }
        
            public Date getRegisterDate()
            {
                return this.registerDate;
            }
            
            public void setRegisterDate(Date RegisterDate)
            {
                this.registerDate = RegisterDate;
            }
        
            public String getImageURL()
            {
                return this.imageURL;
            }
            
            public void setImageURL(String ImageURL)
            {
                this.imageURL = ImageURL;
            }
        
            public Integer getStatus()
            {
                return this.status;
            }
            
            public void setStatus(Integer Status)
            {
                this.status = Status;
            }
        
            public Integer getRank()
            {
                return this.rank;
            }
            
            public void setRank(Integer Rank)
            {
                this.rank = Rank;
            }
        
            public String getWebUrl()
            {
                return this.webUrl;
            }
            
            public void setWebUrl(String WebUrl)
            {
                this.webUrl = WebUrl;
            }
        
            public Integer getBrandId()
            {
                return this.brandId;
            }
            
            public void setBrandId(Integer BrandId)
            {
                this.brandId = BrandId;
            }
        
            public Integer getUserTypeId()
            {
                return this.userTypeId;
            }
            
            public void setUserTypeId(Integer UserTypeId)
            {
                this.userTypeId = UserTypeId;
            }
        
            public Integer getAddressId()
            {
                return this.addressId;
            }
            
            public void setAddressId(Integer AddressId)
            {
                this.addressId = AddressId;
            }
        
            public Integer getContactId()
            {
                return this.contactId;
            }
            
            public void setContactId(Integer ContactId)
            {
                this.contactId = ContactId;
            }
        
            public Integer getUserGroupId()
            {
                return this.userGroupId;
            }
            
            public void setUserGroupId(Integer UserGroupId)
            {
                this.userGroupId = UserGroupId;
            }
           
            
    }

