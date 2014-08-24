


































package com.busy.engine.service;

import com.busy.engine.dao.FormDao;
import com.busy.engine.dao.FormDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.Form;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class FormServiceImpl extends AbstractService implements FormService 
{
    protected FormDao formDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    

    public FormServiceImpl() 
    {
        super();
        
        formDao = new FormDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public FormServiceImpl(ServletContext context) 
    {
        super();
        
        formDao = (FormDao) context.getAttribute("formDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<Form> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(formDao.find(id));
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
    public Result<List<Form>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<Form> formList =  formDao.findAll(null, null);
                return ResultFactory.getSuccessResult(formList);
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
    public Result<Form> store(String userName, Integer id, String formName, String description, String submissionEmail, String submissionMethod, String action, Integer resettable, Integer fileUpload)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        Form form;

        if (id == null) 
        {
            form = new Form();
        } 
        else 
        {
            form = formDao.find(id);

            if (form == null) 
            {
                return ResultFactory.getFailResult("Unable to find Form instance with Id=" + id);
            }
        }

        form.setFormName(formName);
        form.setDescription(description);
        form.setSubmissionEmail(submissionEmail);
        form.setSubmissionMethod(submissionMethod);
        form.setAction(action);
        form.setResettable(resettable);
        form.setFileUpload(fileUpload);
        
        
        if (form.getId() == null) 
        {
            formDao.add(form);
        } 
        else 
        {
            form = formDao.update(form);
        }

        return ResultFactory.getSuccessResult(form);

    }
  
    @Override
    public Result<Form> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove Form [null id]");
        } 

        Form form = formDao.find(id);

        if (form == null) 
        {
            return ResultFactory.getFailResult("Unable to load Form for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            formDao.getRelatedObjects(form);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(form.getFormFieldList().size() != 0)
            {
                relatedObjectNames += "FormField ";  
                canBeDeleted = false;
            }
                        
            if(form.getPageList().size() != 0)
            {
                relatedObjectNames += "Page ";  
                canBeDeleted = false;
            }
                        
            if(form.getSliderList().size() != 0)
            {
                relatedObjectNames += "Slider ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                formDao.remove(form);
                
                String msg = "Form with Id: " + form.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("Form is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
