






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class Mailinglist extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_MAILINGLIST_ID = "MailinglistId";
        public static final String PROP_FULL_NAME = "FullName";
        public static final String PROP_EMAIL = "Email";
        public static final String PROP_LIST_STATUS = "ListStatus";
        public static final String PROP_USER_ID = "UserId";
        

        private Integer mailinglistId;
                
        private String fullName;
                
        private String email;
                
        private Integer listStatus;
                
        private Integer userId;
        private User user;        
                 
        
        

        public Mailinglist()
        {
            this.mailinglistId = 0; 
       this.fullName = ""; 
       this.email = ""; 
       this.listStatus = 0; 
       this.userId = 0; 
        
       
       }
       
       @Override
       public Integer getId()
       {
            
            return mailinglistId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("mailinglistId", mailinglistId == null ? 0 : mailinglistId);
                
            builder.add("fullName", fullName == null ? "" : fullName);
                
            builder.add("email", email == null ? "" : email);
                
            builder.add("listStatus", listStatus == null ? 0 : listStatus);
                
            builder.add("userId", userId == null ? 0 : userId);
        
        
    
     
     
     
     if(user != null) user.addJson(builder);
        
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(Mailinglist.PROP_MAILINGLIST_ID) || column.equals(Mailinglist.PROP_FULL_NAME) || column.equals(Mailinglist.PROP_EMAIL) || column.equals(Mailinglist.PROP_LIST_STATUS) || column.equals(Mailinglist.PROP_USER_ID) )
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
            if (column.equals(Mailinglist.PROP_MAILINGLIST_ID) || column.equals(Mailinglist.PROP_LIST_STATUS) || column.equals(Mailinglist.PROP_USER_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static Mailinglist process(ResultSet rs) throws SQLException
        {           
            rs.first();
            return new Mailinglist(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
        }
              
       public Mailinglist(Integer MailinglistId, String FullName, String Email, Integer ListStatus, Integer UserId)
       {
            this.mailinglistId = MailinglistId;
       this.fullName = FullName;
       this.email = Email;
       this.listStatus = ListStatus;
       this.userId = UserId;
              
       
       } 
        
             
        
            public Integer getMailinglistId()
            {
                return this.mailinglistId;
            }
            
            public void setMailinglistId(Integer MailinglistId)
            {
                this.mailinglistId = MailinglistId;
            }
            
            
        
            public String getFullName()
            {
                return this.fullName;
            }
            
            public void setFullName(String FullName)
            {
                this.fullName = FullName;
            }
            
            
        
            public String getEmail()
            {
                return this.email;
            }
            
            public void setEmail(String Email)
            {
                this.email = Email;
            }
            
            
        
            public Integer getListStatus()
            {
                return this.listStatus;
            }
            
            public void setListStatus(Integer ListStatus)
            {
                this.listStatus = ListStatus;
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

