package com.busy.engine.service;

import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class UserServiceImpl extends AbstractService implements UserService
{
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();

    public UserServiceImpl()
    {
        super();
    }

    @Override
    public Result<User> find(String userName, Integer id)
    {

        if (isValidUser(userName))
        {
            return ResultFactory.getSuccessResult(userDao.find(id));
        }
        else
        {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    @Override
    public Result<List<User>> findAll(String userName)
    {
        if (isValidUser(userName))
        {
            List<User> userList = userDao.findAll(null, null);
            return ResultFactory.getSuccessResult(userList);
        }
        else
        {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    @Override
    public Result<User> store(String userName, Integer id, String username, String password, String email, String securityQuestion, String securityAnswer, Date registerDate, String imageURL, Integer userStatus, Integer rank, String webUrl, Integer itemBrandId, Integer userTypeId, Integer addressId, Integer contactId, Integer userGroupId)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);

        if (!checkUserRoles(roles))
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        User user;

        if (id == null)
        {
            user = new User();
        }
        else
        {
            user = userDao.find(id);

            if (user == null)
            {
                return ResultFactory.getFailResult("Unable to find User instance with Id=" + id);
            }
        }

        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setSecurityQuestion(securityQuestion);
        user.setSecurityAnswer(securityAnswer);
        user.setRegisterDate(registerDate);
        user.setImageURL(imageURL);
        user.setUserStatus(userStatus);
        user.setRank(rank);
        user.setWebUrl(webUrl);
        user.setItemBrandId(itemBrandId);
        user.setUserTypeId(userTypeId);
        user.setAddressId(addressId);
        user.setContactId(contactId);
        user.setUserGroupId(userGroupId);

        if (user.getId() == null)
        {
            userDao.add(user);
        }
        else
        {
            user = userDao.update(user);
        }

        return ResultFactory.getSuccessResult(user);

    }

    @Override
    public Result<User> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);

        if (!checkUserRoles(roles))
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null)
        {
            return ResultFactory.getFailResult("Unable to remove User [null id]");
        }

        User user = userDao.find(id);

        if (user == null)
        {
            return ResultFactory.getFailResult("Unable to load User for removal with id=" + id);
        }
        else
        {
            //if all related objects are empty for the given object then we can erase this object
            userDao.getRelatedObjects(user);

            String relatedObjectNames = "";
            boolean canBeDeleted = true;

            if (user.getAffiliateList().size() != 0)
            {
                relatedObjectNames += "Affiliate ";
                canBeDeleted = false;
            }

            if (user.getBlogPostList().size() != 0)
            {
                relatedObjectNames += "BlogPost ";
                canBeDeleted = false;
            }

            if (user.getCommentList().size() != 0)
            {
                relatedObjectNames += "Comment ";
                canBeDeleted = false;
            }

            if (user.getCustomerList().size() != 0)
            {
                relatedObjectNames += "Customer ";
                canBeDeleted = false;
            }

            if (user.getMailinglistList().size() != 0)
            {
                relatedObjectNames += "Mailinglist ";
                canBeDeleted = false;
            }

            if (user.getUserActionList().size() != 0)
            {
                relatedObjectNames += "UserAction ";
                canBeDeleted = false;
            }

            if (user.getUserServiceList().size() != 0)
            {
                relatedObjectNames += "UserService ";
                canBeDeleted = false;
            }

            if (canBeDeleted)
            {
                userDao.remove(user);

                String msg = "User with Id: " + user.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);
            }
            else
            {
                return ResultFactory.getFailResult("User is used with to [" + relatedObjectNames + "] and could not be deleted");
            }

        }

    }
    
    @Override
    public Result<User> findByUsernamePassword(String userName, String password)
    {
        User user = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        
        if(user == null)
        {
            return ResultFactory.getFailResult("Unable to verify user/password combination!");
        } 
        else 
        {
            if(user.getPassword().equals(password))
            {
                return ResultFactory.getSuccessResult(user);
            }
            else
            {                
                return ResultFactory.getFailResult("Unable to verify user/password combination!");
            }            
        }
    }

}
