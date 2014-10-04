


































package com.busy.engine.service;

import com.busy.engine.dao.PageDao;
import com.busy.engine.dao.PageDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.Page;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class PageServiceImpl extends AbstractService implements PageService 
{
    protected PageDao pageDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    
    public PageServiceImpl() 
    {
        super();        
        pageDao = new PageDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public PageServiceImpl(ServletContext context) 
    {
        super();        
        pageDao = (PageDao) context.getAttribute("pageDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<Page> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(pageDao.find(id));
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
    public Result<List<Page>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<Page> pageList =  pageDao.findAll(null, null);
                return ResultFactory.getSuccessResult(pageList);
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
    public Result<Page> store(String userName, Integer id, String pageName, String content, Integer pageStatus, Integer formId, Integer sliderId, Integer metaTagId, Integer templateId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        Page page;

        if (id == null) 
        {
            page = new Page();
        } 
        else 
        {
            page = pageDao.find(id);

            if (page == null) 
            {
                return ResultFactory.getFailResult("Unable to find Page instance with Id=" + id);
            }
        }

        page.setPageName(pageName);
        page.setContent(content);
        page.setPageStatus(pageStatus);
        page.setFormId(formId);
        page.setSliderId(sliderId);
        page.setMetaTagId(metaTagId);
        page.setTemplateId(templateId);
        
        
        if (page.getId() == null) 
        {
            pageDao.add(page);
        } 
        else 
        {
            page = pageDao.update(page);
        }

        return ResultFactory.getSuccessResult(page);

    }
  
    @Override
    public Result<Page> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove Page [null id]");
        } 

        Page page = pageDao.find(id);

        if (page == null) 
        {
            return ResultFactory.getFailResult("Unable to load Page for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            pageDao.getRelatedObjects(page);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(page.getSitePageList().size() != 0)
            {
                relatedObjectNames += "SitePage ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                pageDao.remove(page);
                
                String msg = "Page with Id: " + page.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("Page is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
