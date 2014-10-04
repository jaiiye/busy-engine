






















































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
    
    public class LocalizedStringDaoImpl extends BasicConnection implements Serializable, LocalizedStringDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public LocalizedStringDaoImpl()
        {
            cachingEnabled = false;
        }

        public LocalizedStringDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class LocalizedStringCache
        {
            public static final ConcurrentLruCache<Integer, LocalizedString> localizedStringCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (LocalizedString i : findAll())
                {
                    getCache().put(i.getLocalizedStringId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, LocalizedString> getCache()
        {
            return LocalizedStringCache.localizedStringCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, LocalizedString> buildCache(ArrayList<LocalizedString> localizedStringList)
        {        
            ConcurrentLruCache<Integer, LocalizedString> cache = new ConcurrentLruCache<Integer, LocalizedString>(localizedStringList.size() + 1000);
            for (LocalizedString i : localizedStringList)
            {
                cache.put(i.getLocalizedStringId(), i);
            }
            return cache;
        }

        private static ArrayList<LocalizedString> findAll()
        {
            ArrayList<LocalizedString> localizedString = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("localizedString");
                while (rs.next())
                {
                    localizedString.add(LocalizedString.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("LocalizedString object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return localizedString;
        }
        
        @Override
        public LocalizedString find(Integer id)
        {
            return findByColumn("LocalizedStringId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<LocalizedString> findAll(Integer limit, Integer offset)
        {
            ArrayList<LocalizedString> localizedStringList = new ArrayList<LocalizedString>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for LocalizedString, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    localizedStringList = new ArrayList<LocalizedString>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("localized_string", limit, offset);
                    while (rs.next())
                    {
                        localizedStringList.add(LocalizedString.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("LocalizedString object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return localizedStringList;
         
        }
        
        @Override
        public ArrayList<LocalizedString> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<LocalizedString> localizedStringList = new ArrayList<LocalizedString>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for LocalizedString, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    localizedStringList = new ArrayList<LocalizedString>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            LocalizedString localizedString = (LocalizedString) e.getValue();

                            
                                getRecordById("TextString", localizedString.getTextStringId().toString());
                                localizedString.setTextString(TextString.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object LocalizedString method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                localizedStringList = new ArrayList<LocalizedString>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("localized_string", limit, offset);
                    while (rs.next())
                    {
                        localizedStringList.add(LocalizedString.process(rs));
                    }

                    
                    
                        for (LocalizedString localizedString : localizedStringList)
                        {                        
                            
                                getRecordById("TextString", localizedString.getTextStringId().toString());
                                localizedString.setTextString(TextString.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object LocalizedString method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return localizedStringList;            
        }
        
        @Override
        public ArrayList<LocalizedString> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<LocalizedString> localizedStringList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for LocalizedString(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            LocalizedString i = (LocalizedString) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                localizedStringList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            localizedStringList = null;
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
                    getRecordsByColumnWithLimitOrOffset("localized_string", LocalizedString.checkColumnName(columnName), columnValue, LocalizedString.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        localizedStringList.add(LocalizedString.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("LocalizedString's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return localizedStringList;
        } 
    
        @Override
        public int add(LocalizedString obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                
                LocalizedString.checkColumnSize(obj.getStringValue(), 255);
                
                  

                openConnection();
                prepareStatement("INSERT INTO localized_string(LocalizedStringId,Locale,StringValue,TextStringId,) VALUES (?,?,?);");                    
                preparedStatement.setInt(0, obj.getLocalizedStringId());
                preparedStatement.setInt(1, obj.getLocale());
                preparedStatement.setString(2, obj.getStringValue());
                preparedStatement.setInt(3, obj.getTextStringId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from localized_string;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("LocalizedString's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setLocalizedStringId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public LocalizedString update(LocalizedString obj)
        {
           try
            {   
                
                
                LocalizedString.checkColumnSize(obj.getStringValue(), 255);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE localized_string SET com.busy.util.DatabaseColumn@7d6ade4e=?,com.busy.util.DatabaseColumn@37dc0238=?,com.busy.util.DatabaseColumn@194579f3=? WHERE LocalizedStringId=?;");                    
                preparedStatement.setInt(0, obj.getLocalizedStringId());
                preparedStatement.setInt(1, obj.getLocale());
                preparedStatement.setString(2, obj.getStringValue());
                preparedStatement.setInt(3, obj.getTextStringId());
                preparedStatement.setInt(4, obj.getLocalizedStringId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getLocalizedStringId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("LocalizedString's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("localized_string");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(LocalizedString localized_string)
        {
            
                try
                { 
                    
                            getRecordById("TextString", localized_string.getTextStringId().toString());
                            localized_string.setTextString(TextString.process(rs));                                       
                    
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
        public void getRelatedObjects(LocalizedString localized_string)
        {
             
        }
        
        @Override
        public boolean remove(LocalizedString obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM localized_string WHERE LocalizedStringId=" + obj.getLocalizedStringId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("LocalizedString's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getLocalizedStringId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM localized_string WHERE LocalizedStringId=" + id + ";");           
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
                updateQuery("DELETE FROM localized_string;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("LocalizedString's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM localized_string WHERE " + LocalizedString.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("LocalizedString's removeByColumn method error: " + ex.getMessage());
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
                        LocalizedString i = (LocalizedString) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getLocalizedStringId());
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

