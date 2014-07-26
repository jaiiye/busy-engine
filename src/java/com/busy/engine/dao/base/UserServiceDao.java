



















package com.busy.engine.dao.base;

import com.busy.engine.domain.UserService;

public interface UserServiceDao extends IGenericDao<UserService, Integer>
{
                    
      UserService getRelatedServiceChargeList(UserService user_service);     
        
}
    

