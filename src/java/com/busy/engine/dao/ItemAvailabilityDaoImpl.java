






















































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
    
    public class ItemAvailabilityDaoImpl extends BasicConnection implements Serializable, ItemAvailabilityDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public ItemAvailabilityDaoImpl()
        {
            cachingEnabled = false;
        }

        public ItemAvailabilityDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class ItemAvailabilityCache
        {
            public static final ConcurrentLruCache<Integer, ItemAvailability> itemAvailabilityCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (ItemAvailability i : findAll())
                {
                    getCache().put(i.getItemAvailabilityId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, ItemAvailability> getCache()
        {
            return ItemAvailabilityCache.itemAvailabilityCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, ItemAvailability> buildCache(ArrayList<ItemAvailability> itemAvailabilityList)
        {        
            ConcurrentLruCache<Integer, ItemAvailability> cache = new ConcurrentLruCache<Integer, ItemAvailability>(itemAvailabilityList.size() + 1000);
            for (ItemAvailability i : itemAvailabilityList)
            {
                cache.put(i.getItemAvailabilityId(), i);
            }
            return cache;
        }

        private static ArrayList<ItemAvailability> findAll()
        {
            ArrayList<ItemAvailability> itemAvailability = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("itemAvailability");
                while (rs.next())
                {
                    itemAvailability.add(ItemAvailability.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemAvailability object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return itemAvailability;
        }
        
        @Override
        public ItemAvailability find(Integer id)
        {
            return findByColumn("ItemAvailabilityId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ItemAvailability findWithInfo(Integer id)
        {
            ItemAvailability itemAvailability = findByColumn("ItemAvailabilityId", id.toString(), null, null).get(0);
            
            
            
            return itemAvailability;
        }
        
        @Override
        public ArrayList<ItemAvailability> findAll(Integer limit, Integer offset)
        {
            ArrayList<ItemAvailability> itemAvailabilityList = new ArrayList<ItemAvailability>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for ItemAvailability, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    itemAvailabilityList = new ArrayList<ItemAvailability>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("item_availability", limit, offset);
                    while (rs.next())
                    {
                        itemAvailabilityList.add(ItemAvailability.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ItemAvailability object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemAvailabilityList;
         
        }
        
        @Override
        public ArrayList<ItemAvailability> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ItemAvailability> itemAvailabilityList = new ArrayList<ItemAvailability>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for ItemAvailability, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    itemAvailabilityList = new ArrayList<ItemAvailability>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                itemAvailabilityList = new ArrayList<ItemAvailability>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("item_availability", limit, offset);
                    while (rs.next())
                    {
                        itemAvailabilityList.add(ItemAvailability.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object ItemAvailability method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemAvailabilityList;            
        }
        
        @Override
        public ArrayList<ItemAvailability> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ItemAvailability> itemAvailabilityList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for ItemAvailability(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            ItemAvailability i = (ItemAvailability) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                itemAvailabilityList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            itemAvailabilityList = null;
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
                    getRecordsByColumnWithLimitOrOffset("item_availability", ItemAvailability.checkColumnName(columnName), columnValue, ItemAvailability.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        itemAvailabilityList.add(ItemAvailability.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ItemAvailability's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemAvailabilityList;
        } 
    
        @Override
        public int add(ItemAvailability obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                ItemAvailability.checkColumnSize(obj.getType(), 45);
                  

                openConnection();
                prepareStatement("INSERT INTO item_availability(Type) VALUES (?);");                    
                preparedStatement.setString(1, obj.getType());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_availability;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemAvailability's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setItemAvailabilityId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public ItemAvailability update(ItemAvailability obj)
        {
           try
            {   
                
                ItemAvailability.checkColumnSize(obj.getType(), 45);
                                  
                openConnection();                           
                prepareStatement("UPDATE item_availability SET Type=? WHERE ItemAvailabilityId=?;");                    
                preparedStatement.setString(1, obj.getType());
                preparedStatement.setInt(2, obj.getItemAvailabilityId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getItemAvailabilityId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("ItemAvailability's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("item_availability");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(ItemAvailability item_availability)
        {
              
        }
        
        @Override
        public void getRelatedObjects(ItemAvailability item_availability)
        {
            item_availability.setOptionAvailabilityList(new OptionAvailabilityDaoImpl().findByColumn("ItemAvailabilityId", item_availability.getItemAvailabilityId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(ItemAvailability obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM item_availability WHERE ItemAvailabilityId=" + obj.getItemAvailabilityId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemAvailability's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getItemAvailabilityId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM item_availability WHERE ItemAvailabilityId=" + id + ";");           
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
                updateQuery("DELETE FROM item_availability;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemAvailability's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_availability WHERE " + ItemAvailability.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ItemAvailability's removeByColumn method error: " + ex.getMessage());
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
                        ItemAvailability i = (ItemAvailability) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getItemAvailabilityId());
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
        
                    
        public void getRelatedOptionAvailabilityList(ItemAvailability item_availability)
        {           
            item_availability.setOptionAvailabilityList(new OptionAvailabilityDaoImpl().findByColumn("ItemAvailabilityId", item_availability.getItemAvailabilityId().toString(),null,null));
        }        
        
            
        
          
        
                
          
        
    }

