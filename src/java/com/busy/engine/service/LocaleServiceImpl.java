


































package com.busy.engine.service;

import com.busy.engine.dao.LocaleDao;
import com.busy.engine.dao.LocaleDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.Locale;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class LocaleServiceImpl extends AbstractService implements LocaleService 
{
    protected LocaleDao localeDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    

    public LocaleServiceImpl() 
    {
        super();
        
        localeDao = new LocaleDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public LocaleServiceImpl(ServletContext context) 
    {
        super();
        
        localeDao = (LocaleDao) context.getAttribute("localeDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<Locale> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(localeDao.find(id));
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
    public Result<List<Locale>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<Locale> localeList =  localeDao.findAll(null, null);
                return ResultFactory.getSuccessResult(localeList);
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
    public Result<Locale> store(String userName, Integer id, String localeString, String localeCharacterSet)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        Locale locale;

        if (id == null) 
        {
            locale = new Locale();
        } 
        else 
        {
            locale = localeDao.find(id);

            if (locale == null) 
            {
                return ResultFactory.getFailResult("Unable to find Locale instance with Id=" + id);
            }
        }

        locale.setLocaleString(localeString);
        locale.setLocaleCharacterSet(localeCharacterSet);
        
        
        if (locale.getId() == null) 
        {
            localeDao.add(locale);
        } 
        else 
        {
            locale = localeDao.update(locale);
        }

        return ResultFactory.getSuccessResult(locale);

    }
  
    @Override
    public Result<Locale> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove Locale [null id]");
        } 

        Locale locale = localeDao.find(id);

        if (locale == null) 
        {
            return ResultFactory.getFailResult("Unable to load Locale for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            localeDao.getRelatedObjects(locale);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                localeDao.remove(locale);
                
                String msg = "Locale with Id: " + locale.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("Locale is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
