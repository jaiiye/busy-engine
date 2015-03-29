






















































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
    
    public class ItemDiscountDaoImpl extends BasicConnection implements Serializable, ItemDiscountDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public ItemDiscountDaoImpl()
        {
            cachingEnabled = false;
        }

        public ItemDiscountDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class ItemDiscountCache
        {
            public static final ConcurrentLruCache<Integer, ItemDiscount> itemDiscountCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (ItemDiscount i : findAll())
                {
                    getCache().put(i.getItemDiscountId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, ItemDiscount> getCache()
        {
            return ItemDiscountCache.itemDiscountCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, ItemDiscount> buildCache(ArrayList<ItemDiscount> itemDiscountList)
        {        
            ConcurrentLruCache<Integer, ItemDiscount> cache = new ConcurrentLruCache<Integer, ItemDiscount>(itemDiscountList.size() + 1000);
            for (ItemDiscount i : itemDiscountList)
            {
                cache.put(i.getItemDiscountId(), i);
            }
            return cache;
        }

        private static ArrayList<ItemDiscount> findAll()
        {
            ArrayList<ItemDiscount> itemDiscount = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("itemDiscount");
                while (rs.next())
                {
                    itemDiscount.add(ItemDiscount.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemDiscount object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return itemDiscount;
        }
        
        @Override
        public ItemDiscount find(Integer id)
        {
            return findByColumn("ItemDiscountId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ItemDiscount findWithInfo(Integer id)
        {
            ItemDiscount itemDiscount = findByColumn("ItemDiscountId", id.toString(), null, null).get(0);
            
            
                try
                {

                
                    getRecordById("Item", itemDiscount.getItemId().toString());
                    itemDiscount.setItem(Item.process(rs));               
                
                    getRecordById("Discount", itemDiscount.getDiscountId().toString());
                    itemDiscount.setDiscount(Discount.process(rs));               
                  

                }
                catch (SQLException ex)
                {
                        System.out.println("Object ItemDiscount method findWithInfo(Integer id) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            
            
            return itemDiscount;
        }
        
        @Override
        public ArrayList<ItemDiscount> findAll(Integer limit, Integer offset)
        {
            ArrayList<ItemDiscount> itemDiscountList = new ArrayList<ItemDiscount>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for ItemDiscount, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    itemDiscountList = new ArrayList<ItemDiscount>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("item_discount", limit, offset);
                    while (rs.next())
                    {
                        itemDiscountList.add(ItemDiscount.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ItemDiscount object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemDiscountList;
         
        }
        
        @Override
        public ArrayList<ItemDiscount> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ItemDiscount> itemDiscountList = new ArrayList<ItemDiscount>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for ItemDiscount, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    itemDiscountList = new ArrayList<ItemDiscount>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            ItemDiscount itemDiscount = (ItemDiscount) e.getValue();

                            
                                getRecordById("Item", itemDiscount.getItemId().toString());
                                itemDiscount.setItem(Item.process(rs));               
                            
                                getRecordById("Discount", itemDiscount.getDiscountId().toString());
                                itemDiscount.setDiscount(Discount.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object ItemDiscount method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                itemDiscountList = new ArrayList<ItemDiscount>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("item_discount", limit, offset);
                    while (rs.next())
                    {
                        itemDiscountList.add(ItemDiscount.process(rs));
                    }

                    
                    
                        for (ItemDiscount itemDiscount : itemDiscountList)
                        {                        
                            
                                getRecordById("Item", itemDiscount.getItemId().toString());
                                itemDiscount.setItem(Item.process(rs));               
                            
                                getRecordById("Discount", itemDiscount.getDiscountId().toString());
                                itemDiscount.setDiscount(Discount.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object ItemDiscount method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemDiscountList;            
        }
        
        @Override
        public ArrayList<ItemDiscount> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ItemDiscount> itemDiscountList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for ItemDiscount(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            ItemDiscount i = (ItemDiscount) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                itemDiscountList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            itemDiscountList = null;
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
                    getRecordsByColumnWithLimitOrOffset("item_discount", ItemDiscount.checkColumnName(columnName), columnValue, ItemDiscount.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        itemDiscountList.add(ItemDiscount.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ItemDiscount's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemDiscountList;
        } 
    
        @Override
        public int add(ItemDiscount obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO item_discount(ItemId,DiscountId,ApplyToOptions) VALUES (?,?,?);");                    
                preparedStatement.setInt(1, obj.getItemId());
                preparedStatement.setInt(2, obj.getDiscountId());
                preparedStatement.setInt(3, obj.getApplyToOptions());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_discount;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemDiscount's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setItemDiscountId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public ItemDiscount update(ItemDiscount obj)
        {
           try
            {   
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item_discount SET ItemId=?,DiscountId=?,ApplyToOptions=? WHERE ItemDiscountId=?;");                    
                preparedStatement.setInt(1, obj.getItemId());
                preparedStatement.setInt(2, obj.getDiscountId());
                preparedStatement.setInt(3, obj.getApplyToOptions());
                preparedStatement.setInt(4, obj.getItemDiscountId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getItemDiscountId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("ItemDiscount's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("item_discount");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(ItemDiscount item_discount)
        {
            
                try
                { 
                    
                            getRecordById("Item", item_discount.getItemId().toString());
                            item_discount.setItem(Item.process(rs));                                       
                    
                            getRecordById("Discount", item_discount.getDiscountId().toString());
                            item_discount.setDiscount(Discount.process(rs));                                       
                    
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
        public void getRelatedObjects(ItemDiscount item_discount)
        {
             
        }
        
        @Override
        public boolean remove(ItemDiscount obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM item_discount WHERE ItemDiscountId=" + obj.getItemDiscountId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemDiscount's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getItemDiscountId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM item_discount WHERE ItemDiscountId=" + id + ";");           
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
                updateQuery("DELETE FROM item_discount;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemDiscount's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_discount WHERE " + ItemDiscount.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ItemDiscount's removeByColumn method error: " + ex.getMessage());
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
                        ItemDiscount i = (ItemDiscount) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getItemDiscountId());
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
        
        
            
        
        
        public void getRelatedItem(ItemDiscount item_discount)
        {            
            try
            {                 
                getRecordById("Item", item_discount.getItemId().toString());
                item_discount.setItem(Item.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getItem error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
        
        public void getRelatedDiscount(ItemDiscount item_discount)
        {            
            try
            {                 
                getRecordById("Discount", item_discount.getDiscountId().toString());
                item_discount.setDiscount(Discount.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getDiscount error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
          
        
                
        
        public void getRelatedItemWithInfo(ItemDiscount item_discount)
        {            
            item_discount.setItem(new ItemDaoImpl().findWithInfo(item_discount.getItemId()));
        }
        
        public void getRelatedDiscountWithInfo(ItemDiscount item_discount)
        {            
            item_discount.setDiscount(new DiscountDaoImpl().findWithInfo(item_discount.getDiscountId()));
        }
          
        
    }

