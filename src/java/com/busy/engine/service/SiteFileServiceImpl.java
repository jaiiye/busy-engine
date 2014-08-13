





































package com.busy.engine.service;

import com.busy.engine.dao.SiteFileDao;
import com.busy.engine.dao.SiteFileDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.SiteFile;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class SiteFileServiceImpl extends AbstractService implements SiteFileService 
{
    protected SiteFileDao siteFileDao = new SiteFileDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public SiteFileServiceImpl() 
    {
        super();
    }

    @Override
    public Result<SiteFile> find(String userName, Integer id)
    {

        if (isValidUser(userName)) 
        {
            return ResultFactory.getSuccessResult(siteFileDao.find(id));
        }
        else 
        {            
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }
    
    @Override
    public Result<List<SiteFile>> findAll(String userName) 
    {
        if (isValidUser(userName)) 
        {
            List<SiteFile> siteFileList =  siteFileDao.findAll(null, null);
            return ResultFactory.getSuccessResult(siteFileList);
        } 
        else 
        {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    @Override
    public Result<SiteFile> store(String userName, Integer id, String fileName, String description, String label)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        SiteFile siteFile;

        if (id == null) 
        {
            siteFile = new SiteFile();
        } 
        else 
        {
            siteFile = siteFileDao.find(id);

            if (siteFile == null) 
            {
                return ResultFactory.getFailResult("Unable to find SiteFile instance with Id=" + id);
            }
        }

        siteFile.setFileName(fileName);
        siteFile.setDescription(description);
        siteFile.setLabel(label);
        
        
        if (siteFile.getId() == null) 
        {
            siteFileDao.add(siteFile);
        } 
        else 
        {
            siteFile = siteFileDao.update(siteFile);
        }

        return ResultFactory.getSuccessResult(siteFile);

    }
  
    @Override
    public Result<SiteFile> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove SiteFile [null id]");
        } 

        SiteFile siteFile = siteFileDao.find(id);

        if (siteFile == null) 
        {
            return ResultFactory.getFailResult("Unable to load SiteFile for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            siteFileDao.getRelatedObjects(siteFile);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(siteFile.getFileFolderList().size() != 0)
            {
                relatedObjectNames += "FileFolder ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                siteFileDao.remove(siteFile);
                
                String msg = "SiteFile with Id: " + siteFile.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("SiteFile is used with to [" + relatedObjectNames + "] and could not be deleted");
            }
            
        }

    }

}

