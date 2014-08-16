





















































package com.busy.engine.service;

import com.busy.engine.dao.CategoryDao;
import com.busy.engine.dao.CategoryDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.Category;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class CategoryServiceImpl extends AbstractService implements CategoryService 
{
    protected CategoryDao categoryDao = new CategoryDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public CategoryServiceImpl() 
    {
        super();
    }

    @Override
    public Result<Category> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName)) 
            {
                return ResultFactory.getSuccessResult(categoryDao.find(id));
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
    public Result<List<Category>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName)) 
            {
                List<Category> categoryList =  categoryDao.findAll(null, null);
                return ResultFactory.getSuccessResult(categoryList);
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
    public Result<Category> store(String userName, Integer id, String categoryName, Integer discountId, Integer parentCategoryId)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        Category category;

        if (id == null) 
        {
            category = new Category();
        } 
        else 
        {
            category = categoryDao.find(id);

            if (category == null) 
            {
                return ResultFactory.getFailResult("Unable to find Category instance with Id=" + id);
            }
        }

        category.setCategoryName(categoryName);
        category.setDiscountId(discountId);
        category.setParentCategoryId(parentCategoryId);
        
        
        if (category.getId() == null) 
        {
            categoryDao.add(category);
        } 
        else 
        {
            category = categoryDao.update(category);
        }

        return ResultFactory.getSuccessResult(category);

    }
  
    @Override
    public Result<Category> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove Category [null id]");
        } 

        Category category = categoryDao.find(id);

        if (category == null) 
        {
            return ResultFactory.getFailResult("Unable to load Category for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            categoryDao.getRelatedObjects(category);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                        
            if(category.getItemCategoryList().size() != 0)
            {
                relatedObjectNames += "ItemCategory ";  
                canBeDeleted = false;
            }
                          
            
            if(canBeDeleted)
            {                
                categoryDao.remove(category);
                
                String msg = "Category with Id: " + category.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("Category is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
