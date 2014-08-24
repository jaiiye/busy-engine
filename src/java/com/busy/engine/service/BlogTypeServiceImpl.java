


































package com.busy.engine.service;

import com.busy.engine.dao.BlogTypeDao;
import com.busy.engine.dao.BlogTypeDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.BlogType;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class BlogTypeServiceImpl extends AbstractService implements BlogTypeService 
{
    protected BlogTypeDao blogTypeDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    

    public BlogTypeServiceImpl() 
    {
        super();
        
        blogTypeDao = new BlogTypeDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public BlogTypeServiceImpl(ServletContext context) 
    {
        super();
        
        blogTypeDao = (BlogTypeDao) context.getAttribute("blogTypeDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<BlogType> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(blogTypeDao.find(id));
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
    public Result<List<BlogType>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<BlogType> blogTypeList =  blogTypeDao.findAll(null, null);
                return ResultFactory.getSuccessResult(blogTypeList);
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
    public Result<BlogType> store(String userName, Integer id, String typeName)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        BlogType blogType;

        if (id == null) 
        {
            blogType = new BlogType();
        } 
        else 
        {
            blogType = blogTypeDao.find(id);

            if (blogType == null) 
            {
                return ResultFactory.getFailResult("Unable to find BlogType instance with Id=" + id);
            }
        }

        blogType.setTypeName(typeName);
        
        
        if (blogType.getId() == null) 
        {
            blogTypeDao.add(blogType);
        } 
        else 
        {
            blogType = blogTypeDao.update(blogType);
        }

        return ResultFactory.getSuccessResult(blogType);

    }
  
    @Override
    public Result<BlogType> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove BlogType [null id]");
        } 

        BlogType blogType = blogTypeDao.find(id);

        if (blogType == null) 
        {
            return ResultFactory.getFailResult("Unable to load BlogType for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            blogTypeDao.getRelatedObjects(blogType);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(blogType.getBlogList().size() != 0)
            {
                relatedObjectNames += "Blog ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                blogTypeDao.remove(blogType);
                
                String msg = "BlogType with Id: " + blogType.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("BlogType is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
