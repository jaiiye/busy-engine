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

public class UserRoleDaoImpl extends BasicConnection implements Serializable, UserRoleDao
{

    private static final long serialVersionUID = 1L;
    private boolean cachingEnabled;

    public UserRoleDaoImpl()
    {
        cachingEnabled = false;
    }

    public UserRoleDaoImpl(boolean enableCache)
    {
        cachingEnabled = enableCache;
    }

    
    @Override
    public UserRole find(String id)
    {
        return findByColumn("UserRole", id, null, null).get(0);
    }

    private static class UserRoleCache
    {

        public static final ConcurrentLruCache<String, UserRole> userRoleCache = buildCache(findAll());
    }

    private void checkCacheState()
    {
        if (getCache().size() == 0)
        {
            System.out.println("Found the cache empty, rebuilding...");
            for (UserRole i : findAll())
            {
                getCache().put(i.getId(), i);
            }
        }
    }

    public static ConcurrentLruCache<String, UserRole> getCache()
    {
        return UserRoleCache.userRoleCache;
    }

    protected Object readResolve()
    {
        return getCache();
    }

    public static ConcurrentLruCache<String, UserRole> buildCache(ArrayList<UserRole> userRoleList)
    {
        ConcurrentLruCache<String, UserRole> cache = new ConcurrentLruCache<String, UserRole>(userRoleList.size() + 1000);
        for (UserRole i : userRoleList)
        {
            cache.put(i.getId(), i);
        }
        return cache;
    }

