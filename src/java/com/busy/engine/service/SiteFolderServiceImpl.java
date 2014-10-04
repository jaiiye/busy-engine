


































package com.busy.engine.service;

import com.busy.engine.dao.SiteFolderDao;
import com.busy.engine.dao.SiteFolderDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.SiteFolder;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class SiteFolderServiceImpl extends AbstractService implements SiteFolderService 
{
    protected SiteFolderDao siteFolderDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    
    public SiteFolderServiceImpl() 
    {
        super();        
        siteFolderDao = new SiteFolderDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public SiteFolderServiceImpl(ServletContext context) 
    {
        super();        
        siteFolderDao = (SiteFolderDao) context.getAttribute("siteFolderDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<SiteFolder> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(siteFolderDao.find(id));
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
    public Result<List<SiteFolder>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<SiteFolder> siteFolderList =  siteFolderDao.findAll(null, null);
                return ResultFactory.getSuccessResult(siteFolderList);
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
    public Result<SiteFolder> store(String userName, Integer id, String folderName, String description, Integer rank, Integer siteId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        SiteFolder siteFolder;

        if (id == null) 
        {
            siteFolder = new SiteFolder();
        } 
        else 
        {
            siteFolder = siteFolderDao.find(id);

            if (siteFolder == null) 
            {
                return ResultFactory.getFailResult("Unable to find SiteFolder instance with Id=" + id);
            }
        }

        siteFolder.setFolderName(folderName);
        siteFolder.setDescription(description);
        siteFolder.setRank(rank);
        siteFolder.setSiteId(siteId);
        
        
        if (siteFolder.getId() == null) 
        {
            siteFolderDao.add(siteFolder);
        } 
        else 
        {
            siteFolder = siteFolderDao.update(siteFolder);
        }

        return ResultFactory.getSuccessResult(siteFolder);

    }
  
    @Override
    public Result<SiteFolder> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove SiteFolder [null id]");
        } 

        SiteFolder siteFolder = siteFolderDao.find(id);

        if (siteFolder == null) 
        {
            return ResultFactory.getFailResult("Unable to load SiteFolder for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            siteFolderDao.getRelatedObjects(siteFolder);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(siteFolder.getFileFolderList().size() != 0)
            {
                relatedObjectNames += "FileFolder ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                siteFolderDao.remove(siteFolder);
                
                String msg = "SiteFolder with Id: " + siteFolder.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("SiteFolder is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
