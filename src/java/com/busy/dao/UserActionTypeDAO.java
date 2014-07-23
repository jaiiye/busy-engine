





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class UserActionTypeDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
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
                               
        public static UserActionType processUserActionType(ResultSet rs) throws SQLException
        {        
            return new UserActionType(rs.getInt(1), rs.getString(2));
        }
        
        public static int addUserActionType(String TypeName)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(TypeName, 255);
                                            
                openConnection();
                prepareStatement("INSERT INTO user_action_type(TypeName) VALUES (?);");                    
                preparedStatement.setString(1, TypeName);
                
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
        
        public static ArrayList<UserActionType> getAllUserActionTypeWithRelatedInfo()
        {
            ArrayList<UserActionType> user_action_typeList = new ArrayList<UserActionType>();
            try
            {
                getAllRecordsByTableName("user_action_type");
                while (rs.next())
                {
                    user_action_typeList.add(processUserActionType(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("getAllUserActionTypeWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_action_typeList;
        }
        
        
        public static UserActionType getRelatedInfo(UserActionType user_action_type)
        {
           
                  
            
            return user_action_type;
        }
        
        public static UserActionType getAllRelatedObjects(UserActionType user_action_type)
        {           
            user_action_type.setUserActionList(UserActionDAO.getAllUserActionByColumn("UserActionTypeId", user_action_type.getUserActionTypeId().toString()));
             
            return user_action_type;
        }
        
        
                    
        public static UserActionType getRelatedUserActionList(UserActionType user_action_type)
        {           
            user_action_type.setUserActionList(UserActionDAO.getAllUserActionByColumn("UserActionTypeId", user_action_type.getUserActionTypeId().toString()));
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
                
        public static void updateUserActionType(Integer UserActionTypeId,String TypeName)
        {  
            try
            {   
                
                checkColumnSize(TypeName, 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE user_action_type SET TypeName=? WHERE UserActionTypeId=?;");                    
                preparedStatement.setString(1, TypeName);
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

