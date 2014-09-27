






















































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
    
    public class BlogTypeDaoImpl extends BasicConnection implements Serializable, BlogTypeDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public BlogTypeDaoImpl()
        {
            cachingEnabled = false;
        }

        public BlogTypeDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class BlogTypeCache
        {
            public static final ConcurrentLruCache<Integer, BlogType> blogTypeCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (BlogType i : findAll())
                {
                    getCache().put(i.getBlogTypeId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, BlogType> getCache()
        {
            return BlogTypeCache.blogTypeCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, BlogType> buildCache(ArrayList<BlogType> blogTypeList)
        {        
            ConcurrentLruCache<Integer, BlogType> cache = new ConcurrentLruCache<Integer, BlogType>(blogTypeList.size() + 1000);
            for (BlogType i : blogTypeList)
            {
                cache.put(i.getBlogTypeId(), i);
            }
            return cache;
        }

        private static ArrayList<BlogType> findAll()
        {
            ArrayList<BlogType> blogType = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("blogType");
                while (rs.next())
                {
                    blogType.add(BlogType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("BlogType object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return blogType;
        }
        
        @Override
        public BlogType find(Integer id)
        {
            return findByColumn("BlogTypeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<BlogType> findAll(Integer limit, Integer offset)
        {
            ArrayList<BlogType> blogTypeList = new ArrayList<BlogType>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for BlogType, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    blogTypeList = new ArrayList<BlogType>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("blog_type", limit, offset);
                    while (rs.next())
                    {
                        blogTypeList.add(BlogType.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("BlogType object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return blogTypeList;
         
        }
        
        @Override
        public ArrayList<BlogType> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<BlogType> blogTypeList = new ArrayList<BlogType>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for BlogType, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    blogTypeList = new ArrayList<BlogType>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                blogTypeList = new ArrayList<BlogType>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("blog_type", limit, offset);
                    while (rs.next())
                    {
                        blogTypeList.add(BlogType.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object BlogType method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return blogTypeList;            
        }
        
        @Override
        public ArrayList<BlogType> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<BlogType> blogTypeList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for BlogType(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            BlogType i = (BlogType) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                blogTypeList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            blogTypeList = null;
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
                    getRecordsByColumnWithLimitOrOffset("blog_type", BlogType.checkColumnName(columnName), columnValue, BlogType.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        blogTypeList.add(BlogType.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("BlogType's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return blogTypeList;
        } 
    
        @Override
        public int add(BlogType obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                BlogType.checkColumnSize(obj.getTypeName(), 100);
                  

                openConnection();
                prepareStatement("INSERT INTO blog_type(BlogTypeId,TypeName,) VALUES (?);");                    
                preparedStatement.setInt(0, obj.getBlogTypeId());
                preparedStatement.setString(1, obj.getTypeName());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from blog_type;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("BlogType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setBlogTypeId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public BlogType update(BlogType obj)
        {
           try
            {   
                
                BlogType.checkColumnSize(obj.getTypeName(), 100);
                                  
                openConnection();                           
                prepareStatement("UPDATE blog_type SET com.busy.util.DatabaseColumn@6af75c29=? WHERE BlogTypeId=?;");                    
                preparedStatement.setInt(0, obj.getBlogTypeId());
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setInt(2, obj.getBlogTypeId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getBlogTypeId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("BlogType's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("blog_type");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(BlogType blog_type)
        {
              
        }
        
        @Override
        public void getRelatedObjects(BlogType blog_type)
        {
            blog_type.setBlogList(new BlogDaoImpl().findByColumn("BlogTypeId", blog_type.getBlogTypeId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(BlogType obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM blog_type WHERE BlogTypeId=" + obj.getBlogTypeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("BlogType's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getBlogTypeId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM blog_type WHERE BlogTypeId=" + id + ";");           
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
                updateQuery("DELETE FROM blog_type;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("BlogType's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM blog_type WHERE " + BlogType.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("BlogType's removeByColumn method error: " + ex.getMessage());
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
                        BlogType i = (BlogType) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getBlogTypeId());
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
        
                    
        public void getRelatedBlogList(BlogType blog_type)
        {           
            blog_type.setBlogList(new BlogDaoImpl().findByColumn("BlogTypeId", blog_type.getBlogTypeId().toString(),null,null));
        }        
        
                             
    }

