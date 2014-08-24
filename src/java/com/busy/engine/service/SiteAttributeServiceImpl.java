


































package com.busy.engine.service;

import com.busy.engine.dao.SiteAttributeDao;
import com.busy.engine.dao.SiteAttributeDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.SiteAttribute;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class SiteAttributeServiceImpl extends AbstractService implements SiteAttributeService 
{
    protected SiteAttributeDao siteAttributeDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    

    public SiteAttributeServiceImpl() 
    {
        super();
        
        siteAttributeDao = new SiteAttributeDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public SiteAttributeServiceImpl(ServletContext context) 
    {
        super();
        
        siteAttributeDao = (SiteAttributeDao) context.getAttribute("siteAttributeDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<SiteAttribute> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(siteAttributeDao.find(id));
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
    public Result<List<SiteAttribute>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<SiteAttribute> siteAttributeList =  siteAttributeDao.findAll(null, null);
                return ResultFactory.getSuccessResult(siteAttributeList);
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
    public Result<SiteAttribute> store(String userName, Integer id, String attributeKey, String attributeValue, String attributeType, Integer siteId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        SiteAttribute siteAttribute;

        if (id == null) 
        {
            siteAttribute = new SiteAttribute();
        } 
        else 
        {
            siteAttribute = siteAttributeDao.find(id);

            if (siteAttribute == null) 
            {
                return ResultFactory.getFailResult("Unable to find SiteAttribute instance with Id=" + id);
            }
        }

        siteAttribute.setAttributeKey(attributeKey);
        siteAttribute.setAttributeValue(attributeValue);
        siteAttribute.setAttributeType(attributeType);
        siteAttribute.setSiteId(siteId);
        
        
        if (siteAttribute.getId() == null) 
        {
            siteAttributeDao.add(siteAttribute);
        } 
        else 
        {
            siteAttribute = siteAttributeDao.update(siteAttribute);
        }

        return ResultFactory.getSuccessResult(siteAttribute);

    }
  
    @Override
    public Result<SiteAttribute> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove SiteAttribute [null id]");
        } 

        SiteAttribute siteAttribute = siteAttributeDao.find(id);

        if (siteAttribute == null) 
        {
            return ResultFactory.getFailResult("Unable to load SiteAttribute for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            siteAttributeDao.getRelatedObjects(siteAttribute);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                siteAttributeDao.remove(siteAttribute);
                
                String msg = "SiteAttribute with Id: " + siteAttribute.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("SiteAttribute is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