    private static ArrayList<UserRole> findAll()
    {
        ArrayList<UserRole> userRole = new ArrayList<>();
        try
        {
            getAllRecordsByTableName("userRole");
            while (rs.next())
            {
                userRole.add(UserRole.process(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("UserRole object's findAll() method error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return userRole;
    }

    @Override
    public ArrayList<UserRole> findAll(Integer limit, Integer offset)
    {
        ArrayList<UserRole> userRoleList = new ArrayList<UserRole>();
        boolean cacheNotUsed = false;

        //check cache first
        if (cachingEnabled)
        {
            System.out.println("Find all operation for UserRole, getting objects from cache...");
            checkCacheState();

            if (limit == null && offset == null)
            {
                userRoleList = new ArrayList<UserRole>(getCache().getValues());
            }
            else
            {
                cacheNotUsed = true;
            }
        }

        if (!cachingEnabled || cacheNotUsed)
        {
            try
            {
                getRecordsByTableNameWithLimitOrOffset("user_role", limit, offset);
                while (rs.next())
                {
                    userRoleList.add(UserRole.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("UserRole object's findAll method error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        return userRoleList;

    }

    @Override
    public ArrayList<UserRole> findAllWithInfo(Integer limit, Integer offset)
    {
        ArrayList<UserRole> userRoleList = new ArrayList<UserRole>();
        boolean cacheNotUsed = false;

        //check cache first
        if (cachingEnabled)
        {
            checkCacheState();

            System.out.println("Find all with info operation for UserRole, getting objects from cache...");

            if (limit == null && offset == null)
            {
                userRoleList = new ArrayList<UserRole>(getCache().getValues());
            }
            else
            {
                cacheNotUsed = true;
            }

        }

        if (!cachingEnabled || cacheNotUsed)
        {
            userRoleList = new ArrayList<UserRole>();
            try
            {
                getRecordsByTableNameWithLimitOrOffset("user_role", limit, offset);
                while (rs.next())
                {
                    userRoleList.add(UserRole.process(rs));
                }

            }
            catch (SQLException ex)
            {
                System.out.println("Object UserRole method findAllWithInfo(Integer, Integer) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        return userRoleList;
    }

    @Override
    public ArrayList<UserRole> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
    {
        ArrayList<UserRole> userRoleList = new ArrayList<>();
        boolean cacheNotUsed = false;

        if (cachingEnabled)
        {
            if (limit == null && offset == null)
            {

                System.out.println("Find by column for UserRole(" + columnName + "=" + columnValue + "), getting objects from cache...");
                for (Entry e : getCache().getEntries())
                {
                    try
                    {
                        UserRole i = (UserRole) e.getValue();
                        if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                        {
                            userRoleList.add(i);
                        }
                    }
                    catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                    {
                        ex.printStackTrace();
                        userRoleList = null;
                    }
                }
            }
            else
            {
                cacheNotUsed = true;
            }
        }

        if (!cachingEnabled || cacheNotUsed)
        {
            try
            {
                getRecordsByColumnWithLimitOrOffset("user_role", UserRole.checkColumnName(columnName), columnValue, UserRole.isColumnNumeric(columnName), limit, offset);
                while (rs.next())
                {
                    userRoleList.add(UserRole.process(rs));
                }
            }
            catch (SQLException ex)
            {
                System.out.println("UserRole's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
            }
            finally
            {
                closeConnection();
            }
        }
        return userRoleList;
    }

    @Override
    public int add(UserRole obj)
    {
        boolean success = false;
        int id = 0;
        try
        {
            UserRole.checkColumnSize(obj.getUserName(), 30);
            UserRole.checkColumnSize(obj.getRoleName(), 20);

            openConnection();
            prepareStatement("INSERT INTO user_role(UserName,RoleName) VALUES (?,?);");
            preparedStatement.setString(0, obj.getUserName());
            preparedStatement.setString(1, obj.getRoleName());

            preparedStatement.executeUpdate();
        }
        catch (Exception ex)
        {
            System.out.println("UserRole's add method error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        
        if (cachingEnabled && success)
        {            
            getCache().put(obj.getId(), obj); //synchronizing between local cache and database
        }
        return id;
    }

    @Override
    public UserRole update(UserRole obj)
    {
        try
        {
            UserRole.checkColumnSize(obj.getUserName(), 30);
            UserRole.checkColumnSize(obj.getRoleName(), 20);

            openConnection();
            prepareStatement("UPDATE user_role SET com.busy.util.DatabaseColumn@24ec155d=? WHERE UserName=?;");
            preparedStatement.setString(0, obj.getUserName());
            preparedStatement.setString(1, obj.getRoleName());
            preparedStatement.setString(2, obj.getUserName());
            preparedStatement.executeUpdate();

            if (cachingEnabled)
            {
                getCache().put(obj.getId(), obj);
            }
        }
        catch (Exception ex)
        {
            System.out.println("UserRole's update error: " + ex.getMessage());
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
            count = getAllRecordsCountByTableName("user_role");
        }
        return count;
    }

    @Override
    public void getRelatedInfo(UserRole user_role)
    {

    }

    @Override
    public void getRelatedObjects(UserRole user_role)
    {

    }

    @Override
    public boolean remove(UserRole obj)
    {
        boolean success = false;
        try
        {
            updateQuery("DELETE FROM user_role WHERE UserRole=" + obj.getRoleName() + " AND UserName = " + obj.getUserName() + ";");
            success = true;
        }
        catch (Exception ex)
        {
            System.out.println("UserRole's remove error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }

        if (cachingEnabled && success)
        {
            getCache().remove(obj.getId());
        }

        return success;
    }

    @Override
    public boolean removeById(String id)
    {
        boolean success = false;
        try
        {
            String[] parts = id.split("-");
            updateQuery("DELETE FROM user_role WHERE RoleName=" + parts[1] + " AND UserName = " + parts[0] + ";");
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

        if (cachingEnabled && success)
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
            updateQuery("DELETE FROM user_role;");
            success = true;
        }
        catch (Exception ex)
        {
            System.out.println("UserRole's removeAll() method error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }

        if (cachingEnabled && success)
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
            updateQuery("DELETE FROM user_role WHERE " + UserRole.checkColumnName(columnName) + "=" + columnValue + ";");
            success = true;
        }
        catch (Exception ex)
        {
            System.out.println("UserRole's removeByColumn method error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }

        if (cachingEnabled && success)
        {
            ArrayList<String> keys = new ArrayList<String>();

            for (Entry e : getCache().getEntries())
            {
                try
                {
                    UserRole i = (UserRole) e.getValue();
                    if (i.getClass().getMethod("get" + columnName).invoke(i).toString().equals(columnValue))
                    {
                        keys.add(i.getId());
                    }
                }
                catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                {
                    ex.printStackTrace();
                }
            }

            for (String id : keys)
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
