






















































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
    
    public class SiteLanguageDaoImpl extends BasicConnection implements Serializable, SiteLanguageDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public SiteLanguageDaoImpl()
        {
            cachingEnabled = false;
        }

        public SiteLanguageDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class SiteLanguageCache
        {
            public static final ConcurrentLruCache<Integer, SiteLanguage> siteLanguageCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (SiteLanguage i : findAll())
                {
                    getCache().put(i.getSiteLanguageId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, SiteLanguage> getCache()
        {
            return SiteLanguageCache.siteLanguageCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, SiteLanguage> buildCache(ArrayList<SiteLanguage> siteLanguageList)
        {        
            ConcurrentLruCache<Integer, SiteLanguage> cache = new ConcurrentLruCache<Integer, SiteLanguage>(siteLanguageList.size() + 1000);
            for (SiteLanguage i : siteLanguageList)
            {
                cache.put(i.getSiteLanguageId(), i);
            }
            return cache;
        }

        private static ArrayList<SiteLanguage> findAll()
        {
            ArrayList<SiteLanguage> siteLanguage = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("siteLanguage");
                while (rs.next())
                {
                    siteLanguage.add(SiteLanguage.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("SiteLanguage object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return siteLanguage;
        }
        
        @Override
        public SiteLanguage find(Integer id)
        {
            return findByColumn("SiteLanguageId", id.toString(), null, null).get(0);
        }
        
        @Override
        public SiteLanguage findWithInfo(Integer id)
        {
            SiteLanguage siteLanguage = findByColumn("SiteLanguageId", id.toString(), null, null).get(0);
            
            
                try
                {

                
                    getRecordById("Site", siteLanguage.getSiteId().toString());
                    siteLanguage.setSite(Site.process(rs));               
                  

                }
                catch (SQLException ex)
                {
                        System.out.println("Object SiteLanguage method findWithInfo(Integer id) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            
            
            return siteLanguage;
        }
        
        @Override
        public ArrayList<SiteLanguage> findAll(Integer limit, Integer offset)
        {
            ArrayList<SiteLanguage> siteLanguageList = new ArrayList<SiteLanguage>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for SiteLanguage, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    siteLanguageList = new ArrayList<SiteLanguage>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("site_language", limit, offset);
                    while (rs.next())
                    {
                        siteLanguageList.add(SiteLanguage.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("SiteLanguage object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return siteLanguageList;
         
        }
        
        @Override
        public ArrayList<SiteLanguage> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<SiteLanguage> siteLanguageList = new ArrayList<SiteLanguage>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for SiteLanguage, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    siteLanguageList = new ArrayList<SiteLanguage>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            SiteLanguage siteLanguage = (SiteLanguage) e.getValue();

                            
                                getRecordById("Site", siteLanguage.getSiteId().toString());
                                siteLanguage.setSite(Site.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object SiteLanguage method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                siteLanguageList = new ArrayList<SiteLanguage>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("site_language", limit, offset);
                    while (rs.next())
                    {
                        siteLanguageList.add(SiteLanguage.process(rs));
                    }

                    
                    
                        for (SiteLanguage siteLanguage : siteLanguageList)
                        {                        
                            
                                getRecordById("Site", siteLanguage.getSiteId().toString());
                                siteLanguage.setSite(Site.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object SiteLanguage method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return siteLanguageList;            
        }
        
        @Override
        public ArrayList<SiteLanguage> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<SiteLanguage> siteLanguageList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for SiteLanguage(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            SiteLanguage i = (SiteLanguage) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                siteLanguageList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            siteLanguageList = null;
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
                    getRecordsByColumnWithLimitOrOffset("site_language", SiteLanguage.checkColumnName(columnName), columnValue, SiteLanguage.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        siteLanguageList.add(SiteLanguage.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("SiteLanguage's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return siteLanguageList;
        } 
    
        @Override
        public int add(SiteLanguage obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                SiteLanguage.checkColumnSize(obj.getLanguageName(), 100);
                SiteLanguage.checkColumnSize(obj.getLocale(), 10);
                
                SiteLanguage.checkColumnSize(obj.getFlagFileName(), 255);
                
                  

                openConnection();
                prepareStatement("INSERT INTO site_language(LanguageName,Locale,Rtl,FlagFileName,SiteId) VALUES (?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getLanguageName());
                preparedStatement.setString(2, obj.getLocale());
                preparedStatement.setInt(3, obj.getRtl());
                preparedStatement.setString(4, obj.getFlagFileName());
                preparedStatement.setInt(5, obj.getSiteId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site_language;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SiteLanguage's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setSiteLanguageId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public SiteLanguage update(SiteLanguage obj)
        {
           try
            {   
                
                SiteLanguage.checkColumnSize(obj.getLanguageName(), 100);
                SiteLanguage.checkColumnSize(obj.getLocale(), 10);
                
                SiteLanguage.checkColumnSize(obj.getFlagFileName(), 255);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE site_language SET LanguageName=?,Locale=?,Rtl=?,FlagFileName=?,SiteId=? WHERE SiteLanguageId=?;");                    
                preparedStatement.setString(1, obj.getLanguageName());
                preparedStatement.setString(2, obj.getLocale());
                preparedStatement.setInt(3, obj.getRtl());
                preparedStatement.setString(4, obj.getFlagFileName());
                preparedStatement.setInt(5, obj.getSiteId());
                preparedStatement.setInt(6, obj.getSiteLanguageId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getSiteLanguageId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("SiteLanguage's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("site_language");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(SiteLanguage site_language)
        {
            
                try
                { 
                    
                            getRecordById("Site", site_language.getSiteId().toString());
                            site_language.setSite(Site.process(rs));                                       
                    
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
        public void getRelatedObjects(SiteLanguage site_language)
        {
             
        }
        
        @Override
        public boolean remove(SiteLanguage obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM site_language WHERE SiteLanguageId=" + obj.getSiteLanguageId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SiteLanguage's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getSiteLanguageId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM site_language WHERE SiteLanguageId=" + id + ";");           
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
                updateQuery("DELETE FROM site_language;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SiteLanguage's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_language WHERE " + SiteLanguage.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("SiteLanguage's removeByColumn method error: " + ex.getMessage());
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
                        SiteLanguage i = (SiteLanguage) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getSiteLanguageId());
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
        
        
            
        
        
        public void getRelatedSite(SiteLanguage site_language)
        {            
            try
            {                 
                getRecordById("Site", site_language.getSiteId().toString());
                site_language.setSite(Site.process(rs));                                                       
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
          
        
                
        
        public void getRelatedSiteWithInfo(SiteLanguage site_language)
        {            
            site_language.setSite(new SiteDaoImpl().findWithInfo(site_language.getSiteId()));
        }
          
        
    }

