


































package com.busy.engine.dao;

import com.busy.engine.entity.UserService;

public interface UserServiceDao extends IGenericDao<UserService, Integer>
{
                    
      void getRelatedServiceChargeList(UserService user_service);     
        
}
    

