


































package com.busy.engine.service;

import com.busy.engine.dao.BlogPostCategoryDao;
import com.busy.engine.dao.BlogPostCategoryDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.BlogPostCategory;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class BlogPostCategoryServiceImpl extends AbstractService implements BlogPostCategoryService 
{
    protected BlogPostCategoryDao blogPostCategoryDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    
    public BlogPostCategoryServiceImpl() 
    {
        super();        
        blogPostCategoryDao = new BlogPostCategoryDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public BlogPostCategoryServiceImpl(ServletContext context) 
    {
        super();        
        blogPostCategoryDao = (BlogPostCategoryDao) context.getAttribute("blogPostCategoryDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<BlogPostCategory> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(blogPostCategoryDao.find(id));
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
    public Result<List<BlogPostCategory>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<BlogPostCategory> blogPostCategoryList =  blogPostCategoryDao.findAll(null, null);
                return ResultFactory.getSuccessResult(blogPostCategoryList);
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
    public Result<BlogPostCategory> store(String userName, Integer id, Integer blogPostId, Integer postCategoryId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        BlogPostCategory blogPostCategory;

        if (id == null) 
        {
            blogPostCategory = new BlogPostCategory();
        } 
        else 
        {
            blogPostCategory = blogPostCategoryDao.find(id);

            if (blogPostCategory == null) 
            {
                return ResultFactory.getFailResult("Unable to find BlogPostCategory instance with Id=" + id);
            }
        }

        blogPostCategory.setBlogPostId(blogPostId);
        blogPostCategory.setPostCategoryId(postCategoryId);
        
        
        if (blogPostCategory.getId() == null) 
        {
            blogPostCategoryDao.add(blogPostCategory);
        } 
        else 
        {
            blogPostCategory = blogPostCategoryDao.update(blogPostCategory);
        }

        return ResultFactory.getSuccessResult(blogPostCategory);

    }
  
    @Override
    public Result<BlogPostCategory> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove BlogPostCategory [null id]");
        } 

        BlogPostCategory blogPostCategory = blogPostCategoryDao.find(id);

        if (blogPostCategory == null) 
        {
            return ResultFactory.getFailResult("Unable to load BlogPostCategory for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            blogPostCategoryDao.getRelatedObjects(blogPostCategory);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                blogPostCategoryDao.remove(blogPostCategory);
                
                String msg = "BlogPostCategory with Id: " + blogPostCategory.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("BlogPostCategory is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
