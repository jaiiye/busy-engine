






















































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
    
    public class UserTypeDaoImpl extends BasicConnection implements Serializable, UserTypeDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public UserTypeDaoImpl()
        {
            cachingEnabled = false;
        }

        public UserTypeDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class UserTypeCache
        {
            public static final ConcurrentLruCache<Integer, UserType> userTypeCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (UserType i : findAll())
                {
                    getCache().put(i.getUserTypeId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, UserType> getCache()
        {
            return UserTypeCache.userTypeCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, UserType> buildCache(ArrayList<UserType> userTypeList)
        {        
            ConcurrentLruCache<Integer, UserType> cache = new ConcurrentLruCache<Integer, UserType>(userTypeList.size() + 1000);
            for (UserType i : userTypeList)
            {
                cache.put(i.getUserTypeId(), i);
            }
            return cache;
        }

        private static ArrayList<UserType> findAll()
        {
            ArrayList<UserType> userType = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("userType");
                while (rs.next())
                {
                    userType.add(UserType.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("UserType object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return userType;
        }
        
        @Override
        public UserType find(Integer id)
        {
            return findByColumn("UserTypeId", id.toString(), null, null).get(0);
        }
        
        @Override
        public UserType findWithInfo(Integer id)
        {
            UserType userType = findByColumn("UserTypeId", id.toString(), null, null).get(0);
            
            
            
            return userType;
        }
        
        @Override
        public ArrayList<UserType> findAll(Integer limit, Integer offset)
        {
            ArrayList<UserType> userTypeList = new ArrayList<UserType>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for UserType, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    userTypeList = new ArrayList<UserType>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("user_type", limit, offset);
                    while (rs.next())
                    {
                        userTypeList.add(UserType.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("UserType object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return userTypeList;
         
        }
        
        @Override
        public ArrayList<UserType> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<UserType> userTypeList = new ArrayList<UserType>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for UserType, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    userTypeList = new ArrayList<UserType>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                userTypeList = new ArrayList<UserType>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("user_type", limit, offset);
                    while (rs.next())
                    {
                        userTypeList.add(UserType.process(rs));
                    }

                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object UserType method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return userTypeList;            
        }
        
        @Override
        public ArrayList<UserType> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<UserType> userTypeList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for UserType(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            UserType i = (UserType) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                userTypeList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            userTypeList = null;
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
                    getRecordsByColumnWithLimitOrOffset("user_type", UserType.checkColumnName(columnName), columnValue, UserType.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        userTypeList.add(UserType.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("UserType's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return userTypeList;
        } 
    
        @Override
        public int add(UserType obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                UserType.checkColumnSize(obj.getTypeName(), 45);
                UserType.checkColumnSize(obj.getDescription(), 255);
                UserType.checkColumnSize(obj.getRedirectUrl(), 255);
                  

                openConnection();
                prepareStatement("INSERT INTO user_type(TypeName,Description,RedirectUrl) VALUES (?,?,?);");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getRedirectUrl());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from user_type;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("UserType's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setUserTypeId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public UserType update(UserType obj)
        {
           try
            {   
                
                UserType.checkColumnSize(obj.getTypeName(), 45);
                UserType.checkColumnSize(obj.getDescription(), 255);
                UserType.checkColumnSize(obj.getRedirectUrl(), 255);
                                  
                openConnection();                           
                prepareStatement("UPDATE user_type SET TypeName=?,Description=?,RedirectUrl=? WHERE UserTypeId=?;");                    
                preparedStatement.setString(1, obj.getTypeName());
                preparedStatement.setString(2, obj.getDescription());
                preparedStatement.setString(3, obj.getRedirectUrl());
                preparedStatement.setInt(4, obj.getUserTypeId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getUserTypeId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("UserType's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("user_type");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(UserType user_type)
        {
              
        }
        
        @Override
        public void getRelatedObjects(UserType user_type)
        {
            user_type.setUserList(new UserDaoImpl().findByColumn("UserTypeId", user_type.getUserTypeId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(UserType obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM user_type WHERE UserTypeId=" + obj.getUserTypeId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("UserType's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getUserTypeId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM user_type WHERE UserTypeId=" + id + ";");           
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
                updateQuery("DELETE FROM user_type;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("UserType's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM user_type WHERE " + UserType.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("UserType's removeByColumn method error: " + ex.getMessage());
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
                        UserType i = (UserType) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getUserTypeId());
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
        
                    
        public void getRelatedUserList(UserType user_type)
        {           
            user_type.setUserList(new UserDaoImpl().findByColumn("UserTypeId", user_type.getUserTypeId().toString(),null,null));
        }        
        
            
        
          
        
                
          
        
    }

