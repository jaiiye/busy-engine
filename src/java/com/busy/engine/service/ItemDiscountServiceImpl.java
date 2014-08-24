


































package com.busy.engine.service;

import com.busy.engine.dao.ItemDiscountDao;
import com.busy.engine.dao.ItemDiscountDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.ItemDiscount;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class ItemDiscountServiceImpl extends AbstractService implements ItemDiscountService 
{
    protected ItemDiscountDao itemDiscountDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    

    public ItemDiscountServiceImpl() 
    {
        super();
        
        itemDiscountDao = new ItemDiscountDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public ItemDiscountServiceImpl(ServletContext context) 
    {
        super();
        
        itemDiscountDao = (ItemDiscountDao) context.getAttribute("itemDiscountDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<ItemDiscount> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(itemDiscountDao.find(id));
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
    public Result<List<ItemDiscount>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<ItemDiscount> itemDiscountList =  itemDiscountDao.findAll(null, null);
                return ResultFactory.getSuccessResult(itemDiscountList);
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
    public Result<ItemDiscount> store(String userName, Integer id, Integer itemId, Integer discountId, Integer applyToOptions)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        ItemDiscount itemDiscount;

        if (id == null) 
        {
            itemDiscount = new ItemDiscount();
        } 
        else 
        {
            itemDiscount = itemDiscountDao.find(id);

            if (itemDiscount == null) 
            {
                return ResultFactory.getFailResult("Unable to find ItemDiscount instance with Id=" + id);
            }
        }

        itemDiscount.setItemId(itemId);
        itemDiscount.setDiscountId(discountId);
        itemDiscount.setApplyToOptions(applyToOptions);
        
        
        if (itemDiscount.getId() == null) 
        {
            itemDiscountDao.add(itemDiscount);
        } 
        else 
        {
            itemDiscount = itemDiscountDao.update(itemDiscount);
        }

        return ResultFactory.getSuccessResult(itemDiscount);

    }
  
    @Override
    public Result<ItemDiscount> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove ItemDiscount [null id]");
        } 

        ItemDiscount itemDiscount = itemDiscountDao.find(id);

        if (itemDiscount == null) 
        {
            return ResultFactory.getFailResult("Unable to load ItemDiscount for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            itemDiscountDao.getRelatedObjects(itemDiscount);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                itemDiscountDao.remove(itemDiscount);
                
                String msg = "ItemDiscount with Id: " + itemDiscount.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("ItemDiscount is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
