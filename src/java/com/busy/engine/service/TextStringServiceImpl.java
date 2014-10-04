


































package com.busy.engine.service;

import com.busy.engine.dao.TextStringDao;
import com.busy.engine.dao.TextStringDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.TextString;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class TextStringServiceImpl extends AbstractService implements TextStringService 
{
    protected TextStringDao textStringDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    
    public TextStringServiceImpl() 
    {
        super();        
        textStringDao = new TextStringDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public TextStringServiceImpl(ServletContext context) 
    {
        super();        
        textStringDao = (TextStringDao) context.getAttribute("textStringDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<TextString> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(textStringDao.find(id));
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
    public Result<List<TextString>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<TextString> textStringList =  textStringDao.findAll(null, null);
                return ResultFactory.getSuccessResult(textStringList);
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
    public Result<TextString> store(String userName, Integer id, String key)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        TextString textString;

        if (id == null) 
        {
            textString = new TextString();
        } 
        else 
        {
            textString = textStringDao.find(id);

            if (textString == null) 
            {
                return ResultFactory.getFailResult("Unable to find TextString instance with Id=" + id);
            }
        }

        textString.setKey(key);
        
        
        if (textString.getId() == null) 
        {
            textStringDao.add(textString);
        } 
        else 
        {
            textString = textStringDao.update(textString);
        }

        return ResultFactory.getSuccessResult(textString);

    }
  
    @Override
    public Result<TextString> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove TextString [null id]");
        } 

        TextString textString = textStringDao.find(id);

        if (textString == null) 
        {
            return ResultFactory.getFailResult("Unable to load TextString for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            textStringDao.getRelatedObjects(textString);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(textString.getLocalizedStringList().size() != 0)
            {
                relatedObjectNames += "LocalizedString ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                textStringDao.remove(textString);
                
                String msg = "TextString with Id: " + textString.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("TextString is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
