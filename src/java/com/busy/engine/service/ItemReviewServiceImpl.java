


































package com.busy.engine.service;

import com.busy.engine.dao.ItemReviewDao;
import com.busy.engine.dao.ItemReviewDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.ItemReview;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class ItemReviewServiceImpl extends AbstractService implements ItemReviewService 
{
    protected ItemReviewDao itemReviewDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    
    public ItemReviewServiceImpl() 
    {
        super();        
        itemReviewDao = new ItemReviewDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public ItemReviewServiceImpl(ServletContext context) 
    {
        super();        
        itemReviewDao = (ItemReviewDao) context.getAttribute("itemReviewDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<ItemReview> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(itemReviewDao.find(id));
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
    public Result<List<ItemReview>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<ItemReview> itemReviewList =  itemReviewDao.findAll(null, null);
                return ResultFactory.getSuccessResult(itemReviewList);
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
    public Result<ItemReview> store(String userName, Integer id, Integer itemId, Integer rating, Integer helpfulYes, Integer helpfulNo)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        ItemReview itemReview;

        if (id == null) 
        {
            itemReview = new ItemReview();
        } 
        else 
        {
            itemReview = itemReviewDao.find(id);

            if (itemReview == null) 
            {
                return ResultFactory.getFailResult("Unable to find ItemReview instance with Id=" + id);
            }
        }

        itemReview.setItemId(itemId);
        itemReview.setRating(rating);
        itemReview.setHelpfulYes(helpfulYes);
        itemReview.setHelpfulNo(helpfulNo);
        
        
        if (itemReview.getId() == null) 
        {
            itemReviewDao.add(itemReview);
        } 
        else 
        {
            itemReview = itemReviewDao.update(itemReview);
        }

        return ResultFactory.getSuccessResult(itemReview);

    }
  
    @Override
    public Result<ItemReview> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove ItemReview [null id]");
        } 

        ItemReview itemReview = itemReviewDao.find(id);

        if (itemReview == null) 
        {
            return ResultFactory.getFailResult("Unable to load ItemReview for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            itemReviewDao.getRelatedObjects(itemReview);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(itemReview.getCommentList().size() != 0)
            {
                relatedObjectNames += "Comment ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                itemReviewDao.remove(itemReview);
                
                String msg = "ItemReview with Id: " + itemReview.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("ItemReview is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
