






















































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
    
    public class ItemAttributeDaoImpl extends BasicConnection implements Serializable, ItemAttributeDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public ItemAttributeDaoImpl()
        {
            cachingEnabled = false;
        }

        public ItemAttributeDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class ItemAttributeCache
        {
            public static final ConcurrentLruCache<Integer, ItemAttribute> itemAttributeCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (ItemAttribute i : findAll())
                {
                    getCache().put(i.getItemAttributeId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, ItemAttribute> getCache()
        {
            return ItemAttributeCache.itemAttributeCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, ItemAttribute> buildCache(ArrayList<ItemAttribute> itemAttributeList)
        {        
            ConcurrentLruCache<Integer, ItemAttribute> cache = new ConcurrentLruCache<Integer, ItemAttribute>(itemAttributeList.size() + 1000);
            for (ItemAttribute i : itemAttributeList)
            {
                cache.put(i.getItemAttributeId(), i);
            }
            return cache;
        }

        private static ArrayList<ItemAttribute> findAll()
        {
            ArrayList<ItemAttribute> itemAttribute = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("itemAttribute");
                while (rs.next())
                {
                    itemAttribute.add(ItemAttribute.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemAttribute object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return itemAttribute;
        }
        
        @Override
        public ItemAttribute find(Integer id)
        {
            return findByColumn("ItemAttributeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<ItemAttribute> findAll(Integer limit, Integer offset)
        {
            ArrayList<ItemAttribute> itemAttributeList = new ArrayList<ItemAttribute>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for ItemAttribute, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    itemAttributeList = new ArrayList<ItemAttribute>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("item_attribute", limit, offset);
                    while (rs.next())
                    {
                        itemAttributeList.add(ItemAttribute.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ItemAttribute object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemAttributeList;
         
        }
        
        @Override
        public ArrayList<ItemAttribute> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ItemAttribute> itemAttributeList = new ArrayList<ItemAttribute>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for ItemAttribute, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    itemAttributeList = new ArrayList<ItemAttribute>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            ItemAttribute itemAttribute = (ItemAttribute) e.getValue();

                            
                                getRecordById("ItemAttributeType", itemAttribute.getItemAttributeTypeId().toString());
                                itemAttribute.setItemAttributeType(ItemAttributeType.process(rs));               
                            
                                getRecordById("Item", itemAttribute.getItemId().toString());
                                itemAttribute.setItem(Item.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object ItemAttribute method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                itemAttributeList = new ArrayList<ItemAttribute>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("item_attribute", limit, offset);
                    while (rs.next())
                    {
                        itemAttributeList.add(ItemAttribute.process(rs));
                    }

                    
                    
                        for (ItemAttribute itemAttribute : itemAttributeList)
                        {                        
                            
                                getRecordById("ItemAttributeType", itemAttribute.getItemAttributeTypeId().toString());
                                itemAttribute.setItemAttributeType(ItemAttributeType.process(rs));               
                            
                                getRecordById("Item", itemAttribute.getItemId().toString());
                                itemAttribute.setItem(Item.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object ItemAttribute method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemAttributeList;            
        }
        
        @Override
        public ArrayList<ItemAttribute> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ItemAttribute> itemAttributeList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for ItemAttribute(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            ItemAttribute i = (ItemAttribute) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                itemAttributeList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            itemAttributeList = null;
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
                    getRecordsByColumnWithLimitOrOffset("item_attribute", ItemAttribute.checkColumnName(columnName), columnValue, ItemAttribute.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        itemAttributeList.add(ItemAttribute.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ItemAttribute's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemAttributeList;
        } 
    
        @Override
        public int add(ItemAttribute obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                ItemAttribute.checkColumnSize(obj.getKey(), 100);
                ItemAttribute.checkColumnSize(obj.getValue(), 255);
                ItemAttribute.checkColumnSize(obj.getLocale(), 10);
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO item_attribute(ItemAttributeId,Key,Value,Locale,ItemAttributeTypeId,ItemId,) VALUES (?,?,?,?,?);");                    
                preparedStatement.setInt(0, obj.getItemAttributeId());
                preparedStatement.setString(1, obj.getKey());
                preparedStatement.setString(2, obj.getValue());
                preparedStatement.setString(3, obj.getLocale());
                preparedStatement.setInt(4, obj.getItemAttributeTypeId());
                preparedStatement.setInt(5, obj.getItemId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_attribute;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemAttribute's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setItemAttributeId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public ItemAttribute update(ItemAttribute obj)
        {
           try
            {   
                
                ItemAttribute.checkColumnSize(obj.getKey(), 100);
                ItemAttribute.checkColumnSize(obj.getValue(), 255);
                ItemAttribute.checkColumnSize(obj.getLocale(), 10);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item_attribute SET com.busy.util.DatabaseColumn@604cf8b6=?,com.busy.util.DatabaseColumn@374820a1=?,com.busy.util.DatabaseColumn@56831f4b=?,com.busy.util.DatabaseColumn@2a8e5d21=?,com.busy.util.DatabaseColumn@2d80898e=? WHERE ItemAttributeId=?;");                    
                preparedStatement.setInt(0, obj.getItemAttributeId());
                preparedStatement.setString(1, obj.getKey());
                preparedStatement.setString(2, obj.getValue());
                preparedStatement.setString(3, obj.getLocale());
                preparedStatement.setInt(4, obj.getItemAttributeTypeId());
                preparedStatement.setInt(5, obj.getItemId());
                preparedStatement.setInt(6, obj.getItemAttributeId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getItemAttributeId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("ItemAttribute's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("item_attribute");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(ItemAttribute item_attribute)
        {
            
                try
                { 
                    
                            getRecordById("ItemAttributeType", item_attribute.getItemAttributeTypeId().toString());
                            item_attribute.setItemAttributeType(ItemAttributeType.process(rs));                                       
                    
                            getRecordById("Item", item_attribute.getItemId().toString());
                            item_attribute.setItem(Item.process(rs));                                       
                    
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
        public void getRelatedObjects(ItemAttribute item_attribute)
        {
             
        }
        
        @Override
        public boolean remove(ItemAttribute obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM item_attribute WHERE ItemAttributeId=" + obj.getItemAttributeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemAttribute's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getItemAttributeId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM item_attribute WHERE ItemAttributeId=" + id + ";");           
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
                updateQuery("DELETE FROM item_attribute;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemAttribute's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_attribute WHERE " + ItemAttribute.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ItemAttribute's removeByColumn method error: " + ex.getMessage());
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
                        ItemAttribute i = (ItemAttribute) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getItemAttributeId());
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

