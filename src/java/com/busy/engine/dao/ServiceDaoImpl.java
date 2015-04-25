
























































    package com.busy.engine.dao;

    import com.busy.engine.data.BasicConnection;
    import com.busy.engine.data.Column;
    import com.busy.engine.entity.*;
    import com.busy.engine.util.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    import java.util.Map.Entry;
    import java.lang.reflect.InvocationTargetException;
    
    public class ServiceDaoImpl extends BasicConnection implements Serializable, ServiceDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public ServiceDaoImpl()
        {
            cachingEnabled = false;
        }

        public ServiceDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class ServiceCache
        {
            public static final ConcurrentLruCache<Integer, Service> serviceCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (Service i : findAll())
                {
                    getCache().put(i.getServiceId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, Service> getCache()
        {
            return ServiceCache.serviceCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, Service> buildCache(ArrayList<Service> serviceList)
        {        
            ConcurrentLruCache<Integer, Service> cache = new ConcurrentLruCache<Integer, Service>(serviceList.size() + 1000);
            for (Service i : serviceList)
            {
                cache.put(i.getServiceId(), i);
            }
            return cache;
        }

        private static ArrayList<Service> findAll()
        {
            ArrayList<Service> service = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("service");
                while (rs.next())
                {
                    service.add(Service.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Service object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return service;
        }
        
        @Override
        public Service find(Integer id)
        {
            return findByColumn("ServiceId", id.toString(), null, null).get(0);
        }
        
        @Override
        public Service findWithInfo(Integer id)
        {
            Service service = findByColumn("ServiceId", id.toString(), null, null).get(0);
            
            
                try
                {

                
                    getRecordById("service_charge", service.getServiceChargeId().toString());
                    service.setServiceCharge(ServiceCharge.process(rs));               
                
                    getRecordById("service_type", service.getServiceTypeId().toString());
                    service.setServiceType(ServiceType.process(rs));               
                  

                }
                catch (SQLException ex)
                {
                        System.out.println("Object Service method findWithInfo(Integer id) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            
            
            return service;
        }
        
        @Override
        public ArrayList<Service> findAll(Integer limit, Integer offset)
        {
            ArrayList<Service> serviceList = new ArrayList<Service>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for Service, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    serviceList = new ArrayList<Service>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("service", limit, offset);
                    while (rs.next())
                    {
                        serviceList.add(Service.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Service object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return serviceList;
         
        }
        
        @Override
        public ArrayList<Service> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Service> serviceList = new ArrayList<Service>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for Service, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    serviceList = new ArrayList<Service>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            Service service = (Service) e.getValue();

                            
                                getRecordById("service_charge", service.getServiceChargeId().toString());
                                service.setServiceCharge(ServiceCharge.process(rs));               
                            
                                getRecordById("service_type", service.getServiceTypeId().toString());
                                service.setServiceType(ServiceType.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object Service method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                serviceList = new ArrayList<Service>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("service", limit, offset);
                    while (rs.next())
                    {
                        serviceList.add(Service.process(rs));
                    }

                    
                    
                        for (Service service : serviceList)
                        {                        
                            
                                getRecordById("service_charge", service.getServiceChargeId().toString());
                                service.setServiceCharge(ServiceCharge.process(rs));               
                            
                                getRecordById("service_type", service.getServiceTypeId().toString());
                                service.setServiceType(ServiceType.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object Service method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return serviceList;            
        }
        
        @Override
        public ArrayList<Service> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Service> serviceList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for Service(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            Service i = (Service) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                serviceList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            serviceList = null;
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
                    getRecordsByColumnWithLimitOrOffset("service", Service.checkColumnName(columnName), columnValue, Service.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        serviceList.add(Service.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Service's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return serviceList;
        } 
        
        @Override
        public ArrayList<Service> findByColumns(Column... columns)
        {
            ArrayList<Service> serviceList = new ArrayList<>();

            try
            {
                //make sure the correct isNumeric values are set for columns
                for(Column c : columns) 
                {
                    c.setNumeric(Service.isColumnNumeric(c.getColumnName()));                
                }

                getAllRecordsByColumns("service", columns);
                while (rs.next())
                {
                    serviceList.add(Service.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Service's method findByColumns(Column... columns) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }

            return serviceList;
        }
    
        @Override
        public int add(Service obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                Service.checkColumnSize(obj.getServiceName(), 100);
                Service.checkColumnSize(obj.getDescription(), 65535);
                
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO service(ServiceName,Description,ServiceStatus,ServiceChargeId,ServiceTypeId) VALUES (?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getServiceName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setInt(3, obj.getServiceStatus());
                preparedStatement.setInt(4, obj.getServiceChargeId());
                preparedStatement.setInt(5, obj.getServiceTypeId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from service;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Service's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setServiceId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public Service update(Service obj)
        {
           try
            {   
                
                Service.checkColumnSize(obj.getServiceName(), 100);
                Service.checkColumnSize(obj.getDescription(), 65535);
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE service SET ServiceName=?,Description=?,ServiceStatus=?,ServiceChargeId=?,ServiceTypeId=? WHERE ServiceId=?;");                    
                preparedStatement.setString(1, obj.getServiceName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setInt(3, obj.getServiceStatus());
                preparedStatement.setInt(4, obj.getServiceChargeId());
                preparedStatement.setInt(5, obj.getServiceTypeId());
                preparedStatement.setInt(6, obj.getServiceId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getServiceId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("Service's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("service");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(Service service)
        {
            
                try
                { 
                    
                            getRecordById("service_charge", service.getServiceChargeId().toString());
                            service.setServiceCharge(ServiceCharge.process(rs));                                       
                    
                            getRecordById("service_type", service.getServiceTypeId().toString());
                            service.setServiceType(ServiceType.process(rs));                                       
                    
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
        public void getRelatedObjects(Service service)
        {
            service.setUserServiceList(new UserServiceDaoImpl().findByColumn("ServiceId", service.getServiceId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(Service obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM service WHERE ServiceId=" + obj.getServiceId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Service's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getServiceId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM service WHERE ServiceId=" + id + ";");           
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
                updateQuery("DELETE FROM service;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Service's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM service WHERE " + Service.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Service's removeByColumn method error: " + ex.getMessage());
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
                        Service i = (Service) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getServiceId());
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
        
                    
        public void getRelatedUserServiceList(Service service)
        {           
            service.setUserServiceList(new UserServiceDaoImpl().findByColumn("ServiceId", service.getServiceId().toString(),null,null));
        }        
        
            
        
        
        public void getRelatedServiceCharge(Service service)
        {            
            try
            {                 
                getRecordById("ServiceCharge", service.getServiceChargeId().toString());
                service.setServiceCharge(ServiceCharge.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getServiceCharge error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
        
        public void getRelatedServiceType(Service service)
        {            
            try
            {                 
                getRecordById("ServiceType", service.getServiceTypeId().toString());
                service.setServiceType(ServiceType.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getServiceType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
          
        
                
        
        public void getRelatedServiceChargeWithInfo(Service service)
        {            
            service.setServiceCharge(new ServiceChargeDaoImpl().findWithInfo(service.getServiceChargeId()));
        }
        
        public void getRelatedServiceTypeWithInfo(Service service)
        {            
            service.setServiceType(new ServiceTypeDaoImpl().findWithInfo(service.getServiceTypeId()));
        }
          
        
    }

