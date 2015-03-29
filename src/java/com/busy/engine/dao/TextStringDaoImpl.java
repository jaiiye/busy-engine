






















































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
    
    public class TextStringDaoImpl extends BasicConnection implements Serializable, TextStringDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public TextStringDaoImpl()
        {
            cachingEnabled = false;
        }

        public TextStringDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class TextStringCache
        {
            public static final ConcurrentLruCache<Integer, TextString> textStringCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (TextString i : findAll())
                {
                    getCache().put(i.getTextStringId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, TextString> getCache()
        {
            return TextStringCache.textStringCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, TextString> buildCache(ArrayList<TextString> textStringList)
        {        
            ConcurrentLruCache<Integer, TextString> cache = new ConcurrentLruCache<Integer, TextString>(textStringList.size() + 1000);
            for (TextString i : textStringList)
            {
                cache.put(i.getTextStringId(), i);
            }
            return cache;
        }

        private static ArrayList<TextString> findAll()
        {
            ArrayList<TextString> textString = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("textString");
                while (rs.next())
                {
                    textString.add(TextString.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("TextString object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return textString;
        }
        
        @Override
        public TextString find(Integer id)
        {
            return findByColumn("TextStringId", id.toString(), null, null).get(0);
        }
        
        @Override
        public TextString findWithInfo(Integer id)
        {
            TextString textString = findByColumn("TextStringId", id.toString(), null, null).get(0);
            
            
            
            return textString;
        }
        
        @Override
        public ArrayList<TextString> findAll(Integer limit, Integer offset)
        {
            ArrayList<TextString> textStringList = new ArrayList<TextString>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for TextString, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    textStringList = new ArrayList<TextString>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("text_string", limit, offset);
                    while (rs.next())
                    {
                        textStringList.add(TextString.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("TextString object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return textStringList;
         
        }
        
        @Override
        public ArrayList<TextString> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<TextString> textStringList = new ArrayList<TextString>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for TextString, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    textStringList = new ArrayList<TextString>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                textStringList = new ArrayList<TextString>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("text_string", limit, offset);
                    while (rs.next())
                    {
                        textStringList.add(TextString.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object TextString method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return textStringList;            
        }
        
        @Override
        public ArrayList<TextString> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<TextString> textStringList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for TextString(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            TextString i = (TextString) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                textStringList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            textStringList = null;
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
                    getRecordsByColumnWithLimitOrOffset("text_string", TextString.checkColumnName(columnName), columnValue, TextString.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        textStringList.add(TextString.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("TextString's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return textStringList;
        } 
    
        @Override
        public int add(TextString obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                TextString.checkColumnSize(obj.getKey(), 200);
                  

                openConnection();
                prepareStatement("INSERT INTO text_string(Key) VALUES (?);");                    
                preparedStatement.setString(1, obj.getKey());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from text_string;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("TextString's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setTextStringId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public TextString update(TextString obj)
        {
           try
            {   
                
                TextString.checkColumnSize(obj.getKey(), 200);
                                  
                openConnection();                           
                prepareStatement("UPDATE text_string SET Key=? WHERE TextStringId=?;");                    
                preparedStatement.setString(1, obj.getKey());
                preparedStatement.setInt(2, obj.getTextStringId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getTextStringId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("TextString's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("text_string");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(TextString text_string)
        {
              
        }
        
        @Override
        public void getRelatedObjects(TextString text_string)
        {
            text_string.setLocalizedStringList(new LocalizedStringDaoImpl().findByColumn("TextStringId", text_string.getTextStringId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(TextString obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM text_string WHERE TextStringId=" + obj.getTextStringId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("TextString's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getTextStringId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM text_string WHERE TextStringId=" + id + ";");           
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
                updateQuery("DELETE FROM text_string;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("TextString's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM text_string WHERE " + TextString.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("TextString's removeByColumn method error: " + ex.getMessage());
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
                        TextString i = (TextString) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getTextStringId());
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
        
                    
        public void getRelatedLocalizedStringList(TextString text_string)
        {           
            text_string.setLocalizedStringList(new LocalizedStringDaoImpl().findByColumn("TextStringId", text_string.getTextStringId().toString(),null,null));
        }        
        
            
        
          
        
                
          
        
    }

