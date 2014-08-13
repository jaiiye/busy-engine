package com.busy.engine.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.json.JsonObjectBuilder;

public class UserRole extends AbstractEntity implements EntityItem<String>
{

    private static final long serialVersionUID = 1L;
    public static final String PROP_USER_NAME = "UserName";
    public static final String PROP_ROLE_NAME = "RoleName";

    private String userName;

    private String roleName;

    public UserRole()
    {
        this.userName = "";
        this.roleName = "";
    }

    @Override
    public String getId()
    {

        return userName +  "" + roleName;
    }

    @Override
    public void addJson(JsonObjectBuilder builder)
    {
        builder.add("userName", userName).add("roleName", roleName);
    }

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

    public static UserRole process(ResultSet rs) throws SQLException
    {
        return new UserRole(rs.getString(1), rs.getString(2));
    }

    public UserRole(String UserName, String RoleName)
    {
        this.userName = UserName;
        this.roleName = RoleName;

    }

    public String getUserName()
    {
        return this.userName;
    }

    public void setUserName(String UserName)
    {
        this.userName = UserName;
    }

    public String getRoleName()
    {
        return this.roleName;
    }

    public void setRoleName(String RoleName)
    {
        this.roleName = RoleName;
    }

}
