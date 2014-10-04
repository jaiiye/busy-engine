package com.busy.engine.service;

import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;

import com.busy.engine.entity.UserRole;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class UserRoleServiceImpl extends AbstractService implements UserRoleService
{

    protected UserRoleDao userRoleDao;
    protected UserDao userDao;

    public UserRoleServiceImpl()
    {
        super();
        userRoleDao = new UserRoleDaoImpl();
        userDao = new UserDaoImpl();

    }

    public UserRoleServiceImpl(ServletContext context)
    {
        super();
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
        userDao = (UserDao) context.getAttribute("userDao");

    }

    @Override
    public Result<UserRole> find(String userName, String roleName)
    {
        try
        {
            if (isValidUser(userName, userDao))
            {
                return ResultFactory.getSuccessResult(userRoleDao.find(userName + "-" + roleName));
            }
            else
            {
                return ResultFactory.getFailResult(USER_INVALID);
            }
        }
        catch (Exception ex)
        {
            return ResultFactory.getFailResult(ex.getMessage());
        }
    }

    @Override
    public Result<List<UserRole>> findAll(String userName)
    {
        try
        {
            if (isValidUser(userName, userDao))
            {
                List<UserRole> userRoleList = userRoleDao.findAll(null, null);
                return ResultFactory.getSuccessResult(userRoleList);
            }
            else
            {
                return ResultFactory.getFailResult(USER_INVALID);
            }
        }
        catch (Exception ex)
        {
            return ResultFactory.getFailResult(ex.getMessage());
        }
    }

    @Override
    public Result<UserRole> store(String userName, String roleName)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);

        if (!checkUserRoles(roles))
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        UserRole userRole;

        if (userName == null || roleName == null)
        {
            userRole = new UserRole();
        }
        else
        {
            userRole = userRoleDao.find(userName + "-" + roleName);

            if (userRole == null)
            {
                return ResultFactory.getFailResult("Unable to find UserRole instance with RoleName=" + roleName);
            }
        }

        userRole.setRoleName(roleName);

        if (userRole.getId() == null)
        {
            userRoleDao.add(userRole);
        }
        else
        {
            userRole = userRoleDao.update(userRole);
        }

        return ResultFactory.getSuccessResult(userRole);

    }

    @Override
    public Result<UserRole> remove(String userName, String roleName)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);

        if (!checkUserRoles(roles))
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (userName == null || roleName == null)
        {
            return ResultFactory.getFailResult("Unable to remove UserRole [null id]");
        }

        UserRole userRole = userRoleDao.find(userName + "-" + roleName);

        if (userRole == null)
        {
            return ResultFactory.getFailResult("Unable to load UserRole for removal with RoleName=" + roleName);
        }
        else
        {
            //if all related objects are empty for the given object then we can erase this object
            userRoleDao.getRelatedObjects(userRole);

            String relatedObjectNames = "";
            boolean canBeDeleted = true;

            if (canBeDeleted)
            {
                userRoleDao.remove(userRole);

                String msg = "UserRole with Id: " + userRole.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);
            }
            else
            {
                return ResultFactory.getFailResult("UserRole is used with to [" + relatedObjectNames + "] and could not be deleted");
            }
        }
    }
}
