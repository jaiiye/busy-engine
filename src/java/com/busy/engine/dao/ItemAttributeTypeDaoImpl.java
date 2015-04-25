
























































    package com.busy.engine.dao;

    import com.busy.engine.data.BasicConnection;
    import com.busy.engine.data.Column;
    import com.busy.engine.entity.*;
    import com.busy.engine.util.*;
    import java.util.ArrayList;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.Date;
    import java.util.Map.Entry;
    import java.lang.reflect.InvocationTargetException;
    
    public class ItemAttributeTypeDaoImpl extends BasicConnection implements Serializable, ItemAttributeTypeDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public ItemAttributeTypeDaoImpl()
        {
            cachingEnabled = false;
        }

        public ItemAttributeTypeDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class ItemAttributeTypeCache
        {
            public static final ConcurrentLruCache<Integer, ItemAttributeType> itemAttributeTypeCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (ItemAttributeType i : findAll())
                {
                    getCache().put(i.getItemAttributeTypeId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, ItemAttributeType> getCache()
        {
            return ItemAttributeTypeCache.itemAttributeTypeCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, ItemAttributeType> buildCache(ArrayList<ItemAttributeType> itemAttributeTypeList)
        {        
            ConcurrentLruCache<Integer, ItemAttributeType> cache = new ConcurrentLruCache<Integer, ItemAttributeType>(itemAttributeTypeList.size() + 1000);
            for (ItemAttributeType i : itemAttributeTypeList)
            {
                cache.put(i.getItemAttributeTypeId(), i);
            }
            return cache;
        }

        private static ArrayList<ItemAttributeType> findAll()
        {
            ArrayList<ItemAttributeType> itemAttributeType = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("itemAttributeType");
                while (rs.next())
                {
                    itemAttributeType.add(ItemAttributeType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemAttributeType object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return itemAttributeType;
        }
        
        @Override
        public ItemAttributeType find(Integer id)
        {
            return findByColumn("ItemAttributeTypeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ItemAttributeType findWithInfo(Integer id)
        {
            ItemAttributeType itemAttributeType = findByColumn("ItemAttributeTypeId", id.toString(), null, null).get(0);
            
            
            
            return itemAttributeType;
        }
        
        @Override
        public ArrayList<ItemAttributeType> findAll(Integer limit, Integer offset)
        {
            ArrayList<ItemAttributeType> itemAttributeTypeList = new ArrayList<ItemAttributeType>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for ItemAttributeType, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    itemAttributeTypeList = new ArrayList<ItemAttributeType>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("item_attribute_type", limit, offset);
                    while (rs.next())
                    {
                        itemAttributeTypeList.add(ItemAttributeType.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ItemAttributeType object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemAttributeTypeList;
         
        }
        
        @Override
        public ArrayList<ItemAttributeType> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ItemAttributeType> itemAttributeTypeList = new ArrayList<ItemAttributeType>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for ItemAttributeType, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    itemAttributeTypeList = new ArrayList<ItemAttributeType>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                itemAttributeTypeList = new ArrayList<ItemAttributeType>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("item_attribute_type", limit, offset);
                    while (rs.next())
                    {
                        itemAttributeTypeList.add(ItemAttributeType.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object ItemAttributeType method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemAttributeTypeList;            
        }
        
        @Override
        public ArrayList<ItemAttributeType> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ItemAttributeType> itemAttributeTypeList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for ItemAttributeType(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            ItemAttributeType i = (ItemAttributeType) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                itemAttributeTypeList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            itemAttributeTypeList = null;
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
                    getRecordsByColumnWithLimitOrOffset("item_attribute_type", ItemAttributeType.checkColumnName(columnName), columnValue, ItemAttributeType.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        itemAttributeTypeList.add(ItemAttributeType.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ItemAttributeType's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemAttributeTypeList;
        } 
        
        @Override
        public ArrayList<ItemAttributeType> findByColumns(Column... columns)
        {
            ArrayList<ItemAttributeType> itemAttributeTypeList = new ArrayList<>();

            try
            {
                //make sure the correct isNumeric values are set for columns
                for(Column c : columns) 
                {
                    c.setNumeric(ItemAttributeType.isColumnNumeric(c.getColumnName()));                
                }

                getAllRecordsByColumns("item_attribute_type", columns);
                while (rs.next())
                {
                    itemAttributeTypeList.add(ItemAttributeType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemAttributeType's method findByColumns(Column... columns) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }

            return itemAttributeTypeList;
        }
    
        @Override
        public int add(ItemAttributeType obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                ItemAttributeType.checkColumnSize(obj.getAttributeName(), 45);
                ItemAttributeType.checkColumnSize(obj.getDescription(), 255);
                  

                openConnection();
                prepareStatement("INSERT INTO item_attribute_type(AttributeName,Description) VALUES (?,?);");                    
                preparedStatement.setString(1, obj.getAttributeName());
                preparedStatement.setString(2, obj.getDescription());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_attribute_type;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemAttributeType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setItemAttributeTypeId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public ItemAttributeType update(ItemAttributeType obj)
        {
           try
            {   
                
                ItemAttributeType.checkColumnSize(obj.getAttributeName(), 45);
                ItemAttributeType.checkColumnSize(obj.getDescription(), 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE item_attribute_type SET AttributeName=?,Description=? WHERE ItemAttributeTypeId=?;");                    
                preparedStatement.setString(1, obj.getAttributeName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setInt(3, obj.getItemAttributeTypeId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getItemAttributeTypeId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("ItemAttributeType's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("item_attribute_type");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(ItemAttributeType item_attribute_type)
        {
              
        }
        
        @Override
        public void getRelatedObjects(ItemAttributeType item_attribute_type)
        {
            item_attribute_type.setItemAttributeList(new ItemAttributeDaoImpl().findByColumn("ItemAttributeTypeId", item_attribute_type.getItemAttributeTypeId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(ItemAttributeType obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM item_attribute_type WHERE ItemAttributeTypeId=" + obj.getItemAttributeTypeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemAttributeType's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getItemAttributeTypeId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM item_attribute_type WHERE ItemAttributeTypeId=" + id + ";");           
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
                updateQuery("DELETE FROM item_attribute_type;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemAttributeType's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_attribute_type WHERE " + ItemAttributeType.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ItemAttributeType's removeByColumn method error: " + ex.getMessage());
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
                        ItemAttributeType i = (ItemAttributeType) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getItemAttributeTypeId());
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
        
                    
        public void getRelatedItemAttributeList(ItemAttributeType item_attribute_type)
        {           
            item_attribute_type.setItemAttributeList(new ItemAttributeDaoImpl().findByColumn("ItemAttributeTypeId", item_attribute_type.getItemAttributeTypeId().toString(),null,null));
        }        
        
            
        
          
        
                
          
        
    }

