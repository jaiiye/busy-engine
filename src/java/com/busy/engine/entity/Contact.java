






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class Contact extends AbstractEntity implements EntityItem<Integer>
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
        public static final String PROP_CONTACT_STATUS = "ContactStatus";
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
                
        private Integer contactStatus;
                
        private String webUrl;
                
        private String info;
                
                 
        ArrayList<Affiliate> affiliateList; 
        ArrayList<Customer> customerList; 
        ArrayList<ItemLocation> itemLocationList; 
        ArrayList<User> userList; 
        
        

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
       this.contactStatus = 0; 
       this.webUrl = ""; 
       this.info = ""; 
        
       affiliateList = null; 
        customerList = null; 
        itemLocationList = null; 
        userList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return contactId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("contactId", contactId == null ? 0 : contactId);
                
            builder.add("title", title == null ? "" : title);
                
            builder.add("firstName", firstName == null ? "" : firstName);
                
            builder.add("lastName", lastName == null ? "" : lastName);
                
            builder.add("position", position == null ? "" : position);
                
            builder.add("phone", phone == null ? "" : phone);
                
            builder.add("fax", fax == null ? "" : fax);
                
            builder.add("email", email == null ? "" : email);
                
            builder.add("contactStatus", contactStatus == null ? 0 : contactStatus);
                
            builder.add("webUrl", webUrl == null ? "" : webUrl);
                
            builder.add("info", info == null ? "" : info);
        
        
    
     
     
     
     
     
     
     
     
     
     
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Contact.PROP_CONTACT_ID) || column.equals(Contact.PROP_TITLE) || column.equals(Contact.PROP_FIRST_NAME) || column.equals(Contact.PROP_LAST_NAME) || column.equals(Contact.PROP_POSITION) || column.equals(Contact.PROP_PHONE) || column.equals(Contact.PROP_FAX) || column.equals(Contact.PROP_EMAIL) || column.equals(Contact.PROP_CONTACT_STATUS) || column.equals(Contact.PROP_WEB_URL) || column.equals(Contact.PROP_INFO) )
            {
                return column;
            }
            else
            {
                throw new SQLException("Invalid column name: " + column);
            }
        }
                
        public static void checkColumnSize(String column, int size) throws Exception
        {
            if (column.length() > size)
            {            
                throw new Exception("Invalid column length: " + size + "instead of " + column.length() + " for column: " + column);
            }
        }
                
        public static boolean isColumnNumeric(String column)
        {
            if (column.equals(Contact.PROP_CONTACT_ID) || column.equals(Contact.PROP_TITLE) || column.equals(Contact.PROP_FIRST_NAME) || column.equals(Contact.PROP_LAST_NAME) || column.equals(Contact.PROP_POSITION) || column.equals(Contact.PROP_PHONE) || column.equals(Contact.PROP_FAX) || column.equals(Contact.PROP_EMAIL) || column.equals(Contact.PROP_CONTACT_STATUS) || column.equals(Contact.PROP_WEB_URL) || column.equals(Contact.PROP_INFO) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Contact process(ResultSet rs) throws SQLException
        {        
            return new Contact(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10), rs.getString(11));
        }
              
       public Contact(Integer ContactId, String Title, String FirstName, String LastName, String Position, String Phone, String Fax, String Email, Integer ContactStatus, String WebUrl, String Info)
       {
            this.contactId = ContactId;
       this.title = Title;
       this.firstName = FirstName;
       this.lastName = LastName;
       this.position = Position;
       this.phone = Phone;
       this.fax = Fax;
       this.email = Email;
       this.contactStatus = ContactStatus;
       this.webUrl = WebUrl;
       this.info = Info;
              
       affiliateList = null; 
        customerList = null; 
        itemLocationList = null; 
        userList = null; 
        
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
            
            
        
            public Integer getContactStatus()
            {
                return this.contactStatus;
            }
            
            public void setContactStatus(Integer ContactStatus)
            {
                this.contactStatus = ContactStatus;
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
            
            
         
        
        
        public ArrayList<Affiliate> getAffiliateList()
            {
                return this.affiliateList;
            }
            
            public void setAffiliateList(ArrayList<Affiliate> affiliateList)
            {
                this.affiliateList = affiliateList;
            }
        
        public ArrayList<Customer> getCustomerList()
            {
                return this.customerList;
            }
            
            public void setCustomerList(ArrayList<Customer> customerList)
            {
                this.customerList = customerList;
            }
        
        public ArrayList<ItemLocation> getItemLocationList()
            {
                return this.itemLocationList;
            }
            
            public void setItemLocationList(ArrayList<ItemLocation> itemLocationList)
            {
                this.itemLocationList = itemLocationList;
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

