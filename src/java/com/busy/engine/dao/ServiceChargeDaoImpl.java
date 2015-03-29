






















































    package com.busy.engine.dao;

    import com.busy.engine.data.BasicConnection;
    import com.busy.engine.entity.*;
    import com.busy.engine.util.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    import java.util.Map.Entry;
    import java.lang.reflect.InvocationTargetException;
    
    public class ServiceChargeDaoImpl extends BasicConnection implements Serializable, ServiceChargeDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public ServiceChargeDaoImpl()
        {
            cachingEnabled = false;
        }

        public ServiceChargeDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class ServiceChargeCache
        {
            public static final ConcurrentLruCache<Integer, ServiceCharge> serviceChargeCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (ServiceCharge i : findAll())
                {
                    getCache().put(i.getServiceChargeId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, ServiceCharge> getCache()
        {
            return ServiceChargeCache.serviceChargeCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, ServiceCharge> buildCache(ArrayList<ServiceCharge> serviceChargeList)
        {        
            ConcurrentLruCache<Integer, ServiceCharge> cache = new ConcurrentLruCache<Integer, ServiceCharge>(serviceChargeList.size() + 1000);
            for (ServiceCharge i : serviceChargeList)
            {
                cache.put(i.getServiceChargeId(), i);
            }
            return cache;
        }

        private static ArrayList<ServiceCharge> findAll()
        {
            ArrayList<ServiceCharge> serviceCharge = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("serviceCharge");
                while (rs.next())
                {
                    serviceCharge.add(ServiceCharge.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ServiceCharge object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return serviceCharge;
        }
        
        @Override
        public ServiceCharge find(Integer id)
        {
            return findByColumn("ServiceChargeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ServiceCharge findWithInfo(Integer id)
        {
            ServiceCharge serviceCharge = findByColumn("ServiceChargeId", id.toString(), null, null).get(0);
            
            
                try
                {

                
                    getRecordById("UserService", serviceCharge.getUserServiceId().toString());
                    serviceCharge.setUserService(UserService.process(rs));               
                  

                }
                catch (SQLException ex)
                {
                        System.out.println("Object ServiceCharge method findWithInfo(Integer id) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            
            
            return serviceCharge;
        }
        
        @Override
        public ArrayList<ServiceCharge> findAll(Integer limit, Integer offset)
        {
            ArrayList<ServiceCharge> serviceChargeList = new ArrayList<ServiceCharge>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for ServiceCharge, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    serviceChargeList = new ArrayList<ServiceCharge>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("service_charge", limit, offset);
                    while (rs.next())
                    {
                        serviceChargeList.add(ServiceCharge.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ServiceCharge object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return serviceChargeList;
         
        }
        
        @Override
        public ArrayList<ServiceCharge> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ServiceCharge> serviceChargeList = new ArrayList<ServiceCharge>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for ServiceCharge, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    serviceChargeList = new ArrayList<ServiceCharge>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            ServiceCharge serviceCharge = (ServiceCharge) e.getValue();

                            
                                getRecordById("UserService", serviceCharge.getUserServiceId().toString());
                                serviceCharge.setUserService(UserService.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object ServiceCharge method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                serviceChargeList = new ArrayList<ServiceCharge>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("service_charge", limit, offset);
                    while (rs.next())
                    {
                        serviceChargeList.add(ServiceCharge.process(rs));
                    }

                    
                    
                        for (ServiceCharge serviceCharge : serviceChargeList)
                        {                        
                            
                                getRecordById("UserService", serviceCharge.getUserServiceId().toString());
                                serviceCharge.setUserService(UserService.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object ServiceCharge method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return serviceChargeList;            
        }
        
        @Override
        public ArrayList<ServiceCharge> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ServiceCharge> serviceChargeList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for ServiceCharge(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            ServiceCharge i = (ServiceCharge) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                serviceChargeList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            serviceChargeList = null;
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
                    getRecordsByColumnWithLimitOrOffset("service_charge", ServiceCharge.checkColumnName(columnName), columnValue, ServiceCharge.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        serviceChargeList.add(ServiceCharge.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ServiceCharge's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return serviceChargeList;
        } 
    
        @Override
        public int add(ServiceCharge obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                ServiceCharge.checkColumnSize(obj.getChargeName(), 100);
                ServiceCharge.checkColumnSize(obj.getDescription(), 65535);
                ServiceCharge.checkColumnSize(obj.getRate(), 12);
                ServiceCharge.checkColumnSize(obj.getUnits(), 12);
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO service_charge(ChargeName,Description,Rate,Units,Date,UserServiceId) VALUES (?,?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getChargeName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getRate());
                preparedStatement.setString(4, obj.getUnits());
                preparedStatement.setDate(5, new java.sql.Date(obj.getDate().getTime()));
                preparedStatement.setInt(6, obj.getUserServiceId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from service_charge;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ServiceCharge's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setServiceChargeId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public ServiceCharge update(ServiceCharge obj)
        {
           try
            {   
                
                ServiceCharge.checkColumnSize(obj.getChargeName(), 100);
                ServiceCharge.checkColumnSize(obj.getDescription(), 65535);
                ServiceCharge.checkColumnSize(obj.getRate(), 12);
                ServiceCharge.checkColumnSize(obj.getUnits(), 12);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE service_charge SET ChargeName=?,Description=?,Rate=?,Units=?,Date=?,UserServiceId=? WHERE ServiceChargeId=?;");                    
                preparedStatement.setString(1, obj.getChargeName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getRate());
                preparedStatement.setString(4, obj.getUnits());
                preparedStatement.setDate(5, new java.sql.Date(obj.getDate().getTime()));
                preparedStatement.setInt(6, obj.getUserServiceId());
                preparedStatement.setInt(7, obj.getServiceChargeId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getServiceChargeId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("ServiceCharge's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("service_charge");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(ServiceCharge service_charge)
        {
            
                try
                { 
                    
                            getRecordById("UserService", service_charge.getUserServiceId().toString());
                            service_charge.setUserService(UserService.process(rs));                                       
                    
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
        public void getRelatedObjects(ServiceCharge service_charge)
        {
            service_charge.setServiceList(new ServiceDaoImpl().findByColumn("ServiceChargeId", service_charge.getServiceChargeId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(ServiceCharge obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM service_charge WHERE ServiceChargeId=" + obj.getServiceChargeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ServiceCharge's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getServiceChargeId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM service_charge WHERE ServiceChargeId=" + id + ";");           
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
                updateQuery("DELETE FROM service_charge;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ServiceCharge's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM service_charge WHERE " + ServiceCharge.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ServiceCharge's removeByColumn method error: " + ex.getMessage());
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
                        ServiceCharge i = (ServiceCharge) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getServiceChargeId());
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
        
                    
        public void getRelatedServiceList(ServiceCharge service_charge)
        {           
            service_charge.setServiceList(new ServiceDaoImpl().findByColumn("ServiceChargeId", service_charge.getServiceChargeId().toString(),null,null));
        }        
        
            
        
        
        public void getRelatedUserService(ServiceCharge service_charge)
        {            
            try
            {                 
                getRecordById("UserService", service_charge.getUserServiceId().toString());
                service_charge.setUserService(UserService.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getUserService error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
          
        
                
        
        public void getRelatedUserServiceWithInfo(ServiceCharge service_charge)
        {            
            service_charge.setUserService(new UserServiceDaoImpl().findWithInfo(service_charge.getUserServiceId()));
        }
          
        
    }

