






















































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
    
    public class SiteFolderDaoImpl extends BasicConnection implements Serializable, SiteFolderDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public SiteFolderDaoImpl()
        {
            cachingEnabled = false;
        }

        public SiteFolderDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class SiteFolderCache
        {
            public static final ConcurrentLruCache<Integer, SiteFolder> siteFolderCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (SiteFolder i : findAll())
                {
                    getCache().put(i.getSiteFolderId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, SiteFolder> getCache()
        {
            return SiteFolderCache.siteFolderCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, SiteFolder> buildCache(ArrayList<SiteFolder> siteFolderList)
        {        
            ConcurrentLruCache<Integer, SiteFolder> cache = new ConcurrentLruCache<Integer, SiteFolder>(siteFolderList.size() + 1000);
            for (SiteFolder i : siteFolderList)
            {
                cache.put(i.getSiteFolderId(), i);
            }
            return cache;
        }

        private static ArrayList<SiteFolder> findAll()
        {
            ArrayList<SiteFolder> siteFolder = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("siteFolder");
                while (rs.next())
                {
                    siteFolder.add(SiteFolder.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("SiteFolder object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return siteFolder;
        }
        
        @Override
        public SiteFolder find(Integer id)
        {
            return findByColumn("SiteFolderId", id.toString(), null, null).get(0);
        }
        
        @Override
        public SiteFolder findWithInfo(Integer id)
        {
            SiteFolder siteFolder = findByColumn("SiteFolderId", id.toString(), null, null).get(0);
            
            
                try
                {

                
                    getRecordById("Site", siteFolder.getSiteId().toString());
                    siteFolder.setSite(Site.process(rs));               
                  

                }
                catch (SQLException ex)
                {
                        System.out.println("Object SiteFolder method findWithInfo(Integer id) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            
            
            return siteFolder;
        }
        
        @Override
        public ArrayList<SiteFolder> findAll(Integer limit, Integer offset)
        {
            ArrayList<SiteFolder> siteFolderList = new ArrayList<SiteFolder>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for SiteFolder, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    siteFolderList = new ArrayList<SiteFolder>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("site_folder", limit, offset);
                    while (rs.next())
                    {
                        siteFolderList.add(SiteFolder.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("SiteFolder object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return siteFolderList;
         
        }
        
        @Override
        public ArrayList<SiteFolder> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<SiteFolder> siteFolderList = new ArrayList<SiteFolder>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for SiteFolder, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    siteFolderList = new ArrayList<SiteFolder>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            SiteFolder siteFolder = (SiteFolder) e.getValue();

                            
                                getRecordById("Site", siteFolder.getSiteId().toString());
                                siteFolder.setSite(Site.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object SiteFolder method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                siteFolderList = new ArrayList<SiteFolder>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("site_folder", limit, offset);
                    while (rs.next())
                    {
                        siteFolderList.add(SiteFolder.process(rs));
                    }

                    
                    
                        for (SiteFolder siteFolder : siteFolderList)
                        {                        
                            
                                getRecordById("Site", siteFolder.getSiteId().toString());
                                siteFolder.setSite(Site.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object SiteFolder method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return siteFolderList;            
        }
        
        @Override
        public ArrayList<SiteFolder> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<SiteFolder> siteFolderList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for SiteFolder(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            SiteFolder i = (SiteFolder) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                siteFolderList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            siteFolderList = null;
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
                    getRecordsByColumnWithLimitOrOffset("site_folder", SiteFolder.checkColumnName(columnName), columnValue, SiteFolder.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        siteFolderList.add(SiteFolder.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("SiteFolder's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return siteFolderList;
        } 
    
        @Override
        public int add(SiteFolder obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                SiteFolder.checkColumnSize(obj.getFolderName(), 100);
                SiteFolder.checkColumnSize(obj.getDescription(), 255);
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO site_folder(FolderName,Description,Rank,SiteId) VALUES (?,?,?,?);");                    
                preparedStatement.setString(1, obj.getFolderName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setInt(3, obj.getRank());
                preparedStatement.setInt(4, obj.getSiteId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site_folder;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SiteFolder's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setSiteFolderId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public SiteFolder update(SiteFolder obj)
        {
           try
            {   
                
                SiteFolder.checkColumnSize(obj.getFolderName(), 100);
                SiteFolder.checkColumnSize(obj.getDescription(), 255);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE site_folder SET FolderName=?,Description=?,Rank=?,SiteId=? WHERE SiteFolderId=?;");                    
                preparedStatement.setString(1, obj.getFolderName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setInt(3, obj.getRank());
                preparedStatement.setInt(4, obj.getSiteId());
                preparedStatement.setInt(5, obj.getSiteFolderId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getSiteFolderId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("SiteFolder's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("site_folder");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(SiteFolder site_folder)
        {
            
                try
                { 
                    
                            getRecordById("Site", site_folder.getSiteId().toString());
                            site_folder.setSite(Site.process(rs));                                       
                    
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
        public void getRelatedObjects(SiteFolder site_folder)
        {
            site_folder.setFileFolderList(new FileFolderDaoImpl().findByColumn("SiteFolderId", site_folder.getSiteFolderId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(SiteFolder obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM site_folder WHERE SiteFolderId=" + obj.getSiteFolderId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SiteFolder's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getSiteFolderId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM site_folder WHERE SiteFolderId=" + id + ";");           
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
                updateQuery("DELETE FROM site_folder;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SiteFolder's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_folder WHERE " + SiteFolder.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("SiteFolder's removeByColumn method error: " + ex.getMessage());
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
                        SiteFolder i = (SiteFolder) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getSiteFolderId());
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
        
                    
        public void getRelatedFileFolderList(SiteFolder site_folder)
        {           
            site_folder.setFileFolderList(new FileFolderDaoImpl().findByColumn("SiteFolderId", site_folder.getSiteFolderId().toString(),null,null));
        }        
        
            
        
        
        public void getRelatedSite(SiteFolder site_folder)
        {            
            try
            {                 
                getRecordById("Site", site_folder.getSiteId().toString());
                site_folder.setSite(Site.process(rs));                                                       
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
          
        
                
        
        public void getRelatedSiteWithInfo(SiteFolder site_folder)
        {            
            site_folder.setSite(new SiteDaoImpl().findWithInfo(site_folder.getSiteId()));
        }
          
        
    }

