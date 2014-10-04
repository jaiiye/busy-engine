


































package com.busy.engine.service;

import com.busy.engine.dao.RecurringPaymentDao;
import com.busy.engine.dao.RecurringPaymentDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.RecurringPayment;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class RecurringPaymentServiceImpl extends AbstractService implements RecurringPaymentService 
{
    protected RecurringPaymentDao recurringPaymentDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    
    public RecurringPaymentServiceImpl() 
    {
        super();        
        recurringPaymentDao = new RecurringPaymentDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public RecurringPaymentServiceImpl(ServletContext context) 
    {
        super();        
        recurringPaymentDao = (RecurringPaymentDao) context.getAttribute("recurringPaymentDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<RecurringPayment> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(recurringPaymentDao.find(id));
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
    public Result<List<RecurringPayment>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<RecurringPayment> recurringPaymentList =  recurringPaymentDao.findAll(null, null);
                return ResultFactory.getSuccessResult(recurringPaymentList);
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
    public Result<RecurringPayment> store(String userName, Integer id, Integer cycleLength, Integer cyclePeriod, Integer totalCycles, Date startDate, Integer orderId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        RecurringPayment recurringPayment;

        if (id == null) 
        {
            recurringPayment = new RecurringPayment();
        } 
        else 
        {
            recurringPayment = recurringPaymentDao.find(id);

            if (recurringPayment == null) 
            {
                return ResultFactory.getFailResult("Unable to find RecurringPayment instance with Id=" + id);
            }
        }

        recurringPayment.setCycleLength(cycleLength);
        recurringPayment.setCyclePeriod(cyclePeriod);
        recurringPayment.setTotalCycles(totalCycles);
        recurringPayment.setStartDate(startDate);
        recurringPayment.setOrderId(orderId);
        
        
        if (recurringPayment.getId() == null) 
        {
            recurringPaymentDao.add(recurringPayment);
        } 
        else 
        {
            recurringPayment = recurringPaymentDao.update(recurringPayment);
        }

        return ResultFactory.getSuccessResult(recurringPayment);

    }
  
    @Override
    public Result<RecurringPayment> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove RecurringPayment [null id]");
        } 

        RecurringPayment recurringPayment = recurringPaymentDao.find(id);

        if (recurringPayment == null) 
        {
            return ResultFactory.getFailResult("Unable to load RecurringPayment for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            recurringPaymentDao.getRelatedObjects(recurringPayment);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                recurringPaymentDao.remove(recurringPayment);
                
                String msg = "RecurringPayment with Id: " + recurringPayment.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("RecurringPayment is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
