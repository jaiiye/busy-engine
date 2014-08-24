package com.busy.engine.service;

import com.busy.engine.dao.UserDao;
import com.busy.engine.entity.User;
import com.busy.engine.entity.UserRole;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractService 
{
    protected ArrayList<String> allowedRoles = new ArrayList<String>(Arrays.asList("admin","manager"));
    
    String USER_INVALID = "Did not provide a valid user.";
    String ROLE_INVALID = "User does not have proper credentials to perform the operation.";

    protected boolean isValidUser(String username, UserDao userDao) throws Exception
    {    
        ArrayList<User> results = userDao.findByColumn(User.PROP_USERNAME, username, null, null);
        
        if(results.isEmpty())
        {
            throw new Exception("Could not find user");
        }
        else
        {
            return  true;
        }
    }
    
    public boolean checkUserRoles(List<UserRole> roles)
    {
        boolean actionAllowed = false;
        for(String role : allowedRoles)
        {
            for(UserRole userRole : roles)
            {
                if(userRole.getRoleName().equals(role))
                {
                    actionAllowed = true;
                    break;
                }
            }
            if(actionAllowed)
            {
                break;
            }
        }
        return actionAllowed;
    }

}
