


































package com.busy.engine.service;

import com.busy.engine.dao.TenantAttributeDao;
import com.busy.engine.dao.TenantAttributeDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.TenantAttribute;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class TenantAttributeServiceImpl extends AbstractService implements TenantAttributeService 
{
    protected TenantAttributeDao tenantAttributeDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    
    public TenantAttributeServiceImpl() 
    {
        super();        
        tenantAttributeDao = new TenantAttributeDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public TenantAttributeServiceImpl(ServletContext context) 
    {
        super();        
        tenantAttributeDao = (TenantAttributeDao) context.getAttribute("tenantAttributeDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<TenantAttribute> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(tenantAttributeDao.find(id));
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
    public Result<List<TenantAttribute>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<TenantAttribute> tenantAttributeList =  tenantAttributeDao.findAll(null, null);
                return ResultFactory.getSuccessResult(tenantAttributeList);
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
    public Result<TenantAttribute> store(String userName, Integer id, String attributeKey, String attributeValue, Integer tenantId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        TenantAttribute tenantAttribute;

        if (id == null) 
        {
            tenantAttribute = new TenantAttribute();
        } 
        else 
        {
            tenantAttribute = tenantAttributeDao.find(id);

            if (tenantAttribute == null) 
            {
                return ResultFactory.getFailResult("Unable to find TenantAttribute instance with Id=" + id);
            }
        }

        tenantAttribute.setAttributeKey(attributeKey);
        tenantAttribute.setAttributeValue(attributeValue);
        tenantAttribute.setTenantId(tenantId);
        
        
        if (tenantAttribute.getId() == null) 
        {
            tenantAttributeDao.add(tenantAttribute);
        } 
        else 
        {
            tenantAttribute = tenantAttributeDao.update(tenantAttribute);
        }

        return ResultFactory.getSuccessResult(tenantAttribute);

    }
  
    @Override
    public Result<TenantAttribute> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove TenantAttribute [null id]");
        } 

        TenantAttribute tenantAttribute = tenantAttributeDao.find(id);

        if (tenantAttribute == null) 
        {
            return ResultFactory.getFailResult("Unable to load TenantAttribute for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            tenantAttributeDao.getRelatedObjects(tenantAttribute);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                tenantAttributeDao.remove(tenantAttribute);
                
                String msg = "TenantAttribute with Id: " + tenantAttribute.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("TenantAttribute is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
