


































package com.busy.engine.service;

import com.busy.engine.dao.SliderDao;
import com.busy.engine.dao.SliderDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.Slider;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class SliderServiceImpl extends AbstractService implements SliderService 
{
    protected SliderDao sliderDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    
    public SliderServiceImpl() 
    {
        super();        
        sliderDao = new SliderDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public SliderServiceImpl(ServletContext context) 
    {
        super();        
        sliderDao = (SliderDao) context.getAttribute("sliderDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<Slider> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(sliderDao.find(id));
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
    public Result<List<Slider>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<Slider> sliderList =  sliderDao.findAll(null, null);
                return ResultFactory.getSuccessResult(sliderList);
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
    public Result<Slider> store(String userName, Integer id, String sliderName, Integer sliderTypeId, Integer formId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        Slider slider;

        if (id == null) 
        {
            slider = new Slider();
        } 
        else 
        {
            slider = sliderDao.find(id);

            if (slider == null) 
            {
                return ResultFactory.getFailResult("Unable to find Slider instance with Id=" + id);
            }
        }

        slider.setSliderName(sliderName);
        slider.setSliderTypeId(sliderTypeId);
        slider.setFormId(formId);
        
        
        if (slider.getId() == null) 
        {
            sliderDao.add(slider);
        } 
        else 
        {
            slider = sliderDao.update(slider);
        }

        return ResultFactory.getSuccessResult(slider);

    }
  
    @Override
    public Result<Slider> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove Slider [null id]");
        } 

        Slider slider = sliderDao.find(id);

        if (slider == null) 
        {
            return ResultFactory.getFailResult("Unable to load Slider for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            sliderDao.getRelatedObjects(slider);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(slider.getPageList().size() != 0)
            {
                relatedObjectNames += "Page ";  
                canBeDeleted = false;
            }
                        
            if(slider.getSliderItemList().size() != 0)
            {
                relatedObjectNames += "SliderItem ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                sliderDao.remove(slider);
                
                String msg = "Slider with Id: " + slider.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("Slider is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
