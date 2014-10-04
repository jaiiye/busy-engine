


































package com.busy.engine.service;

import com.busy.engine.dao.ReturnRequestDao;
import com.busy.engine.dao.ReturnRequestDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.ReturnRequest;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class ReturnRequestServiceImpl extends AbstractService implements ReturnRequestService 
{
    protected ReturnRequestDao returnRequestDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    
    public ReturnRequestServiceImpl() 
    {
        super();        
        returnRequestDao = new ReturnRequestDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public ReturnRequestServiceImpl(ServletContext context) 
    {
        super();        
        returnRequestDao = (ReturnRequestDao) context.getAttribute("returnRequestDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<ReturnRequest> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(returnRequestDao.find(id));
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
    public Result<List<ReturnRequest>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<ReturnRequest> returnRequestList =  returnRequestDao.findAll(null, null);
                return ResultFactory.getSuccessResult(returnRequestList);
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
    public Result<ReturnRequest> store(String userName, Integer id, Integer quantity, Date requestDate, String returnReason, String requestedAction, String notes, Integer requestStatus, Integer orderItemId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        ReturnRequest returnRequest;

        if (id == null) 
        {
            returnRequest = new ReturnRequest();
        } 
        else 
        {
            returnRequest = returnRequestDao.find(id);

            if (returnRequest == null) 
            {
                return ResultFactory.getFailResult("Unable to find ReturnRequest instance with Id=" + id);
            }
        }

        returnRequest.setQuantity(quantity);
        returnRequest.setRequestDate(requestDate);
        returnRequest.setReturnReason(returnReason);
        returnRequest.setRequestedAction(requestedAction);
        returnRequest.setNotes(notes);
        returnRequest.setRequestStatus(requestStatus);
        returnRequest.setOrderItemId(orderItemId);
        
        
        if (returnRequest.getId() == null) 
        {
            returnRequestDao.add(returnRequest);
        } 
        else 
        {
            returnRequest = returnRequestDao.update(returnRequest);
        }

        return ResultFactory.getSuccessResult(returnRequest);

    }
  
    @Override
    public Result<ReturnRequest> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove ReturnRequest [null id]");
        } 

        ReturnRequest returnRequest = returnRequestDao.find(id);

        if (returnRequest == null) 
        {
            return ResultFactory.getFailResult("Unable to load ReturnRequest for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            returnRequestDao.getRelatedObjects(returnRequest);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                returnRequestDao.remove(returnRequest);
                
                String msg = "ReturnRequest with Id: " + returnRequest.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("ReturnRequest is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
