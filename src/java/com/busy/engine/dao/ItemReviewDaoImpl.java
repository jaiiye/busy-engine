






















































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
    
    public class ItemReviewDaoImpl extends BasicConnection implements Serializable, ItemReviewDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public ItemReviewDaoImpl()
        {
            cachingEnabled = false;
        }

        public ItemReviewDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class ItemReviewCache
        {
            public static final ConcurrentLruCache<Integer, ItemReview> itemReviewCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (ItemReview i : findAll())
                {
                    getCache().put(i.getItemReviewId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, ItemReview> getCache()
        {
            return ItemReviewCache.itemReviewCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, ItemReview> buildCache(ArrayList<ItemReview> itemReviewList)
        {        
            ConcurrentLruCache<Integer, ItemReview> cache = new ConcurrentLruCache<Integer, ItemReview>(itemReviewList.size() + 1000);
            for (ItemReview i : itemReviewList)
            {
                cache.put(i.getItemReviewId(), i);
            }
            return cache;
        }

        private static ArrayList<ItemReview> findAll()
        {
            ArrayList<ItemReview> itemReview = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("itemReview");
                while (rs.next())
                {
                    itemReview.add(ItemReview.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("ItemReview object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return itemReview;
        }
        
        @Override
        public ItemReview find(Integer id)
        {
            return findByColumn("ItemReviewId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<ItemReview> findAll(Integer limit, Integer offset)
        {
            ArrayList<ItemReview> itemReviewList = new ArrayList<ItemReview>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for ItemReview, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    itemReviewList = new ArrayList<ItemReview>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("item_review", limit, offset);
                    while (rs.next())
                    {
                        itemReviewList.add(ItemReview.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ItemReview object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemReviewList;
         
        }
        
        @Override
        public ArrayList<ItemReview> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<ItemReview> itemReviewList = new ArrayList<ItemReview>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for ItemReview, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    itemReviewList = new ArrayList<ItemReview>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            ItemReview itemReview = (ItemReview) e.getValue();

                            
                                getRecordById("Item", itemReview.getItemId().toString());
                                itemReview.setItem(Item.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object ItemReview method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                itemReviewList = new ArrayList<ItemReview>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("item_review", limit, offset);
                    while (rs.next())
                    {
                        itemReviewList.add(ItemReview.process(rs));
                    }

                    
                    
                        for (ItemReview itemReview : itemReviewList)
                        {                        
                            
                                getRecordById("Item", itemReview.getItemId().toString());
                                itemReview.setItem(Item.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object ItemReview method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemReviewList;            
        }
        
        @Override
        public ArrayList<ItemReview> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<ItemReview> itemReviewList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for ItemReview(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            ItemReview i = (ItemReview) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                itemReviewList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            itemReviewList = null;
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
                    getRecordsByColumnWithLimitOrOffset("item_review", ItemReview.checkColumnName(columnName), columnValue, ItemReview.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        itemReviewList.add(ItemReview.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("ItemReview's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return itemReviewList;
        } 
    
        @Override
        public int add(ItemReview obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                
                
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO item_review(ItemReviewId,ItemId,Rating,HelpfulYes,HelpfulNo,) VALUES (?,?,?,?);");                    
                preparedStatement.setInt(0, obj.getItemReviewId());
                preparedStatement.setInt(1, obj.getItemId());
                preparedStatement.setInt(2, obj.getRating());
                preparedStatement.setInt(3, obj.getHelpfulYes());
                preparedStatement.setInt(4, obj.getHelpfulNo());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from item_review;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemReview's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setItemReviewId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public ItemReview update(ItemReview obj)
        {
           try
            {   
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE item_review SET com.busy.util.DatabaseColumn@193e93d9=?,com.busy.util.DatabaseColumn@b6da6c8=?,com.busy.util.DatabaseColumn@67632c65=?,com.busy.util.DatabaseColumn@3c8b61c6=? WHERE ItemReviewId=?;");                    
                preparedStatement.setInt(0, obj.getItemReviewId());
                preparedStatement.setInt(1, obj.getItemId());
                preparedStatement.setInt(2, obj.getRating());
                preparedStatement.setInt(3, obj.getHelpfulYes());
                preparedStatement.setInt(4, obj.getHelpfulNo());
                preparedStatement.setInt(5, obj.getItemReviewId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getItemReviewId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("ItemReview's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("item_review");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(ItemReview item_review)
        {
            
                try
                { 
                    
                            getRecordById("Item", item_review.getItemId().toString());
                            item_review.setItem(Item.process(rs));                                       
                    
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
        public void getRelatedObjects(ItemReview item_review)
        {
            item_review.setCommentList(new CommentDaoImpl().findByColumn("ItemReviewId", item_review.getItemReviewId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(ItemReview obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM item_review WHERE ItemReviewId=" + obj.getItemReviewId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemReview's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getItemReviewId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM item_review WHERE ItemReviewId=" + id + ";");           
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
                updateQuery("DELETE FROM item_review;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("ItemReview's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM item_review WHERE " + ItemReview.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("ItemReview's removeByColumn method error: " + ex.getMessage());
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
                        ItemReview i = (ItemReview) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getItemReviewId());
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
        
                    
        public void getRelatedCommentList(ItemReview item_review)
        {           
            item_review.setCommentList(new CommentDaoImpl().findByColumn("ItemReviewId", item_review.getItemReviewId().toString(),null,null));
        }        
        
                             
    }

