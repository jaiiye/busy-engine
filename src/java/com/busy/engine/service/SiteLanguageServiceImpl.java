


































package com.busy.engine.service;

import com.busy.engine.dao.SiteLanguageDao;
import com.busy.engine.dao.SiteLanguageDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.SiteLanguage;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class SiteLanguageServiceImpl extends AbstractService implements SiteLanguageService 
{
    protected SiteLanguageDao siteLanguageDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    
    public SiteLanguageServiceImpl() 
    {
        super();        
        siteLanguageDao = new SiteLanguageDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public SiteLanguageServiceImpl(ServletContext context) 
    {
        super();        
        siteLanguageDao = (SiteLanguageDao) context.getAttribute("siteLanguageDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<SiteLanguage> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(siteLanguageDao.find(id));
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
    public Result<List<SiteLanguage>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<SiteLanguage> siteLanguageList =  siteLanguageDao.findAll(null, null);
                return ResultFactory.getSuccessResult(siteLanguageList);
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
    public Result<SiteLanguage> store(String userName, Integer id, String languageName, String locale, Integer rtl, String flagFileName, Integer siteId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        SiteLanguage siteLanguage;

        if (id == null) 
        {
            siteLanguage = new SiteLanguage();
        } 
        else 
        {
            siteLanguage = siteLanguageDao.find(id);

            if (siteLanguage == null) 
            {
                return ResultFactory.getFailResult("Unable to find SiteLanguage instance with Id=" + id);
            }
        }

        siteLanguage.setLanguageName(languageName);
        siteLanguage.setLocale(locale);
        siteLanguage.setRtl(rtl);
        siteLanguage.setFlagFileName(flagFileName);
        siteLanguage.setSiteId(siteId);
        
        
        if (siteLanguage.getId() == null) 
        {
            siteLanguageDao.add(siteLanguage);
        } 
        else 
        {
            siteLanguage = siteLanguageDao.update(siteLanguage);
        }

        return ResultFactory.getSuccessResult(siteLanguage);

    }
  
    @Override
    public Result<SiteLanguage> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove SiteLanguage [null id]");
        } 

        SiteLanguage siteLanguage = siteLanguageDao.find(id);

        if (siteLanguage == null) 
        {
            return ResultFactory.getFailResult("Unable to load SiteLanguage for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            siteLanguageDao.getRelatedObjects(siteLanguage);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                siteLanguageDao.remove(siteLanguage);
                
                String msg = "SiteLanguage with Id: " + siteLanguage.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("SiteLanguage is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
