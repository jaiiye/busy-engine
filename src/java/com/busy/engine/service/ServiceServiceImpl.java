





















































package com.busy.engine.service;

import com.busy.engine.dao.ServiceDao;
import com.busy.engine.dao.ServiceDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.Service;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class ServiceServiceImpl extends AbstractService implements ServiceService 
{
    protected ServiceDao serviceDao = new ServiceDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public ServiceServiceImpl() 
    {
        super();
    }

    @Override
    public Result<Service> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName)) 
            {
                return ResultFactory.getSuccessResult(serviceDao.find(id));
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
    public Result<List<Service>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName)) 
            {
                List<Service> serviceList =  serviceDao.findAll(null, null);
                return ResultFactory.getSuccessResult(serviceList);
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
    public Result<Service> store(String userName, Integer id, String serviceName, String description, Integer serviceStatus, Integer serviceChargeId, Integer serviceTypeId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        Service service;

        if (id == null) 
        {
            service = new Service();
        } 
        else 
        {
            service = serviceDao.find(id);

            if (service == null) 
            {
                return ResultFactory.getFailResult("Unable to find Service instance with Id=" + id);
            }
        }

        service.setServiceName(serviceName);
        service.setDescription(description);
        service.setServiceStatus(serviceStatus);
        service.setServiceChargeId(serviceChargeId);
        service.setServiceTypeId(serviceTypeId);
        
        
        if (service.getId() == null) 
        {
            serviceDao.add(service);
        } 
        else 
        {
            service = serviceDao.update(service);
        }

        return ResultFactory.getSuccessResult(service);

    }
  
    @Override
    public Result<Service> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove Service [null id]");
        } 

        Service service = serviceDao.find(id);

        if (service == null) 
        {
            return ResultFactory.getFailResult("Unable to load Service for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            serviceDao.getRelatedObjects(service);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(service.getUserServiceList().size() != 0)
            {
                relatedObjectNames += "UserService ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                serviceDao.remove(service);
                
                String msg = "Service with Id: " + service.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("Service is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
