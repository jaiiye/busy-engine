





































package com.busy.engine.service;

import com.busy.engine.dao.FileFolderDao;
import com.busy.engine.dao.FileFolderDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.FileFolder;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class FileFolderServiceImpl extends AbstractService implements FileFolderService 
{
    protected FileFolderDao fileFolderDao = new FileFolderDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public FileFolderServiceImpl() 
    {
        super();
    }

    @Override
    public Result<FileFolder> find(String userName, Integer id)
    {

        if (isValidUser(userName)) 
        {
            return ResultFactory.getSuccessResult(fileFolderDao.find(id));
        }
        else 
        {            
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }
    
    @Override
    public Result<List<FileFolder>> findAll(String userName) 
    {
        if (isValidUser(userName)) 
        {
            List<FileFolder> fileFolderList =  fileFolderDao.findAll(null, null);
            return ResultFactory.getSuccessResult(fileFolderList);
        } 
        else 
        {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    @Override
    public Result<FileFolder> store(String userName, Integer id, Integer siteFileId, Integer siteFolderId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        FileFolder fileFolder;

        if (id == null) 
        {
            fileFolder = new FileFolder();
        } 
        else 
        {
            fileFolder = fileFolderDao.find(id);

            if (fileFolder == null) 
            {
                return ResultFactory.getFailResult("Unable to find FileFolder instance with Id=" + id);
            }
        }

        fileFolder.setSiteFileId(siteFileId);
        fileFolder.setSiteFolderId(siteFolderId);
        
        
        if (fileFolder.getId() == null) 
        {
            fileFolderDao.add(fileFolder);
        } 
        else 
        {
            fileFolder = fileFolderDao.update(fileFolder);
        }

        return ResultFactory.getSuccessResult(fileFolder);

    }
  
    @Override
    public Result<FileFolder> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove FileFolder [null id]");
        } 

        FileFolder fileFolder = fileFolderDao.find(id);

        if (fileFolder == null) 
        {
            return ResultFactory.getFailResult("Unable to load FileFolder for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            fileFolderDao.getRelatedObjects(fileFolder);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                fileFolderDao.remove(fileFolder);
                
                String msg = "FileFolder with Id: " + fileFolder.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("FileFolder is used with to [" + relatedObjectNames + "] and could not be deleted");
            }
            
        }

    }

}

