


































package com.busy.engine.service;

import com.busy.engine.dao.MetaTagDao;
import com.busy.engine.dao.MetaTagDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.MetaTag;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class MetaTagServiceImpl extends AbstractService implements MetaTagService 
{
    protected MetaTagDao metaTagDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    

    public MetaTagServiceImpl() 
    {
        super();
        
        metaTagDao = new MetaTagDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public MetaTagServiceImpl(ServletContext context) 
    {
        super();
        
        metaTagDao = (MetaTagDao) context.getAttribute("metaTagDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<MetaTag> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(metaTagDao.find(id));
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
    public Result<List<MetaTag>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<MetaTag> metaTagList =  metaTagDao.findAll(null, null);
                return ResultFactory.getSuccessResult(metaTagList);
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
    public Result<MetaTag> store(String userName, Integer id, String title, String description, String keywords)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        MetaTag metaTag;

        if (id == null) 
        {
            metaTag = new MetaTag();
        } 
        else 
        {
            metaTag = metaTagDao.find(id);

            if (metaTag == null) 
            {
                return ResultFactory.getFailResult("Unable to find MetaTag instance with Id=" + id);
            }
        }

        metaTag.setTitle(title);
        metaTag.setDescription(description);
        metaTag.setKeywords(keywords);
        
        
        if (metaTag.getId() == null) 
        {
            metaTagDao.add(metaTag);
        } 
        else 
        {
            metaTag = metaTagDao.update(metaTag);
        }

        return ResultFactory.getSuccessResult(metaTag);

    }
  
    @Override
    public Result<MetaTag> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove MetaTag [null id]");
        } 

        MetaTag metaTag = metaTagDao.find(id);

        if (metaTag == null) 
        {
            return ResultFactory.getFailResult("Unable to load MetaTag for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            metaTagDao.getRelatedObjects(metaTag);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(metaTag.getBlogPostList().size() != 0)
            {
                relatedObjectNames += "BlogPost ";  
                canBeDeleted = false;
            }
                        
            if(metaTag.getItemList().size() != 0)
            {
                relatedObjectNames += "Item ";  
                canBeDeleted = false;
            }
                        
            if(metaTag.getPageList().size() != 0)
            {
                relatedObjectNames += "Page ";  
                canBeDeleted = false;
            }
                        
            if(metaTag.getVendorList().size() != 0)
            {
                relatedObjectNames += "Vendor ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                metaTagDao.remove(metaTag);
                
                String msg = "MetaTag with Id: " + metaTag.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("MetaTag is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
