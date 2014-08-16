





















































package com.busy.engine.service;

import com.busy.engine.dao.PaypalDao;
import com.busy.engine.dao.PaypalDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.Paypal;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class PaypalServiceImpl extends AbstractService implements PaypalService 
{
    protected PaypalDao paypalDao = new PaypalDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public PaypalServiceImpl() 
    {
        super();
    }

    @Override
    public Result<Paypal> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName)) 
            {
                return ResultFactory.getSuccessResult(paypalDao.find(id));
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
    public Result<List<Paypal>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName)) 
            {
                List<Paypal> paypalList =  paypalDao.findAll(null, null);
                return ResultFactory.getSuccessResult(paypalList);
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
    public Result<Paypal> store(String userName, Integer id, String payPalUrl, String currencyCode, String apiUsername, String apiPassword, String apiSignature, String apiEndpoint, Boolean activeProfile, String returnUrl, String cancelUrl, String paymentType, String environment)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        Paypal paypal;

        if (id == null) 
        {
            paypal = new Paypal();
        } 
        else 
        {
            paypal = paypalDao.find(id);

            if (paypal == null) 
            {
                return ResultFactory.getFailResult("Unable to find Paypal instance with Id=" + id);
            }
        }

        paypal.setPayPalUrl(payPalUrl);
        paypal.setCurrencyCode(currencyCode);
        paypal.setApiUsername(apiUsername);
        paypal.setApiPassword(apiPassword);
        paypal.setApiSignature(apiSignature);
        paypal.setApiEndpoint(apiEndpoint);
        paypal.setActiveProfile(activeProfile);
        paypal.setReturnUrl(returnUrl);
        paypal.setCancelUrl(cancelUrl);
        paypal.setPaymentType(paymentType);
        paypal.setEnvironment(environment);
        
        
        if (paypal.getId() == null) 
        {
            paypalDao.add(paypal);
        } 
        else 
        {
            paypal = paypalDao.update(paypal);
        }

        return ResultFactory.getSuccessResult(paypal);

    }
  
    @Override
    public Result<Paypal> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove Paypal [null id]");
        } 

        Paypal paypal = paypalDao.find(id);

        if (paypal == null) 
        {
            return ResultFactory.getFailResult("Unable to load Paypal for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            paypalDao.getRelatedObjects(paypal);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                paypalDao.remove(paypal);
                
                String msg = "Paypal with Id: " + paypal.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("Paypal is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
