






















































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
    
    public class PostCategoryDaoImpl extends BasicConnection implements Serializable, PostCategoryDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public PostCategoryDaoImpl()
        {
            cachingEnabled = false;
        }

        public PostCategoryDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class PostCategoryCache
        {
            public static final ConcurrentLruCache<Integer, PostCategory> postCategoryCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (PostCategory i : findAll())
                {
                    getCache().put(i.getPostCategoryId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, PostCategory> getCache()
        {
            return PostCategoryCache.postCategoryCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, PostCategory> buildCache(ArrayList<PostCategory> postCategoryList)
        {        
            ConcurrentLruCache<Integer, PostCategory> cache = new ConcurrentLruCache<Integer, PostCategory>(postCategoryList.size() + 1000);
            for (PostCategory i : postCategoryList)
            {
                cache.put(i.getPostCategoryId(), i);
            }
            return cache;
        }

        private static ArrayList<PostCategory> findAll()
        {
            ArrayList<PostCategory> postCategory = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("postCategory");
                while (rs.next())
                {
                    postCategory.add(PostCategory.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("PostCategory object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return postCategory;
        }
        
        @Override
        public PostCategory find(Integer id)
        {
            return findByColumn("PostCategoryId", id.toString(), null, null).get(0);
        }
        
        @Override
        public PostCategory findWithInfo(Integer id)
        {
            PostCategory postCategory = findByColumn("PostCategoryId", id.toString(), null, null).get(0);
            
            
            
            return postCategory;
        }
        
        @Override
        public ArrayList<PostCategory> findAll(Integer limit, Integer offset)
        {
            ArrayList<PostCategory> postCategoryList = new ArrayList<PostCategory>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for PostCategory, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    postCategoryList = new ArrayList<PostCategory>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("post_category", limit, offset);
                    while (rs.next())
                    {
                        postCategoryList.add(PostCategory.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("PostCategory object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return postCategoryList;
         
        }
        
        @Override
        public ArrayList<PostCategory> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<PostCategory> postCategoryList = new ArrayList<PostCategory>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for PostCategory, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    postCategoryList = new ArrayList<PostCategory>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                postCategoryList = new ArrayList<PostCategory>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("post_category", limit, offset);
                    while (rs.next())
                    {
                        postCategoryList.add(PostCategory.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object PostCategory method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return postCategoryList;            
        }
        
        @Override
        public ArrayList<PostCategory> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<PostCategory> postCategoryList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for PostCategory(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            PostCategory i = (PostCategory) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                postCategoryList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            postCategoryList = null;
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
                    getRecordsByColumnWithLimitOrOffset("post_category", PostCategory.checkColumnName(columnName), columnValue, PostCategory.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        postCategoryList.add(PostCategory.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("PostCategory's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return postCategoryList;
        } 
    
        @Override
        public int add(PostCategory obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                PostCategory.checkColumnSize(obj.getCategoryName(), 100);
                  

                openConnection();
                prepareStatement("INSERT INTO post_category(CategoryName) VALUES (?);");                    
                preparedStatement.setString(1, obj.getCategoryName());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from post_category;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("PostCategory's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setPostCategoryId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public PostCategory update(PostCategory obj)
        {
           try
            {   
                
                PostCategory.checkColumnSize(obj.getCategoryName(), 100);
                                  
                openConnection();                           
                prepareStatement("UPDATE post_category SET CategoryName=? WHERE PostCategoryId=?;");                    
                preparedStatement.setString(1, obj.getCategoryName());
                preparedStatement.setInt(2, obj.getPostCategoryId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getPostCategoryId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("PostCategory's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("post_category");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(PostCategory post_category)
        {
              
        }
        
        @Override
        public void getRelatedObjects(PostCategory post_category)
        {
            post_category.setBlogPostCategoryList(new BlogPostCategoryDaoImpl().findByColumn("PostCategoryId", post_category.getPostCategoryId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(PostCategory obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM post_category WHERE PostCategoryId=" + obj.getPostCategoryId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("PostCategory's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getPostCategoryId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM post_category WHERE PostCategoryId=" + id + ";");           
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
                updateQuery("DELETE FROM post_category;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("PostCategory's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM post_category WHERE " + PostCategory.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("PostCategory's removeByColumn method error: " + ex.getMessage());
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
                        PostCategory i = (PostCategory) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getPostCategoryId());
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
        
                    
        public void getRelatedBlogPostCategoryList(PostCategory post_category)
        {           
            post_category.setBlogPostCategoryList(new BlogPostCategoryDaoImpl().findByColumn("PostCategoryId", post_category.getPostCategoryId().toString(),null,null));
        }        
        
            
        
          
        
                
          
        
    }

