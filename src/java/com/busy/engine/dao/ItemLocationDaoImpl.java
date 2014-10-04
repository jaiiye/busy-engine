






















































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
    
    public class ItemLocationDaoImpl extends BasicConnection implements Serializable, ItemLocationDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public ItemLocationDaoImpl()
        {
            cachingEnabled = false;
        }

        public ItemLocationDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class ItemLocationCache
        {
            public static final ConcurrentLruCache<Integer, ItemLocation> itemLocationCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (ItemLocation i : findAll())
                {
                    getCache().put(i.getItemLocationId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, ItemLocation> getCache()
        {
            return ItemLocationCache.itemLocationCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, ItemLocation> buildCache(ArrayList<ItemLocation> itemLocationList)
        {        
            ConcurrentLruCache<Integer, ItemLocation> cache = new ConcurrentLruCache<Integer, ItemLocation>(itemLocationList.size() + 1000);
            for (ItemLocation i : itemLocationList)
            {
                cache.put(i.getItemLocationId(), i);
            }
            return cache;
        }

        private static ArrayList<ItemLocation> findAll()
        {
            ArrayList<ItemLocation> itemLocation = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("itemLocation");
                while (rs.next())
                {
                    itemLocation.add(ItemLocation.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemLocation object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return itemLocation;
        }
        
        @Override
        public ItemLocation find(Integer id)
        {
            return findByColumn("ItemLocationId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<ItemLocation> findAll(Integer limit, Integer offset)
        {
            ArrayList<ItemLocation> itemLocationList = new ArrayList<ItemLocation>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for ItemLocation, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    itemLocationList = new ArrayList<ItemLocation>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("item_location", limit, offset);
                    while (rs.next())
                    {
                        itemLocationList.add(ItemLocation.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ItemLocation object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemLocationList;
         
        }
        
        @Override
        public ArrayList<ItemLocation> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ItemLocation> itemLocationList = new ArrayList<ItemLocation>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for ItemLocation, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    itemLocationList = new ArrayList<ItemLocation>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            ItemLocation itemLocation = (ItemLocation) e.getValue();

                            
                                getRecordById("Item", itemLocation.getItemId().toString());
                                itemLocation.setItem(Item.process(rs));               
                            
                                getRecordById("Address", itemLocation.getAddressId().toString());
                                itemLocation.setAddress(Address.process(rs));               
                            
                                getRecordById("Contact", itemLocation.getContactId().toString());
                                itemLocation.setContact(Contact.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object ItemLocation method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                itemLocationList = new ArrayList<ItemLocation>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("item_location", limit, offset);
                    while (rs.next())
                    {
                        itemLocationList.add(ItemLocation.process(rs));
                    }

                    
                    
                        for (ItemLocation itemLocation : itemLocationList)
                        {                        
                            
                                getRecordById("Item", itemLocation.getItemId().toString());
                                itemLocation.setItem(Item.process(rs));               
                            
                                getRecordById("Address", itemLocation.getAddressId().toString());
                                itemLocation.setAddress(Address.process(rs));               
                            
                                getRecordById("Contact", itemLocation.getContactId().toString());
                                itemLocation.setContact(Contact.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object ItemLocation method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemLocationList;            
        }
        
        @Override
        public ArrayList<ItemLocation> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ItemLocation> itemLocationList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for ItemLocation(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            ItemLocation i = (ItemLocation) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                itemLocationList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            itemLocationList = null;
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
                    getRecordsByColumnWithLimitOrOffset("item_location", ItemLocation.checkColumnName(columnName), columnValue, ItemLocation.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        itemLocationList.add(ItemLocation.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ItemLocation's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemLocationList;
        } 
    
        @Override
        public int add(ItemLocation obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                ItemLocation.checkColumnSize(obj.getLatitude(), 20);
                ItemLocation.checkColumnSize(obj.getLongitude(), 20);
                
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO item_location(ItemLocationId,Latitude,Longitude,ItemId,AddressId,ContactId,) VALUES (?,?,?,?,?);");                    
                preparedStatement.setInt(0, obj.getItemLocationId());
                preparedStatement.setString(1, obj.getLatitude());
                preparedStatement.setString(2, obj.getLongitude());
                preparedStatement.setInt(3, obj.getItemId());
                preparedStatement.setInt(4, obj.getAddressId());
                preparedStatement.setInt(5, obj.getContactId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_location;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemLocation's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setItemLocationId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public ItemLocation update(ItemLocation obj)
        {
           try
            {   
                
                ItemLocation.checkColumnSize(obj.getLatitude(), 20);
                ItemLocation.checkColumnSize(obj.getLongitude(), 20);
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item_location SET com.busy.util.DatabaseColumn@2487657e=?,com.busy.util.DatabaseColumn@74782e8a=?,com.busy.util.DatabaseColumn@5a8ecbc7=?,com.busy.util.DatabaseColumn@144c67cf=?,com.busy.util.DatabaseColumn@b7f4cad=? WHERE ItemLocationId=?;");                    
                preparedStatement.setInt(0, obj.getItemLocationId());
                preparedStatement.setString(1, obj.getLatitude());
                preparedStatement.setString(2, obj.getLongitude());
                preparedStatement.setInt(3, obj.getItemId());
                preparedStatement.setInt(4, obj.getAddressId());
                preparedStatement.setInt(5, obj.getContactId());
                preparedStatement.setInt(6, obj.getItemLocationId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getItemLocationId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("ItemLocation's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("item_location");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(ItemLocation item_location)
        {
            
                try
                { 
                    
                            getRecordById("Item", item_location.getItemId().toString());
                            item_location.setItem(Item.process(rs));                                       
                    
                            getRecordById("Address", item_location.getAddressId().toString());
                            item_location.setAddress(Address.process(rs));                                       
                    
                            getRecordById("Contact", item_location.getContactId().toString());
                            item_location.setContact(Contact.process(rs));                                       
                    
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
        public void getRelatedObjects(ItemLocation item_location)
        {
             
        }
        
        @Override
        public boolean remove(ItemLocation obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM item_location WHERE ItemLocationId=" + obj.getItemLocationId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemLocation's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getItemLocationId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM item_location WHERE ItemLocationId=" + id + ";");           
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
                updateQuery("DELETE FROM item_location;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemLocation's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_location WHERE " + ItemLocation.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ItemLocation's removeByColumn method error: " + ex.getMessage());
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
                        ItemLocation i = (ItemLocation) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getItemLocationId());
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

