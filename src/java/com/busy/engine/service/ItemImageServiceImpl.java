


































package com.busy.engine.service;

import com.busy.engine.dao.ItemImageDao;
import com.busy.engine.dao.ItemImageDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.ItemImage;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class ItemImageServiceImpl extends AbstractService implements ItemImageService 
{
    protected ItemImageDao itemImageDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    
    public ItemImageServiceImpl() 
    {
        super();        
        itemImageDao = new ItemImageDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public ItemImageServiceImpl(ServletContext context) 
    {
        super();        
        itemImageDao = (ItemImageDao) context.getAttribute("itemImageDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<ItemImage> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(itemImageDao.find(id));
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
    public Result<List<ItemImage>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<ItemImage> itemImageList =  itemImageDao.findAll(null, null);
                return ResultFactory.getSuccessResult(itemImageList);
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
    public Result<ItemImage> store(String userName, Integer id, String imageName, String thumbnailName, String alternateText, Integer rank, Integer itemId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        ItemImage itemImage;

        if (id == null) 
        {
            itemImage = new ItemImage();
        } 
        else 
        {
            itemImage = itemImageDao.find(id);

            if (itemImage == null) 
            {
                return ResultFactory.getFailResult("Unable to find ItemImage instance with Id=" + id);
            }
        }

        itemImage.setImageName(imageName);
        itemImage.setThumbnailName(thumbnailName);
        itemImage.setAlternateText(alternateText);
        itemImage.setRank(rank);
        itemImage.setItemId(itemId);
        
        
        if (itemImage.getId() == null) 
        {
            itemImageDao.add(itemImage);
        } 
        else 
        {
            itemImage = itemImageDao.update(itemImage);
        }

        return ResultFactory.getSuccessResult(itemImage);

    }
  
    @Override
    public Result<ItemImage> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove ItemImage [null id]");
        } 

        ItemImage itemImage = itemImageDao.find(id);

        if (itemImage == null) 
        {
            return ResultFactory.getFailResult("Unable to load ItemImage for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            itemImageDao.getRelatedObjects(itemImage);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                itemImageDao.remove(itemImage);
                
                String msg = "ItemImage with Id: " + itemImage.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("ItemImage is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
