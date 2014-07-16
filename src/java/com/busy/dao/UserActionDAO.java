











 
















    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.UserAction;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class UserActionDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(UserAction.PROP_USER_ACTION_ID) || column.equals(UserAction.PROP_DATE) || column.equals(UserAction.PROP_DETAIL) || column.equals(UserAction.PROP_ACTION_TYPE_ID) || column.equals(UserAction.PROP_USER_ID) )
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
            if (column.equals(UserAction.PROP_USER_ACTION_ID) || column.equals(UserAction.PROP_ACTION_TYPE_ID) || column.equals(UserAction.PROP_USER_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static UserAction processUserAction(ResultSet rs) throws SQLException
        {        
            return new UserAction(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
        }
        
        public static int addUserAction(Date Date, String Detail, Integer ActionTypeId, Integer UserId)
        {   
            int id = 0;
            try
            {
                
                
                checkColumnSize(Detail, 65535);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO user_action(Date,Detail,ActionTypeId,UserId) VALUES (?,?,?,?);");                    
                preparedStatement.setDate(1, new java.sql.Date(Date.getTime()));
                preparedStatement.setString(2, Detail);
                preparedStatement.setInt(3, ActionTypeId);
                preparedStatement.setInt(4, UserId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from user_action;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addUserAction error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllUserActionCount()
        {
            return getAllRecordsCountByTableName("user_action");        
        }
        
        public static ArrayList<UserAction> getAllUserAction()
        {
            ArrayList<UserAction> user_action = new ArrayList<UserAction>();
            try
            {
                getAllRecordsByTableName("user_action");
                while(rs.next())
                {
                    user_action.add(processUserAction(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllUserAction error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_action;
        }
                
        public static ArrayList<UserAction> getUserActionPaged(int limit, int offset)
        {
            ArrayList<UserAction> user_action = new ArrayList<UserAction>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("user_action", limit, offset);
                while (rs.next())
                {
                    user_action.add(processUserAction(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getUserActionPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_action;
        } 
        
        public static ArrayList<UserAction> getAllUserActionByColumn(String columnName, String columnValue)
        {
            ArrayList<UserAction> user_action = new ArrayList<UserAction>();
            try
            {
                getAllRecordsByColumn("user_action", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    user_action.add(processUserAction(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllUserActionByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_action;
        }
        
        public static UserAction getUserActionByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            UserAction user_action = new UserAction();
            try
            {
                getRecordsByColumnWithLimitAndOffset("user_action", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   user_action = processUserAction(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getUserActionByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_action;
        }                
                
        public static void updateUserAction(Integer UserActionId,Date Date,String Detail,Integer ActionTypeId,Integer UserId)
        {  
            try
            {   
                
                
                checkColumnSize(Detail, 65535);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE user_action SET Date=?,Detail=?,ActionTypeId=?,UserId=? WHERE UserActionId=?;");                    
                preparedStatement.setDate(1, new java.sql.Date(Date.getTime()));
                preparedStatement.setString(2, Detail);
                preparedStatement.setInt(3, ActionTypeId);
                preparedStatement.setInt(4, UserId);
                preparedStatement.setInt(5, UserActionId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateUserAction error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllUserAction()
        {
            try
            {
                updateQuery("DELETE FROM user_action;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllUserAction error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteUserActionById(String id)
        {
            try
            {
                updateQuery("DELETE FROM user_action WHERE UserActionId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteUserActionById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteUserActionByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM user_action WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteUserActionByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

