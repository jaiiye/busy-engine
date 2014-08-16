





















































package com.busy.engine.service;

import com.busy.engine.dao.ItemTypeDao;
import com.busy.engine.dao.ItemTypeDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.ItemType;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class ItemTypeServiceImpl extends AbstractService implements ItemTypeService 
{
    protected ItemTypeDao itemTypeDao = new ItemTypeDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public ItemTypeServiceImpl() 
    {
        super();
    }

    @Override
    public Result<ItemType> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName)) 
            {
                return ResultFactory.getSuccessResult(itemTypeDao.find(id));
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
    public Result<List<ItemType>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName)) 
            {
                List<ItemType> itemTypeList =  itemTypeDao.findAll(null, null);
                return ResultFactory.getSuccessResult(itemTypeList);
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
    public Result<ItemType> store(String userName, Integer id, String typeName, Integer rank)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        ItemType itemType;

        if (id == null) 
        {
            itemType = new ItemType();
        } 
        else 
        {
            itemType = itemTypeDao.find(id);

            if (itemType == null) 
            {
                return ResultFactory.getFailResult("Unable to find ItemType instance with Id=" + id);
            }
        }

        itemType.setTypeName(typeName);
        itemType.setRank(rank);
        
        
        if (itemType.getId() == null) 
        {
            itemTypeDao.add(itemType);
        } 
        else 
        {
            itemType = itemTypeDao.update(itemType);
        }

        return ResultFactory.getSuccessResult(itemType);

    }
  
    @Override
    public Result<ItemType> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove ItemType [null id]");
        } 

        ItemType itemType = itemTypeDao.find(id);

        if (itemType == null) 
        {
            return ResultFactory.getFailResult("Unable to load ItemType for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            itemTypeDao.getRelatedObjects(itemType);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(itemType.getItemList().size() != 0)
            {
                relatedObjectNames += "Item ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                itemTypeDao.remove(itemType);
                
                String msg = "ItemType with Id: " + itemType.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("ItemType is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
