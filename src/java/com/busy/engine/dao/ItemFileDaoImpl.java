






















































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
    
    public class ItemFileDaoImpl extends BasicConnection implements Serializable, ItemFileDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public ItemFileDaoImpl()
        {
            cachingEnabled = false;
        }

        public ItemFileDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class ItemFileCache
        {
            public static final ConcurrentLruCache<Integer, ItemFile> itemFileCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (ItemFile i : findAll())
                {
                    getCache().put(i.getItemFileId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, ItemFile> getCache()
        {
            return ItemFileCache.itemFileCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, ItemFile> buildCache(ArrayList<ItemFile> itemFileList)
        {        
            ConcurrentLruCache<Integer, ItemFile> cache = new ConcurrentLruCache<Integer, ItemFile>(itemFileList.size() + 1000);
            for (ItemFile i : itemFileList)
            {
                cache.put(i.getItemFileId(), i);
            }
            return cache;
        }

        private static ArrayList<ItemFile> findAll()
        {
            ArrayList<ItemFile> itemFile = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("itemFile");
                while (rs.next())
                {
                    itemFile.add(ItemFile.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemFile object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return itemFile;
        }
        
        @Override
        public ItemFile find(Integer id)
        {
            return findByColumn("ItemFileId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<ItemFile> findAll(Integer limit, Integer offset)
        {
            ArrayList<ItemFile> itemFileList = new ArrayList<ItemFile>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for ItemFile, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    itemFileList = new ArrayList<ItemFile>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("item_file", limit, offset);
                    while (rs.next())
                    {
                        itemFileList.add(ItemFile.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ItemFile object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemFileList;
         
        }
        
        @Override
        public ArrayList<ItemFile> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ItemFile> itemFileList = new ArrayList<ItemFile>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for ItemFile, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    itemFileList = new ArrayList<ItemFile>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            ItemFile itemFile = (ItemFile) e.getValue();

                            
                                getRecordById("Item", itemFile.getItemId().toString());
                                itemFile.setItem(Item.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object ItemFile method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                itemFileList = new ArrayList<ItemFile>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("item_file", limit, offset);
                    while (rs.next())
                    {
                        itemFileList.add(ItemFile.process(rs));
                    }

                    
                    
                        for (ItemFile itemFile : itemFileList)
                        {                        
                            
                                getRecordById("Item", itemFile.getItemId().toString());
                                itemFile.setItem(Item.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object ItemFile method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemFileList;            
        }
        
        @Override
        public ArrayList<ItemFile> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ItemFile> itemFileList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for ItemFile(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            ItemFile i = (ItemFile) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                itemFileList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            itemFileList = null;
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
                    getRecordsByColumnWithLimitOrOffset("item_file", ItemFile.checkColumnName(columnName), columnValue, ItemFile.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        itemFileList.add(ItemFile.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ItemFile's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemFileList;
        } 
    
        @Override
        public int add(ItemFile obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                ItemFile.checkColumnSize(obj.getFileName(), 255);
                ItemFile.checkColumnSize(obj.getDescription(), 255);
                ItemFile.checkColumnSize(obj.getLabel(), 255);
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO item_file(ItemFileId,FileName,Description,Label,Hidden,ItemId,) VALUES (?,?,?,?,?);");                    
                preparedStatement.setInt(0, obj.getItemFileId());
                preparedStatement.setString(1, obj.getFileName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getLabel());
                preparedStatement.setInt(4, obj.getHidden());
                preparedStatement.setInt(5, obj.getItemId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_file;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemFile's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setItemFileId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public ItemFile update(ItemFile obj)
        {
           try
            {   
                
                ItemFile.checkColumnSize(obj.getFileName(), 255);
                ItemFile.checkColumnSize(obj.getDescription(), 255);
                ItemFile.checkColumnSize(obj.getLabel(), 255);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item_file SET com.busy.util.DatabaseColumn@1b4795b6=?,com.busy.util.DatabaseColumn@216d10c7=?,com.busy.util.DatabaseColumn@621bfad=?,com.busy.util.DatabaseColumn@8960ccb=?,com.busy.util.DatabaseColumn@2fdbc24a=? WHERE ItemFileId=?;");                    
                preparedStatement.setInt(0, obj.getItemFileId());
                preparedStatement.setString(1, obj.getFileName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getLabel());
                preparedStatement.setInt(4, obj.getHidden());
                preparedStatement.setInt(5, obj.getItemId());
                preparedStatement.setInt(6, obj.getItemFileId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getItemFileId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("ItemFile's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("item_file");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(ItemFile item_file)
        {
            
                try
                { 
                    
                            getRecordById("Item", item_file.getItemId().toString());
                            item_file.setItem(Item.process(rs));                                       
                    
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
        public void getRelatedObjects(ItemFile item_file)
        {
             
        }
        
        @Override
        public boolean remove(ItemFile obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM item_file WHERE ItemFileId=" + obj.getItemFileId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemFile's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getItemFileId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM item_file WHERE ItemFileId=" + id + ";");           
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
                updateQuery("DELETE FROM item_file;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemFile's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_file WHERE " + ItemFile.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ItemFile's removeByColumn method error: " + ex.getMessage());
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
                        ItemFile i = (ItemFile) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getItemFileId());
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

