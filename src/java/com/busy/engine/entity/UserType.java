






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class UserType extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_USER_TYPE_ID = "UserTypeId";
        public static final String PROP_TYPE_NAME = "TypeName";
        public static final String PROP_DESCRIPTION = "Description";
        public static final String PROP_REDIRECT_URL = "RedirectUrl";
        

        private Integer userTypeId;
                
        private String typeName;
                
        private String description;
                
        private String redirectUrl;
                
                 
        ArrayList<User> userList; 
        
        

        public UserType()
        {
            this.userTypeId = 0; 
       this.typeName = ""; 
       this.description = ""; 
       this.redirectUrl = ""; 
        
       userList = null; 
        
       }
       
       @Override
       public Integer getId()
       {
            
            return userTypeId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("userTypeId", userTypeId == null ? 0 : userTypeId);
                
            builder.add("typeName", typeName == null ? "" : typeName);
                
            builder.add("description", description == null ? "" : description);
                
            builder.add("redirectUrl", redirectUrl == null ? "" : redirectUrl);
        
        
    
     
     
     
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(UserType.PROP_USER_TYPE_ID) || column.equals(UserType.PROP_TYPE_NAME) || column.equals(UserType.PROP_DESCRIPTION) || column.equals(UserType.PROP_REDIRECT_URL) )
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
            if (column.equals(UserType.PROP_USER_TYPE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static UserType process(ResultSet rs) throws SQLException
        {           
            if(rs.getRow() == 0)
            {
                rs.first();
            }
            return new UserType(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
        }
              
       public UserType(Integer UserTypeId, String TypeName, String Description, String RedirectUrl)
       {
            this.userTypeId = UserTypeId;
       this.typeName = TypeName;
       this.description = Description;
       this.redirectUrl = RedirectUrl;
              
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
            
            
        
            public String getRedirectUrl()
            {
                return this.redirectUrl;
            }
            
            public void setRedirectUrl(String RedirectUrl)
            {
                this.redirectUrl = RedirectUrl;
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

