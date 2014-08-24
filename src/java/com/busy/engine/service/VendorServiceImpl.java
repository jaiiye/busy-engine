


































package com.busy.engine.service;

import com.busy.engine.dao.VendorDao;
import com.busy.engine.dao.VendorDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.Vendor;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class VendorServiceImpl extends AbstractService implements VendorService 
{
    protected VendorDao vendorDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    

    public VendorServiceImpl() 
    {
        super();
        
        vendorDao = new VendorDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public VendorServiceImpl(ServletContext context) 
    {
        super();
        
        vendorDao = (VendorDao) context.getAttribute("vendorDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<Vendor> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(vendorDao.find(id));
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
    public Result<List<Vendor>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<Vendor> vendorList =  vendorDao.findAll(null, null);
                return ResultFactory.getSuccessResult(vendorList);
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
    public Result<Vendor> store(String userName, Integer id, String vendorName, String description, Integer rank, Integer vendorStatus, Integer metaTagId, Integer templateId, Integer vendorTypeId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        Vendor vendor;

        if (id == null) 
        {
            vendor = new Vendor();
        } 
        else 
        {
            vendor = vendorDao.find(id);

            if (vendor == null) 
            {
                return ResultFactory.getFailResult("Unable to find Vendor instance with Id=" + id);
            }
        }

        vendor.setVendorName(vendorName);
        vendor.setDescription(description);
        vendor.setRank(rank);
        vendor.setVendorStatus(vendorStatus);
        vendor.setMetaTagId(metaTagId);
        vendor.setTemplateId(templateId);
        vendor.setVendorTypeId(vendorTypeId);
        
        
        if (vendor.getId() == null) 
        {
            vendorDao.add(vendor);
        } 
        else 
        {
            vendor = vendorDao.update(vendor);
        }

        return ResultFactory.getSuccessResult(vendor);

    }
  
    @Override
    public Result<Vendor> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove Vendor [null id]");
        } 

        Vendor vendor = vendorDao.find(id);

        if (vendor == null) 
        {
            return ResultFactory.getFailResult("Unable to load Vendor for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            vendorDao.getRelatedObjects(vendor);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(vendor.getItemList().size() != 0)
            {
                relatedObjectNames += "Item ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                vendorDao.remove(vendor);
                
                String msg = "Vendor with Id: " + vendor.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("Vendor is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
