





































package com.busy.engine.service;

import com.busy.engine.dao.RelatedItemDao;
import com.busy.engine.dao.RelatedItemDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.RelatedItem;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class RelatedItemServiceImpl extends AbstractService implements RelatedItemService 
{
    protected RelatedItemDao relatedItemDao = new RelatedItemDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public RelatedItemServiceImpl() 
    {
        super();
    }

    @Override
    public Result<RelatedItem> find(String userName, Integer id)
    {

        if (isValidUser(userName)) 
        {
            return ResultFactory.getSuccessResult(relatedItemDao.find(id));
        }
        else 
        {            
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }
    
    @Override
    public Result<List<RelatedItem>> findAll(String userName) 
    {
        if (isValidUser(userName)) 
        {
            List<RelatedItem> relatedItemList =  relatedItemDao.findAll(null, null);
            return ResultFactory.getSuccessResult(relatedItemList);
        } 
        else 
        {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    @Override
    public Result<RelatedItem> store(String userName, Integer id, Integer item1, Integer item2)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        RelatedItem relatedItem;

        if (id == null) 
        {
            relatedItem = new RelatedItem();
        } 
        else 
        {
            relatedItem = relatedItemDao.find(id);

            if (relatedItem == null) 
            {
                return ResultFactory.getFailResult("Unable to find RelatedItem instance with Id=" + id);
            }
        }

        relatedItem.setItem1(item1);
        relatedItem.setItem2(item2);
        
        
        if (relatedItem.getId() == null) 
        {
            relatedItemDao.add(relatedItem);
        } 
        else 
        {
            relatedItem = relatedItemDao.update(relatedItem);
        }

        return ResultFactory.getSuccessResult(relatedItem);

    }
  
    @Override
    public Result<RelatedItem> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove RelatedItem [null id]");
        } 

        RelatedItem relatedItem = relatedItemDao.find(id);

        if (relatedItem == null) 
        {
            return ResultFactory.getFailResult("Unable to load RelatedItem for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            relatedItemDao.getRelatedObjects(relatedItem);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                relatedItemDao.remove(relatedItem);
                
                String msg = "RelatedItem with Id: " + relatedItem.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("RelatedItem is used with to [" + relatedObjectNames + "] and could not be deleted");
            }
            
        }

    }

}

