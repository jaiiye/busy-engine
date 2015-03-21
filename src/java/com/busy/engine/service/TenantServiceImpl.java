


































package com.busy.engine.service;

import com.busy.engine.dao.TenantDao;
import com.busy.engine.dao.TenantDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.Tenant;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class TenantServiceImpl extends AbstractService implements TenantService 
{
    protected TenantDao tenantDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    
    public TenantServiceImpl() 
    {
        super();        
        tenantDao = new TenantDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public TenantServiceImpl(ServletContext context) 
    {
        super();        
        tenantDao = (TenantDao) context.getAttribute("tenantDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<Tenant> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(tenantDao.find(id));
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
    public Result<List<Tenant>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<Tenant> tenantList =  tenantDao.findAll(null, null);
                return ResultFactory.getSuccessResult(tenantList);
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
    public Result<Tenant> store(String userName, Integer id, String name, String logo, Integer dashboardId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        Tenant tenant;

        if (id == null) 
        {
            tenant = new Tenant();
        } 
        else 
        {
            tenant = tenantDao.find(id);

            if (tenant == null) 
            {
                return ResultFactory.getFailResult("Unable to find Tenant instance with Id=" + id);
            }
        }

        tenant.setName(name);
        tenant.setLogo(logo);
        tenant.setDashboardId(dashboardId);
        
        
        if (tenant.getId() == null) 
        {
            tenantDao.add(tenant);
        } 
        else 
        {
            tenant = tenantDao.update(tenant);
        }

        return ResultFactory.getSuccessResult(tenant);

    }
  
    @Override
    public Result<Tenant> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove Tenant [null id]");
        } 

        Tenant tenant = tenantDao.find(id);

        if (tenant == null) 
        {
            return ResultFactory.getFailResult("Unable to load Tenant for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            tenantDao.getRelatedObjects(tenant);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(tenant.getSiteList().size() != 0)
            {
                relatedObjectNames += "Site ";  
                canBeDeleted = false;
            }
                        
            if(tenant.getTenantAttributeList().size() != 0)
            {
                relatedObjectNames += "TenantAttribute ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                tenantDao.remove(tenant);
                
                String msg = "Tenant with Id: " + tenant.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("Tenant is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
