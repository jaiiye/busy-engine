





















































package com.busy.engine.service;

import com.busy.engine.dao.MailinglistDao;
import com.busy.engine.dao.MailinglistDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.Mailinglist;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class MailinglistServiceImpl extends AbstractService implements MailinglistService 
{
    protected MailinglistDao mailinglistDao = new MailinglistDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public MailinglistServiceImpl() 
    {
        super();
    }

    @Override
    public Result<Mailinglist> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName)) 
            {
                return ResultFactory.getSuccessResult(mailinglistDao.find(id));
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
    public Result<List<Mailinglist>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName)) 
            {
                List<Mailinglist> mailinglistList =  mailinglistDao.findAll(null, null);
                return ResultFactory.getSuccessResult(mailinglistList);
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
    public Result<Mailinglist> store(String userName, Integer id, String fullName, String email, Integer listStatus, Integer userId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        Mailinglist mailinglist;

        if (id == null) 
        {
            mailinglist = new Mailinglist();
        } 
        else 
        {
            mailinglist = mailinglistDao.find(id);

            if (mailinglist == null) 
            {
                return ResultFactory.getFailResult("Unable to find Mailinglist instance with Id=" + id);
            }
        }

        mailinglist.setFullName(fullName);
        mailinglist.setEmail(email);
        mailinglist.setListStatus(listStatus);
        mailinglist.setUserId(userId);
        
        
        if (mailinglist.getId() == null) 
        {
            mailinglistDao.add(mailinglist);
        } 
        else 
        {
            mailinglist = mailinglistDao.update(mailinglist);
        }

        return ResultFactory.getSuccessResult(mailinglist);

    }
  
    @Override
    public Result<Mailinglist> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove Mailinglist [null id]");
        } 

        Mailinglist mailinglist = mailinglistDao.find(id);

        if (mailinglist == null) 
        {
            return ResultFactory.getFailResult("Unable to load Mailinglist for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            mailinglistDao.getRelatedObjects(mailinglist);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                mailinglistDao.remove(mailinglist);
                
                String msg = "Mailinglist with Id: " + mailinglist.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("Mailinglist is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
