


































package com.busy.engine.service;

import com.busy.engine.dao.SiteItemDao;
import com.busy.engine.dao.SiteItemDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.SiteItem;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class SiteItemServiceImpl extends AbstractService implements SiteItemService 
{
    protected SiteItemDao siteItemDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    
    public SiteItemServiceImpl() 
    {
        super();        
        siteItemDao = new SiteItemDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public SiteItemServiceImpl(ServletContext context) 
    {
        super();        
        siteItemDao = (SiteItemDao) context.getAttribute("siteItemDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<SiteItem> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(siteItemDao.find(id));
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
    public Result<List<SiteItem>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<SiteItem> siteItemList =  siteItemDao.findAll(null, null);
                return ResultFactory.getSuccessResult(siteItemList);
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
    public Result<SiteItem> store(String userName, Integer id, Integer siteId, Integer itemId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        SiteItem siteItem;

        if (id == null) 
        {
            siteItem = new SiteItem();
        } 
        else 
        {
            siteItem = siteItemDao.find(id);

            if (siteItem == null) 
            {
                return ResultFactory.getFailResult("Unable to find SiteItem instance with Id=" + id);
            }
        }

        siteItem.setSiteId(siteId);
        siteItem.setItemId(itemId);
        
        
        if (siteItem.getId() == null) 
        {
            siteItemDao.add(siteItem);
        } 
        else 
        {
            siteItem = siteItemDao.update(siteItem);
        }

        return ResultFactory.getSuccessResult(siteItem);

    }
  
    @Override
    public Result<SiteItem> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove SiteItem [null id]");
        } 

        SiteItem siteItem = siteItemDao.find(id);

        if (siteItem == null) 
        {
            return ResultFactory.getFailResult("Unable to load SiteItem for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            siteItemDao.getRelatedObjects(siteItem);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                siteItemDao.remove(siteItem);
                
                String msg = "SiteItem with Id: " + siteItem.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("SiteItem is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
