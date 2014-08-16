





















































package com.busy.engine.service;

import com.busy.engine.dao.DashboardDao;
import com.busy.engine.dao.DashboardDaoImpl;
import com.busy.engine.dao.UserDao;
import com.busy.engine.dao.UserDaoImpl;
import com.busy.engine.dao.UserRoleDao;
import com.busy.engine.dao.UserRoleDaoImpl;
import com.busy.engine.entity.Dashboard;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import com.busy.engine.vo.Result;
import com.busy.engine.vo.ResultFactory;
import java.util.List;
import java.util.Date;

public class DashboardServiceImpl extends AbstractService implements DashboardService 
{
    protected DashboardDao dashboardDao = new DashboardDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected UserRoleDao userRoleDao = new UserRoleDaoImpl();
    

    public DashboardServiceImpl() 
    {
        super();
    }

    @Override
    public Result<Dashboard> find(String userName, Integer id)
    {
        try
        {
            if (isValidUser(userName)) 
            {
                return ResultFactory.getSuccessResult(dashboardDao.find(id));
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
    public Result<List<Dashboard>> findAll(String userName) 
    {
        try
        {
            if (isValidUser(userName)) 
            {
                List<Dashboard> dashboardList =  dashboardDao.findAll(null, null);
                return ResultFactory.getSuccessResult(dashboardList);
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
    public Result<Dashboard> store(String userName, Integer id, Integer userCount, Integer blogPostCount, Integer itemCount, Integer orderCount, Integer siteFileCount, Integer imageCount, Integer blogCount, Integer commentCount, Integer pageCount, Integer formCount, Integer sliderCount, Integer itemBrandCount, Integer categoryCount, Integer itemOptionCount)
    {        
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        Dashboard dashboard;

        if (id == null) 
        {
            dashboard = new Dashboard();
        } 
        else 
        {
            dashboard = dashboardDao.find(id);

            if (dashboard == null) 
            {
                return ResultFactory.getFailResult("Unable to find Dashboard instance with Id=" + id);
            }
        }

        dashboard.setUserCount(userCount);
        dashboard.setBlogPostCount(blogPostCount);
        dashboard.setItemCount(itemCount);
        dashboard.setOrderCount(orderCount);
        dashboard.setSiteFileCount(siteFileCount);
        dashboard.setImageCount(imageCount);
        dashboard.setBlogCount(blogCount);
        dashboard.setCommentCount(commentCount);
        dashboard.setPageCount(pageCount);
        dashboard.setFormCount(formCount);
        dashboard.setSliderCount(sliderCount);
        dashboard.setItemBrandCount(itemBrandCount);
        dashboard.setCategoryCount(categoryCount);
        dashboard.setItemOptionCount(itemOptionCount);
        
        
        if (dashboard.getId() == null) 
        {
            dashboardDao.add(dashboard);
        } 
        else 
        {
            dashboard = dashboardDao.update(dashboard);
        }

        return ResultFactory.getSuccessResult(dashboard);

    }
  
    @Override
    public Result<Dashboard> remove(String userName, Integer id)
    {
        User actionUser = userDao.findByColumn(User.PROP_USERNAME, userName, null, null).get(0);
        List<UserRole> roles = userRoleDao.findByColumn(UserRole.PROP_USER_NAME, actionUser.getUsername(), null, null);
                        
        if (! checkUserRoles(roles)) 
        {
            return ResultFactory.getFailResult(ROLE_INVALID);
        }

        if (id == null) 
        {
            return ResultFactory.getFailResult("Unable to remove Dashboard [null id]");
        } 

        Dashboard dashboard = dashboardDao.find(id);

        if (dashboard == null) 
        {
            return ResultFactory.getFailResult("Unable to load Dashboard for removal with id=" + id);
        } 
        else 
        {
            //if all related objects are empty for the given object then we can erase this object
            dashboardDao.getRelatedObjects(dashboard);
            
            String relatedObjectNames = "";
            boolean canBeDeleted = true;
            
                          
            
            if(canBeDeleted)
            {                
                dashboardDao.remove(dashboard);
                
                String msg = "Dashboard with Id: " + dashboard.getId() + " was deleted by " + userName;
                return ResultFactory.getSuccessResultMsg(msg);                
            }
            else 
            {
                return ResultFactory.getFailResult("Dashboard is used with to [" + relatedObjectNames + "] and could not be deleted");
            }            
        }
    }
}
