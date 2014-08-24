


































package com.busy.engine.service;

import com.busy.engine.dao.ServiceTypeDao;
import com.busy.engine.dao.ServiceTypeDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.ServiceType;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class ServiceTypeServiceImpl extends AbstractService implements ServiceTypeService 
{
    protected ServiceTypeDao serviceTypeDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    

    public ServiceTypeServiceImpl() 
    {
        super();
        
        serviceTypeDao = new ServiceTypeDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public ServiceTypeServiceImpl(ServletContext context) 
    {
        super();
        
        serviceTypeDao = (ServiceTypeDao) context.getAttribute("serviceTypeDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<ServiceType> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(serviceTypeDao.find(id));
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
    public Result<List<ServiceType>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<ServiceType> serviceTypeList =  serviceTypeDao.findAll(null, null);
                return ResultFactory.getSuccessResult(serviceTypeList);
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
    public Result<ServiceType> store(String userName, Integer id, String typeName, String desciption)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        ServiceType serviceType;

        if (id == null) 
        {
            serviceType = new ServiceType();
        } 
        else 
        {
            serviceType = serviceTypeDao.find(id);

            if (serviceType == null) 
            {
                return ResultFactory.getFailResult("Unable to find ServiceType instance with Id=" + id);
            }
        }

        serviceType.setTypeName(typeName);
        serviceType.setDesciption(desciption);
        
        
        if (serviceType.getId() == null) 
        {
            serviceTypeDao.add(serviceType);
        } 
        else 
        {
            serviceType = serviceTypeDao.update(serviceType);
        }

        return ResultFactory.getSuccessResult(serviceType);

    }
  
    @Override
    public Result<ServiceType> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove ServiceType [null id]");
        } 

        ServiceType serviceType = serviceTypeDao.find(id);

        if (serviceType == null) 
        {
            return ResultFactory.getFailResult("Unable to load ServiceType for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            serviceTypeDao.getRelatedObjects(serviceType);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(serviceType.getServiceList().size() != 0)
            {
                relatedObjectNames += "Service ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                serviceTypeDao.remove(serviceType);
                
                String msg = "ServiceType with Id: " + serviceType.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("ServiceType is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
