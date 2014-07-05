


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class UserActionType extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_USERACTIONTYPEID = "UserActionTypeId";
        public static final String PROP_ACTIONTYPENAME = "ActionTypeName";
        
        
        private Integer userActionTypeId;
        private String actionTypeName;
        
        
        public UserActionType()
        {
            this.userActionTypeId = 0; 
       this.actionTypeName = ""; 
        }
        
        public UserActionType(Integer UserActionTypeId, String ActionTypeName)
        {
            this.userActionTypeId = UserActionTypeId;
       this.actionTypeName = ActionTypeName;
        } 
        
        
            public Integer getUserActionTypeId()
            {
                return this.userActionTypeId;
            }
            
            public void setUserActionTypeId(Integer UserActionTypeId)
            {
                this.userActionTypeId = UserActionTypeId;
            }
        
            public String getActionTypeName()
            {
                return this.actionTypeName;
            }
            
            public void setActionTypeName(String ActionTypeName)
            {
                this.actionTypeName = ActionTypeName;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(UserActionType.PROP_USERACTIONTYPEID) || column.equals(UserActionType.PROP_ACTIONTYPENAME) )
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
            if (column.equals(UserActionType.PROP_USERACTIONTYPEID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static UserActionType processUserActionType(ResultSet rs) throws SQLException
        {        
            return new UserActionType(rs.getInt(1), rs.getString(2));
        }
        
        public static int addUserActionType(String ActionTypeName)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(ActionTypeName, 255);
                                            
                openConnection();
                prepareStatement("INSERT INTO user_action_type(ActionTypeName) VALUES (?);");                    
                preparedStatement.setString(1, ActionTypeName);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from user_action_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addUserActionType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllUserActionTypeCount()
        {
            return getAllRecordsCountByTableName("user_action_type");        
        }
        
        public static ArrayList<UserActionType> getAllUserActionType()
        {
            ArrayList<UserActionType> user_action_type = new ArrayList<UserActionType>();
            try
            {
                getAllRecordsByTableName("user_action_type");
                while(rs.next())
                {
                    user_action_type.add(processUserActionType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllUserActionType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_action_type;
        }
                
        public static ArrayList<UserActionType> getUserActionTypePaged(int limit, int offset)
        {
            ArrayList<UserActionType> user_action_type = new ArrayList<UserActionType>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("user_action_type", limit, offset);
                while (rs.next())
                {
                    user_action_type.add(processUserActionType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getUserActionTypePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_action_type;
        } 
        
        public static ArrayList<UserActionType> getAllUserActionTypeByColumn(String columnName, String columnValue)
        {
            ArrayList<UserActionType> user_action_type = new ArrayList<UserActionType>();
            try
            {
                getAllRecordsByColumn("user_action_type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    user_action_type.add(processUserActionType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllUserActionTypeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_action_type;
        }
        
        public static UserActionType getUserActionTypeByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            UserActionType user_action_type = new UserActionType();
            try
            {
                getRecordsByColumnWithLimitAndOffset("user_action_type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   user_action_type = processUserActionType(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getUserActionTypeByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_action_type;
        }                
                
        public static void updateUserActionType(Integer UserActionTypeId,String ActionTypeName)
        {  
            try
            {   
                
                checkColumnSize(ActionTypeName, 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE user_action_type SET ActionTypeName=? WHERE UserActionTypeId=?;");                    
                preparedStatement.setString(1, ActionTypeName);
                preparedStatement.setInt(2, UserActionTypeId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateUserActionType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllUserActionType()
        {
            try
            {
                updateQuery("DELETE FROM user_action_type;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllUserActionType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteUserActionTypeById(String id)
        {
            try
            {
                updateQuery("DELETE FROM user_action_type WHERE UserActionTypeId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteUserActionTypeById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteUserActionTypeByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM user_action_type WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteUserActionTypeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

