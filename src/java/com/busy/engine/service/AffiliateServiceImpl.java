





































package com.busy.engine.service;

import com.busy.engine.dao.AffiliateDao;
import com.busy.engine.dao.AffiliateDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.Affiliate;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class AffiliateServiceImpl extends AbstractService implements AffiliateService 
{
    protected AffiliateDao affiliateDao = new AffiliateDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public AffiliateServiceImpl() 
    {
        super();
    }

    @Override
    public Result<Affiliate> find(String userName, Integer id)
    {

        if (isValidUser(userName)) 
        {
            return ResultFactory.getSuccessResult(affiliateDao.find(id));
        }
        else 
        {            
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }
    
    @Override
    public Result<List<Affiliate>> findAll(String userName) 
    {
        if (isValidUser(userName)) 
        {
            List<Affiliate> affiliateList =  affiliateDao.findAll(null, null);
            return ResultFactory.getSuccessResult(affiliateList);
        } 
        else 
        {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    @Override
    public Result<Affiliate> store(String userName, Integer id, String companyName, String email, String phone, String fax, String webUrl, String details, Integer serviceHours, Integer affiliateStatus, Integer userId, Integer contactId, Integer addressId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        Affiliate affiliate;

        if (id == null) 
        {
            affiliate = new Affiliate();
        } 
        else 
        {
            affiliate = affiliateDao.find(id);

            if (affiliate == null) 
            {
                return ResultFactory.getFailResult("Unable to find Affiliate instance with Id=" + id);
            }
        }

        affiliate.setCompanyName(companyName);
        affiliate.setEmail(email);
        affiliate.setPhone(phone);
        affiliate.setFax(fax);
        affiliate.setWebUrl(webUrl);
        affiliate.setDetails(details);
        affiliate.setServiceHours(serviceHours);
        affiliate.setAffiliateStatus(affiliateStatus);
        affiliate.setUserId(userId);
        affiliate.setContactId(contactId);
        affiliate.setAddressId(addressId);
        
        
        if (affiliate.getId() == null) 
        {
            affiliateDao.add(affiliate);
        } 
        else 
        {
            affiliate = affiliateDao.update(affiliate);
        }

        return ResultFactory.getSuccessResult(affiliate);

    }
  
    @Override
    public Result<Affiliate> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove Affiliate [null id]");
        } 

        Affiliate affiliate = affiliateDao.find(id);

        if (affiliate == null) 
        {
            return ResultFactory.getFailResult("Unable to load Affiliate for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            affiliateDao.getRelatedObjects(affiliate);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(affiliate.getOrderList().size() != 0)
            {
                relatedObjectNames += "Order ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                affiliateDao.remove(affiliate);
                
                String msg = "Affiliate with Id: " + affiliate.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("Affiliate is used with to [" + relatedObjectNames + "] and could not be deleted");
            }
            
        }

    }

}

