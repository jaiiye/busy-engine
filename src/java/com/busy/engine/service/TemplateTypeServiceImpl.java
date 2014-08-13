





































package com.busy.engine.service;

import com.busy.engine.dao.TemplateTypeDao;
import com.busy.engine.dao.TemplateTypeDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.TemplateType;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class TemplateTypeServiceImpl extends AbstractService implements TemplateTypeService 
{
    protected TemplateTypeDao templateTypeDao = new TemplateTypeDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public TemplateTypeServiceImpl() 
    {
        super();
    }

    @Override
    public Result<TemplateType> find(String userName, Integer id)
    {

        if (isValidUser(userName)) 
        {
            return ResultFactory.getSuccessResult(templateTypeDao.find(id));
        }
        else 
        {            
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }
    
    @Override
    public Result<List<TemplateType>> findAll(String userName) 
    {
        if (isValidUser(userName)) 
        {
            List<TemplateType> templateTypeList =  templateTypeDao.findAll(null, null);
            return ResultFactory.getSuccessResult(templateTypeList);
        } 
        else 
        {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    @Override
    public Result<TemplateType> store(String userName, Integer id, String typeName, String typeValue)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        TemplateType templateType;

        if (id == null) 
        {
            templateType = new TemplateType();
        } 
        else 
        {
            templateType = templateTypeDao.find(id);

            if (templateType == null) 
            {
                return ResultFactory.getFailResult("Unable to find TemplateType instance with Id=" + id);
            }
        }

        templateType.setTypeName(typeName);
        templateType.setTypeValue(typeValue);
        
        
        if (templateType.getId() == null) 
        {
            templateTypeDao.add(templateType);
        } 
        else 
        {
            templateType = templateTypeDao.update(templateType);
        }

        return ResultFactory.getSuccessResult(templateType);

    }
  
    @Override
    public Result<TemplateType> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove TemplateType [null id]");
        } 

        TemplateType templateType = templateTypeDao.find(id);

        if (templateType == null) 
        {
            return ResultFactory.getFailResult("Unable to load TemplateType for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            templateTypeDao.getRelatedObjects(templateType);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(templateType.getTemplateList().size() != 0)
            {
                relatedObjectNames += "Template ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                templateTypeDao.remove(templateType);
                
                String msg = "TemplateType with Id: " + templateType.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("TemplateType is used with to [" + relatedObjectNames + "] and could not be deleted");
            }
            
        }

    }

}

