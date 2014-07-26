package com.busy.engine.dao.impl;

import com.transitionsoft.BasicConnection;
import com.busy.engine.domain.*;
import com.busy.engine.dao.base.*;
import java.util.ArrayList;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;

public class UserRoleDaoImpl extends BasicConnection implements Serializable, UserRoleDao
{

    private static final long serialVersionUID = 1L;

    @Override
    public UserRole find(Integer id)
    {
        return findByColumn("UserRoleId", id.toString(), null, null).get(0);
    }

    @Override
    public ArrayList<UserRole> findAll(Integer limit, Integer offset)
    {
        ArrayList<UserRole> user_role = new ArrayList<>();
        try
        {
            getAllRecordsByTableName("user_role");
            while (rs.next())
            {
                user_role.add(UserRole.process(rs));
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
        return user_role;

    }

    @Override
    public ArrayList<UserRole> findAllWithInfo(Integer limit, Integer offset)
    {
        ArrayList<UserRole> user_roleList = new ArrayList<>();
        try
        {
            getRecordsByTableNameWithLimitOrOffset("user_role", limit, offset);
            while (rs.next())
            {
                user_roleList.add(UserRole.process(rs));
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
        return user_roleList;
    }

    @Override
    public ArrayList<UserRole> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
    {
        ArrayList<UserRole> user_role = new ArrayList<>();
        try
        {
            getRecordsByColumnWithLimitOrOffset("user_role", UserRole.checkColumnName(columnName), columnValue, UserRole.isColumnNumeric(columnName), limit, offset);
            while (rs.next())
            {
                user_role.add(UserRole.process(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Object UserRole's method getByColumnPaged(columnName, columnValue, limit, offset) error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return user_role;
    }

    @Override
    public void add(UserRole obj)
    {
        try
        {
            UserRole.checkColumnSize(obj.getUserName(), 30);
            UserRole.checkColumnSize(obj.getRoleName(), 20);

            openConnection();
            prepareStatement("INSERT INTO user_role(RoleName) VALUES (?);");
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
    }

    public static int add(String UserName, String RoleName)
    {
        int id = 0;
        try
        {
            UserRole.checkColumnSize(UserName, 30);
            UserRole.checkColumnSize(RoleName, 20);

            openConnection();
            prepareStatement("INSERT INTO user_role(RoleName) VALUES (?);");
            preparedStatement.setString(1, RoleName);

            preparedStatement.executeUpdate();

            rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from user_role;");
            while (rs.next())
            {
                id = rs.getInt(1);
            }
        }
        catch (Exception ex)
        {
            System.out.println("addUserRole error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
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
            prepareStatement("UPDATE user_role SET RoleName=? WHERE UserName=?;");
            preparedStatement.setString(1, obj.getRoleName());
            preparedStatement.setString(2, obj.getUserName());
            preparedStatement.executeUpdate();
        }
        catch (Exception ex)
        {
            System.out.println("updateUserRole error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return obj;
    }

    public static void update(String UserName, String RoleName)
    {
        try
        {
            UserRole.checkColumnSize(UserName, 30);
            UserRole.checkColumnSize(RoleName, 20);

            openConnection();
            prepareStatement("UPDATE user_role SET RoleName=? WHERE UserName=?;");
            preparedStatement.setString(1, RoleName);
            preparedStatement.setString(2, UserName);
            preparedStatement.executeUpdate();
        }
        catch (Exception ex)
        {
            System.out.println("updateUserRole error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    @Override
    public int getAllCount()
    {
        return getAllRecordsCountByTableName("user_role");
    }

    @Override
    public UserRole getRelatedInfo(UserRole user_role)
    {

        return user_role;
    }

    @Override
    public UserRole getRelatedObjects(UserRole user_role)
    {

        return user_role;
    }

    @Override
    public void remove(UserRole obj)
    {
        try
        {
            updateQuery("DELETE FROM user_role WHERE UserName= " + obj.getUserName() + " AND UserRole=" + obj.getRoleName() + ";");
        }
        catch (Exception ex)
        {
            System.out.println("deleteUserRoleById error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    @Override
    public void remove(Integer id)
    {
        try
        {
            updateQuery("DELETE FROM user_role WHERE UserRoleId=" + id.intValue() + ";");
        }
        catch (Exception ex)
        {
            System.out.println("deleteUserRoleById error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    @Override
    public void removeAll()
    {
        try
        {
            updateQuery("DELETE FROM user_role;");
        }
        catch (Exception ex)
        {
            System.out.println("UserRole's deleteAll() method error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    @Override
    public void removeByColumn(String columnName, String columnValue)
    {
        try
        {
            updateQuery("DELETE FROM user_role WHERE " + UserRole.checkColumnName(columnName) + "=" + columnValue + ";");
        }
        catch (Exception ex)
        {
            System.out.println("UserRole's deleteByColumn method error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

}
