






















































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
    
    public class UserGroupDaoImpl extends BasicConnection implements Serializable, UserGroupDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public UserGroupDaoImpl()
        {
            cachingEnabled = false;
        }

        public UserGroupDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class UserGroupCache
        {
            public static final ConcurrentLruCache<Integer, UserGroup> userGroupCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (UserGroup i : findAll())
                {
                    getCache().put(i.getUserGroupId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, UserGroup> getCache()
        {
            return UserGroupCache.userGroupCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, UserGroup> buildCache(ArrayList<UserGroup> userGroupList)
        {        
            ConcurrentLruCache<Integer, UserGroup> cache = new ConcurrentLruCache<Integer, UserGroup>(userGroupList.size() + 1000);
            for (UserGroup i : userGroupList)
            {
                cache.put(i.getUserGroupId(), i);
            }
            return cache;
        }

        private static ArrayList<UserGroup> findAll()
        {
            ArrayList<UserGroup> userGroup = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("userGroup");
                while (rs.next())
                {
                    userGroup.add(UserGroup.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("UserGroup object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return userGroup;
        }
        
        @Override
        public UserGroup find(Integer id)
        {
            return findByColumn("UserGroupId", id.toString(), null, null).get(0);
        }
        
        @Override
        public UserGroup findWithInfo(Integer id)
        {
            UserGroup userGroup = findByColumn("UserGroupId", id.toString(), null, null).get(0);
            
            
                try
                {

                
                    getRecordById("Site", userGroup.getSiteId().toString());
                    userGroup.setSite(Site.process(rs));               
                
                    getRecordById("Discount", userGroup.getDiscountId().toString());
                    userGroup.setDiscount(Discount.process(rs));               
                  

                }
                catch (SQLException ex)
                {
                        System.out.println("Object UserGroup method findWithInfo(Integer id) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            
            
            return userGroup;
        }
        
        @Override
        public ArrayList<UserGroup> findAll(Integer limit, Integer offset)
        {
            ArrayList<UserGroup> userGroupList = new ArrayList<UserGroup>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for UserGroup, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    userGroupList = new ArrayList<UserGroup>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("user_group", limit, offset);
                    while (rs.next())
                    {
                        userGroupList.add(UserGroup.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("UserGroup object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return userGroupList;
         
        }
        
        @Override
        public ArrayList<UserGroup> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<UserGroup> userGroupList = new ArrayList<UserGroup>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for UserGroup, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    userGroupList = new ArrayList<UserGroup>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            UserGroup userGroup = (UserGroup) e.getValue();

                            
                                getRecordById("Site", userGroup.getSiteId().toString());
                                userGroup.setSite(Site.process(rs));               
                            
                                getRecordById("Discount", userGroup.getDiscountId().toString());
                                userGroup.setDiscount(Discount.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object UserGroup method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                userGroupList = new ArrayList<UserGroup>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("user_group", limit, offset);
                    while (rs.next())
                    {
                        userGroupList.add(UserGroup.process(rs));
                    }

                    
                    
                        for (UserGroup userGroup : userGroupList)
                        {                        
                            
                                getRecordById("Site", userGroup.getSiteId().toString());
                                userGroup.setSite(Site.process(rs));               
                            
                                getRecordById("Discount", userGroup.getDiscountId().toString());
                                userGroup.setDiscount(Discount.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object UserGroup method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return userGroupList;            
        }
        
        @Override
        public ArrayList<UserGroup> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<UserGroup> userGroupList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for UserGroup(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            UserGroup i = (UserGroup) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                userGroupList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            userGroupList = null;
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
                    getRecordsByColumnWithLimitOrOffset("user_group", UserGroup.checkColumnName(columnName), columnValue, UserGroup.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        userGroupList.add(UserGroup.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("UserGroup's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return userGroupList;
        } 
    
        @Override
        public int add(UserGroup obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                UserGroup.checkColumnSize(obj.getGroupName(), 100);
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO user_group(GroupName,SiteId,DiscountId) VALUES (?,?,?);");                    
                preparedStatement.setString(1, obj.getGroupName());
                preparedStatement.setInt(2, obj.getSiteId());
                preparedStatement.setInt(3, obj.getDiscountId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from user_group;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("UserGroup's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setUserGroupId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public UserGroup update(UserGroup obj)
        {
           try
            {   
                
                UserGroup.checkColumnSize(obj.getGroupName(), 100);
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE user_group SET GroupName=?,SiteId=?,DiscountId=? WHERE UserGroupId=?;");                    
                preparedStatement.setString(1, obj.getGroupName());
                preparedStatement.setInt(2, obj.getSiteId());
                preparedStatement.setInt(3, obj.getDiscountId());
                preparedStatement.setInt(4, obj.getUserGroupId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getUserGroupId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("UserGroup's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("user_group");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(UserGroup user_group)
        {
            
                try
                { 
                    
                            getRecordById("Site", user_group.getSiteId().toString());
                            user_group.setSite(Site.process(rs));                                       
                    
                            getRecordById("Discount", user_group.getDiscountId().toString());
                            user_group.setDiscount(Discount.process(rs));                                       
                    
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
        public void getRelatedObjects(UserGroup user_group)
        {
            user_group.setUserList(new UserDaoImpl().findByColumn("UserGroupId", user_group.getUserGroupId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(UserGroup obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM user_group WHERE UserGroupId=" + obj.getUserGroupId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("UserGroup's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getUserGroupId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM user_group WHERE UserGroupId=" + id + ";");           
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
                updateQuery("DELETE FROM user_group;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("UserGroup's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM user_group WHERE " + UserGroup.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("UserGroup's removeByColumn method error: " + ex.getMessage());
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
                        UserGroup i = (UserGroup) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getUserGroupId());
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
        
                    
        public void getRelatedUserList(UserGroup user_group)
        {           
            user_group.setUserList(new UserDaoImpl().findByColumn("UserGroupId", user_group.getUserGroupId().toString(),null,null));
        }        
        
            
        
        
        public void getRelatedSite(UserGroup user_group)
        {            
            try
            {                 
                getRecordById("Site", user_group.getSiteId().toString());
                user_group.setSite(Site.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getSite error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
        
        public void getRelatedDiscount(UserGroup user_group)
        {            
            try
            {                 
                getRecordById("Discount", user_group.getDiscountId().toString());
                user_group.setDiscount(Discount.process(rs));                                                       
            }
            catch (SQLException ex)
            {
                System.out.println("getDiscount error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }                            
        }
          
        
                
        
        public void getRelatedSiteWithInfo(UserGroup user_group)
        {            
            user_group.setSite(new SiteDaoImpl().findWithInfo(user_group.getSiteId()));
        }
        
        public void getRelatedDiscountWithInfo(UserGroup user_group)
        {            
            user_group.setDiscount(new DiscountDaoImpl().findWithInfo(user_group.getDiscountId()));
        }
          
        
    }

