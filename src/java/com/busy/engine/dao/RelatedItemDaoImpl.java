






















































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
    
    public class RelatedItemDaoImpl extends BasicConnection implements Serializable, RelatedItemDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public RelatedItemDaoImpl()
        {
            cachingEnabled = false;
        }

        public RelatedItemDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class RelatedItemCache
        {
            public static final ConcurrentLruCache<Integer, RelatedItem> relatedItemCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (RelatedItem i : findAll())
                {
                    getCache().put(i.getRelatedItemId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, RelatedItem> getCache()
        {
            return RelatedItemCache.relatedItemCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, RelatedItem> buildCache(ArrayList<RelatedItem> relatedItemList)
        {        
            ConcurrentLruCache<Integer, RelatedItem> cache = new ConcurrentLruCache<Integer, RelatedItem>(relatedItemList.size() + 1000);
            for (RelatedItem i : relatedItemList)
            {
                cache.put(i.getRelatedItemId(), i);
            }
            return cache;
        }

        private static ArrayList<RelatedItem> findAll()
        {
            ArrayList<RelatedItem> relatedItem = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("relatedItem");
                while (rs.next())
                {
                    relatedItem.add(RelatedItem.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("RelatedItem object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return relatedItem;
        }
        
        @Override
        public RelatedItem find(Integer id)
        {
            return findByColumn("RelatedItemId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<RelatedItem> findAll(Integer limit, Integer offset)
        {
            ArrayList<RelatedItem> relatedItemList = new ArrayList<RelatedItem>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for RelatedItem, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    relatedItemList = new ArrayList<RelatedItem>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("related_item", limit, offset);
                    while (rs.next())
                    {
                        relatedItemList.add(RelatedItem.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("RelatedItem object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return relatedItemList;
         
        }
        
        @Override
        public ArrayList<RelatedItem> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<RelatedItem> relatedItemList = new ArrayList<RelatedItem>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for RelatedItem, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    relatedItemList = new ArrayList<RelatedItem>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                relatedItemList = new ArrayList<RelatedItem>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("related_item", limit, offset);
                    while (rs.next())
                    {
                        relatedItemList.add(RelatedItem.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object RelatedItem method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return relatedItemList;            
        }
        
        @Override
        public ArrayList<RelatedItem> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<RelatedItem> relatedItemList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for RelatedItem(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            RelatedItem i = (RelatedItem) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                relatedItemList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            relatedItemList = null;
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
                    getRecordsByColumnWithLimitOrOffset("related_item", RelatedItem.checkColumnName(columnName), columnValue, RelatedItem.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        relatedItemList.add(RelatedItem.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("RelatedItem's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return relatedItemList;
        } 
    
        @Override
        public int add(RelatedItem obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO related_item(RelatedItemId,Item1,Item2,) VALUES (?,?);");                    
                preparedStatement.setInt(0, obj.getRelatedItemId());
                preparedStatement.setInt(1, obj.getItem1());
                preparedStatement.setInt(2, obj.getItem2());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from related_item;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("RelatedItem's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setRelatedItemId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public RelatedItem update(RelatedItem obj)
        {
           try
            {   
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE related_item SET com.busy.util.DatabaseColumn@2d40608d=?,com.busy.util.DatabaseColumn@70ed8f5e=? WHERE RelatedItemId=?;");                    
                preparedStatement.setInt(0, obj.getRelatedItemId());
                preparedStatement.setInt(1, obj.getItem1());
                preparedStatement.setInt(2, obj.getItem2());
                preparedStatement.setInt(3, obj.getRelatedItemId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getRelatedItemId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("RelatedItem's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("related_item");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(RelatedItem related_item)
        {
              
        }
        
        @Override
        public void getRelatedObjects(RelatedItem related_item)
        {
             
        }
        
        @Override
        public boolean remove(RelatedItem obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM related_item WHERE RelatedItemId=" + obj.getRelatedItemId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("RelatedItem's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getRelatedItemId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM related_item WHERE RelatedItemId=" + id + ";");           
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
                updateQuery("DELETE FROM related_item;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("RelatedItem's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM related_item WHERE " + RelatedItem.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("RelatedItem's removeByColumn method error: " + ex.getMessage());
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
                        RelatedItem i = (RelatedItem) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getRelatedItemId());
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

