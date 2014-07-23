package com.busy.dao;

import com.transitionsoft.BasicConnection;
import com.busy.entity.*;
import java.util.ArrayList;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UserRoleDAO extends BasicConnection implements Serializable
{

    private static final long serialVersionUID = 1L;

    public static String checkColumnName(String column) throws SQLException
    {
        if (column.equals(UserRole.PROP_USER_NAME) || column.equals(UserRole.PROP_ROLE_NAME))
        {
            return column;
        }
        else
        {
            throw new SQLException("Invalid column name: " + column);
        }
    }

    public static void checkColumnSize(String column, int size) throws Exception
    {
        if (column.length() > size)
        {
            throw new Exception("Invalid column length: " + size + "instead of " + column.length() + " for column: " + column);
        }
    }

    public static boolean isColumnNumeric(String column)
    {
        return false;     
    }

    public static UserRole processUserRole(ResultSet rs) throws SQLException
    {
        return new UserRole(rs.getString(1), rs.getString(2));
    }

    public static int addUserRole(String UserName, String RoleName)
    {
        int id = 0;
        try
        {
            checkColumnSize(UserName, 30);
            checkColumnSize(RoleName, 20);

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

    public static int getAllUserRoleCount()
    {
        return getAllRecordsCountByTableName("user_role");
    }

    public static ArrayList<UserRole> getAllUserRole()
    {
        ArrayList<UserRole> user_role = new ArrayList<UserRole>();
        try
        {
            getAllRecordsByTableName("user_role");
            while (rs.next())
            {
                user_role.add(processUserRole(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getAllUserRole error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return user_role;
    }

    public static ArrayList<UserRole> getAllUserRoleWithRelatedInfo()
    {
        ArrayList<UserRole> user_roleList = new ArrayList<UserRole>();
        try
        {
            getAllRecordsByTableName("user_role");
            while (rs.next())
            {
                user_roleList.add(processUserRole(rs));
            }

        }
        catch (SQLException ex)
        {
            System.out.println("getAllUserRoleWithRelatedInfo error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return user_roleList;
    }

    public static UserRole getRelatedInfo(UserRole user_role)
    {

        return user_role;
    }

    public static UserRole getAllRelatedObjects(UserRole user_role)
    {

        return user_role;
    }

    public static ArrayList<UserRole> getUserRolePaged(int limit, int offset)
    {
        ArrayList<UserRole> user_role = new ArrayList<UserRole>();
        try
        {
            getRecordsByTableNameWithLimitAndOffset("user_role", limit, offset);
            while (rs.next())
            {
                user_role.add(processUserRole(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getUserRolePaged error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return user_role;
    }

    public static ArrayList<UserRole> getAllUserRoleByColumn(String columnName, String columnValue)
    {
        ArrayList<UserRole> user_role = new ArrayList<UserRole>();
        try
        {
            getAllRecordsByColumn("user_role", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
            while (rs.next())
            {
                user_role.add(processUserRole(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getAllUserRoleByColumn error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return user_role;
    }

    public static UserRole getUserRoleByColumnPaged(String columnName, String columnValue, int limit, int offset)
    {
        UserRole user_role = new UserRole();
        try
        {
            getRecordsByColumnWithLimitAndOffset("user_role", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
            while (rs.next())
            {
                user_role = processUserRole(rs);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getUserRoleByColumnPaged error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return user_role;
    }

    public static void updateUserRole(String UserName, String RoleName)
    {
        try
        {
            checkColumnSize(UserName, 30);
            checkColumnSize(RoleName, 20);

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

    public static void deleteAllUserRole()
    {
        try
        {
            updateQuery("DELETE FROM user_role;");
        }
        catch (Exception ex)
        {
            System.out.println("deleteAllUserRole error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteUserRoleById(String id)
    {
        try
        {
            updateQuery("DELETE FROM user_role WHERE UserRoleId=" + id + ";");
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

    public static void deleteUserRoleByColumn(String columnName, String columnValue)
    {
        try
        {
            updateQuery("DELETE FROM user_role WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");
        }
        catch (Exception ex)
        {
            System.out.println("deleteUserRoleByColumn error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

}
