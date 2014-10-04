


































package com.busy.engine.service;

import com.busy.engine.dao.SitePageDao;
import com.busy.engine.dao.SitePageDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.SitePage;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class SitePageServiceImpl extends AbstractService implements SitePageService 
{
    protected SitePageDao sitePageDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    
    public SitePageServiceImpl() 
    {
        super();        
        sitePageDao = new SitePageDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public SitePageServiceImpl(ServletContext context) 
    {
        super();        
        sitePageDao = (SitePageDao) context.getAttribute("sitePageDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<SitePage> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(sitePageDao.find(id));
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
    public Result<List<SitePage>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<SitePage> sitePageList =  sitePageDao.findAll(null, null);
                return ResultFactory.getSuccessResult(sitePageList);
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
    public Result<SitePage> store(String userName, Integer id, Integer siteId, Integer pageId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        SitePage sitePage;

        if (id == null) 
        {
            sitePage = new SitePage();
        } 
        else 
        {
            sitePage = sitePageDao.find(id);

            if (sitePage == null) 
            {
                return ResultFactory.getFailResult("Unable to find SitePage instance with Id=" + id);
            }
        }

        sitePage.setSiteId(siteId);
        sitePage.setPageId(pageId);
        
        
        if (sitePage.getId() == null) 
        {
            sitePageDao.add(sitePage);
        } 
        else 
        {
            sitePage = sitePageDao.update(sitePage);
        }

        return ResultFactory.getSuccessResult(sitePage);

    }
  
    @Override
    public Result<SitePage> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove SitePage [null id]");
        } 

        SitePage sitePage = sitePageDao.find(id);

        if (sitePage == null) 
        {
            return ResultFactory.getFailResult("Unable to load SitePage for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            sitePageDao.getRelatedObjects(sitePage);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                sitePageDao.remove(sitePage);
                
                String msg = "SitePage with Id: " + sitePage.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("SitePage is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
