






















































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
    
    public class ServiceTypeDaoImpl extends BasicConnection implements Serializable, ServiceTypeDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public ServiceTypeDaoImpl()
        {
            cachingEnabled = false;
        }

        public ServiceTypeDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class ServiceTypeCache
        {
            public static final ConcurrentLruCache<Integer, ServiceType> serviceTypeCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (ServiceType i : findAll())
                {
                    getCache().put(i.getServiceTypeId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, ServiceType> getCache()
        {
            return ServiceTypeCache.serviceTypeCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, ServiceType> buildCache(ArrayList<ServiceType> serviceTypeList)
        {        
            ConcurrentLruCache<Integer, ServiceType> cache = new ConcurrentLruCache<Integer, ServiceType>(serviceTypeList.size() + 1000);
            for (ServiceType i : serviceTypeList)
            {
                cache.put(i.getServiceTypeId(), i);
            }
            return cache;
        }

        private static ArrayList<ServiceType> findAll()
        {
            ArrayList<ServiceType> serviceType = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("serviceType");
                while (rs.next())
                {
                    serviceType.add(ServiceType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ServiceType object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return serviceType;
        }
        
        @Override
        public ServiceType find(Integer id)
        {
            return findByColumn("ServiceTypeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<ServiceType> findAll(Integer limit, Integer offset)
        {
            ArrayList<ServiceType> serviceTypeList = new ArrayList<ServiceType>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for ServiceType, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    serviceTypeList = new ArrayList<ServiceType>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("service_type", limit, offset);
                    while (rs.next())
                    {
                        serviceTypeList.add(ServiceType.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ServiceType object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return serviceTypeList;
         
        }
        
        @Override
        public ArrayList<ServiceType> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ServiceType> serviceTypeList = new ArrayList<ServiceType>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for ServiceType, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    serviceTypeList = new ArrayList<ServiceType>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                serviceTypeList = new ArrayList<ServiceType>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("service_type", limit, offset);
                    while (rs.next())
                    {
                        serviceTypeList.add(ServiceType.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object ServiceType method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return serviceTypeList;            
        }
        
        @Override
        public ArrayList<ServiceType> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ServiceType> serviceTypeList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for ServiceType(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            ServiceType i = (ServiceType) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                serviceTypeList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            serviceTypeList = null;
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
                    getRecordsByColumnWithLimitOrOffset("service_type", ServiceType.checkColumnName(columnName), columnValue, ServiceType.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        serviceTypeList.add(ServiceType.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ServiceType's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return serviceTypeList;
        } 
    
        @Override
        public int add(ServiceType obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                ServiceType.checkColumnSize(obj.getTypeName(), 100);
                ServiceType.checkColumnSize(obj.getDesciption(), 65535);
                  

                openConnection();
                prepareStatement("INSERT INTO service_type(ServiceTypeId,TypeName,Desciption,) VALUES (?,?);");                    
                preparedStatement.setInt(0, obj.getServiceTypeId());
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getDesciption());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from service_type;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ServiceType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setServiceTypeId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public ServiceType update(ServiceType obj)
        {
           try
            {   
                
                ServiceType.checkColumnSize(obj.getTypeName(), 100);
                ServiceType.checkColumnSize(obj.getDesciption(), 65535);
                                  
                openConnection();                           
                prepareStatement("UPDATE service_type SET com.busy.util.DatabaseColumn@68d22fb7=?,com.busy.util.DatabaseColumn@c0bf6d1=? WHERE ServiceTypeId=?;");                    
                preparedStatement.setInt(0, obj.getServiceTypeId());
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getDesciption());
                preparedStatement.setInt(3, obj.getServiceTypeId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getServiceTypeId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("ServiceType's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("service_type");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(ServiceType service_type)
        {
              
        }
        
        @Override
        public void getRelatedObjects(ServiceType service_type)
        {
            service_type.setServiceList(new ServiceDaoImpl().findByColumn("ServiceTypeId", service_type.getServiceTypeId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(ServiceType obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM service_type WHERE ServiceTypeId=" + obj.getServiceTypeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ServiceType's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getServiceTypeId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM service_type WHERE ServiceTypeId=" + id + ";");           
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
                updateQuery("DELETE FROM service_type;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ServiceType's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM service_type WHERE " + ServiceType.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ServiceType's removeByColumn method error: " + ex.getMessage());
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
                        ServiceType i = (ServiceType) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getServiceTypeId());
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
        
                    
        public void getRelatedServiceList(ServiceType service_type)
        {           
            service_type.setServiceList(new ServiceDaoImpl().findByColumn("ServiceTypeId", service_type.getServiceTypeId().toString(),null,null));
        }        
        
                             
    }

