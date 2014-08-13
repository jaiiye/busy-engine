





































package com.busy.engine.service;

import com.busy.engine.dao.TemplateDao;
import com.busy.engine.dao.TemplateDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.Template;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class TemplateServiceImpl extends AbstractService implements TemplateService 
{
    protected TemplateDao templateDao = new TemplateDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public TemplateServiceImpl() 
    {
        super();
    }

    @Override
    public Result<Template> find(String userName, Integer id)
    {

        if (isValidUser(userName)) 
        {
            return ResultFactory.getSuccessResult(templateDao.find(id));
        }
        else 
        {            
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }
    
    @Override
    public Result<List<Template>> findAll(String userName) 
    {
        if (isValidUser(userName)) 
        {
            List<Template> templateList =  templateDao.findAll(null, null);
            return ResultFactory.getSuccessResult(templateList);
        } 
        else 
        {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    @Override
    public Result<Template> store(String userName, Integer id, String templateName, String markup, Integer templateStatus, Integer templateTypeId, Integer parentTemplateId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        Template template;

        if (id == null) 
        {
            template = new Template();
        } 
        else 
        {
            template = templateDao.find(id);

            if (template == null) 
            {
                return ResultFactory.getFailResult("Unable to find Template instance with Id=" + id);
            }
        }

        template.setTemplateName(templateName);
        template.setMarkup(markup);
        template.setTemplateStatus(templateStatus);
        template.setTemplateTypeId(templateTypeId);
        template.setParentTemplateId(parentTemplateId);
        
        
        if (template.getId() == null) 
        {
            templateDao.add(template);
        } 
        else 
        {
            template = templateDao.update(template);
        }

        return ResultFactory.getSuccessResult(template);

    }
  
    @Override
    public Result<Template> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove Template [null id]");
        } 

        Template template = templateDao.find(id);

        if (template == null) 
        {
            return ResultFactory.getFailResult("Unable to load Template for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            templateDao.getRelatedObjects(template);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(template.getItemList().size() != 0)
            {
                relatedObjectNames += "Item ";  
                canBeDeleted = false;
            }
                        
            if(template.getPageList().size() != 0)
            {
                relatedObjectNames += "Page ";  
                canBeDeleted = false;
            }
                        
            if(template.getResourceUrlList().size() != 0)
            {
                relatedObjectNames += "ResourceUrl ";  
                canBeDeleted = false;
            }
                        
            if(template.getSiteList().size() != 0)
            {
                relatedObjectNames += "Site ";  
                canBeDeleted = false;
            }
                        
            if(template.getVendorList().size() != 0)
            {
                relatedObjectNames += "Vendor ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                templateDao.remove(template);
                
                String msg = "Template with Id: " + template.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("Template is used with to [" + relatedObjectNames + "] and could not be deleted");
            }
            
        }

    }

}

