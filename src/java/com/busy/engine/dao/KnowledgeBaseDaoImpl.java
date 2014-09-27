






















































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
    
    public class KnowledgeBaseDaoImpl extends BasicConnection implements Serializable, KnowledgeBaseDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public KnowledgeBaseDaoImpl()
        {
            cachingEnabled = false;
        }

        public KnowledgeBaseDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class KnowledgeBaseCache
        {
            public static final ConcurrentLruCache<Integer, KnowledgeBase> knowledgeBaseCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (KnowledgeBase i : findAll())
                {
                    getCache().put(i.getKnowledgeBaseId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, KnowledgeBase> getCache()
        {
            return KnowledgeBaseCache.knowledgeBaseCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, KnowledgeBase> buildCache(ArrayList<KnowledgeBase> knowledgeBaseList)
        {        
            ConcurrentLruCache<Integer, KnowledgeBase> cache = new ConcurrentLruCache<Integer, KnowledgeBase>(knowledgeBaseList.size() + 1000);
            for (KnowledgeBase i : knowledgeBaseList)
            {
                cache.put(i.getKnowledgeBaseId(), i);
            }
            return cache;
        }

        private static ArrayList<KnowledgeBase> findAll()
        {
            ArrayList<KnowledgeBase> knowledgeBase = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("knowledgeBase");
                while (rs.next())
                {
                    knowledgeBase.add(KnowledgeBase.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("KnowledgeBase object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return knowledgeBase;
        }
        
        @Override
        public KnowledgeBase find(Integer id)
        {
            return findByColumn("KnowledgeBaseId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<KnowledgeBase> findAll(Integer limit, Integer offset)
        {
            ArrayList<KnowledgeBase> knowledgeBaseList = new ArrayList<KnowledgeBase>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for KnowledgeBase, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    knowledgeBaseList = new ArrayList<KnowledgeBase>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("knowledge_base", limit, offset);
                    while (rs.next())
                    {
                        knowledgeBaseList.add(KnowledgeBase.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("KnowledgeBase object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return knowledgeBaseList;
         
        }
        
        @Override
        public ArrayList<KnowledgeBase> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<KnowledgeBase> knowledgeBaseList = new ArrayList<KnowledgeBase>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for KnowledgeBase, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    knowledgeBaseList = new ArrayList<KnowledgeBase>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                knowledgeBaseList = new ArrayList<KnowledgeBase>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("knowledge_base", limit, offset);
                    while (rs.next())
                    {
                        knowledgeBaseList.add(KnowledgeBase.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object KnowledgeBase method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return knowledgeBaseList;            
        }
        
        @Override
        public ArrayList<KnowledgeBase> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<KnowledgeBase> knowledgeBaseList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for KnowledgeBase(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            KnowledgeBase i = (KnowledgeBase) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                knowledgeBaseList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            knowledgeBaseList = null;
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
                    getRecordsByColumnWithLimitOrOffset("knowledge_base", KnowledgeBase.checkColumnName(columnName), columnValue, KnowledgeBase.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        knowledgeBaseList.add(KnowledgeBase.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("KnowledgeBase's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return knowledgeBaseList;
        } 
    
        @Override
        public int add(KnowledgeBase obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                KnowledgeBase.checkColumnSize(obj.getKnowledgeBaseName(), 200);
                KnowledgeBase.checkColumnSize(obj.getDescription(), 65535);
                
                
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO knowledge_base(KnowledgeBaseId,KnowledgeBaseName,Description,Rank,LastModified,LatestTopic,LatestPost,) VALUES (?,?,?,?,?,?);");                    
                preparedStatement.setInt(0, obj.getKnowledgeBaseId());
                preparedStatement.setString(1, obj.getKnowledgeBaseName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setInt(3, obj.getRank());
                preparedStatement.setDate(4, new java.sql.Date(obj.getLastModified().getTime()));
                preparedStatement.setInt(5, obj.getLatestTopic());
                preparedStatement.setInt(6, obj.getLatestPost());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from knowledge_base;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("KnowledgeBase's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setKnowledgeBaseId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public KnowledgeBase update(KnowledgeBase obj)
        {
           try
            {   
                
                KnowledgeBase.checkColumnSize(obj.getKnowledgeBaseName(), 200);
                KnowledgeBase.checkColumnSize(obj.getDescription(), 65535);
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE knowledge_base SET com.busy.util.DatabaseColumn@2185c29e=?,com.busy.util.DatabaseColumn@3324a06d=?,com.busy.util.DatabaseColumn@1b8268d0=?,com.busy.util.DatabaseColumn@78dcce3f=?,com.busy.util.DatabaseColumn@77c27288=?,com.busy.util.DatabaseColumn@617393d5=? WHERE KnowledgeBaseId=?;");                    
                preparedStatement.setInt(0, obj.getKnowledgeBaseId());
                preparedStatement.setString(1, obj.getKnowledgeBaseName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setInt(3, obj.getRank());
                preparedStatement.setDate(4, new java.sql.Date(obj.getLastModified().getTime()));
                preparedStatement.setInt(5, obj.getLatestTopic());
                preparedStatement.setInt(6, obj.getLatestPost());
                preparedStatement.setInt(7, obj.getKnowledgeBaseId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getKnowledgeBaseId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("KnowledgeBase's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("knowledge_base");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(KnowledgeBase knowledge_base)
        {
              
        }
        
        @Override
        public void getRelatedObjects(KnowledgeBase knowledge_base)
        {
            knowledge_base.setBlogList(new BlogDaoImpl().findByColumn("KnowledgeBaseId", knowledge_base.getKnowledgeBaseId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(KnowledgeBase obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM knowledge_base WHERE KnowledgeBaseId=" + obj.getKnowledgeBaseId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("KnowledgeBase's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getKnowledgeBaseId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM knowledge_base WHERE KnowledgeBaseId=" + id + ";");           
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
                updateQuery("DELETE FROM knowledge_base;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("KnowledgeBase's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM knowledge_base WHERE " + KnowledgeBase.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("KnowledgeBase's removeByColumn method error: " + ex.getMessage());
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
                        KnowledgeBase i = (KnowledgeBase) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getKnowledgeBaseId());
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
        
                    
        public void getRelatedBlogList(KnowledgeBase knowledge_base)
        {           
            knowledge_base.setBlogList(new BlogDaoImpl().findByColumn("KnowledgeBaseId", knowledge_base.getKnowledgeBaseId().toString(),null,null));
        }        
        
                             
    }

