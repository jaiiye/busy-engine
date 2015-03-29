






















































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
    
    public class ItemOptionDaoImpl extends BasicConnection implements Serializable, ItemOptionDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public ItemOptionDaoImpl()
        {
            cachingEnabled = false;
        }

        public ItemOptionDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class ItemOptionCache
        {
            public static final ConcurrentLruCache<Integer, ItemOption> itemOptionCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (ItemOption i : findAll())
                {
                    getCache().put(i.getItemOptionId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, ItemOption> getCache()
        {
            return ItemOptionCache.itemOptionCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, ItemOption> buildCache(ArrayList<ItemOption> itemOptionList)
        {        
            ConcurrentLruCache<Integer, ItemOption> cache = new ConcurrentLruCache<Integer, ItemOption>(itemOptionList.size() + 1000);
            for (ItemOption i : itemOptionList)
            {
                cache.put(i.getItemOptionId(), i);
            }
            return cache;
        }

        private static ArrayList<ItemOption> findAll()
        {
            ArrayList<ItemOption> itemOption = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("itemOption");
                while (rs.next())
                {
                    itemOption.add(ItemOption.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemOption object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return itemOption;
        }
        
        @Override
        public ItemOption find(Integer id)
        {
            return findByColumn("ItemOptionId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ItemOption findWithInfo(Integer id)
        {
            ItemOption itemOption = findByColumn("ItemOptionId", id.toString(), null, null).get(0);
            
            
            
            return itemOption;
        }
        
        @Override
        public ArrayList<ItemOption> findAll(Integer limit, Integer offset)
        {
            ArrayList<ItemOption> itemOptionList = new ArrayList<ItemOption>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for ItemOption, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    itemOptionList = new ArrayList<ItemOption>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("item_option", limit, offset);
                    while (rs.next())
                    {
                        itemOptionList.add(ItemOption.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ItemOption object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemOptionList;
         
        }
        
        @Override
        public ArrayList<ItemOption> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ItemOption> itemOptionList = new ArrayList<ItemOption>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for ItemOption, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    itemOptionList = new ArrayList<ItemOption>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                itemOptionList = new ArrayList<ItemOption>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("item_option", limit, offset);
                    while (rs.next())
                    {
                        itemOptionList.add(ItemOption.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object ItemOption method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemOptionList;            
        }
        
        @Override
        public ArrayList<ItemOption> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ItemOption> itemOptionList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for ItemOption(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            ItemOption i = (ItemOption) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                itemOptionList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            itemOptionList = null;
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
                    getRecordsByColumnWithLimitOrOffset("item_option", ItemOption.checkColumnName(columnName), columnValue, ItemOption.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        itemOptionList.add(ItemOption.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ItemOption's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemOptionList;
        } 
    
        @Override
        public int add(ItemOption obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                ItemOption.checkColumnSize(obj.getOptionName(), 100);
                ItemOption.checkColumnSize(obj.getDescription(), 65535);
                  

                openConnection();
                prepareStatement("INSERT INTO item_option(OptionName,Description) VALUES (?,?);");                    
                preparedStatement.setString(1, obj.getOptionName());
                preparedStatement.setString(2, obj.getDescription());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_option;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemOption's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setItemOptionId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public ItemOption update(ItemOption obj)
        {
           try
            {   
                
                ItemOption.checkColumnSize(obj.getOptionName(), 100);
                ItemOption.checkColumnSize(obj.getDescription(), 65535);
                                  
                openConnection();                           
                prepareStatement("UPDATE item_option SET OptionName=?,Description=? WHERE ItemOptionId=?;");                    
                preparedStatement.setString(1, obj.getOptionName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setInt(3, obj.getItemOptionId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getItemOptionId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("ItemOption's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("item_option");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(ItemOption item_option)
        {
              
        }
        
        @Override
        public void getRelatedObjects(ItemOption item_option)
        {
            item_option.setOptionAvailabilityList(new OptionAvailabilityDaoImpl().findByColumn("ItemOptionId", item_option.getItemOptionId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(ItemOption obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM item_option WHERE ItemOptionId=" + obj.getItemOptionId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemOption's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getItemOptionId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM item_option WHERE ItemOptionId=" + id + ";");           
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
                updateQuery("DELETE FROM item_option;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemOption's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_option WHERE " + ItemOption.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ItemOption's removeByColumn method error: " + ex.getMessage());
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
                        ItemOption i = (ItemOption) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getItemOptionId());
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
        
                    
        public void getRelatedOptionAvailabilityList(ItemOption item_option)
        {           
            item_option.setOptionAvailabilityList(new OptionAvailabilityDaoImpl().findByColumn("ItemOptionId", item_option.getItemOptionId().toString(),null,null));
        }        
        
            
        
          
        
                
          
        
    }

