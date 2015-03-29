






















































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
    
    public class LocaleDaoImpl extends BasicConnection implements Serializable, LocaleDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public LocaleDaoImpl()
        {
            cachingEnabled = false;
        }

        public LocaleDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class LocaleCache
        {
            public static final ConcurrentLruCache<Integer, Locale> localeCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (Locale i : findAll())
                {
                    getCache().put(i.getLocaleId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, Locale> getCache()
        {
            return LocaleCache.localeCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, Locale> buildCache(ArrayList<Locale> localeList)
        {        
            ConcurrentLruCache<Integer, Locale> cache = new ConcurrentLruCache<Integer, Locale>(localeList.size() + 1000);
            for (Locale i : localeList)
            {
                cache.put(i.getLocaleId(), i);
            }
            return cache;
        }

        private static ArrayList<Locale> findAll()
        {
            ArrayList<Locale> locale = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("locale");
                while (rs.next())
                {
                    locale.add(Locale.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Locale object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return locale;
        }
        
        @Override
        public Locale find(Integer id)
        {
            return findByColumn("LocaleId", id.toString(), null, null).get(0);
        }
        
        @Override
        public Locale findWithInfo(Integer id)
        {
            Locale locale = findByColumn("LocaleId", id.toString(), null, null).get(0);
            
            
            
            return locale;
        }
        
        @Override
        public ArrayList<Locale> findAll(Integer limit, Integer offset)
        {
            ArrayList<Locale> localeList = new ArrayList<Locale>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for Locale, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    localeList = new ArrayList<Locale>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("locale", limit, offset);
                    while (rs.next())
                    {
                        localeList.add(Locale.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Locale object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return localeList;
         
        }
        
        @Override
        public ArrayList<Locale> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Locale> localeList = new ArrayList<Locale>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for Locale, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    localeList = new ArrayList<Locale>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                localeList = new ArrayList<Locale>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("locale", limit, offset);
                    while (rs.next())
                    {
                        localeList.add(Locale.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object Locale method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return localeList;            
        }
        
        @Override
        public ArrayList<Locale> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Locale> localeList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for Locale(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            Locale i = (Locale) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                localeList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            localeList = null;
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
                    getRecordsByColumnWithLimitOrOffset("locale", Locale.checkColumnName(columnName), columnValue, Locale.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        localeList.add(Locale.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Locale's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return localeList;
        } 
    
        @Override
        public int add(Locale obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                Locale.checkColumnSize(obj.getLocaleString(), 10);
                Locale.checkColumnSize(obj.getLocaleCharacterSet(), 25);
                  

                openConnection();
                prepareStatement("INSERT INTO locale(LocaleString,LocaleCharacterSet) VALUES (?,?);");                    
                preparedStatement.setString(1, obj.getLocaleString());
                preparedStatement.setString(2, obj.getLocaleCharacterSet());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from locale;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Locale's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setLocaleId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public Locale update(Locale obj)
        {
           try
            {   
                
                Locale.checkColumnSize(obj.getLocaleString(), 10);
                Locale.checkColumnSize(obj.getLocaleCharacterSet(), 25);
                                  
                openConnection();                           
                prepareStatement("UPDATE locale SET LocaleString=?,LocaleCharacterSet=? WHERE LocaleId=?;");                    
                preparedStatement.setString(1, obj.getLocaleString());
                preparedStatement.setString(2, obj.getLocaleCharacterSet());
                preparedStatement.setInt(3, obj.getLocaleId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getLocaleId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("Locale's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("locale");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(Locale locale)
        {
              
        }
        
        @Override
        public void getRelatedObjects(Locale locale)
        {
             
        }
        
        @Override
        public boolean remove(Locale obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM locale WHERE LocaleId=" + obj.getLocaleId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Locale's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getLocaleId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM locale WHERE LocaleId=" + id + ";");           
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
                updateQuery("DELETE FROM locale;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Locale's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM locale WHERE " + Locale.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Locale's removeByColumn method error: " + ex.getMessage());
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
                        Locale i = (Locale) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getLocaleId());
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

