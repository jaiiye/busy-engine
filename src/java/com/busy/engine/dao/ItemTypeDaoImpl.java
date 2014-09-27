






















































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
    
    public class ItemTypeDaoImpl extends BasicConnection implements Serializable, ItemTypeDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public ItemTypeDaoImpl()
        {
            cachingEnabled = false;
        }

        public ItemTypeDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class ItemTypeCache
        {
            public static final ConcurrentLruCache<Integer, ItemType> itemTypeCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (ItemType i : findAll())
                {
                    getCache().put(i.getItemTypeId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, ItemType> getCache()
        {
            return ItemTypeCache.itemTypeCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, ItemType> buildCache(ArrayList<ItemType> itemTypeList)
        {        
            ConcurrentLruCache<Integer, ItemType> cache = new ConcurrentLruCache<Integer, ItemType>(itemTypeList.size() + 1000);
            for (ItemType i : itemTypeList)
            {
                cache.put(i.getItemTypeId(), i);
            }
            return cache;
        }

        private static ArrayList<ItemType> findAll()
        {
            ArrayList<ItemType> itemType = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("itemType");
                while (rs.next())
                {
                    itemType.add(ItemType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemType object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return itemType;
        }
        
        @Override
        public ItemType find(Integer id)
        {
            return findByColumn("ItemTypeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<ItemType> findAll(Integer limit, Integer offset)
        {
            ArrayList<ItemType> itemTypeList = new ArrayList<ItemType>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for ItemType, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    itemTypeList = new ArrayList<ItemType>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("item_type", limit, offset);
                    while (rs.next())
                    {
                        itemTypeList.add(ItemType.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ItemType object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemTypeList;
         
        }
        
        @Override
        public ArrayList<ItemType> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ItemType> itemTypeList = new ArrayList<ItemType>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for ItemType, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    itemTypeList = new ArrayList<ItemType>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                itemTypeList = new ArrayList<ItemType>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("item_type", limit, offset);
                    while (rs.next())
                    {
                        itemTypeList.add(ItemType.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object ItemType method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemTypeList;            
        }
        
        @Override
        public ArrayList<ItemType> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ItemType> itemTypeList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for ItemType(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            ItemType i = (ItemType) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                itemTypeList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            itemTypeList = null;
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
                    getRecordsByColumnWithLimitOrOffset("item_type", ItemType.checkColumnName(columnName), columnValue, ItemType.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        itemTypeList.add(ItemType.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ItemType's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemTypeList;
        } 
    
        @Override
        public int add(ItemType obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                ItemType.checkColumnSize(obj.getTypeName(), 100);
                
                  

                openConnection();
                prepareStatement("INSERT INTO item_type(ItemTypeId,TypeName,Rank,) VALUES (?,?);");                    
                preparedStatement.setInt(0, obj.getItemTypeId());
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setInt(2, obj.getRank());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_type;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setItemTypeId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public ItemType update(ItemType obj)
        {
           try
            {   
                
                ItemType.checkColumnSize(obj.getTypeName(), 100);
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item_type SET com.busy.util.DatabaseColumn@b04d408=?,com.busy.util.DatabaseColumn@529e97b=? WHERE ItemTypeId=?;");                    
                preparedStatement.setInt(0, obj.getItemTypeId());
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setInt(2, obj.getRank());
                preparedStatement.setInt(3, obj.getItemTypeId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getItemTypeId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("ItemType's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("item_type");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(ItemType item_type)
        {
              
        }
        
        @Override
        public void getRelatedObjects(ItemType item_type)
        {
            item_type.setItemList(new ItemDaoImpl().findByColumn("ItemTypeId", item_type.getItemTypeId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(ItemType obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM item_type WHERE ItemTypeId=" + obj.getItemTypeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemType's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getItemTypeId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM item_type WHERE ItemTypeId=" + id + ";");           
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
                updateQuery("DELETE FROM item_type;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemType's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_type WHERE " + ItemType.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ItemType's removeByColumn method error: " + ex.getMessage());
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
                        ItemType i = (ItemType) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getItemTypeId());
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
        
                    
        public void getRelatedItemList(ItemType item_type)
        {           
            item_type.setItemList(new ItemDaoImpl().findByColumn("ItemTypeId", item_type.getItemTypeId().toString(),null,null));
        }        
        
                             
    }

