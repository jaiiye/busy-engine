
























































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
    
    public class BlogPostDaoImpl extends BasicConnection implements Serializable, BlogPostDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public BlogPostDaoImpl()
        {
            cachingEnabled = false;
        }

        public BlogPostDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class BlogPostCache
        {
            public static final ConcurrentLruCache<Integer, BlogPost> blogPostCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (BlogPost i : findAll())
                {
                    getCache().put(i.getBlogPostId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, BlogPost> getCache()
        {
            return BlogPostCache.blogPostCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, BlogPost> buildCache(ArrayList<BlogPost> blogPostList)
        {        
            ConcurrentLruCache<Integer, BlogPost> cache = new ConcurrentLruCache<Integer, BlogPost>(blogPostList.size() + 1000);
            for (BlogPost i : blogPostList)
            {
                cache.put(i.getBlogPostId(), i);
            }
            return cache;
        }

        private static ArrayList<BlogPost> findAll()
        {
            ArrayList<BlogPost> blogPost = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("blogPost");
                while (rs.next())
                {
                    blogPost.add(BlogPost.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("BlogPost object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blogPost;
        }
        
        @Override
        public BlogPost find(Integer id)
        {
            return findByColumn("BlogPostId", id.toString(), null, null).get(0);
        }
        
        @Override
        public BlogPost findWithInfo(Integer id)
        {
            BlogPost blogPost = findByColumn("BlogPostId", id.toString(), null, null).get(0);
            
            
                try
                {

                
                    getRecordById("user", blogPost.getUserId().toString());
                    blogPost.setUser(User.process(rs));               
                
                    getRecordById("blog", blogPost.getBlogId().toString());
                    blogPost.setBlog(Blog.process(rs));               
                
                    getRecordById("meta_tag", blogPost.getMetaTagId().toString());
                    blogPost.setMetaTag(MetaTag.process(rs));               
                  

                }
                catch (SQLException ex)
                {
                        System.out.println("Object BlogPost method findWithInfo(Integer id) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            
            
            return blogPost;
        }
        
        @Override
        public ArrayList<BlogPost> findAll(Integer limit, Integer offset)
        {
            ArrayList<BlogPost> blogPostList = new ArrayList<BlogPost>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for BlogPost, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    blogPostList = new ArrayList<BlogPost>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("blog_post", limit, offset);
                    while (rs.next())
                    {
                        blogPostList.add(BlogPost.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("BlogPost object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return blogPostList;
         
        }
        
        @Override
        public ArrayList<BlogPost> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<BlogPost> blogPostList = new ArrayList<BlogPost>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for BlogPost, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    blogPostList = new ArrayList<BlogPost>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            BlogPost blogPost = (BlogPost) e.getValue();

                            
                                getRecordById("user", blogPost.getUserId().toString());
                                blogPost.setUser(User.process(rs));               
                            
                                getRecordById("blog", blogPost.getBlogId().toString());
                                blogPost.setBlog(Blog.process(rs));               
                            
                                getRecordById("meta_tag", blogPost.getMetaTagId().toString());
                                blogPost.setMetaTag(MetaTag.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object BlogPost method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                blogPostList = new ArrayList<BlogPost>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("blog_post", limit, offset);
                    while (rs.next())
                    {
                        blogPostList.add(BlogPost.process(rs));
                    }

                    
                    
                        for (BlogPost blogPost : blogPostList)
                        {                        
                            
                                getRecordById("user", blogPost.getUserId().toString());
                                blogPost.setUser(User.process(rs));               
                            
                                getRecordById("blog", blogPost.getBlogId().toString());
                                blogPost.setBlog(Blog.process(rs));               
                            
                                getRecordById("meta_tag", blogPost.getMetaTagId().toString());
                                blogPost.setMetaTag(MetaTag.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object BlogPost method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return blogPostList;            
        }
        
        @Override
        public ArrayList<BlogPost> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<BlogPost> blogPostList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for BlogPost(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            BlogPost i = (BlogPost) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                blogPostList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            blogPostList = null;
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
                    getRecordsByColumnWithLimitOrOffset("blog_post", BlogPost.checkColumnName(columnName), columnValue, BlogPost.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        blogPostList.add(BlogPost.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("BlogPost's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return blogPostList;
        } 
        
        @Override
        public ArrayList<BlogPost> findByColumns(Column... columns)
        {
            ArrayList<BlogPost> blogPostList = new ArrayList<>();

            try
            {
                //make sure the correct isNumeric values are set for columns
                for(Column c : columns) 
                {
                    c.setNumeric(BlogPost.isColumnNumeric(c.getColumnName()));                
                }

                getAllRecordsByColumns("blog_post", columns);
                while (rs.next())
                {
                    blogPostList.add(BlogPost.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("BlogPost's method findByColumns(Column... columns) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }

            return blogPostList;
        }
    
        @Override
        public int add(BlogPost obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                BlogPost.checkColumnSize(obj.getTitle(), 255);
                BlogPost.checkColumnSize(obj.getContent(), 65535);
                BlogPost.checkColumnSize(obj.getImageURL(), 255);
                BlogPost.checkColumnSize(obj.getTags(), 255);
                
                
                
                
                
                BlogPost.checkColumnSize(obj.getExcerpt(), 255);
                
                BlogPost.checkColumnSize(obj.getLocale(), 10);
                
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO blog_post(Title,Content,ImageURL,Tags,Featured,RatingSum,VoteCount,CommentCount,PostStatus,Excerpt,LastModified,Locale,UserId,BlogId,MetaTagId) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setString(1, obj.getTitle());
                preparedStatement.setString(2, obj.getContent());
                preparedStatement.setString(3, obj.getImageURL());
                preparedStatement.setString(4, obj.getTags());
                preparedStatement.setInt(5, obj.getFeatured());
                preparedStatement.setInt(6, obj.getRatingSum());
                preparedStatement.setInt(7, obj.getVoteCount());
                preparedStatement.setInt(8, obj.getCommentCount());
                preparedStatement.setInt(9, obj.getPostStatus());
                preparedStatement.setString(10, obj.getExcerpt());
                preparedStatement.setDate(11, new java.sql.Date(obj.getLastModified().getTime()));
                preparedStatement.setString(12, obj.getLocale());
                preparedStatement.setInt(13, obj.getUserId());
                preparedStatement.setInt(14, obj.getBlogId());
                preparedStatement.setInt(15, obj.getMetaTagId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from blog_post;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("BlogPost's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setBlogPostId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public BlogPost update(BlogPost obj)
        {
           try
            {   
                
                BlogPost.checkColumnSize(obj.getTitle(), 255);
                BlogPost.checkColumnSize(obj.getContent(), 65535);
                BlogPost.checkColumnSize(obj.getImageURL(), 255);
                BlogPost.checkColumnSize(obj.getTags(), 255);
                
                
                
                
                
                BlogPost.checkColumnSize(obj.getExcerpt(), 255);
                
                BlogPost.checkColumnSize(obj.getLocale(), 10);
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE blog_post SET Title=?,Content=?,ImageURL=?,Tags=?,Featured=?,RatingSum=?,VoteCount=?,CommentCount=?,PostStatus=?,Excerpt=?,LastModified=?,Locale=?,UserId=?,BlogId=?,MetaTagId=? WHERE BlogPostId=?;");                    
                preparedStatement.setString(1, obj.getTitle());
                preparedStatement.setString(2, obj.getContent());
                preparedStatement.setString(3, obj.getImageURL());
                preparedStatement.setString(4, obj.getTags());
                preparedStatement.setInt(5, obj.getFeatured());
                preparedStatement.setInt(6, obj.getRatingSum());
                preparedStatement.setInt(7, obj.getVoteCount());
                preparedStatement.setInt(8, obj.getCommentCount());
                preparedStatement.setInt(9, obj.getPostStatus());
                preparedStatement.setString(10, obj.getExcerpt());
                preparedStatement.setDate(11, new java.sql.Date(obj.getLastModified().getTime()));
                preparedStatement.setString(12, obj.getLocale());
                preparedStatement.setInt(13, obj.getUserId());
                preparedStatement.setInt(14, obj.getBlogId());
                preparedStatement.setInt(15, obj.getMetaTagId());
                preparedStatement.setInt(16, obj.getBlogPostId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getBlogPostId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("BlogPost's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("blog_post");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(BlogPost blog_post)
        {
            
                try
                { 
                    
                            getRecordById("user", blog_post.getUserId().toString());
                            blog_post.setUser(User.process(rs));                                       
                    
                            getRecordById("blog", blog_post.getBlogId().toString());
                            blog_post.setBlog(Blog.process(rs));                                       
                    
                            getRecordById("meta_tag", blog_post.getMetaTagId().toString());
                            blog_post.setMetaTag(MetaTag.process(rs));                                       
                    
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
        public void getRelatedObjects(BlogPost blog_post)
        {
            blog_post.setBlogPostCategoryList(new BlogPostCategoryDaoImpl().findByColumn("BlogPostId", blog_post.getBlogPostId().toString(),null,null));
blog_post.setCommentList(new CommentDaoImpl().findByColumn("BlogPostId", blog_post.getBlogPostId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(BlogPost obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM blog_post WHERE BlogPostId=" + obj.getBlogPostId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("BlogPost's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getBlogPostId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM blog_post WHERE BlogPostId=" + id + ";");           
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
                updateQuery("DELETE FROM blog_post;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("BlogPost's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM blog_post WHERE " + BlogPost.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("BlogPost's removeByColumn method error: " + ex.getMessage());
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
                        BlogPost i = (BlogPost) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getBlogPostId());
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
        
                    
        public void getRelatedBlogPostCategoryList(BlogPost blog_post)
        {           
            blog_post.setBlogPostCategoryList(new BlogPostCategoryDaoImpl().findByColumn("BlogPostId", blog_post.getBlogPostId().toString(),null,null));
        }        
                    
        public void getRelatedCommentList(BlogPost blog_post)
        {           
            blog_post.setCommentList(new CommentDaoImpl().findByColumn("BlogPostId", blog_post.getBlogPostId().toString(),null,null));
        }        
        
            
        
        
        public void getRelatedUser(BlogPost blog_post)
        {            
            try
            {                 
                getRecordById("User", blog_post.getUserId().toString());
                blog_post.setUser(User.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getUser error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
        
        public void getRelatedBlog(BlogPost blog_post)
        {            
            try
            {                 
                getRecordById("Blog", blog_post.getBlogId().toString());
                blog_post.setBlog(Blog.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getBlog error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
        
        public void getRelatedMetaTag(BlogPost blog_post)
        {            
            try
            {                 
                getRecordById("MetaTag", blog_post.getMetaTagId().toString());
                blog_post.setMetaTag(MetaTag.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getMetaTag error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
          
        
                
        
        public void getRelatedUserWithInfo(BlogPost blog_post)
        {            
            blog_post.setUser(new UserDaoImpl().findWithInfo(blog_post.getUserId()));
        }
        
        public void getRelatedBlogWithInfo(BlogPost blog_post)
        {            
            blog_post.setBlog(new BlogDaoImpl().findWithInfo(blog_post.getBlogId()));
        }
        
        public void getRelatedMetaTagWithInfo(BlogPost blog_post)
        {            
            blog_post.setMetaTag(new MetaTagDaoImpl().findWithInfo(blog_post.getMetaTagId()));
        }
          
        
    }

