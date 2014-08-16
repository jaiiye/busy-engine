





















































package com.busy.engine.service;

import com.busy.engine.dao.CustomerOrderDao;
import com.busy.engine.dao.CustomerOrderDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.CustomerOrder;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class CustomerOrderServiceImpl extends AbstractService implements CustomerOrderService 
{
    protected CustomerOrderDao customerOrderDao = new CustomerOrderDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public CustomerOrderServiceImpl() 
    {
        super();
    }

    @Override
    public Result<CustomerOrder> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName)) 
            {
                return ResultFactory.getSuccessResult(customerOrderDao.find(id));
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
    public Result<List<CustomerOrder>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName)) 
            {
                List<CustomerOrder> customerOrderList =  customerOrderDao.findAll(null, null);
                return ResultFactory.getSuccessResult(customerOrderList);
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
    public Result<CustomerOrder> store(String userName, Integer id, Integer customerId, Integer orderId, Integer discountId, String customerIp)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        CustomerOrder customerOrder;

        if (id == null) 
        {
            customerOrder = new CustomerOrder();
        } 
        else 
        {
            customerOrder = customerOrderDao.find(id);

            if (customerOrder == null) 
            {
                return ResultFactory.getFailResult("Unable to find CustomerOrder instance with Id=" + id);
            }
        }

        customerOrder.setCustomerId(customerId);
        customerOrder.setOrderId(orderId);
        customerOrder.setDiscountId(discountId);
        customerOrder.setCustomerIp(customerIp);
        
        
        if (customerOrder.getId() == null) 
        {
            customerOrderDao.add(customerOrder);
        } 
        else 
        {
            customerOrder = customerOrderDao.update(customerOrder);
        }

        return ResultFactory.getSuccessResult(customerOrder);

    }
  
    @Override
    public Result<CustomerOrder> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove CustomerOrder [null id]");
        } 

        CustomerOrder customerOrder = customerOrderDao.find(id);

        if (customerOrder == null) 
        {
            return ResultFactory.getFailResult("Unable to load CustomerOrder for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            customerOrderDao.getRelatedObjects(customerOrder);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(customerOrder.getOrderItemList().size() != 0)
            {
                relatedObjectNames += "OrderItem ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                customerOrderDao.remove(customerOrder);
                
                String msg = "CustomerOrder with Id: " + customerOrder.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("CustomerOrder is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
