





















































package com.busy.engine.service;

import com.busy.engine.dao.KnowledgeBaseDao;
import com.busy.engine.dao.KnowledgeBaseDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.KnowledgeBase;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class KnowledgeBaseServiceImpl extends AbstractService implements KnowledgeBaseService 
{
    protected KnowledgeBaseDao knowledgeBaseDao = new KnowledgeBaseDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public KnowledgeBaseServiceImpl() 
    {
        super();
    }

    @Override
    public Result<KnowledgeBase> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName)) 
            {
                return ResultFactory.getSuccessResult(knowledgeBaseDao.find(id));
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
    public Result<List<KnowledgeBase>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName)) 
            {
                List<KnowledgeBase> knowledgeBaseList =  knowledgeBaseDao.findAll(null, null);
                return ResultFactory.getSuccessResult(knowledgeBaseList);
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
    public Result<KnowledgeBase> store(String userName, Integer id, String knowledgeBaseName, String description, Integer rank, Date lastModified, Integer latestTopic, Integer latestPost)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        KnowledgeBase knowledgeBase;

        if (id == null) 
        {
            knowledgeBase = new KnowledgeBase();
        } 
        else 
        {
            knowledgeBase = knowledgeBaseDao.find(id);

            if (knowledgeBase == null) 
            {
                return ResultFactory.getFailResult("Unable to find KnowledgeBase instance with Id=" + id);
            }
        }

        knowledgeBase.setKnowledgeBaseName(knowledgeBaseName);
        knowledgeBase.setDescription(description);
        knowledgeBase.setRank(rank);
        knowledgeBase.setLastModified(lastModified);
        knowledgeBase.setLatestTopic(latestTopic);
        knowledgeBase.setLatestPost(latestPost);
        
        
        if (knowledgeBase.getId() == null) 
        {
            knowledgeBaseDao.add(knowledgeBase);
        } 
        else 
        {
            knowledgeBase = knowledgeBaseDao.update(knowledgeBase);
        }

        return ResultFactory.getSuccessResult(knowledgeBase);

    }
  
    @Override
    public Result<KnowledgeBase> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove KnowledgeBase [null id]");
        } 

        KnowledgeBase knowledgeBase = knowledgeBaseDao.find(id);

        if (knowledgeBase == null) 
        {
            return ResultFactory.getFailResult("Unable to load KnowledgeBase for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            knowledgeBaseDao.getRelatedObjects(knowledgeBase);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(knowledgeBase.getBlogList().size() != 0)
            {
                relatedObjectNames += "Blog ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                knowledgeBaseDao.remove(knowledgeBase);
                
                String msg = "KnowledgeBase with Id: " + knowledgeBase.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("KnowledgeBase is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
