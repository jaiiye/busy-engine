





































package com.busy.engine.service;

import com.busy.engine.dao.SiteDao;
import com.busy.engine.dao.SiteDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.Site;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class SiteServiceImpl extends AbstractService implements SiteService 
{
    protected SiteDao siteDao = new SiteDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public SiteServiceImpl() 
    {
        super();
    }

    @Override
    public Result<Site> find(String userName, Integer id)
    {

        if (isValidUser(userName)) 
        {
            return ResultFactory.getSuccessResult(siteDao.find(id));
        }
        else 
        {            
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }
    
    @Override
    public Result<List<Site>> findAll(String userName) 
    {
        if (isValidUser(userName)) 
        {
            List<Site> siteList =  siteDao.findAll(null, null);
            return ResultFactory.getSuccessResult(siteList);
        } 
        else 
        {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    @Override
    public Result<Site> store(String userName, Integer id, String siteName, String domain, Integer mode, String url, String logoTitle, String logoImage, Integer useAsStore, String emailHost, Integer emailPort, String emailUsername, String emailPassword, Integer siteStatus, String locale, Integer templateId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        Site site;

        if (id == null) 
        {
            site = new Site();
        } 
        else 
        {
            site = siteDao.find(id);

            if (site == null) 
            {
                return ResultFactory.getFailResult("Unable to find Site instance with Id=" + id);
            }
        }

        site.setSiteName(siteName);
        site.setDomain(domain);
        site.setMode(mode);
        site.setUrl(url);
        site.setLogoTitle(logoTitle);
        site.setLogoImage(logoImage);
        site.setUseAsStore(useAsStore);
        site.setEmailHost(emailHost);
        site.setEmailPort(emailPort);
        site.setEmailUsername(emailUsername);
        site.setEmailPassword(emailPassword);
        site.setSiteStatus(siteStatus);
        site.setLocale(locale);
        site.setTemplateId(templateId);
        
        
        if (site.getId() == null) 
        {
            siteDao.add(site);
        } 
        else 
        {
            site = siteDao.update(site);
        }

        return ResultFactory.getSuccessResult(site);

    }
  
    @Override
    public Result<Site> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove Site [null id]");
        } 

        Site site = siteDao.find(id);

        if (site == null) 
        {
            return ResultFactory.getFailResult("Unable to load Site for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            siteDao.getRelatedObjects(site);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(site.getSiteAttributeList().size() != 0)
            {
                relatedObjectNames += "SiteAttribute ";  
                canBeDeleted = false;
            }
                        
            if(site.getSiteFolderList().size() != 0)
            {
                relatedObjectNames += "SiteFolder ";  
                canBeDeleted = false;
            }
                        
            if(site.getSiteImageList().size() != 0)
            {
                relatedObjectNames += "SiteImage ";  
                canBeDeleted = false;
            }
                        
            if(site.getSiteItemList().size() != 0)
            {
                relatedObjectNames += "SiteItem ";  
                canBeDeleted = false;
            }
                        
            if(site.getSiteLanguageList().size() != 0)
            {
                relatedObjectNames += "SiteLanguage ";  
                canBeDeleted = false;
            }
                        
            if(site.getSitePageList().size() != 0)
            {
                relatedObjectNames += "SitePage ";  
                canBeDeleted = false;
            }
                        
            if(site.getUserGroupList().size() != 0)
            {
                relatedObjectNames += "UserGroup ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                siteDao.remove(site);
                
                String msg = "Site with Id: " + site.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("Site is used with to [" + relatedObjectNames + "] and could not be deleted");
            }
            
        }

    }

}

