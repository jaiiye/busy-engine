





































package com.busy.engine.service;

import com.busy.engine.dao.DiscountDao;
import com.busy.engine.dao.DiscountDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.Discount;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class DiscountServiceImpl extends AbstractService implements DiscountService 
{
    protected DiscountDao discountDao = new DiscountDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public DiscountServiceImpl() 
    {
        super();
    }

    @Override
    public Result<Discount> find(String userName, Integer id)
    {

        if (isValidUser(userName)) 
        {
            return ResultFactory.getSuccessResult(discountDao.find(id));
        }
        else 
        {            
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }
    
    @Override
    public Result<List<Discount>> findAll(String userName) 
    {
        if (isValidUser(userName)) 
        {
            List<Discount> discountList =  discountDao.findAll(null, null);
            return ResultFactory.getSuccessResult(discountList);
        } 
        else 
        {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    @Override
    public Result<Discount> store(String userName, Integer id, String discountName, Double discountAmount, Double discountPercent, Date startDate, Date endDate, String couponCode, Integer discountStatus, Integer askCouponCode, Integer usePercentage)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        Discount discount;

        if (id == null) 
        {
            discount = new Discount();
        } 
        else 
        {
            discount = discountDao.find(id);

            if (discount == null) 
            {
                return ResultFactory.getFailResult("Unable to find Discount instance with Id=" + id);
            }
        }

        discount.setDiscountName(discountName);
        discount.setDiscountAmount(discountAmount);
        discount.setDiscountPercent(discountPercent);
        discount.setStartDate(startDate);
        discount.setEndDate(endDate);
        discount.setCouponCode(couponCode);
        discount.setDiscountStatus(discountStatus);
        discount.setAskCouponCode(askCouponCode);
        discount.setUsePercentage(usePercentage);
        
        
        if (discount.getId() == null) 
        {
            discountDao.add(discount);
        } 
        else 
        {
            discount = discountDao.update(discount);
        }

        return ResultFactory.getSuccessResult(discount);

    }
  
    @Override
    public Result<Discount> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove Discount [null id]");
        } 

        Discount discount = discountDao.find(id);

        if (discount == null) 
        {
            return ResultFactory.getFailResult("Unable to load Discount for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            discountDao.getRelatedObjects(discount);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(discount.getCategoryList().size() != 0)
            {
                relatedObjectNames += "Category ";  
                canBeDeleted = false;
            }
                        
            if(discount.getCustomerOrderList().size() != 0)
            {
                relatedObjectNames += "CustomerOrder ";  
                canBeDeleted = false;
            }
                        
            if(discount.getItemDiscountList().size() != 0)
            {
                relatedObjectNames += "ItemDiscount ";  
                canBeDeleted = false;
            }
                        
            if(discount.getUserGroupList().size() != 0)
            {
                relatedObjectNames += "UserGroup ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                discountDao.remove(discount);
                
                String msg = "Discount with Id: " + discount.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("Discount is used with to [" + relatedObjectNames + "] and could not be deleted");
            }
            
        }

    }

}

