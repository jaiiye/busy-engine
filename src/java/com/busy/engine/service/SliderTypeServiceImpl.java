


































package com.busy.engine.service;

import com.busy.engine.dao.SliderTypeDao;
import com.busy.engine.dao.SliderTypeDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.SliderType;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class SliderTypeServiceImpl extends AbstractService implements SliderTypeService 
{
    protected SliderTypeDao sliderTypeDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    

    public SliderTypeServiceImpl() 
    {
        super();
        
        sliderTypeDao = new SliderTypeDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public SliderTypeServiceImpl(ServletContext context) 
    {
        super();
        
        sliderTypeDao = (SliderTypeDao) context.getAttribute("sliderTypeDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<SliderType> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(sliderTypeDao.find(id));
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
    public Result<List<SliderType>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<SliderType> sliderTypeList =  sliderTypeDao.findAll(null, null);
                return ResultFactory.getSuccessResult(sliderTypeList);
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
    public Result<SliderType> store(String userName, Integer id, String typeName, String code)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        SliderType sliderType;

        if (id == null) 
        {
            sliderType = new SliderType();
        } 
        else 
        {
            sliderType = sliderTypeDao.find(id);

            if (sliderType == null) 
            {
                return ResultFactory.getFailResult("Unable to find SliderType instance with Id=" + id);
            }
        }

        sliderType.setTypeName(typeName);
        sliderType.setCode(code);
        
        
        if (sliderType.getId() == null) 
        {
            sliderTypeDao.add(sliderType);
        } 
        else 
        {
            sliderType = sliderTypeDao.update(sliderType);
        }

        return ResultFactory.getSuccessResult(sliderType);

    }
  
    @Override
    public Result<SliderType> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove SliderType [null id]");
        } 

        SliderType sliderType = sliderTypeDao.find(id);

        if (sliderType == null) 
        {
            return ResultFactory.getFailResult("Unable to load SliderType for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            sliderTypeDao.getRelatedObjects(sliderType);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(sliderType.getSliderList().size() != 0)
            {
                relatedObjectNames += "Slider ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                sliderTypeDao.remove(sliderType);
                
                String msg = "SliderType with Id: " + sliderType.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("SliderType is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
