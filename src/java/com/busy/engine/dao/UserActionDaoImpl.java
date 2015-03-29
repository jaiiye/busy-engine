






















































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
    
    public class UserActionDaoImpl extends BasicConnection implements Serializable, UserActionDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public UserActionDaoImpl()
        {
            cachingEnabled = false;
        }

        public UserActionDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class UserActionCache
        {
            public static final ConcurrentLruCache<Integer, UserAction> userActionCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (UserAction i : findAll())
                {
                    getCache().put(i.getUserActionId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, UserAction> getCache()
        {
            return UserActionCache.userActionCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, UserAction> buildCache(ArrayList<UserAction> userActionList)
        {        
            ConcurrentLruCache<Integer, UserAction> cache = new ConcurrentLruCache<Integer, UserAction>(userActionList.size() + 1000);
            for (UserAction i : userActionList)
            {
                cache.put(i.getUserActionId(), i);
            }
            return cache;
        }

        private static ArrayList<UserAction> findAll()
        {
            ArrayList<UserAction> userAction = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("userAction");
                while (rs.next())
                {
                    userAction.add(UserAction.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("UserAction object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return userAction;
        }
        
        @Override
        public UserAction find(Integer id)
        {
            return findByColumn("UserActionId", id.toString(), null, null).get(0);
        }
        
        @Override
        public UserAction findWithInfo(Integer id)
        {
            UserAction userAction = findByColumn("UserActionId", id.toString(), null, null).get(0);
            
            
                try
                {

                
                    getRecordById("UserActionType", userAction.getUserActionTypeId().toString());
                    userAction.setUserActionType(UserActionType.process(rs));               
                
                    getRecordById("User", userAction.getUserId().toString());
                    userAction.setUser(User.process(rs));               
                  

                }
                catch (SQLException ex)
                {
                        System.out.println("Object UserAction method findWithInfo(Integer id) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            
            
            return userAction;
        }
        
        @Override
        public ArrayList<UserAction> findAll(Integer limit, Integer offset)
        {
            ArrayList<UserAction> userActionList = new ArrayList<UserAction>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for UserAction, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    userActionList = new ArrayList<UserAction>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("user_action", limit, offset);
                    while (rs.next())
                    {
                        userActionList.add(UserAction.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("UserAction object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return userActionList;
         
        }
        
        @Override
        public ArrayList<UserAction> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<UserAction> userActionList = new ArrayList<UserAction>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for UserAction, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    userActionList = new ArrayList<UserAction>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            UserAction userAction = (UserAction) e.getValue();

                            
                                getRecordById("UserActionType", userAction.getUserActionTypeId().toString());
                                userAction.setUserActionType(UserActionType.process(rs));               
                            
                                getRecordById("User", userAction.getUserId().toString());
                                userAction.setUser(User.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object UserAction method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                userActionList = new ArrayList<UserAction>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("user_action", limit, offset);
                    while (rs.next())
                    {
                        userActionList.add(UserAction.process(rs));
                    }

                    
                    
                        for (UserAction userAction : userActionList)
                        {                        
                            
                                getRecordById("UserActionType", userAction.getUserActionTypeId().toString());
                                userAction.setUserActionType(UserActionType.process(rs));               
                            
                                getRecordById("User", userAction.getUserId().toString());
                                userAction.setUser(User.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object UserAction method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return userActionList;            
        }
        
        @Override
        public ArrayList<UserAction> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<UserAction> userActionList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for UserAction(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            UserAction i = (UserAction) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                userActionList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            userActionList = null;
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
                    getRecordsByColumnWithLimitOrOffset("user_action", UserAction.checkColumnName(columnName), columnValue, UserAction.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        userActionList.add(UserAction.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("UserAction's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return userActionList;
        } 
    
        @Override
        public int add(UserAction obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                
                UserAction.checkColumnSize(obj.getDetail(), 65535);
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO user_action(Date,Detail,UserActionTypeId,UserId) VALUES (?,?,?,?);");                    
                preparedStatement.setDate(1, new java.sql.Date(obj.getDate().getTime()));
                preparedStatement.setString(2, obj.getDetail());
                preparedStatement.setInt(3, obj.getUserActionTypeId());
                preparedStatement.setInt(4, obj.getUserId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from user_action;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("UserAction's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setUserActionId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public UserAction update(UserAction obj)
        {
           try
            {   
                
                
                UserAction.checkColumnSize(obj.getDetail(), 65535);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE user_action SET Date=?,Detail=?,UserActionTypeId=?,UserId=? WHERE UserActionId=?;");                    
                preparedStatement.setDate(1, new java.sql.Date(obj.getDate().getTime()));
                preparedStatement.setString(2, obj.getDetail());
                preparedStatement.setInt(3, obj.getUserActionTypeId());
                preparedStatement.setInt(4, obj.getUserId());
                preparedStatement.setInt(5, obj.getUserActionId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getUserActionId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("UserAction's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("user_action");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(UserAction user_action)
        {
            
                try
                { 
                    
                            getRecordById("UserActionType", user_action.getUserActionTypeId().toString());
                            user_action.setUserActionType(UserActionType.process(rs));                                       
                    
                            getRecordById("User", user_action.getUserId().toString());
                            user_action.setUser(User.process(rs));                                       
                    
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
        public void getRelatedObjects(UserAction user_action)
        {
             
        }
        
        @Override
        public boolean remove(UserAction obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM user_action WHERE UserActionId=" + obj.getUserActionId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("UserAction's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getUserActionId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM user_action WHERE UserActionId=" + id + ";");           
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
                updateQuery("DELETE FROM user_action;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("UserAction's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM user_action WHERE " + UserAction.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("UserAction's removeByColumn method error: " + ex.getMessage());
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
                        UserAction i = (UserAction) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getUserActionId());
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
        
        
            
        
        
        public void getRelatedUserActionType(UserAction user_action)
        {            
            try
            {                 
                getRecordById("UserActionType", user_action.getUserActionTypeId().toString());
                user_action.setUserActionType(UserActionType.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getUserActionType error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
        
        public void getRelatedUser(UserAction user_action)
        {            
            try
            {                 
                getRecordById("User", user_action.getUserId().toString());
                user_action.setUser(User.process(rs));                                                       
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
          
        
                
        
        public void getRelatedUserActionTypeWithInfo(UserAction user_action)
        {            
            user_action.setUserActionType(new UserActionTypeDaoImpl().findWithInfo(user_action.getUserActionTypeId()));
        }
        
        public void getRelatedUserWithInfo(UserAction user_action)
        {            
            user_action.setUser(new UserDaoImpl().findWithInfo(user_action.getUserId()));
        }
          
        
    }

