
























































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
    
    public class SitePageDaoImpl extends BasicConnection implements Serializable, SitePageDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public SitePageDaoImpl()
        {
            cachingEnabled = false;
        }

        public SitePageDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class SitePageCache
        {
            public static final ConcurrentLruCache<Integer, SitePage> sitePageCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (SitePage i : findAll())
                {
                    getCache().put(i.getSitePageId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, SitePage> getCache()
        {
            return SitePageCache.sitePageCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, SitePage> buildCache(ArrayList<SitePage> sitePageList)
        {        
            ConcurrentLruCache<Integer, SitePage> cache = new ConcurrentLruCache<Integer, SitePage>(sitePageList.size() + 1000);
            for (SitePage i : sitePageList)
            {
                cache.put(i.getSitePageId(), i);
            }
            return cache;
        }

        private static ArrayList<SitePage> findAll()
        {
            ArrayList<SitePage> sitePage = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("sitePage");
                while (rs.next())
                {
                    sitePage.add(SitePage.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("SitePage object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return sitePage;
        }
        
        @Override
        public SitePage find(Integer id)
        {
            return findByColumn("SitePageId", id.toString(), null, null).get(0);
        }
        
        @Override
        public SitePage findWithInfo(Integer id)
        {
            SitePage sitePage = findByColumn("SitePageId", id.toString(), null, null).get(0);
            
            
                try
                {

                
                    getRecordById("site", sitePage.getSiteId().toString());
                    sitePage.setSite(Site.process(rs));               
                
                    getRecordById("page", sitePage.getPageId().toString());
                    sitePage.setPage(Page.process(rs));               
                  

                }
                catch (SQLException ex)
                {
                        System.out.println("Object SitePage method findWithInfo(Integer id) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            
            
            return sitePage;
        }
        
        @Override
        public ArrayList<SitePage> findAll(Integer limit, Integer offset)
        {
            ArrayList<SitePage> sitePageList = new ArrayList<SitePage>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for SitePage, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    sitePageList = new ArrayList<SitePage>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("site_page", limit, offset);
                    while (rs.next())
                    {
                        sitePageList.add(SitePage.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("SitePage object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return sitePageList;
         
        }
        
        @Override
        public ArrayList<SitePage> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<SitePage> sitePageList = new ArrayList<SitePage>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for SitePage, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    sitePageList = new ArrayList<SitePage>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            SitePage sitePage = (SitePage) e.getValue();

                            
                                getRecordById("site", sitePage.getSiteId().toString());
                                sitePage.setSite(Site.process(rs));               
                            
                                getRecordById("page", sitePage.getPageId().toString());
                                sitePage.setPage(Page.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object SitePage method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                sitePageList = new ArrayList<SitePage>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("site_page", limit, offset);
                    while (rs.next())
                    {
                        sitePageList.add(SitePage.process(rs));
                    }

                    
                    
                        for (SitePage sitePage : sitePageList)
                        {                        
                            
                                getRecordById("site", sitePage.getSiteId().toString());
                                sitePage.setSite(Site.process(rs));               
                            
                                getRecordById("page", sitePage.getPageId().toString());
                                sitePage.setPage(Page.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object SitePage method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return sitePageList;            
        }
        
        @Override
        public ArrayList<SitePage> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<SitePage> sitePageList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for SitePage(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            SitePage i = (SitePage) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                sitePageList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            sitePageList = null;
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
                    getRecordsByColumnWithLimitOrOffset("site_page", SitePage.checkColumnName(columnName), columnValue, SitePage.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        sitePageList.add(SitePage.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("SitePage's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return sitePageList;
        } 
        
        @Override
        public ArrayList<SitePage> findByColumns(Column... columns)
        {
            ArrayList<SitePage> sitePageList = new ArrayList<>();

            try
            {
                //make sure the correct isNumeric values are set for columns
                for(Column c : columns) 
                {
                    c.setNumeric(SitePage.isColumnNumeric(c.getColumnName()));                
                }

                getAllRecordsByColumns("site_page", columns);
                while (rs.next())
                {
                    sitePageList.add(SitePage.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("SitePage's method findByColumns(Column... columns) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }

            return sitePageList;
        }
    
        @Override
        public int add(SitePage obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO site_page(SiteId,PageId) VALUES (?,?);");                    
                preparedStatement.setInt(1, obj.getSiteId());
                preparedStatement.setInt(2, obj.getPageId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site_page;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SitePage's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setSitePageId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public SitePage update(SitePage obj)
        {
           try
            {   
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE site_page SET SiteId=?,PageId=? WHERE SitePageId=?;");                    
                preparedStatement.setInt(1, obj.getSiteId());
                preparedStatement.setInt(2, obj.getPageId());
                preparedStatement.setInt(3, obj.getSitePageId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getSitePageId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("SitePage's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("site_page");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(SitePage site_page)
        {
            
                try
                { 
                    
                            getRecordById("site", site_page.getSiteId().toString());
                            site_page.setSite(Site.process(rs));                                       
                    
                            getRecordById("page", site_page.getPageId().toString());
                            site_page.setPage(Page.process(rs));                                       
                    
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
        public void getRelatedObjects(SitePage site_page)
        {
             
        }
        
        @Override
        public boolean remove(SitePage obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM site_page WHERE SitePageId=" + obj.getSitePageId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SitePage's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getSitePageId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM site_page WHERE SitePageId=" + id + ";");           
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
                updateQuery("DELETE FROM site_page;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SitePage's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_page WHERE " + SitePage.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("SitePage's removeByColumn method error: " + ex.getMessage());
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
                        SitePage i = (SitePage) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getSitePageId());
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
        
        
            
        
        
        public void getRelatedSite(SitePage site_page)
        {            
            try
            {                 
                getRecordById("Site", site_page.getSiteId().toString());
                site_page.setSite(Site.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getSite error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
        
        public void getRelatedPage(SitePage site_page)
        {            
            try
            {                 
                getRecordById("Page", site_page.getPageId().toString());
                site_page.setPage(Page.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getPage error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
          
        
                
        
        public void getRelatedSiteWithInfo(SitePage site_page)
        {            
            site_page.setSite(new SiteDaoImpl().findWithInfo(site_page.getSiteId()));
        }
        
        public void getRelatedPageWithInfo(SitePage site_page)
        {            
            site_page.setPage(new PageDaoImpl().findWithInfo(site_page.getPageId()));
        }
          
        
    }

