





































package com.busy.engine.service;

import com.busy.engine.dao.PostCategoryDao;
import com.busy.engine.dao.PostCategoryDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.PostCategory;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class PostCategoryServiceImpl extends AbstractService implements PostCategoryService 
{
    protected PostCategoryDao postCategoryDao = new PostCategoryDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public PostCategoryServiceImpl() 
    {
        super();
    }

    @Override
    public Result<PostCategory> find(String userName, Integer id)
    {

        if (isValidUser(userName)) 
        {
            return ResultFactory.getSuccessResult(postCategoryDao.find(id));
        }
        else 
        {            
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }
    
    @Override
    public Result<List<PostCategory>> findAll(String userName) 
    {
        if (isValidUser(userName)) 
        {
            List<PostCategory> postCategoryList =  postCategoryDao.findAll(null, null);
            return ResultFactory.getSuccessResult(postCategoryList);
        } 
        else 
        {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    @Override
    public Result<PostCategory> store(String userName, Integer id, String categoryName)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        PostCategory postCategory;

        if (id == null) 
        {
            postCategory = new PostCategory();
        } 
        else 
        {
            postCategory = postCategoryDao.find(id);

            if (postCategory == null) 
            {
                return ResultFactory.getFailResult("Unable to find PostCategory instance with Id=" + id);
            }
        }

        postCategory.setCategoryName(categoryName);
        
        
        if (postCategory.getId() == null) 
        {
            postCategoryDao.add(postCategory);
        } 
        else 
        {
            postCategory = postCategoryDao.update(postCategory);
        }

        return ResultFactory.getSuccessResult(postCategory);

    }
  
    @Override
    public Result<PostCategory> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove PostCategory [null id]");
        } 

        PostCategory postCategory = postCategoryDao.find(id);

        if (postCategory == null) 
        {
            return ResultFactory.getFailResult("Unable to load PostCategory for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            postCategoryDao.getRelatedObjects(postCategory);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(postCategory.getBlogPostCategoryList().size() != 0)
            {
                relatedObjectNames += "BlogPostCategory ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                postCategoryDao.remove(postCategory);
                
                String msg = "PostCategory with Id: " + postCategory.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("PostCategory is used with to [" + relatedObjectNames + "] and could not be deleted");
            }
            
        }

    }

}

