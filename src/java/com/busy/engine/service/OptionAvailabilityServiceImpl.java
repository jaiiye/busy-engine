





































package com.busy.engine.service;

import com.busy.engine.dao.OptionAvailabilityDao;
import com.busy.engine.dao.OptionAvailabilityDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.OptionAvailability;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class OptionAvailabilityServiceImpl extends AbstractService implements OptionAvailabilityService 
{
    protected OptionAvailabilityDao optionAvailabilityDao = new OptionAvailabilityDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public OptionAvailabilityServiceImpl() 
    {
        super();
    }

    @Override
    public Result<OptionAvailability> find(String userName, Integer id)
    {

        if (isValidUser(userName)) 
        {
            return ResultFactory.getSuccessResult(optionAvailabilityDao.find(id));
        }
        else 
        {            
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }
    
    @Override
    public Result<List<OptionAvailability>> findAll(String userName) 
    {
        if (isValidUser(userName)) 
        {
            List<OptionAvailability> optionAvailabilityList =  optionAvailabilityDao.findAll(null, null);
            return ResultFactory.getSuccessResult(optionAvailabilityList);
        } 
        else 
        {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    @Override
    public Result<OptionAvailability> store(String userName, Integer id, Integer itemId, Integer itemOptionId, Integer itemAvailabilityId, Integer availableQuantity, Double price, Date availableFrom, Date availableTo, Integer maximumQuantity)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        OptionAvailability optionAvailability;

        if (id == null) 
        {
            optionAvailability = new OptionAvailability();
        } 
        else 
        {
            optionAvailability = optionAvailabilityDao.find(id);

            if (optionAvailability == null) 
            {
                return ResultFactory.getFailResult("Unable to find OptionAvailability instance with Id=" + id);
            }
        }

        optionAvailability.setItemId(itemId);
        optionAvailability.setItemOptionId(itemOptionId);
        optionAvailability.setItemAvailabilityId(itemAvailabilityId);
        optionAvailability.setAvailableQuantity(availableQuantity);
        optionAvailability.setPrice(price);
        optionAvailability.setAvailableFrom(availableFrom);
        optionAvailability.setAvailableTo(availableTo);
        optionAvailability.setMaximumQuantity(maximumQuantity);
        
        
        if (optionAvailability.getId() == null) 
        {
            optionAvailabilityDao.add(optionAvailability);
        } 
        else 
        {
            optionAvailability = optionAvailabilityDao.update(optionAvailability);
        }

        return ResultFactory.getSuccessResult(optionAvailability);

    }
  
    @Override
    public Result<OptionAvailability> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove OptionAvailability [null id]");
        } 

        OptionAvailability optionAvailability = optionAvailabilityDao.find(id);

        if (optionAvailability == null) 
        {
            return ResultFactory.getFailResult("Unable to load OptionAvailability for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            optionAvailabilityDao.getRelatedObjects(optionAvailability);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                optionAvailabilityDao.remove(optionAvailability);
                
                String msg = "OptionAvailability with Id: " + optionAvailability.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("OptionAvailability is used with to [" + relatedObjectNames + "] and could not be deleted");
            }
            
        }

    }

}

