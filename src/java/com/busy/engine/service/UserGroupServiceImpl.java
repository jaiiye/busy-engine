





































package com.busy.engine.service;

import com.busy.engine.dao.UserGroupDao;
import com.busy.engine.dao.UserGroupDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.UserGroup;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class UserGroupServiceImpl extends AbstractService implements UserGroupService 
{
    protected UserGroupDao userGroupDao = new UserGroupDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public UserGroupServiceImpl() 
    {
        super();
    }

    @Override
    public Result<UserGroup> find(String userName, Integer id)
    {

        if (isValidUser(userName)) 
        {
            return ResultFactory.getSuccessResult(userGroupDao.find(id));
        }
        else 
        {            
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }
    
    @Override
    public Result<List<UserGroup>> findAll(String userName) 
    {
        if (isValidUser(userName)) 
        {
            List<UserGroup> userGroupList =  userGroupDao.findAll(null, null);
            return ResultFactory.getSuccessResult(userGroupList);
        } 
        else 
        {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    @Override
    public Result<UserGroup> store(String userName, Integer id, String groupName, Integer siteId, Integer discountId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        UserGroup userGroup;

        if (id == null) 
        {
            userGroup = new UserGroup();
        } 
        else 
        {
            userGroup = userGroupDao.find(id);

            if (userGroup == null) 
            {
                return ResultFactory.getFailResult("Unable to find UserGroup instance with Id=" + id);
            }
        }

        userGroup.setGroupName(groupName);
        userGroup.setSiteId(siteId);
        userGroup.setDiscountId(discountId);
        
        
        if (userGroup.getId() == null) 
        {
            userGroupDao.add(userGroup);
        } 
        else 
        {
            userGroup = userGroupDao.update(userGroup);
        }

        return ResultFactory.getSuccessResult(userGroup);

    }
  
    @Override
    public Result<UserGroup> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove UserGroup [null id]");
        } 

        UserGroup userGroup = userGroupDao.find(id);

        if (userGroup == null) 
        {
            return ResultFactory.getFailResult("Unable to load UserGroup for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            userGroupDao.getRelatedObjects(userGroup);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(userGroup.getUserList().size() != 0)
            {
                relatedObjectNames += "User ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                userGroupDao.remove(userGroup);
                
                String msg = "UserGroup with Id: " + userGroup.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("UserGroup is used with to [" + relatedObjectNames + "] and could not be deleted");
            }
            
        }

    }

}

