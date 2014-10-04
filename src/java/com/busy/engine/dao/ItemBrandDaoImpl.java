






















































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
    
    public class ItemBrandDaoImpl extends BasicConnection implements Serializable, ItemBrandDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public ItemBrandDaoImpl()
        {
            cachingEnabled = false;
        }

        public ItemBrandDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class ItemBrandCache
        {
            public static final ConcurrentLruCache<Integer, ItemBrand> itemBrandCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (ItemBrand i : findAll())
                {
                    getCache().put(i.getItemBrandId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, ItemBrand> getCache()
        {
            return ItemBrandCache.itemBrandCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, ItemBrand> buildCache(ArrayList<ItemBrand> itemBrandList)
        {        
            ConcurrentLruCache<Integer, ItemBrand> cache = new ConcurrentLruCache<Integer, ItemBrand>(itemBrandList.size() + 1000);
            for (ItemBrand i : itemBrandList)
            {
                cache.put(i.getItemBrandId(), i);
            }
            return cache;
        }

        private static ArrayList<ItemBrand> findAll()
        {
            ArrayList<ItemBrand> itemBrand = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("itemBrand");
                while (rs.next())
                {
                    itemBrand.add(ItemBrand.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemBrand object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return itemBrand;
        }
        
        @Override
        public ItemBrand find(Integer id)
        {
            return findByColumn("ItemBrandId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<ItemBrand> findAll(Integer limit, Integer offset)
        {
            ArrayList<ItemBrand> itemBrandList = new ArrayList<ItemBrand>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for ItemBrand, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    itemBrandList = new ArrayList<ItemBrand>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("item_brand", limit, offset);
                    while (rs.next())
                    {
                        itemBrandList.add(ItemBrand.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ItemBrand object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemBrandList;
         
        }
        
        @Override
        public ArrayList<ItemBrand> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ItemBrand> itemBrandList = new ArrayList<ItemBrand>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for ItemBrand, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    itemBrandList = new ArrayList<ItemBrand>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                itemBrandList = new ArrayList<ItemBrand>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("item_brand", limit, offset);
                    while (rs.next())
                    {
                        itemBrandList.add(ItemBrand.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object ItemBrand method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemBrandList;            
        }
        
        @Override
        public ArrayList<ItemBrand> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ItemBrand> itemBrandList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for ItemBrand(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            ItemBrand i = (ItemBrand) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                itemBrandList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            itemBrandList = null;
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
                    getRecordsByColumnWithLimitOrOffset("item_brand", ItemBrand.checkColumnName(columnName), columnValue, ItemBrand.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        itemBrandList.add(ItemBrand.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ItemBrand's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemBrandList;
        } 
    
        @Override
        public int add(ItemBrand obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                ItemBrand.checkColumnSize(obj.getBrandName(), 100);
                ItemBrand.checkColumnSize(obj.getDescription(), 65535);
                  

                openConnection();
                prepareStatement("INSERT INTO item_brand(ItemBrandId,BrandName,Description,) VALUES (?,?);");                    
                preparedStatement.setInt(0, obj.getItemBrandId());
                preparedStatement.setString(1, obj.getBrandName());
                preparedStatement.setString(2, obj.getDescription());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_brand;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemBrand's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setItemBrandId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public ItemBrand update(ItemBrand obj)
        {
           try
            {   
                
                ItemBrand.checkColumnSize(obj.getBrandName(), 100);
                ItemBrand.checkColumnSize(obj.getDescription(), 65535);
                                  
                openConnection();                           
                prepareStatement("UPDATE item_brand SET com.busy.util.DatabaseColumn@38ffe5b1=?,com.busy.util.DatabaseColumn@5b451a38=? WHERE ItemBrandId=?;");                    
                preparedStatement.setInt(0, obj.getItemBrandId());
                preparedStatement.setString(1, obj.getBrandName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setInt(3, obj.getItemBrandId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getItemBrandId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("ItemBrand's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("item_brand");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(ItemBrand item_brand)
        {
              
        }
        
        @Override
        public void getRelatedObjects(ItemBrand item_brand)
        {
            item_brand.setItemList(new ItemDaoImpl().findByColumn("ItemBrandId", item_brand.getItemBrandId().toString(),null,null));
item_brand.setUserList(new UserDaoImpl().findByColumn("ItemBrandId", item_brand.getItemBrandId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(ItemBrand obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM item_brand WHERE ItemBrandId=" + obj.getItemBrandId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemBrand's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getItemBrandId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM item_brand WHERE ItemBrandId=" + id + ";");           
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
                updateQuery("DELETE FROM item_brand;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemBrand's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_brand WHERE " + ItemBrand.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ItemBrand's removeByColumn method error: " + ex.getMessage());
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
                        ItemBrand i = (ItemBrand) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getItemBrandId());
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
        
                    
        public void getRelatedItemList(ItemBrand item_brand)
        {           
            item_brand.setItemList(new ItemDaoImpl().findByColumn("ItemBrandId", item_brand.getItemBrandId().toString(),null,null));
        }        
                    
        public void getRelatedUserList(ItemBrand item_brand)
        {           
            item_brand.setUserList(new UserDaoImpl().findByColumn("ItemBrandId", item_brand.getItemBrandId().toString(),null,null));
        }        
        
                             
    }

