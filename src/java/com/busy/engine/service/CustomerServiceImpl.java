





















































package com.busy.engine.service;

import com.busy.engine.dao.CustomerDao;
import com.busy.engine.dao.CustomerDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.Customer;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class CustomerServiceImpl extends AbstractService implements CustomerService 
{
    protected CustomerDao customerDao = new CustomerDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public CustomerServiceImpl() 
    {
        super();
    }

    @Override
    public Result<Customer> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName)) 
            {
                return ResultFactory.getSuccessResult(customerDao.find(id));
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
    public Result<List<Customer>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName)) 
            {
                List<Customer> customerList =  customerDao.findAll(null, null);
                return ResultFactory.getSuccessResult(customerList);
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
    public Result<Customer> store(String userName, Integer id, Integer contactId, Integer userId, Integer billingAddressId, Integer shippingAddressId, Integer customerStatus)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        Customer customer;

        if (id == null) 
        {
            customer = new Customer();
        } 
        else 
        {
            customer = customerDao.find(id);

            if (customer == null) 
            {
                return ResultFactory.getFailResult("Unable to find Customer instance with Id=" + id);
            }
        }

        customer.setContactId(contactId);
        customer.setUserId(userId);
        customer.setBillingAddressId(billingAddressId);
        customer.setShippingAddressId(shippingAddressId);
        customer.setCustomerStatus(customerStatus);
        
        
        if (customer.getId() == null) 
        {
            customerDao.add(customer);
        } 
        else 
        {
            customer = customerDao.update(customer);
        }

        return ResultFactory.getSuccessResult(customer);

    }
  
    @Override
    public Result<Customer> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove Customer [null id]");
        } 

        Customer customer = customerDao.find(id);

        if (customer == null) 
        {
            return ResultFactory.getFailResult("Unable to load Customer for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            customerDao.getRelatedObjects(customer);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(customer.getCustomerOrderList().size() != 0)
            {
                relatedObjectNames += "CustomerOrder ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                customerDao.remove(customer);
                
                String msg = "Customer with Id: " + customer.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("Customer is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
