






















































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
    
    public class UserDaoImpl extends BasicConnection implements Serializable, UserDao
    {    
        private static final long serialVersionUID = 1L;  
        private boolean cachingEnabled;
        
        public UserDaoImpl()
        {
            cachingEnabled = false;
        }

        public UserDaoImpl(boolean enableCache)
        {
            cachingEnabled = enableCache;
        }

        private static class UserCache
        {
            public static final ConcurrentLruCache<Integer, User> userCache = buildCache(findAll());
        }

        private void checkCacheState()
        {
            if(getCache().size() == 0)
            {
                System.out.println("Found the cache empty, rebuilding...");
                for (User i : findAll())
                {
                    getCache().put(i.getUserId(), i);
                } 
            }
        }

        public static ConcurrentLruCache<Integer, User> getCache()
        {
            return UserCache.userCache;
        }

        protected Object readResolve()
        {
            return getCache();
        }

        public static ConcurrentLruCache<Integer, User> buildCache(ArrayList<User> userList)
        {        
            ConcurrentLruCache<Integer, User> cache = new ConcurrentLruCache<Integer, User>(userList.size() + 1000);
            for (User i : userList)
            {
                cache.put(i.getUserId(), i);
            }
            return cache;
        }

        private static ArrayList<User> findAll()
        {
            ArrayList<User> user = new ArrayList<>();
            try
            {
                getAllRecordsByTableName("user");
                while (rs.next())
                {
                    user.add(User.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("User object's findAll() method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            return user;
        }
        
        @Override
        public User find(Integer id)
        {
            return findByColumn("UserId", id.toString(), null, null).get(0);
        }
        
        @Override
        public ArrayList<User> findAll(Integer limit, Integer offset)
        {
            ArrayList<User> userList = new ArrayList<User>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                System.out.println("Find all operation for User, getting objects from cache...");
                checkCacheState();

                if(limit == null && offset == null)
                {
                    userList = new ArrayList<User>(getCache().getValues());
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
                    getRecordsByTableNameWithLimitOrOffset("user", limit, offset);
                    while (rs.next())
                    {
                        userList.add(User.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("User object's findAll method error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return userList;
         
        }
        
        @Override
        public ArrayList<User> findAllWithInfo(Integer limit, Integer offset)
        {
            ArrayList<User> userList = new ArrayList<User>();
            boolean cacheNotUsed = false;

            //check cache first
            if (cachingEnabled)
            {            
                checkCacheState();

                System.out.println("Find all with info operation for User, getting objects from cache...");

                if (limit == null && offset == null)
                {
                    userList = new ArrayList<User>(getCache().getValues());
                }
                else
                {                
                    cacheNotUsed = true;
                }

                
                    try
                    {
                        for (Entry e : getCache().getEntries())
                        {
                            User user = (User) e.getValue();

                            
                                getRecordById("ItemBrand", user.getItemBrandId().toString());
                                user.setItemBrand(ItemBrand.process(rs));               
                            
                                getRecordById("UserType", user.getUserTypeId().toString());
                                user.setUserType(UserType.process(rs));               
                            
                                getRecordById("Address", user.getAddressId().toString());
                                user.setAddress(Address.process(rs));               
                            
                                getRecordById("Contact", user.getContactId().toString());
                                user.setContact(Contact.process(rs));               
                            
                                getRecordById("UserGroup", user.getUserGroupId().toString());
                                user.setUserGroup(UserGroup.process(rs));               
                                                    
                        }
                    }
                    catch (SQLException ex)
                    {
                        System.out.println("Object User method findAllWithInfo(Integer, Integer) using caching option error: " + ex.getMessage());
                    }
                    finally
                    {
                        closeConnection();
                    }
                
            }

            if( !cachingEnabled || cacheNotUsed)
            {
                userList = new ArrayList<User>();
                try
                {
                    getRecordsByTableNameWithLimitOrOffset("user", limit, offset);
                    while (rs.next())
                    {
                        userList.add(User.process(rs));
                    }

                    
                    
                        for (User user : userList)
                        {                        
                            
                                getRecordById("ItemBrand", user.getItemBrandId().toString());
                                user.setItemBrand(ItemBrand.process(rs));               
                            
                                getRecordById("UserType", user.getUserTypeId().toString());
                                user.setUserType(UserType.process(rs));               
                            
                                getRecordById("Address", user.getAddressId().toString());
                                user.setAddress(Address.process(rs));               
                            
                                getRecordById("Contact", user.getContactId().toString());
                                user.setContact(Contact.process(rs));               
                            
                                getRecordById("UserGroup", user.getUserGroupId().toString());
                                user.setUserGroup(UserGroup.process(rs));               
                              
                        }
                    
                    
                }
                catch (SQLException ex)
                {
                    System.out.println("Object User method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return userList;            
        }
        
        @Override
        public ArrayList<User> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
        {
            ArrayList<User> userList = new ArrayList<>();
            boolean cacheNotUsed = false;

            if (cachingEnabled)
            {
                if (limit == null && offset == null)
                {

                    System.out.println("Find by column for User(" + columnName + "=" + columnValue + "), getting objects from cache...");
                    for (Entry e : getCache().getEntries())
                    {
                        try
                        {
                            User i = (User) e.getValue();
                            if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                            {
                                userList.add(i);
                            }
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            ex.printStackTrace();
                            userList = null;
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
                    getRecordsByColumnWithLimitOrOffset("user", User.checkColumnName(columnName), columnValue, User.isColumnNumeric(columnName), limit, offset);
                    while (rs.next())
                    {
                        userList.add(User.process(rs));
                    }
                }
                catch (SQLException ex)
                {
                    System.out.println("User's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
                }
                finally
                {
                    closeConnection();
                }
            }
            return userList;
        } 
    
        @Override
        public int add(User obj)
        {        
            boolean success = false;
            int id = 0;
            try
            {                
                
                User.checkColumnSize(obj.getUsername(), 30);
                User.checkColumnSize(obj.getPassword(), 15);
                User.checkColumnSize(obj.getEmail(), 255);
                User.checkColumnSize(obj.getSecurityQuestion(), 100);
                User.checkColumnSize(obj.getSecurityAnswer(), 30);
                
                User.checkColumnSize(obj.getImageUrl(), 255);
                
                
                User.checkColumnSize(obj.getWebUrl(), 255);
                
                
                
                
                
                  

                openConnection();
                prepareStatement("INSERT INTO user(UserId,Username,Password,Email,SecurityQuestion,SecurityAnswer,RegisterDate,ImageUrl,UserStatus,Rank,WebUrl,ItemBrandId,UserTypeId,AddressId,ContactId,UserGroupId,) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");                    
                preparedStatement.setInt(0, obj.getUserId());
                preparedStatement.setString(1, obj.getUsername());
                preparedStatement.setString(2, obj.getPassword());
                preparedStatement.setString(3, obj.getEmail());
                preparedStatement.setString(4, obj.getSecurityQuestion());
                preparedStatement.setString(5, obj.getSecurityAnswer());
                preparedStatement.setDate(6, new java.sql.Date(obj.getRegisterDate().getTime()));
                preparedStatement.setString(7, obj.getImageUrl());
                preparedStatement.setInt(8, obj.getUserStatus());
                preparedStatement.setInt(9, obj.getRank());
                preparedStatement.setString(10, obj.getWebUrl());
                preparedStatement.setInt(11, obj.getItemBrandId());
                preparedStatement.setInt(12, obj.getUserTypeId());
                preparedStatement.setInt(13, obj.getAddressId());
                preparedStatement.setInt(14, obj.getContactId());
                preparedStatement.setInt(15, obj.getUserGroupId());
                
                preparedStatement.executeUpdate();

                rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from user;");
                while (rs.next())
                {
                    id = rs.getInt(1);
                }
                
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("User's add method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if (cachingEnabled && success)
            {
                obj.setUserId(id);
                getCache().put(id, obj); //synchronizing between local cache and database
            }
                
            return id;
        }
        
        @Override
        public User update(User obj)
        {
           try
            {   
                
                User.checkColumnSize(obj.getUsername(), 30);
                User.checkColumnSize(obj.getPassword(), 15);
                User.checkColumnSize(obj.getEmail(), 255);
                User.checkColumnSize(obj.getSecurityQuestion(), 100);
                User.checkColumnSize(obj.getSecurityAnswer(), 30);
                
                User.checkColumnSize(obj.getImageUrl(), 255);
                
                
                User.checkColumnSize(obj.getWebUrl(), 255);
                
                
                
                
                
                                  
                openConnection();                           
                prepareStatement("UPDATE user SET com.busy.util.DatabaseColumn@73b2c7eb=?,com.busy.util.DatabaseColumn@22630700=?,com.busy.util.DatabaseColumn@56a3354a=?,com.busy.util.DatabaseColumn@18a55580=?,com.busy.util.DatabaseColumn@7dcd54e0=?,com.busy.util.DatabaseColumn@472c075e=?,com.busy.util.DatabaseColumn@8ea7394=?,com.busy.util.DatabaseColumn@3a512e5c=?,com.busy.util.DatabaseColumn@2d2c07a2=?,com.busy.util.DatabaseColumn@a9df3ae=?,com.busy.util.DatabaseColumn@51b057ba=?,com.busy.util.DatabaseColumn@62a0b01e=?,com.busy.util.DatabaseColumn@2f7cbaff=?,com.busy.util.DatabaseColumn@4a992c3d=?,com.busy.util.DatabaseColumn@1947af53=? WHERE UserId=?;");                    
                preparedStatement.setInt(0, obj.getUserId());
                preparedStatement.setString(1, obj.getUsername());
                preparedStatement.setString(2, obj.getPassword());
                preparedStatement.setString(3, obj.getEmail());
                preparedStatement.setString(4, obj.getSecurityQuestion());
                preparedStatement.setString(5, obj.getSecurityAnswer());
                preparedStatement.setDate(6, new java.sql.Date(obj.getRegisterDate().getTime()));
                preparedStatement.setString(7, obj.getImageUrl());
                preparedStatement.setInt(8, obj.getUserStatus());
                preparedStatement.setInt(9, obj.getRank());
                preparedStatement.setString(10, obj.getWebUrl());
                preparedStatement.setInt(11, obj.getItemBrandId());
                preparedStatement.setInt(12, obj.getUserTypeId());
                preparedStatement.setInt(13, obj.getAddressId());
                preparedStatement.setInt(14, obj.getContactId());
                preparedStatement.setInt(15, obj.getUserGroupId());
                preparedStatement.setInt(16, obj.getUserId());
                preparedStatement.executeUpdate();
                
                if (cachingEnabled)
                {
                    getCache().put(obj.getUserId(), obj);
                }            
            }
            catch (Exception ex)
            {
                System.out.println("User's update error: " + ex.getMessage());
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
                count = getAllRecordsCountByTableName("user");
            }
            return count;
        }
        
        
        @Override
        public void getRelatedInfo(User user)
        {
            
                try
                { 
                    
                            getRecordById("ItemBrand", user.getItemBrandId().toString());
                            user.setItemBrand(ItemBrand.process(rs));                                       
                    
                            getRecordById("UserType", user.getUserTypeId().toString());
                            user.setUserType(UserType.process(rs));                                       
                    
                            getRecordById("Address", user.getAddressId().toString());
                            user.setAddress(Address.process(rs));                                       
                    
                            getRecordById("Contact", user.getContactId().toString());
                            user.setContact(Contact.process(rs));                                       
                    
                            getRecordById("UserGroup", user.getUserGroupId().toString());
                            user.setUserGroup(UserGroup.process(rs));                                       
                    
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
        public void getRelatedObjects(User user)
        {
            user.setAffiliateList(new AffiliateDaoImpl().findByColumn("UserId", user.getUserId().toString(),null,null));
user.setBlogPostList(new BlogPostDaoImpl().findByColumn("UserId", user.getUserId().toString(),null,null));
user.setCommentList(new CommentDaoImpl().findByColumn("UserId", user.getUserId().toString(),null,null));
user.setCustomerList(new CustomerDaoImpl().findByColumn("UserId", user.getUserId().toString(),null,null));
user.setMailinglistList(new MailinglistDaoImpl().findByColumn("UserId", user.getUserId().toString(),null,null));
user.setUserActionList(new UserActionDaoImpl().findByColumn("UserId", user.getUserId().toString(),null,null));
user.setUserServiceList(new UserServiceDaoImpl().findByColumn("UserId", user.getUserId().toString(),null,null));
 
        }
        
        @Override
        public boolean remove(User obj)
        {     
            boolean success = false;
            try
            {
                updateQuery("DELETE FROM user WHERE UserId=" + obj.getUserId() + ";");            
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("User's remove error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
            
            if(cachingEnabled && success)
            {
                getCache().remove(obj.getUserId());
            }
            
            return success;            
        }
        
        @Override
        public boolean removeById(Integer id)
        {      
            boolean success = false;      
            try
            {
                updateQuery("DELETE FROM user WHERE UserId=" + id + ";");           
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
                updateQuery("DELETE FROM user;");          
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("User's removeAll() method error: " + ex.getMessage());
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
                updateQuery("DELETE FROM user WHERE " + User.checkColumnName(columnName) + "=" + columnValue + ";");           
                success = true;       
            }
            catch (Exception ex)
            {
                System.out.println("User's removeByColumn method error: " + ex.getMessage());
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
                        User i = (User) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            keys.add(i.getUserId());
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
        
                    
        public void getRelatedAffiliateList(User user)
        {           
            user.setAffiliateList(new AffiliateDaoImpl().findByColumn("UserId", user.getUserId().toString(),null,null));
        }        
                    
        public void getRelatedBlogPostList(User user)
        {           
            user.setBlogPostList(new BlogPostDaoImpl().findByColumn("UserId", user.getUserId().toString(),null,null));
        }        
                    
        public void getRelatedCommentList(User user)
        {           
            user.setCommentList(new CommentDaoImpl().findByColumn("UserId", user.getUserId().toString(),null,null));
        }        
                    
        public void getRelatedCustomerList(User user)
        {           
            user.setCustomerList(new CustomerDaoImpl().findByColumn("UserId", user.getUserId().toString(),null,null));
        }        
                    
        public void getRelatedMailinglistList(User user)
        {           
            user.setMailinglistList(new MailinglistDaoImpl().findByColumn("UserId", user.getUserId().toString(),null,null));
        }        
                    
        public void getRelatedUserActionList(User user)
        {           
            user.setUserActionList(new UserActionDaoImpl().findByColumn("UserId", user.getUserId().toString(),null,null));
        }        
                    
        public void getRelatedUserServiceList(User user)
        {           
            user.setUserServiceList(new UserServiceDaoImpl().findByColumn("UserId", user.getUserId().toString(),null,null));
        }        
        
                             
    }

