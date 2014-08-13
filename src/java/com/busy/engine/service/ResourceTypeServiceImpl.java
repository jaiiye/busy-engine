





































package com.busy.engine.service;

import com.busy.engine.dao.ResourceTypeDao;
import com.busy.engine.dao.ResourceTypeDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.ResourceType;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class ResourceTypeServiceImpl extends AbstractService implements ResourceTypeService 
{
    protected ResourceTypeDao resourceTypeDao = new ResourceTypeDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public ResourceTypeServiceImpl() 
    {
        super();
    }

    @Override
    public Result<ResourceType> find(String userName, Integer id)
    {

        if (isValidUser(userName)) 
        {
            return ResultFactory.getSuccessResult(resourceTypeDao.find(id));
        }
        else 
        {            
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }
    
    @Override
    public Result<List<ResourceType>> findAll(String userName) 
    {
        if (isValidUser(userName)) 
        {
            List<ResourceType> resourceTypeList =  resourceTypeDao.findAll(null, null);
            return ResultFactory.getSuccessResult(resourceTypeList);
        } 
        else 
        {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    @Override
    public Result<ResourceType> store(String userName, Integer id, String typeName, String typeValue)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        ResourceType resourceType;

        if (id == null) 
        {
            resourceType = new ResourceType();
        } 
        else 
        {
            resourceType = resourceTypeDao.find(id);

            if (resourceType == null) 
            {
                return ResultFactory.getFailResult("Unable to find ResourceType instance with Id=" + id);
            }
        }

        resourceType.setTypeName(typeName);
        resourceType.setTypeValue(typeValue);
        
        
        if (resourceType.getId() == null) 
        {
            resourceTypeDao.add(resourceType);
        } 
        else 
        {
            resourceType = resourceTypeDao.update(resourceType);
        }

        return ResultFactory.getSuccessResult(resourceType);

    }
  
    @Override
    public Result<ResourceType> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove ResourceType [null id]");
        } 

        ResourceType resourceType = resourceTypeDao.find(id);

        if (resourceType == null) 
        {
            return ResultFactory.getFailResult("Unable to load ResourceType for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            resourceTypeDao.getRelatedObjects(resourceType);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(resourceType.getResourceUrlList().size() != 0)
            {
                relatedObjectNames += "ResourceUrl ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                resourceTypeDao.remove(resourceType);
                
                String msg = "ResourceType with Id: " + resourceType.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("ResourceType is used with to [" + relatedObjectNames + "] and could not be deleted");
            }
            
        }

    }

}

