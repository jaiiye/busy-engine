











 











    package com.busy.engine.entity;

    import java.util.ArrayList;
    import java.util.Date;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import javax.json.JsonObjectBuilder;
    import java.text.SimpleDateFormat;
    
    public class UserActionType extends AbstractEntity implements EntityItem<Integer>
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
       
       @Override
       public Integer getId()
       {
            
            return userActionTypeId;
       }
       
        @Override
        public void addJson(JsonObjectBuilder builder)
        {
        builder.add("userActionTypeId", userActionTypeId).add("typeName", typeName);
        
             
        }
       
       public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(UserActionType.PROP_USER_ACTION_TYPE_ID) || column.equals(UserActionType.PROP_TYPE_NAME) )
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
            if (column.equals(UserActionType.PROP_USER_ACTION_TYPE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static UserActionType process(ResultSet rs) throws SQLException
        {        
            return new UserActionType(rs.getInt(1), rs.getString(2));
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

