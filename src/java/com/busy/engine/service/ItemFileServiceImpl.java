





































package com.busy.engine.service;

import com.busy.engine.dao.ItemFileDao;
import com.busy.engine.dao.ItemFileDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.ItemFile;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class ItemFileServiceImpl extends AbstractService implements ItemFileService 
{
    protected ItemFileDao itemFileDao = new ItemFileDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public ItemFileServiceImpl() 
    {
        super();
    }

    @Override
    public Result<ItemFile> find(String userName, Integer id)
    {

        if (isValidUser(userName)) 
        {
            return ResultFactory.getSuccessResult(itemFileDao.find(id));
        }
        else 
        {            
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }
    
    @Override
    public Result<List<ItemFile>> findAll(String userName) 
    {
        if (isValidUser(userName)) 
        {
            List<ItemFile> itemFileList =  itemFileDao.findAll(null, null);
            return ResultFactory.getSuccessResult(itemFileList);
        } 
        else 
        {
            return ResultFactory.getFailResult(USER_INVALID);
        }
    }

    @Override
    public Result<ItemFile> store(String userName, Integer id, String fileName, String description, String label, Integer hidden, Integer itemId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        ItemFile itemFile;

        if (id == null) 
        {
            itemFile = new ItemFile();
        } 
        else 
        {
            itemFile = itemFileDao.find(id);

            if (itemFile == null) 
            {
                return ResultFactory.getFailResult("Unable to find ItemFile instance with Id=" + id);
            }
        }

        itemFile.setFileName(fileName);
        itemFile.setDescription(description);
        itemFile.setLabel(label);
        itemFile.setHidden(hidden);
        itemFile.setItemId(itemId);
        
        
        if (itemFile.getId() == null) 
        {
            itemFileDao.add(itemFile);
        } 
        else 
        {
            itemFile = itemFileDao.update(itemFile);
        }

        return ResultFactory.getSuccessResult(itemFile);

    }
  
    @Override
    public Result<ItemFile> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove ItemFile [null id]");
        } 

        ItemFile itemFile = itemFileDao.find(id);

        if (itemFile == null) 
        {
            return ResultFactory.getFailResult("Unable to load ItemFile for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            itemFileDao.getRelatedObjects(itemFile);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                itemFileDao.remove(itemFile);
                
                String msg = "ItemFile with Id: " + itemFile.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("ItemFile is used with to [" + relatedObjectNames + "] and could not be deleted");
            }
            
        }

    }

}

