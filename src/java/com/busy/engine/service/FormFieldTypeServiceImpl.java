


































package com.busy.engine.service;

import com.busy.engine.dao.FormFieldTypeDao;
import com.busy.engine.dao.FormFieldTypeDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.FormFieldType;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class FormFieldTypeServiceImpl extends AbstractService implements FormFieldTypeService 
{
    protected FormFieldTypeDao formFieldTypeDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    

    public FormFieldTypeServiceImpl() 
    {
        super();
        
        formFieldTypeDao = new FormFieldTypeDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public FormFieldTypeServiceImpl(ServletContext context) 
    {
        super();
        
        formFieldTypeDao = (FormFieldTypeDao) context.getAttribute("formFieldTypeDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<FormFieldType> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(formFieldTypeDao.find(id));
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
    public Result<List<FormFieldType>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<FormFieldType> formFieldTypeList =  formFieldTypeDao.findAll(null, null);
                return ResultFactory.getSuccessResult(formFieldTypeList);
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
    public Result<FormFieldType> store(String userName, Integer id, String typeName, String inputType)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        FormFieldType formFieldType;

        if (id == null) 
        {
            formFieldType = new FormFieldType();
        } 
        else 
        {
            formFieldType = formFieldTypeDao.find(id);

            if (formFieldType == null) 
            {
                return ResultFactory.getFailResult("Unable to find FormFieldType instance with Id=" + id);
            }
        }

        formFieldType.setTypeName(typeName);
        formFieldType.setInputType(inputType);
        
        
        if (formFieldType.getId() == null) 
        {
            formFieldTypeDao.add(formFieldType);
        } 
        else 
        {
            formFieldType = formFieldTypeDao.update(formFieldType);
        }

        return ResultFactory.getSuccessResult(formFieldType);

    }
  
    @Override
    public Result<FormFieldType> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove FormFieldType [null id]");
        } 

        FormFieldType formFieldType = formFieldTypeDao.find(id);

        if (formFieldType == null) 
        {
            return ResultFactory.getFailResult("Unable to load FormFieldType for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            formFieldTypeDao.getRelatedObjects(formFieldType);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(formFieldType.getFormFieldList().size() != 0)
            {
                relatedObjectNames += "FormField ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                formFieldTypeDao.remove(formFieldType);
                
                String msg = "FormFieldType with Id: " + formFieldType.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("FormFieldType is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
