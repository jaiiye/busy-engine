





































    package com.busy.engine.dao;

import com.busy.engine.entity.Service;
import com.busy.engine.entity.UserService;
import com.busy.engine.entity.User;
import com.busy.engine.entity.Blog;
    import com.busy.engine.data.BasicConnection;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    
    public class UserServiceDaoImpl extends BasicConnection implements Serializable, UserServiceDao
    {    
        private static final long serialVersionUID = 1L;        
        
        @Override
        public UserService find(Integer id)
        {
            return findByColumn("UserServiceId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<UserService> findAll(Integer limit, Integer offset)
        {
            ArrayList<UserService> user_service = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("user_service");
                while(rs.next())
                {
                    user_service.add(UserService.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("UserService object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_service;
         
        }
        
        @Override
        public ArrayList<UserService> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<UserService> user_serviceList = new ArrayList<>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("user_service", limit, offset);
                while (rs.next())
                {
                    user_serviceList.add(UserService.process(rs));
                }

                
                    for(UserService user_service : user_serviceList)
                    {
                        
                            getRecordById("Blog", user_service.getBlogId().toString());
                            user_service.setBlog(Blog.process(rs));               
                        
                            getRecordById("User", user_service.getUserId().toString());
                            user_service.setUser(User.process(rs));               
                        
                            getRecordById("Service", user_service.getServiceId().toString());
                            user_service.setService(Service.process(rs));               
                        
                    }
             
            }
            catch (SQLException ex)
            {
                System.out.println("Object UserService method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_serviceList;
        }
        
        @Override
        public ArrayList<UserService> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<UserService> user_service = new ArrayList<>();
            try
            {
                getRecordsByColumnWithLimitOrOffset("user_service", UserService.checkColumnName(columnName), columnValue, UserService.isColumnNumeric(columnName), limit, offset);
                while(rs.next())
                {
                   user_service.add(UserService.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("UserService's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user_service;
        } 
    
        @Override
        public int add(UserService obj)
        {
            int id = 0;
            try
            {
                
                
                
                UserService.checkColumnSize(obj.getDetails(), 65535);
                UserService.checkColumnSize(obj.getContractUrl(), 255);
                UserService.checkColumnSize(obj.getDeliverableUrl(), 255);
                
                
                
                
                
                                            
                openConnection();
                prepareStatement("INSERT INTO user_service(StartDate,EndDate,Details,ContractUrl,DeliverableUrl,DepositAmount,UserRank,BlogId,UserId,ServiceId) VALUES (?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setDate(1, new java.sql.Date(obj.getStartDate().getTime()));
                preparedStatement.setDate(2, new java.sql.Date(obj.getEndDate().getTime()));
                preparedStatement.setString(3, obj.getDetails());
                preparedStatement.setString(4, obj.getContractUrl());
                preparedStatement.setString(5, obj.getDeliverableUrl());
                preparedStatement.setDouble(6, obj.getDepositAmount());
                preparedStatement.setInt(7, obj.getUserRank());
                preparedStatement.setInt(8, obj.getBlogId());
                preparedStatement.setInt(9, obj.getUserId());
                preparedStatement.setInt(10, obj.getServiceId());
                
                preparedStatement.executeUpdate(); 
                
                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from user_service;");
                while(rs.next())
                {
                    id =  rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                System.out.println("UserService's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return id;
        }
        
        @Override
        public UserService update(UserService obj)
        {
           try
            {   
                
                
                
                UserService.checkColumnSize(obj.getDetails(), 65535);
                UserService.checkColumnSize(obj.getContractUrl(), 255);
                UserService.checkColumnSize(obj.getDeliverableUrl(), 255);
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE user_service SET StartDate=?,EndDate=?,Details=?,ContractUrl=?,DeliverableUrl=?,DepositAmount=?,UserRank=?,BlogId=?,UserId=?,ServiceId=? WHERE UserServiceId=?;");                    
                preparedStatement.setDate(1, new java.sql.Date(obj.getStartDate().getTime()));
                preparedStatement.setDate(2, new java.sql.Date(obj.getEndDate().getTime()));
                preparedStatement.setString(3, obj.getDetails());
                preparedStatement.setString(4, obj.getContractUrl());
                preparedStatement.setString(5, obj.getDeliverableUrl());
                preparedStatement.setDouble(6, obj.getDepositAmount());
                preparedStatement.setInt(7, obj.getUserRank());
                preparedStatement.setInt(8, obj.getBlogId());
                preparedStatement.setInt(9, obj.getUserId());
                preparedStatement.setInt(10, obj.getServiceId());
                preparedStatement.setInt(11, obj.getUserServiceId());
                preparedStatement.executeUpdate();
            }
            catch (Exception ex)
            {
                System.out.println("UserService's update error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }  
            return obj;
        }
        
        @Override
        public int getAllCount()
        {        
            return getAllRecordsCountByTableName("user_service");
        }
        
        
        @Override
        public void getRelatedInfo(UserService user_service)
        {
            
                try
                { 
                    
                            getRecordById("Blog", user_service.getBlogId().toString());
                            user_service.setBlog(Blog.process(rs));                                       
                    
                            getRecordById("User", user_service.getUserId().toString());
                            user_service.setUser(User.process(rs));                                       
                    
                            getRecordById("Service", user_service.getServiceId().toString());
                            user_service.setService(Service.process(rs));                                       
                    
                    }
                catch (SQLException ex)
                {
                    System.out.println("getRelatedInfo error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }                    
              
        }
        
        @Override
        public void getRelatedObjects(UserService user_service)
        {
            user_service.setServiceChargeList(new ServiceChargeDaoImpl().findByColumn("UserServiceId", user_service.getUserServiceId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(UserService obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM user_service WHERE UserServiceId=" + obj.getUserServiceId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("UserService's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM user_service WHERE UserServiceId=" + id + ";");           
                success = true;           
            }
            catch (Exception ex)
            {
                System.out.println("removeById error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }

        @Override
        public boolean removeAll()
        {
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM user_service;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("UserService's removeAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }

        @Override
        public boolean removeByColumn(String columnName, String columnValue)
        {
            boolean success = false;
            try
            { 
                updateQuery("DELETE FROM user_service WHERE " + UserService.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("UserService's removeByColumn method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return success;
        }
        
                    
        public void getRelatedServiceChargeList(UserService user_service)
        {           
            user_service.setServiceChargeList(new ServiceChargeDaoImpl().findByColumn("UserServiceId", user_service.getUserServiceId().toString(),null,null));
        }        
        
                             
    }

