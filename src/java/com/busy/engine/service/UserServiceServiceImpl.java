


































package com.busy.engine.service;

import com.busy.engine.dao.UserServiceDao;
import com.busy.engine.dao.UserServiceDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.UserService;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class UserServiceServiceImpl extends AbstractService implements UserServiceService 
{
    protected UserServiceDao userServiceDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    
    public UserServiceServiceImpl() 
    {
        super();        
        userServiceDao = new UserServiceDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public UserServiceServiceImpl(ServletContext context) 
    {
        super();        
        userServiceDao = (UserServiceDao) context.getAttribute("userServiceDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<UserService> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(userServiceDao.find(id));
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
    public Result<List<UserService>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<UserService> userServiceList =  userServiceDao.findAll(null, null);
                return ResultFactory.getSuccessResult(userServiceList);
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
    public Result<UserService> store(String userName, Integer id, Date startDate, Date endDate, String details, String contractUrl, String deliverableUrl, Double depositAmount, Integer userRank, Integer blogId, Integer userId, Integer serviceId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        UserService userService;

        if (id == null) 
        {
            userService = new UserService();
        } 
        else 
        {
            userService = userServiceDao.find(id);

            if (userService == null) 
            {
                return ResultFactory.getFailResult("Unable to find UserService instance with Id=" + id);
            }
        }

        userService.setStartDate(startDate);
        userService.setEndDate(endDate);
        userService.setDetails(details);
        userService.setContractUrl(contractUrl);
        userService.setDeliverableUrl(deliverableUrl);
        userService.setDepositAmount(depositAmount);
        userService.setUserRank(userRank);
        userService.setBlogId(blogId);
        userService.setUserId(userId);
        userService.setServiceId(serviceId);
        
        
        if (userService.getId() == null) 
        {
            userServiceDao.add(userService);
        } 
        else 
        {
            userService = userServiceDao.update(userService);
        }

        return ResultFactory.getSuccessResult(userService);

    }
  
    @Override
    public Result<UserService> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove UserService [null id]");
        } 

        UserService userService = userServiceDao.find(id);

        if (userService == null) 
        {
            return ResultFactory.getFailResult("Unable to load UserService for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            userServiceDao.getRelatedObjects(userService);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(userService.getServiceChargeList().size() != 0)
            {
                relatedObjectNames += "ServiceCharge ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                userServiceDao.remove(userService);
                
                String msg = "UserService with Id: " + userService.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("UserService is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
