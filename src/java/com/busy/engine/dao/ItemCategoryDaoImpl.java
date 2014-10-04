






















































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
    
    public class ItemCategoryDaoImpl extends BasicConnection implements Serializable, ItemCategoryDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public ItemCategoryDaoImpl()
        {
            cachingEnabled = false;
        }

        public ItemCategoryDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class ItemCategoryCache
        {
            public static final ConcurrentLruCache<Integer, ItemCategory> itemCategoryCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (ItemCategory i : findAll())
                {
                    getCache().put(i.getItemCategoryId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, ItemCategory> getCache()
        {
            return ItemCategoryCache.itemCategoryCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, ItemCategory> buildCache(ArrayList<ItemCategory> itemCategoryList)
        {        
            ConcurrentLruCache<Integer, ItemCategory> cache = new ConcurrentLruCache<Integer, ItemCategory>(itemCategoryList.size() + 1000);
            for (ItemCategory i : itemCategoryList)
            {
                cache.put(i.getItemCategoryId(), i);
            }
            return cache;
        }

        private static ArrayList<ItemCategory> findAll()
        {
            ArrayList<ItemCategory> itemCategory = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("itemCategory");
                while (rs.next())
                {
                    itemCategory.add(ItemCategory.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemCategory object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return itemCategory;
        }
        
        @Override
        public ItemCategory find(Integer id)
        {
            return findByColumn("ItemCategoryId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<ItemCategory> findAll(Integer limit, Integer offset)
        {
            ArrayList<ItemCategory> itemCategoryList = new ArrayList<ItemCategory>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for ItemCategory, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    itemCategoryList = new ArrayList<ItemCategory>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("item_category", limit, offset);
                    while (rs.next())
                    {
                        itemCategoryList.add(ItemCategory.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ItemCategory object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemCategoryList;
         
        }
        
        @Override
        public ArrayList<ItemCategory> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ItemCategory> itemCategoryList = new ArrayList<ItemCategory>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for ItemCategory, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    itemCategoryList = new ArrayList<ItemCategory>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            ItemCategory itemCategory = (ItemCategory) e.getValue();

                            
                                getRecordById("Category", itemCategory.getCategoryId().toString());
                                itemCategory.setCategory(Category.process(rs));               
                            
                                getRecordById("Item", itemCategory.getItemId().toString());
                                itemCategory.setItem(Item.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object ItemCategory method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                itemCategoryList = new ArrayList<ItemCategory>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("item_category", limit, offset);
                    while (rs.next())
                    {
                        itemCategoryList.add(ItemCategory.process(rs));
                    }

                    
                    
                        for (ItemCategory itemCategory : itemCategoryList)
                        {                        
                            
                                getRecordById("Category", itemCategory.getCategoryId().toString());
                                itemCategory.setCategory(Category.process(rs));               
                            
                                getRecordById("Item", itemCategory.getItemId().toString());
                                itemCategory.setItem(Item.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object ItemCategory method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemCategoryList;            
        }
        
        @Override
        public ArrayList<ItemCategory> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ItemCategory> itemCategoryList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for ItemCategory(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            ItemCategory i = (ItemCategory) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                itemCategoryList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            itemCategoryList = null;
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
                    getRecordsByColumnWithLimitOrOffset("item_category", ItemCategory.checkColumnName(columnName), columnValue, ItemCategory.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        itemCategoryList.add(ItemCategory.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ItemCategory's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemCategoryList;
        } 
    
        @Override
        public int add(ItemCategory obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO item_category(ItemCategoryId,CategoryId,ItemId,) VALUES (?,?);");                    
                preparedStatement.setInt(0, obj.getItemCategoryId());
                preparedStatement.setInt(1, obj.getCategoryId());
                preparedStatement.setInt(2, obj.getItemId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_category;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemCategory's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setItemCategoryId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public ItemCategory update(ItemCategory obj)
        {
           try
            {   
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item_category SET com.busy.util.DatabaseColumn@6215f9e4=?,com.busy.util.DatabaseColumn@5657045=? WHERE ItemCategoryId=?;");                    
                preparedStatement.setInt(0, obj.getItemCategoryId());
                preparedStatement.setInt(1, obj.getCategoryId());
                preparedStatement.setInt(2, obj.getItemId());
                preparedStatement.setInt(3, obj.getItemCategoryId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getItemCategoryId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("ItemCategory's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("item_category");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(ItemCategory item_category)
        {
            
                try
                { 
                    
                            getRecordById("Category", item_category.getCategoryId().toString());
                            item_category.setCategory(Category.process(rs));                                       
                    
                            getRecordById("Item", item_category.getItemId().toString());
                            item_category.setItem(Item.process(rs));                                       
                    
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
        public void getRelatedObjects(ItemCategory item_category)
        {
             
        }
        
        @Override
        public boolean remove(ItemCategory obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM item_category WHERE ItemCategoryId=" + obj.getItemCategoryId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemCategory's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getItemCategoryId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM item_category WHERE ItemCategoryId=" + id + ";");           
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
                updateQuery("DELETE FROM item_category;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemCategory's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_category WHERE " + ItemCategory.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ItemCategory's removeByColumn method error: " + ex.getMessage());
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
                        ItemCategory i = (ItemCategory) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getItemCategoryId());
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

