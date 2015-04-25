
























































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
    
    public class EntityStatusDaoImpl extends BasicConnection implements Serializable, EntityStatusDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public EntityStatusDaoImpl()
        {
            cachingEnabled = false;
        }

        public EntityStatusDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class EntityStatusCache
        {
            public static final ConcurrentLruCache<Integer, EntityStatus> entityStatusCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (EntityStatus i : findAll())
                {
                    getCache().put(i.getEntityStatusId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, EntityStatus> getCache()
        {
            return EntityStatusCache.entityStatusCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, EntityStatus> buildCache(ArrayList<EntityStatus> entityStatusList)
        {        
            ConcurrentLruCache<Integer, EntityStatus> cache = new ConcurrentLruCache<Integer, EntityStatus>(entityStatusList.size() + 1000);
            for (EntityStatus i : entityStatusList)
            {
                cache.put(i.getEntityStatusId(), i);
            }
            return cache;
        }

        private static ArrayList<EntityStatus> findAll()
        {
            ArrayList<EntityStatus> entityStatus = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("entityStatus");
                while (rs.next())
                {
                    entityStatus.add(EntityStatus.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("EntityStatus object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return entityStatus;
        }
        
        @Override
        public EntityStatus find(Integer id)
        {
            return findByColumn("EntityStatusId", id.toString(), null, null).get(0);
        }
        
        @Override
        public EntityStatus findWithInfo(Integer id)
        {
            EntityStatus entityStatus = findByColumn("EntityStatusId", id.toString(), null, null).get(0);
            
            
            
            return entityStatus;
        }
        
        @Override
        public ArrayList<EntityStatus> findAll(Integer limit, Integer offset)
        {
            ArrayList<EntityStatus> entityStatusList = new ArrayList<EntityStatus>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for EntityStatus, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    entityStatusList = new ArrayList<EntityStatus>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("entity_status", limit, offset);
                    while (rs.next())
                    {
                        entityStatusList.add(EntityStatus.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("EntityStatus object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return entityStatusList;
         
        }
        
        @Override
        public ArrayList<EntityStatus> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<EntityStatus> entityStatusList = new ArrayList<EntityStatus>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for EntityStatus, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    entityStatusList = new ArrayList<EntityStatus>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                entityStatusList = new ArrayList<EntityStatus>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("entity_status", limit, offset);
                    while (rs.next())
                    {
                        entityStatusList.add(EntityStatus.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object EntityStatus method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return entityStatusList;            
        }
        
        @Override
        public ArrayList<EntityStatus> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<EntityStatus> entityStatusList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for EntityStatus(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            EntityStatus i = (EntityStatus) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                entityStatusList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            entityStatusList = null;
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
                    getRecordsByColumnWithLimitOrOffset("entity_status", EntityStatus.checkColumnName(columnName), columnValue, EntityStatus.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        entityStatusList.add(EntityStatus.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("EntityStatus's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return entityStatusList;
        } 
        
        @Override
        public ArrayList<EntityStatus> findByColumns(Column... columns)
        {
            ArrayList<EntityStatus> entityStatusList = new ArrayList<>();

            try
            {
                //make sure the correct isNumeric values are set for columns
                for(Column c : columns) 
                {
                    c.setNumeric(EntityStatus.isColumnNumeric(c.getColumnName()));                
                }

                getAllRecordsByColumns("entity_status", columns);
                while (rs.next())
                {
                    entityStatusList.add(EntityStatus.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("EntityStatus's method findByColumns(Column... columns) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }

            return entityStatusList;
        }
    
        @Override
        public int add(EntityStatus obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                
                EntityStatus.checkColumnSize(obj.getStatusName(), 100);
                EntityStatus.checkColumnSize(obj.getAppliesTo(), 100);
                  

                openConnection();
                prepareStatement("INSERT INTO entity_status(StatusCode,StatusName,AppliesTo) VALUES (?,?,?);");                    
                preparedStatement.setInt(1, obj.getStatusCode());
                preparedStatement.setString(2, obj.getStatusName());
                preparedStatement.setString(3, obj.getAppliesTo());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from entity_status;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("EntityStatus's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setEntityStatusId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public EntityStatus update(EntityStatus obj)
        {
           try
            {   
                
                
                EntityStatus.checkColumnSize(obj.getStatusName(), 100);
                EntityStatus.checkColumnSize(obj.getAppliesTo(), 100);
                                  
                openConnection();                           
                prepareStatement("UPDATE entity_status SET StatusCode=?,StatusName=?,AppliesTo=? WHERE EntityStatusId=?;");                    
                preparedStatement.setInt(1, obj.getStatusCode());
                preparedStatement.setString(2, obj.getStatusName());
                preparedStatement.setString(3, obj.getAppliesTo());
                preparedStatement.setInt(4, obj.getEntityStatusId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getEntityStatusId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("EntityStatus's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("entity_status");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(EntityStatus entity_status)
        {
              
        }
        
        @Override
        public void getRelatedObjects(EntityStatus entity_status)
        {
             
        }
        
        @Override
        public boolean remove(EntityStatus obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM entity_status WHERE EntityStatusId=" + obj.getEntityStatusId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("EntityStatus's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getEntityStatusId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM entity_status WHERE EntityStatusId=" + id + ";");           
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
                updateQuery("DELETE FROM entity_status;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("EntityStatus's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM entity_status WHERE " + EntityStatus.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("EntityStatus's removeByColumn method error: " + ex.getMessage());
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
                        EntityStatus i = (EntityStatus) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getEntityStatusId());
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

