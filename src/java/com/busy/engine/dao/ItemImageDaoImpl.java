
























































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
    
    public class ItemImageDaoImpl extends BasicConnection implements Serializable, ItemImageDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public ItemImageDaoImpl()
        {
            cachingEnabled = false;
        }

        public ItemImageDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class ItemImageCache
        {
            public static final ConcurrentLruCache<Integer, ItemImage> itemImageCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (ItemImage i : findAll())
                {
                    getCache().put(i.getItemImageId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, ItemImage> getCache()
        {
            return ItemImageCache.itemImageCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, ItemImage> buildCache(ArrayList<ItemImage> itemImageList)
        {        
            ConcurrentLruCache<Integer, ItemImage> cache = new ConcurrentLruCache<Integer, ItemImage>(itemImageList.size() + 1000);
            for (ItemImage i : itemImageList)
            {
                cache.put(i.getItemImageId(), i);
            }
            return cache;
        }

        private static ArrayList<ItemImage> findAll()
        {
            ArrayList<ItemImage> itemImage = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("itemImage");
                while (rs.next())
                {
                    itemImage.add(ItemImage.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemImage object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return itemImage;
        }
        
        @Override
        public ItemImage find(Integer id)
        {
            return findByColumn("ItemImageId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ItemImage findWithInfo(Integer id)
        {
            ItemImage itemImage = findByColumn("ItemImageId", id.toString(), null, null).get(0);
            
            
                try
                {

                
                    getRecordById("item", itemImage.getItemId().toString());
                    itemImage.setItem(Item.process(rs));               
                  

                }
                catch (SQLException ex)
                {
                        System.out.println("Object ItemImage method findWithInfo(Integer id) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            
            
            return itemImage;
        }
        
        @Override
        public ArrayList<ItemImage> findAll(Integer limit, Integer offset)
        {
            ArrayList<ItemImage> itemImageList = new ArrayList<ItemImage>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for ItemImage, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    itemImageList = new ArrayList<ItemImage>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("item_image", limit, offset);
                    while (rs.next())
                    {
                        itemImageList.add(ItemImage.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ItemImage object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemImageList;
         
        }
        
        @Override
        public ArrayList<ItemImage> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ItemImage> itemImageList = new ArrayList<ItemImage>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for ItemImage, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    itemImageList = new ArrayList<ItemImage>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            ItemImage itemImage = (ItemImage) e.getValue();

                            
                                getRecordById("item", itemImage.getItemId().toString());
                                itemImage.setItem(Item.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object ItemImage method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                itemImageList = new ArrayList<ItemImage>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("item_image", limit, offset);
                    while (rs.next())
                    {
                        itemImageList.add(ItemImage.process(rs));
                    }

                    
                    
                        for (ItemImage itemImage : itemImageList)
                        {                        
                            
                                getRecordById("item", itemImage.getItemId().toString());
                                itemImage.setItem(Item.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object ItemImage method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemImageList;            
        }
        
        @Override
        public ArrayList<ItemImage> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ItemImage> itemImageList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for ItemImage(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            ItemImage i = (ItemImage) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                itemImageList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            itemImageList = null;
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
                    getRecordsByColumnWithLimitOrOffset("item_image", ItemImage.checkColumnName(columnName), columnValue, ItemImage.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        itemImageList.add(ItemImage.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ItemImage's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemImageList;
        } 
        
        @Override
        public ArrayList<ItemImage> findByColumns(Column... columns)
        {
            ArrayList<ItemImage> itemImageList = new ArrayList<>();

            try
            {
                //make sure the correct isNumeric values are set for columns
                for(Column c : columns) 
                {
                    c.setNumeric(ItemImage.isColumnNumeric(c.getColumnName()));                
                }

                getAllRecordsByColumns("item_image", columns);
                while (rs.next())
                {
                    itemImageList.add(ItemImage.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemImage's method findByColumns(Column... columns) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }

            return itemImageList;
        }
    
        @Override
        public int add(ItemImage obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                ItemImage.checkColumnSize(obj.getImageName(), 255);
                ItemImage.checkColumnSize(obj.getThumbnailName(), 255);
                ItemImage.checkColumnSize(obj.getAlternateText(), 255);
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO item_image(ImageName,ThumbnailName,AlternateText,Rank,ItemId) VALUES (?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getImageName());
                preparedStatement.setString(2, obj.getThumbnailName());
                preparedStatement.setString(3, obj.getAlternateText());
                preparedStatement.setInt(4, obj.getRank());
                preparedStatement.setInt(5, obj.getItemId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_image;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemImage's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setItemImageId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public ItemImage update(ItemImage obj)
        {
           try
            {   
                
                ItemImage.checkColumnSize(obj.getImageName(), 255);
                ItemImage.checkColumnSize(obj.getThumbnailName(), 255);
                ItemImage.checkColumnSize(obj.getAlternateText(), 255);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item_image SET ImageName=?,ThumbnailName=?,AlternateText=?,Rank=?,ItemId=? WHERE ItemImageId=?;");                    
                preparedStatement.setString(1, obj.getImageName());
                preparedStatement.setString(2, obj.getThumbnailName());
                preparedStatement.setString(3, obj.getAlternateText());
                preparedStatement.setInt(4, obj.getRank());
                preparedStatement.setInt(5, obj.getItemId());
                preparedStatement.setInt(6, obj.getItemImageId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getItemImageId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("ItemImage's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("item_image");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(ItemImage item_image)
        {
            
                try
                { 
                    
                            getRecordById("item", item_image.getItemId().toString());
                            item_image.setItem(Item.process(rs));                                       
                    
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
        public void getRelatedObjects(ItemImage item_image)
        {
             
        }
        
        @Override
        public boolean remove(ItemImage obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM item_image WHERE ItemImageId=" + obj.getItemImageId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemImage's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getItemImageId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM item_image WHERE ItemImageId=" + id + ";");           
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
                updateQuery("DELETE FROM item_image;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemImage's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_image WHERE " + ItemImage.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ItemImage's removeByColumn method error: " + ex.getMessage());
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
                        ItemImage i = (ItemImage) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getItemImageId());
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
        
        
            
        
        
        public void getRelatedItem(ItemImage item_image)
        {            
            try
            {                 
                getRecordById("Item", item_image.getItemId().toString());
                item_image.setItem(Item.process(rs));                                                       
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
          
        
                
        
        public void getRelatedItemWithInfo(ItemImage item_image)
        {            
            item_image.setItem(new ItemDaoImpl().findWithInfo(item_image.getItemId()));
        }
          
        
    }

