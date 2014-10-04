






















































    package com.busy.engine.dao;

    import com.busy.engine.data.BasicConnection;
    import com.busy.engine.entity.*;
    import com.busy.engine.dao.*;
    import com.busy.engine.util.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    import java.util.Map.Entry;
    import java.lang.reflect.InvocationTargetException;
    
    public class UserServiceDaoImpl extends BasicConnection implements Serializable, UserServiceDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public UserServiceDaoImpl()
        {
            cachingEnabled = false;
        }

        public UserServiceDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class UserServiceCache
        {
            public static final ConcurrentLruCache<Integer, UserService> userServiceCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (UserService i : findAll())
                {
                    getCache().put(i.getUserServiceId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, UserService> getCache()
        {
            return UserServiceCache.userServiceCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, UserService> buildCache(ArrayList<UserService> userServiceList)
        {        
            ConcurrentLruCache<Integer, UserService> cache = new ConcurrentLruCache<Integer, UserService>(userServiceList.size() + 1000);
            for (UserService i : userServiceList)
            {
                cache.put(i.getUserServiceId(), i);
            }
            return cache;
        }

        private static ArrayList<UserService> findAll()
        {
            ArrayList<UserService> userService = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("userService");
                while (rs.next())
                {
                    userService.add(UserService.process(rs));
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
            return userService;
        }
        
        @Override
        public UserService find(Integer id)
        {
            return findByColumn("UserServiceId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<UserService> findAll(Integer limit, Integer offset)
        {
            ArrayList<UserService> userServiceList = new ArrayList<UserService>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for UserService, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    userServiceList = new ArrayList<UserService>(getCache().getValues());
                }
                else
                {
                    cacheNotUsed = true;
                }
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("user_service", limit, offset);
                    while (rs.next())
                    {
                        userServiceList.add(UserService.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("UserService object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return userServiceList;
         
        }
        
        @Override
        public ArrayList<UserService> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<UserService> userServiceList = new ArrayList<UserService>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for UserService, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    userServiceList = new ArrayList<UserService>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            UserService userService = (UserService) e.getValue();

                            
                                getRecordById("Blog", userService.getBlogId().toString());
                                userService.setBlog(Blog.process(rs));               
                            
                                getRecordById("User", userService.getUserId().toString());
                                userService.setUser(User.process(rs));               
                            
                                getRecordById("Service", userService.getServiceId().toString());
                                userService.setService(Service.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object UserService method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                userServiceList = new ArrayList<UserService>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("user_service", limit, offset);
                    while (rs.next())
                    {
                        userServiceList.add(UserService.process(rs));
                    }

                    
                    
                        for (UserService userService : userServiceList)
                        {                        
                            
                                getRecordById("Blog", userService.getBlogId().toString());
                                userService.setBlog(Blog.process(rs));               
                            
                                getRecordById("User", userService.getUserId().toString());
                                userService.setUser(User.process(rs));               
                            
                                getRecordById("Service", userService.getServiceId().toString());
                                userService.setService(Service.process(rs));               
                              
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
            }
            return userServiceList;            
        }
        
        @Override
        public ArrayList<UserService> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<UserService> userServiceList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for UserService(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            UserService i = (UserService) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                userServiceList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            userServiceList = null;
                        }
                    }
                }
                else
                {
                    cacheNotUsed = true;
                }
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                try
                {
                    getRecordsByColumnWithLimitOrOffset("user_service", UserService.checkColumnName(columnName), columnValue, UserService.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        userServiceList.add(UserService.process(rs));
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
            }
            return userServiceList;
        } 
    
        @Override
        public int add(UserService obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                
                
                UserService.checkColumnSize(obj.getDetails(), 65535);
                UserService.checkColumnSize(obj.getContractUrl(), 255);
                UserService.checkColumnSize(obj.getDeliverableUrl(), 255);
                
                
                
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO user_service(UserServiceId,StartDate,EndDate,Details,ContractUrl,DeliverableUrl,DepositAmount,UserRank,BlogId,UserId,ServiceId,) VALUES (?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setInt(0, obj.getUserServiceId());
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
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("UserService's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setUserServiceId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
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
                prepareStatement("UPDATE user_service SET com.busy.util.DatabaseColumn@5eb87906=?,com.busy.util.DatabaseColumn@c513d6a=?,com.busy.util.DatabaseColumn@7fa03079=?,com.busy.util.DatabaseColumn@3798fb9=?,com.busy.util.DatabaseColumn@1e14a699=?,com.busy.util.DatabaseColumn@1b2b625c=?,com.busy.util.DatabaseColumn@65f6bab8=?,com.busy.util.DatabaseColumn@2abde5f8=?,com.busy.util.DatabaseColumn@11a6be58=?,com.busy.util.DatabaseColumn@51df17b2=? WHERE UserServiceId=?;");                    
                preparedStatement.setInt(0, obj.getUserServiceId());
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
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getUserServiceId(), obj);
                }            
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
            int count = 0;
            if (cachingEnabled)
            {
                count = getCache().size();
            }
            else
            {
                count = getAllRecordsCountByTableName("user_service");
            }
            return count;
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
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getUserServiceId());
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
            
            if(cachingEnabled && success)
            {
                getCache().remove(id);
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
        
            if(cachingEnabled && success)
            {
                getCache().clear();
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
            
            if(cachingEnabled && success)
            {
                ArrayList<Integer> keys = new ArrayList<Integer>();

                for (Entry e : getCache().getEntries())
                {
                    try
                    {
                        UserService i = (UserService) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getUserServiceId());
                        }
                    }
                    catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                    {
                        ex.printStackTrace();
                    }
                }

                for(int id : keys)
                {
                    getCache().remove(id);
                }
            }
            
            return success;
        }
        
        public boolean isCachingEnabled()
        {
            return cachingEnabled;
        }
        
        public void setCachingEnabled(boolean cachingEnabled)
        {
            this.cachingEnabled = cachingEnabled;
        }
        
                    
        public void getRelatedServiceChargeList(UserService user_service)
        {           
            user_service.setServiceChargeList(new ServiceChargeDaoImpl().findByColumn("UserServiceId", user_service.getUserServiceId().toString(),null,null));
        }        
        
                             
    }

