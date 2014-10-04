


































package com.busy.engine.service;

import com.busy.engine.dao.ShipmentDao;
import com.busy.engine.dao.ShipmentDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.Shipment;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class ShipmentServiceImpl extends AbstractService implements ShipmentService 
{
    protected ShipmentDao shipmentDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    
    public ShipmentServiceImpl() 
    {
        super();        
        shipmentDao = new ShipmentDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public ShipmentServiceImpl(ServletContext context) 
    {
        super();        
        shipmentDao = (ShipmentDao) context.getAttribute("shipmentDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<Shipment> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(shipmentDao.find(id));
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
    public Result<List<Shipment>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<Shipment> shipmentList =  shipmentDao.findAll(null, null);
                return ResultFactory.getSuccessResult(shipmentList);
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
    public Result<Shipment> store(String userName, Integer id, Date createdOn, String trackingNumber, Double totalWeight, Date shipDate, Date deliveryDate, Integer itemQuantity, Integer orderId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        Shipment shipment;

        if (id == null) 
        {
            shipment = new Shipment();
        } 
        else 
        {
            shipment = shipmentDao.find(id);

            if (shipment == null) 
            {
                return ResultFactory.getFailResult("Unable to find Shipment instance with Id=" + id);
            }
        }

        shipment.setCreatedOn(createdOn);
        shipment.setTrackingNumber(trackingNumber);
        shipment.setTotalWeight(totalWeight);
        shipment.setShipDate(shipDate);
        shipment.setDeliveryDate(deliveryDate);
        shipment.setItemQuantity(itemQuantity);
        shipment.setOrderId(orderId);
        
        
        if (shipment.getId() == null) 
        {
            shipmentDao.add(shipment);
        } 
        else 
        {
            shipment = shipmentDao.update(shipment);
        }

        return ResultFactory.getSuccessResult(shipment);

    }
  
    @Override
    public Result<Shipment> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove Shipment [null id]");
        } 

        Shipment shipment = shipmentDao.find(id);

        if (shipment == null) 
        {
            return ResultFactory.getFailResult("Unable to load Shipment for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            shipmentDao.getRelatedObjects(shipment);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                shipmentDao.remove(shipment);
                
                String msg = "Shipment with Id: " + shipment.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("Shipment is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
