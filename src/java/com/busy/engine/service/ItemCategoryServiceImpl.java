


































package com.busy.engine.service;

import com.busy.engine.dao.ItemCategoryDao;
import com.busy.engine.dao.ItemCategoryDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.ItemCategory;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class ItemCategoryServiceImpl extends AbstractService implements ItemCategoryService 
{
    protected ItemCategoryDao itemCategoryDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    
    public ItemCategoryServiceImpl() 
    {
        super();        
        itemCategoryDao = new ItemCategoryDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public ItemCategoryServiceImpl(ServletContext context) 
    {
        super();        
        itemCategoryDao = (ItemCategoryDao) context.getAttribute("itemCategoryDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<ItemCategory> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(itemCategoryDao.find(id));
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
    public Result<List<ItemCategory>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<ItemCategory> itemCategoryList =  itemCategoryDao.findAll(null, null);
                return ResultFactory.getSuccessResult(itemCategoryList);
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
    public Result<ItemCategory> store(String userName, Integer id, Integer categoryId, Integer itemId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        ItemCategory itemCategory;

        if (id == null) 
        {
            itemCategory = new ItemCategory();
        } 
        else 
        {
            itemCategory = itemCategoryDao.find(id);

            if (itemCategory == null) 
            {
                return ResultFactory.getFailResult("Unable to find ItemCategory instance with Id=" + id);
            }
        }

        itemCategory.setCategoryId(categoryId);
        itemCategory.setItemId(itemId);
        
        
        if (itemCategory.getId() == null) 
        {
            itemCategoryDao.add(itemCategory);
        } 
        else 
        {
            itemCategory = itemCategoryDao.update(itemCategory);
        }

        return ResultFactory.getSuccessResult(itemCategory);

    }
  
    @Override
    public Result<ItemCategory> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove ItemCategory [null id]");
        } 

        ItemCategory itemCategory = itemCategoryDao.find(id);

        if (itemCategory == null) 
        {
            return ResultFactory.getFailResult("Unable to load ItemCategory for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            itemCategoryDao.getRelatedObjects(itemCategory);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                itemCategoryDao.remove(itemCategory);
                
                String msg = "ItemCategory with Id: " + itemCategory.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("ItemCategory is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
