






















































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
    
    public class SiteDaoImpl extends BasicConnection implements Serializable, SiteDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public SiteDaoImpl()
        {
            cachingEnabled = false;
        }

        public SiteDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class SiteCache
        {
            public static final ConcurrentLruCache<Integer, Site> siteCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (Site i : findAll())
                {
                    getCache().put(i.getSiteId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, Site> getCache()
        {
            return SiteCache.siteCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, Site> buildCache(ArrayList<Site> siteList)
        {        
            ConcurrentLruCache<Integer, Site> cache = new ConcurrentLruCache<Integer, Site>(siteList.size() + 1000);
            for (Site i : siteList)
            {
                cache.put(i.getSiteId(), i);
            }
            return cache;
        }

        private static ArrayList<Site> findAll()
        {
            ArrayList<Site> site = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("site");
                while (rs.next())
                {
                    site.add(Site.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Site object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return site;
        }
        
        @Override
        public Site find(Integer id)
        {
            return findByColumn("SiteId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Site> findAll(Integer limit, Integer offset)
        {
            ArrayList<Site> siteList = new ArrayList<Site>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for Site, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    siteList = new ArrayList<Site>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("site", limit, offset);
                    while (rs.next())
                    {
                        siteList.add(Site.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Site object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return siteList;
         
        }
        
        @Override
        public ArrayList<Site> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Site> siteList = new ArrayList<Site>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for Site, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    siteList = new ArrayList<Site>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            Site site = (Site) e.getValue();

                            
                                getRecordById("Template", site.getTemplateId().toString());
                                site.setTemplate(Template.process(rs));               
                            
                                getRecordById("SiteEmail", site.getSiteEmailId().toString());
                                site.setSiteEmail(SiteEmail.process(rs));               
                            
                                getRecordById("Dashboard", site.getDashboardId().toString());
                                site.setDashboard(Dashboard.process(rs));               
                            
                                getRecordById("Tenant", site.getTenantId().toString());
                                site.setTenant(Tenant.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object Site method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                siteList = new ArrayList<Site>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("site", limit, offset);
                    while (rs.next())
                    {
                        siteList.add(Site.process(rs));
                    }

                    
                    
                        for (Site site : siteList)
                        {                        
                            
                                getRecordById("Template", site.getTemplateId().toString());
                                site.setTemplate(Template.process(rs));               
                            
                                getRecordById("SiteEmail", site.getSiteEmailId().toString());
                                site.setSiteEmail(SiteEmail.process(rs));               
                            
                                getRecordById("Dashboard", site.getDashboardId().toString());
                                site.setDashboard(Dashboard.process(rs));               
                            
                                getRecordById("Tenant", site.getTenantId().toString());
                                site.setTenant(Tenant.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object Site method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return siteList;            
        }
        
        @Override
        public ArrayList<Site> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Site> siteList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for Site(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            Site i = (Site) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                siteList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            siteList = null;
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
                    getRecordsByColumnWithLimitOrOffset("site", Site.checkColumnName(columnName), columnValue, Site.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        siteList.add(Site.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Site's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return siteList;
        } 
    
        @Override
        public int add(Site obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                Site.checkColumnSize(obj.getSiteName(), 100);
                Site.checkColumnSize(obj.getDomain(), 255);
                
                Site.checkColumnSize(obj.getUrl(), 255);
                Site.checkColumnSize(obj.getLogoTitle(), 100);
                Site.checkColumnSize(obj.getLogoImageUrl(), 255);
                
                
                Site.checkColumnSize(obj.getLocale(), 100);
                
                
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO site(SiteId,SiteName,Domain,Mode,Url,LogoTitle,LogoImageUrl,UseAsStore,SiteStatus,Locale,TemplateId,SiteEmailId,DashboardId,TenantId,) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setInt(0, obj.getSiteId());
                preparedStatement.setString(1, obj.getSiteName());
                preparedStatement.setString(2, obj.getDomain());
                preparedStatement.setInt(3, obj.getMode());
                preparedStatement.setString(4, obj.getUrl());
                preparedStatement.setString(5, obj.getLogoTitle());
                preparedStatement.setString(6, obj.getLogoImageUrl());
                preparedStatement.setInt(7, obj.getUseAsStore());
                preparedStatement.setInt(8, obj.getSiteStatus());
                preparedStatement.setString(9, obj.getLocale());
                preparedStatement.setInt(10, obj.getTemplateId());
                preparedStatement.setInt(11, obj.getSiteEmailId());
                preparedStatement.setInt(12, obj.getDashboardId());
                preparedStatement.setInt(13, obj.getTenantId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Site's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setSiteId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public Site update(Site obj)
        {
           try
            {   
                
                Site.checkColumnSize(obj.getSiteName(), 100);
                Site.checkColumnSize(obj.getDomain(), 255);
                
                Site.checkColumnSize(obj.getUrl(), 255);
                Site.checkColumnSize(obj.getLogoTitle(), 100);
                Site.checkColumnSize(obj.getLogoImageUrl(), 255);
                
                
                Site.checkColumnSize(obj.getLocale(), 100);
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE site SET com.busy.util.DatabaseColumn@413c67c9=?,com.busy.util.DatabaseColumn@38f28135=?,com.busy.util.DatabaseColumn@2d90b817=?,com.busy.util.DatabaseColumn@797ec322=?,com.busy.util.DatabaseColumn@5b5ee1eb=?,com.busy.util.DatabaseColumn@7789389c=?,com.busy.util.DatabaseColumn@5ff1667a=?,com.busy.util.DatabaseColumn@5019d975=?,com.busy.util.DatabaseColumn@7ce42b24=?,com.busy.util.DatabaseColumn@4297b1fc=?,com.busy.util.DatabaseColumn@1681cd62=?,com.busy.util.DatabaseColumn@84d7cb6=?,com.busy.util.DatabaseColumn@55addb90=? WHERE SiteId=?;");                    
                preparedStatement.setInt(0, obj.getSiteId());
                preparedStatement.setString(1, obj.getSiteName());
                preparedStatement.setString(2, obj.getDomain());
                preparedStatement.setInt(3, obj.getMode());
                preparedStatement.setString(4, obj.getUrl());
                preparedStatement.setString(5, obj.getLogoTitle());
                preparedStatement.setString(6, obj.getLogoImageUrl());
                preparedStatement.setInt(7, obj.getUseAsStore());
                preparedStatement.setInt(8, obj.getSiteStatus());
                preparedStatement.setString(9, obj.getLocale());
                preparedStatement.setInt(10, obj.getTemplateId());
                preparedStatement.setInt(11, obj.getSiteEmailId());
                preparedStatement.setInt(12, obj.getDashboardId());
                preparedStatement.setInt(13, obj.getTenantId());
                preparedStatement.setInt(14, obj.getSiteId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getSiteId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("Site's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("site");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(Site site)
        {
            
                try
                { 
                    
                            getRecordById("Template", site.getTemplateId().toString());
                            site.setTemplate(Template.process(rs));                                       
                    
                            getRecordById("SiteEmail", site.getSiteEmailId().toString());
                            site.setSiteEmail(SiteEmail.process(rs));                                       
                    
                            getRecordById("Dashboard", site.getDashboardId().toString());
                            site.setDashboard(Dashboard.process(rs));                                       
                    
                            getRecordById("Tenant", site.getTenantId().toString());
                            site.setTenant(Tenant.process(rs));                                       
                    
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
        public void getRelatedObjects(Site site)
        {
            site.setSiteAttributeList(new SiteAttributeDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
site.setSiteFolderList(new SiteFolderDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
site.setSiteImageList(new SiteImageDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
site.setSiteItemList(new SiteItemDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
site.setSiteLanguageList(new SiteLanguageDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
site.setSitePageList(new SitePageDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
site.setUserGroupList(new UserGroupDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(Site obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM site WHERE SiteId=" + obj.getSiteId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Site's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getSiteId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM site WHERE SiteId=" + id + ";");           
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
                updateQuery("DELETE FROM site;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Site's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site WHERE " + Site.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Site's removeByColumn method error: " + ex.getMessage());
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
                        Site i = (Site) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getSiteId());
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
        
                    
        public void getRelatedSiteAttributeList(Site site)
        {           
            site.setSiteAttributeList(new SiteAttributeDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
        }        
                    
        public void getRelatedSiteFolderList(Site site)
        {           
            site.setSiteFolderList(new SiteFolderDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
        }        
                    
        public void getRelatedSiteImageList(Site site)
        {           
            site.setSiteImageList(new SiteImageDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
        }        
                    
        public void getRelatedSiteItemList(Site site)
        {           
            site.setSiteItemList(new SiteItemDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
        }        
                    
        public void getRelatedSiteLanguageList(Site site)
        {           
            site.setSiteLanguageList(new SiteLanguageDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
        }        
                    
        public void getRelatedSitePageList(Site site)
        {           
            site.setSitePageList(new SitePageDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
        }        
                    
        public void getRelatedUserGroupList(Site site)
        {           
            site.setUserGroupList(new UserGroupDaoImpl().findByColumn("SiteId", site.getSiteId().toString(),null,null));
        }        
        
                             
    }

