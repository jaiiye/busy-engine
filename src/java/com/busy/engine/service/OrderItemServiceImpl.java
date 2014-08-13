





































package com.busy.engine.service;

import com.busy.engine.dao.OrderItemDao;
import com.busy.engine.dao.OrderItemDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.OrderItem;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class OrderItemServiceImpl extends AbstractService implements OrderItemService 
{
    protected OrderItemDao orderItemDao = new OrderItemDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public OrderItemServiceImpl() 
    {
        super();
    }

    @Override
    public Result<OrderItem> find(String userName, Integer id)
    {

        if (isValidUser(userName)) 
        {
            return ResultFactory.getSuccessResult(orderItemDao.find(id));
        }
        else 
        {            
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }
    
    @Override
    public Result<List<OrderItem>> findAll(String userName) 
    {
        if (isValidUser(userName)) 
        {
            List<OrderItem> orderItemList =  orderItemDao.findAll(null, null);
            return ResultFactory.getSuccessResult(orderItemList);
        } 
        else 
        {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    @Override
    public Result<OrderItem> store(String userName, Integer id, Integer customerOrderId, Integer itemId, Integer quantity, String optionName, Double unitPrice)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        OrderItem orderItem;

        if (id == null) 
        {
            orderItem = new OrderItem();
        } 
        else 
        {
            orderItem = orderItemDao.find(id);

            if (orderItem == null) 
            {
                return ResultFactory.getFailResult("Unable to find OrderItem instance with Id=" + id);
            }
        }

        orderItem.setCustomerOrderId(customerOrderId);
        orderItem.setItemId(itemId);
        orderItem.setQuantity(quantity);
        orderItem.setOptionName(optionName);
        orderItem.setUnitPrice(unitPrice);
        
        
        if (orderItem.getId() == null) 
        {
            orderItemDao.add(orderItem);
        } 
        else 
        {
            orderItem = orderItemDao.update(orderItem);
        }

        return ResultFactory.getSuccessResult(orderItem);

    }
  
    @Override
    public Result<OrderItem> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove OrderItem [null id]");
        } 

        OrderItem orderItem = orderItemDao.find(id);

        if (orderItem == null) 
        {
            return ResultFactory.getFailResult("Unable to load OrderItem for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            orderItemDao.getRelatedObjects(orderItem);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(orderItem.getReturnRequestList().size() != 0)
            {
                relatedObjectNames += "ReturnRequest ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                orderItemDao.remove(orderItem);
                
                String msg = "OrderItem with Id: " + orderItem.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("OrderItem is used with to [" + relatedObjectNames + "] and could not be deleted");
            }
            
        }

    }

}

