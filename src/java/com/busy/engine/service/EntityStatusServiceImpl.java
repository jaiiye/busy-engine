


































package com.busy.engine.service;

import com.busy.engine.dao.EntityStatusDao;
import com.busy.engine.dao.EntityStatusDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.EntityStatus;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class EntityStatusServiceImpl extends AbstractService implements EntityStatusService 
{
    protected EntityStatusDao entityStatusDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    
    public EntityStatusServiceImpl() 
    {
        super();        
        entityStatusDao = new EntityStatusDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public EntityStatusServiceImpl(ServletContext context) 
    {
        super();        
        entityStatusDao = (EntityStatusDao) context.getAttribute("entityStatusDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<EntityStatus> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(entityStatusDao.find(id));
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
    public Result<List<EntityStatus>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<EntityStatus> entityStatusList =  entityStatusDao.findAll(null, null);
                return ResultFactory.getSuccessResult(entityStatusList);
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
    public Result<EntityStatus> store(String userName, Integer id, Integer statusCode, String statusName, String appliesTo)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        EntityStatus entityStatus;

        if (id == null) 
        {
            entityStatus = new EntityStatus();
        } 
        else 
        {
            entityStatus = entityStatusDao.find(id);

            if (entityStatus == null) 
            {
                return ResultFactory.getFailResult("Unable to find EntityStatus instance with Id=" + id);
            }
        }

        entityStatus.setStatusCode(statusCode);
        entityStatus.setStatusName(statusName);
        entityStatus.setAppliesTo(appliesTo);
        
        
        if (entityStatus.getId() == null) 
        {
            entityStatusDao.add(entityStatus);
        } 
        else 
        {
            entityStatus = entityStatusDao.update(entityStatus);
        }

        return ResultFactory.getSuccessResult(entityStatus);

    }
  
    @Override
    public Result<EntityStatus> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove EntityStatus [null id]");
        } 

        EntityStatus entityStatus = entityStatusDao.find(id);

        if (entityStatus == null) 
        {
            return ResultFactory.getFailResult("Unable to load EntityStatus for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            entityStatusDao.getRelatedObjects(entityStatus);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                entityStatusDao.remove(entityStatus);
                
                String msg = "EntityStatus with Id: " + entityStatus.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("EntityStatus is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
