


































package com.busy.engine.service;

import com.busy.engine.dao.UserActionDao;
import com.busy.engine.dao.UserActionDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.UserAction;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class UserActionServiceImpl extends AbstractService implements UserActionService 
{
    protected UserActionDao userActionDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    
    public UserActionServiceImpl() 
    {
        super();        
        userActionDao = new UserActionDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public UserActionServiceImpl(ServletContext context) 
    {
        super();        
        userActionDao = (UserActionDao) context.getAttribute("userActionDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<UserAction> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(userActionDao.find(id));
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
    public Result<List<UserAction>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<UserAction> userActionList =  userActionDao.findAll(null, null);
                return ResultFactory.getSuccessResult(userActionList);
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
    public Result<UserAction> store(String userName, Integer id, Date date, String detail, Integer userActionTypeId, Integer userId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        UserAction userAction;

        if (id == null) 
        {
            userAction = new UserAction();
        } 
        else 
        {
            userAction = userActionDao.find(id);

            if (userAction == null) 
            {
                return ResultFactory.getFailResult("Unable to find UserAction instance with Id=" + id);
            }
        }

        userAction.setDate(date);
        userAction.setDetail(detail);
        userAction.setUserActionTypeId(userActionTypeId);
        userAction.setUserId(userId);
        
        
        if (userAction.getId() == null) 
        {
            userActionDao.add(userAction);
        } 
        else 
        {
            userAction = userActionDao.update(userAction);
        }

        return ResultFactory.getSuccessResult(userAction);

    }
  
    @Override
    public Result<UserAction> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove UserAction [null id]");
        } 

        UserAction userAction = userActionDao.find(id);

        if (userAction == null) 
        {
            return ResultFactory.getFailResult("Unable to load UserAction for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            userActionDao.getRelatedObjects(userAction);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                userActionDao.remove(userAction);
                
                String msg = "UserAction with Id: " + userAction.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("UserAction is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
