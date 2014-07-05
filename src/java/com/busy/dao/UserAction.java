package com.busy.dao;

import com.transitionsoft.*;
import java.util.ArrayList;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UserAction extends BasicConnection implements Serializable
{

    private static final long serialVersionUID = 1L;
    public static final String PROP_USERACTIONID = "UserActionId";
    public static final String PROP_USERID = "UserId";
    public static final String PROP_ACTIONDATE = "ActionDate";
    public static final String PROP_ACTIONTYPEID = "ActionTypeId";
    public static final String PROP_ACTIONDETAIL = "ActionDetail";

    private Integer userActionId;
    private Integer userId;
    private Date actionDate;
    private Integer actionTypeId;
    private String actionDetail;

    public UserAction()
    {
        this.userActionId = 0;
        this.userId = 0;
        this.actionDate = null;
        this.actionTypeId = 0;
        this.actionDetail = "";
    }

    public UserAction(Integer UserActionId, Integer UserId, Date ActionDate, Integer ActionTypeId, String ActionDetail)
    {
        this.userActionId = UserActionId;
        this.userId = UserId;
        this.actionDate = ActionDate;
        this.actionTypeId = ActionTypeId;
        this.actionDetail = ActionDetail;
    }

    public Integer getUserActionId()
    {
        return this.userActionId;
    }

    public void setUserActionId(Integer UserActionId)
    {
        this.userActionId = UserActionId;
    }

    public Integer getUserId()
    {
        return this.userId;
    }

    public void setUserId(Integer UserId)
    {
        this.userId = UserId;
    }

    public Date getActionDate()
    {
        return this.actionDate;
    }

    public void setActionDate(Date ActionDate)
    {
        this.actionDate = ActionDate;
    }

    public Integer getActionTypeId()
    {
        return this.actionTypeId;
    }

    public void setActionTypeId(Integer ActionTypeId)
    {
        this.actionTypeId = ActionTypeId;
    }

    public String getActionDetail()
    {
        return this.actionDetail;
    }

    public void setActionDetail(String ActionDetail)
    {
        this.actionDetail = ActionDetail;
    }

    public static String checkColumnName(String column) throws SQLException
    {
        if (column.equals(UserAction.PROP_USERACTIONID) || column.equals(UserAction.PROP_USERID) || column.equals(UserAction.PROP_ACTIONDATE) || column.equals(UserAction.PROP_ACTIONTYPEID) || column.equals(UserAction.PROP_ACTIONDETAIL))
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
        if (column.equals(UserAction.PROP_USERACTIONID) || column.equals(UserAction.PROP_USERID) || column.equals(UserAction.PROP_ACTIONTYPEID))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static UserAction processUserAction(ResultSet rs) throws SQLException
    {
        return new UserAction(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getInt(4), rs.getString(5));
    }

    public static int addUserAction(Integer UserId, Date ActionDate, Integer ActionTypeId, String ActionDetail)
    {
        int id = 0;
        try
        {

            checkColumnSize(ActionDetail, 65535);

            openConnection();
            prepareStatement("INSERT INTO user_action(UserId,ActionDate,ActionTypeId,ActionDetail) VALUES (?,?,?,?);");
            preparedStatement.setInt(1, UserId);
            preparedStatement.setDate(2, new java.sql.Date(ActionDate.getTime()));
            preparedStatement.setInt(3, ActionTypeId);
            preparedStatement.setString(4, ActionDetail);

            preparedStatement.executeUpdate();

            rs = statement.executeQuery("SELECT DISTINCT LAST_INSERT_Id() from user_action;");
            while (rs.next())
            {
                id = rs.getInt(1);
            }
        }
        catch (Exception ex)
        {
            System.out.println("addUserAction error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return id;
    }

    public static int getAllUserActionCount()
    {
        return getAllRecordsCountByTableName("user_action");
    }

    public static ArrayList<UserAction> getAllUserAction()
    {
        ArrayList<UserAction> user_action = new ArrayList<UserAction>();
        try
        {
            getAllRecordsByTableName("user_action");
            while (rs.next())
            {
                user_action.add(processUserAction(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getAllUserAction error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return user_action;
    }

    public static ArrayList<UserAction> getUserActionPaged(int limit, int offset)
    {
        ArrayList<UserAction> user_action = new ArrayList<UserAction>();
        try
        {
            getRecordsByTableNameWithLimitAndOffset("user_action", limit, offset);
            while (rs.next())
            {
                user_action.add(processUserAction(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getUserActionPaged error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return user_action;
    }

    public static ArrayList<UserAction> getAllUserActionByColumn(String columnName, String columnValue)
    {
        ArrayList<UserAction> user_action = new ArrayList<UserAction>();
        try
        {
            getAllRecordsByColumn("user_action", checkColumnName(columnName), columnValue, isColumnNumeric(columnName));
            while (rs.next())
            {
                user_action.add(processUserAction(rs));
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getAllUserActionByColumn error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return user_action;
    }

    public static UserAction getUserActionByColumnPaged(String columnName, String columnValue, int limit, int offset)
    {
        UserAction user_action = new UserAction();
        try
        {
            getRecordsByColumnWithLimitAndOffset("user_action", checkColumnName(columnName), columnValue, isColumnNumeric(columnName), limit, offset);
            while (rs.next())
            {
                user_action = processUserAction(rs);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("getUserActionByColumnPaged error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
        return user_action;
    }

    public static void updateUserAction(Integer UserActionId, Integer UserId, Date ActionDate, Integer ActionTypeId, String ActionDetail)
    {
        try
        {

            checkColumnSize(ActionDetail, 65535);

            openConnection();
            prepareStatement("UPDATE user_action SET UserId=?,ActionDate=?,ActionTypeId=?,ActionDetail=? WHERE UserActionId=?;");
            preparedStatement.setInt(1, UserId);
            preparedStatement.setDate(2, new java.sql.Date(ActionDate.getTime()));
            preparedStatement.setInt(3, ActionTypeId);
            preparedStatement.setString(4, ActionDetail);
            preparedStatement.setInt(5, UserActionId);
            preparedStatement.executeUpdate();
        }
        catch (Exception ex)
        {
            System.out.println("updateUserAction error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteAllUserAction()
    {
        try
        {
            updateQuery("DELETE FROM user_action;");
        }
        catch (Exception ex)
        {
            System.out.println("deleteAllUserAction error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteUserActionById(String id)
    {
        try
        {
            updateQuery("DELETE FROM user_action WHERE UserActionId=" + id + ";");
        }
        catch (Exception ex)
        {
            System.out.println("deleteUserActionById error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

    public static void deleteUserActionByColumn(String columnName, String columnValue)
    {
        try
        {
            updateQuery("DELETE FROM user_action WHERE " + checkColumnName(columnName) + "=" + columnValue + ";");
        }
        catch (Exception ex)
        {
            System.out.println("deleteUserActionByColumn error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
        }
    }

}
