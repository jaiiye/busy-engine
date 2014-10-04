






























 






    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class UserAction extends AbstractEntity implements EntityItem<Integer>
    {    
        private static final long serialVersionUID = 1L;       
        public static final String PROP_USER_ACTION_ID = "UserActionId";
        public static final String PROP_DATE = "Date";
        public static final String PROP_DETAIL = "Detail";
        public static final String PROP_USER_ACTION_TYPE_ID = "UserActionTypeId";
        public static final String PROP_USER_ID = "UserId";
        

        private Integer userActionId;
                
        private Date date;
                
        private String detail;
                
        private Integer userActionTypeId;
        private UserActionType userActionType;        
        private Integer userId;
        private User user;        
                 
        
        

        public UserAction()
        {
            this.userActionId = 0; 
       this.date = null; 
       this.detail = ""; 
       this.userActionTypeId = 0; 
       this.userId = 0; 
        
       
       }
       
       @Override
       public Integer getId()
       {
            
            return userActionId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
                
            builder.add("userActionId", userActionId == null ? 0 : userActionId);
                
            builder.add("date", date == null ? "" : new SimpleDateFormat("yyyyMMdd").format(date));
                
            builder.add("detail", detail == null ? "" : detail);
                
            builder.add("userActionTypeId", userActionTypeId == null ? 0 : userActionTypeId);
                
            builder.add("userId", userId == null ? 0 : userId);
        
        
    
     
     
     if(userActionType != null) userActionType.addJson(builder);
        
     if(user != null) user.addJson(builder);
        
              
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(UserAction.PROP_USER_ACTION_ID) || column.equals(UserAction.PROP_DATE) || column.equals(UserAction.PROP_DETAIL) || column.equals(UserAction.PROP_USER_ACTION_TYPE_ID) || column.equals(UserAction.PROP_USER_ID) )
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
            if (column.equals(UserAction.PROP_USER_ACTION_ID) || column.equals(UserAction.PROP_DATE) || column.equals(UserAction.PROP_DETAIL) || column.equals(UserAction.PROP_USER_ACTION_TYPE_ID) || column.equals(UserAction.PROP_USER_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static UserAction process(ResultSet rs) throws SQLException
        {        
            return new UserAction(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
        }
              
       public UserAction(Integer UserActionId, Date Date, String Detail, Integer UserActionTypeId, Integer UserId)
       {
            this.userActionId = UserActionId;
       this.date = Date;
       this.detail = Detail;
       this.userActionTypeId = UserActionTypeId;
       this.userId = UserId;
              
       
       } 
        
             
        
            public Integer getUserActionId()
            {
                return this.userActionId;
            }
            
            public void setUserActionId(Integer UserActionId)
            {
                this.userActionId = UserActionId;
            }
            
            
        
            public Date getDate()
            {
                return this.date;
            }
            
            public void setDate(Date Date)
            {
                this.date = Date;
            }
            
            
        
            public String getDetail()
            {
                return this.detail;
            }
            
            public void setDetail(String Detail)
            {
                this.detail = Detail;
            }
            
            
        
            public Integer getUserActionTypeId()
            {
                return this.userActionTypeId;
            }
            
            public void setUserActionTypeId(Integer UserActionTypeId)
            {
                this.userActionTypeId = UserActionTypeId;
            }
            
            
                   
            public UserActionType getUserActionType()
                {
                    return this.userActionType;
                }

                public void setUserActionType(UserActionType userActionType)
                {
                    this.userActionType = userActionType;
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

