





































    package com.busy.dao;

    import com.transitionsoft.BasicConnection;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class UserTypeDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(UserType.PROP_USER_TYPE_ID) || column.equals(UserType.PROP_TYPE_NAME) || column.equals(UserType.PROP_DESCRIPTION) || column.equals(UserType.PROP_REDIRECT_U_R_L) )
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
                               
        public static UserType processUserType(ResultSet rs) throws SQLException
        {        
            return new UserType(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
        }
        
        public static int addUserType(String TypeName, String Description, String RedirectURL)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(TypeName, 45);
                checkColumnSize(Description, 255);
                checkColumnSize(RedirectURL, 255);
                                            
                openConnection();
                prepareStatement("INSERT INTO user_type(TypeName,Description,RedirectURL) VALUES (?,?,?);");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setString(2, Description);
                preparedStatement.setString(3, RedirectURL);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from user_type;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addUserType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllUserTypeCount()
        {
            return getAllRecordsCountByTableName("user_type");        
        }
        
        public static ArrayList<UserType> getAllUserType()
        {
            ArrayList<UserType> user_type = new ArrayList<UserType>();
            try
            {
                getAllRecordsByTableName("user_type");
                while(rs.next())
                {
                    user_type.add(processUserType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllUserType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_type;
        }
        
        public static ArrayList<UserType> getAllUserTypeWithRelatedInfo()
        {
            ArrayList<UserType> user_typeList = new ArrayList<UserType>();
            try
            {
                getAllRecordsByTableName("user_type");
                while (rs.next())
                {
                    user_typeList.add(processUserType(rs));
                }

                
            }
            catch (SQLException ex)
            {
                System.out.println("getAllUserTypeWithRelatedInfo error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_typeList;
        }
        
        
        public static UserType getRelatedInfo(UserType user_type)
        {
           
                  
            
            return user_type;
        }
        
        public static UserType getAllRelatedObjects(UserType user_type)
        {           
            user_type.setUserList(UserDAO.getAllUserByColumn("UserTypeId", user_type.getUserTypeId().toString()));
             
            return user_type;
        }
        
        
                    
        public static UserType getRelatedUserList(UserType user_type)
        {           
            user_type.setUserList(UserDAO.getAllUserByColumn("UserTypeId", user_type.getUserTypeId().toString()));
            return user_type;
        }        
        
                
        public static ArrayList<UserType> getUserTypePaged(int limit, int offset)
        {
            ArrayList<UserType> user_type = new ArrayList<UserType>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("user_type", limit, offset);
                while (rs.next())
                {
                    user_type.add(processUserType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getUserTypePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_type;
        } 
        
        public static ArrayList<UserType> getAllUserTypeByColumn(String columnName, String columnValue)
        {
            ArrayList<UserType> user_type = new ArrayList<UserType>();
            try
            {
                getAllRecordsByColumn("user_type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    user_type.add(processUserType(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllUserTypeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_type;
        }
        
        public static UserType getUserTypeByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            UserType user_type = new UserType();
            try
            {
                getRecordsByColumnWithLimitAndOffset("user_type", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   user_type = processUserType(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getUserTypeByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_type;
        }                
                
        public static void updateUserType(Integer UserTypeId,String TypeName,String Description,String RedirectURL)
        {  
            try
            {   
                
                checkColumnSize(TypeName, 45);
                checkColumnSize(Description, 255);
                checkColumnSize(RedirectURL, 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE user_type SET TypeName=?,Description=?,RedirectURL=? WHERE UserTypeId=?;");                    
                preparedStatement.setString(1, TypeName);
                preparedStatement.setString(2, Description);
                preparedStatement.setString(3, RedirectURL);
                preparedStatement.setInt(4, UserTypeId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateUserType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllUserType()
        {
            try
            {
                updateQuery("DELETE FROM user_type;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllUserType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteUserTypeById(String id)
        {
            try
            {
                updateQuery("DELETE FROM user_type WHERE UserTypeId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteUserTypeById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteUserTypeByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM user_type WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteUserTypeByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

