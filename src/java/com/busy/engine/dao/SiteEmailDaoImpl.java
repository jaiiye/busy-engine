
























































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
    
    public class SiteEmailDaoImpl extends BasicConnection implements Serializable, SiteEmailDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public SiteEmailDaoImpl()
        {
            cachingEnabled = false;
        }

        public SiteEmailDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class SiteEmailCache
        {
            public static final ConcurrentLruCache<Integer, SiteEmail> siteEmailCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (SiteEmail i : findAll())
                {
                    getCache().put(i.getSiteEmailId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, SiteEmail> getCache()
        {
            return SiteEmailCache.siteEmailCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, SiteEmail> buildCache(ArrayList<SiteEmail> siteEmailList)
        {        
            ConcurrentLruCache<Integer, SiteEmail> cache = new ConcurrentLruCache<Integer, SiteEmail>(siteEmailList.size() + 1000);
            for (SiteEmail i : siteEmailList)
            {
                cache.put(i.getSiteEmailId(), i);
            }
            return cache;
        }

        private static ArrayList<SiteEmail> findAll()
        {
            ArrayList<SiteEmail> siteEmail = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("siteEmail");
                while (rs.next())
                {
                    siteEmail.add(SiteEmail.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("SiteEmail object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return siteEmail;
        }
        
        @Override
        public SiteEmail find(Integer id)
        {
            return findByColumn("SiteEmailId", id.toString(), null, null).get(0);
        }
        
        @Override
        public SiteEmail findWithInfo(Integer id)
        {
            SiteEmail siteEmail = findByColumn("SiteEmailId", id.toString(), null, null).get(0);
            
            
            
            return siteEmail;
        }
        
        @Override
        public ArrayList<SiteEmail> findAll(Integer limit, Integer offset)
        {
            ArrayList<SiteEmail> siteEmailList = new ArrayList<SiteEmail>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for SiteEmail, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    siteEmailList = new ArrayList<SiteEmail>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("site_email", limit, offset);
                    while (rs.next())
                    {
                        siteEmailList.add(SiteEmail.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("SiteEmail object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return siteEmailList;
         
        }
        
        @Override
        public ArrayList<SiteEmail> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<SiteEmail> siteEmailList = new ArrayList<SiteEmail>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for SiteEmail, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    siteEmailList = new ArrayList<SiteEmail>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                siteEmailList = new ArrayList<SiteEmail>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("site_email", limit, offset);
                    while (rs.next())
                    {
                        siteEmailList.add(SiteEmail.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object SiteEmail method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return siteEmailList;            
        }
        
        @Override
        public ArrayList<SiteEmail> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<SiteEmail> siteEmailList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for SiteEmail(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            SiteEmail i = (SiteEmail) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                siteEmailList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            siteEmailList = null;
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
                    getRecordsByColumnWithLimitOrOffset("site_email", SiteEmail.checkColumnName(columnName), columnValue, SiteEmail.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        siteEmailList.add(SiteEmail.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("SiteEmail's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return siteEmailList;
        } 
        
        @Override
        public ArrayList<SiteEmail> findByColumns(Column... columns)
        {
            ArrayList<SiteEmail> siteEmailList = new ArrayList<>();

            try
            {
                //make sure the correct isNumeric values are set for columns
                for(Column c : columns) 
                {
                    c.setNumeric(SiteEmail.isColumnNumeric(c.getColumnName()));                
                }

                getAllRecordsByColumns("site_email", columns);
                while (rs.next())
                {
                    siteEmailList.add(SiteEmail.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("SiteEmail's method findByColumns(Column... columns) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }

            return siteEmailList;
        }
    
        @Override
        public int add(SiteEmail obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                SiteEmail.checkColumnSize(obj.getHost(), 255);
                
                SiteEmail.checkColumnSize(obj.getUsername(), 255);
                SiteEmail.checkColumnSize(obj.getPassword(), 45);
                  

                openConnection();
                prepareStatement("INSERT INTO site_email(Host,Port,Username,Password) VALUES (?,?,?,?);");                    
                preparedStatement.setString(1, obj.getHost());
                preparedStatement.setInt(2, obj.getPort());
                preparedStatement.setString(3, obj.getUsername());
                preparedStatement.setString(4, obj.getPassword());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site_email;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SiteEmail's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setSiteEmailId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public SiteEmail update(SiteEmail obj)
        {
           try
            {   
                
                SiteEmail.checkColumnSize(obj.getHost(), 255);
                
                SiteEmail.checkColumnSize(obj.getUsername(), 255);
                SiteEmail.checkColumnSize(obj.getPassword(), 45);
                                  
                openConnection();                           
                prepareStatement("UPDATE site_email SET Host=?,Port=?,Username=?,Password=? WHERE SiteEmailId=?;");                    
                preparedStatement.setString(1, obj.getHost());
                preparedStatement.setInt(2, obj.getPort());
                preparedStatement.setString(3, obj.getUsername());
                preparedStatement.setString(4, obj.getPassword());
                preparedStatement.setInt(5, obj.getSiteEmailId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getSiteEmailId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("SiteEmail's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("site_email");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(SiteEmail site_email)
        {
              
        }
        
        @Override
        public void getRelatedObjects(SiteEmail site_email)
        {
            site_email.setSiteList(new SiteDaoImpl().findByColumn("SiteEmailId", site_email.getSiteEmailId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(SiteEmail obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM site_email WHERE SiteEmailId=" + obj.getSiteEmailId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SiteEmail's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getSiteEmailId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM site_email WHERE SiteEmailId=" + id + ";");           
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
                updateQuery("DELETE FROM site_email;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SiteEmail's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_email WHERE " + SiteEmail.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("SiteEmail's removeByColumn method error: " + ex.getMessage());
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
                        SiteEmail i = (SiteEmail) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getSiteEmailId());
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
        
                    
        public void getRelatedSiteList(SiteEmail site_email)
        {           
            site_email.setSiteList(new SiteDaoImpl().findByColumn("SiteEmailId", site_email.getSiteEmailId().toString(),null,null));
        }        
        
            
        
          
        
                
          
        
    }

