






















































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
    
    public class CommentDaoImpl extends BasicConnection implements Serializable, CommentDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public CommentDaoImpl()
        {
            cachingEnabled = false;
        }

        public CommentDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class CommentCache
        {
            public static final ConcurrentLruCache<Integer, Comment> commentCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (Comment i : findAll())
                {
                    getCache().put(i.getCommentId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, Comment> getCache()
        {
            return CommentCache.commentCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, Comment> buildCache(ArrayList<Comment> commentList)
        {        
            ConcurrentLruCache<Integer, Comment> cache = new ConcurrentLruCache<Integer, Comment>(commentList.size() + 1000);
            for (Comment i : commentList)
            {
                cache.put(i.getCommentId(), i);
            }
            return cache;
        }

        private static ArrayList<Comment> findAll()
        {
            ArrayList<Comment> comment = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("comment");
                while (rs.next())
                {
                    comment.add(Comment.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Comment object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return comment;
        }
        
        @Override
        public Comment find(Integer id)
        {
            return findByColumn("CommentId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<Comment> findAll(Integer limit, Integer offset)
        {
            ArrayList<Comment> commentList = new ArrayList<Comment>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for Comment, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    commentList = new ArrayList<Comment>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("comment", limit, offset);
                    while (rs.next())
                    {
                        commentList.add(Comment.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Comment object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return commentList;
         
        }
        
        @Override
        public ArrayList<Comment> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<Comment> commentList = new ArrayList<Comment>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for Comment, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    commentList = new ArrayList<Comment>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            Comment comment = (Comment) e.getValue();

                            
                                getRecordById("User", comment.getUserId().toString());
                                comment.setUser(User.process(rs));               
                            
                                getRecordById("BlogPost", comment.getBlogPostId().toString());
                                comment.setBlogPost(BlogPost.process(rs));               
                            
                                getRecordById("ItemReview", comment.getItemReviewId().toString());
                                comment.setItemReview(ItemReview.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object Comment method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                commentList = new ArrayList<Comment>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("comment", limit, offset);
                    while (rs.next())
                    {
                        commentList.add(Comment.process(rs));
                    }

                    
                    
                        for (Comment comment : commentList)
                        {                        
                            
                                getRecordById("User", comment.getUserId().toString());
                                comment.setUser(User.process(rs));               
                            
                                getRecordById("BlogPost", comment.getBlogPostId().toString());
                                comment.setBlogPost(BlogPost.process(rs));               
                            
                                getRecordById("ItemReview", comment.getItemReviewId().toString());
                                comment.setItemReview(ItemReview.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object Comment method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return commentList;            
        }
        
        @Override
        public ArrayList<Comment> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<Comment> commentList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for Comment(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            Comment i = (Comment) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                commentList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            commentList = null;
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
                    getRecordsByColumnWithLimitOrOffset("comment", Comment.checkColumnName(columnName), columnValue, Comment.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        commentList.add(Comment.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("Comment's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return commentList;
        } 
    
        @Override
        public int add(Comment obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                Comment.checkColumnSize(obj.getTitle(), 45);
                Comment.checkColumnSize(obj.getContent(), 65535);
                
                
                
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO comment(CommentId,Title,Content,Date,CommentStatus,UserId,BlogPostId,ItemReviewId,) VALUES (?,?,?,?,?,?,?);");                    
                preparedStatement.setInt(0, obj.getCommentId());
                preparedStatement.setString(1, obj.getTitle());
                preparedStatement.setString(2, obj.getContent());
                preparedStatement.setDate(3, new java.sql.Date(obj.getDate().getTime()));
                preparedStatement.setInt(4, obj.getCommentStatus());
                preparedStatement.setInt(5, obj.getUserId());
                preparedStatement.setInt(6, obj.getBlogPostId());
                preparedStatement.setInt(7, obj.getItemReviewId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from comment;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Comment's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setCommentId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public Comment update(Comment obj)
        {
           try
            {   
                
                Comment.checkColumnSize(obj.getTitle(), 45);
                Comment.checkColumnSize(obj.getContent(), 65535);
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE comment SET com.busy.util.DatabaseColumn@181f562c=?,com.busy.util.DatabaseColumn@46e36c6b=?,com.busy.util.DatabaseColumn@172977e0=?,com.busy.util.DatabaseColumn@1d890b12=?,com.busy.util.DatabaseColumn@7f4590b9=?,com.busy.util.DatabaseColumn@22982b0e=?,com.busy.util.DatabaseColumn@7e52ceca=? WHERE CommentId=?;");                    
                preparedStatement.setInt(0, obj.getCommentId());
                preparedStatement.setString(1, obj.getTitle());
                preparedStatement.setString(2, obj.getContent());
                preparedStatement.setDate(3, new java.sql.Date(obj.getDate().getTime()));
                preparedStatement.setInt(4, obj.getCommentStatus());
                preparedStatement.setInt(5, obj.getUserId());
                preparedStatement.setInt(6, obj.getBlogPostId());
                preparedStatement.setInt(7, obj.getItemReviewId());
                preparedStatement.setInt(8, obj.getCommentId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getCommentId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("Comment's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("comment");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(Comment comment)
        {
            
                try
                { 
                    
                            getRecordById("User", comment.getUserId().toString());
                            comment.setUser(User.process(rs));                                       
                    
                            getRecordById("BlogPost", comment.getBlogPostId().toString());
                            comment.setBlogPost(BlogPost.process(rs));                                       
                    
                            getRecordById("ItemReview", comment.getItemReviewId().toString());
                            comment.setItemReview(ItemReview.process(rs));                                       
                    
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
        public void getRelatedObjects(Comment comment)
        {
             
        }
        
        @Override
        public boolean remove(Comment obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM comment WHERE CommentId=" + obj.getCommentId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Comment's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getCommentId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM comment WHERE CommentId=" + id + ";");           
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
                updateQuery("DELETE FROM comment;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("Comment's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM comment WHERE " + Comment.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("Comment's removeByColumn method error: " + ex.getMessage());
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
                        Comment i = (Comment) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getCommentId());
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

