






















































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
    
    public class DashboardDaoImpl extends BasicConnection implements Serializable, DashboardDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public DashboardDaoImpl()
        {
            cachingEnabled = false;
        }

        public DashboardDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class DashboardCache
        {
            public static final ConcurrentLruCache<Integer, Dashboard> dashboardCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (Dashboard i : findAll())
                {
                    getCache().put(i.getDashboardId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, Dashboard> getCache()
        {
            return DashboardCache.dashboardCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, Dashboard> buildCache(ArrayList<Dashboard> dashboardList)
        {        
            ConcurrentLruCache<Integer, Dashboard> cache = new ConcurrentLruCache<Integer, Dashboard>(dashboardList.size() + 1000);
            for (Dashboard i : dashboardList)
            {
                cache.put(i.getDashboardId(), i);
            }
            return cache;
        }

        private static ArrayList<Dashboard> findAll()
        {
            ArrayList<Dashboard> dashboard = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("dashboard");
                while (rs.next())
                {
                    dashboard.add(Dashboard.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Dashboard object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return dashboard;
        }
        
        @Override
        public Dashboard find(Integer id)
        {
            return findByColumn("DashboardId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Dashboard> findAll(Integer limit, Integer offset)
        {
            ArrayList<Dashboard> dashboardList = new ArrayList<Dashboard>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for Dashboard, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    dashboardList = new ArrayList<Dashboard>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("dashboard", limit, offset);
                    while (rs.next())
                    {
                        dashboardList.add(Dashboard.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Dashboard object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return dashboardList;
         
        }
        
        @Override
        public ArrayList<Dashboard> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Dashboard> dashboardList = new ArrayList<Dashboard>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for Dashboard, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    dashboardList = new ArrayList<Dashboard>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                dashboardList = new ArrayList<Dashboard>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("dashboard", limit, offset);
                    while (rs.next())
                    {
                        dashboardList.add(Dashboard.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object Dashboard method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return dashboardList;            
        }
        
        @Override
        public ArrayList<Dashboard> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Dashboard> dashboardList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for Dashboard(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            Dashboard i = (Dashboard) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                dashboardList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            dashboardList = null;
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
                    getRecordsByColumnWithLimitOrOffset("dashboard", Dashboard.checkColumnName(columnName), columnValue, Dashboard.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        dashboardList.add(Dashboard.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Dashboard's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return dashboardList;
        } 
    
        @Override
        public int add(Dashboard obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO dashboard(DashboardId,UserCount,BlogPostCount,ItemCount,OrderCount,SiteFileCount,ImageCount,BlogCount,CommentCount,PageCount,FormCount,SliderCount,ItemBrandCount,CategoryCount,ItemOptionCount,) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setInt(0, obj.getDashboardId());
                preparedStatement.setInt(1, obj.getUserCount());
                preparedStatement.setInt(2, obj.getBlogPostCount());
                preparedStatement.setInt(3, obj.getItemCount());
                preparedStatement.setInt(4, obj.getOrderCount());
                preparedStatement.setInt(5, obj.getSiteFileCount());
                preparedStatement.setInt(6, obj.getImageCount());
                preparedStatement.setInt(7, obj.getBlogCount());
                preparedStatement.setInt(8, obj.getCommentCount());
                preparedStatement.setInt(9, obj.getPageCount());
                preparedStatement.setInt(10, obj.getFormCount());
                preparedStatement.setInt(11, obj.getSliderCount());
                preparedStatement.setInt(12, obj.getItemBrandCount());
                preparedStatement.setInt(13, obj.getCategoryCount());
                preparedStatement.setInt(14, obj.getItemOptionCount());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from dashboard;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Dashboard's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setDashboardId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public Dashboard update(Dashboard obj)
        {
           try
            {   
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE dashboard SET com.busy.util.DatabaseColumn@572ad941=?,com.busy.util.DatabaseColumn@440cb121=?,com.busy.util.DatabaseColumn@7e682186=?,com.busy.util.DatabaseColumn@91f2e0=?,com.busy.util.DatabaseColumn@170c362=?,com.busy.util.DatabaseColumn@2054ffd4=?,com.busy.util.DatabaseColumn@1f8f9e49=?,com.busy.util.DatabaseColumn@100669aa=?,com.busy.util.DatabaseColumn@17eac31b=?,com.busy.util.DatabaseColumn@1047346d=?,com.busy.util.DatabaseColumn@73599dfa=?,com.busy.util.DatabaseColumn@50b1e102=?,com.busy.util.DatabaseColumn@11021214=?,com.busy.util.DatabaseColumn@38cfb805=? WHERE DashboardId=?;");                    
                preparedStatement.setInt(0, obj.getDashboardId());
                preparedStatement.setInt(1, obj.getUserCount());
                preparedStatement.setInt(2, obj.getBlogPostCount());
                preparedStatement.setInt(3, obj.getItemCount());
                preparedStatement.setInt(4, obj.getOrderCount());
                preparedStatement.setInt(5, obj.getSiteFileCount());
                preparedStatement.setInt(6, obj.getImageCount());
                preparedStatement.setInt(7, obj.getBlogCount());
                preparedStatement.setInt(8, obj.getCommentCount());
                preparedStatement.setInt(9, obj.getPageCount());
                preparedStatement.setInt(10, obj.getFormCount());
                preparedStatement.setInt(11, obj.getSliderCount());
                preparedStatement.setInt(12, obj.getItemBrandCount());
                preparedStatement.setInt(13, obj.getCategoryCount());
                preparedStatement.setInt(14, obj.getItemOptionCount());
                preparedStatement.setInt(15, obj.getDashboardId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getDashboardId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("Dashboard's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("dashboard");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(Dashboard dashboard)
        {
              
        }
        
        @Override
        public void getRelatedObjects(Dashboard dashboard)
        {
             
        }
        
        @Override
        public boolean remove(Dashboard obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM dashboard WHERE DashboardId=" + obj.getDashboardId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Dashboard's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getDashboardId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM dashboard WHERE DashboardId=" + id + ";");           
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
                updateQuery("DELETE FROM dashboard;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Dashboard's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM dashboard WHERE " + Dashboard.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Dashboard's removeByColumn method error: " + ex.getMessage());
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
                        Dashboard i = (Dashboard) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getDashboardId());
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
        
        
                             
    }

