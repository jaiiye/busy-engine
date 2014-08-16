





















































package com.busy.engine.service;

import com.busy.engine.dao.ServiceChargeDao;
import com.busy.engine.dao.ServiceChargeDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.ServiceCharge;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class ServiceChargeServiceImpl extends AbstractService implements ServiceChargeService 
{
    protected ServiceChargeDao serviceChargeDao = new ServiceChargeDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public ServiceChargeServiceImpl() 
    {
        super();
    }

    @Override
    public Result<ServiceCharge> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName)) 
            {
                return ResultFactory.getSuccessResult(serviceChargeDao.find(id));
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
    public Result<List<ServiceCharge>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName)) 
            {
                List<ServiceCharge> serviceChargeList =  serviceChargeDao.findAll(null, null);
                return ResultFactory.getSuccessResult(serviceChargeList);
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
    public Result<ServiceCharge> store(String userName, Integer id, String chargeName, String description, String rate, String units, Date date, Integer userServiceId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        ServiceCharge serviceCharge;

        if (id == null) 
        {
            serviceCharge = new ServiceCharge();
        } 
        else 
        {
            serviceCharge = serviceChargeDao.find(id);

            if (serviceCharge == null) 
            {
                return ResultFactory.getFailResult("Unable to find ServiceCharge instance with Id=" + id);
            }
        }

        serviceCharge.setChargeName(chargeName);
        serviceCharge.setDescription(description);
        serviceCharge.setRate(rate);
        serviceCharge.setUnits(units);
        serviceCharge.setDate(date);
        serviceCharge.setUserServiceId(userServiceId);
        
        
        if (serviceCharge.getId() == null) 
        {
            serviceChargeDao.add(serviceCharge);
        } 
        else 
        {
            serviceCharge = serviceChargeDao.update(serviceCharge);
        }

        return ResultFactory.getSuccessResult(serviceCharge);

    }
  
    @Override
    public Result<ServiceCharge> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove ServiceCharge [null id]");
        } 

        ServiceCharge serviceCharge = serviceChargeDao.find(id);

        if (serviceCharge == null) 
        {
            return ResultFactory.getFailResult("Unable to load ServiceCharge for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            serviceChargeDao.getRelatedObjects(serviceCharge);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(serviceCharge.getServiceList().size() != 0)
            {
                relatedObjectNames += "Service ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                serviceChargeDao.remove(serviceCharge);
                
                String msg = "ServiceCharge with Id: " + serviceCharge.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("ServiceCharge is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
