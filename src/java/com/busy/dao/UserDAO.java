


 









 
















    package com.busy.dao;

    import com.transitionsoft.*;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class UserDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(User.PROP_USER_ID) || column.equals(User.PROP_USERNAME) || column.equals(User.PROP_PASSWORD) || column.equals(User.PROP_EMAIL) || column.equals(User.PROP_SECURITY_QUESTION) || column.equals(User.PROP_SECURITY_ANSWER) || column.equals(User.PROP_REGISTER_DATE) || column.equals(User.PROP_IMAGE_U_R_L) || column.equals(User.PROP_STATUS) || column.equals(User.PROP_WEB_URL) || column.equals(User.PROP_BRAND_ID) || column.equals(User.PROP_USER_TYPE_ID) || column.equals(User.PROP_ADDRESS_ID) || column.equals(User.PROP_CONTACT_ID) || column.equals(User.PROP_USER_GROUP_ID) )
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
            if (column.equals(User.PROP_USER_ID) || column.equals(User.PROP_STATUS) || column.equals(User.PROP_BRAND_ID) || column.equals(User.PROP_USER_TYPE_ID) || column.equals(User.PROP_ADDRESS_ID) || column.equals(User.PROP_CONTACT_ID) || column.equals(User.PROP_USER_GROUP_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static User processUser(ResultSet rs) throws SQLException
        {        
            return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7), rs.getString(8), rs.getInt(9), rs.getString(10), rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14), rs.getInt(15));
        }
        
        public static int addUser(String Username, String Password, String Email, String SecurityQuestion, String SecurityAnswer, Date RegisterDate, String ImageURL, Integer Status, String WebUrl, Integer BrandId, Integer UserTypeId, Integer AddressId, Integer ContactId, Integer UserGroupId)
        {   
            int id = 0;
            try
            {
                
                checkColumnSize(Username, 30);
                checkColumnSize(Password, 15);
                checkColumnSize(Email, 255);
                checkColumnSize(SecurityQuestion, 100);
                checkColumnSize(SecurityAnswer, 30);
                
                checkColumnSize(ImageURL, 255);
                
                checkColumnSize(WebUrl, 255);
                
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO user(Username,Password,Email,SecurityQuestion,SecurityAnswer,RegisterDate,ImageURL,Status,WebUrl,BrandId,UserTypeId,AddressId,ContactId,UserGroupId) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, Username);
                preparedStatement.setString(2, Password);
                preparedStatement.setString(3, Email);
                preparedStatement.setString(4, SecurityQuestion);
                preparedStatement.setString(5, SecurityAnswer);
                preparedStatement.setDate(6, new java.sql.Date(RegisterDate.getTime()));
                preparedStatement.setString(7, ImageURL);
                preparedStatement.setInt(8, Status);
                preparedStatement.setString(9, WebUrl);
                preparedStatement.setInt(10, BrandId);
                preparedStatement.setInt(11, UserTypeId);
                preparedStatement.setInt(12, AddressId);
                preparedStatement.setInt(13, ContactId);
                preparedStatement.setInt(14, UserGroupId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from user;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addUser error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllUserCount()
        {
            return getAllRecordsCountByTableName("user");        
        }
        
        public static ArrayList<User> getAllUser()
        {
            ArrayList<User> user = new ArrayList<User>();
            try
            {
                getAllRecordsByTableName("user");
                while(rs.next())
                {
                    user.add(processUser(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllUser error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user;
        }
                
        public static ArrayList<User> getUserPaged(int limit, int offset)
        {
            ArrayList<User> user = new ArrayList<User>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("user", limit, offset);
                while (rs.next())
                {
                    user.add(processUser(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getUserPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user;
        } 
        
        public static ArrayList<User> getAllUserByColumn(String columnName, String columnValue)
        {
            ArrayList<User> user = new ArrayList<User>();
            try
            {
                getAllRecordsByColumn("user", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    user.add(processUser(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllUserByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user;
        }
        
        public static User getUserByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            User user = new User();
            try
            {
                getRecordsByColumnWithLimitAndOffset("user", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   user = processUser(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getUserByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user;
        }                
                
        public static void updateUser(Integer UserId,String Username,String Password,String Email,String SecurityQuestion,String SecurityAnswer,Date RegisterDate,String ImageURL,Integer Status,String WebUrl,Integer BrandId,Integer UserTypeId,Integer AddressId,Integer ContactId,Integer UserGroupId)
        {  
            try
            {   
                
                checkColumnSize(Username, 30);
                checkColumnSize(Password, 15);
                checkColumnSize(Email, 255);
                checkColumnSize(SecurityQuestion, 100);
                checkColumnSize(SecurityAnswer, 30);
                
                checkColumnSize(ImageURL, 255);
                
                checkColumnSize(WebUrl, 255);
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE user SET Username=?,Password=?,Email=?,SecurityQuestion=?,SecurityAnswer=?,RegisterDate=?,ImageURL=?,Status=?,WebUrl=?,BrandId=?,UserTypeId=?,AddressId=?,ContactId=?,UserGroupId=? WHERE UserId=?;");                    
                preparedStatement.setString(1, Username);
                preparedStatement.setString(2, Password);
                preparedStatement.setString(3, Email);
                preparedStatement.setString(4, SecurityQuestion);
                preparedStatement.setString(5, SecurityAnswer);
                preparedStatement.setDate(6, new java.sql.Date(RegisterDate.getTime()));
                preparedStatement.setString(7, ImageURL);
                preparedStatement.setInt(8, Status);
                preparedStatement.setString(9, WebUrl);
                preparedStatement.setInt(10, BrandId);
                preparedStatement.setInt(11, UserTypeId);
                preparedStatement.setInt(12, AddressId);
                preparedStatement.setInt(13, ContactId);
                preparedStatement.setInt(14, UserGroupId);
                preparedStatement.setInt(15, UserId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateUser error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllUser()
        {
            try
            {
                updateQuery("DELETE FROM user;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllUser error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteUserById(String id)
        {
            try
            {
                updateQuery("DELETE FROM user WHERE UserId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteUserById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteUserByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM user WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteUserByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

