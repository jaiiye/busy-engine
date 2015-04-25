
























































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
    
    public class SiteAttributeDaoImpl extends BasicConnection implements Serializable, SiteAttributeDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public SiteAttributeDaoImpl()
        {
            cachingEnabled = false;
        }

        public SiteAttributeDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class SiteAttributeCache
        {
            public static final ConcurrentLruCache<Integer, SiteAttribute> siteAttributeCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (SiteAttribute i : findAll())
                {
                    getCache().put(i.getSiteAttributeId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, SiteAttribute> getCache()
        {
            return SiteAttributeCache.siteAttributeCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, SiteAttribute> buildCache(ArrayList<SiteAttribute> siteAttributeList)
        {        
            ConcurrentLruCache<Integer, SiteAttribute> cache = new ConcurrentLruCache<Integer, SiteAttribute>(siteAttributeList.size() + 1000);
            for (SiteAttribute i : siteAttributeList)
            {
                cache.put(i.getSiteAttributeId(), i);
            }
            return cache;
        }

        private static ArrayList<SiteAttribute> findAll()
        {
            ArrayList<SiteAttribute> siteAttribute = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("siteAttribute");
                while (rs.next())
                {
                    siteAttribute.add(SiteAttribute.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("SiteAttribute object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return siteAttribute;
        }
        
        @Override
        public SiteAttribute find(Integer id)
        {
            return findByColumn("SiteAttributeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public SiteAttribute findWithInfo(Integer id)
        {
            SiteAttribute siteAttribute = findByColumn("SiteAttributeId", id.toString(), null, null).get(0);
            
            
                try
                {

                
                    getRecordById("site", siteAttribute.getSiteId().toString());
                    siteAttribute.setSite(Site.process(rs));               
                  

                }
                catch (SQLException ex)
                {
                        System.out.println("Object SiteAttribute method findWithInfo(Integer id) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            
            
            return siteAttribute;
        }
        
        @Override
        public ArrayList<SiteAttribute> findAll(Integer limit, Integer offset)
        {
            ArrayList<SiteAttribute> siteAttributeList = new ArrayList<SiteAttribute>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for SiteAttribute, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    siteAttributeList = new ArrayList<SiteAttribute>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("site_attribute", limit, offset);
                    while (rs.next())
                    {
                        siteAttributeList.add(SiteAttribute.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("SiteAttribute object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return siteAttributeList;
         
        }
        
        @Override
        public ArrayList<SiteAttribute> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<SiteAttribute> siteAttributeList = new ArrayList<SiteAttribute>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for SiteAttribute, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    siteAttributeList = new ArrayList<SiteAttribute>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            SiteAttribute siteAttribute = (SiteAttribute) e.getValue();

                            
                                getRecordById("site", siteAttribute.getSiteId().toString());
                                siteAttribute.setSite(Site.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object SiteAttribute method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                siteAttributeList = new ArrayList<SiteAttribute>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("site_attribute", limit, offset);
                    while (rs.next())
                    {
                        siteAttributeList.add(SiteAttribute.process(rs));
                    }

                    
                    
                        for (SiteAttribute siteAttribute : siteAttributeList)
                        {                        
                            
                                getRecordById("site", siteAttribute.getSiteId().toString());
                                siteAttribute.setSite(Site.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object SiteAttribute method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return siteAttributeList;            
        }
        
        @Override
        public ArrayList<SiteAttribute> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<SiteAttribute> siteAttributeList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for SiteAttribute(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            SiteAttribute i = (SiteAttribute) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                siteAttributeList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            siteAttributeList = null;
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
                    getRecordsByColumnWithLimitOrOffset("site_attribute", SiteAttribute.checkColumnName(columnName), columnValue, SiteAttribute.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        siteAttributeList.add(SiteAttribute.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("SiteAttribute's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return siteAttributeList;
        } 
        
        @Override
        public ArrayList<SiteAttribute> findByColumns(Column... columns)
        {
            ArrayList<SiteAttribute> siteAttributeList = new ArrayList<>();

            try
            {
                //make sure the correct isNumeric values are set for columns
                for(Column c : columns) 
                {
                    c.setNumeric(SiteAttribute.isColumnNumeric(c.getColumnName()));                
                }

                getAllRecordsByColumns("site_attribute", columns);
                while (rs.next())
                {
                    siteAttributeList.add(SiteAttribute.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("SiteAttribute's method findByColumns(Column... columns) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }

            return siteAttributeList;
        }
    
        @Override
        public int add(SiteAttribute obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                SiteAttribute.checkColumnSize(obj.getAttributeKey(), 100);
                SiteAttribute.checkColumnSize(obj.getAttributeValue(), 255);
                SiteAttribute.checkColumnSize(obj.getAttributeType(), 45);
                
                  

                openConnection();
                prepareStatement("INSERT INTO site_attribute(AttributeKey,AttributeValue,AttributeType,SiteId) VALUES (?,?,?,?);");                    
                preparedStatement.setString(1, obj.getAttributeKey());
                preparedStatement.setString(2, obj.getAttributeValue());
                preparedStatement.setString(3, obj.getAttributeType());
                preparedStatement.setInt(4, obj.getSiteId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from site_attribute;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SiteAttribute's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setSiteAttributeId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public SiteAttribute update(SiteAttribute obj)
        {
           try
            {   
                
                SiteAttribute.checkColumnSize(obj.getAttributeKey(), 100);
                SiteAttribute.checkColumnSize(obj.getAttributeValue(), 255);
                SiteAttribute.checkColumnSize(obj.getAttributeType(), 45);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE site_attribute SET AttributeKey=?,AttributeValue=?,AttributeType=?,SiteId=? WHERE SiteAttributeId=?;");                    
                preparedStatement.setString(1, obj.getAttributeKey());
                preparedStatement.setString(2, obj.getAttributeValue());
                preparedStatement.setString(3, obj.getAttributeType());
                preparedStatement.setInt(4, obj.getSiteId());
                preparedStatement.setInt(5, obj.getSiteAttributeId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getSiteAttributeId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("SiteAttribute's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("site_attribute");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(SiteAttribute site_attribute)
        {
            
                try
                { 
                    
                            getRecordById("site", site_attribute.getSiteId().toString());
                            site_attribute.setSite(Site.process(rs));                                       
                    
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
        public void getRelatedObjects(SiteAttribute site_attribute)
        {
             
        }
        
        @Override
        public boolean remove(SiteAttribute obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM site_attribute WHERE SiteAttributeId=" + obj.getSiteAttributeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SiteAttribute's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getSiteAttributeId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM site_attribute WHERE SiteAttributeId=" + id + ";");           
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
                updateQuery("DELETE FROM site_attribute;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("SiteAttribute's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM site_attribute WHERE " + SiteAttribute.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("SiteAttribute's removeByColumn method error: " + ex.getMessage());
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
                        SiteAttribute i = (SiteAttribute) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getSiteAttributeId());
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
        
        
            
        
        
        public void getRelatedSite(SiteAttribute site_attribute)
        {            
            try
            {                 
                getRecordById("Site", site_attribute.getSiteId().toString());
                site_attribute.setSite(Site.process(rs));                                                       
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
          
        
                
        
        public void getRelatedSiteWithInfo(SiteAttribute site_attribute)
        {            
            site_attribute.setSite(new SiteDaoImpl().findWithInfo(site_attribute.getSiteId()));
        }
          
        
    }

