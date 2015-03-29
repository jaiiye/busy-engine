






















































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
    
    public class TenantDaoImpl extends BasicConnection implements Serializable, TenantDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public TenantDaoImpl()
        {
            cachingEnabled = false;
        }

        public TenantDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class TenantCache
        {
            public static final ConcurrentLruCache<Integer, Tenant> tenantCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (Tenant i : findAll())
                {
                    getCache().put(i.getTenantId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, Tenant> getCache()
        {
            return TenantCache.tenantCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, Tenant> buildCache(ArrayList<Tenant> tenantList)
        {        
            ConcurrentLruCache<Integer, Tenant> cache = new ConcurrentLruCache<Integer, Tenant>(tenantList.size() + 1000);
            for (Tenant i : tenantList)
            {
                cache.put(i.getTenantId(), i);
            }
            return cache;
        }

        private static ArrayList<Tenant> findAll()
        {
            ArrayList<Tenant> tenant = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("tenant");
                while (rs.next())
                {
                    tenant.add(Tenant.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Tenant object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return tenant;
        }
        
        @Override
        public Tenant find(Integer id)
        {
            return findByColumn("TenantId", id.toString(), null, null).get(0);
        }
        
        @Override
        public Tenant findWithInfo(Integer id)
        {
            Tenant tenant = findByColumn("TenantId", id.toString(), null, null).get(0);
            
            
                try
                {

                
                    getRecordById("Dashboard", tenant.getDashboardId().toString());
                    tenant.setDashboard(Dashboard.process(rs));               
                  

                }
                catch (SQLException ex)
                {
                        System.out.println("Object Tenant method findWithInfo(Integer id) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            
            
            return tenant;
        }
        
        @Override
        public ArrayList<Tenant> findAll(Integer limit, Integer offset)
        {
            ArrayList<Tenant> tenantList = new ArrayList<Tenant>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for Tenant, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    tenantList = new ArrayList<Tenant>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("tenant", limit, offset);
                    while (rs.next())
                    {
                        tenantList.add(Tenant.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Tenant object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return tenantList;
         
        }
        
        @Override
        public ArrayList<Tenant> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Tenant> tenantList = new ArrayList<Tenant>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for Tenant, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    tenantList = new ArrayList<Tenant>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            Tenant tenant = (Tenant) e.getValue();

                            
                                getRecordById("Dashboard", tenant.getDashboardId().toString());
                                tenant.setDashboard(Dashboard.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object Tenant method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                tenantList = new ArrayList<Tenant>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("tenant", limit, offset);
                    while (rs.next())
                    {
                        tenantList.add(Tenant.process(rs));
                    }

                    
                    
                        for (Tenant tenant : tenantList)
                        {                        
                            
                                getRecordById("Dashboard", tenant.getDashboardId().toString());
                                tenant.setDashboard(Dashboard.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object Tenant method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return tenantList;            
        }
        
        @Override
        public ArrayList<Tenant> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Tenant> tenantList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for Tenant(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            Tenant i = (Tenant) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                tenantList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            tenantList = null;
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
                    getRecordsByColumnWithLimitOrOffset("tenant", Tenant.checkColumnName(columnName), columnValue, Tenant.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        tenantList.add(Tenant.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Tenant's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return tenantList;
        } 
    
        @Override
        public int add(Tenant obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                Tenant.checkColumnSize(obj.getName(), 255);
                Tenant.checkColumnSize(obj.getLogo(), 255);
                
                  

                openConnection();
                prepareStatement("INSERT INTO tenant(Name,Logo,DashboardId) VALUES (?,?,?);");                    
                preparedStatement.setString(1, obj.getName());
                preparedStatement.setString(2, obj.getLogo());
                preparedStatement.setInt(3, obj.getDashboardId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from tenant;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Tenant's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setTenantId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public Tenant update(Tenant obj)
        {
           try
            {   
                
                Tenant.checkColumnSize(obj.getName(), 255);
                Tenant.checkColumnSize(obj.getLogo(), 255);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE tenant SET Name=?,Logo=?,DashboardId=? WHERE TenantId=?;");                    
                preparedStatement.setString(1, obj.getName());
                preparedStatement.setString(2, obj.getLogo());
                preparedStatement.setInt(3, obj.getDashboardId());
                preparedStatement.setInt(4, obj.getTenantId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getTenantId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("Tenant's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("tenant");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(Tenant tenant)
        {
            
                try
                { 
                    
                            getRecordById("Dashboard", tenant.getDashboardId().toString());
                            tenant.setDashboard(Dashboard.process(rs));                                       
                    
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
        public void getRelatedObjects(Tenant tenant)
        {
            tenant.setSiteList(new SiteDaoImpl().findByColumn("TenantId", tenant.getTenantId().toString(),null,null));
tenant.setTenantAttributeList(new TenantAttributeDaoImpl().findByColumn("TenantId", tenant.getTenantId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(Tenant obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM tenant WHERE TenantId=" + obj.getTenantId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Tenant's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getTenantId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM tenant WHERE TenantId=" + id + ";");           
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
                updateQuery("DELETE FROM tenant;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Tenant's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM tenant WHERE " + Tenant.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Tenant's removeByColumn method error: " + ex.getMessage());
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
                        Tenant i = (Tenant) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getTenantId());
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
        
                    
        public void getRelatedSiteList(Tenant tenant)
        {           
            tenant.setSiteList(new SiteDaoImpl().findByColumn("TenantId", tenant.getTenantId().toString(),null,null));
        }        
                    
        public void getRelatedTenantAttributeList(Tenant tenant)
        {           
            tenant.setTenantAttributeList(new TenantAttributeDaoImpl().findByColumn("TenantId", tenant.getTenantId().toString(),null,null));
        }        
        
            
        
        
        public void getRelatedDashboard(Tenant tenant)
        {            
            try
            {                 
                getRecordById("Dashboard", tenant.getDashboardId().toString());
                tenant.setDashboard(Dashboard.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getDashboard error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
          
        
                
        
        public void getRelatedDashboardWithInfo(Tenant tenant)
        {            
            tenant.setDashboard(new DashboardDaoImpl().findWithInfo(tenant.getDashboardId()));
        }
          
        
    }

