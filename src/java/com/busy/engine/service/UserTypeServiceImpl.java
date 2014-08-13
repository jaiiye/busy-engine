





































package com.busy.engine.service;

import com.busy.engine.dao.UserTypeDao;
import com.busy.engine.dao.UserTypeDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.UserType;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class UserTypeServiceImpl extends AbstractService implements UserTypeService 
{
    protected UserTypeDao userTypeDao = new UserTypeDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public UserTypeServiceImpl() 
    {
        super();
    }

    @Override
    public Result<UserType> find(String userName, Integer id)
    {

        if (isValidUser(userName)) 
        {
            return ResultFactory.getSuccessResult(userTypeDao.find(id));
        }
        else 
        {            
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }
    
    @Override
    public Result<List<UserType>> findAll(String userName) 
    {
        if (isValidUser(userName)) 
        {
            List<UserType> userTypeList =  userTypeDao.findAll(null, null);
            return ResultFactory.getSuccessResult(userTypeList);
        } 
        else 
        {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    @Override
    public Result<UserType> store(String userName, Integer id, String typeName, String description, String redirectUrl)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        UserType userType;

        if (id == null) 
        {
            userType = new UserType();
        } 
        else 
        {
            userType = userTypeDao.find(id);

            if (userType == null) 
            {
                return ResultFactory.getFailResult("Unable to find UserType instance with Id=" + id);
            }
        }

        userType.setTypeName(typeName);
        userType.setDescription(description);
        userType.setRedirectUrl(redirectUrl);
        
        
        if (userType.getId() == null) 
        {
            userTypeDao.add(userType);
        } 
        else 
        {
            userType = userTypeDao.update(userType);
        }

        return ResultFactory.getSuccessResult(userType);

    }
  
    @Override
    public Result<UserType> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove UserType [null id]");
        } 

        UserType userType = userTypeDao.find(id);

        if (userType == null) 
        {
            return ResultFactory.getFailResult("Unable to load UserType for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            userTypeDao.getRelatedObjects(userType);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(userType.getUserList().size() != 0)
            {
                relatedObjectNames += "User ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                userTypeDao.remove(userType);
                
                String msg = "UserType with Id: " + userType.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("UserType is used with to [" + relatedObjectNames + "] and could not be deleted");
            }
            
        }

    }

}

