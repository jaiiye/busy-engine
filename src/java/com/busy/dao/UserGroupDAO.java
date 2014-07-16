











 
















    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.UserGroup;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class UserGroupDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(UserGroup.PROP_USER_GROUP_ID) || column.equals(UserGroup.PROP_GROUP_NAME) || column.equals(UserGroup.PROP_SITE_ID) || column.equals(UserGroup.PROP_DISCOUNT_ID) )
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
            if (column.equals(UserGroup.PROP_USER_GROUP_ID) || column.equals(UserGroup.PROP_SITE_ID) || column.equals(UserGroup.PROP_DISCOUNT_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static UserGroup processUserGroup(ResultSet rs) throws SQLException
        {        
            return new UserGroup(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
        }
        
        public static int addUserGroup(String GroupName, Integer SiteId, Integer DiscountId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(GroupName, 100);
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO user_group(GroupName,SiteId,DiscountId) VALUES (?,?,?);");                    
                preparedStatement.setString(1, GroupName);
                preparedStatement.setInt(2, SiteId);
                preparedStatement.setInt(3, DiscountId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from user_group;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addUserGroup error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllUserGroupCount()
        {
            return getAllRecordsCountByTableName("user_group");        
        }
        
        public static ArrayList<UserGroup> getAllUserGroup()
        {
            ArrayList<UserGroup> user_group = new ArrayList<UserGroup>();
            try
            {
                getAllRecordsByTableName("user_group");
                while(rs.next())
                {
                    user_group.add(processUserGroup(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllUserGroup error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_group;
        }
                
        public static ArrayList<UserGroup> getUserGroupPaged(int limit, int offset)
        {
            ArrayList<UserGroup> user_group = new ArrayList<UserGroup>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("user_group", limit, offset);
                while (rs.next())
                {
                    user_group.add(processUserGroup(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getUserGroupPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_group;
        } 
        
        public static ArrayList<UserGroup> getAllUserGroupByColumn(String columnName, String columnValue)
        {
            ArrayList<UserGroup> user_group = new ArrayList<UserGroup>();
            try
            {
                getAllRecordsByColumn("user_group", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    user_group.add(processUserGroup(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllUserGroupByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_group;
        }
        
        public static UserGroup getUserGroupByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            UserGroup user_group = new UserGroup();
            try
            {
                getRecordsByColumnWithLimitAndOffset("user_group", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   user_group = processUserGroup(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getUserGroupByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_group;
        }                
                
        public static void updateUserGroup(Integer UserGroupId,String GroupName,Integer SiteId,Integer DiscountId)
        {  
            try
            {   
                
                checkColumnSize(GroupName, 100);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE user_group SET GroupName=?,SiteId=?,DiscountId=? WHERE UserGroupId=?;");                    
                preparedStatement.setString(1, GroupName);
                preparedStatement.setInt(2, SiteId);
                preparedStatement.setInt(3, DiscountId);
                preparedStatement.setInt(4, UserGroupId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateUserGroup error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllUserGroup()
        {
            try
            {
                updateQuery("DELETE FROM user_group;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllUserGroup error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteUserGroupById(String id)
        {
            try
            {
                updateQuery("DELETE FROM user_group WHERE UserGroupId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteUserGroupById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteUserGroupByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM user_group WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteUserGroupByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

