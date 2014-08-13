package com.busy.engine.dao;

import com.busy.engine.entity.UserRole;
import com.busy.engine.data.BasicConnection;
import java.util.ArrayList;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;

public class UserRoleDaoImpl extends BasicConnection implements Serializable, UserRoleDao
{

    private static final long serialVersionUID = 1L;

    @Override
    public UserRole find(String id)
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
            System.out.println("UserRole's method findByColumn(columnName, columnValue, limit, offset) error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return user_role;
    }

    @Override
    public int add(UserRole obj)
    {
        int id = 0;
        try
        {
            UserRole.checkColumnSize(obj.getUserName(), 30);
            UserRole.checkColumnSize(obj.getRoleName(), 20);

            openConnection();
            prepareStatement("INSERT INTO user_role(RoleName) VALUES (?);");
            preparedStatement.setString(1, obj.getRoleName());

            preparedStatement.executeUpdate();

            rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from user_role;");
            while (rs.next())
            {
                id = rs.getInt(1);
            }
        }
        catch (Exception ex)
        {
            System.out.println("UserRole's add method error: " + ex.getMessage());
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
        return getAllRecordsCountByTableName("user_role");
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
            updateQuery("DELETE FROM user_role WHERE UserName=" + obj.getUserName() + " AND RoleName=" + obj.getRoleName() + ";");
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
        return success;
    }

    @Override
    public boolean removeById(String id)
    {
        boolean success = false;
        String[] parts = id.split(" ");
        try
        {
            updateQuery("DELETE FROM user_role WHERE UserName = " + parts[0] + " AND RoleName=" + parts[1] + ";");
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
        return success;
    }

}
