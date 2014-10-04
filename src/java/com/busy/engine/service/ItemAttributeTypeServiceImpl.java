


































package com.busy.engine.service;

import com.busy.engine.dao.ItemAttributeTypeDao;
import com.busy.engine.dao.ItemAttributeTypeDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.ItemAttributeType;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class ItemAttributeTypeServiceImpl extends AbstractService implements ItemAttributeTypeService 
{
    protected ItemAttributeTypeDao itemAttributeTypeDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    
    public ItemAttributeTypeServiceImpl() 
    {
        super();        
        itemAttributeTypeDao = new ItemAttributeTypeDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public ItemAttributeTypeServiceImpl(ServletContext context) 
    {
        super();        
        itemAttributeTypeDao = (ItemAttributeTypeDao) context.getAttribute("itemAttributeTypeDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<ItemAttributeType> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(itemAttributeTypeDao.find(id));
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
    public Result<List<ItemAttributeType>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<ItemAttributeType> itemAttributeTypeList =  itemAttributeTypeDao.findAll(null, null);
                return ResultFactory.getSuccessResult(itemAttributeTypeList);
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
    public Result<ItemAttributeType> store(String userName, Integer id, String attributeName, String description)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        ItemAttributeType itemAttributeType;

        if (id == null) 
        {
            itemAttributeType = new ItemAttributeType();
        } 
        else 
        {
            itemAttributeType = itemAttributeTypeDao.find(id);

            if (itemAttributeType == null) 
            {
                return ResultFactory.getFailResult("Unable to find ItemAttributeType instance with Id=" + id);
            }
        }

        itemAttributeType.setAttributeName(attributeName);
        itemAttributeType.setDescription(description);
        
        
        if (itemAttributeType.getId() == null) 
        {
            itemAttributeTypeDao.add(itemAttributeType);
        } 
        else 
        {
            itemAttributeType = itemAttributeTypeDao.update(itemAttributeType);
        }

        return ResultFactory.getSuccessResult(itemAttributeType);

    }
  
    @Override
    public Result<ItemAttributeType> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove ItemAttributeType [null id]");
        } 

        ItemAttributeType itemAttributeType = itemAttributeTypeDao.find(id);

        if (itemAttributeType == null) 
        {
            return ResultFactory.getFailResult("Unable to load ItemAttributeType for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            itemAttributeTypeDao.getRelatedObjects(itemAttributeType);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(itemAttributeType.getItemAttributeList().size() != 0)
            {
                relatedObjectNames += "ItemAttribute ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                itemAttributeTypeDao.remove(itemAttributeType);
                
                String msg = "ItemAttributeType with Id: " + itemAttributeType.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("ItemAttributeType is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
