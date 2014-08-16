





















































package com.busy.engine.service;

import com.busy.engine.dao.CommentDao;
import com.busy.engine.dao.CommentDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.Comment;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class CommentServiceImpl extends AbstractService implements CommentService 
{
    protected CommentDao commentDao = new CommentDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public CommentServiceImpl() 
    {
        super();
    }

    @Override
    public Result<Comment> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName)) 
            {
                return ResultFactory.getSuccessResult(commentDao.find(id));
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
    public Result<List<Comment>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName)) 
            {
                List<Comment> commentList =  commentDao.findAll(null, null);
                return ResultFactory.getSuccessResult(commentList);
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
    public Result<Comment> store(String userName, Integer id, String title, String content, Date date, Integer commentStatus, Integer userId, Integer blogPostId, Integer itemReviewId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        Comment comment;

        if (id == null) 
        {
            comment = new Comment();
        } 
        else 
        {
            comment = commentDao.find(id);

            if (comment == null) 
            {
                return ResultFactory.getFailResult("Unable to find Comment instance with Id=" + id);
            }
        }

        comment.setTitle(title);
        comment.setContent(content);
        comment.setDate(date);
        comment.setCommentStatus(commentStatus);
        comment.setUserId(userId);
        comment.setBlogPostId(blogPostId);
        comment.setItemReviewId(itemReviewId);
        
        
        if (comment.getId() == null) 
        {
            commentDao.add(comment);
        } 
        else 
        {
            comment = commentDao.update(comment);
        }

        return ResultFactory.getSuccessResult(comment);

    }
  
    @Override
    public Result<Comment> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove Comment [null id]");
        } 

        Comment comment = commentDao.find(id);

        if (comment == null) 
        {
            return ResultFactory.getFailResult("Unable to load Comment for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            commentDao.getRelatedObjects(comment);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                commentDao.remove(comment);
                
                String msg = "Comment with Id: " + comment.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("Comment is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
