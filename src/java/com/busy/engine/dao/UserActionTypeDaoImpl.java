






















































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
    
    public class UserActionTypeDaoImpl extends BasicConnection implements Serializable, UserActionTypeDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public UserActionTypeDaoImpl()
        {
            cachingEnabled = false;
        }

        public UserActionTypeDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class UserActionTypeCache
        {
            public static final ConcurrentLruCache<Integer, UserActionType> userActionTypeCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (UserActionType i : findAll())
                {
                    getCache().put(i.getUserActionTypeId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, UserActionType> getCache()
        {
            return UserActionTypeCache.userActionTypeCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, UserActionType> buildCache(ArrayList<UserActionType> userActionTypeList)
        {        
            ConcurrentLruCache<Integer, UserActionType> cache = new ConcurrentLruCache<Integer, UserActionType>(userActionTypeList.size() + 1000);
            for (UserActionType i : userActionTypeList)
            {
                cache.put(i.getUserActionTypeId(), i);
            }
            return cache;
        }

        private static ArrayList<UserActionType> findAll()
        {
            ArrayList<UserActionType> userActionType = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("userActionType");
                while (rs.next())
                {
                    userActionType.add(UserActionType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("UserActionType object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return userActionType;
        }
        
        @Override
        public UserActionType find(Integer id)
        {
            return findByColumn("UserActionTypeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public UserActionType findWithInfo(Integer id)
        {
            UserActionType userActionType = findByColumn("UserActionTypeId", id.toString(), null, null).get(0);
            
            
            
            return userActionType;
        }
        
        @Override
        public ArrayList<UserActionType> findAll(Integer limit, Integer offset)
        {
            ArrayList<UserActionType> userActionTypeList = new ArrayList<UserActionType>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for UserActionType, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    userActionTypeList = new ArrayList<UserActionType>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("user_action_type", limit, offset);
                    while (rs.next())
                    {
                        userActionTypeList.add(UserActionType.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("UserActionType object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return userActionTypeList;
         
        }
        
        @Override
        public ArrayList<UserActionType> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<UserActionType> userActionTypeList = new ArrayList<UserActionType>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for UserActionType, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    userActionTypeList = new ArrayList<UserActionType>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                userActionTypeList = new ArrayList<UserActionType>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("user_action_type", limit, offset);
                    while (rs.next())
                    {
                        userActionTypeList.add(UserActionType.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object UserActionType method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return userActionTypeList;            
        }
        
        @Override
        public ArrayList<UserActionType> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<UserActionType> userActionTypeList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for UserActionType(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            UserActionType i = (UserActionType) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                userActionTypeList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            userActionTypeList = null;
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
                    getRecordsByColumnWithLimitOrOffset("user_action_type", UserActionType.checkColumnName(columnName), columnValue, UserActionType.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        userActionTypeList.add(UserActionType.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("UserActionType's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return userActionTypeList;
        } 
    
        @Override
        public int add(UserActionType obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                UserActionType.checkColumnSize(obj.getTypeName(), 255);
                  

                openConnection();
                prepareStatement("INSERT INTO user_action_type(TypeName) VALUES (?);");                    
                preparedStatement.setString(1, obj.getTypeName());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from user_action_type;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("UserActionType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setUserActionTypeId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public UserActionType update(UserActionType obj)
        {
           try
            {   
                
                UserActionType.checkColumnSize(obj.getTypeName(), 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE user_action_type SET TypeName=? WHERE UserActionTypeId=?;");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setInt(2, obj.getUserActionTypeId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getUserActionTypeId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("UserActionType's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("user_action_type");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(UserActionType user_action_type)
        {
              
        }
        
        @Override
        public void getRelatedObjects(UserActionType user_action_type)
        {
            user_action_type.setUserActionList(new UserActionDaoImpl().findByColumn("UserActionTypeId", user_action_type.getUserActionTypeId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(UserActionType obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM user_action_type WHERE UserActionTypeId=" + obj.getUserActionTypeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("UserActionType's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getUserActionTypeId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM user_action_type WHERE UserActionTypeId=" + id + ";");           
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
                updateQuery("DELETE FROM user_action_type;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("UserActionType's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM user_action_type WHERE " + UserActionType.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("UserActionType's removeByColumn method error: " + ex.getMessage());
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
                        UserActionType i = (UserActionType) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getUserActionTypeId());
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
        
                    
        public void getRelatedUserActionList(UserActionType user_action_type)
        {           
            user_action_type.setUserActionList(new UserActionDaoImpl().findByColumn("UserActionTypeId", user_action_type.getUserActionTypeId().toString(),null,null));
        }        
        
            
        
          
        
                
          
        
    }

