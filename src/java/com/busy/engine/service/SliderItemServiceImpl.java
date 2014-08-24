


































package com.busy.engine.service;

import com.busy.engine.dao.SliderItemDao;
import com.busy.engine.dao.SliderItemDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.SliderItem;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class SliderItemServiceImpl extends AbstractService implements SliderItemService 
{
    protected SliderItemDao sliderItemDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    

    public SliderItemServiceImpl() 
    {
        super();
        
        sliderItemDao = new SliderItemDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public SliderItemServiceImpl(ServletContext context) 
    {
        super();
        
        sliderItemDao = (SliderItemDao) context.getAttribute("sliderItemDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<SliderItem> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(sliderItemDao.find(id));
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
    public Result<List<SliderItem>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<SliderItem> sliderItemList =  sliderItemDao.findAll(null, null);
                return ResultFactory.getSuccessResult(sliderItemList);
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
    public Result<SliderItem> store(String userName, Integer id, String title, String description, String url, String imageName, String alternateText, Integer rank, Integer sliderId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        SliderItem sliderItem;

        if (id == null) 
        {
            sliderItem = new SliderItem();
        } 
        else 
        {
            sliderItem = sliderItemDao.find(id);

            if (sliderItem == null) 
            {
                return ResultFactory.getFailResult("Unable to find SliderItem instance with Id=" + id);
            }
        }

        sliderItem.setTitle(title);
        sliderItem.setDescription(description);
        sliderItem.setUrl(url);
        sliderItem.setImageName(imageName);
        sliderItem.setAlternateText(alternateText);
        sliderItem.setRank(rank);
        sliderItem.setSliderId(sliderId);
        
        
        if (sliderItem.getId() == null) 
        {
            sliderItemDao.add(sliderItem);
        } 
        else 
        {
            sliderItem = sliderItemDao.update(sliderItem);
        }

        return ResultFactory.getSuccessResult(sliderItem);

    }
  
    @Override
    public Result<SliderItem> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove SliderItem [null id]");
        } 

        SliderItem sliderItem = sliderItemDao.find(id);

        if (sliderItem == null) 
        {
            return ResultFactory.getFailResult("Unable to load SliderItem for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            sliderItemDao.getRelatedObjects(sliderItem);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                sliderItemDao.remove(sliderItem);
                
                String msg = "SliderItem with Id: " + sliderItem.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("SliderItem is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
