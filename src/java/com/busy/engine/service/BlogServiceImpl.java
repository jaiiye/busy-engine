





















































package com.busy.engine.service;

import com.busy.engine.dao.BlogDao;
import com.busy.engine.dao.BlogDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.Blog;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class BlogServiceImpl extends AbstractService implements BlogService 
{
    protected BlogDao blogDao = new BlogDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public BlogServiceImpl() 
    {
        super();
    }

    @Override
    public Result<Blog> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName)) 
            {
                return ResultFactory.getSuccessResult(blogDao.find(id));
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
    public Result<List<Blog>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName)) 
            {
                List<Blog> blogList =  blogDao.findAll(null, null);
                return ResultFactory.getSuccessResult(blogList);
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
    public Result<Blog> store(String userName, Integer id, String topic, Integer blogTypeId, Integer knowledgeBaseId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        Blog blog;

        if (id == null) 
        {
            blog = new Blog();
        } 
        else 
        {
            blog = blogDao.find(id);

            if (blog == null) 
            {
                return ResultFactory.getFailResult("Unable to find Blog instance with Id=" + id);
            }
        }

        blog.setTopic(topic);
        blog.setBlogTypeId(blogTypeId);
        blog.setKnowledgeBaseId(knowledgeBaseId);
        
        
        if (blog.getId() == null) 
        {
            blogDao.add(blog);
        } 
        else 
        {
            blog = blogDao.update(blog);
        }

        return ResultFactory.getSuccessResult(blog);

    }
  
    @Override
    public Result<Blog> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove Blog [null id]");
        } 

        Blog blog = blogDao.find(id);

        if (blog == null) 
        {
            return ResultFactory.getFailResult("Unable to load Blog for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            blogDao.getRelatedObjects(blog);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(blog.getBlogPostList().size() != 0)
            {
                relatedObjectNames += "BlogPost ";  
                canBeDeleted = false;
            }
                        
            if(blog.getUserServiceList().size() != 0)
            {
                relatedObjectNames += "UserService ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                blogDao.remove(blog);
                
                String msg = "Blog with Id: " + blog.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("Blog is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
