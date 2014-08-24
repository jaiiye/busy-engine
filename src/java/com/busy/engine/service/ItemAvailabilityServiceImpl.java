


































package com.busy.engine.service;

import com.busy.engine.dao.ItemAvailabilityDao;
import com.busy.engine.dao.ItemAvailabilityDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.ItemAvailability;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class ItemAvailabilityServiceImpl extends AbstractService implements ItemAvailabilityService 
{
    protected ItemAvailabilityDao itemAvailabilityDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    

    public ItemAvailabilityServiceImpl() 
    {
        super();
        
        itemAvailabilityDao = new ItemAvailabilityDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public ItemAvailabilityServiceImpl(ServletContext context) 
    {
        super();
        
        itemAvailabilityDao = (ItemAvailabilityDao) context.getAttribute("itemAvailabilityDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<ItemAvailability> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(itemAvailabilityDao.find(id));
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
    public Result<List<ItemAvailability>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<ItemAvailability> itemAvailabilityList =  itemAvailabilityDao.findAll(null, null);
                return ResultFactory.getSuccessResult(itemAvailabilityList);
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
    public Result<ItemAvailability> store(String userName, Integer id, String type)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        ItemAvailability itemAvailability;

        if (id == null) 
        {
            itemAvailability = new ItemAvailability();
        } 
        else 
        {
            itemAvailability = itemAvailabilityDao.find(id);

            if (itemAvailability == null) 
            {
                return ResultFactory.getFailResult("Unable to find ItemAvailability instance with Id=" + id);
            }
        }

        itemAvailability.setType(type);
        
        
        if (itemAvailability.getId() == null) 
        {
            itemAvailabilityDao.add(itemAvailability);
        } 
        else 
        {
            itemAvailability = itemAvailabilityDao.update(itemAvailability);
        }

        return ResultFactory.getSuccessResult(itemAvailability);

    }
  
    @Override
    public Result<ItemAvailability> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove ItemAvailability [null id]");
        } 

        ItemAvailability itemAvailability = itemAvailabilityDao.find(id);

        if (itemAvailability == null) 
        {
            return ResultFactory.getFailResult("Unable to load ItemAvailability for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            itemAvailabilityDao.getRelatedObjects(itemAvailability);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(itemAvailability.getOptionAvailabilityList().size() != 0)
            {
                relatedObjectNames += "OptionAvailability ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                itemAvailabilityDao.remove(itemAvailability);
                
                String msg = "ItemAvailability with Id: " + itemAvailability.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("ItemAvailability is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
