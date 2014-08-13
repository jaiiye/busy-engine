package com.busy.engine.dao;

import com.busy.engine.entity.UserGroup;
import com.busy.engine.entity.Contact;
import com.busy.engine.entity.Address;
import com.busy.engine.entity.User;
import com.busy.engine.entity.ItemBrand;
import com.busy.engine.entity.UserType;
import com.busy.engine.data.BasicConnection;
import java.util.ArrayList;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;

public class UserDaoImpl extends BasicConnection implements Serializable, UserDao
{

    private static final long serialVersionUID = 1L;

    @Override
    public User find(Integer id)
    {
        return findByColumn("UserId", id.toString(), null, null).get(0);
    }

    @Override
    public ArrayList<User> findAll(Integer limit, Integer offset)
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
    public ArrayList<User> findAllWithInfo(Integer limit, Integer offset)
    {
        ArrayList<User> userList = new ArrayList<>();
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
        return userList;
    }

    @Override
    public ArrayList<User> findByColumn(String columnName, String columnValue, Integer limit, Integer offset)
    {
        ArrayList<User> user = new ArrayList<>();
        try
        {
            getRecordsByColumnWithLimitOrOffset("user", User.checkColumnName(columnName), columnValue, User.isColumnNumeric(columnName), limit, offset);
            while (rs.next())
            {
                user.add(User.process(rs));
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
        return user;
    }

    @Override
    public int add(User obj)
    {
        int id = 0;
        try
        {
            User.checkColumnSize(obj.getUsername(), 30);
            User.checkColumnSize(obj.getPassword(), 15);
            User.checkColumnSize(obj.getEmail(), 255);
            User.checkColumnSize(obj.getSecurityQuestion(), 100);
            User.checkColumnSize(obj.getSecurityAnswer(), 30);
            User.checkColumnSize(obj.getImageURL(), 255);
            User.checkColumnSize(obj.getWebUrl(), 255);

            openConnection();
            prepareStatement("INSERT INTO user(Username,Password,Email,SecurityQuestion,SecurityAnswer,RegisterDate,ImageURL,UserStatus,Rank,WebUrl,ItemBrandId,UserTypeId,AddressId,ContactId,UserGroupId) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
            preparedStatement.setString(1, obj.getUsername());
            preparedStatement.setString(2, obj.getPassword());
            preparedStatement.setString(3, obj.getEmail());
            preparedStatement.setString(4, obj.getSecurityQuestion());
            preparedStatement.setString(5, obj.getSecurityAnswer());
            preparedStatement.setDate(6, new java.sql.Date(obj.getRegisterDate().getTime()));
            preparedStatement.setString(7, obj.getImageURL());
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
        }
        catch (Exception ex)
        {
            System.out.println("User's add method error: " + ex.getMessage());
        }
        finally
        {
            closeConnection();
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

            User.checkColumnSize(obj.getImageURL(), 255);

            User.checkColumnSize(obj.getWebUrl(), 255);

            openConnection();
            prepareStatement("UPDATE user SET Username=?,Password=?,Email=?,SecurityQuestion=?,SecurityAnswer=?,RegisterDate=?,ImageURL=?,UserStatus=?,Rank=?,WebUrl=?,ItemBrandId=?,UserTypeId=?,AddressId=?,ContactId=?,UserGroupId=? WHERE UserId=?;");
            preparedStatement.setString(1, obj.getUsername());
            preparedStatement.setString(2, obj.getPassword());
            preparedStatement.setString(3, obj.getEmail());
            preparedStatement.setString(4, obj.getSecurityQuestion());
            preparedStatement.setString(5, obj.getSecurityAnswer());
            preparedStatement.setDate(6, new java.sql.Date(obj.getRegisterDate().getTime()));
            preparedStatement.setString(7, obj.getImageURL());
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
        return getAllRecordsCountByTableName("user");
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
        user.setAffiliateList(new AffiliateDaoImpl().findByColumn("UserId", user.getUserId().toString(), null, null));
        user.setBlogPostList(new BlogPostDaoImpl().findByColumn("UserId", user.getUserId().toString(), null, null));
        user.setCommentList(new CommentDaoImpl().findByColumn("UserId", user.getUserId().toString(), null, null));
        user.setCustomerList(new CustomerDaoImpl().findByColumn("UserId", user.getUserId().toString(), null, null));
        user.setMailinglistList(new MailinglistDaoImpl().findByColumn("UserId", user.getUserId().toString(), null, null));
        user.setUserActionList(new UserActionDaoImpl().findByColumn("UserId", user.getUserId().toString(), null, null));
        user.setUserServiceList(new UserServiceDaoImpl().findByColumn("UserId", user.getUserId().toString(), null, null));

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
        return success;
    }

    public void getRelatedAffiliateList(User user)
    {
        user.setAffiliateList(new AffiliateDaoImpl().findByColumn("UserId", user.getUserId().toString(), null, null));
    }

    public void getRelatedBlogPostList(User user)
    {
        user.setBlogPostList(new BlogPostDaoImpl().findByColumn("UserId", user.getUserId().toString(), null, null));
    }

    public void getRelatedCommentList(User user)
    {
        user.setCommentList(new CommentDaoImpl().findByColumn("UserId", user.getUserId().toString(), null, null));
    }

    public void getRelatedCustomerList(User user)
    {
        user.setCustomerList(new CustomerDaoImpl().findByColumn("UserId", user.getUserId().toString(), null, null));
    }

    public void getRelatedMailinglistList(User user)
    {
        user.setMailinglistList(new MailinglistDaoImpl().findByColumn("UserId", user.getUserId().toString(), null, null));
    }

    public void getRelatedUserActionList(User user)
    {
        user.setUserActionList(new UserActionDaoImpl().findByColumn("UserId", user.getUserId().toString(), null, null));
    }

    public void getRelatedUserServiceList(User user)
    {
        user.setUserServiceList(new UserServiceDaoImpl().findByColumn("UserId", user.getUserId().toString(), null, null));
    }

}
