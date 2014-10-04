


































package com.busy.engine.service;

import com.busy.engine.dao.StateProvinceDao;
import com.busy.engine.dao.StateProvinceDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.StateProvince;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class StateProvinceServiceImpl extends AbstractService implements StateProvinceService 
{
    protected StateProvinceDao stateProvinceDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    
    public StateProvinceServiceImpl() 
    {
        super();        
        stateProvinceDao = new StateProvinceDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public StateProvinceServiceImpl(ServletContext context) 
    {
        super();        
        stateProvinceDao = (StateProvinceDao) context.getAttribute("stateProvinceDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<StateProvince> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(stateProvinceDao.find(id));
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
    public Result<List<StateProvince>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<StateProvince> stateProvinceList =  stateProvinceDao.findAll(null, null);
                return ResultFactory.getSuccessResult(stateProvinceList);
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
    public Result<StateProvince> store(String userName, Integer id, String name, String abbreviation, Integer countryId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        StateProvince stateProvince;

        if (id == null) 
        {
            stateProvince = new StateProvince();
        } 
        else 
        {
            stateProvince = stateProvinceDao.find(id);

            if (stateProvince == null) 
            {
                return ResultFactory.getFailResult("Unable to find StateProvince instance with Id=" + id);
            }
        }

        stateProvince.setName(name);
        stateProvince.setAbbreviation(abbreviation);
        stateProvince.setCountryId(countryId);
        
        
        if (stateProvince.getId() == null) 
        {
            stateProvinceDao.add(stateProvince);
        } 
        else 
        {
            stateProvince = stateProvinceDao.update(stateProvince);
        }

        return ResultFactory.getSuccessResult(stateProvince);

    }
  
    @Override
    public Result<StateProvince> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove StateProvince [null id]");
        } 

        StateProvince stateProvince = stateProvinceDao.find(id);

        if (stateProvince == null) 
        {
            return ResultFactory.getFailResult("Unable to load StateProvince for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            stateProvinceDao.getRelatedObjects(stateProvince);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(stateProvince.getShippingList().size() != 0)
            {
                relatedObjectNames += "Shipping ";  
                canBeDeleted = false;
            }
                        
            if(stateProvince.getTaxRateList().size() != 0)
            {
                relatedObjectNames += "TaxRate ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                stateProvinceDao.remove(stateProvince);
                
                String msg = "StateProvince with Id: " + stateProvince.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("StateProvince is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
