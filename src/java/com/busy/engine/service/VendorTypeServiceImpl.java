





































package com.busy.engine.service;

import com.busy.engine.dao.VendorTypeDao;
import com.busy.engine.dao.VendorTypeDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.VendorType;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class VendorTypeServiceImpl extends AbstractService implements VendorTypeService 
{
    protected VendorTypeDao vendorTypeDao = new VendorTypeDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public VendorTypeServiceImpl() 
    {
        super();
    }

    @Override
    public Result<VendorType> find(String userName, Integer id)
    {

        if (isValidUser(userName)) 
        {
            return ResultFactory.getSuccessResult(vendorTypeDao.find(id));
        }
        else 
        {            
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }
    
    @Override
    public Result<List<VendorType>> findAll(String userName) 
    {
        if (isValidUser(userName)) 
        {
            List<VendorType> vendorTypeList =  vendorTypeDao.findAll(null, null);
            return ResultFactory.getSuccessResult(vendorTypeList);
        } 
        else 
        {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    @Override
    public Result<VendorType> store(String userName, Integer id, String typeName)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        VendorType vendorType;

        if (id == null) 
        {
            vendorType = new VendorType();
        } 
        else 
        {
            vendorType = vendorTypeDao.find(id);

            if (vendorType == null) 
            {
                return ResultFactory.getFailResult("Unable to find VendorType instance with Id=" + id);
            }
        }

        vendorType.setTypeName(typeName);
        
        
        if (vendorType.getId() == null) 
        {
            vendorTypeDao.add(vendorType);
        } 
        else 
        {
            vendorType = vendorTypeDao.update(vendorType);
        }

        return ResultFactory.getSuccessResult(vendorType);

    }
  
    @Override
    public Result<VendorType> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove VendorType [null id]");
        } 

        VendorType vendorType = vendorTypeDao.find(id);

        if (vendorType == null) 
        {
            return ResultFactory.getFailResult("Unable to load VendorType for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            vendorTypeDao.getRelatedObjects(vendorType);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(vendorType.getVendorList().size() != 0)
            {
                relatedObjectNames += "Vendor ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                vendorTypeDao.remove(vendorType);
                
                String msg = "VendorType with Id: " + vendorType.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("VendorType is used with to [" + relatedObjectNames + "] and could not be deleted");
            }
            
        }

    }

}

