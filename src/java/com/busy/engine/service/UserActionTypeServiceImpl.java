





































package com.busy.engine.service;

import com.busy.engine.dao.UserActionTypeDao;
import com.busy.engine.dao.UserActionTypeDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.UserActionType;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class UserActionTypeServiceImpl extends AbstractService implements UserActionTypeService 
{
    protected UserActionTypeDao userActionTypeDao = new UserActionTypeDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public UserActionTypeServiceImpl() 
    {
        super();
    }

    @Override
    public Result<UserActionType> find(String userName, Integer id)
    {

        if (isValidUser(userName)) 
        {
            return ResultFactory.getSuccessResult(userActionTypeDao.find(id));
        }
        else 
        {            
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }
    
    @Override
    public Result<List<UserActionType>> findAll(String userName) 
    {
        if (isValidUser(userName)) 
        {
            List<UserActionType> userActionTypeList =  userActionTypeDao.findAll(null, null);
            return ResultFactory.getSuccessResult(userActionTypeList);
        } 
        else 
        {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    @Override
    public Result<UserActionType> store(String userName, Integer id, String typeName)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        UserActionType userActionType;

        if (id == null) 
        {
            userActionType = new UserActionType();
        } 
        else 
        {
            userActionType = userActionTypeDao.find(id);

            if (userActionType == null) 
            {
                return ResultFactory.getFailResult("Unable to find UserActionType instance with Id=" + id);
            }
        }

        userActionType.setTypeName(typeName);
        
        
        if (userActionType.getId() == null) 
        {
            userActionTypeDao.add(userActionType);
        } 
        else 
        {
            userActionType = userActionTypeDao.update(userActionType);
        }

        return ResultFactory.getSuccessResult(userActionType);

    }
  
    @Override
    public Result<UserActionType> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove UserActionType [null id]");
        } 

        UserActionType userActionType = userActionTypeDao.find(id);

        if (userActionType == null) 
        {
            return ResultFactory.getFailResult("Unable to load UserActionType for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            userActionTypeDao.getRelatedObjects(userActionType);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(userActionType.getUserActionList().size() != 0)
            {
                relatedObjectNames += "UserAction ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                userActionTypeDao.remove(userActionType);
                
                String msg = "UserActionType with Id: " + userActionType.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("UserActionType is used with to [" + relatedObjectNames + "] and could not be deleted");
            }
            
        }

    }

}

