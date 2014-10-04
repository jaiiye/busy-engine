


































package com.busy.engine.service;

import com.busy.engine.dao.SiteEmailDao;
import com.busy.engine.dao.SiteEmailDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.SiteEmail;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class SiteEmailServiceImpl extends AbstractService implements SiteEmailService 
{
    protected SiteEmailDao siteEmailDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    
    public SiteEmailServiceImpl() 
    {
        super();        
        siteEmailDao = new SiteEmailDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public SiteEmailServiceImpl(ServletContext context) 
    {
        super();        
        siteEmailDao = (SiteEmailDao) context.getAttribute("siteEmailDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<SiteEmail> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(siteEmailDao.find(id));
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
    public Result<List<SiteEmail>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<SiteEmail> siteEmailList =  siteEmailDao.findAll(null, null);
                return ResultFactory.getSuccessResult(siteEmailList);
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
    public Result<SiteEmail> store(String userName, Integer id, String host, Integer port, String username, String password)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        SiteEmail siteEmail;

        if (id == null) 
        {
            siteEmail = new SiteEmail();
        } 
        else 
        {
            siteEmail = siteEmailDao.find(id);

            if (siteEmail == null) 
            {
                return ResultFactory.getFailResult("Unable to find SiteEmail instance with Id=" + id);
            }
        }

        siteEmail.setHost(host);
        siteEmail.setPort(port);
        siteEmail.setUsername(username);
        siteEmail.setPassword(password);
        
        
        if (siteEmail.getId() == null) 
        {
            siteEmailDao.add(siteEmail);
        } 
        else 
        {
            siteEmail = siteEmailDao.update(siteEmail);
        }

        return ResultFactory.getSuccessResult(siteEmail);

    }
  
    @Override
    public Result<SiteEmail> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove SiteEmail [null id]");
        } 

        SiteEmail siteEmail = siteEmailDao.find(id);

        if (siteEmail == null) 
        {
            return ResultFactory.getFailResult("Unable to load SiteEmail for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            siteEmailDao.getRelatedObjects(siteEmail);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(siteEmail.getSiteList().size() != 0)
            {
                relatedObjectNames += "Site ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                siteEmailDao.remove(siteEmail);
                
                String msg = "SiteEmail with Id: " + siteEmail.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("SiteEmail is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
