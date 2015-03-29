






















































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
    
    public class TenantAttributeDaoImpl extends BasicConnection implements Serializable, TenantAttributeDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public TenantAttributeDaoImpl()
        {
            cachingEnabled = false;
        }

        public TenantAttributeDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class TenantAttributeCache
        {
            public static final ConcurrentLruCache<Integer, TenantAttribute> tenantAttributeCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (TenantAttribute i : findAll())
                {
                    getCache().put(i.getTenantAttributeId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, TenantAttribute> getCache()
        {
            return TenantAttributeCache.tenantAttributeCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, TenantAttribute> buildCache(ArrayList<TenantAttribute> tenantAttributeList)
        {        
            ConcurrentLruCache<Integer, TenantAttribute> cache = new ConcurrentLruCache<Integer, TenantAttribute>(tenantAttributeList.size() + 1000);
            for (TenantAttribute i : tenantAttributeList)
            {
                cache.put(i.getTenantAttributeId(), i);
            }
            return cache;
        }

        private static ArrayList<TenantAttribute> findAll()
        {
            ArrayList<TenantAttribute> tenantAttribute = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("tenantAttribute");
                while (rs.next())
                {
                    tenantAttribute.add(TenantAttribute.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("TenantAttribute object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return tenantAttribute;
        }
        
        @Override
        public TenantAttribute find(Integer id)
        {
            return findByColumn("TenantAttributeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public TenantAttribute findWithInfo(Integer id)
        {
            TenantAttribute tenantAttribute = findByColumn("TenantAttributeId", id.toString(), null, null).get(0);
            
            
                try
                {

                
                    getRecordById("Tenant", tenantAttribute.getTenantId().toString());
                    tenantAttribute.setTenant(Tenant.process(rs));               
                  

                }
                catch (SQLException ex)
                {
                        System.out.println("Object TenantAttribute method findWithInfo(Integer id) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            
            
            return tenantAttribute;
        }
        
        @Override
        public ArrayList<TenantAttribute> findAll(Integer limit, Integer offset)
        {
            ArrayList<TenantAttribute> tenantAttributeList = new ArrayList<TenantAttribute>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for TenantAttribute, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    tenantAttributeList = new ArrayList<TenantAttribute>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("tenant_attribute", limit, offset);
                    while (rs.next())
                    {
                        tenantAttributeList.add(TenantAttribute.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("TenantAttribute object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return tenantAttributeList;
         
        }
        
        @Override
        public ArrayList<TenantAttribute> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<TenantAttribute> tenantAttributeList = new ArrayList<TenantAttribute>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for TenantAttribute, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    tenantAttributeList = new ArrayList<TenantAttribute>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            TenantAttribute tenantAttribute = (TenantAttribute) e.getValue();

                            
                                getRecordById("Tenant", tenantAttribute.getTenantId().toString());
                                tenantAttribute.setTenant(Tenant.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object TenantAttribute method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                tenantAttributeList = new ArrayList<TenantAttribute>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("tenant_attribute", limit, offset);
                    while (rs.next())
                    {
                        tenantAttributeList.add(TenantAttribute.process(rs));
                    }

                    
                    
                        for (TenantAttribute tenantAttribute : tenantAttributeList)
                        {                        
                            
                                getRecordById("Tenant", tenantAttribute.getTenantId().toString());
                                tenantAttribute.setTenant(Tenant.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object TenantAttribute method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return tenantAttributeList;            
        }
        
        @Override
        public ArrayList<TenantAttribute> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<TenantAttribute> tenantAttributeList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for TenantAttribute(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            TenantAttribute i = (TenantAttribute) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                tenantAttributeList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            tenantAttributeList = null;
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
                    getRecordsByColumnWithLimitOrOffset("tenant_attribute", TenantAttribute.checkColumnName(columnName), columnValue, TenantAttribute.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        tenantAttributeList.add(TenantAttribute.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("TenantAttribute's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return tenantAttributeList;
        } 
    
        @Override
        public int add(TenantAttribute obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                TenantAttribute.checkColumnSize(obj.getAttributeKey(), 100);
                TenantAttribute.checkColumnSize(obj.getAttributeValue(), 255);
                
                  

                openConnection();
                prepareStatement("INSERT INTO tenant_attribute(AttributeKey,AttributeValue,TenantId) VALUES (?,?,?);");                    
                preparedStatement.setString(1, obj.getAttributeKey());
                preparedStatement.setString(2, obj.getAttributeValue());
                preparedStatement.setInt(3, obj.getTenantId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from tenant_attribute;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("TenantAttribute's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setTenantAttributeId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public TenantAttribute update(TenantAttribute obj)
        {
           try
            {   
                
                TenantAttribute.checkColumnSize(obj.getAttributeKey(), 100);
                TenantAttribute.checkColumnSize(obj.getAttributeValue(), 255);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE tenant_attribute SET AttributeKey=?,AttributeValue=?,TenantId=? WHERE TenantAttributeId=?;");                    
                preparedStatement.setString(1, obj.getAttributeKey());
                preparedStatement.setString(2, obj.getAttributeValue());
                preparedStatement.setInt(3, obj.getTenantId());
                preparedStatement.setInt(4, obj.getTenantAttributeId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getTenantAttributeId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("TenantAttribute's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("tenant_attribute");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(TenantAttribute tenant_attribute)
        {
            
                try
                { 
                    
                            getRecordById("Tenant", tenant_attribute.getTenantId().toString());
                            tenant_attribute.setTenant(Tenant.process(rs));                                       
                    
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
        public void getRelatedObjects(TenantAttribute tenant_attribute)
        {
             
        }
        
        @Override
        public boolean remove(TenantAttribute obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM tenant_attribute WHERE TenantAttributeId=" + obj.getTenantAttributeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("TenantAttribute's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getTenantAttributeId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM tenant_attribute WHERE TenantAttributeId=" + id + ";");           
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
                updateQuery("DELETE FROM tenant_attribute;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("TenantAttribute's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM tenant_attribute WHERE " + TenantAttribute.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("TenantAttribute's removeByColumn method error: " + ex.getMessage());
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
                        TenantAttribute i = (TenantAttribute) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getTenantAttributeId());
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
        
        
            
        
        
        public void getRelatedTenant(TenantAttribute tenant_attribute)
        {            
            try
            {                 
                getRecordById("Tenant", tenant_attribute.getTenantId().toString());
                tenant_attribute.setTenant(Tenant.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getTenant error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
          
        
                
        
        public void getRelatedTenantWithInfo(TenantAttribute tenant_attribute)
        {            
            tenant_attribute.setTenant(new TenantDaoImpl().findWithInfo(tenant_attribute.getTenantId()));
        }
          
        
    }

