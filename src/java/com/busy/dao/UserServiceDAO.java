


 









 
















    package com.busy.dao;

    import com.transitionsoft.*;
    import com.busy.entity.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class UserServiceDAO extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
               
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(UserService.PROP_USER_SERVICE_ID) || column.equals(UserService.PROP_START_DATE) || column.equals(UserService.PROP_END_DATE) || column.equals(UserService.PROP_DETAILS) || column.equals(UserService.PROP_CONTRACT_URL) || column.equals(UserService.PROP_DELIVERABLE_URL) || column.equals(UserService.PROP_DEPOSIT_AMOUNT) || column.equals(UserService.PROP_BLOG_ID) || column.equals(UserService.PROP_USER_ID) || column.equals(UserService.PROP_SERVICE_ID) )
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
            if (column.equals(UserService.PROP_USER_SERVICE_ID) || column.equals(UserService.PROP_DEPOSIT_AMOUNT) || column.equals(UserService.PROP_BLOG_ID) || column.equals(UserService.PROP_USER_ID) || column.equals(UserService.PROP_SERVICE_ID) )
            {
                return true;
            }        
            else
            {            
                return false;
            }
        }
                               
        public static UserService processUserService(ResultSet rs) throws SQLException
        {        
            return new UserService(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));
        }
        
        public static int addUserService(Date StartDate, Date EndDate, String Details, String ContractUrl, String DeliverableUrl, Double DepositAmount, Integer BlogId, Integer UserId, Integer ServiceId)
        {   
            int id = 0;
            try
            {
                
                
                
                checkColumnSize(Details, 65535);
                checkColumnSize(ContractUrl, 255);
                checkColumnSize(DeliverableUrl, 255);
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO user_service(StartDate,EndDate,Details,ContractUrl,DeliverableUrl,DepositAmount,BlogId,UserId,ServiceId) VALUES (?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setDate(1, new java.sql.Date(StartDate.getTime()));
                preparedStatement.setDate(2, new java.sql.Date(EndDate.getTime()));
                preparedStatement.setString(3, Details);
                preparedStatement.setString(4, ContractUrl);
                preparedStatement.setString(5, DeliverableUrl);
                preparedStatement.setDouble(6, DepositAmount);
                preparedStatement.setInt(7, BlogId);
                preparedStatement.setInt(8, UserId);
                preparedStatement.setInt(9, ServiceId);
                
                preparedStatement.executeUpdate();
            
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from user_service;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("addUserService error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }        
        
        public static int getAllUserServiceCount()
        {
            return getAllRecordsCountByTableName("user_service");        
        }
        
        public static ArrayList<UserService> getAllUserService()
        {
            ArrayList<UserService> user_service = new ArrayList<UserService>();
            try
            {
                getAllRecordsByTableName("user_service");
                while(rs.next())
                {
                    user_service.add(processUserService(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllUserService error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_service;
        }
                
        public static ArrayList<UserService> getUserServicePaged(int limit, int offset)
        {
            ArrayList<UserService> user_service = new ArrayList<UserService>();
            try
            {
                getRecordsByTableNameWithLimitAndOffset("user_service", limit, offset);
                while (rs.next())
                {
                    user_service.add(processUserService(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getUserServicePaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_service;
        } 
        
        public static ArrayList<UserService> getAllUserServiceByColumn(String columnName, String columnValue)
        {
            ArrayList<UserService> user_service = new ArrayList<UserService>();
            try
            {
                getAllRecordsByColumn("user_service", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
                while(rs.next())
                {
                    user_service.add(processUserService(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getAllUserServiceByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_service;
        }
        
        public static UserService getUserServiceByColumnPaged(String columnName, String columnValue, int limit, int offset)
        {
            UserService user_service = new UserService();
            try
            {
                getRecordsByColumnWithLimitAndOffset("user_service", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   user_service = processUserService(rs);
                }
            }
            catch (SQLException ex)
            {
                System.out.println("getUserServiceByColumnPaged error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_service;
        }                
                
        public static void updateUserService(Integer UserServiceId,Date StartDate,Date EndDate,String Details,String ContractUrl,String DeliverableUrl,Double DepositAmount,Integer BlogId,Integer UserId,Integer ServiceId)
        {  
            try
            {   
                
                
                
                checkColumnSize(Details, 65535);
                checkColumnSize(ContractUrl, 255);
                checkColumnSize(DeliverableUrl, 255);
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE user_service SET StartDate=?,EndDate=?,Details=?,ContractUrl=?,DeliverableUrl=?,DepositAmount=?,BlogId=?,UserId=?,ServiceId=? WHERE UserServiceId=?;");                    
                preparedStatement.setDate(1, new java.sql.Date(StartDate.getTime()));
                preparedStatement.setDate(2, new java.sql.Date(EndDate.getTime()));
                preparedStatement.setString(3, Details);
                preparedStatement.setString(4, ContractUrl);
                preparedStatement.setString(5, DeliverableUrl);
                preparedStatement.setDouble(6, DepositAmount);
                preparedStatement.setInt(7, BlogId);
                preparedStatement.setInt(8, UserId);
                preparedStatement.setInt(9, ServiceId);
                preparedStatement.setInt(10, UserServiceId);
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("updateUserService error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteAllUserService()
        {
            try
            {
                updateQuery("DELETE FROM user_service;");
            }
            catch (Exception ex)
            {
                System.out.println("deleteAllUserService error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        
        public static void deleteUserServiceById(String id)
        {
            try
            {
                updateQuery("DELETE FROM user_service WHERE UserServiceId=" + id + ";");            
            }
            catch (Exception ex)
            {
                System.out.println("deleteUserServiceById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }

        public static void deleteUserServiceByColumn(String columnName, String columnValue)
        {
            try
            { 
                updateQuery("DELETE FROM user_service WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");        
            }
            catch (Exception ex)
            {
                System.out.println("deleteUserServiceByColumn error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
            
    }

