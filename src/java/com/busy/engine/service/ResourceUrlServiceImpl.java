





















































package com.busy.engine.service;

import com.busy.engine.dao.ResourceUrlDao;
import com.busy.engine.dao.ResourceUrlDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.ResourceUrl;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class ResourceUrlServiceImpl extends AbstractService implements ResourceUrlService 
{
    protected ResourceUrlDao resourceUrlDao = new ResourceUrlDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public ResourceUrlServiceImpl() 
    {
        super();
    }

    @Override
    public Result<ResourceUrl> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName)) 
            {
                return ResultFactory.getSuccessResult(resourceUrlDao.find(id));
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
    public Result<List<ResourceUrl>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName)) 
            {
                List<ResourceUrl> resourceUrlList =  resourceUrlDao.findAll(null, null);
                return ResultFactory.getSuccessResult(resourceUrlList);
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
    public Result<ResourceUrl> store(String userName, Integer id, String url, Integer templateId, Integer resourceTypeId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        ResourceUrl resourceUrl;

        if (id == null) 
        {
            resourceUrl = new ResourceUrl();
        } 
        else 
        {
            resourceUrl = resourceUrlDao.find(id);

            if (resourceUrl == null) 
            {
                return ResultFactory.getFailResult("Unable to find ResourceUrl instance with Id=" + id);
            }
        }

        resourceUrl.setUrl(url);
        resourceUrl.setTemplateId(templateId);
        resourceUrl.setResourceTypeId(resourceTypeId);
        
        
        if (resourceUrl.getId() == null) 
        {
            resourceUrlDao.add(resourceUrl);
        } 
        else 
        {
            resourceUrl = resourceUrlDao.update(resourceUrl);
        }

        return ResultFactory.getSuccessResult(resourceUrl);

    }
  
    @Override
    public Result<ResourceUrl> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove ResourceUrl [null id]");
        } 

        ResourceUrl resourceUrl = resourceUrlDao.find(id);

        if (resourceUrl == null) 
        {
            return ResultFactory.getFailResult("Unable to load ResourceUrl for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            resourceUrlDao.getRelatedObjects(resourceUrl);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                resourceUrlDao.remove(resourceUrl);
                
                String msg = "ResourceUrl with Id: " + resourceUrl.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("ResourceUrl is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
