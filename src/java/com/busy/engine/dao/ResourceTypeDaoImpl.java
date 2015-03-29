






















































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
    
    public class ResourceTypeDaoImpl extends BasicConnection implements Serializable, ResourceTypeDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public ResourceTypeDaoImpl()
        {
            cachingEnabled = false;
        }

        public ResourceTypeDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class ResourceTypeCache
        {
            public static final ConcurrentLruCache<Integer, ResourceType> resourceTypeCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (ResourceType i : findAll())
                {
                    getCache().put(i.getResourceTypeId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, ResourceType> getCache()
        {
            return ResourceTypeCache.resourceTypeCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, ResourceType> buildCache(ArrayList<ResourceType> resourceTypeList)
        {        
            ConcurrentLruCache<Integer, ResourceType> cache = new ConcurrentLruCache<Integer, ResourceType>(resourceTypeList.size() + 1000);
            for (ResourceType i : resourceTypeList)
            {
                cache.put(i.getResourceTypeId(), i);
            }
            return cache;
        }

        private static ArrayList<ResourceType> findAll()
        {
            ArrayList<ResourceType> resourceType = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("resourceType");
                while (rs.next())
                {
                    resourceType.add(ResourceType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ResourceType object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return resourceType;
        }
        
        @Override
        public ResourceType find(Integer id)
        {
            return findByColumn("ResourceTypeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ResourceType findWithInfo(Integer id)
        {
            ResourceType resourceType = findByColumn("ResourceTypeId", id.toString(), null, null).get(0);
            
            
            
            return resourceType;
        }
        
        @Override
        public ArrayList<ResourceType> findAll(Integer limit, Integer offset)
        {
            ArrayList<ResourceType> resourceTypeList = new ArrayList<ResourceType>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for ResourceType, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    resourceTypeList = new ArrayList<ResourceType>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("resource_type", limit, offset);
                    while (rs.next())
                    {
                        resourceTypeList.add(ResourceType.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ResourceType object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return resourceTypeList;
         
        }
        
        @Override
        public ArrayList<ResourceType> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ResourceType> resourceTypeList = new ArrayList<ResourceType>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for ResourceType, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    resourceTypeList = new ArrayList<ResourceType>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                resourceTypeList = new ArrayList<ResourceType>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("resource_type", limit, offset);
                    while (rs.next())
                    {
                        resourceTypeList.add(ResourceType.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object ResourceType method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return resourceTypeList;            
        }
        
        @Override
        public ArrayList<ResourceType> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ResourceType> resourceTypeList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for ResourceType(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            ResourceType i = (ResourceType) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                resourceTypeList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            resourceTypeList = null;
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
                    getRecordsByColumnWithLimitOrOffset("resource_type", ResourceType.checkColumnName(columnName), columnValue, ResourceType.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        resourceTypeList.add(ResourceType.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ResourceType's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return resourceTypeList;
        } 
    
        @Override
        public int add(ResourceType obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                ResourceType.checkColumnSize(obj.getTypeName(), 45);
                ResourceType.checkColumnSize(obj.getTypeValue(), 150);
                  

                openConnection();
                prepareStatement("INSERT INTO resource_type(TypeName,TypeValue) VALUES (?,?);");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getTypeValue());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from resource_type;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ResourceType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setResourceTypeId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public ResourceType update(ResourceType obj)
        {
           try
            {   
                
                ResourceType.checkColumnSize(obj.getTypeName(), 45);
                ResourceType.checkColumnSize(obj.getTypeValue(), 150);
                                  
                openConnection();                           
                prepareStatement("UPDATE resource_type SET TypeName=?,TypeValue=? WHERE ResourceTypeId=?;");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getTypeValue());
                preparedStatement.setInt(3, obj.getResourceTypeId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getResourceTypeId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("ResourceType's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("resource_type");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(ResourceType resource_type)
        {
              
        }
        
        @Override
        public void getRelatedObjects(ResourceType resource_type)
        {
            resource_type.setResourceUrlList(new ResourceUrlDaoImpl().findByColumn("ResourceTypeId", resource_type.getResourceTypeId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(ResourceType obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM resource_type WHERE ResourceTypeId=" + obj.getResourceTypeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ResourceType's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getResourceTypeId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM resource_type WHERE ResourceTypeId=" + id + ";");           
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
                updateQuery("DELETE FROM resource_type;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ResourceType's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM resource_type WHERE " + ResourceType.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ResourceType's removeByColumn method error: " + ex.getMessage());
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
                        ResourceType i = (ResourceType) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getResourceTypeId());
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
        
                    
        public void getRelatedResourceUrlList(ResourceType resource_type)
        {           
            resource_type.setResourceUrlList(new ResourceUrlDaoImpl().findByColumn("ResourceTypeId", resource_type.getResourceTypeId().toString(),null,null));
        }        
        
            
        
          
        
                
          
        
    }

