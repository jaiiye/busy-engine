






















































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
    
    public class MetaTagDaoImpl extends BasicConnection implements Serializable, MetaTagDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public MetaTagDaoImpl()
        {
            cachingEnabled = false;
        }

        public MetaTagDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class MetaTagCache
        {
            public static final ConcurrentLruCache<Integer, MetaTag> metaTagCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (MetaTag i : findAll())
                {
                    getCache().put(i.getMetaTagId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, MetaTag> getCache()
        {
            return MetaTagCache.metaTagCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, MetaTag> buildCache(ArrayList<MetaTag> metaTagList)
        {        
            ConcurrentLruCache<Integer, MetaTag> cache = new ConcurrentLruCache<Integer, MetaTag>(metaTagList.size() + 1000);
            for (MetaTag i : metaTagList)
            {
                cache.put(i.getMetaTagId(), i);
            }
            return cache;
        }

        private static ArrayList<MetaTag> findAll()
        {
            ArrayList<MetaTag> metaTag = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("metaTag");
                while (rs.next())
                {
                    metaTag.add(MetaTag.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("MetaTag object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return metaTag;
        }
        
        @Override
        public MetaTag find(Integer id)
        {
            return findByColumn("MetaTagId", id.toString(), null, null).get(0);
        }
        
        @Override
        public MetaTag findWithInfo(Integer id)
        {
            MetaTag metaTag = findByColumn("MetaTagId", id.toString(), null, null).get(0);
            
            
            
            return metaTag;
        }
        
        @Override
        public ArrayList<MetaTag> findAll(Integer limit, Integer offset)
        {
            ArrayList<MetaTag> metaTagList = new ArrayList<MetaTag>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for MetaTag, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    metaTagList = new ArrayList<MetaTag>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("meta_tag", limit, offset);
                    while (rs.next())
                    {
                        metaTagList.add(MetaTag.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("MetaTag object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return metaTagList;
         
        }
        
        @Override
        public ArrayList<MetaTag> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<MetaTag> metaTagList = new ArrayList<MetaTag>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for MetaTag, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    metaTagList = new ArrayList<MetaTag>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                metaTagList = new ArrayList<MetaTag>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("meta_tag", limit, offset);
                    while (rs.next())
                    {
                        metaTagList.add(MetaTag.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object MetaTag method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return metaTagList;            
        }
        
        @Override
        public ArrayList<MetaTag> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<MetaTag> metaTagList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for MetaTag(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            MetaTag i = (MetaTag) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                metaTagList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            metaTagList = null;
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
                    getRecordsByColumnWithLimitOrOffset("meta_tag", MetaTag.checkColumnName(columnName), columnValue, MetaTag.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        metaTagList.add(MetaTag.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("MetaTag's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return metaTagList;
        } 
    
        @Override
        public int add(MetaTag obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                MetaTag.checkColumnSize(obj.getTitle(), 150);
                MetaTag.checkColumnSize(obj.getDescription(), 255);
                MetaTag.checkColumnSize(obj.getKeywords(), 255);
                  

                openConnection();
                prepareStatement("INSERT INTO meta_tag(Title,Description,Keywords) VALUES (?,?,?);");                    
                preparedStatement.setString(1, obj.getTitle());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getKeywords());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from meta_tag;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("MetaTag's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setMetaTagId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public MetaTag update(MetaTag obj)
        {
           try
            {   
                
                MetaTag.checkColumnSize(obj.getTitle(), 150);
                MetaTag.checkColumnSize(obj.getDescription(), 255);
                MetaTag.checkColumnSize(obj.getKeywords(), 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE meta_tag SET Title=?,Description=?,Keywords=? WHERE MetaTagId=?;");                    
                preparedStatement.setString(1, obj.getTitle());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getKeywords());
                preparedStatement.setInt(4, obj.getMetaTagId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getMetaTagId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("MetaTag's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("meta_tag");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(MetaTag meta_tag)
        {
              
        }
        
        @Override
        public void getRelatedObjects(MetaTag meta_tag)
        {
            meta_tag.setBlogPostList(new BlogPostDaoImpl().findByColumn("MetaTagId", meta_tag.getMetaTagId().toString(),null,null));
meta_tag.setItemList(new ItemDaoImpl().findByColumn("MetaTagId", meta_tag.getMetaTagId().toString(),null,null));
meta_tag.setPageList(new PageDaoImpl().findByColumn("MetaTagId", meta_tag.getMetaTagId().toString(),null,null));
meta_tag.setVendorList(new VendorDaoImpl().findByColumn("MetaTagId", meta_tag.getMetaTagId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(MetaTag obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM meta_tag WHERE MetaTagId=" + obj.getMetaTagId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("MetaTag's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getMetaTagId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM meta_tag WHERE MetaTagId=" + id + ";");           
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
                updateQuery("DELETE FROM meta_tag;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("MetaTag's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM meta_tag WHERE " + MetaTag.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("MetaTag's removeByColumn method error: " + ex.getMessage());
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
                        MetaTag i = (MetaTag) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getMetaTagId());
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
        
                    
        public void getRelatedBlogPostList(MetaTag meta_tag)
        {           
            meta_tag.setBlogPostList(new BlogPostDaoImpl().findByColumn("MetaTagId", meta_tag.getMetaTagId().toString(),null,null));
        }        
                    
        public void getRelatedItemList(MetaTag meta_tag)
        {           
            meta_tag.setItemList(new ItemDaoImpl().findByColumn("MetaTagId", meta_tag.getMetaTagId().toString(),null,null));
        }        
                    
        public void getRelatedPageList(MetaTag meta_tag)
        {           
            meta_tag.setPageList(new PageDaoImpl().findByColumn("MetaTagId", meta_tag.getMetaTagId().toString(),null,null));
        }        
                    
        public void getRelatedVendorList(MetaTag meta_tag)
        {           
            meta_tag.setVendorList(new VendorDaoImpl().findByColumn("MetaTagId", meta_tag.getMetaTagId().toString(),null,null));
        }        
        
            
        
          
        
                
          
        
    }

