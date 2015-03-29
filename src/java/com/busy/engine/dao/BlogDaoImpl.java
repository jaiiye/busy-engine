






















































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
    
    public class BlogDaoImpl extends BasicConnection implements Serializable, BlogDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public BlogDaoImpl()
        {
            cachingEnabled = false;
        }

        public BlogDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class BlogCache
        {
            public static final ConcurrentLruCache<Integer, Blog> blogCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (Blog i : findAll())
                {
                    getCache().put(i.getBlogId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, Blog> getCache()
        {
            return BlogCache.blogCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, Blog> buildCache(ArrayList<Blog> blogList)
        {        
            ConcurrentLruCache<Integer, Blog> cache = new ConcurrentLruCache<Integer, Blog>(blogList.size() + 1000);
            for (Blog i : blogList)
            {
                cache.put(i.getBlogId(), i);
            }
            return cache;
        }

        private static ArrayList<Blog> findAll()
        {
            ArrayList<Blog> blog = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("blog");
                while (rs.next())
                {
                    blog.add(Blog.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Blog object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blog;
        }
        
        @Override
        public Blog find(Integer id)
        {
            return findByColumn("BlogId", id.toString(), null, null).get(0);
        }
        
        @Override
        public Blog findWithInfo(Integer id)
        {
            Blog blog = findByColumn("BlogId", id.toString(), null, null).get(0);
            
            
                try
                {

                
                    getRecordById("BlogType", blog.getBlogTypeId().toString());
                    blog.setBlogType(BlogType.process(rs));               
                
                    getRecordById("KnowledgeBase", blog.getKnowledgeBaseId().toString());
                    blog.setKnowledgeBase(KnowledgeBase.process(rs));               
                  

                }
                catch (SQLException ex)
                {
                        System.out.println("Object Blog method findWithInfo(Integer id) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            
            
            return blog;
        }
        
        @Override
        public ArrayList<Blog> findAll(Integer limit, Integer offset)
        {
            ArrayList<Blog> blogList = new ArrayList<Blog>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for Blog, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    blogList = new ArrayList<Blog>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("blog", limit, offset);
                    while (rs.next())
                    {
                        blogList.add(Blog.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Blog object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return blogList;
         
        }
        
        @Override
        public ArrayList<Blog> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Blog> blogList = new ArrayList<Blog>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for Blog, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    blogList = new ArrayList<Blog>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            Blog blog = (Blog) e.getValue();

                            
                                getRecordById("BlogType", blog.getBlogTypeId().toString());
                                blog.setBlogType(BlogType.process(rs));               
                            
                                getRecordById("KnowledgeBase", blog.getKnowledgeBaseId().toString());
                                blog.setKnowledgeBase(KnowledgeBase.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object Blog method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                blogList = new ArrayList<Blog>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("blog", limit, offset);
                    while (rs.next())
                    {
                        blogList.add(Blog.process(rs));
                    }

                    
                    
                        for (Blog blog : blogList)
                        {                        
                            
                                getRecordById("BlogType", blog.getBlogTypeId().toString());
                                blog.setBlogType(BlogType.process(rs));               
                            
                                getRecordById("KnowledgeBase", blog.getKnowledgeBaseId().toString());
                                blog.setKnowledgeBase(KnowledgeBase.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object Blog method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return blogList;            
        }
        
        @Override
        public ArrayList<Blog> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Blog> blogList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for Blog(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            Blog i = (Blog) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                blogList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            blogList = null;
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
                    getRecordsByColumnWithLimitOrOffset("blog", Blog.checkColumnName(columnName), columnValue, Blog.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        blogList.add(Blog.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Blog's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return blogList;
        } 
    
        @Override
        public int add(Blog obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                Blog.checkColumnSize(obj.getTopic(), 100);
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO blog(Topic,BlogTypeId,KnowledgeBaseId) VALUES (?,?,?);");                    
                preparedStatement.setString(1, obj.getTopic());
                preparedStatement.setInt(2, obj.getBlogTypeId());
                preparedStatement.setInt(3, obj.getKnowledgeBaseId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from blog;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Blog's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setBlogId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public Blog update(Blog obj)
        {
           try
            {   
                
                Blog.checkColumnSize(obj.getTopic(), 100);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE blog SET Topic=?,BlogTypeId=?,KnowledgeBaseId=? WHERE BlogId=?;");                    
                preparedStatement.setString(1, obj.getTopic());
                preparedStatement.setInt(2, obj.getBlogTypeId());
                preparedStatement.setInt(3, obj.getKnowledgeBaseId());
                preparedStatement.setInt(4, obj.getBlogId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getBlogId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("Blog's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("blog");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(Blog blog)
        {
            
                try
                { 
                    
                            getRecordById("BlogType", blog.getBlogTypeId().toString());
                            blog.setBlogType(BlogType.process(rs));                                       
                    
                            getRecordById("KnowledgeBase", blog.getKnowledgeBaseId().toString());
                            blog.setKnowledgeBase(KnowledgeBase.process(rs));                                       
                    
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
        public void getRelatedObjects(Blog blog)
        {
            blog.setBlogPostList(new BlogPostDaoImpl().findByColumn("BlogId", blog.getBlogId().toString(),null,null));
blog.setUserServiceList(new UserServiceDaoImpl().findByColumn("BlogId", blog.getBlogId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(Blog obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM blog WHERE BlogId=" + obj.getBlogId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Blog's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getBlogId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM blog WHERE BlogId=" + id + ";");           
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
                updateQuery("DELETE FROM blog;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Blog's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM blog WHERE " + Blog.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Blog's removeByColumn method error: " + ex.getMessage());
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
                        Blog i = (Blog) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getBlogId());
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
        
                    
        public void getRelatedBlogPostList(Blog blog)
        {           
            blog.setBlogPostList(new BlogPostDaoImpl().findByColumn("BlogId", blog.getBlogId().toString(),null,null));
        }        
                    
        public void getRelatedUserServiceList(Blog blog)
        {           
            blog.setUserServiceList(new UserServiceDaoImpl().findByColumn("BlogId", blog.getBlogId().toString(),null,null));
        }        
        
            
        
        
        public void getRelatedBlogType(Blog blog)
        {            
            try
            {                 
                getRecordById("BlogType", blog.getBlogTypeId().toString());
                blog.setBlogType(BlogType.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getBlogType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
        
        public void getRelatedKnowledgeBase(Blog blog)
        {            
            try
            {                 
                getRecordById("KnowledgeBase", blog.getKnowledgeBaseId().toString());
                blog.setKnowledgeBase(KnowledgeBase.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getKnowledgeBase error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
          
        
                
        
        public void getRelatedBlogTypeWithInfo(Blog blog)
        {            
            blog.setBlogType(new BlogTypeDaoImpl().findWithInfo(blog.getBlogTypeId()));
        }
        
        public void getRelatedKnowledgeBaseWithInfo(Blog blog)
        {            
            blog.setKnowledgeBase(new KnowledgeBaseDaoImpl().findWithInfo(blog.getKnowledgeBaseId()));
        }
          
        
    }

