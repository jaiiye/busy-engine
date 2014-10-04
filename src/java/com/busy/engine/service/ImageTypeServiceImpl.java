


































package com.busy.engine.service;

import com.busy.engine.dao.ImageTypeDao;
import com.busy.engine.dao.ImageTypeDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.ImageType;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class ImageTypeServiceImpl extends AbstractService implements ImageTypeService 
{
    protected ImageTypeDao imageTypeDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    
    public ImageTypeServiceImpl() 
    {
        super();        
        imageTypeDao = new ImageTypeDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public ImageTypeServiceImpl(ServletContext context) 
    {
        super();        
        imageTypeDao = (ImageTypeDao) context.getAttribute("imageTypeDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<ImageType> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(imageTypeDao.find(id));
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
    public Result<List<ImageType>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<ImageType> imageTypeList =  imageTypeDao.findAll(null, null);
                return ResultFactory.getSuccessResult(imageTypeList);
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
    public Result<ImageType> store(String userName, Integer id, String typeName, String description)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        ImageType imageType;

        if (id == null) 
        {
            imageType = new ImageType();
        } 
        else 
        {
            imageType = imageTypeDao.find(id);

            if (imageType == null) 
            {
                return ResultFactory.getFailResult("Unable to find ImageType instance with Id=" + id);
            }
        }

        imageType.setTypeName(typeName);
        imageType.setDescription(description);
        
        
        if (imageType.getId() == null) 
        {
            imageTypeDao.add(imageType);
        } 
        else 
        {
            imageType = imageTypeDao.update(imageType);
        }

        return ResultFactory.getSuccessResult(imageType);

    }
  
    @Override
    public Result<ImageType> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove ImageType [null id]");
        } 

        ImageType imageType = imageTypeDao.find(id);

        if (imageType == null) 
        {
            return ResultFactory.getFailResult("Unable to load ImageType for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            imageTypeDao.getRelatedObjects(imageType);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(imageType.getSiteImageList().size() != 0)
            {
                relatedObjectNames += "SiteImage ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                imageTypeDao.remove(imageType);
                
                String msg = "ImageType with Id: " + imageType.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("ImageType is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
