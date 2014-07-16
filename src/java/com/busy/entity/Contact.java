


 









 








    package com.busy.entity;

    import com.transitionsoft.*;
    import java.io.Serializable;
    import java.util.Date;
    
    public class Contact implements Serializable
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_CONTACT_ID = "ContactId";
        public static final String PROP_TITLE = "Title";
        public static final String PROP_FIRST_NAME = "FirstName";
        public static final String PROP_LAST_NAME = "LastName";
        public static final String PROP_POSITION = "Position";
        public static final String PROP_PHONE = "Phone";
        public static final String PROP_FAX = "Fax";
        public static final String PROP_EMAIL = "Email";
        public static final String PROP_STATUS = "Status";
        public static final String PROP_WEB_URL = "WebUrl";
        public static final String PROP_INFO = "Info";
        

        private Integer contactId;
        private String title;
        private String firstName;
        private String lastName;
        private String position;
        private String phone;
        private String fax;
        private String email;
        private Integer status;
        private String webUrl;
        private String info;
        

        public Contact()
        {
            this.contactId = 0; 
       this.title = ""; 
       this.firstName = ""; 
       this.lastName = ""; 
       this.position = ""; 
       this.phone = ""; 
       this.fax = ""; 
       this.email = ""; 
       this.status = 0; 
       this.webUrl = ""; 
       this.info = ""; 
        }
        
        public Contact(Integer ContactId, String Title, String FirstName, String LastName, String Position, String Phone, String Fax, String Email, Integer Status, String WebUrl, String Info)
        {
            this.contactId = ContactId;
       this.title = Title;
       this.firstName = FirstName;
       this.lastName = LastName;
       this.position = Position;
       this.phone = Phone;
       this.fax = Fax;
       this.email = Email;
       this.status = Status;
       this.webUrl = WebUrl;
       this.info = Info;
        } 
        
             
        
            public Integer getContactId()
            {
                return this.contactId;
            }
            
            public void setContactId(Integer ContactId)
            {
                this.contactId = ContactId;
            }
        
            public String getTitle()
            {
                return this.title;
            }
            
            public void setTitle(String Title)
            {
                this.title = Title;
            }
        
            public String getFirstName()
            {
                return this.firstName;
            }
            
            public void setFirstName(String FirstName)
            {
                this.firstName = FirstName;
            }
        
            public String getLastName()
            {
                return this.lastName;
            }
            
            public void setLastName(String LastName)
            {
                this.lastName = LastName;
            }
        
            public String getPosition()
            {
                return this.position;
            }
            
            public void setPosition(String Position)
            {
                this.position = Position;
            }
        
            public String getPhone()
            {
                return this.phone;
            }
            
            public void setPhone(String Phone)
            {
                this.phone = Phone;
            }
        
            public String getFax()
            {
                return this.fax;
            }
            
            public void setFax(String Fax)
            {
                this.fax = Fax;
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
        
            public String getWebUrl()
            {
                return this.webUrl;
            }
            
            public void setWebUrl(String WebUrl)
            {
                this.webUrl = WebUrl;
            }
        
            public String getInfo()
            {
                return this.info;
            }
            
            public void setInfo(String Info)
            {
                this.info = Info;
            }
           
            
    }

