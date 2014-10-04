


































package com.busy.engine.service;

import com.busy.engine.dao.FormFieldDao;
import com.busy.engine.dao.FormFieldDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.FormField;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class FormFieldServiceImpl extends AbstractService implements FormFieldService 
{
    protected FormFieldDao formFieldDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    
    public FormFieldServiceImpl() 
    {
        super();        
        formFieldDao = new FormFieldDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public FormFieldServiceImpl(ServletContext context) 
    {
        super();        
        formFieldDao = (FormFieldDao) context.getAttribute("formFieldDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<FormField> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(formFieldDao.find(id));
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
    public Result<List<FormField>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<FormField> formFieldList =  formFieldDao.findAll(null, null);
                return ResultFactory.getSuccessResult(formFieldList);
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
    public Result<FormField> store(String userName, Integer id, String fieldName, String label, String errorText, String validationRegex, Integer rank, String defaultValue, String options, String groupName, Integer optional, Integer formFieldTypeId, Integer formId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        FormField formField;

        if (id == null) 
        {
            formField = new FormField();
        } 
        else 
        {
            formField = formFieldDao.find(id);

            if (formField == null) 
            {
                return ResultFactory.getFailResult("Unable to find FormField instance with Id=" + id);
            }
        }

        formField.setFieldName(fieldName);
        formField.setLabel(label);
        formField.setErrorText(errorText);
        formField.setValidationRegex(validationRegex);
        formField.setRank(rank);
        formField.setDefaultValue(defaultValue);
        formField.setOptions(options);
        formField.setGroupName(groupName);
        formField.setOptional(optional);
        formField.setFormFieldTypeId(formFieldTypeId);
        formField.setFormId(formId);
        
        
        if (formField.getId() == null) 
        {
            formFieldDao.add(formField);
        } 
        else 
        {
            formField = formFieldDao.update(formField);
        }

        return ResultFactory.getSuccessResult(formField);

    }
  
    @Override
    public Result<FormField> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove FormField [null id]");
        } 

        FormField formField = formFieldDao.find(id);

        if (formField == null) 
        {
            return ResultFactory.getFailResult("Unable to load FormField for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            formFieldDao.getRelatedObjects(formField);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                formFieldDao.remove(formField);
                
                String msg = "FormField with Id: " + formField.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("FormField is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
