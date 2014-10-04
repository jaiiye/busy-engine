






















































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
    
    public class SiteItemDaoImpl extends BasicConnection implements Serializable, SiteItemDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public SiteItemDaoImpl()
        {
            cachingEnabled = false;
        }

        public SiteItemDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class SiteItemCache
        {
            public static final ConcurrentLruCache<Integer, SiteItem> siteItemCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (SiteItem i : findAll())
                {
                    getCache().put(i.getSiteItemId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, SiteItem> getCache()
        {
            return SiteItemCache.siteItemCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, SiteItem> buildCache(ArrayList<SiteItem> siteItemList)
        {        
            ConcurrentLruCache<Integer, SiteItem> cache = new ConcurrentLruCache<Integer, SiteItem>(siteItemList.size() + 1000);
            for (SiteItem i : siteItemList)
            {
                cache.put(i.getSiteItemId(), i);
            }
            return cache;
        }

        private static ArrayList<SiteItem> findAll()
        {
            ArrayList<SiteItem> siteItem = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("siteItem");
                while (rs.next())
                {
                    siteItem.add(SiteItem.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("SiteItem object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return siteItem;
        }
        
        @Override
        public SiteItem find(Integer id)
        {
            return findByColumn("SiteItemId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<SiteItem> findAll(Integer limit, Integer offset)
        {
            ArrayList<SiteItem> siteItemList = new ArrayList<SiteItem>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for SiteItem, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    siteItemList = new ArrayList<SiteItem>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("site_item", limit, offset);
                    while (rs.next())
                    {
                        siteItemList.add(SiteItem.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("SiteItem object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return siteItemList;
         
        }
        
        @Override
        public ArrayList<SiteItem> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<SiteItem> siteItemList = new ArrayList<SiteItem>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for SiteItem, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    siteItemList = new ArrayList<SiteItem>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            SiteItem siteItem = (SiteItem) e.getValue();

                            
                                getRecordById("Site", siteItem.getSiteId().toString());
                                siteItem.setSite(Site.process(rs));               
                            
                                getRecordById("Item", siteItem.getItemId().toString());
                                siteItem.setItem(Item.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object SiteItem method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                siteItemList = new ArrayList<SiteItem>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("site_item", limit, offset);
                    while (rs.next())
                    {
                        siteItemList.add(SiteItem.process(rs));
                    }

                    
                    
                        for (SiteItem siteItem : siteItemList)
                        {                        
                            
                                getRecordById("Site", siteItem.getSiteId().toString());
                                siteItem.setSite(Site.process(rs));               
                            
                                getRecordById("Item", siteItem.getItemId().toString());
                                siteItem.setItem(Item.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object SiteItem method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return siteItemList;            
        }
        
        @Override
        public ArrayList<SiteItem> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<SiteItem> siteItemList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for SiteItem(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            SiteItem i = (SiteItem) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                siteItemList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            siteItemList = null;
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
                    getRecordsByColumnWithLimitOrOffset("site_item", SiteItem.checkColumnName(columnName), columnValue, SiteItem.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        siteItemList.add(SiteItem.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("SiteItem's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return siteItemList;
        } 
    
        @Override
        public int add(SiteItem obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO site_item(SiteItemId,SiteId,ItemId,) VALUES (?,?);");                    
                preparedStatement.setInt(0, obj.getSiteItemId());
                preparedStatement.setInt(1, obj.getSiteId());
                preparedStatement.setInt(2, obj.getItemId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site_item;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SiteItem's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setSiteItemId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public SiteItem update(SiteItem obj)
        {
           try
            {   
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE site_item SET com.busy.util.DatabaseColumn@52d8e95=?,com.busy.util.DatabaseColumn@7c7f55ea=? WHERE SiteItemId=?;");                    
                preparedStatement.setInt(0, obj.getSiteItemId());
                preparedStatement.setInt(1, obj.getSiteId());
                preparedStatement.setInt(2, obj.getItemId());
                preparedStatement.setInt(3, obj.getSiteItemId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getSiteItemId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("SiteItem's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("site_item");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(SiteItem site_item)
        {
            
                try
                { 
                    
                            getRecordById("Site", site_item.getSiteId().toString());
                            site_item.setSite(Site.process(rs));                                       
                    
                            getRecordById("Item", site_item.getItemId().toString());
                            site_item.setItem(Item.process(rs));                                       
                    
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
        public void getRelatedObjects(SiteItem site_item)
        {
             
        }
        
        @Override
        public boolean remove(SiteItem obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM site_item WHERE SiteItemId=" + obj.getSiteItemId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SiteItem's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getSiteItemId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM site_item WHERE SiteItemId=" + id + ";");           
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
                updateQuery("DELETE FROM site_item;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SiteItem's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_item WHERE " + SiteItem.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("SiteItem's removeByColumn method error: " + ex.getMessage());
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
                        SiteItem i = (SiteItem) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getSiteItemId());
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

