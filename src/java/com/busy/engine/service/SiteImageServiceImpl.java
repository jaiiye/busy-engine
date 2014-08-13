





































package com.busy.engine.service;

import com.busy.engine.dao.SiteImageDao;
import com.busy.engine.dao.SiteImageDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.SiteImage;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class SiteImageServiceImpl extends AbstractService implements SiteImageService 
{
    protected SiteImageDao siteImageDao = new SiteImageDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public SiteImageServiceImpl() 
    {
        super();
    }

    @Override
    public Result<SiteImage> find(String userName, Integer id)
    {

        if (isValidUser(userName)) 
        {
            return ResultFactory.getSuccessResult(siteImageDao.find(id));
        }
        else 
        {            
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }
    
    @Override
    public Result<List<SiteImage>> findAll(String userName) 
    {
        if (isValidUser(userName)) 
        {
            List<SiteImage> siteImageList =  siteImageDao.findAll(null, null);
            return ResultFactory.getSuccessResult(siteImageList);
        } 
        else 
        {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    @Override
    public Result<SiteImage> store(String userName, Integer id, String fileName, String description, String linkUrl, Integer rank, Integer imageTypeId, Integer siteId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        SiteImage siteImage;

        if (id == null) 
        {
            siteImage = new SiteImage();
        } 
        else 
        {
            siteImage = siteImageDao.find(id);

            if (siteImage == null) 
            {
                return ResultFactory.getFailResult("Unable to find SiteImage instance with Id=" + id);
            }
        }

        siteImage.setFileName(fileName);
        siteImage.setDescription(description);
        siteImage.setLinkUrl(linkUrl);
        siteImage.setRank(rank);
        siteImage.setImageTypeId(imageTypeId);
        siteImage.setSiteId(siteId);
        
        
        if (siteImage.getId() == null) 
        {
            siteImageDao.add(siteImage);
        } 
        else 
        {
            siteImage = siteImageDao.update(siteImage);
        }

        return ResultFactory.getSuccessResult(siteImage);

    }
  
    @Override
    public Result<SiteImage> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove SiteImage [null id]");
        } 

        SiteImage siteImage = siteImageDao.find(id);

        if (siteImage == null) 
        {
            return ResultFactory.getFailResult("Unable to load SiteImage for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            siteImageDao.getRelatedObjects(siteImage);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                siteImageDao.remove(siteImage);
                
                String msg = "SiteImage with Id: " + siteImage.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("SiteImage is used with to [" + relatedObjectNames + "] and could not be deleted");
            }
            
        }

    }

}

