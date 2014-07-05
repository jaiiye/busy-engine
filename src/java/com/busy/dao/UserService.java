


 









 













    package com.busy.dao;

    import com.transitionsoft.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class UserService extends BasicConnection implements Serializable
    {    
        private static final long serialVersionUID = 1L;        
        public static final String PROP_USERSERVICEID = "UserServiceId";
        public static final String PROP_SERVICEID = "ServiceId";
        public static final String PROP_USERID = "UserId";
        public static final String PROP_BLOGID = "BlogId";
        public static final String PROP_STARTDATE = "StartDate";
        public static final String PROP_ENDDATE = "EndDate";
        public static final String PROP_DETAILS = "Details";
        public static final String PROP_CONTRACTURL = "ContractUrl";
        public static final String PROP_DELIVERABLEURL = "DeliverableUrl";
        public static final String PROP_DEPOSITAMOUNT = "DepositAmount";
        
        
        private Integer userServiceId;
        private Integer serviceId;
        private Integer userId;
        private Integer blogId;
        private Date startDate;
        private Date endDate;
        private String details;
        private String contractUrl;
        private String deliverableUrl;
        private Double depositAmount;
        
        
        public UserService()
        {
            this.userServiceId = 0; 
       this.serviceId = 0; 
       this.userId = 0; 
       this.blogId = 0; 
       this.startDate = null; 
       this.endDate = null; 
       this.details = ""; 
       this.contractUrl = ""; 
       this.deliverableUrl = ""; 
       this.depositAmount = 0.0; 
        }
        
        public UserService(Integer UserServiceId, Integer ServiceId, Integer UserId, Integer BlogId, Date StartDate, Date EndDate, String Details, String ContractUrl, String DeliverableUrl, Double DepositAmount)
        {
            this.userServiceId = UserServiceId;
       this.serviceId = ServiceId;
       this.userId = UserId;
       this.blogId = BlogId;
       this.startDate = StartDate;
       this.endDate = EndDate;
       this.details = Details;
       this.contractUrl = ContractUrl;
       this.deliverableUrl = DeliverableUrl;
       this.depositAmount = DepositAmount;
        } 
        
        
            public Integer getUserServiceId()
            {
                return this.userServiceId;
            }
            
            public void setUserServiceId(Integer UserServiceId)
            {
                this.userServiceId = UserServiceId;
            }
        
            public Integer getServiceId()
            {
                return this.serviceId;
            }
            
            public void setServiceId(Integer ServiceId)
            {
                this.serviceId = ServiceId;
            }
        
            public Integer getUserId()
            {
                return this.userId;
            }
            
            public void setUserId(Integer UserId)
            {
                this.userId = UserId;
            }
        
            public Integer getBlogId()
            {
                return this.blogId;
            }
            
            public void setBlogId(Integer BlogId)
            {
                this.blogId = BlogId;
            }
        
            public Date getStartDate()
            {
                return this.startDate;
            }
            
            public void setStartDate(Date StartDate)
            {
                this.startDate = StartDate;
            }
        
            public Date getEndDate()
            {
                return this.endDate;
            }
            
            public void setEndDate(Date EndDate)
            {
                this.endDate = EndDate;
            }
        
            public String getDetails()
            {
                return this.details;
            }
            
            public void setDetails(String Details)
            {
                this.details = Details;
            }
        
            public String getContractUrl()
            {
                return this.contractUrl;
            }
            
            public void setContractUrl(String ContractUrl)
            {
                this.contractUrl = ContractUrl;
            }
        
            public String getDeliverableUrl()
            {
                return this.deliverableUrl;
            }
            
            public void setDeliverableUrl(String DeliverableUrl)
            {
                this.deliverableUrl = DeliverableUrl;
            }
        
            public Double getDepositAmount()
            {
                return this.depositAmount;
            }
            
            public void setDepositAmount(Double DepositAmount)
            {
                this.depositAmount = DepositAmount;
            }
        
                
        public static String checkColumnName(String column) throws SQLException
        {            
            if(column.equals(UserService.PROP_USERSERVICEID) || column.equals(UserService.PROP_SERVICEID) || column.equals(UserService.PROP_USERID) || column.equals(UserService.PROP_BLOGID) || column.equals(UserService.PROP_STARTDATE) || column.equals(UserService.PROP_ENDDATE) || column.equals(UserService.PROP_DETAILS) || column.equals(UserService.PROP_CONTRACTURL) || column.equals(UserService.PROP_DELIVERABLEURL) || column.equals(UserService.PROP_DEPOSITAMOUNT) )
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
            if (column.equals(UserService.PROP_USERSERVICEID) || column.equals(UserService.PROP_SERVICEID) || column.equals(UserService.PROP_USERID) || column.equals(UserService.PROP_BLOGID) || column.equals(UserService.PROP_DEPOSITAMOUNT) )
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
            return new UserService(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getDouble(10));
        }
        
        public static int addUserService(Integer ServiceId, Integer UserId, Integer BlogId, Date StartDate, Date EndDate, String Details, String ContractUrl, String DeliverableUrl, Double DepositAmount)
        {   
            int id = 0;
            try
            {
                
                
                
                
                
                
                checkColumnSize(Details, 65535);
                checkColumnSize(ContractUrl, 255);
                checkColumnSize(DeliverableUrl, 255);
                
                                            
                openConnection();
                prepareStatement("INSERT INTO user_service(ServiceId,UserId,BlogId,StartDate,EndDate,Details,ContractUrl,DeliverableUrl,DepositAmount) VALUES (?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setInt(1, ServiceId);
                preparedStatement.setInt(2, UserId);
                preparedStatement.setInt(3, BlogId);
                preparedStatement.setDate(4, new java.sql.Date(StartDate.getTime()));
                preparedStatement.setDate(5, new java.sql.Date(EndDate.getTime()));
                preparedStatement.setString(6, Details);
                preparedStatement.setString(7, ContractUrl);
                preparedStatement.setString(8, DeliverableUrl);
                preparedStatement.setDouble(9, DepositAmount);
                
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
                
        public static void updateUserService(Integer UserServiceId,Integer ServiceId,Integer UserId,Integer BlogId,Date StartDate,Date EndDate,String Details,String ContractUrl,String DeliverableUrl,Double DepositAmount)
        {  
            try
            {   
                
                
                
                
                
                
                checkColumnSize(Details, 65535);
                checkColumnSize(ContractUrl, 255);
                checkColumnSize(DeliverableUrl, 255);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE user_service SET ServiceId=?,UserId=?,BlogId=?,StartDate=?,EndDate=?,Details=?,ContractUrl=?,DeliverableUrl=?,DepositAmount=? WHERE UserServiceId=?;");                    
                preparedStatement.setInt(1, ServiceId);
                preparedStatement.setInt(2, UserId);
                preparedStatement.setInt(3, BlogId);
                preparedStatement.setDate(4, new java.sql.Date(StartDate.getTime()));
                preparedStatement.setDate(5, new java.sql.Date(EndDate.getTime()));
                preparedStatement.setString(6, Details);
                preparedStatement.setString(7, ContractUrl);
                preparedStatement.setString(8, DeliverableUrl);
                preparedStatement.setDouble(9, DepositAmount);
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

