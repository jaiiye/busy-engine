


































package com.busy.engine.service;

import com.busy.engine.dao.ItemBrandDao;
import com.busy.engine.dao.ItemBrandDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.ItemBrand;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class ItemBrandServiceImpl extends AbstractService implements ItemBrandService 
{
    protected ItemBrandDao itemBrandDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    
    public ItemBrandServiceImpl() 
    {
        super();        
        itemBrandDao = new ItemBrandDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public ItemBrandServiceImpl(ServletContext context) 
    {
        super();        
        itemBrandDao = (ItemBrandDao) context.getAttribute("itemBrandDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<ItemBrand> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(itemBrandDao.find(id));
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
    public Result<List<ItemBrand>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<ItemBrand> itemBrandList =  itemBrandDao.findAll(null, null);
                return ResultFactory.getSuccessResult(itemBrandList);
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
    public Result<ItemBrand> store(String userName, Integer id, String brandName, String description)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        ItemBrand itemBrand;

        if (id == null) 
        {
            itemBrand = new ItemBrand();
        } 
        else 
        {
            itemBrand = itemBrandDao.find(id);

            if (itemBrand == null) 
            {
                return ResultFactory.getFailResult("Unable to find ItemBrand instance with Id=" + id);
            }
        }

        itemBrand.setBrandName(brandName);
        itemBrand.setDescription(description);
        
        
        if (itemBrand.getId() == null) 
        {
            itemBrandDao.add(itemBrand);
        } 
        else 
        {
            itemBrand = itemBrandDao.update(itemBrand);
        }

        return ResultFactory.getSuccessResult(itemBrand);

    }
  
    @Override
    public Result<ItemBrand> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove ItemBrand [null id]");
        } 

        ItemBrand itemBrand = itemBrandDao.find(id);

        if (itemBrand == null) 
        {
            return ResultFactory.getFailResult("Unable to load ItemBrand for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            itemBrandDao.getRelatedObjects(itemBrand);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(itemBrand.getItemList().size() != 0)
            {
                relatedObjectNames += "Item ";  
                canBeDeleted = false;
            }
                        
            if(itemBrand.getUserList().size() != 0)
            {
                relatedObjectNames += "User ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                itemBrandDao.remove(itemBrand);
                
                String msg = "ItemBrand with Id: " + itemBrand.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("ItemBrand is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
