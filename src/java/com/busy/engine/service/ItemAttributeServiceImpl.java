





















































package com.busy.engine.service;

import com.busy.engine.dao.ItemAttributeDao;
import com.busy.engine.dao.ItemAttributeDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.ItemAttribute;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class ItemAttributeServiceImpl extends AbstractService implements ItemAttributeService 
{
    protected ItemAttributeDao itemAttributeDao = new ItemAttributeDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public ItemAttributeServiceImpl() 
    {
        super();
    }

    @Override
    public Result<ItemAttribute> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName)) 
            {
                return ResultFactory.getSuccessResult(itemAttributeDao.find(id));
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
    public Result<List<ItemAttribute>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName)) 
            {
                List<ItemAttribute> itemAttributeList =  itemAttributeDao.findAll(null, null);
                return ResultFactory.getSuccessResult(itemAttributeList);
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
    public Result<ItemAttribute> store(String userName, Integer id, String key, String value, String locale, Integer itemAttributeTypeId, Integer itemId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        ItemAttribute itemAttribute;

        if (id == null) 
        {
            itemAttribute = new ItemAttribute();
        } 
        else 
        {
            itemAttribute = itemAttributeDao.find(id);

            if (itemAttribute == null) 
            {
                return ResultFactory.getFailResult("Unable to find ItemAttribute instance with Id=" + id);
            }
        }

        itemAttribute.setKey(key);
        itemAttribute.setValue(value);
        itemAttribute.setLocale(locale);
        itemAttribute.setItemAttributeTypeId(itemAttributeTypeId);
        itemAttribute.setItemId(itemId);
        
        
        if (itemAttribute.getId() == null) 
        {
            itemAttributeDao.add(itemAttribute);
        } 
        else 
        {
            itemAttribute = itemAttributeDao.update(itemAttribute);
        }

        return ResultFactory.getSuccessResult(itemAttribute);

    }
  
    @Override
    public Result<ItemAttribute> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove ItemAttribute [null id]");
        } 

        ItemAttribute itemAttribute = itemAttributeDao.find(id);

        if (itemAttribute == null) 
        {
            return ResultFactory.getFailResult("Unable to load ItemAttribute for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            itemAttributeDao.getRelatedObjects(itemAttribute);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                itemAttributeDao.remove(itemAttribute);
                
                String msg = "ItemAttribute with Id: " + itemAttribute.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("ItemAttribute is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
