





















































package com.busy.engine.service;

import com.busy.engine.dao.PageTemplateDao;
import com.busy.engine.dao.PageTemplateDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.PageTemplate;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class PageTemplateServiceImpl extends AbstractService implements PageTemplateService 
{
    protected PageTemplateDao pageTemplateDao = new PageTemplateDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public PageTemplateServiceImpl() 
    {
        super();
    }

    @Override
    public Result<PageTemplate> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName)) 
            {
                return ResultFactory.getSuccessResult(pageTemplateDao.find(id));
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
    public Result<List<PageTemplate>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName)) 
            {
                List<PageTemplate> pageTemplateList =  pageTemplateDao.findAll(null, null);
                return ResultFactory.getSuccessResult(pageTemplateList);
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
    public Result<PageTemplate> store(String userName, Integer id, String name, String markup)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        PageTemplate pageTemplate;

        if (id == null) 
        {
            pageTemplate = new PageTemplate();
        } 
        else 
        {
            pageTemplate = pageTemplateDao.find(id);

            if (pageTemplate == null) 
            {
                return ResultFactory.getFailResult("Unable to find PageTemplate instance with Id=" + id);
            }
        }

        pageTemplate.setName(name);
        pageTemplate.setMarkup(markup);
        
        
        if (pageTemplate.getId() == null) 
        {
            pageTemplateDao.add(pageTemplate);
        } 
        else 
        {
            pageTemplate = pageTemplateDao.update(pageTemplate);
        }

        return ResultFactory.getSuccessResult(pageTemplate);

    }
  
    @Override
    public Result<PageTemplate> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove PageTemplate [null id]");
        } 

        PageTemplate pageTemplate = pageTemplateDao.find(id);

        if (pageTemplate == null) 
        {
            return ResultFactory.getFailResult("Unable to load PageTemplate for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            pageTemplateDao.getRelatedObjects(pageTemplate);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                pageTemplateDao.remove(pageTemplate);
                
                String msg = "PageTemplate with Id: " + pageTemplate.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("PageTemplate is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
