


































package com.busy.engine.service;

import com.busy.engine.dao.OrderDao;
import com.busy.engine.dao.OrderDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.Order;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class OrderServiceImpl extends AbstractService implements OrderService 
{
    protected OrderDao orderDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    

    public OrderServiceImpl() 
    {
        super();
        
        orderDao = new OrderDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public OrderServiceImpl(ServletContext context) 
    {
        super();
        
        orderDao = (OrderDao) context.getAttribute("orderDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<Order> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(orderDao.find(id));
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
    public Result<List<Order>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<Order> orderList =  orderDao.findAll(null, null);
                return ResultFactory.getSuccessResult(orderList);
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
    public Result<Order> store(String userName, Integer id, Date orderDate, Date shipDate, String paymentMethod, String purchaseOrder, String transactionId, Double amountBilled, String paymentStatus, String pendingReason, String paymentType, Double transactionFee, String currencyCode, String payerId, Double subtotalAmount, Double discountAmount, Double taxAmount, Double shippingAmount, Double totalAmount, Double refundAmount, String notes, Integer orderStatus, Integer shippingId, Integer affiliateId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        Order order;

        if (id == null) 
        {
            order = new Order();
        } 
        else 
        {
            order = orderDao.find(id);

            if (order == null) 
            {
                return ResultFactory.getFailResult("Unable to find Order instance with Id=" + id);
            }
        }

        order.setOrderDate(orderDate);
        order.setShipDate(shipDate);
        order.setPaymentMethod(paymentMethod);
        order.setPurchaseOrder(purchaseOrder);
        order.setTransactionId(transactionId);
        order.setAmountBilled(amountBilled);
        order.setPaymentStatus(paymentStatus);
        order.setPendingReason(pendingReason);
        order.setPaymentType(paymentType);
        order.setTransactionFee(transactionFee);
        order.setCurrencyCode(currencyCode);
        order.setPayerId(payerId);
        order.setSubtotalAmount(subtotalAmount);
        order.setDiscountAmount(discountAmount);
        order.setTaxAmount(taxAmount);
        order.setShippingAmount(shippingAmount);
        order.setTotalAmount(totalAmount);
        order.setRefundAmount(refundAmount);
        order.setNotes(notes);
        order.setOrderStatus(orderStatus);
        order.setShippingId(shippingId);
        order.setAffiliateId(affiliateId);
        
        
        if (order.getId() == null) 
        {
            orderDao.add(order);
        } 
        else 
        {
            order = orderDao.update(order);
        }

        return ResultFactory.getSuccessResult(order);

    }
  
    @Override
    public Result<Order> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove Order [null id]");
        } 

        Order order = orderDao.find(id);

        if (order == null) 
        {
            return ResultFactory.getFailResult("Unable to load Order for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            orderDao.getRelatedObjects(order);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(order.getCustomerOrderList().size() != 0)
            {
                relatedObjectNames += "CustomerOrder ";  
                canBeDeleted = false;
            }
                        
            if(order.getRecurringPaymentList().size() != 0)
            {
                relatedObjectNames += "RecurringPayment ";  
                canBeDeleted = false;
            }
                        
            if(order.getShipmentList().size() != 0)
            {
                relatedObjectNames += "Shipment ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                orderDao.remove(order);
                
                String msg = "Order with Id: " + order.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("Order is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
