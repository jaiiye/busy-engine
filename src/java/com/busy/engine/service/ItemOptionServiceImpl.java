





































package com.busy.engine.service;

import com.busy.engine.dao.ItemOptionDao;
import com.busy.engine.dao.ItemOptionDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.ItemOption;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class ItemOptionServiceImpl extends AbstractService implements ItemOptionService 
{
    protected ItemOptionDao itemOptionDao = new ItemOptionDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public ItemOptionServiceImpl() 
    {
        super();
    }

    @Override
    public Result<ItemOption> find(String userName, Integer id)
    {

        if (isValidUser(userName)) 
        {
            return ResultFactory.getSuccessResult(itemOptionDao.find(id));
        }
        else 
        {            
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }
    
    @Override
    public Result<List<ItemOption>> findAll(String userName) 
    {
        if (isValidUser(userName)) 
        {
            List<ItemOption> itemOptionList =  itemOptionDao.findAll(null, null);
            return ResultFactory.getSuccessResult(itemOptionList);
        } 
        else 
        {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    @Override
    public Result<ItemOption> store(String userName, Integer id, String optionName, String description)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        ItemOption itemOption;

        if (id == null) 
        {
            itemOption = new ItemOption();
        } 
        else 
        {
            itemOption = itemOptionDao.find(id);

            if (itemOption == null) 
            {
                return ResultFactory.getFailResult("Unable to find ItemOption instance with Id=" + id);
            }
        }

        itemOption.setOptionName(optionName);
        itemOption.setDescription(description);
        
        
        if (itemOption.getId() == null) 
        {
            itemOptionDao.add(itemOption);
        } 
        else 
        {
            itemOption = itemOptionDao.update(itemOption);
        }

        return ResultFactory.getSuccessResult(itemOption);

    }
  
    @Override
    public Result<ItemOption> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove ItemOption [null id]");
        } 

        ItemOption itemOption = itemOptionDao.find(id);

        if (itemOption == null) 
        {
            return ResultFactory.getFailResult("Unable to load ItemOption for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            itemOptionDao.getRelatedObjects(itemOption);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(itemOption.getOptionAvailabilityList().size() != 0)
            {
                relatedObjectNames += "OptionAvailability ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                itemOptionDao.remove(itemOption);
                
                String msg = "ItemOption with Id: " + itemOption.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("ItemOption is used with to [" + relatedObjectNames + "] and could not be deleted");
            }
            
        }

    }

}

