






















































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
    
    public class BlogPostCategoryDaoImpl extends BasicConnection implements Serializable, BlogPostCategoryDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public BlogPostCategoryDaoImpl()
        {
            cachingEnabled = false;
        }

        public BlogPostCategoryDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class BlogPostCategoryCache
        {
            public static final ConcurrentLruCache<Integer, BlogPostCategory> blogPostCategoryCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (BlogPostCategory i : findAll())
                {
                    getCache().put(i.getBlogPostCategoryId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, BlogPostCategory> getCache()
        {
            return BlogPostCategoryCache.blogPostCategoryCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, BlogPostCategory> buildCache(ArrayList<BlogPostCategory> blogPostCategoryList)
        {        
            ConcurrentLruCache<Integer, BlogPostCategory> cache = new ConcurrentLruCache<Integer, BlogPostCategory>(blogPostCategoryList.size() + 1000);
            for (BlogPostCategory i : blogPostCategoryList)
            {
                cache.put(i.getBlogPostCategoryId(), i);
            }
            return cache;
        }

        private static ArrayList<BlogPostCategory> findAll()
        {
            ArrayList<BlogPostCategory> blogPostCategory = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("blogPostCategory");
                while (rs.next())
                {
                    blogPostCategory.add(BlogPostCategory.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("BlogPostCategory object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blogPostCategory;
        }
        
        @Override
        public BlogPostCategory find(Integer id)
        {
            return findByColumn("BlogPostCategoryId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<BlogPostCategory> findAll(Integer limit, Integer offset)
        {
            ArrayList<BlogPostCategory> blogPostCategoryList = new ArrayList<BlogPostCategory>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for BlogPostCategory, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    blogPostCategoryList = new ArrayList<BlogPostCategory>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("blog_post_category", limit, offset);
                    while (rs.next())
                    {
                        blogPostCategoryList.add(BlogPostCategory.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("BlogPostCategory object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return blogPostCategoryList;
         
        }
        
        @Override
        public ArrayList<BlogPostCategory> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<BlogPostCategory> blogPostCategoryList = new ArrayList<BlogPostCategory>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for BlogPostCategory, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    blogPostCategoryList = new ArrayList<BlogPostCategory>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            BlogPostCategory blogPostCategory = (BlogPostCategory) e.getValue();

                            
                                getRecordById("BlogPost", blogPostCategory.getBlogPostId().toString());
                                blogPostCategory.setBlogPost(BlogPost.process(rs));               
                            
                                getRecordById("PostCategory", blogPostCategory.getPostCategoryId().toString());
                                blogPostCategory.setPostCategory(PostCategory.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object BlogPostCategory method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                blogPostCategoryList = new ArrayList<BlogPostCategory>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("blog_post_category", limit, offset);
                    while (rs.next())
                    {
                        blogPostCategoryList.add(BlogPostCategory.process(rs));
                    }

                    
                    
                        for (BlogPostCategory blogPostCategory : blogPostCategoryList)
                        {                        
                            
                                getRecordById("BlogPost", blogPostCategory.getBlogPostId().toString());
                                blogPostCategory.setBlogPost(BlogPost.process(rs));               
                            
                                getRecordById("PostCategory", blogPostCategory.getPostCategoryId().toString());
                                blogPostCategory.setPostCategory(PostCategory.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object BlogPostCategory method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return blogPostCategoryList;            
        }
        
        @Override
        public ArrayList<BlogPostCategory> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<BlogPostCategory> blogPostCategoryList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for BlogPostCategory(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            BlogPostCategory i = (BlogPostCategory) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                blogPostCategoryList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            blogPostCategoryList = null;
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
                    getRecordsByColumnWithLimitOrOffset("blog_post_category", BlogPostCategory.checkColumnName(columnName), columnValue, BlogPostCategory.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        blogPostCategoryList.add(BlogPostCategory.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("BlogPostCategory's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return blogPostCategoryList;
        } 
    
        @Override
        public int add(BlogPostCategory obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO blog_post_category(BlogPostCategoryId,BlogPostId,PostCategoryId,) VALUES (?,?);");                    
                preparedStatement.setInt(0, obj.getBlogPostCategoryId());
                preparedStatement.setInt(1, obj.getBlogPostId());
                preparedStatement.setInt(2, obj.getPostCategoryId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from blog_post_category;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("BlogPostCategory's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setBlogPostCategoryId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public BlogPostCategory update(BlogPostCategory obj)
        {
           try
            {   
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE blog_post_category SET com.busy.util.DatabaseColumn@30b55ecf=?,com.busy.util.DatabaseColumn@3e607f77=? WHERE BlogPostCategoryId=?;");                    
                preparedStatement.setInt(0, obj.getBlogPostCategoryId());
                preparedStatement.setInt(1, obj.getBlogPostId());
                preparedStatement.setInt(2, obj.getPostCategoryId());
                preparedStatement.setInt(3, obj.getBlogPostCategoryId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getBlogPostCategoryId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("BlogPostCategory's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("blog_post_category");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(BlogPostCategory blog_post_category)
        {
            
                try
                { 
                    
                            getRecordById("BlogPost", blog_post_category.getBlogPostId().toString());
                            blog_post_category.setBlogPost(BlogPost.process(rs));                                       
                    
                            getRecordById("PostCategory", blog_post_category.getPostCategoryId().toString());
                            blog_post_category.setPostCategory(PostCategory.process(rs));                                       
                    
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
        public void getRelatedObjects(BlogPostCategory blog_post_category)
        {
             
        }
        
        @Override
        public boolean remove(BlogPostCategory obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM blog_post_category WHERE BlogPostCategoryId=" + obj.getBlogPostCategoryId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("BlogPostCategory's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getBlogPostCategoryId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM blog_post_category WHERE BlogPostCategoryId=" + id + ";");           
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
                updateQuery("DELETE FROM blog_post_category;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("BlogPostCategory's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM blog_post_category WHERE " + BlogPostCategory.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("BlogPostCategory's removeByColumn method error: " + ex.getMessage());
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
                        BlogPostCategory i = (BlogPostCategory) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getBlogPostCategoryId());
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

