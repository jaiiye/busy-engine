


































package com.busy.engine.service;

import com.busy.engine.dao.ItemLocationDao;
import com.busy.engine.dao.ItemLocationDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.ItemLocation;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class ItemLocationServiceImpl extends AbstractService implements ItemLocationService 
{
    protected ItemLocationDao itemLocationDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    
    public ItemLocationServiceImpl() 
    {
        super();        
        itemLocationDao = new ItemLocationDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public ItemLocationServiceImpl(ServletContext context) 
    {
        super();        
        itemLocationDao = (ItemLocationDao) context.getAttribute("itemLocationDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<ItemLocation> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(itemLocationDao.find(id));
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
    public Result<List<ItemLocation>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<ItemLocation> itemLocationList =  itemLocationDao.findAll(null, null);
                return ResultFactory.getSuccessResult(itemLocationList);
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
    public Result<ItemLocation> store(String userName, Integer id, String latitude, String longitude, Integer itemId, Integer addressId, Integer contactId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        ItemLocation itemLocation;

        if (id == null) 
        {
            itemLocation = new ItemLocation();
        } 
        else 
        {
            itemLocation = itemLocationDao.find(id);

            if (itemLocation == null) 
            {
                return ResultFactory.getFailResult("Unable to find ItemLocation instance with Id=" + id);
            }
        }

        itemLocation.setLatitude(latitude);
        itemLocation.setLongitude(longitude);
        itemLocation.setItemId(itemId);
        itemLocation.setAddressId(addressId);
        itemLocation.setContactId(contactId);
        
        
        if (itemLocation.getId() == null) 
        {
            itemLocationDao.add(itemLocation);
        } 
        else 
        {
            itemLocation = itemLocationDao.update(itemLocation);
        }

        return ResultFactory.getSuccessResult(itemLocation);

    }
  
    @Override
    public Result<ItemLocation> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove ItemLocation [null id]");
        } 

        ItemLocation itemLocation = itemLocationDao.find(id);

        if (itemLocation == null) 
        {
            return ResultFactory.getFailResult("Unable to load ItemLocation for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            itemLocationDao.getRelatedObjects(itemLocation);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                itemLocationDao.remove(itemLocation);
                
                String msg = "ItemLocation with Id: " + itemLocation.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("ItemLocation is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
