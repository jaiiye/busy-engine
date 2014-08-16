





















































package com.busy.engine.service;

import com.busy.engine.dao.BlogPostDao;
import com.busy.engine.dao.BlogPostDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.BlogPost;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class BlogPostServiceImpl extends AbstractService implements BlogPostService 
{
    protected BlogPostDao blogPostDao = new BlogPostDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public BlogPostServiceImpl() 
    {
        super();
    }

    @Override
    public Result<BlogPost> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName)) 
            {
                return ResultFactory.getSuccessResult(blogPostDao.find(id));
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
    public Result<List<BlogPost>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName)) 
            {
                List<BlogPost> blogPostList =  blogPostDao.findAll(null, null);
                return ResultFactory.getSuccessResult(blogPostList);
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
    public Result<BlogPost> store(String userName, Integer id, String title, String content, String imageURL, String tags, Integer featured, Integer ratingSum, Integer voteCount, Integer commentCount, Integer postStatus, String excerpt, Date lastModified, String locale, Integer userId, Integer blogId, Integer metaTagId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        BlogPost blogPost;

        if (id == null) 
        {
            blogPost = new BlogPost();
        } 
        else 
        {
            blogPost = blogPostDao.find(id);

            if (blogPost == null) 
            {
                return ResultFactory.getFailResult("Unable to find BlogPost instance with Id=" + id);
            }
        }

        blogPost.setTitle(title);
        blogPost.setContent(content);
        blogPost.setImageURL(imageURL);
        blogPost.setTags(tags);
        blogPost.setFeatured(featured);
        blogPost.setRatingSum(ratingSum);
        blogPost.setVoteCount(voteCount);
        blogPost.setCommentCount(commentCount);
        blogPost.setPostStatus(postStatus);
        blogPost.setExcerpt(excerpt);
        blogPost.setLastModified(lastModified);
        blogPost.setLocale(locale);
        blogPost.setUserId(userId);
        blogPost.setBlogId(blogId);
        blogPost.setMetaTagId(metaTagId);
        
        
        if (blogPost.getId() == null) 
        {
            blogPostDao.add(blogPost);
        } 
        else 
        {
            blogPost = blogPostDao.update(blogPost);
        }

        return ResultFactory.getSuccessResult(blogPost);

    }
  
    @Override
    public Result<BlogPost> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove BlogPost [null id]");
        } 

        BlogPost blogPost = blogPostDao.find(id);

        if (blogPost == null) 
        {
            return ResultFactory.getFailResult("Unable to load BlogPost for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            blogPostDao.getRelatedObjects(blogPost);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(blogPost.getBlogPostCategoryList().size() != 0)
            {
                relatedObjectNames += "BlogPostCategory ";  
                canBeDeleted = false;
            }
                        
            if(blogPost.getCommentList().size() != 0)
            {
                relatedObjectNames += "Comment ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                blogPostDao.remove(blogPost);
                
                String msg = "BlogPost with Id: " + blogPost.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("BlogPost is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
