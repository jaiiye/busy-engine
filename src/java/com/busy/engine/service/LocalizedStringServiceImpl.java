package com.busy.engine.service;

import com.busy.engine.dao.LocalizedStringDao;
import com.busy.engine.dao.LocalizedStringDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.LocalizedString;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class LocalizedStringServiceImpl extends AbstractService implements LocalizedStringService
{

    protected LocalizedStringDao localizedStringDao;
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;

    public LocalizedStringServiceImpl()
    {
        super();
        localizedStringDao = new LocalizedStringDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }

    public LocalizedStringServiceImpl(ServletContext context)
    {
        super();
        localizedStringDao = (LocalizedStringDao) context.getAttribute("localizedStringDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<LocalizedString> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao))
            {
                return ResultFactory.getSuccessResult(localizedStringDao.find(id));
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
    public Result<List<LocalizedString>> findAll(String userName)
    {
        try
        {
            if (isValidUser(userName, userDao))
            {
                List<LocalizedString> localizedStringList = localizedStringDao.findAll(null, null);
                return ResultFactory.getSuccessResult(localizedStringList);
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
    public Result<LocalizedString> store(String userName, Integer id, String locale, String stringValue, Integer textStringId)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);

        if (!checkUserRoles(roles))
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        LocalizedString localizedString;

        if (id == null)
        {
            localizedString = new LocalizedString();
        }
        else
        {
            localizedString = localizedStringDao.find(id);

            if (localizedString == null)
            {
                return ResultFactory.getFailResult("Unable to find LocalizedString instance with Id=" + id);
            }
        }

        localizedString.setLocale(locale);
        localizedString.setStringValue(stringValue);
        localizedString.setTextStringId(textStringId);

        if (localizedString.getId() == null)
        {
            localizedStringDao.add(localizedString);
        }
        else
        {
            localizedString = localizedStringDao.update(localizedString);
        }

        return ResultFactory.getSuccessResult(localizedString);

    }

    @Override
    public Result<LocalizedString> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);

        if (!checkUserRoles(roles))
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null)
        {
            return ResultFactory.getFailResult("Unable to remove LocalizedString [null id]");
        }

        LocalizedString localizedString = localizedStringDao.find(id);

        if (localizedString == null)
        {
            return ResultFactory.getFailResult("Unable to load LocalizedString for removal with id=" + id);
        }
        else
        {
            //if all related objects are empty for the given object then we can erase this object
            localizedStringDao.getRelatedObjects(localizedString);

            String relatedObjectNames = "";
            boolean canBeDeleted = true;

            if (canBeDeleted)
            {
                localizedStringDao.remove(localizedString);

                String msg = "LocalizedString with Id: " + localizedString.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);
            }
            else
            {
                return ResultFactory.getFailResult("LocalizedString is used with to [" + relatedObjectNames + "] and could not be deleted");
            }
        }
    }
}
