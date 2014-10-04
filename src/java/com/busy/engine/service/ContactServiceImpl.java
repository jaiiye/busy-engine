


































package com.busy.engine.service;

import com.busy.engine.dao.ContactDao;
import com.busy.engine.dao.ContactDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.Contact;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Date;

public class ContactServiceImpl extends AbstractService implements ContactService 
{
    protected ContactDao contactDao;    
    protected UserDao userDao;
    protected UserRoleDao userRoleDao;
    
    public ContactServiceImpl() 
    {
        super();        
        contactDao = new ContactDaoImpl();
        userDao = new UserDaoImpl();
        userRoleDao = new UserRoleDaoImpl();
    }
    
    public ContactServiceImpl(ServletContext context) 
    {
        super();        
        contactDao = (ContactDao) context.getAttribute("contactDao");
        userDao = (UserDao) context.getAttribute("userDao");
        userRoleDao = (UserRoleDao) context.getAttribute("userRoleDao");
    }

    @Override
    public Result<Contact> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                return ResultFactory.getSuccessResult(contactDao.find(id));
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
    public Result<List<Contact>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName, userDao)) 
            {
                List<Contact> contactList =  contactDao.findAll(null, null);
                return ResultFactory.getSuccessResult(contactList);
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
    public Result<Contact> store(String userName, Integer id, String title, String firstName, String lastName, String position, String phone, String fax, String email, Integer contactStatus, String webUrl, String info)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        Contact contact;

        if (id == null) 
        {
            contact = new Contact();
        } 
        else 
        {
            contact = contactDao.find(id);

            if (contact == null) 
            {
                return ResultFactory.getFailResult("Unable to find Contact instance with Id=" + id);
            }
        }

        contact.setTitle(title);
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setPosition(position);
        contact.setPhone(phone);
        contact.setFax(fax);
        contact.setEmail(email);
        contact.setContactStatus(contactStatus);
        contact.setWebUrl(webUrl);
        contact.setInfo(info);
        
        
        if (contact.getId() == null) 
        {
            contactDao.add(contact);
        } 
        else 
        {
            contact = contactDao.update(contact);
        }

        return ResultFactory.getSuccessResult(contact);

    }
  
    @Override
    public Result<Contact> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove Contact [null id]");
        } 

        Contact contact = contactDao.find(id);

        if (contact == null) 
        {
            return ResultFactory.getFailResult("Unable to load Contact for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            contactDao.getRelatedObjects(contact);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(contact.getAffiliateList().size() != 0)
            {
                relatedObjectNames += "Affiliate ";  
                canBeDeleted = false;
            }
                        
            if(contact.getCustomerList().size() != 0)
            {
                relatedObjectNames += "Customer ";  
                canBeDeleted = false;
            }
                        
            if(contact.getItemLocationList().size() != 0)
            {
                relatedObjectNames += "ItemLocation ";  
                canBeDeleted = false;
            }
                        
            if(contact.getUserList().size() != 0)
            {
                relatedObjectNames += "User ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                contactDao.remove(contact);
                
                String msg = "Contact with Id: " + contact.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("Contact is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
