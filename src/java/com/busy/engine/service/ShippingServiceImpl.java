


































package com.busy.engine.service;

import com.busy.engine.dao.ShippingDao;
import com.busy.engine.dao.ShippingDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.Shipping;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class ShippingServiceImpl extends AbstractService implements ShippingService 
{
    protected ShippingDao shippingDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    
    public ShippingServiceImpl() 
    {
        super();        
        shippingDao = new ShippingDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public ShippingServiceImpl(ServletContext context) 
    {
        super();        
        shippingDao = (ShippingDao) context.getAttribute("shippingDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<Shipping> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(shippingDao.find(id));
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
    public Result<List<Shipping>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<Shipping> shippingList =  shippingDao.findAll(null, null);
                return ResultFactory.getSuccessResult(shippingList);
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
    public Result<Shipping> store(String userName, Integer id, String methodName, Double quantity, String unitOfMeasure, Double ratePerUnitCost, Double additionalCost, Integer stateProvinceId, Integer countryId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        Shipping shipping;

        if (id == null) 
        {
            shipping = new Shipping();
        } 
        else 
        {
            shipping = shippingDao.find(id);

            if (shipping == null) 
            {
                return ResultFactory.getFailResult("Unable to find Shipping instance with Id=" + id);
            }
        }

        shipping.setMethodName(methodName);
        shipping.setQuantity(quantity);
        shipping.setUnitOfMeasure(unitOfMeasure);
        shipping.setRatePerUnitCost(ratePerUnitCost);
        shipping.setAdditionalCost(additionalCost);
        shipping.setStateProvinceId(stateProvinceId);
        shipping.setCountryId(countryId);
        
        
        if (shipping.getId() == null) 
        {
            shippingDao.add(shipping);
        } 
        else 
        {
            shipping = shippingDao.update(shipping);
        }

        return ResultFactory.getSuccessResult(shipping);

    }
  
    @Override
    public Result<Shipping> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove Shipping [null id]");
        } 

        Shipping shipping = shippingDao.find(id);

        if (shipping == null) 
        {
            return ResultFactory.getFailResult("Unable to load Shipping for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            shippingDao.getRelatedObjects(shipping);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(shipping.getOrderList().size() != 0)
            {
                relatedObjectNames += "Order ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                shippingDao.remove(shipping);
                
                String msg = "Shipping with Id: " + shipping.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("Shipping is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
