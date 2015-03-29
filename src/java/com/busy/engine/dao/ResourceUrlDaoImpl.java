






















































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
    
    public class ResourceUrlDaoImpl extends BasicConnection implements Serializable, ResourceUrlDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public ResourceUrlDaoImpl()
        {
            cachingEnabled = false;
        }

        public ResourceUrlDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class ResourceUrlCache
        {
            public static final ConcurrentLruCache<Integer, ResourceUrl> resourceUrlCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (ResourceUrl i : findAll())
                {
                    getCache().put(i.getResourceUrlId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, ResourceUrl> getCache()
        {
            return ResourceUrlCache.resourceUrlCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, ResourceUrl> buildCache(ArrayList<ResourceUrl> resourceUrlList)
        {        
            ConcurrentLruCache<Integer, ResourceUrl> cache = new ConcurrentLruCache<Integer, ResourceUrl>(resourceUrlList.size() + 1000);
            for (ResourceUrl i : resourceUrlList)
            {
                cache.put(i.getResourceUrlId(), i);
            }
            return cache;
        }

        private static ArrayList<ResourceUrl> findAll()
        {
            ArrayList<ResourceUrl> resourceUrl = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("resourceUrl");
                while (rs.next())
                {
                    resourceUrl.add(ResourceUrl.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ResourceUrl object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return resourceUrl;
        }
        
        @Override
        public ResourceUrl find(Integer id)
        {
            return findByColumn("ResourceUrlId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ResourceUrl findWithInfo(Integer id)
        {
            ResourceUrl resourceUrl = findByColumn("ResourceUrlId", id.toString(), null, null).get(0);
            
            
                try
                {

                
                    getRecordById("Template", resourceUrl.getTemplateId().toString());
                    resourceUrl.setTemplate(Template.process(rs));               
                
                    getRecordById("ResourceType", resourceUrl.getResourceTypeId().toString());
                    resourceUrl.setResourceType(ResourceType.process(rs));               
                  

                }
                catch (SQLException ex)
                {
                        System.out.println("Object ResourceUrl method findWithInfo(Integer id) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            
            
            return resourceUrl;
        }
        
        @Override
        public ArrayList<ResourceUrl> findAll(Integer limit, Integer offset)
        {
            ArrayList<ResourceUrl> resourceUrlList = new ArrayList<ResourceUrl>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for ResourceUrl, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    resourceUrlList = new ArrayList<ResourceUrl>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("resource_url", limit, offset);
                    while (rs.next())
                    {
                        resourceUrlList.add(ResourceUrl.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ResourceUrl object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return resourceUrlList;
         
        }
        
        @Override
        public ArrayList<ResourceUrl> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ResourceUrl> resourceUrlList = new ArrayList<ResourceUrl>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for ResourceUrl, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    resourceUrlList = new ArrayList<ResourceUrl>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            ResourceUrl resourceUrl = (ResourceUrl) e.getValue();

                            
                                getRecordById("Template", resourceUrl.getTemplateId().toString());
                                resourceUrl.setTemplate(Template.process(rs));               
                            
                                getRecordById("ResourceType", resourceUrl.getResourceTypeId().toString());
                                resourceUrl.setResourceType(ResourceType.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object ResourceUrl method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                resourceUrlList = new ArrayList<ResourceUrl>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("resource_url", limit, offset);
                    while (rs.next())
                    {
                        resourceUrlList.add(ResourceUrl.process(rs));
                    }

                    
                    
                        for (ResourceUrl resourceUrl : resourceUrlList)
                        {                        
                            
                                getRecordById("Template", resourceUrl.getTemplateId().toString());
                                resourceUrl.setTemplate(Template.process(rs));               
                            
                                getRecordById("ResourceType", resourceUrl.getResourceTypeId().toString());
                                resourceUrl.setResourceType(ResourceType.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object ResourceUrl method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return resourceUrlList;            
        }
        
        @Override
        public ArrayList<ResourceUrl> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ResourceUrl> resourceUrlList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for ResourceUrl(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            ResourceUrl i = (ResourceUrl) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                resourceUrlList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            resourceUrlList = null;
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
                    getRecordsByColumnWithLimitOrOffset("resource_url", ResourceUrl.checkColumnName(columnName), columnValue, ResourceUrl.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        resourceUrlList.add(ResourceUrl.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ResourceUrl's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return resourceUrlList;
        } 
    
        @Override
        public int add(ResourceUrl obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                ResourceUrl.checkColumnSize(obj.getUrl(), 255);
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO resource_url(Url,TemplateId,ResourceTypeId) VALUES (?,?,?);");                    
                preparedStatement.setString(1, obj.getUrl());
                preparedStatement.setInt(2, obj.getTemplateId());
                preparedStatement.setInt(3, obj.getResourceTypeId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from resource_url;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ResourceUrl's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setResourceUrlId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public ResourceUrl update(ResourceUrl obj)
        {
           try
            {   
                
                ResourceUrl.checkColumnSize(obj.getUrl(), 255);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE resource_url SET Url=?,TemplateId=?,ResourceTypeId=? WHERE ResourceUrlId=?;");                    
                preparedStatement.setString(1, obj.getUrl());
                preparedStatement.setInt(2, obj.getTemplateId());
                preparedStatement.setInt(3, obj.getResourceTypeId());
                preparedStatement.setInt(4, obj.getResourceUrlId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getResourceUrlId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("ResourceUrl's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("resource_url");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(ResourceUrl resource_url)
        {
            
                try
                { 
                    
                            getRecordById("Template", resource_url.getTemplateId().toString());
                            resource_url.setTemplate(Template.process(rs));                                       
                    
                            getRecordById("ResourceType", resource_url.getResourceTypeId().toString());
                            resource_url.setResourceType(ResourceType.process(rs));                                       
                    
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
        public void getRelatedObjects(ResourceUrl resource_url)
        {
             
        }
        
        @Override
        public boolean remove(ResourceUrl obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM resource_url WHERE ResourceUrlId=" + obj.getResourceUrlId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ResourceUrl's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getResourceUrlId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM resource_url WHERE ResourceUrlId=" + id + ";");           
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
                updateQuery("DELETE FROM resource_url;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ResourceUrl's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM resource_url WHERE " + ResourceUrl.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ResourceUrl's removeByColumn method error: " + ex.getMessage());
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
                        ResourceUrl i = (ResourceUrl) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getResourceUrlId());
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
        
        
            
        
        
        public void getRelatedTemplate(ResourceUrl resource_url)
        {            
            try
            {                 
                getRecordById("Template", resource_url.getTemplateId().toString());
                resource_url.setTemplate(Template.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getTemplate error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
        
        public void getRelatedResourceType(ResourceUrl resource_url)
        {            
            try
            {                 
                getRecordById("ResourceType", resource_url.getResourceTypeId().toString());
                resource_url.setResourceType(ResourceType.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getResourceType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
          
        
                
        
        public void getRelatedTemplateWithInfo(ResourceUrl resource_url)
        {            
            resource_url.setTemplate(new TemplateDaoImpl().findWithInfo(resource_url.getTemplateId()));
        }
        
        public void getRelatedResourceTypeWithInfo(ResourceUrl resource_url)
        {            
            resource_url.setResourceType(new ResourceTypeDaoImpl().findWithInfo(resource_url.getResourceTypeId()));
        }
          
        
    }

